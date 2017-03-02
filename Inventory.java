package salesandinventorymanagementsystem;

import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author alger
 */
public class Inventory extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement ps = null;
    Statement stmt = null;
    ResultSet rs = null;
    int click = 0;
    String code = "";
    int editBtn = 0;
    String username;
    String USERTYPE;
    String NAME;
    float sum = 0;
    String fromDate;
    String toDate;

    public Inventory() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CenterText();
        itemtbl();
        CostumerTbl();
        AccountTble();
        RecordsTble();
        Date();
        setTitle("Amores Inventory Management");
        setResizable(false);
        btn_icons();

    }

    public void btn_icons() {
        newItem.setIcon(new ImageIcon("img/addITEM.png"));
        newCustomer.setIcon(new ImageIcon("img/addCOSTUMER.png"));
        newEmployee.setIcon(new ImageIcon("img/addACCOUNT.png"));
        logs.setIcon(new ImageIcon("img/viewRECORDS.png"));
        help.setIcon(new ImageIcon("img/help.png"));
    }

    public void Date() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
        Date date = new Date();
        date_lb.setText(sdf.format(date));
    }

    public void CenterText() {
        JLabel[] lb = {lb1, lb2, lb3, lb4, lb5};
        for (int i = 0; i < lb.length; i++) {
            lb[i].setHorizontalAlignment(SwingConstants.CENTER);
        }
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

    public void item_tblRefresh() {
        item_tbl.setDefaultEditor(Object.class, null);
        click = 0;
        DefaultTableModel model = (DefaultTableModel) item_tbl.getModel();
        model.setRowCount(0);
        String sql = "SELECT * FROM inventory";
        String[] item_info = new String[4];
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                item_info[0] = rs.getString("item_code");
                item_info[1] = rs.getString("item_name");
                item_info[2] = rs.getString("stocks");
                item_info[3] = rs.getString("price");
                Object[] item = {item_info[0], item_info[1], item_info[2], item_info[3]};
                model.addRow(item);
            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        itmCode_tf.setText("");
        itemName_tf.setText("");
        cat_cb.setSelectedItem("Select Any");
        description_tf.setText("");
        price_tf.setText("");
        stocks_tf.setText("");
        search_tf.setText("");
        itmCode_tf.setEditable(false);
        itemName_tf.setEnabled(true);
        description_tf.setEnabled(true);
        price_tf.setEnabled(true);
        stocks_tf.setEnabled(true);
        cat_cb.setEnabled(true);
    }

    public void itemtbl() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 4; i++) {
            item_tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        item_tbl.setDefaultEditor(Object.class, null);
        item_tbl.setRowHeight(item_tbl.getRowHeight() + 5);
        item_tbl.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable item_tbl = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = item_tbl.rowAtPoint(p);

                if (me.getClickCount() == 1) {
                    String item_code = item_tbl.getModel().getValueAt(row, 0).toString();
                    String sql = "SELECT * FROM inventory WHERE item_code = " + item_code + "";
                    String[] itemInfo = new String[5];
                    try {
                        ps = conn.prepareStatement(sql);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            itemInfo[0] = rs.getString("item_name");
                            itemInfo[1] = rs.getString("category");
                            itemInfo[2] = rs.getString("description");
                            itemInfo[3] = rs.getString("price");
                            itemInfo[4] = rs.getString("stocks");

                            itemName_tf.setText(itemInfo[0]);
                            cat_cb.setSelectedItem(itemInfo[1]);
                            description_tf.setText(itemInfo[2]);
                            price_tf.setText(itemInfo[3]);
                            stocks_tf.setText(itemInfo[4]);
                            itmCode_tf.setText(item_code);
                        }
                        ps.close();
                        rs.close();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }

            private void setVisible(boolean b) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    public void CostumerFormRefresh() {

        customer_name.setText("");
        customer_address.setText("");
        customer_city.setText("");
        customer_province.setText("");
        pincode.setText("");
        contact_number.setText("");
        email_add.setText("");
        male.setSelected(true);

    }

    public void CostumerTbl() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        costumer_tbl.setAutoResizeMode(AUTO_RESIZE_OFF);
        TableColumn col = costumer_tbl.getColumnModel().getColumn(0);
        for (int i = 0; i < 9; i++) {
            col = costumer_tbl.getColumnModel().getColumn(i);
            col.setPreferredWidth(200);
            costumer_tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        costumer_tbl.setRowHeight(costumer_tbl.getRowHeight() + 5);

    }

    public void AccountTble() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        account_tbl.setAutoResizeMode(AUTO_RESIZE_OFF);
        TableColumn col = account_tbl.getColumnModel().getColumn(0);
        for (int i = 0; i < 8; i++) {
            col = account_tbl.getColumnModel().getColumn(i);
            col.setPreferredWidth(150);
            account_tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        account_tbl.setRowHeight(account_tbl.getRowHeight() + 5);

    }

    public void RecordsTble() {
        {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < 3; i++) {
                systemLog_tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            for (int i = 0; i < 4; i++) {
                inventoryLog_tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            for (int i = 0; i < 3; i++) {
                costumerLog_tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            for (int i = 0; i < 4; i++) {
                accountLog_tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            systemLog_tbl.setRowHeight(account_tbl.getRowHeight() + 5);
            inventoryLog_tbl.setRowHeight(account_tbl.getRowHeight() + 5);
            costumerLog_tbl.setRowHeight(account_tbl.getRowHeight() + 5);
            accountLog_tbl.setRowHeight(account_tbl.getRowHeight() + 5);
            systemLog_tbl.setAutoResizeMode(AUTO_RESIZE_OFF);
            TableColumn col1 = systemLog_tbl.getColumnModel().getColumn(0);
            TableColumn col2 = systemLog_tbl.getColumnModel().getColumn(1);
            TableColumn col3 = systemLog_tbl.getColumnModel().getColumn(2);
            col1 = systemLog_tbl.getColumnModel().getColumn(0);
            col2 = systemLog_tbl.getColumnModel().getColumn(1);
            col3 = systemLog_tbl.getColumnModel().getColumn(2);
            col1.setPreferredWidth(180);
            col2.setPreferredWidth(180);
            col3.setPreferredWidth(570);
        }
        {
            costumerLog_tbl.setAutoResizeMode(AUTO_RESIZE_OFF);
            TableColumn col1 = costumerLog_tbl.getColumnModel().getColumn(0);
            TableColumn col2 = costumerLog_tbl.getColumnModel().getColumn(1);
            TableColumn col3 = costumerLog_tbl.getColumnModel().getColumn(2);
            col1 = costumerLog_tbl.getColumnModel().getColumn(0);
            col2 = costumerLog_tbl.getColumnModel().getColumn(1);
            col3 = costumerLog_tbl.getColumnModel().getColumn(2);
            col1.setPreferredWidth(180);
            col2.setPreferredWidth(180);
            col3.setPreferredWidth(570);
        }
    }

    public void costumerTableRefresh() {
        costumer_info_txtarea.setText("");
        costumer_info_txtarea.setText("Display Costumer Information");
        DefaultTableModel costumerTable = (DefaultTableModel) costumer_tbl.getModel();
        costumerTable.setRowCount(0);
        String sql = "SELECT * FROM costumer";
        try {
            String[] costumerInfo = new String[9];
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                costumerInfo[0] = rs.getString("customerName");
                costumerInfo[1] = rs.getString("gender");
                costumerInfo[2] = rs.getString("address");
                costumerInfo[3] = rs.getString("city");
                costumerInfo[4] = rs.getString("province");
                costumerInfo[5] = rs.getString("pincode");
                costumerInfo[6] = rs.getString("phone_number");
                costumerInfo[7] = rs.getString("costumer_email");
                costumerInfo[8] = rs.getString("costumer_id");
                Object[] s = {costumerInfo[0], costumerInfo[1], costumerInfo[2], costumerInfo[3], costumerInfo[4], costumerInfo[5], costumerInfo[6], costumerInfo[7], costumerInfo[8]};
                costumerTable.addRow(s);
            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void CostumerTbl_mouseListener() {
        costumer_tbl.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable costumer_tbl = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = costumer_tbl.rowAtPoint(p);
                if (me.getClickCount() == 1) {
                    String costumerNo = costumer_tbl.getModel().getValueAt(row, 8).toString();
                    String sql = "SELECT *  FROM costumer WHERE costumer_id = '" + costumerNo + "'";
                    String[] info = new String[13];
                    try {

                        ps = conn.prepareStatement(sql);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            info[0] = rs.getString("customerName");
                            info[1] = rs.getString("gender");
                            info[2] = rs.getString("address");
                            info[3] = rs.getString("city");
                            info[4] = rs.getString("province");
                            info[5] = rs.getString("pincode");
                            info[6] = rs.getString("phone_number");
                            info[7] = rs.getString("costumer_email");

                        }
                        ps.close();
                        rs.close();
                        String infos = "Customer Information \n------------------------------------------------------------\nCustomer Name : " + info[0] + "\n\n" + "Gender : " + info[1] + "\n\nAddress : " + info[2] + "\n\nCity: " + info[3] + ""
                                + "\n\nProvince: " + info[4] + "\n\nPostal Code: " + info[5] + "\n\n" + "Phone Number : " + info[6] + "\n\n" + "Customer Email : " + info[7];
                        costumer_info_txtarea.setText("");
                        costumer_info_txtarea.setText(infos);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
    }

    public void CostumerUpdateTbl() {
        costumer_tbl.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    TableModel costumer_tbl = (TableModel) e.getSource();
                    String data = (String) costumer_tbl.getValueAt(row, column);
                    String id = (String) costumer_tbl.getValueAt(row, 8);
                    String[] title = {"customerName", "gender", "address", "city", "province", "pincode", "phone_number", "costumer_email"};

                    String sql = "UPDATE costumer SET " + title[column] + "= '" + data + "' WHERE costumer_id = '" + id + "'";
                    try {
                        stmt = conn.createStatement();
                        stmt.executeUpdate(sql);
                        String costumerNo = costumer_tbl.getValueAt(row, 8).toString();
                        String sql1 = "SELECT *  FROM costumer WHERE costumer_id = '" + costumerNo + "'";
                        String[] info = new String[8];
                        try {

                            ps = conn.prepareStatement(sql1);
                            rs = ps.executeQuery();
                            while (rs.next()) {
                                info[0] = rs.getString("customerName");
                                info[1] = rs.getString("gender");
                                info[2] = rs.getString("address");
                                info[3] = rs.getString("city");
                                info[4] = rs.getString("province");
                                info[5] = rs.getString("pincode");
                                info[6] = rs.getString("phone_number");
                                info[7] = rs.getString("costumer_email");

                            }
                            ps.close();
                            rs.close();
                            String infos = "Customer Information \n------------------------------------------------------------\nCustomer Name : " + info[0] + "\n\n" + "Gender : " + info[1] + "\n\nAddress : " + info[2] + "\n\nCity: " + info[3] + ""
                                    + "\n\nProvince: " + info[4] + "\n\nPostal Code: " + info[5] + "\n\n" + "Phone Number : " + info[6] + "\n\n" + "Customer Email : " + info[7];
                            costumer_info_txtarea.setText("");
                            costumer_info_txtarea.setText(infos);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Postal Code");
                    }
                }
            }
        });
    }

    public void AccountFormRefresh() {
        JTextField[] accInfo = {user_tf, pass_tf, accEmail_tf, accPhone_tf, accFname_tf, accLname_tf, dobYear_tf};
        for (int i = 0; i < accInfo.length; i++) {
            accInfo[i].setText("");
        }
        dob_month.setSelectedItem("Month");
        dob_day.setSelectedItem("Day");
        dobYear_tf.setText("YYYY");
        lb.setText("img/default.png");
        m_rbtn.setSelected(true);

    }

    public void AccountTableRefresh() {
        profPic2.setIcon(new ImageIcon("img/default.png"));
        accountType_cb.setEnabled(false);
        accName_tf.setEditable(false);
        edit_btn1.setText("Edit");
        editBtn = 0;
        DefaultTableModel model = (DefaultTableModel) account_tbl.getModel();
        model.setRowCount(0);
        String sql = "SELECT * FROM account";
        String[] accountInfo = new String[8];
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                accountInfo[0] = rs.getString("account_number");
                accountInfo[1] = rs.getString("fname");
                accountInfo[2] = rs.getString("lname");
                accountInfo[3] = rs.getString("gender");
                accountInfo[4] = rs.getString("email");
                accountInfo[5] = rs.getString("phone_number");
                accountInfo[6] = rs.getString("dob");
                accountInfo[7] = rs.getString("accountType");
                model.addRow(accountInfo);
            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    void AccounTblMouseListener() {
        account_tbl.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable costumer_tbl = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = costumer_tbl.rowAtPoint(p);
                if (me.getClickCount() == 1) {
                    String accNumber = costumer_tbl.getModel().getValueAt(row, 0).toString();
                    String sql = "SELECT * FROM account WHERE account_number = '" + accNumber + "'";
                    String accName = null;
                    String accType = null;
                    String imgPath = null;

                    try {

                        ps = conn.prepareStatement(sql);
                        rs = ps.executeQuery();
                        while (rs.next()) {

                            accName = rs.getString("username");
                            accType = rs.getString("accountType");
                            imgPath = rs.getString("id_picture");

                            profPic2.setIcon(new ImageIcon(imgPath));
                            accName_tf.setText(accName);
                            accountType_cb.setSelectedItem(accType);
                        }
                        ps.close();
                        rs.close();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }
            }

        });
    }

    public void AccountTblUpdate() {
        account_tbl.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    TableModel account = (TableModel) e.getSource();
                    String data = (String) account.getValueAt(row, column);
                    String id = (String) account_tbl.getModel().getValueAt(row, 0);
                    String[] title = {"account_number", "fname", "lname", "gender", "email", "phone_number", "dob"};
                    String sql = "UPDATE account SET " + title[column] + " = '" + data + "' WHERE account_number = " + id + " ";
                    try {

                        stmt = conn.createStatement();
                        stmt.executeUpdate(sql);
                        stmt.close();
                        AccountTableRefresh();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }
            }
        });
    }

    public void Logs() {
        String sql1 = "SELECT * FROM logs ORDER BY action_id ";
        DefaultTableModel model = (DefaultTableModel) systemLog_tbl.getModel();
        model.setRowCount(0);
        try {
            ps = conn.prepareStatement(sql1);
            rs = ps.executeQuery();
            while (rs.next()) {
                String date_time = rs.getString("date/time");
                String action = rs.getString("action");
                String user = rs.getString("user");
                String act = user + " has " + action;
                Object[] row = {date_time, user, act};
                model.addRow(row);
            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void InventoryLogs() {
        DefaultTableModel model = (DefaultTableModel) inventoryLog_tbl.getModel();
        model.setRowCount(0);
        String sql = "SELECT * FROM inventory_log";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String date_time = rs.getString("date_time");
                String user = rs.getString("user");
                String action = rs.getString("action");
                String itemName = rs.getString("item_name");
                Object[] row = {date_time, user, action, itemName};
                model.addRow(row);

            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void CostumerLog() {
        DefaultTableModel model = (DefaultTableModel) costumerLog_tbl.getModel();
        model.setRowCount(0);
        String sql = "SELECT * FROM costumer_log";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String date_time = rs.getString("date_time");
                String user = rs.getString("user");
                String action = rs.getString("action");
                Object[] row = {date_time, user, action};
                model.addRow(row);

            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void AccountLog() {
        DefaultTableModel model = (DefaultTableModel) accountLog_tbl.getModel();
        model.setRowCount(0);
        String sql = "SELECT * FROM account_log";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String date = rs.getString("date_time");
                String user = rs.getString("user");
                String accountName = rs.getString("account");
                String action = rs.getString("action");
                Object[] log = {date, user, action, accountName};
                model.addRow(log);
            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jButton39 = new javax.swing.JButton();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        main_panel = new javax.swing.JPanel();
        Inventory_panel = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jButton21 = new javax.swing.JButton();
        search_cb = new javax.swing.JComboBox<>();
        search_tf = new javax.swing.JTextField();
        sort_cb = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        item_tbl = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        itmCode_tf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        itemName_tf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        description_tf = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        price_tf = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        stocks_tf = new javax.swing.JTextField();
        UPDATE = new javax.swing.JButton();
        del_btn = new javax.swing.JButton();
        UPDATE2 = new javax.swing.JButton();
        cat_cb = new javax.swing.JComboBox<>();
        addItem_panel = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        stocks_tf1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        itemName_tf1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        price_tf1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        itmCode_tf1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cat_cb1 = new javax.swing.JComboBox<>();
        description_tf1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton25 = new javax.swing.JButton();
        clear_btn = new javax.swing.JButton();
        costumer_panel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        costumer_tbl = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        costumer_info_txtarea = new javax.swing.JTextArea();
        delete_btn = new javax.swing.JButton();
        EmployeeReg_panel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        pass_tf = new javax.swing.JTextField();
        user_tf = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        accEmail_tf = new javax.swing.JTextField();
        accPhone_tf = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        accType_cb = new javax.swing.JComboBox<>();
        jPanel41 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        accLname_tf = new javax.swing.JTextField();
        accFname_tf = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        m_rbtn = new javax.swing.JRadioButton();
        f_rbtn = new javax.swing.JRadioButton();
        dob_month = new javax.swing.JComboBox<>();
        dob_day = new javax.swing.JComboBox<>();
        dobYear_tf = new javax.swing.JTextField();
        profPic = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        lb = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        Accounts_panel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        account_tbl = new javax.swing.JTable();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        profPic2 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        accName_tf = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        accountType_cb = new javax.swing.JComboBox<>();
        accDelete_btn = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        edit_btn1 = new javax.swing.JButton();
        logs_panel = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel44 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        systemLog_tbl = new javax.swing.JTable();
        jButton35 = new javax.swing.JButton();
        jPanel45 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        inventoryLog_tbl = new javax.swing.JTable();
        jButton36 = new javax.swing.JButton();
        jPanel46 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        costumerLog_tbl = new javax.swing.JTable();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jPanel47 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        accountLog_tbl = new javax.swing.JTable();
        jButton41 = new javax.swing.JButton();
        customerR_panel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        customer_name = new javax.swing.JTextField();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        customer_address = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        customer_city = new javax.swing.JTextField();
        customer_province = new javax.swing.JTextField();
        pincode = new javax.swing.JTextField();
        contact_number = new javax.swing.JTextField();
        email_add = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        newItem = new javax.swing.JButton();
        logs = new javax.swing.JButton();
        newCustomer = new javax.swing.JButton();
        newEmployee = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        lb4 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        lb1 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        lb2 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        lb3 = new javax.swing.JLabel();
        help = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        lb5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        date_lb = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        jMenu7.setText("File");
        jMenuBar4.add(jMenu7);

        jMenu8.setText("Edit");
        jMenuBar4.add(jMenu8);

        jButton39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton39.setText("Clear Log");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        main_panel.setBackground(new java.awt.Color(204, 204, 255));
        main_panel.setLayout(new java.awt.CardLayout());

        Inventory_panel.setBackground(new java.awt.Color(204, 204, 255));

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel23.setBackground(new java.awt.Color(204, 204, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton21.setText("Search");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        search_cb.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        search_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Any", "Item Code", "Item Name", "Stocks", "Price" }));
        search_cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_cbActionPerformed(evt);
            }
        });

        search_tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        sort_cb.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sort_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Any", "Item Code", "Item Name", "Stocks", "Price" }));
        sort_cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sort_cbActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Search By:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Sort By:");

        item_tbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        item_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Code", "Item Name", "Stocks", "Price"
            }
        ));
        jScrollPane1.setViewportView(item_tbl);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(2, 2, 2)
                        .addComponent(sort_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search_tf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton21)))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_tf)
                    .addComponent(search_cb)
                    .addComponent(jLabel5)
                    .addComponent(sort_cb)
                    .addComponent(jLabel8))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        jPanel10.setBackground(new java.awt.Color(204, 204, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("ITEM CODE");

        itmCode_tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("ITEM NAME");

        itemName_tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("CATEGORY");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("DESCRIPTION");

        description_tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("PRICE");

        price_tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText("STOCKS");

        stocks_tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        UPDATE.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        UPDATE.setText("Update");
        UPDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPDATEActionPerformed(evt);
            }
        });

        del_btn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        del_btn.setText("Delete");
        del_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                del_btnActionPerformed(evt);
            }
        });

        UPDATE2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        UPDATE2.setText("Stock In");
        UPDATE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPDATE2ActionPerformed(evt);
            }
        });

        cat_cb.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cat_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hollowblocks", "Finesand", "Coarsesand", "Rivermixed", "Concrete Products", "Cement/ deform bars", "Coco Lumber", "Filling Materials", "Crushed Aggregate G-3/4", "Crushed Aggregate G-" }));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(UPDATE2, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(del_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UPDATE))
                    .addComponent(stocks_tf, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(price_tf, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(description_tf, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cat_cb, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemName_tf, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(itmCode_tf, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(210, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(224, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(179, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(195, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(192, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itmCode_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemName_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cat_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(description_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(price_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stocks_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UPDATE2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UPDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(del_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout Inventory_panelLayout = new javax.swing.GroupLayout(Inventory_panel);
        Inventory_panel.setLayout(Inventory_panelLayout);
        Inventory_panelLayout.setHorizontalGroup(
            Inventory_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Inventory_panelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Inventory_panelLayout.setVerticalGroup(
            Inventory_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Inventory_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        main_panel.add(Inventory_panel, "card2");

        addItem_panel.setBackground(new java.awt.Color(204, 204, 255));

        jPanel11.setBackground(new java.awt.Color(204, 204, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel12.setBackground(new java.awt.Color(204, 204, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0), "Add new item", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), java.awt.Color.black)); // NOI18N

        stocks_tf1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Description:");

        itemName_tf1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Price:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Item Name:");

        price_tf1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Category:");

        itmCode_tf1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Stocks:");

        cat_cb1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cat_cb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Any", "Hollowblocks", "Finesand", "Coarsesand", "Rivermixed", "Concrete Products", "Cement/ deform bars", "Coco Lumber", "Filling Materials", "Crushed Aggregate G-3/4", "Crushed Aggregate G-1" }));

        description_tf1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Item Code:");

        jButton25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton25.setText("ADD");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        clear_btn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        clear_btn.setText("CLEAR");
        clear_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(clear_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cat_cb1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(itemName_tf1)
                            .addComponent(itmCode_tf1)
                            .addComponent(description_tf1)
                            .addComponent(price_tf1)
                            .addComponent(stocks_tf1))))
                .addGap(10, 10, 10))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(itmCode_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(itemName_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cat_cb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(description_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(price_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(stocks_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton25)
                    .addComponent(clear_btn))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(312, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );

        javax.swing.GroupLayout addItem_panelLayout = new javax.swing.GroupLayout(addItem_panel);
        addItem_panel.setLayout(addItem_panelLayout);
        addItem_panelLayout.setHorizontalGroup(
            addItem_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addItem_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        addItem_panelLayout.setVerticalGroup(
            addItem_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addItem_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        main_panel.add(addItem_panel, "card3");

        costumer_panel.setBackground(new java.awt.Color(204, 204, 255));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Customer Information Table", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        costumer_tbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        costumer_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Name", "Gender", "Address", "City", "Province", "Pincode", "Phone Number", "Email", "Customer id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        costumer_tbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(costumer_tbl);

        costumer_info_txtarea.setEditable(false);
        costumer_info_txtarea.setColumns(20);
        costumer_info_txtarea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        costumer_info_txtarea.setRows(5);
        costumer_info_txtarea.setText("\n");
        jScrollPane3.setViewportView(costumer_info_txtarea);

        delete_btn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        delete_btn.setText("Delete Costumer");
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(delete_btn)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(6, 6, 6)
                .addComponent(delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout costumer_panelLayout = new javax.swing.GroupLayout(costumer_panel);
        costumer_panel.setLayout(costumer_panelLayout);
        costumer_panelLayout.setHorizontalGroup(
            costumer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costumer_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        costumer_panelLayout.setVerticalGroup(
            costumer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costumer_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        main_panel.add(costumer_panel, "card4");

        EmployeeReg_panel.setBackground(new java.awt.Color(204, 204, 255));

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel40.setBackground(new java.awt.Color(204, 204, 255));
        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0), "Account Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 12), java.awt.Color.black)); // NOI18N

        pass_tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pass_tf.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pass_tf.setFocusTraversalPolicyProvider(true);

        user_tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        user_tf.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        user_tf.setFocusTraversalPolicyProvider(true);
        user_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_tfActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText("Phone Number:");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("Account Name:");

        accEmail_tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        accEmail_tf.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        accEmail_tf.setFocusTraversalPolicyProvider(true);

        accPhone_tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        accPhone_tf.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        accPhone_tf.setFocusTraversalPolicyProvider(true);

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel34.setText("Password:");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel35.setText("Email:");

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel63.setText("Position:");

        accType_cb.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        accType_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cashier", "Clerk", "Manager" }));
        accType_cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accType_cbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pass_tf, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(user_tf)
                            .addComponent(accEmail_tf))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(accPhone_tf, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(accType_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(user_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(pass_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(accEmail_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(accPhone_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(accType_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        jPanel41.setBackground(new java.awt.Color(204, 204, 255));
        jPanel41.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0), "Personal Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 12), java.awt.Color.black)); // NOI18N

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel64.setText("Date of Birth:");

        accLname_tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        accLname_tf.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        accLname_tf.setFocusTraversalPolicyProvider(true);

        accFname_tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        accFname_tf.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        accFname_tf.setFocusTraversalPolicyProvider(true);

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel65.setText("First Name:");

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel66.setText("Last Name:");

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel67.setText("Gender:");

        m_rbtn.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(m_rbtn);
        m_rbtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        m_rbtn.setText("Male");

        f_rbtn.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(f_rbtn);
        f_rbtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        f_rbtn.setText("Female");

        dob_month.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dob_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        dob_day.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dob_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        dobYear_tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dobYear_tf.setText("YYYY");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65)
                    .addComponent(jLabel66)
                    .addComponent(jLabel67)
                    .addComponent(jLabel64))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel41Layout.createSequentialGroup()
                                .addComponent(dob_month, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dob_day, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dobYear_tf, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                            .addGroup(jPanel41Layout.createSequentialGroup()
                                .addComponent(m_rbtn)
                                .addGap(18, 18, 18)
                                .addComponent(f_rbtn)))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(accLname_tf, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(accFname_tf))
                        .addContainerGap())))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(accFname_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(accLname_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(m_rbtn)
                    .addComponent(f_rbtn))
                .addGap(9, 9, 9)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(dob_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dob_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dobYear_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        profPic.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\SalesAndInventoryManagementSystem\\img\\default.png")); // NOI18N
        profPic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton29.setText("Choose a File");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setText("Upload");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        lb.setForeground(new java.awt.Color(255, 255, 255));
        lb.setText("jLabel35");

        jButton31.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton31.setText("Save");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton32.setText("Clear");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton33.setText("Cancel");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton31)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton29)
                            .addComponent(profPic, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton30))
                        .addGap(0, 180, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(profPic, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout EmployeeReg_panelLayout = new javax.swing.GroupLayout(EmployeeReg_panel);
        EmployeeReg_panel.setLayout(EmployeeReg_panelLayout);
        EmployeeReg_panelLayout.setHorizontalGroup(
            EmployeeReg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeReg_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        EmployeeReg_panelLayout.setVerticalGroup(
            EmployeeReg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeReg_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        main_panel.add(EmployeeReg_panel, "card6");

        Accounts_panel.setBackground(new java.awt.Color(204, 204, 255));

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User Account Table", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        account_tbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        account_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Account Number", "First Name", "Last Name", "Gender", "Email", "Phone", "Date of Birth", "Accesibility Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(account_tbl);

        jPanel42.setBackground(new java.awt.Color(204, 204, 255));
        jPanel42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel43.setBackground(new java.awt.Color(102, 102, 102));

        profPic2.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\SalesAndInventoryManagementSystem\\img\\default.png")); // NOI18N

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profPic2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(profPic2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel68.setText("Account Name:");

        accName_tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel69.setText("Position");

        accountType_cb.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        accountType_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cashier", "Clerk", "Manager" }));

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(accountType_cb, 0, 205, Short.MAX_VALUE)
                    .addComponent(accName_tf, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel68)
                .addGap(6, 6, 6)
                .addComponent(accName_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel69)
                .addGap(6, 6, 6)
                .addComponent(accountType_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        accDelete_btn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        accDelete_btn.setText("Delete");
        accDelete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accDelete_btnActionPerformed(evt);
            }
        });

        jButton34.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton34.setText("Change Image");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        edit_btn1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        edit_btn1.setText("Edit");
        edit_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_btn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(accDelete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edit_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(accDelete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout Accounts_panelLayout = new javax.swing.GroupLayout(Accounts_panel);
        Accounts_panel.setLayout(Accounts_panelLayout);
        Accounts_panelLayout.setHorizontalGroup(
            Accounts_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Accounts_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Accounts_panelLayout.setVerticalGroup(
            Accounts_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Accounts_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        main_panel.add(Accounts_panel, "card7");

        logs_panel.setBackground(new java.awt.Color(204, 204, 255));

        jPanel13.setBackground(new java.awt.Color(204, 204, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jPanel44.setBackground(new java.awt.Color(204, 204, 255));

        systemLog_tbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        systemLog_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date/Time", "User", "Action"
            }
        ));
        jScrollPane5.setViewportView(systemLog_tbl);

        jButton35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton35.setText("Clear Log");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton35)))
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        jTabbedPane1.addTab("System Log", jPanel44);

        jPanel45.setBackground(new java.awt.Color(204, 204, 255));

        inventoryLog_tbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inventoryLog_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date/Time", "User", "Action", "Item Name"
            }
        ));
        jScrollPane9.setViewportView(inventoryLog_tbl);

        jButton36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton36.setText("Clear Log");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton36)))
                .addContainerGap())
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        jTabbedPane1.addTab("Inventory Log", jPanel45);

        jPanel46.setBackground(new java.awt.Color(204, 204, 255));

        costumerLog_tbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        costumerLog_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date/Time", "User", "Action"
            }
        ));
        jScrollPane10.setViewportView(costumerLog_tbl);

        jButton37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton37.setText("Clear Log");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jButton38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton38.setText("Clear Log");

        jButton40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton40.setText("Clear Log");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton40)))
                .addContainerGap())
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel46Layout.createSequentialGroup()
                    .addGap(424, 424, 424)
                    .addComponent(jButton37)
                    .addContainerGap(433, Short.MAX_VALUE)))
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel46Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton38)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel46Layout.createSequentialGroup()
                    .addGap(204, 204, 204)
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(204, Short.MAX_VALUE)))
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel46Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton38)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Costumer Log", jPanel46);

        jPanel47.setBackground(new java.awt.Color(204, 204, 255));

        accountLog_tbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        accountLog_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date/Time", "User", "Action", "Account Name"
            }
        ));
        jScrollPane11.setViewportView(accountLog_tbl);

        jButton41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton41.setText("Clear Log");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton41)))
                .addContainerGap())
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        jTabbedPane1.addTab("Account Log", jPanel47);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout logs_panelLayout = new javax.swing.GroupLayout(logs_panel);
        logs_panel.setLayout(logs_panelLayout);
        logs_panelLayout.setHorizontalGroup(
            logs_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logs_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        logs_panelLayout.setVerticalGroup(
            logs_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logs_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        main_panel.add(logs_panel, "card8");

        customerR_panel.setBackground(new java.awt.Color(204, 204, 255));

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel15.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Customer Name:");

        customer_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        male.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup2.add(male);
        male.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        male.setText("Male ");

        female.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup2.add(female);
        female.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        female.setText("Female");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setText("Gender :");

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setText("Address :");

        customer_address.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setText("City :");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setText("Province :");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel42.setText("Pin Code :");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel43.setText("Contact Number :");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel44.setText("Email Address:");

        customer_city.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        customer_province.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        pincode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        contact_number.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        email_add.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Clear");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(email_add, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(contact_number, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pincode, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customer_province, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customer_city, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customer_address, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customer_name, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(male, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(female)))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(customer_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(male, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(female, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customer_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customer_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customer_province, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pincode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contact_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Customer Information");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(272, 272, 272))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout customerR_panelLayout = new javax.swing.GroupLayout(customerR_panel);
        customerR_panel.setLayout(customerR_panelLayout);
        customerR_panelLayout.setHorizontalGroup(
            customerR_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerR_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        customerR_panelLayout.setVerticalGroup(
            customerR_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerR_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        main_panel.add(customerR_panel, "card9");

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel30.setBackground(new java.awt.Color(51, 51, 51));

        newItem.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\SalesAndInventoryManagementSystem\\img\\addITEM.png")); // NOI18N
        newItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newItemActionPerformed(evt);
            }
        });

        logs.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\SalesAndInventoryManagementSystem\\img\\viewRECORDS.png")); // NOI18N
        logs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logsActionPerformed(evt);
            }
        });

        newCustomer.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\SalesAndInventoryManagementSystem\\img\\addCOSTUMER.png")); // NOI18N
        newCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCustomerActionPerformed(evt);
            }
        });

        newEmployee.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\SalesAndInventoryManagementSystem\\img\\addACCOUNT.png")); // NOI18N
        newEmployee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newEmployeeActionPerformed(evt);
            }
        });

        jPanel33.setBackground(new java.awt.Color(204, 204, 255));

        lb4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lb4.setText("Logs");
        lb4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lb4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lb4))
        );

        jPanel34.setBackground(new java.awt.Color(204, 204, 255));

        lb1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lb1.setText("New Item");
        lb1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lb1))
        );

        jPanel35.setBackground(new java.awt.Color(204, 204, 255));

        lb2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lb2.setText("New Costumer");
        lb2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lb2))
        );

        jPanel36.setBackground(new java.awt.Color(204, 204, 255));

        lb3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lb3.setText("New Employee");
        lb3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lb3))
        );

        help.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\SalesAndInventoryManagementSystem\\img\\help.png")); // NOI18N
        help.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });

        jPanel32.setBackground(new java.awt.Color(204, 204, 255));

        lb5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lb5.setText("Help");
        lb5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lb5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newItem, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logs, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(help, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(logs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(newEmployee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(newCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(newItem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(help, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(0, 0, 0)));

        jButton16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton16.setText("Inventory");
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton17.setText("Customer");
        jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton18.setText("Account");
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton20.setText("Change User");
        jButton20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton24.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton24.setText("Exit");
        jButton24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton19.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton19.setText("Sales");
        jButton19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        date_lb.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        date_lb.setForeground(new java.awt.Color(204, 204, 255));
        date_lb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        date_lb.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(jButton18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(date_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(date_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(234, Short.MAX_VALUE))
        );

        jPanel37.setBackground(new java.awt.Color(204, 204, 255));
        jPanel37.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        jLabel39.setFont(new java.awt.Font("Times New Roman", 0, 25)); // NOI18N
        jLabel39.setText("   MAIN MENU");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jMenu9.setBackground(new java.awt.Color(255, 255, 255));

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setText("View Inventory");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem2.setText("Register Item");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem2);

        jMenuBar1.add(jMenu9);

        setJMenuBar(jMenuBar1);

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

    private void newItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newItemActionPerformed
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();
        main_panel.add(addItem_panel);
        main_panel.repaint();
        main_panel.revalidate();
        Code();
        itmCode_tf1.setText(code);
        itmCode_tf1.setEditable(false);
        itemName_tf1.requestFocus();
        itemName_tf1.setText("");
        description_tf1.setText("");
        price_tf1.setText("");
        stocks_tf1.setText("");
        cat_cb1.setSelectedItem("Select Any");
    }//GEN-LAST:event_newItemActionPerformed

    private void logsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logsActionPerformed
        if (!"Manager".equals(USERTYPE)) {
            JOptionPane.showMessageDialog(null, "You need permission to perform this action", "Access Denied", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Logs();
            InventoryLogs();
            CostumerLog();
            AccountLog();
            main_panel.removeAll();
            main_panel.repaint();
            main_panel.revalidate();
            main_panel.add(logs_panel);
            main_panel.repaint();
            main_panel.revalidate();
        }
    }//GEN-LAST:event_logsActionPerformed

    private void newCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCustomerActionPerformed
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();
        main_panel.add(customerR_panel);
        main_panel.repaint();
        main_panel.revalidate();
        CostumerFormRefresh();
    }//GEN-LAST:event_newCustomerActionPerformed

    private void newEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newEmployeeActionPerformed
        ImageIcon c = new ImageIcon("img/default.png");
        profPic.setIcon(c);
        AccountFormRefresh();
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();
        main_panel.add(EmployeeReg_panel);
        main_panel.repaint();
        main_panel.revalidate();
    }//GEN-LAST:event_newEmployeeActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();
        main_panel.add(Inventory_panel);
        main_panel.repaint();
        main_panel.revalidate();
        item_tblRefresh();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();
        main_panel.add(costumer_panel);
        main_panel.repaint();
        main_panel.revalidate();
        costumerTableRefresh();
        CostumerTbl_mouseListener();
        CostumerUpdateTbl();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();
        main_panel.add(Accounts_panel);
        main_panel.repaint();
        main_panel.revalidate();
        AccountTableRefresh();
        AccountTblUpdate();
        AccounTblMouseListener();
        profPic2.setIcon(new ImageIcon("img/default.png"));
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        int opt = JOptionPane.showConfirmDialog(null, "Are you sure you want to lagout?", "Lagout", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            String sql = "INSERT INTO logs (action,user) VALUES ('Lagout','" + username + "')";
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                conn.close();
                rs.close();
                ps.close();
                stmt.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
            new Login().show();
            this.hide();
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        String sql3 = "INSERT INTO logs (action,user) VALUES ('Lagout','" + username + "')";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql3);
            conn.close();
            stmt.close();
            this.setVisible(false);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        String search = search_tf.getText();
        String search_by = (String) search_cb.getSelectedItem();
        DefaultTableModel model = (DefaultTableModel) item_tbl.getModel();
        model.setRowCount(0);
        String sql = " ";
        String searchBy = "";
        if ("Select Any".equals(search_by)) {
            sql = "SELECT * FROM inventory";
        }
        if ("Item Code".equals(search_by)) {
            searchBy = "item_code";
            sql = "SELECT * FROM inventory WHERE " + searchBy + " LIKE '" + search + "%'";
        }
        if ("Item Name".equals(search_by)) {
            searchBy = "item_name";
            sql = "SELECT * FROM inventory WHERE " + searchBy + " LIKE '" + search + "%'";
        }
        if ("Stocks".equals(search_by)) {
            searchBy = "stocks";
            sql = "SELECT * FROM inventory WHERE " + searchBy + " LIKE '" + search + "%'";
        }
        if ("Price".equals(search_by)) {
            searchBy = "price";
            sql = "SELECT * FROM inventory WHERE " + searchBy + " LIKE '" + search + "%'";
        }

        String[] item_info = new String[4];
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                item_info[0] = rs.getString("item_code");
                item_info[1] = rs.getString("item_name");
                item_info[2] = rs.getString("stocks");
                item_info[3] = rs.getString("price");
                Object[] item = {item_info[0], item_info[1], item_info[2], item_info[3]};
                model.addRow(item);
            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void search_cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_cbActionPerformed

    }//GEN-LAST:event_search_cbActionPerformed

    private void sort_cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sort_cbActionPerformed
        search_cb.setSelectedItem("Select Any");
        DefaultTableModel model = (DefaultTableModel) item_tbl.getModel();
        model.setRowCount(0);
        String sortby = " ";
        String sql = "";

        if (sort_cb.getSelectedItem() == "Select Any") {
            sql = "SELECT * FROM inventory";
        }
        if (sort_cb.getSelectedItem() == "Item Code") {
            sortby = "item_code";
            sql = "SELECT * FROM inventory ORDER BY " + sortby + "";
        }
        if (sort_cb.getSelectedItem() == "Item Name") {
            sortby = "item_name";
            sql = "SELECT * FROM inventory ORDER BY " + sortby + "";
        }
        if (sort_cb.getSelectedItem() == "Stocks") {
            sortby = "stocks";
            sql = "SELECT * FROM inventory ORDER BY " + sortby + "";
        }
        if (sort_cb.getSelectedItem() == "Price") {
            sortby = "price";
            sql = "SELECT * FROM inventory ORDER BY " + sortby + "";
        }

        String[] item_info = new String[4];
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                item_info[0] = rs.getString("item_code");
                item_info[1] = rs.getString("item_name");
                item_info[2] = rs.getString("stocks");
                item_info[3] = rs.getString("price");
                Object[] item = {item_info[0], item_info[1], item_info[2], item_info[3]};
                model.addRow(item);
            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_sort_cbActionPerformed

    private void UPDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPDATEActionPerformed
        int row = item_tbl.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No Selected Item");
        } else {
            ItemEdit itemEdit = new ItemEdit();
            ItemEdit.itemCode = item_tbl.getValueAt(row, 0).toString();
            ItemEdit.itemName = item_tbl.getValueAt(row, 1).toString();
            ItemEdit.price = item_tbl.getValueAt(row, 3).toString();
            ItemEdit.description = description_tf.getText();
            ItemEdit.category = cat_cb.getSelectedItem().toString();
            itemEdit.main = this;
            itemEdit.USERNAME = username;
            itemEdit.show();
        }
