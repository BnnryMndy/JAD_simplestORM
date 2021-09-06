package controllers;

import models.User;
import services.UserService;
import views.View;

import java.util.List;

public class Controller {
    private static final Controller instance = new Controller();
    private View view;
    private UserService userService = new UserService();
    private User user;

    private Controller(){
        view = View.getInstance();
    }

    public static Controller getInstance() {
//        if(instance == null) instance = new Controller();
        return instance;
    }

    public int MasterOperation(String opr){
        String[] op = opr.split(" ");
        if(op[0].trim().equals("login")){
            this.Login(op[1], op[2]);
            return 0;
        }

        if(op[0].trim().equals("register")){
            this.Register(op[1], op[2]);
            return 0;
        }
        if(op[0].trim().equals("logout")){
            this.Logout();
            return 0;
        }
        if(op[0].trim().equals("editPassword") ){
            this.EditPassword(op[1]);
            return 0;
        }
        if (op[0].trim().equals("getUsersList")){
            this.GetAllUsers();
            return 0;
        }

        view.OperationFailed("operation not found");
        return -1;
    }

    public int Login(String login, String password){
        if(user != null){
            view.FailedLogin("already login as "+user.getName());
            return -2;
        }

        List<User> users = userService.findAllUsers();

        for (User currUser: users) {
            if(currUser.getName().equals(login)){
                if(currUser.getPassword().equals(password)){
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
            if(currUser.getName().equals(login)){
                return -1;
            }
        }

        user = new User(login, password);
        userService.saveUser(user);
        view.OperationSuccess();
        return 0;
    }

    public int EditPassword(String password){
        if(user == null){
            return -2;
        }
        user.setPassword(password);
        userService.updateUser(user);
        view.OperationSuccess();
        return 0;
    }

    public int Logout(){
        if(user == null){
            view.OperationFailed("available only for authorized users");
            return -2;
        }

        user = null;
        view.OperationSuccess();
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
