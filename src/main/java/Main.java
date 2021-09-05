import models.User;
import services.UserService;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]){
        UserService userService = new UserService();
        User user = new User("Pog Champovich");
        userService.saveUser(user);
    }
}
