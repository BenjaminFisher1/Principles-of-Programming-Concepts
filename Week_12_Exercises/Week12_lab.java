import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Week12_lab {
    public static void main(String[] args){
        if(args.length < 3){
            System.err.println("give username, password, and dbname as command line arguments.");
            return;
        }

        String url = "jdbc:mariadb://localhost:3306/" + args[2];
        String user = args[0];
        String password = args[1];

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            System.out.println("Connected to MariaDB!");
            System.out.println("Grabbing SalesPerson data");

            // Query to get salesman info with total sales
            String query = "SELECT s.name, s.city, s.commission, COALESCE(SUM(o.purchase_amt), 0) as totalSales " +
                          "FROM salesman s " +
                          "LEFT JOIN orders o ON s.salesman_id = o.salesman_id " +
                          "GROUP BY s.salesman_id, s.name, s.city, s.commission;";

            ResultSet rs = stmt.executeQuery(query);

            ArrayList<SalesPerson> salesPersonList = new ArrayList<>();
            addSalesPersons(rs, salesPersonList);

            System.out.println("SalesPersons and Total Earnings");
            System.out.println("Name Earnings");
            salesPersonList.stream()
                    .map(person -> person.name + " " + person.totalSales)
                    .forEach(System.out::println);

            System.out.println();
            System.out.println("SalesPersons and Total Commissions");
            System.out.println("Name Commission");
            salesPersonList.stream()
                    .map(person -> person.name + " " + (person.totalSales * person.commission))
                    .forEach(System.out::println);

        } catch (SQLException e){
            System.err.println("Database connection failed");
            e.printStackTrace();
        }
    }

    static void addSalesPersons(ResultSet rs, ArrayList<SalesPerson> salesPersonList) throws SQLException {
        if(!rs.next()){
            return;
        }

        SalesPerson person = new SalesPerson(
            rs.getString("name"),
            rs.getString("city"),
            rs.getDouble("commission"),
            rs.getDouble("totalSales")
        );
        salesPersonList.add(person);
        addSalesPersons(rs, salesPersonList);
    }
}

class SalesPerson {
    String name;
    String city;
    double commission;
    double totalSales;

    SalesPerson(String name, String city, double commission, double totalSales){
        this.name = name;
        this.city = city;
        this.commission = commission;
        this.totalSales = totalSales;
    }
}

//Sample Run:

// java -cp .:mariadb-java-client-3.5.7.jar Week12_lab.java b PASSWORDHERE labwork
// Connected to MariaDB!
// Grabbing SalesPerson data
// SalesPersons and Total Earnings
// Name Earnings
// Alice Johnson 6950.0
// Bob Smith 3350.0
// Carol White 240.0
// David Brown 620.0
// Eva Green 3580.0

// SalesPersons and Total Commissions
// Name Commission
// Alice Johnson 1042.5
// Bob Smith 402.0
// Carol White 43.2
// David Brown 62.0
// Eva Green 716.0

