package com.stardew.controller;

import com.google.gson.reflect.TypeToken;
import com.stardew.model.LobbyDTO;
import com.stardew.model.Result;
import com.stardew.model.ServerApp;
import com.stardew.model.userInfo.User;
import com.stardew.network.*;

import java.util.*;

public class LobbyController {
    private static LobbyController instance ;
    private final List<Lobby> lobbies = Collections.synchronizedList(new ArrayList<>());
    private final PreGameController preGameController = PreGameController.getInstance();
    private final Set<Integer> IDs = Collections.synchronizedSet(new HashSet<>());


    private LobbyController() {}

    public static synchronized LobbyController getInstance() {
        if(instance == null) {
            instance = new LobbyController();
        }
        return instance;
    }


    public Lobby findLobby(int id) {
        synchronized (lobbies) {
            for (Lobby lobby : lobbies) {
                if (lobby.getId() == id) {
                    return lobby;
                }
            }
        }
        return null;
    }

    public Lobby createLobby(boolean isPrivate , String name , String password , int id , User admin , boolean visible) {
        if(isPrivate) {
            PrivateLobby privateLobby = new PrivateLobby(id , name , admin , visible , password);
            lobbies.add(privateLobby);
            return privateLobby;
        }
        else {
            PublicLobby publicLobby = new PublicLobby(id , name , admin , visible);
            lobbies.add(publicLobby);
            return publicLobby;
        }
    }

    public void handleCreateLobby(Message message, ClientConnectionThread connection){
        String name = message.getFromBody("name");
        int id = generateUniqueId();
        boolean isPrivate = message.getFromBody("privacy");
        boolean isVisible = message.getFromBody("visible");

        User user = connection.getUser();
        String password = isPrivate ? message.getFromBody("password") : "";
        Lobby lobby = createLobby(isPrivate, name, password, id, user, isVisible);

        LobbyDTO ltd = lobby.toDTO();
        Result res = new Result(true , "lobby created");
        HashMap<String , Object> body = new HashMap<>();
        body.put("lobbyDTO" , ltd);
        body.put("username" , user.getUsername());
        body.put("result" , res);
        Message response = new Message(body , MessageType.CREATE_LOBBY_RESULT);
        connection.sendMessage(response);
    }

    public void sendLobbies(ClientConnectionThread connection){
        ArrayList<LobbyDTO> lobbyDTOS = new ArrayList<>();
        synchronized (lobbies) {
            for (Lobby lobby : lobbies) {
                LobbyDTO lobbyDTO = lobby.toDTO();
                lobbyDTOS.add(lobbyDTO);
            }
        }
        HashMap<String , Object> body = new HashMap<>();
        body.put("lobbyDTOS" , lobbyDTOS);
        Message response = new Message(body , MessageType.SEND_LOBBIES_RESULT);
        connection.sendMessage(response);
    }

    public void sendSearchLobby(Message message, ClientConnectionThread connection){
        String search = message.getFromBody("search");
        int id = Integer.parseInt(search);
        Lobby lobby = findLobby(id);
        if(lobby != null) {
            LobbyDTO lobbyDTO = lobby.toDTO();
            HashMap<String , Object> body = new HashMap<>();
            body.put("lobbyDTOS" , lobbyDTO);
            Message response = new Message(body , MessageType.SEARCH_LOBBY_RESULT);
            connection.sendMessage(response);
        }

    }

    public void handleJoinLobby(Message message, ClientConnectionThread connection){
        LobbyDTO lobbyDTO = message.getFromBody("lobbyDTO", new TypeToken<LobbyDTO>() {}.getType());

        int id = lobbyDTO.id;
        Lobby lobby = findLobby(id);
        HashMap<String, Object> body = new HashMap<>();
        if(lobby != null) {
            if(lobby.hasCapacity()) {


                if (lobby instanceof PrivateLobby privateLobby) {
                    String password = message.getFromBody("password");
                    if (!privateLobby.getPassword().equals(password)) {
                        body.put("result", new Result(false, "password does not match"));
                    } else {
                        body.put("result", new Result(true, "password matched , you joined the lobby"));
                        lobby.addUser(connection.getUser());

                    }
                } else {
                    body.put("result", new Result(true, "you are joined"));
                    lobby.addUser(connection.getUser());

                }



            }
            else{
                body.put("result", new Result(false, "lobby is full"));
            }
            body.put("lobbyDTO", lobby.toDTO());
            Message response = new Message(body, MessageType.JOIN_LOBBY_RESULT);
            connection.sendMessage(response);

            if(response.getFromBody("result" , Result.class).getSuccessful())
                sendLobbyUpdateMessageToAll(lobby);




        }
    }


