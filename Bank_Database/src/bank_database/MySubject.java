/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_database;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Федор
 */
public class MySubject extends javax.swing.JFrame {

    public Connection conn = null;
    
    public MySubject(Connection connect, String Client_id, String Contract_id) {
        initComponents();
        conn=connect;
        
        String Cell_number;
        try {
                String Query = "SELECT CELL_NUMBER, CLIENT_ID FROM CONTRACTS WHERE CONTRACT_ID="+Contract_id;
                PreparedStatement statement = conn.prepareStatement(Query);
                ResultSet resultSet = statement.executeQuery();
                if(!resultSet.next())
                        {
                            JOptionPane.showMessageDialog(null, "Не существует данного контракта.");
                            return;                              
                        }
                Cell_number=resultSet.getString("CELL_NUMBER");
                
                Query = "SELECT ITEM_ID, NAME, COST FROM ITEMS WHERE (CONTRACT_ID="+Contract_id+")AND(ITEM_ID NOT IN (SELECT ITEM_ID FROM HISTORY_CELL WHERE ACSESS_TYPE=2))";
                statement = conn.prepareStatement(Query);
                resultSet = statement.executeQuery();
                
                jTextArea1.append("Список вещей клиента "+Client_id+" по контракту "+Contract_id+" для ячейки "+Cell_number+":\n");
                boolean flag=resultSet.next();
                if(!flag)
                    jTextArea1.append("Нет вещей по данному контракту\n");
                while(flag)
                {
                    jTextArea1.append("\tПредмет: "+resultSet.getString("ITEM_ID")+"\t Название: "+resultSet.getString("NAME")+"\n"
                            + "\tСтоимость: "+resultSet.getString("COST")+"\n\n");
                    flag=resultSet.next();
                }                        
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Мои предметы");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(282, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(262, 262, 262))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
