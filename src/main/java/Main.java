import controllers.Controller;
import models.User;
import services.UserService;
import views.View;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        View view = View.getInstance();
        Controller controller = Controller.getInstance();

        Scanner in = new Scanner(System.in);
        String[] op = in.next().split(" ");
        System.out.println(op[0]);
        do{
            controller.MasterOperation(op);
            System.out.println("Enter Command:");
            op = in.next().split(" ");
            System.out.println(op[0]);
        } while (op[0] != "quit");
    }
}
