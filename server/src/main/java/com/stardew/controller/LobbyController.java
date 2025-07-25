package com.stardew.controller;

import com.google.gson.reflect.TypeToken;
import com.stardew.model.LobbyDTO;
import com.stardew.model.Result;
import com.stardew.model.ServerApp;
import com.stardew.model.userInfo.User;
import com.stardew.network.*;

import java.util.ArrayList;
import java.util.HashMap;

public class LobbyController {
    private static LobbyController instance ;
    private final ArrayList<Lobby> lobbies = new ArrayList<>();
    private final PreGameController preGameController = PreGameController.getInstance();


    private LobbyController() {}

    public static synchronized LobbyController getInstance() {
        if(instance == null) {
            instance = new LobbyController();
        }
        return instance;
    }


    public Lobby findLobby(int id) {
        for (Lobby lobby : lobbies) {
            if (lobby.getId() == id) {
                return lobby;
            }
        }
        return null;
    }

    public void createLobby(boolean isPrivate , String name , String password , int id , User admin , boolean visible) {
        if(isPrivate) {
            PrivateLobby privateLobby = new PrivateLobby(id , name , admin , visible , password);
            lobbies.add(privateLobby);
        }
        else {
            PublicLobby publicLobby = new PublicLobby(id , name , admin , visible);
            lobbies.add(publicLobby);
        }
    }

    public boolean joinLobby(int id , User user , String password ) {
        Lobby lobby = findLobby(id);
        if(lobby != null) {
            if(lobby instanceof PrivateLobby privateLobby) {
                if(privateLobby.getPassword().equals(password)) {
                    privateLobby.addUser(user);
                    return true;
                }
            }
            else if(lobby instanceof PublicLobby publicLobby) {
                publicLobby.addUser(user);
                return true;
            }
        }
        return false;
    }

    public void removeLobby(int id) {
        Lobby lobby = findLobby(id);
        if(lobby != null) {
            lobbies.remove(lobby);
        }
    }

    public void handleCreateLobby(Message message, ClientConnectionThread connection){
        String name = message.getFromBody("name");
        int id = message.getIntFromBody("id");
        boolean isPrivate = message.getFromBody("privacy");
        boolean isVisible = message.getFromBody("visible");

        User user = connection.getUser();
        if(!isPrivate) {
            createLobby(isPrivate , name , "", id , user , isVisible);
        }
        else {
            String password = message.getFromBody("password");
            createLobby(isPrivate , name , password, id , user , isVisible);
        }
        Lobby l = lobbies.getLast();
        LobbyDTO ltd = l.toDTO();
        Result res = showMessageOfCreatingLobby();
        HashMap<String , Object> body = new HashMap<>();
        body.put("lobbyDTO" , ltd);
        body.put("result" , res);
        Message response = new Message(body , MessageType.CREATE_LOBBY_RESULT);
        connection.sendMessage(response);
    }

    public Result showMessageOfCreatingLobby(){
        return new Result(true , "lobby created");
    }

    public ArrayList<Lobby> getLobbies() {
        return lobbies;
    }

    public void sendLobbies(ClientConnectionThread connection){
        ArrayList<LobbyDTO> lobbyDTOS = new ArrayList<>();
        for(Lobby lobby : lobbies) {
            LobbyDTO lobbyDTO = lobby.toDTO();
            lobbyDTOS.add(lobbyDTO);
        }
        HashMap<String , Object> body = new HashMap<>();
        body.put("lobbyDTOS" , lobbyDTOS);
        Message response = new Message(body , MessageType.SEND_LOBBIES_RESULT);
        connection.sendMessage(response);
    }

    public void sendSearchLobby( Message message , ClientConnectionThread connection){
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
        if(lobby != null) {

            HashMap<String , Object> body = new HashMap<>();
            if(lobby instanceof PrivateLobby privateLobby) {
                String password = message.getFromBody("password");
                if(!privateLobby.getPassword().equals(password)) {
                    body.put("result" , new Result(false, "password does not match"));
                }
                else {
                    body.put("result" , new Result(true, "password matched , you joined the lobby"));
                    lobby.addUser(connection.getUser());
                }
            }
            else {
                body.put("result" , new Result(true , "you are joined"));
                lobby.addUser(connection.getUser());
            }

            body.put("lobbyDTO" , lobby.toDTO());
            Message response = new Message(body , MessageType.JOIN_LOBBY_RESULT);
            connection.sendMessage(response);

            HashMap<String , Object> body2 = new HashMap<>();
            body2.put("lobbyID" , lobbyDTO.id);
            body2.put("players" , lobby.getUsernameOfUsers());
            Message response2 = new Message(body2 , MessageType.LOBBY_PLAYERS_LIST_UPDATED);
            for(User u : lobby.getUsers()) {
                ClientConnectionThread clientConnectionThread = ServerApp.findConnection(u.getUsername());
                if(clientConnectionThread == null) {
                    System.out.println("Connection to " + u.getUsername() + " failed");
                }
                else {
                    clientConnectionThread.sendMessage(response2);
                }
            }
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
}