    public void handleStartGame(Message message, ClientConnectionThread connection) {
        int lobbyID = message.getIntFromBody("lobbyID");
        Lobby lobby = findLobby(lobbyID);

        HashMap<String , Object> responseBody = new HashMap<>();
        Result result;

        if (lobby == null) {
            result = new Result(false, "lobby not found");
            responseBody.put("result", result);
            Message response = new Message(responseBody, MessageType.START_GAME_RESULT);
            connection.sendMessage(response);
            return;
        }

        if (lobby.getUsers().size() < 2) {
            result = new Result(false, "lobby members not enough");
            responseBody.put("result", result);
            Message response = new Message(responseBody, MessageType.START_GAME_RESULT);
            connection.sendMessage(response);
            return;
        }

        if (!lobby.getAdmin().getUsername().equals(connection.getUser().getUsername())) {
            result = new Result(false, "You are not the admin");
            responseBody.put("result", result);
            Message response = new Message(responseBody, MessageType.START_GAME_RESULT);
            connection.sendMessage(response);
            return;
        }

        result = new Result(true, "Now You are ready to start the game. Please select a farm!");
        responseBody.put("result", result);
        Message response = new Message(responseBody, MessageType.START_GAME_RESULT);
        connection.sendMessage(response);


        HashMap<String , Object> orderBody = new HashMap<>();
        orderBody.put("lobbyID" , lobbyID);
        Message order = new Message(orderBody, MessageType.GO_TO_SELECT_FARM_MENU);

        for (User lobbyUser : lobby.getUsers()) {
            ClientConnectionThread clientConnection = ServerApp.findConnection(lobbyUser.getUsername());
            if (clientConnection == null)
                System.out.println("Connection lost with user <" + lobbyUser.getUsername() + ">");
            else
                clientConnection.sendMessage(order);
        }

        preGameController.addNewPreGame(lobby);
    }

    public void leaveLobby(Message message, ClientConnectionThread connection) {
        int lobbyID = message.getIntFromBody("lobbyID");
        Lobby lobby = findLobby(lobbyID);
        User user = connection.getUser();
        if(lobby != null) {
            lobby.removeUser(user);
            if(lobby.getAdmin().getUsername().equals(user.getUsername())) {
                if(!lobby.getUsers().isEmpty()) {
                    lobby.setAdmin(lobby.getUsers().getFirst());
                }
                else {
                    lobbies.remove(lobby);
                    removeId(lobby.getId());
                }
            }
            HashMap<String , Object> responseBody = new HashMap<>();
            responseBody.put("result" , new Result(true, "You left lobby successfully"));
            Message response = new Message(responseBody, MessageType.LEAVE_LOBBY_RESULT);
            connection.sendMessage(response);


            sendLobbyUpdateMessageToAll(lobby);

        }
    }

    public void handleDestroyLobby(Message message, ClientConnectionThread connection) {
        int lobbyID = message.getIntFromBody("lobbyID");
        Lobby lobby = findLobby(lobbyID);
        if(lobby != null) {
            lobbies.remove(lobby);
            removeId(lobby.getId());
            HashMap<String , Object> responseBody = new HashMap<>();
            responseBody.put("result" , new Result(true, "lobby successfully destroyed"));
            Message response = new Message(responseBody, MessageType.DESTROY_LOBBY_RESULT);
            connection.sendMessage(response);
        }
    }

    private void sendLobbyUpdateMessageToAll(Lobby lobby) {
        HashMap<String , Object> orderBody = new HashMap<>();
        orderBody.put("lobbyID" , lobby.getId());
        orderBody.put("lobbyDTO" , lobby.toDTO());
        Message order = new Message(orderBody, MessageType.LOBBY_PLAYERS_LIST_UPDATED);
        for(User u : lobby.getUsers()) {
            ClientConnectionThread clientConnection = ServerApp.findConnection(u.getUsername());
            if(clientConnection == null)
                System.out.println("Connection lost with user <" + u.getUsername() + ">");
            else
                clientConnection.sendMessage(order);
        }
    }

    public void sendOnlineUsers(ClientConnectionThread connection) {
        ArrayList<String> onlineUsers = new ArrayList<>();
        synchronized (ServerApp.getClientConnectionThreads()) {
            for (ClientConnectionThread cl : ServerApp.getClientConnectionThreads()) {
                if (cl.getUser() != null)
                    onlineUsers.add(cl.getUser().getUsername());
            }
        }
        HashMap<String , Object> responseBody = new HashMap<>();
        responseBody.put("onlineUsers", onlineUsers);
        Message response = new Message(responseBody, MessageType.SEND_ONLINE_USERS_RESULT);
        connection.sendMessage(response);
    }

    public int generateUniqueId() {
        Random rand = new Random();
        int id;
        do {
            id = rand.nextInt(9000) + 1000;
        } while (IDs.contains(id));
        IDs.add(id);
        return id;
    }

    public void removeId(int id) {
        IDs.remove(id);
    }

}