//        if ("".equals(itmCode_tf.getText())) {
//            JOptionPane.showMessageDialog(null, "No Selected Item");
//
//        } else {
//
//            String[] itemInfo = new String[6];
//            itemInfo[0] = itmCode_tf.getText();
//            itemInfo[1] = itemName_tf.getText();
//            itemInfo[2] = (String) cat_cb.getSelectedItem();
//            itemInfo[3] = description_tf.getText();
//            itemInfo[4] = price_tf.getText();
//            itemInfo[5] = stocks_tf.getText();
//            String sql = "UPDATE inventory SET item_code ='" + itemInfo[0] + "' , item_name = '" + itemInfo[1] + "', category = '" + itemInfo[2] + "', description = '" + itemInfo[3] + "',"
//                    + " price = '" + itemInfo[4] + "', stocks = '" + itemInfo[5] + "' WHERE item_code = " + itemInfo[0] + " ";
//            String sqlLog = "INSERT INTO inventory_log (user,action,item_name) VALUES('" + username + "','Update Item','" + itemInfo[1] + "')";
//            int opt = JOptionPane.showConfirmDialog(null, "Save changes?", "Confirmation", JOptionPane.YES_NO_OPTION);
//            if (opt == JOptionPane.YES_OPTION) {
//                try {
//                    int price = Integer.parseInt(price_tf.getText());
//                    stmt = conn.createStatement();
//                    stmt.executeUpdate(sql);
//                    stmt.executeUpdate(sqlLog);
//                    item_tblRefresh();
//                    JOptionPane.showMessageDialog(null, "Changes has been applied");
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, "Invalid Price, Please Input a number");
//                }
//            }
//        }
    }//GEN-LAST:event_UPDATEActionPerformed

    private void del_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_btnActionPerformed
        String code = itmCode_tf.getText();
        String sql = "DELETE FROM inventory WHERE item_code = " + code + "";
        String sqlLog = "INSERT INTO inventory_log (user,action,item_name) VALUES('" + username + "','Delete Item','" + itemName_tf.getText() + "')";
        if ("".equals(itmCode_tf.getText())) {
            JOptionPane.showMessageDialog(null, "No item selected");

        } else {

            try {

                int opt = JOptionPane.showConfirmDialog(null, "delete this item?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (opt == JOptionPane.YES_OPTION) {
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.executeUpdate(sqlLog);
                    stmt.close();
                    item_tblRefresh();
                    JOptionPane.showMessageDialog(null, "Item Deleted");
                }
            } catch (Exception ex) {

            }
        }
    }//GEN-LAST:event_del_btnActionPerformed

    private void UPDATE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPDATE2ActionPerformed
        int row = item_tbl.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No Selected Item");
        } else {
            StockIn in = new StockIn();
            in.itemCode = item_tbl.getValueAt(row, 0).toString();
            in.itemName = item_tbl.getValueAt(row, 1).toString();
            in.stock = item_tbl.getValueAt(row, 2).toString();
            in.main = this;
            in.USERNAME = username;
            in.show();
        }
    }//GEN-LAST:event_UPDATE2ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        String[] itemInfo = new String[6];
        itemInfo[0] = itmCode_tf1.getText();
        itemInfo[1] = itemName_tf1.getText();
        itemInfo[2] = (String) cat_cb1.getSelectedItem();
        itemInfo[3] = description_tf1.getText();
        itemInfo[4] = price_tf1.getText();
        itemInfo[5] = stocks_tf1.getText();
        if ("".equals(itemInfo[0]) || "".equals(itemInfo[1]) || "Select Any".equals(itemInfo[2]) || "".equals(itemInfo[3]) || "".equals(itemInfo[4]) || "".equals(itemInfo[5])) {
            JOptionPane.showMessageDialog(null, "You did not fill in all the form");
        } else {
            int opt = JOptionPane.showConfirmDialog(null, "Add this item?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (opt != JOptionPane.NO_OPTION) {
                String sql = "INSERT INTO inventory(item_code,item_name,category,description,price,stocks) "
                        + "VALUES('" + itemInfo[0].toLowerCase() + "','" + itemInfo[1].toLowerCase() + "','" + itemInfo[2] + "','" + itemInfo[3].toLowerCase() + "','" + itemInfo[4].toLowerCase() + "','" + itemInfo[5].toLowerCase() + "')";
                String sql2 = "INSERT INTO inventory_log (user,action,item_name) VALUES('" + username + "','Added new Item','" + itemInfo[1] + "')";
                try {
                    int price = Integer.parseInt(itemInfo[4]);
                    int stock = Integer.parseInt(itemInfo[5]);
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.executeUpdate(sql2);
                    JOptionPane.showMessageDialog(null, "New item has been added", "Succesful", JOptionPane.INFORMATION_MESSAGE);
                    itmCode_tf1.setText("");
                    itemName_tf1.setText("");
                    description_tf1.setText("");
                    price_tf1.setText("");
                    stocks_tf1.setText("");
                    cat_cb1.setSelectedItem("Select Any");
                    Code();
                    itmCode_tf1.setText(code);
                    itemName_tf1.requestFocus();
                    stmt.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Invalid Price/Stock Please Input a number ");
                }
            }
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void clear_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_btnActionPerformed
        itemName_tf1.setText("");
        description_tf1.setText("");
        price_tf1.setText("");
        stocks_tf1.setText("");
        cat_cb1.setSelectedItem("Select Any");
    }//GEN-LAST:event_clear_btnActionPerformed

    private void user_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_tfActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        JFileChooser img = new JFileChooser();
        int profpic = img.showOpenDialog(null);
        if (profpic == JFileChooser.APPROVE_OPTION) {
            lb.setText(img.getSelectedFile().getAbsolutePath());

        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        ImageIcon icc = new ImageIcon(lb.getText());
        profPic.setIcon(icc);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        f_rbtn.setActionCommand("Female");
        m_rbtn.setActionCommand("Male");
        if ("".equals(user_tf.getText()) || "".equals(pass_tf.getText()) || "".equals(accEmail_tf.getText()) || "".equals(accPhone_tf.getText()) || "".equals(accFname_tf.getText()) || "".equals(accLname_tf.getText()) || "".equals(dobYear_tf.getText())) {
            JOptionPane.showMessageDialog(null, "You did not fill out all the required fields");
        } else {
            String gender = buttonGroup1.getSelection().getActionCommand();
            String dobMonth = (String) dob_month.getSelectedItem();
            String dobDay = (String) dob_day.getSelectedItem();
            String accType = (String) accType_cb.getSelectedItem();
            if ("Month".equals(dobMonth)) {
                JOptionPane.showMessageDialog(null, "Select a month");

            }
            if ("Day".equals(dobDay)) {
                JOptionPane.showMessageDialog(null, "Select a day");

            }
            if ("YYYY".equals(dobYear_tf.getText())) {
                JOptionPane.showMessageDialog(null, "Input Year");

            } else {
                try {
                    String imgPath = lb.getText();
                    int yr = Integer.parseInt(dobYear_tf.getText());
                    int opt = JOptionPane.showConfirmDialog(null, "Confirm registration?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (opt != JOptionPane.NO_OPTION) {
                        Code();
                        String sql = "INSERT INTO account (id_picture,username,password,email,phone_number,fname,lname,gender,dob,accountType,account_number)"
                                + "VALUES('" + imgPath.replace("\\", "\\\\") + "', '" + user_tf.getText() + "', '" + pass_tf.getText() + "', '" + accEmail_tf.getText() + "', '" + accPhone_tf.getText() + "', '" + accFname_tf.getText() + "', '" + accLname_tf.getText() + "', '" + gender + "', '" + dobMonth + "/" + dobDay + "/" + yr + "','" + accType + "','" + code + "' )";
                        String sqlLog = "INSERT INTO account_log (user,action,account)VALUES('" + username + "','Register new Account','" + user_tf.getText() + "')";
                        stmt = conn.createStatement();
                        stmt.execute(sql);
                        stmt.execute(sqlLog);
                        AccountFormRefresh();
                        stmt.close();
                        JOptionPane.showMessageDialog(null, "Account Created");

                    }

                } catch (Exception ex) {
                    System.out.println(ex);

                }

            }

        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton33ActionPerformed

    private void accDelete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accDelete_btnActionPerformed
        int row = account_tbl.getSelectedRow();
        if (row != -1) {
            int opt = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account?", "Delete ", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                String accNumber = account_tbl.getModel().getValueAt(row, 0).toString();
                String sql = "DELETE FROM account WHERE account_number = '" + accNumber + "'";
                String sqlLog = "INSERT INTO account_log (user,action,account)VALUES('" + username + "','Delete account','" + accName_tf.getText() + "')";
                try {
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.executeUpdate(sqlLog);
                    stmt.close();
                    JOptionPane.showMessageDialog(null, "Account Deleted");
                    AccountTableRefresh();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }
        }
    }//GEN-LAST:event_accDelete_btnActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        int row = account_tbl.getSelectedRow();
        if (row != -1) {
            String accNum = (String) account_tbl.getValueAt(row, 0);
            JFileChooser img = new JFileChooser();
            int profpic = img.showOpenDialog(null);
            if (profpic == JFileChooser.APPROVE_OPTION) {
                ImageIcon newImg = new ImageIcon(img.getSelectedFile().getAbsolutePath());
                profPic2.setIcon(newImg);
                int opt = JOptionPane.showConfirmDialog(null, "Save new Profile ID?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (opt == JOptionPane.YES_OPTION) {
                    String sql = "UPDATE account SET id_picture = '" + newImg.toString().replace("\\", "\\\\") + "' WHERE account_number = " + accNum + "";
                    try {
                        stmt = conn.createStatement();
                        stmt.executeUpdate(sql);
                        stmt.close();
                        JOptionPane.showMessageDialog(null, "New ID saved");
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } else {
                    AccountTableRefresh();
                    JOptionPane.showMessageDialog(null, "No changes");
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

    }//GEN-LAST:event_jButton34ActionPerformed

    private void edit_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_btn1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) account_tbl.getModel();
        int row = account_tbl.getSelectedRow();
        editBtn += 1;
        if (editBtn == 1) {
            if (row != -1) {
                accDelete_btn.setEnabled(true);
                accountType_cb.setEnabled(true);
                edit_btn1.setText("Save");

            } else {
                editBtn = 0;
                JOptionPane.showMessageDialog(null, "No Selected Account");
            }

        }
        if (editBtn == 2) {
            String accType = (String) accountType_cb.getSelectedItem();
            String accNumber = (String) account_tbl.getValueAt(row, 0);
            String sql = "UPDATE account SET accountType = '" + accType + "' WHERE account_number =" + accNumber + " ";
            int opt = JOptionPane.showConfirmDialog(null, "Change Account Type?", "Confirmation", JOptionPane.YES_NO_OPTION);
            try {
                if (opt != JOptionPane.NO_OPTION) {
                    String account = (String) accountType_cb.getSelectedItem();
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                    AccountTableRefresh();
                    JOptionPane.showMessageDialog(null, "Changes applied");
                } else {
                    editBtn = 1;
                }
            } catch (Exception ex) {
                System.out.println(ex);

            }
        }
    }//GEN-LAST:event_edit_btn1ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        int opt = JOptionPane.showConfirmDialog(null, "Are you sure you want to Clear the logs??", "Confirmation", JOptionPane.YES_NO_OPTION);
        DefaultTableModel model = (DefaultTableModel) systemLog_tbl.getModel();
        String sql = "DELETE FROM logs";
        String sql3 = "INSERT INTO logs (action,user) VALUES ( 'Clear the log','" + username + "')";
        if (opt == JOptionPane.YES_OPTION) {
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                stmt.executeUpdate(sql3);
                stmt.close();
                JOptionPane.showMessageDialog(null, "Clear logs complete");
                Logs();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_jButton35ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        conn = DBConnect.DBConnect();
        item_tblRefresh();
        String sql = "INSERT INTO logs (action,user) VALUES ('Login','" + username + "')";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception ex) {
        }

    }//GEN-LAST:event_formWindowOpened

    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnActionPerformed
        int row = costumer_tbl.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No selected Costumer");
        } else {
            String cosId = costumer_tbl.getModel().getValueAt(row, 8).toString();
            String lname = costumer_tbl.getModel().getValueAt(row, 0).toString();
            String sql = "DELETE FROM costumer WHERE costumer_id = " + cosId + "";
            String sqlLog = "INSERT INTO costumer_log(user,action) VALUES('" + username + "','Delete Customer (" + lname + ")')";
            try {

                if (!"".equals(cosId)) {
                    int opt = JOptionPane.showConfirmDialog(null, "Delete Customer?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (opt != JOptionPane.NO_OPTION) {
                        stmt = conn.createStatement();
                        stmt.executeUpdate(sql);
                        stmt.executeUpdate(sqlLog);
                        stmt.close();
                        JOptionPane.showMessageDialog(null, "Customer Deleted");
                        costumerTableRefresh();
                    }
                } else {

                    JOptionPane.showMessageDialog(null, "No Customer Selected");
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_delete_btnActionPerformed

    private void accType_cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accType_cbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accType_cbActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        String sql3 = "INSERT INTO logs (action,user) VALUES ('Lagout','" + username + "')";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql3);
            stmt.close();
            this.setVisible(false);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionPerformed
        File f = new File("help/try.html");
        try {
            Desktop.getDesktop().open(f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_helpActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        SalesReport report = new SalesReport();
        report.date = date_lb.getText();
        report.show();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();
        main_panel.add(addItem_panel);
        main_panel.repaint();
        main_panel.revalidate();
        Code();
        itmCode_tf1.setText(code);
        itmCode_tf1.setEditable(false);
        itemName_tf1.requestFocus();
        itemName_tf1.setText("");
        description_tf1.setText("");
        price_tf1.setText("");
        stocks_tf1.setText("");
        cat_cb1.setSelectedItem("Select Any");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();
        main_panel.add(Inventory_panel);
        main_panel.repaint();
        main_panel.revalidate();
        item_tblRefresh();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String gender = "";
        String name = customer_name.getText();
        String address = customer_address.getText();
        String city = customer_city.getText();
        String province = customer_province.getText();
        String pincode1 = pincode.getText();
        String contact = contact_number.getText();
        String email = email_add.getText();
        if (male.isSelected()) {
            gender = "Male";
        } else {
            gender = "Female";
        }

        if (!"".equals(gender) && !"".equals(name) && !"".equals(address) && !"".equals(city) && !"".equals(province) && !"".equals(pincode1) && !"".equals(contact) && !"".equals(email)) {
            int opt = JOptionPane.showConfirmDialog(null, "Saved new Customer?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                String sql = "INSERT INTO costumer (customerName,gender,address,city,province,pincode,phone_number,costumer_email) VALUES('" + name + "',"
                        + "'" + gender + "','" + address + "','" + city + "','" + province + "','" + pincode1 + "','" + contact + "','" + email + "')";
                String sqlL = "INSERT INTO costumer_log (user,action) VALUES ('"+username+"','Registered new Customer ("+name+")')";
                try {
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.executeUpdate(sqlL);
                    JOptionPane.showMessageDialog(null, "Customer Saved");
                    stmt.close();
                    CostumerFormRefresh();
                } catch (SQLException | HeadlessException ex) {
                    System.out.println(ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "You did not fill out all the required fields");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Accounts_panel;
    private javax.swing.JPanel EmployeeReg_panel;
    private javax.swing.JPanel Inventory_panel;
    private javax.swing.JButton UPDATE;
    private javax.swing.JButton UPDATE2;
    private javax.swing.JButton accDelete_btn;
    private javax.swing.JTextField accEmail_tf;
    private javax.swing.JTextField accFname_tf;
    private javax.swing.JTextField accLname_tf;
    private javax.swing.JTextField accName_tf;
    private javax.swing.JTextField accPhone_tf;
    private javax.swing.JComboBox<String> accType_cb;
    private javax.swing.JTable accountLog_tbl;
    private javax.swing.JComboBox<String> accountType_cb;
    private javax.swing.JTable account_tbl;
    private javax.swing.JPanel addItem_panel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cat_cb;
    private javax.swing.JComboBox<String> cat_cb1;
    private javax.swing.JButton clear_btn;
    private javax.swing.JTextField contact_number;
    private javax.swing.JTable costumerLog_tbl;
    private javax.swing.JTextArea costumer_info_txtarea;
    private javax.swing.JPanel costumer_panel;
    private javax.swing.JTable costumer_tbl;
    private javax.swing.JPanel customerR_panel;
    private javax.swing.JTextField customer_address;
    private javax.swing.JTextField customer_city;
    private javax.swing.JTextField customer_name;
    private javax.swing.JTextField customer_province;
    private javax.swing.JLabel date_lb;
    private javax.swing.JButton del_btn;
    private javax.swing.JButton delete_btn;
    private javax.swing.JTextField description_tf;
    private javax.swing.JTextField description_tf1;
    private javax.swing.JTextField dobYear_tf;
    private javax.swing.JComboBox<String> dob_day;
    private javax.swing.JComboBox<String> dob_month;
    private javax.swing.JButton edit_btn1;
    private javax.swing.JTextField email_add;
    private javax.swing.JRadioButton f_rbtn;
    private javax.swing.JRadioButton female;
    private javax.swing.JButton help;
    private javax.swing.JTable inventoryLog_tbl;
    private javax.swing.JTextField itemName_tf;
    private javax.swing.JTextField itemName_tf1;
    private javax.swing.JTable item_tbl;
    private javax.swing.JTextField itmCode_tf;
    private javax.swing.JTextField itmCode_tf1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JLabel lb5;
    private javax.swing.JButton logs;
    private javax.swing.JPanel logs_panel;
    private javax.swing.JRadioButton m_rbtn;
    private javax.swing.JPanel main_panel;
    private javax.swing.JRadioButton male;
    private javax.swing.JButton newCustomer;
    private javax.swing.JButton newEmployee;
    private javax.swing.JButton newItem;
    private javax.swing.JTextField pass_tf;
    private javax.swing.JTextField pincode;
    private javax.swing.JTextField price_tf;
    private javax.swing.JTextField price_tf1;
    private javax.swing.JLabel profPic;
    private javax.swing.JLabel profPic2;
    private javax.swing.JComboBox<String> search_cb;
    private javax.swing.JTextField search_tf;
    private javax.swing.JComboBox<String> sort_cb;
    private javax.swing.JTextField stocks_tf;
    private javax.swing.JTextField stocks_tf1;
    private javax.swing.JTable systemLog_tbl;
    private javax.swing.JTextField user_tf;
    // End of variables declaration//GEN-END:variables
}
