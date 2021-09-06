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
        System.out.println("Enter Command:");
        Scanner in = new Scanner(System.in);
        String op = in.nextLine();
        System.out.println(op);
        do{
            controller.MasterOperation(op);

            System.out.println("Enter Command:");
            op = in.nextLine();
            System.out.println(op);
        } while (!op.equals("quit"));
    }
}
