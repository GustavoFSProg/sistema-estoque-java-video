/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import conexao.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author oem
 */
public class Produtos extends javax.swing.JFrame {
    
    
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;


    /**
     * Creates new form Produtos
     */
    public Produtos() {
        initComponents();
         conexao = ModuloConexao.conector();
         
         pesquisar_fornecedores();
         
         pesquisar_fornecedores_por_id();
         
    }
         

// 3. Popula a ComboBox com os dados
//for (String nome : listaDeNomes) {
//    ForId.addItem(nome);
//}
//    }
    
//    ForId.removeAllItems();

// 2. Exemplo de lista vinda do seu Banco de Dados
  
    
        private void adicionar(){
        String sql = "insert into  tb_produtos (nome, descricao, preco, qtd_estoque, for_id) values( ?, ?, ? , ?, ?)";
        try{
            int preco = Integer.parseInt(Preco.getText());
            int qtd = Integer.parseInt(Qtd.getText());      
            int forId = Integer.parseInt(Fornecedor.getText());


            
            pst=conexao.prepareStatement(sql);
//            pst.setString(1,  IdField.getText());            
            pst.setString(1, Nome.getText()); 
            pst.setString(2, Descricao.getText());   
            pst.setInt(3, preco);       
            pst.setInt(4, qtd); 
            pst.setInt(5, forId);
               
   
            
            if(Nome.getText().isEmpty() || Preco.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
                
                
            }else{
            
             int adicionado =     pst.executeUpdate();
            
                          

            if(adicionado > 0){
                
               JOptionPane.showMessageDialog(null,"Produto Cadastrado com sucesso!");
               
                     limpar_campos();

              
            }
            }
             }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
        
            public void pesquisar_fornecedores(){
//          String sql = "select  id as Id, name as Nome, email as Email, fone as Telefone, endereco as Endereço  from clientes where  name like ?";
                


//String query = "SELECT * FROM TABELA WHERE CONDICAO = TAL";


          try{
              
//                  List<String> ForId = new ArrayList<String>();
                    
                                        String sql = "select id, nome from tb_fornecedores";
               pst=conexao.prepareStatement(sql);
               
//               pst.setString(1, ConsultaNome.getText() + "%");
               
                     rs= pst.executeQuery();
                         
                
                    
                     

                     while(rs.next()){

       ForId.addItem(rs.getString("id")+ "--" + rs.getString("nome"));

   }
                     
          
              }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
          
           
      }
            
            
            
              public void pesquisar_fornecedores_por_id(){
//          String sql = "select  id as Id, name as Nome, email as Email, fone as Telefone, endereco as Endereço  from clientes where  name like ?";
                


//String query = "SELECT * FROM TABELA WHERE CONDICAO = TAL";


          try{
              
//                  List<String> ForId = new ArrayList<String>();
                    
                                        String sql = "select id from tb_fornecedores";
               pst=conexao.prepareStatement(sql);
               
//               pst.setString(1, ConsultaNome.getText() + "%");
               
                     rs= pst.executeQuery();
                         
                
                    
                     

                     while(rs.next()){

      CodigoForId.addItem(rs.getString("id"));

   }
                     
          
              }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
          
           
      }
            
            
        
            public void pesquisar_cliente(){
//          String sql = "select  id as Id, name as Nome, email as Email, fone as Telefone, endereco as Endereço  from clientes where  name like ?";
                    String sql = "select * from tb_produtos where  nome like ?";

          try{
               pst=conexao.prepareStatement(sql);
               
               pst.setString(1, ConsultaNome.getText() + "%");
               
                     rs= pst.executeQuery();
                     
                     Tabela.setModel(DbUtils.resultSetToTableModel(rs));
          
              }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
          
           
      }
            
            
            
             public void pesquisar_produtos_all(){
//          String sql = "select  id as Id, name as Nome, email as Email, fone as Telefone, endereco as Endereço  from clientes where  name like ?";
                    String sql = "select * from tb_produtos";

          try{
               pst=conexao.prepareStatement(sql);
               
//               pst.setString(1, ConsultaNome.getText() + "%");
               
                     rs= pst.executeQuery();
                     
                     Tabela.setModel(DbUtils.resultSetToTableModel(rs));
          
              }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
          
           
      }
             
                   public void pesquisar_produtos_for_id(){
//          String sql = "select  id as Id, name as Nome, email as Email, fone as Telefone, endereco as Endereço  from clientes where  name like ?";
                    String sql = "select p.id, p.nome, p.descricao, p.preco, p.qtd_estoque, f.nome as fornecedores from tb_produtos as p inner join tb_fornecedores as f on(p.for_id=f.id)";

          try{
               pst=conexao.prepareStatement(sql);
               
//               pst.setString(1, ConsultaNome.getText() + "%");
               
                     rs= pst.executeQuery();
                     
                     Tabela.setModel(DbUtils.resultSetToTableModel(rs));
          
              }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
          
           
      }
        
         
         private void limpar_campos(){
             
             
               Id.setText("");
                     Nome.setText("");  
                Descricao.setText("");           
                Preco.setText("");
                 Qtd.setText("");
//                 ForId.setText("");
              


                 
                 
//                 ((DefaultTableModel) Tabela.getModel()).setRowCount(0);
                 
           
       }
         
                 
                 
       private void deletar(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse produto?", "Atenção", JOptionPane.YES_NO_OPTION);
        
      if(confirma ==JOptionPane.YES_OPTION)  {
          String sql = "delete from tb_produtos  where id=?";
          
          try{
              pst=conexao.prepareStatement(sql);
               int numberID = Integer.parseInt(Id.getText());
              
              pst.setInt(1, numberID);
              
               int apagado =   pst.executeUpdate();
               
               if(apagado > 0){
                   
              
                JOptionPane.showMessageDialog(null,"Produto deletado com sucesso!");
              
               limpar_campos();
               
//               AddButton.setEnabled(true);
                
                               }

              
                }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
      }
       }
         
            public void setar_campos(){
            int setar = Tabela.getSelectedRow();
            
               Id.setText(Tabela.getModel().getValueAt(setar, 0).toString());  
               Nome.setText(Tabela.getModel().getValueAt(setar, 1).toString()); 
                Descricao.setText(Tabela.getModel().getValueAt(setar, 2).toString());   
               Preco.setText(Tabela.getModel().getValueAt(setar, 3).toString());
               Qtd.setText(Tabela.getModel().getValueAt(setar, 4).toString());     
//               ForId.setText(Tabela.getModel().getValueAt(setar, 5).toString());
             


//               AddButton.setEnabled(false);

        }
            
            
          private void update(){
           
             String sql = "update  tb_produtos  set   nome=?, descricao=?, preco=? , qtd_estoque=?, for_id=?  where  id=?";
             try{
                  int numberID = Integer.parseInt(Id.getText());
                 
                 
            int preco = Integer.parseInt(Preco.getText());
            int qtd = Integer.parseInt(Qtd.getText());      
            int forId = Integer.parseInt(ForId.getSelectedItem().toString());


            
            pst=conexao.prepareStatement(sql);
//            pst.setString(1,  IdField.getText());            
            pst.setString(1, Nome.getText()); 
            pst.setString(2, Descricao.getText());   
            pst.setInt(3, preco);       
            pst.setInt(4, qtd); 
            pst.setInt(5, forId);  
            pst.setInt(6, numberID);   

 
             

      if(Nome.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
                
                
            }else{
            
             int adicionado =     pst.executeUpdate();
            
                          

            if(adicionado > 0){
                
               JOptionPane.showMessageDialog(null,"Produto Atualizado com sucesso!");
//              
               
               limpar_campos();
                 
//                  AddButton.setEnabled(true);
              
            }
            }
             }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
          }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Id = new javax.swing.JTextField();
        Nome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Descricao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Preco = new javax.swing.JTextField();
        Qtd = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        NovoButton = new javax.swing.JButton();
        SalvarButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        ForId = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        Fornecedor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        CodigoForId = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        ConsultaNome = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(21, 14, 14));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(238, 250, 250));
        jLabel1.setText("Cadastro de Produtos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(320, 320, 320))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel2.setText("ID");

        jLabel3.setText("Nome");

        jLabel4.setText("Descrição");

        jLabel5.setText("Preço");

        jLabel6.setText("Quantidade no Estoque");

        jLabel7.setText("Escolha o Fornecedor");

        NovoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        NovoButton.setToolTipText("Novo");
        NovoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NovoButtonActionPerformed(evt);
            }
        });

        SalvarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/diskette-45.png"))); // NOI18N
        SalvarButton.setToolTipText("Salvar");
        SalvarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarButtonActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/update.png"))); // NOI18N
        jButton4.setToolTipText("Editar Cliente");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/remove_icon_pequeno.png"))); // NOI18N
        jButton3.setToolTipText("Editar Cliente");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        ForId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ForIdActionPerformed(evt);
            }
        });

        jButton2.setText("COMBO BOX");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setText("Digite o Codigo do Fornecedor");

        CodigoForId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodigoForIdActionPerformed(evt);
            }
        });

        jLabel9.setText("Selecione o Código do Fornecedor");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(NovoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SalvarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(Preco, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(Qtd, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(103, 103, 103)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addComponent(Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ForId, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(CodigoForId, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(345, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Preco, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Qtd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ForId, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CodigoForId, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(SalvarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NovoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jTabbedPane4.addTab("Dados do Produto", jPanel2);

        Tabela.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        Tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Título 4", "Título 5", "Fornecedores"
            }
        ));
        Tabela.setRowHeight(26);
        Tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabela);

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel15.setText("Consulta por Nome:");

        ConsultaNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ConsultaNomeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ConsultaNomeKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(ConsultaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1023, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ConsultaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 67, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Consultar Produtos", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane4)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane4))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void NovoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NovoButtonActionPerformed
        limpar_campos();  
        // TODO add your handling code here:
    }//GEN-LAST:event_NovoButtonActionPerformed

    private void SalvarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarButtonActionPerformed
        adicionar();

        // TODO add your handling code here:
    }//GEN-LAST:event_SalvarButtonActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        update();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        deletar();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   pesquisar_produtos_for_id();   
        
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaMouseClicked

        setar_campos();
// TODO add your handling code here:
    }//GEN-LAST:event_TabelaMouseClicked

    private void ConsultaNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ConsultaNomeKeyReleased
        pesquisar_cliente();            // TODO add your handling code here:
    }//GEN-LAST:event_ConsultaNomeKeyReleased

    private void ConsultaNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ConsultaNomeKeyPressed

            pesquisar_cliente();
// TODO add your handling code here:
    }//GEN-LAST:event_ConsultaNomeKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
           // TODO add your handling code here:
        
        pesquisar_fornecedores();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ForIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ForIdActionPerformed
        // TODO add your handling code here:
        
//        Fornecedor.setText(ForId.getSelectedItem().toString());
    }//GEN-LAST:event_ForIdActionPerformed

    private void CodigoForIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodigoForIdActionPerformed
        // TODO add your handling code here:
        
          Fornecedor.setText(CodigoForId.getSelectedItem().toString());
    }//GEN-LAST:event_CodigoForIdActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Produtos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Produtos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Produtos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Produtos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Produtos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CodigoForId;
    private javax.swing.JTextField ConsultaNome;
    private javax.swing.JTextField Descricao;
    private javax.swing.JComboBox ForId;
    private javax.swing.JTextField Fornecedor;
    private javax.swing.JTextField Id;
    private javax.swing.JTextField Nome;
    private javax.swing.JButton NovoButton;
    private javax.swing.JTextField Preco;
    private javax.swing.JTextField Qtd;
    private javax.swing.JButton SalvarButton;
    private javax.swing.JTable Tabela;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    // End of variables declaration//GEN-END:variables
}
