package view;

import java.util.Scanner;
import java.util.regex.*;

import controller.ProfileMenuController;
public class ProfileMenu implements AppMenu {
    private final  ProfileMenuController controller = new ProfileMenuController();
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        input = input.trim();
        Matcher matcher ;


        if(models.enums.ProfileMenuCommands.ShowCurrentMenu.getMatcher(input)!=null){
            System.out.println("profile menu");
        }
        else if(models.enums.ProfileMenuCommands.ChangePassowrd.getMatcher(input)!=null){
            matcher =models.enums.ProfileMenuCommands.ChangePassowrd.getMatcher(input);
            String newPassword = matcher.group(1).trim();
            String oldPassword = matcher.group(2).trim();
            System.out.println(controller.changePassword(oldPassword, newPassword));

        }
        else if(models.enums.ProfileMenuCommands.ChangeUsername.getMatcher(input)!=null){
            matcher = models.enums.ProfileMenuCommands.ChangeUsername.getMatcher(input);
            String newUsername = matcher.group(1).trim();;
            System.out.println(controller.changeUsername(newUsername));
        }
        else if(models.enums.ProfileMenuCommands.ChangeNickname.getMatcher(input)!=null){
            matcher = models.enums.ProfileMenuCommands.ChangeNickname.getMatcher(input);
            String newNickname = matcher.group(1).trim();
            System.out.println(controller.changeNickname(newNickname));
        }
        else if(models.enums.ProfileMenuCommands.ChangeEmail.getMatcher(input)!=null){
            matcher = models.enums.ProfileMenuCommands.ChangeEmail.getMatcher(input);
            String newEmail = matcher.group(1).trim();
            System.out.println(controller.changeEmail(newEmail));

        }
        else if(models.enums.ProfileMenuCommands.UserInfo.getMatcher(input)!=null){
          System.out.println(controller.showUserInfo());  
        }
        else {
            System.out.println("invalid command");
        }

    }
}
