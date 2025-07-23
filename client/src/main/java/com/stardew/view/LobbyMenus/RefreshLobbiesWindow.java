package com.stardew.view.LobbyMenus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.stardew.model.LobbyDTO;
import com.stardew.models.GameAssetManagers.GamePictureManager;

import com.stardew.view.windows.CloseableWindow;

import java.util.ArrayList;

public class RefreshLobbiesWindow extends CloseableWindow {
    private final Table lobbyListTable;

    public RefreshLobbiesWindow(Stage stage) {
        super("refresh" , stage);

        setSize(750, 500);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
        pad(25, 10, 15, 10);
        setColor(Color.ORANGE);
        align(Align.top);



        lobbyListTable = new Table();
        lobbyListTable.top().left();
        add(lobbyListTable).expand().fill();


    }

    public void updateLobbyList(ArrayList<LobbyDTO> lobbies){
        lobbyListTable.clear();
        for(LobbyDTO lobby : lobbies){
            if(lobby.isVisible ){
                if(!lobby.isPrivate){
                    String line = String.format("ID: %d | Name: %s | PlayersSize : %d | Admin : %s" ,
                        lobby.id , lobby.name , lobby.players.size() , lobby.adminUsername);

                    Label label = new Label(line, GamePictureManager.skin);
                    lobbyListTable.add(label).left().padBottom(10).row();
                }
                else {
                    String line = String.format("Name: %s | Admin: %s" , lobby.name , lobby.adminUsername);
                    Label label = new Label(line, GamePictureManager.skin);
                    lobbyListTable.add(label).left().padBottom(10).row();

                }
            }

        }

    }
}
