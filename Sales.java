/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesandinventorymanagementsystem;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Sales extends javax.swing.JFrame {

    static Connection conn = null;
    PreparedStatement ps = null;
    Statement stmt = null;
    ResultSet rs = null;
    float amountDue = 0;
    double total = 0;
    String username;
    String USERTYPE;
    String NAME;

    public Sales() {
        initComponents();
        SalesTable();
        Date();
        sales_tbl.setRowHeight(sales_tbl.getRowHeight() + 4);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public void SalesTable() {

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        int alignment = 0;
        rightRenderer.setHorizontalAlignment(alignment);
        TableModel tableModel = sales_tbl.getModel();
        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            sales_tbl.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
        sales_tbl.setRowHeight(sales_tbl.getRowHeight() + 4);

    }

    public void FrameRefresh() {

        itemName_tf.setText("");
        quantity_tf.setText("");
        total_tf.setText("");
        itemCode_lb.setText("");
        itemName_lb.setText("");
        description_lb.setText("");
        category_lb.setText("");
        price_lb.setText("");

    }

    public void Date() {

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
        Date date = new Date();
        date_lb.setText(sdf.format(date));
    }

    public void POSRefresh() {
        DefaultTableModel model = (DefaultTableModel) sales_tbl.getModel();
        model.setRowCount(0);
        amountDue_lb.setText("");
        amountDue = 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        amountDue_lb = new javax.swing.JLabel();
        cashier_lb = new javax.swing.JLabel();
        date_lb = new javax.swing.JLabel();
        pos_btn = new javax.swing.JButton();
        inventory_btn = new javax.swing.JButton();
        lagout_btn = new javax.swing.JButton();
        main = new javax.swing.JPanel();
        pos = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        itemName_tf = new javax.swing.JTextField();
        total_tf = new javax.swing.JTextField();
        quantity_tf = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        prcess_btn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        itemName_lb = new javax.swing.JLabel();
        description_lb = new javax.swing.JLabel();
        price_lb = new javax.swing.JLabel();
        itemCode_lb = new javax.swing.JLabel();
        category_lb = new javax.swing.JLabel();
        price_lb1 = new javax.swing.JLabel();
        itemName_lb1 = new javax.swing.JLabel();
        description_lb1 = new javax.swing.JLabel();
        category_lb1 = new javax.swing.JLabel();
        price_lb2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sales_tbl = new javax.swing.JTable();
        inventory = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        all_rbtn = new javax.swing.JRadioButton();
        available_rbtn = new javax.swing.JRadioButton();
        out_btn = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        item_tbl = new javax.swing.JTable();
        exit_btn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        amountDue_lb.setBackground(java.awt.Color.white);
        amountDue_lb.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        amountDue_lb.setForeground(new java.awt.Color(255, 255, 255));
        amountDue_lb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        amountDue_lb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cashier_lb.setBackground(java.awt.Color.white);
        cashier_lb.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cashier_lb.setForeground(new java.awt.Color(255, 255, 255));
        cashier_lb.setText("CASHIER: ALGER");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cashier_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 467, Short.MAX_VALUE)
                .addComponent(amountDue_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(amountDue_lb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
            .addComponent(cashier_lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        date_lb.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        date_lb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        date_lb.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        pos_btn.setBackground(new java.awt.Color(255, 255, 204));
        pos_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        pos_btn.setText("POS");
        pos_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pos_btnActionPerformed(evt);
            }
        });

        inventory_btn.setBackground(new java.awt.Color(255, 255, 204));
        inventory_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        inventory_btn.setText("Inventory");
        inventory_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventory_btnActionPerformed(evt);
            }
        });

        lagout_btn.setBackground(new java.awt.Color(255, 255, 204));
        lagout_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lagout_btn.setText("Change User");
        lagout_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lagout_btnActionPerformed(evt);
            }
        });

        main.setLayout(new java.awt.CardLayout());

        pos.setBackground(new java.awt.Color(204, 204, 255));

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jButton1.setText("ADD TO CART");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel1.setText("Item Name");

        itemName_tf.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        total_tf.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        quantity_tf.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel8.setText("Total:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel7.setText("Quantity");

        prcess_btn.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        prcess_btn.setText("PROCESS");
        prcess_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        prcess_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prcess_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemName_tf)
                    .addComponent(prcess_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(quantity_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8)
                                    .addComponent(total_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addComponent(itemName_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quantity_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(total_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(prcess_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        itemName_lb.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N

        description_lb.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N

        price_lb.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N

        itemCode_lb.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N

        category_lb.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N

        price_lb1.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        price_lb1.setText("Item Code:");

        itemName_lb1.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        itemName_lb1.setText("Item Name:");

        description_lb1.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        description_lb1.setText("Description:");

        category_lb1.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        category_lb1.setText("Category:");

        price_lb2.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        price_lb2.setText("Price:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(price_lb2)
                            .addComponent(category_lb1)
                            .addComponent(description_lb1))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(category_lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(price_lb, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                            .addComponent(description_lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(price_lb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(itemName_lb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(itemName_lb, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                            .addComponent(itemCode_lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(price_lb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemCode_lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemName_lb1)
                    .addComponent(itemName_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(description_lb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(description_lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(category_lb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(category_lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(price_lb2)
                    .addComponent(price_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sales_tbl.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        sales_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE/TIME", "ITEM CODE", "ITEM NAME", "PRICE", "Quantity", "TOTAL"
            }
        ));
        sales_tbl.setGridColor(new java.awt.Color(255, 255, 255));
        sales_tbl.getTableHeader().setResizingAllowed(false);
        sales_tbl.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(sales_tbl);

        javax.swing.GroupLayout posLayout = new javax.swing.GroupLayout(pos);
        pos.setLayout(posLayout);
        posLayout.setHorizontalGroup(
            posLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(posLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(posLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(posLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(0, 0, 0))
        );
        posLayout.setVerticalGroup(
            posLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(posLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(posLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        main.add(pos, "card2");

        inventory.setBackground(new java.awt.Color(204, 204, 255));

        jLabel4.setBackground(new java.awt.Color(204, 204, 255));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel4.setText("Display :");

        all_rbtn.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(all_rbtn);
        all_rbtn.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        all_rbtn.setText("All Items");
        all_rbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                all_rbtnActionPerformed(evt);
            }
        });

        available_rbtn.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(available_rbtn);
        available_rbtn.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        available_rbtn.setText("Available Items");
        available_rbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                available_rbtnActionPerformed(evt);
            }
        });

        out_btn.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(out_btn);
        out_btn.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        out_btn.setText("Out of Stocks");
        out_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                out_btnActionPerformed(evt);
            }
        });

        item_tbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        item_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Code", "Item Name", "Stocks", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(item_tbl);

        javax.swing.GroupLayout inventoryLayout = new javax.swing.GroupLayout(inventory);
        inventory.setLayout(inventoryLayout);
        inventoryLayout.setHorizontalGroup(
            inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inventoryLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6)
                        .addComponent(all_rbtn)
                        .addGap(18, 18, 18)
                        .addComponent(available_rbtn)
                        .addGap(18, 18, 18)
                        .addComponent(out_btn)
                        .addGap(0, 674, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1077, Short.MAX_VALUE))
                .addContainerGap())
        );
        inventoryLayout.setVerticalGroup(
            inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(available_rbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(all_rbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(out_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        main.add(inventory, "card3");

        exit_btn.setBackground(new java.awt.Color(255, 255, 204));
        exit_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        exit_btn.setText("Exit");
        exit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pos_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inventory_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lagout_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exit_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(date_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pos_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inventory_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lagout_btn)
                    .addComponent(exit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        conn = DBConnect.DBConnect();
        String sql3 = "INSERT INTO logs (action,user) VALUES ('Login','" + username + "')";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql3);
            stmt.close();
        } catch (Exception ex) {
        }
        cashier_lb.setText("Cashier : " + NAME);
        final String[] itemInfo = new String[5];
        TextAutoCompleter complete = new TextAutoCompleter(itemName_tf);
        String txt = itemName_tf.getText();
        String sql = "SELECT item_name FROM inventory WHERE stocks !=0 AND item_name LIKE '" + txt + "%'";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("item_name");
                complete.addItem(name);

            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        itemName_tf.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                item();
            }

            public void removeUpdate(DocumentEvent e) {
                item();
            }

            public void insertUpdate(DocumentEvent e) {
                item();
            }

            public void item() {
                String sql = "SELECT * FROM inventory WHERE stocks !=0";

                try {
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        itemInfo[0] = rs.getString("item_name");
                        itemInfo[1] = rs.getString("item_code");
                        itemInfo[2] = rs.getString("description");
                        itemInfo[3] = rs.getString("category");
                        itemInfo[4] = rs.getString("price");
                        if (itemName_tf.getText().equals(itemInfo[0])) {
                            String sql1 = "SELECT * FROM inventory WHERE item_name = '" + itemInfo[0] + "'";
                            try {

                                ps = conn.prepareStatement(sql1);
                                rs = ps.executeQuery();
                                while (rs.next()) {
                                    itemInfo[0] = rs.getString("item_name");
                                    itemInfo[1] = rs.getString("item_code");
                                    itemInfo[2] = rs.getString("description");
                                    itemInfo[3] = rs.getString("category");
                                    itemInfo[4] = rs.getString("price");
                                    itemCode_lb.setText(itemInfo[1]);
                                    itemName_lb.setText(itemInfo[0]);
                                    description_lb.setText(itemInfo[2]);
                                    category_lb.setText(itemInfo[3]);
                                    price_lb.setText(itemInfo[4]);

                                    quantity_tf.getDocument().addDocumentListener(new DocumentListener() {
                                        public void changedUpdate(DocumentEvent e) {

                                        }

                                        public void removeUpdate(DocumentEvent e) {
                                            update();
                                        }

                                        public void insertUpdate(DocumentEvent e) {
                                            insert();
                                        }

                                        public void insert() {
                                            try {
                                                String quant = quantity_tf.getText();
                                                if (quant != null) {
                                                    int quantity = Integer.parseInt(quantity_tf.getText());
                                                    total = quantity * Integer.parseInt(itemInfo[4]);
                                                    total_tf.setText(Double.toString(total));

                                                }
                                            } catch (Exception ex) {
                                                JOptionPane.showMessageDialog(null, "Invalid Quantity");
                                            }
                                        }

                                        public void update() {
                                            total_tf.setText("");
                                        }

                                    });
                                }
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }
                        }
                    }
                    ps.close();
                    rs.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

    }//GEN-LAST:event_formWindowOpened

    private void exit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_btnActionPerformed
        int opt = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            String sql = "INSERT INTO logs (action,user) VALUES ('Lagout','" + username + "')";
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                conn.close();
                stmt.close();
                this.hide();
                new Login().show();
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }
    }//GEN-LAST:event_exit_btnActionPerformed

    private void out_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_out_btnActionPerformed
        DefaultTableModel inventory_tbl = (DefaultTableModel) item_tbl.getModel();
        inventory_tbl.setRowCount(0);
        String sql = "SELECT * FROM inventory WHERE stocks = 0 OR stocks < 0";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String code = rs.getString("item_code");
                String name = rs.getString("item_name");
                String stocks = rs.getString("stocks");
                String price = rs.getString("price");
                Object[] item = {code, name, stocks, price};
                inventory_tbl.addRow(item);
            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_out_btnActionPerformed

    private void available_rbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_available_rbtnActionPerformed
        DefaultTableModel inventory_tbl = (DefaultTableModel) item_tbl.getModel();
        inventory_tbl.setRowCount(0);
        String sql = "SELECT * FROM inventory WHERE stocks > 0";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String code = rs.getString("item_code");
                String name = rs.getString("item_name");
                String stocks = rs.getString("stocks");
                String price = rs.getString("price");
                Object[] item = {code, name, stocks, price};
                inventory_tbl.addRow(item);
            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_available_rbtnActionPerformed

    private void all_rbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_all_rbtnActionPerformed
        DefaultTableModel inventory_tbl = (DefaultTableModel) item_tbl.getModel();
        inventory_tbl.setRowCount(0);
        String sql = "SELECT * FROM inventory";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String code = rs.getString("item_code");
                String name = rs.getString("item_name");
                String stocks = rs.getString("stocks");
                String price = rs.getString("price");
                Object[] item = {code, name, stocks, price};
                inventory_tbl.addRow(item);
            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_all_rbtnActionPerformed

    private void prcess_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prcess_btnActionPerformed
        int row1 = sales_tbl.getRowCount();
        int col1 = sales_tbl.getColumnCount();
        String[][] info = new String[row1][col1];
        for (int i = 0; i < row1; i++) {

            for (int j = 2; j < col1; j++) {
                info[i][j] = sales_tbl.getValueAt(i, j).toString();
                System.out.println(info[i][j]);
            }

        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }

        int row = sales_tbl.getRowCount();
        if ("".equals(amountDue_lb.getText())) {
            JOptionPane.showMessageDialog(null, "No item found");
        } else {
            Bill bill = new Bill();
            bill.salesTable = sales_tbl;
            bill.AmountDue = amountDue;
            bill.date = date_lb.getText();
            bill.sales = this;
            bill.Date = date_lb.getText();
            bill.Info = info;
            bill.row1 = row1;
            bill.col1 = col1;
            bill.show();
        }

    }//GEN-LAST:event_prcess_btnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String itemName = itemName_tf.getText();
        String quantity = quantity_tf.getText();
        String Total = total_tf.getText();
        if (!"".equals(itemName) || !"".equals(quantity) || !"".equals(Total)) {
            DefaultTableModel model = (DefaultTableModel) sales_tbl.getModel();
            DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            String curTimeDate = df.format(cal.getTime());
            String[] saleInfo = new String[6];
            saleInfo[0] = curTimeDate;
            saleInfo[1] = itemCode_lb.getText();
            saleInfo[2] = itemName_lb.getText();
            saleInfo[3] = price_lb.getText();
            saleInfo[4] = quantity_tf.getText();
            saleInfo[5] = total_tf.getText();
            Object[] row = {saleInfo[0], saleInfo[1], saleInfo[2], saleInfo[3], saleInfo[4], saleInfo[5]};
            model.addRow(row);
            FrameRefresh();
            amountDue += total;
            amountDue_lb.setText("Amount Due : " + Float.toString(amountDue));
        } else {

            JOptionPane.showMessageDialog(null, "No item found", "", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void lagout_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lagout_btnActionPerformed
        int opt = JOptionPane.showConfirmDialog(null, "Are you sure you want to lagout??", "Exit", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            String sql = "INSERT INTO logs (action,user) VALUES ('Lagout','" + username + "')";
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                conn.close();
                stmt.close();
                this.hide();
                new Login().show();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_lagout_btnActionPerformed

    private void inventory_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventory_btnActionPerformed
        main.removeAll();
        main.repaint();
        main.revalidate();
        main.add(inventory);
        main.repaint();
        main.revalidate();
        all_rbtn.setSelected(true);
        DefaultTableModel inventory_tbl = (DefaultTableModel) item_tbl.getModel();
        inventory_tbl.setRowCount(0);
        String sql = "SELECT * FROM inventory";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String code = rs.getString("item_code");
                String name = rs.getString("item_name");
                String stocks = rs.getString("stocks");
                String price = rs.getString("price");
                Object[] item = {code, name, stocks, price};
                inventory_tbl.addRow(item);
            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 4; i++) {
            item_tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }//GEN-LAST:event_inventory_btnActionPerformed

    private void pos_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pos_btnActionPerformed
        main.removeAll();
        main.repaint();
        main.revalidate();
        main.add(pos);
        main.repaint();
        main.revalidate();
    }//GEN-LAST:event_pos_btnActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        String sql = "INSERT INTO logs (action,user) VALUES ('Lagout','" + username + "')";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.close();
            stmt.close();
        } catch (Exception ex) {
        }

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
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton all_rbtn;
    private javax.swing.JLabel amountDue_lb;
    private javax.swing.JRadioButton available_rbtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel cashier_lb;
    private javax.swing.JLabel category_lb;
    private javax.swing.JLabel category_lb1;
    private javax.swing.JLabel date_lb;
    private javax.swing.JLabel description_lb;
    private javax.swing.JLabel description_lb1;
    private javax.swing.JButton exit_btn;
    private javax.swing.JPanel inventory;
    private javax.swing.JButton inventory_btn;
    private javax.swing.JLabel itemCode_lb;
    private javax.swing.JLabel itemName_lb;
    private javax.swing.JLabel itemName_lb1;
    private javax.swing.JTextField itemName_tf;
    private javax.swing.JTable item_tbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton lagout_btn;
    private javax.swing.JPanel main;
    private javax.swing.JRadioButton out_btn;
    private javax.swing.JPanel pos;
    private javax.swing.JButton pos_btn;
    private javax.swing.JButton prcess_btn;
    private javax.swing.JLabel price_lb;
    private javax.swing.JLabel price_lb1;
    private javax.swing.JLabel price_lb2;
    private javax.swing.JTextField quantity_tf;
    public javax.swing.JTable sales_tbl;
    private javax.swing.JTextField total_tf;
    // End of variables declaration//GEN-END:variables
}
