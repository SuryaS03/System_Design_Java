package traveller;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.*;
public class Customer {
    public static  int cus_id;
    public  void cus_id_setter(int cus_id){
        this.cus_id=cus_id;
    }
    public  int cus_id_getter(){
        return cus_id;
    }
  static   Scanner sc=new Scanner(System.in);
  static Connection con=DBConnection.getConnection();
    public static void new_user() throws SQLException {
        System.out.println("Enter your Name");
        String cus_name = sc.nextLine();
        System.out.println("Enter your Password");
        System.out.println("Password should not contain \",., ,~");
        String cus_pass = sc.next();
        System.out.println("Enter Mobile Number");
        String mob_no = sc.next();

        String query = "INSERT INTO customer (customer_name, cus_password, mobile_no) VALUES (?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, cus_name);
        pst.setString(2, cus_pass);
        pst.setString(3, mob_no);

        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int customerId = generatedKeys.getInt(1);
                    System.out.println("A new user has been created successfully.");
                    System.out.println("Generated Customer ID: " + customerId);
                    System.out.println("Do not forget the customer ID");
                }
            }
        } else {
            System.out.println("Process failed.");
        }
    }


    public static void login() throws SQLException {

        System.out.println("Enter Your Customer Id");
        int cus_id=sc.nextInt();
        System.out.println("Enter the Password");
        String pass=sc.next();
        String query = "SELECT customer_id FROM customer WHERE customer_id = ? AND cus_password = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, cus_id);
        pst.setString(2, pass);

        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            System.out.println("Successfully logged in");
            Customer obj=new Customer();
            obj.cus_id_setter( cus_id);
        }
        else {
            System.out.println("Invalid Credentials");
            while(true){
            System.out.println("1.Login Again");
            System.out.println("2.New User");
            int pref=sc.nextInt();
            if(pref==1)
            {
                Customer.login();
                break;
            }
            else if(pref==2){
                Customer.new_user();
                break;
            }else
            {
                System.out.println("Invalid Choice");
            }
            }

        }


    }
}
