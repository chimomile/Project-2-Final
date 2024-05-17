package project2;


import java.awt.Image;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import javax.print.PrintException;
import java.awt.print.PrinterJob;
import javax.swing.JCheckBox;
import javax.swing.Timer;
import java.sql.*;
import java.text.DecimalFormatSymbols;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;


public class dashboard extends javax.swing.JFrame {
    
private String itemName;
private double totalWithTax;
private int purchaseID;
private String adminName;
private double total = 0.0;
private int x = 0;
private double tax = 0.0;



    public dashboard() {
        initComponents();
        init();
        
    }
    
    public void init(){
       updateLabels();
       setImage();
       setTime();
    }
    
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    
    private void updateLabels() {
    DatabaseConnection dbConn = new DatabaseConnection();
    List<Menu> menuItems = dbConn.getMenuItems();

    if (menuItems.size() > 0) {
        lblname1.setText(menuItems.get(0).getName());
        lblprice1.setText("Rp " + String.format("%,.0f", menuItems.get(0).getPrice()));
    }
    if (menuItems.size() > 1) {
        lblname2.setText(menuItems.get(1).getName());
        lblprice2.setText("Rp " + String.format("%,.0f", menuItems.get(1).getPrice()));
    }
    if (menuItems.size() > 2) {
        lblname3.setText(menuItems.get(2).getName());
        lblprice3.setText("Rp " + String.format("%,.0f", menuItems.get(2).getPrice()));
    }
    if (menuItems.size() > 3) {
        lblname4.setText(menuItems.get(3).getName());
        lblprice4.setText("Rp " + String.format("%,.0f", menuItems.get(3).getPrice()));
    }
    if (menuItems.size() > 4) {
        lblname5.setText(menuItems.get(4).getName());
        lblprice5.setText("Rp " + String.format("%,.0f", menuItems.get(4).getPrice()));
    }
    if (menuItems.size() > 5) {
        lblname6.setText(menuItems.get(5).getName());
        lblprice6.setText("Rp " + String.format("%,.0f", menuItems.get(5).getPrice()));
    }
    if (menuItems.size() > 6) {
        lblname7.setText(menuItems.get(6).getName());
        lblprice7.setText("Rp " + String.format("%,.0f", menuItems.get(6).getPrice()));
    }
    if (menuItems.size() > 7) {
        lblname8.setText(menuItems.get(7).getName());
        lblprice8.setText("Rp " + String.format("%,.0f", menuItems.get(7).getPrice()));
    }
    if (menuItems.size() > 8) {
        lblname9.setText(menuItems.get(8).getName());
        lblprice9.setText("Rp " + String.format("%,.0f", menuItems.get(8).getPrice()));
    }
}

    private void handleCheckboxAction(JCheckBox checkbox, JSpinner spinner, JLabel nameLabel, JLabel priceLabel) {
    int qty = Integer.parseInt(spinner.getValue().toString());
    int quantity = (int) spinner.getValue();
    if (qty > 0 && checkbox.isSelected()) {
        x++;
        if (x == 1) {
            GoldchickRestaurant();
        }

        double pricePerItem = Double.parseDouble(priceLabel.getText().replace("Rp", "").replace(".", "").trim());
        double totalPrice = qty * pricePerItem;
        total += totalPrice;
        getTax((int) total);

        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedTotalPrice = "Rp " + formatter.format(totalPrice);

        itemName = nameLabel.getText();

        jTextArea1.setText(jTextArea1.getText() + x + ". " + itemName + " (x" + quantity +  ")" + "\t\t\t" + formattedTotalPrice + "\n");
        dudate();
    } else {
        checkbox.setSelected(false);
    }
}
    private void handleCheckBoxSelection(JCheckBox checkBox, String pricePerUnitStr, JSpinner quantitySpinner) {
      if (checkBox.isSelected()) {
        double pricePerUnit = Double.parseDouble(pricePerUnitStr.replace("Rp", "").replace(".", "").trim());
        
        int quantity = (int) quantitySpinner.getValue();
        
        double totalPrice = pricePerUnit * quantity;
        total += totalPrice;
        
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedPrice = "Rp " + formatter.format(totalPrice);
        
        jTextArea1.append(checkBox.getText() + " (x" + quantity +  ")\t\t\t" + formattedPrice + "\n");
    }
}



