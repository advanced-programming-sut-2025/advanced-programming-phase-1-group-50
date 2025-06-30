package com.stardew.view;

import java.util.Scanner;
import java.util.regex.*;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.controller.ProfileMenuController;
import com.stardew.models.app.App;
import com.stardew.models.app.Menus;
import com.stardew.models.enums.ProfileMenuCommands;

public class ProfileMenu implements AppMenu {
    private final  ProfileMenuController controller = new ProfileMenuController();
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        input = input.trim();
        Matcher matcher ;


        if(com.stardew.models.enums.ProfileMenuCommands.ShowCurrentMenu.getMatcher(input)!=null){
            System.out.println("profile menu");
        }
        else if(ProfileMenuCommands.ChangePassword.getMatcher(input)!=null){
            matcher = com.stardew.models.enums.ProfileMenuCommands.ChangePassword.getMatcher(input);
            String newPassword = matcher.group(1).trim();
            String oldPassword = matcher.group(2).trim();
            System.out.println(controller.changePassword(oldPassword, newPassword));

        }
        else if(com.stardew.models.enums.ProfileMenuCommands.ChangeUsername.getMatcher(input)!=null){
            matcher = com.stardew.models.enums.ProfileMenuCommands.ChangeUsername.getMatcher(input);
            String newUsername = matcher.group(1).trim();;
            System.out.println(controller.changeUsername(newUsername));
        }
        else if(com.stardew.models.enums.ProfileMenuCommands.ChangeNickname.getMatcher(input)!=null){
            matcher = com.stardew.models.enums.ProfileMenuCommands.ChangeNickname.getMatcher(input);
            String newNickname = matcher.group(1).trim();
            System.out.println(controller.changeNickname(newNickname));
        }
        else if(com.stardew.models.enums.ProfileMenuCommands.ChangeEmail.getMatcher(input)!=null){
            matcher = com.stardew.models.enums.ProfileMenuCommands.ChangeEmail.getMatcher(input);
            String newEmail = matcher.group(1).trim();
            System.out.println(controller.changeEmail(newEmail));

        }
        else if(com.stardew.models.enums.ProfileMenuCommands.UserInfo.getMatcher(input)!=null){
          System.out.println(controller.showUserInfo());
        }
        else if(com.stardew.models.enums.ProfileMenuCommands.ExitMenu.getMatcher(input)!=null){
            App.setMenu(Menus.MainMenu);
        }
        else {
            System.out.println("invalid command");
        }

    }

    @Override
    public Stage getStage() {
        return null;
    }
}
