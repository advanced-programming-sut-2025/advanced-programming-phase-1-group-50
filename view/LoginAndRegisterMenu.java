package view;

import controller.LoginAndRegisterController;
import models.app.App;
import models.app.SecurityQuestion;
import models.enums.LoginMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginAndRegisterMenu implements AppMenu {
    private final LoginAndRegisterController controller = new LoginAndRegisterController();
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        input = input.trim();
        Matcher matcher;
        if(LoginMenuCommands.Register.getMatcher(input) != null) {
            matcher = LoginMenuCommands.Register.getMatcher(input);
            String username = matcher.group(1).trim();
            String password = matcher.group(2).trim();
            String passwordConfirm = matcher.group(3).trim();
            String nickname = matcher.group(4).trim();
            String email = matcher.group(5).trim();
            String gender = matcher.group(6).trim();
            System.out.println(controller.register(username , password , passwordConfirm , nickname , email , gender ,
                    scanner));




        }
        else if(LoginMenuCommands.Login.getMatcher(input) != null) {
            matcher = LoginMenuCommands.Login.getMatcher(input);
            String username = matcher.group(1).trim();
            String password = matcher.group(2).trim();
            System.out.println(controller.login(username , password));

        }
        else if(LoginMenuCommands.ForgetPassword.getMatcher(input) != null) {
            matcher = LoginMenuCommands.ForgetPassword.getMatcher(input);
            String username = matcher.group(1).trim();
            System.out.println(controller.askSecurityQuestion(username));
            if(controller.askSecurityQuestion(username).getSuccessful()) {
                System.out.println("answer the question");
                System.out.println(controller.findUser(username).getSecurityQuestion().getQuestion());
                String answer = scanner.nextLine();
                answer = answer.trim();
                System.out.println(controller.checkAnswerQuestion(username , answer));
                if(controller.checkAnswerQuestion(username , answer).getSuccessful()){
                    System.out.println("now enter a new password");
                    String newPassword = scanner.nextLine();
                    newPassword = newPassword.trim();
                    System.out.println(controller.setNewPasswordAfterForgetPassword(username, newPassword));
                }
            }



        }
        else {
            System.out.println("invalid command");
        }





    }
}
