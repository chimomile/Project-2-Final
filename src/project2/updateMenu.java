/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package project2;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author wmerl
 */
public class updateMenu extends javax.swing.JFrame {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private final DefaultTableModel model;
    private final DatabaseConnection dbCon;
    private static final Logger logger = Logger.getLogger(updateMenu.class.getName());
    /**
     * Creates new form updateMenu
     */
    public updateMenu() {
        initComponents();
        String[] header = {"Table ID", "Img Path", "Menu`s Name", "Price"};
        model = new DefaultTableModel(header, 0);
        menuTB.setModel(model);
        dbCon = new DatabaseConnection();
        showTable();
        addTableSelectionListener();
    }
    
    private void showTable() {
        model.setRowCount(0);
        con = dbCon.getConnection();
        if (con == null) {
            logger.log(Level.SEVERE, "Database connection failed.");
            return;
        }
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM menu");
            while (rs.next()) {
                String[] row = {
                    rs.getString("menu_id"),
                    rs.getString("img_path"),
                    rs.getString("menu_name"),
                    rs.getString("menu_price")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
    }

    
    private void addTableSelectionListener() {
        menuTB.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = menuTB.getSelectedRow();
                    if (selectedRow != -1) {
                        txtImg.setText((String) model.getValueAt(selectedRow, 1));
                        txtMenu.setText((String) model.getValueAt(selectedRow, 2));
                        txtPrice.setText((String) model.getValueAt(selectedRow, 3));
                    }
                }
            }
        });
    }
    
    private boolean isEmpty() {
        if (txtImg.getText().trim().isEmpty() ||
            txtMenu.getText().trim().isEmpty() ||
            txtPrice.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
    
    private boolean isValidPrice(String price) {
    try {
        Double.parseDouble(price);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

    
     private void clearInputFields() {
        txtImg.setText("");
        txtMenu.setText("");
        txtPrice.setText("");
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
        jScrollPane2 = new javax.swing.JScrollPane();
        menuTB = new javax.swing.JTable();
        addmenuimg = new javax.swing.JLabel();
        txtImg = new javax.swing.JTextField();
        addmenuname = new javax.swing.JLabel();
        txtMenu = new javax.swing.JTextField();
        addmenuprice = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        goToLogin = new javax.swing.JButton();
        txtPrice = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Admin Panel - Employee Management");

        menuTB.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(menuTB);

        addmenuimg.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        addmenuimg.setForeground(new java.awt.Color(255, 255, 255));
        addmenuimg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addmenuimg.setText("Image`s Name");

        txtImg.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImgActionPerformed(evt);
            }
        });

        addmenuname.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        addmenuname.setForeground(new java.awt.Color(255, 255, 255));
        addmenuname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addmenuname.setText("Menu`s Name");

        txtMenu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMenuActionPerformed(evt);
            }
        });

        addmenuprice.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        addmenuprice.setForeground(new java.awt.Color(255, 255, 255));
        addmenuprice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addmenuprice.setText("Menu`s Price");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Data Fields");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Menus Table");

        btnUpdate.setBackground(new java.awt.Color(102, 255, 102));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update Data");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 51, 51));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete Data");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        goToLogin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        goToLogin.setText("Back");
        goToLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToLoginActionPerformed(evt);
            }
        });

        txtPrice.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(goToLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 54, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addmenuimg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtImg)
                            .addComponent(addmenuname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMenu)
                            .addComponent(addmenuprice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrice))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(goToLogin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(addmenuimg)
                        .addGap(18, 18, 18)
                        .addComponent(txtImg, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addmenuname)
                        .addGap(18, 18, 18)
                        .addComponent(txtMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addmenuprice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImgActionPerformed

    private void txtMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMenuActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
         int selectedRow = menuTB.getSelectedRow();
        if (selectedRow >= 0) {
            String id = menuTB.getValueAt(selectedRow, 0).toString();
            String img_path = txtImg.getText();
            String menu_name = txtMenu.getText();
            String menu_price = txtPrice.getText();

            if (!isEmpty()) {
                boolean success = dbCon.updateMenu1(id, img_path, menu_name, menu_price);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Menu updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    showTable();
                    clearInputFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Error updating menu", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to update", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
         int selectedRow = menuTB.getSelectedRow();
        if (selectedRow >= 0) {
            String id = menuTB.getValueAt(selectedRow, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this menu?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = dbCon.deleteMenu1(id);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Menu deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    showTable();
                    clearInputFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Error deleting menu", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void goToLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToLoginActionPerformed
        dashboard dwb = new dashboard();
        dwb.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_goToLoginActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

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
            java.util.logging.Logger.getLogger(updateMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(updateMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(updateMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(updateMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new updateMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addmenuimg;
    private javax.swing.JLabel addmenuname;
    private javax.swing.JLabel addmenuprice;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton goToLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable menuTB;
    private javax.swing.JTextField txtImg;
    private javax.swing.JTextField txtMenu;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
