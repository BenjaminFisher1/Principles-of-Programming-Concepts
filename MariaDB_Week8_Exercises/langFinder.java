import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class langFinder{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mariadb://localhost:3306/nation";
        String user="bot";
        String password="ruffle";

        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement stmt = conn.createStatement();
            System.out.println("Connected to MariaDB!");
            System.out.println("Please enter your language, and this program will return nations who speak that language, unoffical and official.");
            String userLang = sc.nextLine();

            //close as soon as we are done scanning.
            sc.close();

            String query = "SELECT c.name as name FROM countries c JOIN (SELECT l.*, cl.country_id FROM country_languages cl JOIN languages l ON l.language_id = cl.language_id WHERE l.language='" + userLang + "' GROUP BY cl.country_id) AS q ON q.country_id = c.country_id;";
            
            ResultSet rs = stmt.executeQuery(query);
            System.out.print("Conutries which speak " + userLang);
            while(rs.next()){
                System.out.print(rs.getString("name") + "\n");
            }

        } catch (SQLException e){
            System.err.println("Database connection failed");
            e.printStackTrace();
        }
    }
}