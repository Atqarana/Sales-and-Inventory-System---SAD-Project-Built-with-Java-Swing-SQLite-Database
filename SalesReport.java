/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesandinventorymanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alger
 */
public class SalesReport extends javax.swing.JFrame {

    String fromDate = null;
    String toDate = null;
    int sum = 0;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String date;

    public SalesReport() {
        initComponents();
        setTitle("Sales Report");
        setLocationRelativeTo(null);
        CenteText();
        tableHeight();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();
        main_panel.add(daily_panel);
        main_panel.repaint();
        main_panel.revalidate();

    }

    public void CenteText() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 6; i++) {
            summary_tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        for (int i = 0; i < 6; i++) {
            annual_tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        for (int i = 0; i < 6; i++) {
            daily_tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        for (int i = 0; i < 6; i++) {
            monthly_tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void tableHeight() {
        annual_tbl.setRowHeight(annual_tbl.getRowHeight() + 5);
        daily_tbl.setRowHeight(daily_tbl.getRowHeight() + 5);
        monthly_tbl.setRowHeight(monthly_tbl.getRowHeight() + 5);
        summary_tbl.setRowHeight(summary_tbl.getRowHeight() + 5);
    }
//    public void summarySales(){
//        DefaultTableModel model = (DefaultTableModel) summary_tbl.getModel();
//        String sql = "SELECT * FROM sales_item";
//        try{
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while(rs.next()){
//                String date = rs.getString("date_time");
//                String code = rs.getString("item_code");
//                String name = rs.getString("item_name");
//                String quantity = rs.getString("quantity");
//                String price = rs.getString("price");
//                int total = rs.getInt("total");
//                sum = sum + total;
//                Object[] row = {date,code,name,quantity,price,total};
//                model.addRow(row);
//            }
//        }catch(Exception ex){
//            System.out.println(ex);
//        }
//        total_tf1.setText("Total : " +Integer.toString(sum));
//    }

    public void dailySales() {
        daily_lb.setText("Daily Sales Report of " + date + "");
        DefaultTableModel model = (DefaultTableModel) daily_tbl.getModel();
        model.setRowCount(0);
        sum = 0;
        String sql = "SELECT * FROM sales_item WHERE date1 = '" + date + "'";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String date = rs.getString("date_time");
                String code = rs.getString("item_code");
                String name = rs.getString("item_name");
                String quantity = rs.getString("quantity");
                String price = rs.getString("price");
                int total = rs.getInt("total");
                sum = sum + total;
                Object[] row = {date, code, name, quantity, price, total};
                model.addRow(row);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        total_tf2.setText("Total : " + Integer.toString(sum));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        main_panel = new javax.swing.JPanel();
        summary_panel = new javax.swing.JPanel();
        annualSalesReport_lb1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        summary_tbl = new javax.swing.JTable();
        date_dcc1 = new datechooser.beans.DateChooserCombo();
        date_dcc2 = new datechooser.beans.DateChooserCombo();
        total_tf1 = new javax.swing.JTextField();
        annual_panel = new javax.swing.JPanel();
        annual_lb = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        annual_tbl = new javax.swing.JTable();
        jComboBox3 = new javax.swing.JComboBox<>();
        annual_cb = new javax.swing.JComboBox<>();
        total4_tf = new javax.swing.JTextField();
        daily_panel = new javax.swing.JPanel();
        daily_lb = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        daily_tbl = new javax.swing.JTable();
        date_dcc = new datechooser.beans.DateChooserCombo();
        total_tf2 = new javax.swing.JTextField();
        monthly_panel = new javax.swing.JPanel();
        month_lb = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        monthly_tbl = new javax.swing.JTable();
        total_tf3 = new javax.swing.JTextField();
        month_cb = new javax.swing.JComboBox<>();
        year_cb = new javax.swing.JComboBox<>();
        select_btn = new javax.swing.JButton();

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Summary of Sales");

        jTextField3.setBackground(new java.awt.Color(240, 240, 240));
        jTextField3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sales Report");

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jButton1.setText("Summary of Sales");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jButton5.setText("Daily Sales");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jButton6.setText("Monthly Sales");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jButton7.setText("Annual Sales");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        main_panel.setLayout(new java.awt.CardLayout());

        summary_panel.setBackground(new java.awt.Color(204, 204, 255));
        summary_panel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        annualSalesReport_lb1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        annualSalesReport_lb1.setText("Summary of Sales");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("From:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("To:");

        summary_tbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        summary_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date and Time", "Item Code", "Item Name", "Quantity", "Price", "Total"
            }
        ));
        jScrollPane1.setViewportView(summary_tbl);

        date_dcc1.setFieldFont(new java.awt.Font("Trebuchet MS", java.awt.Font.PLAIN, 13));
        date_dcc1.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                date_dcc1OnCommit(evt);
            }
        });

        date_dcc2.setFieldFont(new java.awt.Font("Trebuchet MS", java.awt.Font.PLAIN, 13));
        date_dcc2.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                date_dcc2OnCommit(evt);
            }
        });

        total_tf1.setBackground(new java.awt.Color(240, 240, 240));
        total_tf1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_tf1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout summary_panelLayout = new javax.swing.GroupLayout(summary_panel);
        summary_panel.setLayout(summary_panelLayout);
        summary_panelLayout.setHorizontalGroup(
            summary_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(summary_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(summary_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(summary_panelLayout.createSequentialGroup()
                        .addComponent(annualSalesReport_lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(date_dcc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel5)
                        .addGap(10, 10, 10)
                        .addComponent(date_dcc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, summary_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(total_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        summary_panelLayout.setVerticalGroup(
            summary_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(summary_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(summary_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(annualSalesReport_lb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(date_dcc1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_dcc2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(total_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        main_panel.add(summary_panel, "card2");

        annual_panel.setBackground(new java.awt.Color(204, 204, 255));
        annual_panel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        annual_lb.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        annual_lb.setText("Annual Sales Report of ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Select Year ");

        annual_tbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        annual_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date and Time", "Item Code", "Item Name", "Quantity", "Price", "Total"
            }
        ));
        jScrollPane3.setViewportView(annual_tbl);

        jComboBox3.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041" }));

        annual_cb.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        annual_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Year", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041" }));
        annual_cb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                annual_cbItemStateChanged(evt);
            }
        });

        total4_tf.setBackground(new java.awt.Color(240, 240, 240));
        total4_tf.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        total4_tf.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout annual_panelLayout = new javax.swing.GroupLayout(annual_panel);
        annual_panel.setLayout(annual_panelLayout);
        annual_panelLayout.setHorizontalGroup(
            annual_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(annual_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(annual_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(annual_panelLayout.createSequentialGroup()
                        .addComponent(annual_lb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(annual_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, annual_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(total4_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(annual_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(annual_panelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        annual_panelLayout.setVerticalGroup(
            annual_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(annual_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(annual_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(annual_lb, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(annual_cb))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(total4_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(annual_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(annual_panelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        main_panel.add(annual_panel, "card2");

        daily_panel.setBackground(new java.awt.Color(204, 204, 255));
        daily_panel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        daily_lb.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        daily_lb.setText("Daily Sales Report of ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Select Date");

        daily_tbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        daily_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date and Time", "Item Code", "Item Name", "Quantity", "Price", "Total"
            }
        ));
        jScrollPane2.setViewportView(daily_tbl);

        date_dcc.setFieldFont(new java.awt.Font("Trebuchet MS", java.awt.Font.PLAIN, 13));
        date_dcc.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                date_dccOnCommit(evt);
            }
        });

        total_tf2.setBackground(new java.awt.Color(240, 240, 240));
        total_tf2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        total_tf2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout daily_panelLayout = new javax.swing.GroupLayout(daily_panel);
        daily_panel.setLayout(daily_panelLayout);
        daily_panelLayout.setHorizontalGroup(
            daily_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daily_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(daily_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(daily_panelLayout.createSequentialGroup()
                        .addComponent(daily_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(10, 10, 10)
                        .addComponent(date_dcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, daily_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(total_tf2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        daily_panelLayout.setVerticalGroup(
            daily_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daily_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(daily_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(daily_lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(date_dcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(total_tf2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        main_panel.add(daily_panel, "card2");

        monthly_panel.setBackground(new java.awt.Color(204, 204, 255));
        monthly_panel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        month_lb.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        month_lb.setText("Monthly Sales Report of");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Select Month");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Select Year");

        monthly_tbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        monthly_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date and Time", "Item Code", "Item Name", "Quantity", "Price", "Total"
            }
        ));
        jScrollPane4.setViewportView(monthly_tbl);

        total_tf3.setBackground(new java.awt.Color(240, 240, 240));
        total_tf3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        total_tf3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        month_cb.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        month_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Any", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        year_cb.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        year_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Any", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041" }));

        select_btn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        select_btn.setText("Select");
        select_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout monthly_panelLayout = new javax.swing.GroupLayout(monthly_panel);
        monthly_panel.setLayout(monthly_panelLayout);
        monthly_panelLayout.setHorizontalGroup(
            monthly_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(monthly_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(monthly_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
                    .addGroup(monthly_panelLayout.createSequentialGroup()
                        .addComponent(month_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, monthly_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(monthly_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(total_tf3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, monthly_panelLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(month_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(year_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(select_btn)
                                .addGap(2, 2, 2)))))
                .addContainerGap())
        );
        monthly_panelLayout.setVerticalGroup(
            monthly_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(monthly_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(monthly_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(month_lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(month_cb)
                    .addComponent(year_cb)
                    .addComponent(select_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(total_tf3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        main_panel.add(monthly_panel, "card2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        conn = DBConnect.DBConnect();
        dailySales();
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();
        main_panel.add(summary_panel);
        main_panel.repaint();
        main_panel.revalidate();
        sum = 0;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();
        main_panel.add(daily_panel);
        main_panel.repaint();
        main_panel.revalidate();
        dailySales();
        sum = 0;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();
        main_panel.add(monthly_panel);
        main_panel.repaint();
        main_panel.revalidate();
        sum = 0;
        Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH) + 1;
        String[] months = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};
        month_lb.setText("Monthly Sales Report of " + months[cmonth - 1] + ", " + cyear);
        DefaultTableModel model = (DefaultTableModel) monthly_tbl.getModel();
        model.setRowCount(0);
        String sql = "SELECT * FROM sales_item WHERE year = '" + cyear + "' AND month = '" + cmonth + "'";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String date_time = rs.getString("date_time");
                String item_code = rs.getString("item_code");
                String item_name = rs.getString("item_name");
                String quantity = rs.getString("quantity");
                String price = rs.getString("price");
                int total = rs.getInt("total");
                sum += total;
                Object[] sales = {date_time, item_code, item_name, quantity, price, total};
                model.addRow(sales);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        total_tf3.setText("Total : " + Integer.toString(sum));

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();
        main_panel.add(annual_panel);
        main_panel.repaint();
        main_panel.revalidate();
        sum = 0;
        Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        annual_lb.setText("Annual Sales Report of " + cyear);
        DefaultTableModel model = (DefaultTableModel) annual_tbl.getModel();
        model.setRowCount(0);
        String sql = "SELECT * FROM sales_item WHERE year = '" + cyear + "'";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String date_time = rs.getString("date_time");
                String item_code = rs.getString("item_code");
                String item_name = rs.getString("item_name");
                String quantity = rs.getString("quantity");
                String price = rs.getString("price");
                int total = rs.getInt("total");
                sum += total;
                Object[] sales = {date_time, item_code, item_name, quantity, price, total};
                model.addRow(sales);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        total4_tf.setText("Total : " + Integer.toString(sum));
    }//GEN-LAST:event_jButton7ActionPerformed

    private void date_dcc2OnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_date_dcc2OnCommit
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
        toDate = sdf.format(date_dcc2.getSelectedDate().getTime());
        DefaultTableModel summarySales = (DefaultTableModel) summary_tbl.getModel();
        summarySales.setRowCount(0);
        sum = 0;
        String sql = "SELECT * FROM sales_item WHERE date1 BETWEEN '" + fromDate + "' AND '" + toDate + "'";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String date_time = rs.getString("date_time");
                String item_code = rs.getString("item_code");
                String item_name = rs.getString("item_name");
                String quantity = rs.getString("quantity");
                String price = rs.getString("price");
                int total = rs.getInt("total");
                sum += total;
                Object[] sales = {date_time, item_code, item_name, quantity, price, total};
                summarySales.addRow(sales);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        total_tf1.setText("Total : " + Integer.toString((int) sum));
        annualSalesReport_lb1.setText("Summary of sales From " + fromDate + " to " + toDate);
    }//GEN-LAST:event_date_dcc2OnCommit

    private void date_dccOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_date_dccOnCommit
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
        String date = sdf.format(date_dcc.getSelectedDate().getTime());
        daily_lb.setText("Summary of Sales of " + date + "");
        sum = 0;
        DefaultTableModel model = (DefaultTableModel) daily_tbl.getModel();
        model.setRowCount(0);
        String sql = "SELECT * FROM sales_item WHERE date1 = '" + date + "' ";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String date_time = rs.getString("date_time");
                String item_code = rs.getString("item_code");
                String item_name = rs.getString("item_name");
                String quantity = rs.getString("quantity");
                String price = rs.getString("price");
                int total = rs.getInt("total");
                sum += total;
                Object[] sales = {date_time, item_code, item_name, quantity, price, total};
                model.addRow(sales);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        total_tf2.setText("Total: " + Integer.toString((int) sum));
    }//GEN-LAST:event_date_dccOnCommit

    private void date_dcc1OnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_date_dcc1OnCommit
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
        fromDate = sdf.format(date_dcc1.getSelectedDate().getTime());
    }//GEN-LAST:event_date_dcc1OnCommit

    private void select_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_btnActionPerformed

        int month = month_cb.getSelectedIndex() ;
        String lmonth = month_cb.getSelectedItem().toString();
        String year = (String) year_cb.getSelectedItem();
        if (month_cb.getSelectedItem() != "Select Any" && year_cb.getSelectedItem() != "Select Any") {
            month_lb.setText("Sales Report of " + lmonth + ", " + year);
            sum = 0;
            DefaultTableModel model = (DefaultTableModel) monthly_tbl.getModel();
            model.setRowCount(0);
            String sql = "SELECT * FROM sales_item WHERE month = '" + month + "' AND year = '" + year + "' ";
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String date_time = rs.getString("date_time");
                    String item_code = rs.getString("item_code");
                    String item_name = rs.getString("item_name");
                    String quantity = rs.getString("quantity");
                    String price = rs.getString("price");
                    int total = rs.getInt("total");
                    sum += total;
                    Object[] sales = {date_time, item_code, item_name, quantity, price, total};
                    model.addRow(sales);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

            total_tf3.setText("Total : " + Integer.toString((int) sum));
        } else {
            JOptionPane.showMessageDialog(null, "Select Month/Year");
        }

    }//GEN-LAST:event_select_btnActionPerformed

    private void annual_cbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_annual_cbItemStateChanged
        if (annual_cb.getSelectedItem() != "Select Any") {
            String year = annual_cb.getSelectedItem().toString();
            annual_lb.setText("Annual Sales Report of " + year);
            DefaultTableModel model = (DefaultTableModel) annual_tbl.getModel();
            model.setRowCount(0);
            sum = 0;
            String sql = "SELECT * FROM sales_item WHERE year = '" + year + "'";
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String date_time = rs.getString("date_time");
                    String item_code = rs.getString("item_code");
                    String item_name = rs.getString("item_name");
                    String quantity = rs.getString("quantity");
                    String price = rs.getString("price");
                    int total = rs.getInt("total");
                    sum += total;
                    Object[] sales = {date_time, item_code, item_name, quantity, price, total};
                    model.addRow(sales);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
            total4_tf.setText("Total : " + Integer.toString(sum));
        }
    }//GEN-LAST:event_annual_cbItemStateChanged

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
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalesReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel annualSalesReport_lb1;
    private javax.swing.JComboBox<String> annual_cb;
    private javax.swing.JLabel annual_lb;
    private javax.swing.JPanel annual_panel;
    private javax.swing.JTable annual_tbl;
    private javax.swing.JLabel daily_lb;
    private javax.swing.JPanel daily_panel;
    private javax.swing.JTable daily_tbl;
    private datechooser.beans.DateChooserCombo date_dcc;
    private datechooser.beans.DateChooserCombo date_dcc1;
    private datechooser.beans.DateChooserCombo date_dcc2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JPanel main_panel;
    private javax.swing.JComboBox<String> month_cb;
    private javax.swing.JLabel month_lb;
    private javax.swing.JPanel monthly_panel;
    private javax.swing.JTable monthly_tbl;
    private javax.swing.JButton select_btn;
    private javax.swing.JPanel summary_panel;
    private javax.swing.JTable summary_tbl;
    private javax.swing.JTextField total4_tf;
    private javax.swing.JTextField total_tf1;
    private javax.swing.JTextField total_tf2;
    private javax.swing.JTextField total_tf3;
    private javax.swing.JComboBox<String> year_cb;
    // End of variables declaration//GEN-END:variables
}
