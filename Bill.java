/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesandinventorymanagementsystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alger
 */
public class Bill extends javax.swing.JFrame {

    JTable salesTable;
    String code = "";
    Statement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    float AmountDue;
    String date;
    int year = 0;
    int month = 0;
    Sales sales;
    String Date;
    String[][] Info;
    int row1;
    int col1;
    int payment = 0;
    float change = 0;

    public Bill() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Bill");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CenterText();
    }

    public void Sale_Item() {
        DefaultTableModel model = (DefaultTableModel) salesTable.getModel();
        int row = salesTable.getRowCount();
        int col = salesTable.getColumnCount();
        String[][] items = new String[row][col];
        String[] dbTableCol = {"item_code", "item_name", "date_time", "price", "quantity", "total"};
        for (int i = 0; i < row; i++) {
            for (int x = 0; x < col; x++) {
                items[i][x] = model.getValueAt(i, x).toString();
                System.out.println(items[i][x]);
            }

        }
        for (int z = 0; z < row; z++) {
            String sql = "INSERT INTO sales_item(date_time,item_code,item_name,price,quantity,total,transaction_number,date1,year,month)VALUES('" + items[z][0] + "','" + items[z][1] + "','" + items[z][2] + "','" + items[z][3] + "','" + items[z][4] + "','" + items[z][5] + "','" + code + "','" + date + "','" + year + "','" + month + "')";
            String sql2 = "UPDATE inventory SET stocks = stocks - '" + items[z][4] + "' WHERE item_code = '" + items[z][1] + "'";
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                stmt.executeUpdate(sql2);
                stmt.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    public void CenterText() {
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        payment_tf1.setHorizontalAlignment(SwingConstants.CENTER);
        change_tf1.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void Code() {

        int a = (int) (Math.random() * 10);
        int b = (int) (Math.random() * 10);
        int c = (int) (Math.random() * 10);
        int d = (int) (Math.random() * 10);
        int e = (int) (Math.random() * 10);
        String aString = String.valueOf(a);
        String bString = String.valueOf(b);
        String cString = String.valueOf(c);
        String dString = String.valueOf(d);
        String eString = String.valueOf(e);
        code = aString + bString + cString + dString + eString;
    }

    public void Receipt() {

        try {
            String formatStr = "%-20s%-15s%-15s%-15s";
            File file = new File("Text.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Amores Construction Supply");
            bw.write(System.getProperty("line.separator"));
            bw.write("Sales Receipt");
            bw.write(System.getProperty("line.separator"));
            bw.write("Date : " + Date);
            bw.write(System.getProperty("line.separator"));
            bw.write("-----------------------------------------------------------------");
            bw.write(System.getProperty("line.separator"));
            bw.write(String.format(formatStr,"ITEM","PRICE","QUANTITY","TOTAL"));
            bw.write(System.getProperty("line.separator"));
            bw.write(System.getProperty("line.separator"));
            int row = salesTable.getRowCount();
            int col = salesTable.getColumnCount();
            for (int i = 0; i < row1; i++) {
                bw.write(String.format(formatStr,Info[i][2],Info[i][3],Info[i][4],Info[i][5]));
                bw.write(System.getProperty("line.separator"));

            }
            bw.write(System.getProperty("line.separator"));
            bw.write(System.getProperty("line.separator"));
            bw.write("-----------------------------------------------------------------");
            bw.write(System.getProperty("line.separator"));
            bw.write("Total : " + AmountDue);
            bw.write(System.getProperty("line.separator"));
            bw.write("Payment : " + payment);
            bw.write(System.getProperty("line.separator"));
            bw.write("Change : " + change);
            bw.close();
            fw.close();
            JOptionPane.showMessageDialog(null, "Data Exported");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        payment_tf1 = new javax.swing.JTextField();
        change_tf1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Finish");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Payment");

        payment_tf1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        change_tf1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Change");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(change_tf1)
            .addComponent(payment_tf1)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(payment_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel6)
                .addGap(11, 11, 11)
                .addComponent(change_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!"".equals(payment_tf1.getText()) || !"".equals(change_tf1.getText())) {
            payment = Integer.parseInt(payment_tf1.getText());
            change = Float.parseFloat(change_tf1.getText());
            if (change >= 0) {
                String sql = "INSERT INTO sales(transaction_number,total,payment1,change1,date,year,month)VALUES('" + code + "','" + AmountDue + "','" + payment + "','" + change + "','" + date + "','" + year + "','" + month + "')";
                try {
                    stmt = conn.createStatement();
                    if (stmt.executeUpdate(sql) == 1) {
                        Sale_Item();
                        JOptionPane.showMessageDialog(null, "Transaction Complete");
                        sales.POSRefresh();
                        Receipt();
                        conn.close();
                        stmt.close();
                        Sales.conn = DBConnect.DBConnect();
                        this.setVisible(false);

                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Enter the payment");
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        conn = DBConnect.DBConnect();
        Code();
        Date month1 = new Date();
        Date year1 = new Date();
        month = month1.getMonth() + 1;
        year = year1.getYear() + 1900;
        payment_tf1.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                change();
            }

            public void removeUpdate(DocumentEvent e) {
            }

            public void insertUpdate(DocumentEvent e) {
                change();
            }

            public void change() {
                int payment = Integer.parseInt(payment_tf1.getText());
                float change = payment - AmountDue;
                change_tf1.setText(Float.toString(change));
            }
        });

    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Sales.conn = DBConnect.DBConnect();
    }//GEN-LAST:event_formWindowClosing

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
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField change_tf1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField payment_tf1;
    // End of variables declaration//GEN-END:variables
}
