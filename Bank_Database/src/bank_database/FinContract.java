/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_database;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Федор
 */
public class FinContract extends javax.swing.JFrame {

    public Connection conn = null;
    public String Contract_id = null;
    public String Client_id = null;
    public Vector<String> Cell_numbers = new Vector<String>();
    public Vector<String> Contracts_id = new Vector<String>();
    public int kol=0;
    
    public FinContract(Connection connect, String cli_id, String nam) {
        initComponents();
        conn=connect;
        jButton1.setEnabled(false);
        Client_id=cli_id;
        Name.setText(nam);
        try{
            String Query = "SELECT CONTRACT_ID, CELL_NUMBER FROM CONTRACTS WHERE (CLIENT_ID="+cli_id+")";
            PreparedStatement statement = conn.prepareStatement(Query);
            ResultSet resultSet = statement.executeQuery();
            kol=0;
            while(resultSet.next())
            {
                Cell_numbers.add(resultSet.getString("CELL_NUMBER"));
                Contracts_id.add(resultSet.getString("CONTRACT_ID"));
                kol=kol+1;
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel( Cell_numbers );
            jComboBox1.setModel( model );
            if(jComboBox1.getSelectedItem()!=null)
            {
                String Cell_number=jComboBox1.getSelectedItem().toString();
                for(int i=0; i<kol; i++)
                {
                    if(Cell_number==Cell_numbers.get(i))
                        Contract_id=Contracts_id.get(i);
                }
            }
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Ваша ячейка");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Ваше имя");

        jButton1.setText("Завершить");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Name)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(jButton1)
                .addContainerGap(218, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String Cell_number, Date;
        ArrayList Item_id = new ArrayList();
            String sql = "select cell_number as cell from contracts where (CONTRACT_ID="+Contract_id+")AND(CLIENT_ID="+Client_id+")";
            try{
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                {                
                     Cell_number  = rs.getString("cell").toString();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Ошибка. В доступе отказано.");
                    return;
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date = dateFormat.format( new Date() );
            
                String Query = "SELECT MAX(ID) AS COLL FROM HISTORY_CELL";
                PreparedStatement statement = conn.prepareStatement(Query);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int num=resultSet.getInt("COLL")+1;
                String History_id=String.valueOf(num);
                
                Query = "SELECT ITEM_ID AS ITID FROM ITEMS WHERE CONTRACT_ID="+Contract_id;
                statement = conn.prepareStatement(Query);
                resultSet = statement.executeQuery();
                
                int i=0;
                while(resultSet.next())
                {
                    Item_id.add(resultSet.getString("ITID"));
                    i=i+1;
                }

                for(int j=0; j<i; j++)
                {
                    Query = "SELECT ITEM_ID AS NUM FROM HISTORY_CELL WHERE (ITEM_ID="+Item_id.get(j)+")AND(CELL_NUMBER="+Cell_number+")AND(ACSESS_TYPE=2)AND(DATE_ACSESS>"
                        + "(SELECT FIRST 1 DATE_ACSESS FROM HISTORY_CELL WHERE ACSESS_TYPE<2 ORDER BY DATE_ACSESS ASC))";
                    statement = conn.prepareStatement(Query);
                    ResultSet newres = statement.executeQuery();
                
                     if(!newres.next())
                    {
                        Query = "INSERT INTO HISTORY_CELL(ID, CELL_NUMBER, ACSESS_TYPE, DATE_ACSESS, ITEM_ID) "
                            + "VALUES ("+History_id+", "+Cell_number+", 2, \'"+Date+"\', "+Item_id.get(j)+")";
                        statement = conn.prepareStatement(Query);
                        statement.execute();
                        
                        num=num+1;
                        History_id=String.valueOf(num);
                    }
                }  
                Query = "UPDATE CONTRACTS SET END_DATE=\'"+Date+"\' WHERE CONTRACT_ID="+Contract_id;
                statement = conn.prepareStatement(Query);
                statement.execute();
                       
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            } 
            this.setVisible(false);
            JOptionPane.showMessageDialog(null, "Контракт завершен");
            this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String Cell_number=jComboBox1.getSelectedItem().toString();
        for(int i=0; i<kol; i++)
        {
            if(Cell_number==Cell_numbers.get(i))
                Contract_id=Contracts_id.get(i);
        }
        System.out.println(Contract_id);
        jButton1.setEnabled(true);
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Name;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
