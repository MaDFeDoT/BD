package bank_database;
import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.nio.charset.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class BankDatabaseGUI extends javax.swing.JFrame {

    public String DB_URL = "jdbc:firebirdsql://localhost:3050/C:/DataBase/Test.FDB";
    public String DB_DEFAULT_USER = "SYSDBA";
    public String DB_DEFAULT_PASSWORD = "masterkey";
    public String DB_DEFAULT_ENCODING = "win1251";
    public Connection conn = null;
    public String Name = null;
    public String Client_id = null;
    Properties props = null;
    
    public BankDatabaseGUI() {
        initComponents();
        try{
        Class.forName("org.firebirdsql.jdbc.FBDriver");
        props = new Properties();
        props.setProperty("user", DB_DEFAULT_USER);
        props.setProperty("password", DB_DEFAULT_PASSWORD);
        props.setProperty("encoding", DB_DEFAULT_ENCODING);
        DBConnect();
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
        }
        ClientTable();
        FinContr.setEnabled(false);
        ListContract.setEnabled(false);
        NewContract.setEnabled(false);
        SubjectOperation.setEnabled(false);
 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Client_Table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        NewContract = new javax.swing.JButton();
        ListContract = new javax.swing.JButton();
        SubjectOperation = new javax.swing.JButton();
        FinContr = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("BANK DATABASE SYSTEM");

        Client_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Client_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Client_TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Client_Table);

        jButton1.setText("Регистрация в базе данных");
        jButton1.setToolTipText("");
        jButton1.setName(""); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Exit.setText("Выход");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Список наших клиентов");

        NewContract.setText("Заключить контракт");
        NewContract.setToolTipText("");
        NewContract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewContractActionPerformed(evt);
            }
        });

        ListContract.setText("Список моих контрактов");
        ListContract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListContractActionPerformed(evt);
            }
        });

        SubjectOperation.setText("Операции с предметами");
        SubjectOperation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubjectOperationActionPerformed(evt);
            }
        });

        FinContr.setText("Изъятие всех предметов (завершение контракта)");
        FinContr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinContrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NewContract, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ListContract, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SubjectOperation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FinContr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(216, 216, 216))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NewContract, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ListContract, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SubjectOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FinContr, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Exit)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        RegNewClient Reg_Client = new RegNewClient(conn, this);
        Reg_Client.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.setVisible(false);
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void NewContractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewContractActionPerformed
        NewContract contract = new NewContract(conn, this, Client_id, Name);
        contract.setVisible(true);
    }//GEN-LAST:event_NewContractActionPerformed

    private void ListContractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListContractActionPerformed
        ListContract contract = new ListContract(conn, Client_id);
        contract.setVisible(true);
    }//GEN-LAST:event_ListContractActionPerformed

    private void FinContrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinContrActionPerformed
        FinContract finc = new FinContract(conn, Client_id, Name);
        finc.setVisible(true);
    }//GEN-LAST:event_FinContrActionPerformed

    private void SubjectOperationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubjectOperationActionPerformed
        SubjectOperation subj = new SubjectOperation(conn, Client_id, Name);
        subj.setVisible(true);
    }//GEN-LAST:event_SubjectOperationActionPerformed

    private void Client_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Client_TableMouseClicked
        int row = Client_Table.getSelectedRow();
        Client_id=Client_Table.getModel().getValueAt(row, 0).toString();
        Name=Client_Table.getModel().getValueAt(row, 1).toString();
        FinContr.setEnabled(true);
        ListContract.setEnabled(true);
        NewContract.setEnabled(true);
        SubjectOperation.setEnabled(true);
    }//GEN-LAST:event_Client_TableMouseClicked

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
            java.util.logging.Logger.getLogger(BankDatabaseGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BankDatabaseGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BankDatabaseGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BankDatabaseGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BankDatabaseGUI().setVisible(true);
            }
        });
        
    }

    /**
     * Подключение к БД
     */
    private void DBConnect()
    {
        try {
            conn = DriverManager.getConnection(DB_URL, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void ClientTable()
    {
        try {
        //**************************************ТАБЛИЦА CLIENTS***********
            String strSQL = "select * from CLIENTS";        
            if (conn==null)
            {
                JOptionPane.showMessageDialog(null, "Could not connect to database");
            }
            PreparedStatement statement = conn.prepareStatement(strSQL);
            ResultSet resultSet = statement.executeQuery();
            Client_Table.setModel(DbUtils.resultSetToTableModel(resultSet)); 
                     
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Client_Table;
    private javax.swing.JButton Exit;
    private javax.swing.JButton FinContr;
    private javax.swing.JButton ListContract;
    private javax.swing.JButton NewContract;
    private javax.swing.JButton SubjectOperation;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
