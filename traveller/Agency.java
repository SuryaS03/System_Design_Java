package traveller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Agency {
    public static  Scanner sc=new Scanner(System.in);
    public static  Connection con=DBConnection.getConnection();
    public static  void agencyInfo() throws SQLException {
//    Connection con= ReservationSystem.DBConnection.getConnection();
        String query="select * from agency";
        Statement st= con.createStatement();
        ResultSet rs=st.executeQuery(query);
        while(rs.next()){
            System.out.println("*****************************************");
            System.out.println("Agency Id :"+rs.getInt(1));
            System.out.println("Agency  :"+rs.getString(2));
        }

        System.out.println("*****************************************");

        query="select count(agency_id) from agency";
        st= con.createStatement();
        rs=st.executeQuery(query);
        rs.next();
        int totalAgency=rs.getInt(1);
        int agencyPref;
        do{

            System.out.print("Enter Your choice of Agency Id :");
            agencyPref=sc.nextInt();
            System.out.println();
            if(agencyPref>=1 && agencyPref<=totalAgency)
            {
                Package pg=new Package();
                pg.disPackageInfo(agencyPref);
            }
            else
                System.out.println("Invalid Option ");

        }
        while(agencyPref<1 || agencyPref>totalAgency);

    }
}
