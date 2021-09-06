package views;

import models.User;
import services.UserService;

import java.util.List;

public class View {
    private static View instance;
    private UserService userService = new UserService();

    private View(){}

    public static View getInstance() {
        if(instance == null) instance = new View();
        return instance;
    }

    public void getUserList(){
        List<User> users = userService.findAllUsers();
        System.out.println("Users:");
        for (User user: users) {
            System.out.println(Integer.toString(user.getId())+"\t"+user.getName());
        }
    }

    public void SuccessfulLogin(User user){
        System.out.println("successful login as "+user.getName());
    }

    public void FailedLogin(String error){
        System.out.println("Login failed: "+ error);
    }

    public void OperationFailed(String error){
        System.out.println("operation failed: " + error);
    }


}
