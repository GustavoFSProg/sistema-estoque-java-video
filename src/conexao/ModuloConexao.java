/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;



import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author oem
 */
public class  ModuloConexao {
    
    public static Connection conector(){
    java.sql.Connection conexao = null;    
       
    PreparedStatement pst = null;
    ResultSet rs = null;
    
        
//        
//        String url = "jdbc:postgresql://@localhost:5432/JAVA","borgir", "jogu3340S"; // Replace with your host, port, and database name
//    String user = "borgir"; // Replace with your PostgreSQL username
//    String password = "jogu3340"; // Replace with your PostgreSQL password

        
//            Connection connection = null;
    try {
        conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ESTOQUE", "borgir", "jogu3340");
       
               System.out.println("DEU CERTO A CONEXÃO");

            
            return conexao;

       }catch(Exception e){
           System.out.println("error::");
           System.out.println(e);

            return null;
    }
        // TODO code application logic here
    }
    
    
   
    
    
    
}
