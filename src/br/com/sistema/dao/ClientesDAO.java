/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import br.com.sistema.model.Clientes;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author oem
 */
public class ClientesDAO {
    
    private Connection conn;
    
    public ClientesDAO(){
        this.conn = new  ConexaoBanco().pegarConexao();
        
    }
    
    public void Salvar(Clientes obj){
              
                
        try { 
              String sql = "insert into  tb_clientes (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade,estado) values( ?, ?, ? , ?, ?, ? ,? , ? , ?,?,?,?,?)";
              PreparedStatement stmt =    conn.prepareStatement(sql);
            
            stmt.setString(1, obj.getNome());
            
              stmt.setString(2, obj.getRG());   
            stmt.setString(3, obj.getCPF());       
            stmt.setString(4, obj.getEmail()); 
            stmt.setString(5, "00");        
            stmt.setString(6, obj.getCelular());   
            stmt.setString(7, obj.getCEP());
            stmt.setString(8, obj.getEndereco()); 
            stmt.setString(9,obj.getNumero());    
            stmt.setString(10,obj.getComplemento());            
            stmt.setString(11,obj.getBairro());   
            stmt.setString(12, obj.getCidade());  
            stmt.setString(13, obj.getEstado()); 
//            stmt.setString(13, Estado.getSelectedItem().toString()); 
            
            stmt.execute();
            
            stmt.close();
            
               JOptionPane.showMessageDialog(null,"Cliente Cadastrado com sucesso!");

        } catch (SQLException erro) {
             JOptionPane.showMessageDialog(null,"ERRO no  Cadastro de clientes!" + erro);
        }
            
          

    }

    public void Salvar(telas.Clientes obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
