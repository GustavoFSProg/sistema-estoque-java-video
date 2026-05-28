/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.jdbc;

/**
 *
 * @author oem
 */
public class TestarConexao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            
            new ConexaoBanco().pegarConexao();
            
              System.out.println("DEU CERTO A CONEXÃO VIA CLASSE");
        }catch(Exception e){
              System.out.println("DEU ERRO FATAL!"+e);
        }
        
        // TODO code application logic here
    }
    
}
