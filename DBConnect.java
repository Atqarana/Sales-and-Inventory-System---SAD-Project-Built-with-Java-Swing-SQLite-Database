/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesandinventorymanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author alger
 */
public class DBConnect {
    Connection conn = null;
    public static Connection DBConnect(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:salesandinventory");
            return conn;
        }catch(Exception ex){
            System.out.println("Error" + ex);
            return null;
        }
        
    }
    
}
