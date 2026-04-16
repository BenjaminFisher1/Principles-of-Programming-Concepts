import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Week_13_Lab {
	public static void main(String[] args) {
		if (args.length < 3) {
			System.err.println("give username, password, and dbname as command line arguments.");
			return;
		}

		String url = "jdbc:mariadb://localhost:3306/" + args[2];
		String user = args[0];
		String password = args[1];

		List<Employee> employeesList = new ArrayList<Employee>();

	// Q1:
		try (Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT id, name, salary FROM employees ORDER BY id")) {

			System.out.println("Connected to MariaDB!");
			System.out.println("Grabbing Employee data");

			addEmployees(rs, employeesList);

	    // Q2: 
			System.out.println("All Employees");
			employeesList.stream().forEach(System.out::println);

	    // Q3: 
			Predicate<Employee> highEarnerPredicate = employee -> employee.getSalary() > 50000.0;


			List<Employee> highEarners = employeesList.stream()
					.filter(highEarnerPredicate)
					.collect(Collectors.toList());

	    // Q4:
			System.out.println();
			System.out.println("High Earners");
			highEarners.stream().forEach(System.out::println);

	    // Q5:
			Function<Employee, Employee> applyTax = employee -> new Employee(
					employee.getId(),
					employee.getName(),
					employee.getSalary() * 0.85
			);

	    // Q6: 
			Function<Employee, String> formatSalary = employee -> String.format("$%.2f", employee.getSalary());

	    // Q7: 
			List<Employee> taxedHighEarners = highEarners.stream()
					.map(applyTax)
					.collect(Collectors.toList());

	    // Q7:
			System.out.println();
			System.out.println("High Earners After 15% Tax");
			taxedHighEarners.stream()
					.map(employee -> employee.getName() + " " + formatSalary.apply(employee))
					.forEach(System.out::println);

	    // Q8:
			System.out.println();
			System.out.println("Extra: Taxed and formatted employees by salary band");
			    Map<Boolean, List<Employee>> partitionedEmployees = employeesList.stream()
				    .collect(Collectors.partitioningBy(employee -> employee.getSalary() > 50000.0));

			    partitionedEmployees.entrySet().stream()
				    .flatMap(entry -> entry.getValue().stream().map(employee -> {
					// entry.getKey() is true for salary > 50000 (15% tax => 0.85), false otherwise (10% tax => 0.90).
					double taxMultiplier = entry.getKey() ? 0.85 : 0.90;
					Employee taxedEmployee = new Employee(
						employee.getId(),
						employee.getName(),
						employee.getSalary() * taxMultiplier
					);
					return taxedEmployee.getName() + " " + formatSalary.apply(taxedEmployee);
				    }))
					.forEach(System.out::println);

		} catch (SQLException e) {
			System.err.println("Database connection failed");
			e.printStackTrace();
		}
	}

	static void addEmployees(ResultSet rs, List<Employee> employeesList) throws SQLException {
		if (!rs.next()) {
			return;
		}

		Employee employee = new Employee(
				rs.getInt("id"),
				rs.getString("name"),
				rs.getDouble("salary")
		);
		employeesList.add(employee);
		addEmployees(rs, employeesList);
	}
}

class Employee {
	private int id;
	private String name;
	private double salary;

	public Employee(int id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public String toString() {
		return String.format("Employee{id=%d, name='%s', salary=%.2f}", id, name, salary);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}
}

