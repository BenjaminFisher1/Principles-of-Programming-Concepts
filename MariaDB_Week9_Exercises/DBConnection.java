import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.ArrayList;

public class DBConnection {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mariadb://localhost:3306/";
        String user;
        String password;

        System.out.print("Enter your username: ");
        user = sc.nextLine();
        System.out.print("Enter your password: ");
        password = sc.nextLine();
        System.out.println("Enter db name:");
        //append db name to url
         url += sc.nextLine();

         //close as soon as we are done scanning.
            sc.close();


        //try to connect to db 
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement stmt = conn.createStatement();
            System.out.println("Connected to MariaDB!");
            System.out.println("Grabbing Sales.");
           

            
            //join all three tables and get what we need to construct sales objects.
            String query1 = "SELECT o.order_no, c.customer_name, c.city, s.name, o.purchase_amt,s.commission FROM orders o JOIN customer c ON o.customer_id = c.customer_id JOIN salesman s ON o.salesman_id = s.salesman_id;";
            
            ResultSet rs = stmt.executeQuery(query1);
            
            //store sales objects 
            ArrayList<Sales> salesList = new ArrayList<>();

            //while there are more results, create a new sales object and add it to the arraylist
            while(rs.next()){
                Sales sale = new Sales(rs.getInt("order_no"), rs.getString("customer_name"), rs.getString("city"), rs.getString("name"), rs.getDouble("purchase_amt"), rs.getDouble("commission"));
                salesList.add(sale);
            }
            
            // iterate arraylist and Print all sales objects
            for(Sales sale : salesList){
                System.out.println(sale.orderNumber + " " + sale.customerName + " " + sale.customerCity + " " + sale.salesmanName + " " + sale.amount + " " + sale.commissionAmount);
            }
        } catch (SQLException e){
            System.err.println("Database connection failed");
            e.printStackTrace();
        }
    }
}

class Sales{
    int orderNumber;
    String customerName;
    String customerCity;
    String salesmanName;
    double amount;
    //commission amount is amount * commission rate
    double commissionAmount;

    Sales(int orderNumber, String customerName, String customerCity, String salesmanName, double amount, double commissionRate){
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.customerCity = customerCity;
        this.salesmanName = salesmanName;
        this.amount = amount;
        this.commissionAmount = amount * commissionRate;
    }
}