    public void setImage() {
    DatabaseConnection dbConnection = new DatabaseConnection();
    Connection conn = dbConnection.getConnection();
    
    if (conn != null) {
        try {
            String query = "SELECT img_path FROM menu";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            int labelIndex = 1;
            while (rs.next() && labelIndex <= 9) {
                String imagePath = rs.getString("img_path");
                ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
                Image img = icon.getImage().getScaledInstance(lblmenu1.getWidth(), lblmenu1.getHeight(), Image.SCALE_SMOOTH);
                switch (labelIndex) {
                    case 1 -> lblmenu1.setIcon(new ImageIcon(img));
                    case 2 -> lblmenu2.setIcon(new ImageIcon(img));
                    case 3 -> lblmenu3.setIcon(new ImageIcon(img));
                    case 4 -> lblmenu4.setIcon(new ImageIcon(img));
                    case 5 -> lblmenu5.setIcon(new ImageIcon(img));
                    case 6 -> lblmenu6.setIcon(new ImageIcon(img));
                    case 7 -> lblmenu7.setIcon(new ImageIcon(img));
                    case 8 -> lblmenu8.setIcon(new ImageIcon(img));
                    case 9 -> lblmenu9.setIcon(new ImageIcon(img));
                }
                labelIndex++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


    
    public boolean qtyIsZero(int qty) {
        if (qty==0) {
            JOptionPane.showMessageDialog(null, "Please increase the item quantity");
            return false;
        }
        return true;
    }
    
    public void reset(){
        total=0.0;
        x = 0;
        tax = 0.0;
        btntotal.setEnabled(true);
        jSpinner14.setValue(0);
        jSpinner15.setValue(0);
        jSpinner16.setValue(0);
        jSpinner17.setValue(0);
        jSpinner18.setValue(0);
        jSpinner19.setValue(0);
        jSpinner20.setValue(0);
        jSpinner21.setValue(0);
        jSpinner22.setValue(0);
        jTextFieldtax.setText("0.0");
        jTextFieldtaxSubTotal.setText("0.0");
        jTextFieldTotal.setText("0.0");
        jTextArea1.setText("");
        jCheckBox14.setEnabled(true);
        jCheckBox15.setEnabled(true);
        jCheckBox16.setEnabled(true);
        jCheckBox17.setEnabled(true);
        jCheckBox18.setEnabled(true);
        jCheckBox19.setEnabled(true);
        jCheckBox20.setEnabled(true);
        jCheckBox21.setEnabled(true);
        jCheckBox22.setEnabled(true);
    }
    
    public void dudate() {
        DecimalFormat formatter = new DecimalFormat("#,###");
        
        String formattedTax = formatter.format(tax);
        jTextFieldtax.setText("Rp " + formattedTax);
   
        String formattedTotal = formatter.format(total);
        jTextFieldtaxSubTotal.setText("Rp " + formattedTotal);

        totalWithTax = tax +total;
        String formattedTotalWithTax = formatter.format(totalWithTax);
        jTextFieldTotal.setText("Rp " + formattedTotalWithTax);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel22 = new javax.swing.JPanel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jSpinner17 = new javax.swing.JSpinner();
        jCheckBox17 = new javax.swing.JCheckBox();
        jLabel124 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txttime = new javax.swing.JLabel();
        txtdate = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        lblname1 = new javax.swing.JLabel();
        lblprice1 = new javax.swing.JLabel();
        jSpinner14 = new javax.swing.JSpinner();
        jCheckBox14 = new javax.swing.JCheckBox();
        lblmenu1 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        lblname4 = new javax.swing.JLabel();
        lblprice4 = new javax.swing.JLabel();
        jSpinner15 = new javax.swing.JSpinner();
        jCheckBox15 = new javax.swing.JCheckBox();
        lblmenu4 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        lblname7 = new javax.swing.JLabel();
        lblprice7 = new javax.swing.JLabel();
        jSpinner19 = new javax.swing.JSpinner();
        jCheckBox19 = new javax.swing.JCheckBox();
        lblmenu7 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel103 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        lblname2 = new javax.swing.JLabel();
        lblprice2 = new javax.swing.JLabel();
        jSpinner16 = new javax.swing.JSpinner();
        jCheckBox16 = new javax.swing.JCheckBox();
        lblmenu2 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        lblname3 = new javax.swing.JLabel();
        lblprice3 = new javax.swing.JLabel();
        jSpinner18 = new javax.swing.JSpinner();
        jCheckBox18 = new javax.swing.JCheckBox();
        lblmenu3 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        lblname5 = new javax.swing.JLabel();
        lblprice5 = new javax.swing.JLabel();
        jSpinner20 = new javax.swing.JSpinner();
        jCheckBox20 = new javax.swing.JCheckBox();
        lblmenu5 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        lblname8 = new javax.swing.JLabel();
        lblprice8 = new javax.swing.JLabel();
        jSpinner21 = new javax.swing.JSpinner();
        jCheckBox21 = new javax.swing.JCheckBox();
        lblmenu8 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        lblname6 = new javax.swing.JLabel();
        lblprice6 = new javax.swing.JLabel();
        jSpinner23 = new javax.swing.JSpinner();
        jCheckBox23 = new javax.swing.JCheckBox();
        lblmenu6 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel140 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        lblname9 = new javax.swing.JLabel();
        lblprice9 = new javax.swing.JLabel();
        jSpinner22 = new javax.swing.JSpinner();
        jCheckBox22 = new javax.swing.JCheckBox();
        lblmenu9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btntotal = new javax.swing.JButton();
        btnreceipt = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        goToUpdateBtn = new javax.swing.JButton();
        goToUpdateMenu = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldtax = new javax.swing.JTextField();
        jTextFieldtaxSubTotal = new javax.swing.JTextField();
        jTextFieldTotal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jPanel22.setBackground(new java.awt.Color(240, 234, 214));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 139, 91), 4));

        jLabel118.setText("Name:");

        jLabel119.setText("Price:");

        jLabel120.setText("Quantity:");

        jLabel121.setText("Purchase:");

        jLabel122.setText("Rice + Spicy Fried Chicken");

        jLabel123.setText("Rp20.000");

        jSpinner17.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jLabel124.setForeground(new java.awt.Color(255, 255, 255));
        jLabel124.setText(" ");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel120)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner17, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel121)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox17)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel118)
                    .addComponent(jLabel122))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel124)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel119)
                    .addComponent(jLabel123))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel120)
                    .addComponent(jSpinner17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel121)
                    .addComponent(jCheckBox17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(176, 139, 91));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("GOLDCHICK RESTAURANT");

        txttime.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txttime.setForeground(new java.awt.Color(255, 255, 255));
        txttime.setText(" ");

        txtdate.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtdate.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txttime, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
            .addComponent(txtdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txttime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(118, 180, 162));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Menu Items");

        jPanel19.setBackground(new java.awt.Color(240, 234, 214));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 139, 91), 4));

        jLabel98.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel98.setText("Price:");

        jLabel99.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel99.setText("Quantity:");

        jLabel100.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel100.setText("Purchase:");

        lblname1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblname1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblname1.setText("Rice + Spicy Fried Chicken");

        lblprice1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblprice1.setText("Rp20.000");

        jSpinner14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jSpinner14.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jCheckBox14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblname1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel100)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox14))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel99)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblprice1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner14, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblmenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(lblmenu1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblname1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98)
                    .addComponent(lblprice1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel99)
                    .addComponent(jSpinner14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel100, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(240, 234, 214));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 139, 91), 4));

        jLabel104.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel104.setText("Price:");

        jLabel105.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel105.setText("Quantity:");

        jLabel106.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel106.setText("Purchase:");

        lblname4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblname4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblname4.setText("Rice + Spicy Fried Chicken with Shrimp Paste Sauce");

        lblprice4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblprice4.setText("Rp20.000");

        jSpinner15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jSpinner15.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jCheckBox15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblname4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel106)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox15))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel105)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblprice4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner15, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblmenu4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(lblmenu4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblname4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104)
                    .addComponent(lblprice4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel105)
                    .addComponent(jSpinner15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel106, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel24.setBackground(new java.awt.Color(240, 234, 214));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 139, 91), 4));

        jLabel109.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel109.setText("Price:");

        jLabel110.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel110.setText("Quantity:");

        jLabel132.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel132.setText("Purchase:");

        lblname7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblname7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblname7.setText("Rice ");

        lblprice7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblprice7.setText("Rp6.000");

        jSpinner19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jSpinner19.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jCheckBox19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblname7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel132)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox19))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel110)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblprice7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner19, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblmenu7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(lblmenu7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(lblname7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(lblprice7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110)
                    .addComponent(jSpinner19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel132, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox19, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(240, 234, 214));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 139, 91), 4));

        jLabel103.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel103.setText("Price:");

        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel111.setText("Quantity:");

        jLabel112.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel112.setText("Purchase:");

        lblname2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblname2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblname2.setText("Rice + Spicy Fried Chicken with Green Chili");

        lblprice2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblprice2.setText("Rp25.000");

        jSpinner16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jSpinner16.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jCheckBox16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblname2, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel112)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox16))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel111)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblprice2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner16, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblmenu2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(lblmenu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblname2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103)
                    .addComponent(lblprice2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel111)
                    .addComponent(jSpinner16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox16, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel23.setBackground(new java.awt.Color(240, 234, 214));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 139, 91), 4));

        jLabel115.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel115.setText("Price:");

        jLabel116.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel116.setText("Quantity:");

        jLabel117.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel117.setText("Purchase:");

        lblname3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblname3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblname3.setText("Rice + Spicy Fried Chicken with Matah Sauce");

        lblprice3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblprice3.setText("Rp20.000");

        jSpinner18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jSpinner18.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jCheckBox18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblname3, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel117)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox18))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel116)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblprice3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner18, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblmenu3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblmenu3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblname3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel115)
                    .addComponent(lblprice3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel116)
                    .addComponent(jSpinner18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel117, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel25.setBackground(new java.awt.Color(240, 234, 214));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 139, 91), 4));

        jLabel127.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel127.setText("Price:");

        jLabel128.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel128.setText("Quantity:");

        jLabel129.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel129.setText("Purchase:");

        lblname5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblname5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblname5.setText("Mozzarella Cheese");

        lblprice5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblprice5.setText("Rp5.000");

        jSpinner20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jSpinner20.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jCheckBox20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblname5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel129)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox20))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel128)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblprice5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner20, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(190, Short.MAX_VALUE))
            .addComponent(lblmenu5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(lblmenu5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblname5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel127)
                    .addComponent(lblprice5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel128)
                    .addComponent(jSpinner20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel129, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox20, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel26.setBackground(new java.awt.Color(240, 234, 214));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 139, 91), 4));

        jLabel135.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel135.setText("Price:");

        jLabel136.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel136.setText("Quantity:");

        jLabel137.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel137.setText("Purchase:");

        lblname8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblname8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblname8.setText("Mineral Water");

        lblprice8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblprice8.setText("Rp5.000");

        jSpinner21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jSpinner21.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jCheckBox21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox21ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblname8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel137)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox21))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel136)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblprice8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner21, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblmenu8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(lblmenu8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(lblname8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel135)
                    .addComponent(lblprice8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel136)
                    .addComponent(jSpinner21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel137, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox21, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel28.setBackground(new java.awt.Color(240, 234, 214));
        jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 139, 91), 4));

        jLabel145.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel145.setText("Price:");

        jLabel146.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel146.setText("Quantity:");

        jLabel147.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel147.setText("Purchase:");

        lblname6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblname6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblname6.setText("Fried Cabbage");

        lblprice6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblprice6.setText("Rp7.000");

        jSpinner23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jSpinner23.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jCheckBox23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(lblname6, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(jLabel147)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox23))
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(jLabel146)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblprice6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinner23, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel145, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblmenu6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblmenu6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblname6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel145)
                    .addComponent(lblprice6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel146)
                    .addComponent(jSpinner23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel147, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox23, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel27.setBackground(new java.awt.Color(240, 234, 214));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 139, 91), 4));

        jLabel140.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel140.setText("Price:");

        jLabel141.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel141.setText("Quantity:");

        jLabel142.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel142.setText("Purchase:");

        lblname9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblname9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblname9.setText("Iced Tea");

        lblprice9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblprice9.setText("Rp4.000");

        jSpinner22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jSpinner22.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jCheckBox22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblname9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel141)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblprice9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner22, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel142)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox22)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblmenu9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(lblmenu9, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblname9)
                .addGap(12, 12, 12)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel140)
                    .addComponent(lblprice9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel141)
                    .addComponent(jSpinner22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel142, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox22, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(0, 102, 102));

        btntotal.setBackground(new java.awt.Color(240, 234, 214));
        btntotal.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btntotal.setText("TOTAL");
        btntotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntotalActionPerformed(evt);
            }
        });

        btnreceipt.setBackground(new java.awt.Color(240, 234, 214));
        btnreceipt.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnreceipt.setText("RECEIPT");
        btnreceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreceiptActionPerformed(evt);
            }
        });

        btnreset.setBackground(new java.awt.Color(240, 234, 214));
        btnreset.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnreset.setText("RESET");
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });

        btnexit.setBackground(new java.awt.Color(255, 0, 0));
        btnexit.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnexit.setForeground(new java.awt.Color(255, 255, 255));
        btnexit.setText("EXIT");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        goToUpdateBtn.setBackground(new java.awt.Color(240, 234, 214));
        goToUpdateBtn.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        goToUpdateBtn.setText("ADMIN - EMPLOYEE");
        goToUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToUpdateBtnActionPerformed(evt);
            }
        });

        goToUpdateMenu.setBackground(new java.awt.Color(240, 234, 214));
        goToUpdateMenu.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        goToUpdateMenu.setText("ADMIN - MENUS");
        goToUpdateMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToUpdateMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btntotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnreceipt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnreset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goToUpdateBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goToUpdateMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnexit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btntotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnreceipt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnreset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(goToUpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(goToUpdateMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel25.setText("Total");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel26.setText("Tax");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel27.setText("Sub Total");

        jTextFieldtax.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jTextFieldtax.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldtax.setText("0.0");
        jTextFieldtax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldtaxActionPerformed(evt);
            }
        });

        jTextFieldtaxSubTotal.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jTextFieldtaxSubTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldtaxSubTotal.setText("0.0");
        jTextFieldtaxSubTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldtaxSubTotalActionPerformed(evt);
            }
        });

        jTextFieldTotal.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jTextFieldTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldTotal.setText("0.0");
        jTextFieldTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTotalActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldtaxSubTotal, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldtax, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldtax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jTextFieldtaxSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(35, 35, 35))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 1965, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1239, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnreceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreceiptActionPerformed
        if (total != 0) {
        if (!btntotal.isEnabled()) {
            try {
                // Get item name from jTextArea1
                String itemNameFromTextArea = getItemNameFromTextArea();
                
                // Save transaction details to the database
                TransactionDB.saveTransaction(itemNameFromTextArea, purchaseID, adminName, totalWithTax); 
                
                // Print receipt
                PrinterJob printerJob = PrinterJob.getPrinterJob();
                printerJob.setPrintable(new TextPrintable(jTextArea1));
                if (printerJob.printDialog()) {
                    printerJob.print();
                }
            } catch (PrinterException ex) {
                Logger.getLogger(dashboard.class.getName()).log(Level.SEVERE, null, ex); 
            }
        } else {
            JOptionPane.showMessageDialog(null, "First, You Should Calculate The Total");
        } 
    } else {
        JOptionPane.showMessageDialog(null, "You Haven't purchased any product");
    }
    }//GEN-LAST:event_btnreceiptActionPerformed

    private String getItemNameFromTextArea() {
    StringBuilder itemNamesBuilder = new StringBuilder();
    String text = jTextArea1.getText();
    String[] lines = text.split("\n");
    for (String line : lines) {
        int dotIndex = line.indexOf(". ");
        if (dotIndex != -1) {
            itemName = line.substring(dotIndex + 2).trim();
            // Append the menu name to the string builder
            itemNamesBuilder.append(itemName.split("\\s+\\d+\\.\\d+")[0]).append(", ");
        }
    }
    if (itemNamesBuilder.length() > 0) {
        itemNamesBuilder.setLength(itemNamesBuilder.length() - 2);
    }
    return itemNamesBuilder.toString();
}


    
    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        reset();
    }//GEN-LAST:event_btnresetActionPerformed

    public void GoldchickRestaurant() {
    purchaseID = 2505 + (int) (Math.random() * 80800); // Assign a value to purchaseID
    jTextArea1.setText("***************************Goldchick Restaurant***************************\n"
                      + "Time: " + txttime.getText()
                      + "\t" + "Date: " + txtdate.getText() + "\n"
                      + "Purchase Id: " + purchaseID
                      + "\t" + "Admin Name: " + adminName + "\n"
                      + "*****************************************************************************\n"
                      + "Item Name\t\t\t\t" + "Price\n");
    }                            



    
    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
    System.exit(0);
    }//GEN-LAST:event_btnexitActionPerformed

    private void btntotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntotalActionPerformed
         if (total == 0.0) {
        JOptionPane.showMessageDialog(null, "You Haven't Selected Any Item");
    } else {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);

        String formattedTotal = "Rp " + formatter.format(total);
        String formattedTax = "Rp " + formatter.format(tax);
        totalWithTax = total + tax; // Calculate totalWithTax
        String formattedTotalWithTax = "Rp " + formatter.format(totalWithTax);

        // Append the new text to the existing text in jTextArea1
        jTextArea1.append("\n*****************************************************************************\n"
                + "Tax: \t\t\t\t" + formattedTax + "\n"
                + "Sub Total: \t\t\t\t" + formattedTotal + "\n"
                + "Total: \t\t\t\t" + formattedTotalWithTax + "\n\n"
                + "\"********************************Thank You***********************************\n");

        btntotal.setEnabled(false);
        jCheckBox14.setEnabled(false);
        jCheckBox16.setEnabled(false);
        jCheckBox18.setEnabled(false);
        jCheckBox15.setEnabled(false);
        jCheckBox20.setEnabled(false);
        jCheckBox23.setEnabled(false);
        jCheckBox19.setEnabled(false);
        jCheckBox21.setEnabled(false);
        jCheckBox22.setEnabled(false);
    }
    }//GEN-LAST:event_btntotalActionPerformed

    // menus no 1
    private void jCheckBox14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox14ActionPerformed
         handleCheckboxAction(jCheckBox14, jSpinner14, lblname1, lblprice1);
    }//GEN-LAST:event_jCheckBox14ActionPerformed

    // menus no 2
    private void jCheckBox16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox16ActionPerformed
     handleCheckboxAction(jCheckBox16, jSpinner16, lblname2, lblprice2);
    }//GEN-LAST:event_jCheckBox16ActionPerformed

    // menus no 3
    private void jCheckBox18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox18ActionPerformed
        handleCheckboxAction(jCheckBox18, jSpinner18, lblname3, lblprice3);
    }//GEN-LAST:event_jCheckBox18ActionPerformed

    // menus no 4
    private void jCheckBox15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox15ActionPerformed
        handleCheckboxAction(jCheckBox15, jSpinner15, lblname4, lblprice4);
    }//GEN-LAST:event_jCheckBox15ActionPerformed

    // menus no 5
    private void jCheckBox20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox20ActionPerformed
        handleCheckboxAction(jCheckBox20, jSpinner20, lblname5, lblprice5);
    }//GEN-LAST:event_jCheckBox20ActionPerformed

    // menus no 6
    private void jCheckBox23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox23ActionPerformed
        handleCheckboxAction(jCheckBox23, jSpinner23, lblname6, lblprice6);
    }//GEN-LAST:event_jCheckBox23ActionPerformed

    // menus no 7
    private void jCheckBox19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox19ActionPerformed
        handleCheckboxAction(jCheckBox19, jSpinner19, lblname7, lblprice7);
    }//GEN-LAST:event_jCheckBox19ActionPerformed

    // menus no 8
    private void jCheckBox21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox21ActionPerformed
        handleCheckboxAction(jCheckBox21, jSpinner21, lblname8, lblprice8);
    }//GEN-LAST:event_jCheckBox21ActionPerformed

    // menus no 9
    private void jCheckBox22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox22ActionPerformed
        handleCheckboxAction(jCheckBox22, jSpinner22, lblname9, lblprice9);
    }//GEN-LAST:event_jCheckBox22ActionPerformed

    private void jTextFieldtaxSubTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldtaxSubTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldtaxSubTotalActionPerformed

    private void jTextFieldTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalActionPerformed

    int xx, xy;
    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xx, y-xy);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       Timer timer = new Timer(40, (e) -> {
        float opacity = this.getOpacity();
        opacity += 0.1f;
        if (opacity > 1.0f) {
            opacity = 1.0f;
            ((Timer) e.getSource()).stop();
        }
        this.setOpacity(opacity);
    });
    timer.start();
    }//GEN-LAST:event_formWindowOpened

    private void goToUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToUpdateBtnActionPerformed
        updateBtn updateFrame = new updateBtn();
        updateFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_goToUpdateBtnActionPerformed

    private void goToUpdateMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToUpdateMenuActionPerformed
        updateMenu menuFrame = new updateMenu();
        menuFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_goToUpdateMenuActionPerformed

    private void jTextFieldtaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldtaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldtaxActionPerformed
   
    public double getTax(double t) {
    tax = 0.11 * t; // Tarif pajak flat 11% dari total harga
    return tax;
}
    
    public void setTime(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(dashboard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Date date = new Date();
                    SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MM-yyyy", new Locale("en"));
                    String time = tf.format(date);
                    txttime.setText(time.split(" ") [0]+" " + time.split(" ")[1]);
                    txtdate.setText(df.format(date));
                }
            }
        }).start();
    }
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
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnreceipt;
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btntotal;
    private javax.swing.JButton goToUpdateBtn;
    private javax.swing.JButton goToUpdateMenu;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox20;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner14;
    private javax.swing.JSpinner jSpinner15;
    private javax.swing.JSpinner jSpinner16;
    private javax.swing.JSpinner jSpinner17;
    private javax.swing.JSpinner jSpinner18;
    private javax.swing.JSpinner jSpinner19;
    private javax.swing.JSpinner jSpinner20;
    private javax.swing.JSpinner jSpinner21;
    private javax.swing.JSpinner jSpinner22;
    private javax.swing.JSpinner jSpinner23;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextField jTextFieldtax;
    private javax.swing.JTextField jTextFieldtaxSubTotal;
    private javax.swing.JLabel lblmenu1;
    private javax.swing.JLabel lblmenu2;
    private javax.swing.JLabel lblmenu3;
    private javax.swing.JLabel lblmenu4;
    private javax.swing.JLabel lblmenu5;
    private javax.swing.JLabel lblmenu6;
    private javax.swing.JLabel lblmenu7;
    private javax.swing.JLabel lblmenu8;
    private javax.swing.JLabel lblmenu9;
    private javax.swing.JLabel lblname1;
    private javax.swing.JLabel lblname2;
    private javax.swing.JLabel lblname3;
    private javax.swing.JLabel lblname4;
    private javax.swing.JLabel lblname5;
    private javax.swing.JLabel lblname6;
    private javax.swing.JLabel lblname7;
    private javax.swing.JLabel lblname8;
    private javax.swing.JLabel lblname9;
    private javax.swing.JLabel lblprice1;
    private javax.swing.JLabel lblprice2;
    private javax.swing.JLabel lblprice3;
    private javax.swing.JLabel lblprice4;
    private javax.swing.JLabel lblprice5;
    private javax.swing.JLabel lblprice6;
    private javax.swing.JLabel lblprice7;
    private javax.swing.JLabel lblprice8;
    private javax.swing.JLabel lblprice9;
    private javax.swing.JLabel txtdate;
    private javax.swing.JLabel txttime;
    // End of variables declaration//GEN-END:variables
}
