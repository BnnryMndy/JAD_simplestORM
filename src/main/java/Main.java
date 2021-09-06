import models.User;
import services.UserService;
import views.View;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]){
        View view = View.getInstance();
        UserService userService = new UserService();
//        User user = new User("Pog Champovich");
//        userService.saveUser(user);

        view.getUserList();
    }
}
