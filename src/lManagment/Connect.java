package lManagment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.DriverManager;
import java.sql.*;

/**
 *
 * @author Y.S
 */
public class Connect {
   static Connection con;
    
    static Connection getConnection(){
        
        String url="jdbc:mysql://localhost:3306/library_ms";
        String username="root";
        String pa="";
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
        con=DriverManager.getConnection(url,username,pa);
        }catch(Exception e){
            e.printStackTrace();
           
    }
     return con;
}

}