package com.stardew.view.LobbyMenus;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.stardew.Main;
import com.stardew.model.LobbyDTO;
import com.stardew.model.Result;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.AppMenu;
import com.stardew.view.windows.CloseableWindow;

import java.util.HashMap;
import java.util.Random;

public class LobbyInformationWindow extends CloseableWindow implements AppMenu {

    private TextField lobbyName;
    private TextField password;
    private CheckBox privateOrPublic;
    private CheckBox visible;
    private Label showID;
    private TextButton generateID;
    private TextButton createLobby;
    private boolean generate = false;
    private int id ;


    public LobbyInformationWindow(Stage stage, Skin skin) {
        super("Lobby Information", stage);

        setSize(750, 500);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
        pad(25, 10, 15, 10);
        setColor(Color.ORANGE);
        align(Align.top);

        Table content = new Table(skin);
        content.defaults().pad(10).fillX();

        // Lobby Name
        content.add(new Label("Lobby Name:", skin)).left();
        lobbyName = new TextField("", skin);
        content.add(lobbyName).colspan(2).row();

        content.add(new Label("if you want to create a private lobby" + "\n" + "enter password here", skin)).left();
        password = new TextField("", skin);
        content.add(password).colspan(2).row();

        // Private or Public
        privateOrPublic = new CheckBox(" Private Lobby", skin);
        privateOrPublic.setChecked(true);
        content.add(privateOrPublic).left().colspan(3).row();

        // Visibility
        visible = new CheckBox(" Visible in List", skin);
        content.add(visible).left().colspan(3).row();

        // Generate ID
        generateID = new TextButton("Generate ID", skin);
        content.add(generateID).width(150).left();

        showID = new Label("ID: ---", skin);
        content.add(showID).left().expandX().colspan(2).row();

        // Create Lobby Button
        createLobby = new TextButton("Create Lobby", skin);
        createLobby.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(lobbyName.getText().isEmpty() || !generate ){
                    showResult(new Result(false , "Please fill all fields!"));
                    return;
                }
                if(privateOrPublic.isChecked() ){
                    if(password.getText().isEmpty()) {
                        showResult(new Result(false, "choose a password for your private lobby!"));
                        return;
                    }
                }
                createLobby.setDisabled(true);

                Message message = prepareCreateLobbyMessage();
                Message response = NetworkManager.getConnection().sendAndWaitForResponse(message , 500);
                if ( response == null ||response.getType() != MessageType.CREATE_LOBBY_RESULT) {
                    showResult(new Result(false, "Connection failed or timed out!"));
                    createLobby.setDisabled(false);
                    return;
                }

                Result result = response.getFromBody("result", Result.class);
                if(result.getSuccessful()){
                    System.out.println("Creating lobby");
                    LobbyDTO lobbyDTO = response.getFromBody("lobbyDTO", LobbyDTO.class);
                    String username = response.getFromBody("username", String.class);
                    Screen screen = Main.getMain().getScreen();
                    LobbyMenu lobbyMenu = new LobbyMenu(lobbyDTO , username);
                    Main.getMain().setScreen(lobbyMenu);
                    screen.dispose();
                    System.out.println("disposed");
                    return;
                    //TODO : going to the lobby menu and wait for joining players
                }
                createLobby.setDisabled(false);

            }
        });
        content.add(createLobby).center().colspan(3).padTop(30);

        add(content).expand().top();

        // Event for Generate ID
        generateID.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(generate) return;
                id = getGenerateID();
                showID.setText("ID: " + id);
                generate = true;

            }
        });
    }

    public int getGenerateID(){
        Random rand = new Random();
        return rand.nextInt(10000) + 1;
    }

    public Message prepareCreateLobbyMessage(){
        String name = lobbyName.getText();
        boolean privateLobby = privateOrPublic.isChecked();
        boolean isVisible = visible.isChecked();
        int id = this.id;
        HashMap<String , Object> body = new HashMap<>();
        body.put("name" , name);
        body.put("privacy" , privateLobby);
        body.put("visible" , isVisible);
        body.put("password" , password.getText());
        body.put("id" , id);
        return new Message(body , MessageType.CREATE_LOBBY);

    }
}
