package controllers;

import models.User;
import services.UserService;
import views.View;

import java.util.List;

public class Controller {
    private static Controller instance;
    private View view;
    private UserService userService = new UserService();
    private User user;

    private Controller(){
        view = View.getInstance();
    }

    public static Controller getInstance() {
        if(instance == null) instance = new Controller();
        return instance;
    }

    public int Login(String login, String password){
        if(user != null){
            view.FailedLogin("already login as "+user.getName());
            return -2;
        }

        List<User> users = userService.findAllUsers();

        for (User currUser: users) {
            if(currUser.getName() == login){
                if(currUser.getPassword() == password){
                    user = currUser;
                    view.SuccessfulLogin(user);
                    return 0;
                }
                view.FailedLogin("wrong password");
                return -3;
            }
        }
        view.FailedLogin("wrong login");
        return -1;
    }

    public int Register(String login, String password){
        if(user != null){
            return -2;
        }

        List<User> users = userService.findAllUsers();

        for (User currUser: users) {
            if(currUser.getName() == login){
                return -1;
            }
        }

        user = new User(login, password);
        userService.saveUser(user);
        return 0;
    }

    public int EditPassword(String password){
        if(user == null){
            return -2;
        }
        user.setPassword(password);
        userService.updateUser(user);
        return 0;
    }

    public int Logout(){
        if(user == null){
            view.OperationFailed("available only for authorized users");
            return -2;
        }

        user = null;
        return 0;
    }

    public int GetAllUsers(){
        if(user == null){
            view.OperationFailed("available only for authorized users");
            return -2;
        }

        view.getUserList();
        return 0;
    }
}
