/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author oem
 */
public class ConexaoBanco {
    
    public Connection pegarConexao(){
                    Connection connection = null;
                    
    try {       
        
        
      DriverManager.getConnection("jdbc:postgresql://localhost:5432/ESTOQUE", "borgir", "jogu3340");
       
               
                     System.out.println("DEU CERTO A CONEXÃO");
                     
                     return null;
              
            

       }catch(Exception e){
           System.out.println("error::");
           System.out.println(e);

            return null;
    }
        // TODO code application logic here
    }
    }
    

