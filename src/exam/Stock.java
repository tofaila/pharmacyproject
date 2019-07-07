/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author B10
 */
public class Stock extends javax.swing.JFrame {

    DefaultTableModel model;
    Connection con;
    Statement st;
    ResultSet rs;

    /**
     * Creates new form Stock
     */
    public Stock() {
        initComponents();
        java.util.Date d= new java.util.Date();
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yy");
        lbl_Date.setText(sdf.format(d));
        showTime();
    }
   void showTime(){
        new Timer(0, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                java.util.Date d= new java.util.Date();
                SimpleDateFormat sdf= new SimpleDateFormat("hh:mm:ss a");
                lbl_Time.setText(sdf.format(d));
                
                
            }
            
        }).start();
    }
   public void loadCombo(){
         Connection con = null;
        Statement stm = null;
        try {
            con = DBConnection.getDBconnection();
            stm = con.createStatement();

            List plist = new ArrayList();
            ResultSet rs1 = stm.executeQuery("select medi_name from stock order by medi_name");
            while (rs1.next()) {
                plist.add(rs1.getString("medi_name"));
            }
            cmbMediname.setModel(new javax.swing.DefaultComboBoxModel(plist.toArray()));
            cmbMediname.insertItemAt("Select One", 0);
            cmbMediname.setSelectedIndex(0);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
   }
//     public void addDataToTable(){
//         String name=btnSearch.getText();
//        model=(DefaultTableModel)tblMed.getModel();
//        model.setRowCount(0);
//         try {
//              con=DBConnection.getDBconnection();
//           st= con.createStatement();
//           String query="select * from medicine where MediName like '%"+name+"'";
//           rs=st.executeQuery(query);
//           int i=1;
//           while(rs.next()){
//               String nameData=rs.getString("name");
//               String idData=rs.getString("ID");
//               model.addRow(new Object[]{i, nameData, idData});
//               i++;
//           }
//           
//             
//         } catch (Exception e) {
//             JOptionPane.showMessageDialog(rootPane, "a"+e.getMessage());
//         }
//           
//       }
//     public ArrayList<User> userList() {
//        ArrayList<User> usersList = new ArrayList<>();
//        try {
//            Connection con = DBConnection.getDBconnection();
//            Statement st = con.createStatement();
//            String st1 = "select * from stock";
//            ResultSet rs = st.executeQuery(st1);
//            User user;
//            while (rs.next()) {
//                user = new User(rs.getInt("id"),rs.getInt("medi_id"), rs.getString("medi_name"), rs.getString("comp_name"), rs.getString("qty"), rs.getString("price"), rs.getString("expairy_date"), rs.getString("manu_date"), rs.getString("buying_price"), rs.getString("selling_price"));
//                usersList.add(user);
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        return usersList;
//    }
//     public void show_user() {
//        ArrayList<User> list = userList();
//        DefaultTableModel model = (DefaultTableModel) tblMed.getModel();
//        Object[] row = new Object[10];
//        for (int i = 0; i < list.size(); i++) {
//            row[0] = list.get(i).getid();
//            row[1]=list.get(i).getmedi_id();
//            row[2] = list.get(i).getmedi_name();
//            row[3] = list.get(i).getcomp_name();
//            row[4] = list.get(i).getqty();
//            row[5] = list.get(i).getprice();
//            row[6] = list.get(i).getexpairy_date();
//            row[7] =list.get(i).getmanu_date();
//            row[8] =list.get(i).getbuying_price();
//            row[9] =list.get(i).getselling_price();
//            model.addRow(row);
//        }
//     }
    public void displayInTable() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getDBconnection();

            st = con.createStatement();
            //execute the query....
            rs = st.executeQuery("select * from stock");
            DefaultTableModel tm= (DefaultTableModel)tblMed.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getInt("id"), rs.getString("medi_id"), rs.getString("medi_name"), rs.getString("comp_name"), rs.getString("qty"),rs.getString("price"), rs.getString("expairy_date"), rs.getString("manu_date"),rs.getString("buying_price"), rs.getString("selling_price")};
               tm.addRow(o);
//            while (rs.next()) {
//                model.addRow(new Object[]{rs.getString("id"), rs.getString("medi_id"), rs.getString("medi_name"), rs.getString("comp_name"), rs.getString("qty"), rs.getString("price"), rs.getString("expairy_date"), rs.getString("manu_date"), rs.getString("buying_price"), rs.getString("selling_price")});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void sh() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
           
            Connection con = DBConnection.getDBconnection();
            String sql = "select id, medi_id, medi_name, comp_name, qty,Price, expairy_date, manu_date, buying_price, selling_price from stock where medi_name='" + btnSearch.getText() + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtID.setText(rs.getString(1));
                txtMedID.setText(rs.getString(2));
                cmbMediname.setSelectedItem(rs.getString(3));
                txtCompName.setText(rs.getString(4));
                txtQty.setText(rs.getString(5));
                txtPrice.setText(rs.getString(6));
                txtExDate.setText(rs.getString(7));
                txtManuDate.setText(rs.getString(8));
                txtBP.setText(rs.getString(9));
                txtSP.setText(rs.getString(10));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    

    public void clear() {
        txtID.setText("");
        txtMedID.setText("");
       cmbMediname.setSelectedItem("");
        txtCompName.setText("");
        txtQty.setText("");
        txtPrice.setText("");
        txtExDate.setText("");
        txtManuDate.setText("");
        txtBP.setText("");
        txtSP.setText("");
    }

    public void deleteRecord() {
        String id;
        id = txtID.getText();
        Connection con = null;
        Statement st = null;
        try {
            con = DBConnection.getDBconnection();
            st = con.createStatement();
            int count = 0;
            count = st.executeUpdate("delete from stock where id='" + id + "'");
            if (count > 0) {
                JOptionPane.showMessageDialog(this, "Deleted successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Deleted failed", "error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                st.close();
            } catch (Exception e) {
                System.out.println(e);
            }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtMedID = new javax.swing.JTextField();
        txtCompName = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtExDate = new javax.swing.JTextField();
        txtManuDate = new javax.swing.JTextField();
        txtBP = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtSP = new javax.swing.JTextField();
        cmbMediname = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        lbl_Time = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMed = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnViewAll = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 153));

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setText("ID");

        jLabel2.setText("MediID");

        jLabel3.setText("MediName");

        jLabel4.setText("CompName");

        jLabel5.setText("Qty");

        jLabel6.setText("Price");

        jLabel7.setText("ExpiryDate");

        jLabel8.setText("ManuDate");

        jLabel9.setText("BuyingPrice");

        jLabel10.setText("SellingPrice");

        cmbMediname.setEditable(true);
        cmbMediname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one" }));
        cmbMediname.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMedinameItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtManuDate)
                    .addComponent(txtExDate)
                    .addComponent(txtPrice)
                    .addComponent(txtQty)
                    .addComponent(txtCompName)
                    .addComponent(txtMedID)
                    .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(txtBP)
                    .addComponent(txtSP)
                    .addComponent(cmbMediname, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMedID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbMediname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCompName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtExDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtManuDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtBP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 255, 153));

        jLabel11.setBackground(new java.awt.Color(51, 153, 255));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 51, 0));
        jLabel11.setText("Stock information");

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        lbl_Time.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_Time.setForeground(new java.awt.Color(153, 51, 0));

        lbl_Date.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_Date.setForeground(new java.awt.Color(102, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearch)
                            .addComponent(txtSearch)
                            .addComponent(jLabel11))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblMed.setBackground(new java.awt.Color(0, 204, 255));
        tblMed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "medi_id", "medi_name", "comp_name", "qty", "price", "expairy_date", "manu_date", "buying_price", "selling_price"
            }
        ));
        tblMed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMedMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(51, 51, 255));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(204, 51, 0));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnViewAll.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnViewAll.setForeground(new java.awt.Color(51, 102, 0));
        btnViewAll.setText("ViewAll");
        btnViewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAllActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnClear.setForeground(new java.awt.Color(0, 102, 102));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnSave.setForeground(new java.awt.Color(0, 153, 0));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(153, 153, 255));
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(18, 18, 18)
                .addComponent(btnViewAll)
                .addGap(40, 40, 40)
                .addComponent(btnClear)
                .addGap(38, 38, 38)
                .addComponent(btnExit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(32, 32, 32))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnViewAll)
                    .addComponent(jButton1)
                    .addComponent(btnClear)
                    .addComponent(btnExit))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteRecord();
//       String id=txtID.getText();
//               
//      Connection con=null;
//      Statement st=null;
//        try {
//            con=DBConnection.getDBconnection();
//            st=con.createStatement();
//            int count=0;
//            count=st.executeUpdate("delete from Medicine where id='"+id+"'");
//            if(count>0){
//                JOptionPane.showMessageDialog(rootPane, "Deleted successfully");
//            }else{
//                JOptionPane.showMessageDialog(rootPane, "Deleted failed", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        DefaultTableModel model = (DefaultTableModel) tblMed.getModel();
        try {
            int selectedRowIndex = tblMed.getSelectedRow();
            model.removeRow(selectedRowIndex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed
    private JFrame frame;
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        frame = new JFrame();
        if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Pharmacy management system", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        }


    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
    sh();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String mediId, mediName, compName, qty, price, expDate, maDate, buyingPrice, sellingPrice;
        // Id=txtID.getText();
        mediId = txtMedID.getText();
        mediName = cmbMediname.getSelectedItem().toString();
                
        compName = txtCompName.getText();
        qty = txtQty.getText();
        price = txtPrice.getText();
        expDate = txtExDate.getText();
        maDate = txtManuDate.getText();
        buyingPrice = txtBP.getText();
        sellingPrice = txtSP.getText();
        Connection con = null;
        Statement st = null;
        try {
            con = DBConnection.getDBconnection();
            st = con.createStatement();
            int count = 0;
            count = st.executeUpdate("insert into stock(medi_id, medi_name, comp_name,qty, price, expairy_date, manu_date, buying_price, selling_price) values('" + mediId + "','" + mediName + "','" + compName + "','" + qty + "','" + price + "','" + expDate + "','" + maDate + "','" + buyingPrice + "','" + sellingPrice + "')");
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Saved");
                clear();

            } else {
                JOptionPane.showMessageDialog(null, "insertion failed", "error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//  int c=cmbMediname.getSelectedIndex();
//   Connection con=null;
//   Statement st=null;
//   ResultSet rs=null;
//   int prevqty=0, newqty=0, finalqty=0;
//   if(c<0){
//       try {
//           con=DBConnection.getDBconnection();
//           st=con.createStatement();
//           st.executeUpdate("insert into stock (medi_name, comp_name, qty, price, expairy_date, manu_date, buying_price, selling_price) values('"+cmbMediname.getSelectedItem().toString()+"',"
//                   + "'"+txtCompName.getText()+"','"+txtQty.getText()+"','"+txtPrice.getText()+"','"+txtExDate.getText()+"','"+txtManuDate.getText()+"','"+txtBP.getText()+"','"+txtSP.getText()+"')");
//           JOptionPane.showMessageDialog(this, "Saved");
//       } catch (Exception e) {
//           e.printStackTrace();
//       }
//   }else{
//       try{
//       con=DBConnection.getDBconnection();
//       st=con.createStatement();
//       rs=st.executeQuery("select qty from stock where medi_name='"+cmbMediname.getSelectedItem().toString()+"'");
//       while(rs.next()){
//           prevqty=rs.getInt("qty");
//           
//       }
//       newqty=Integer.parseInt(txtQty.getText());
//       finalqty=prevqty+newqty;
//       st.executeUpdate("update stock set qty='"+finalqty+"' where medi_name='"+cmbMediname.getSelectedItem().toString()+"'");
//       JOptionPane.showMessageDialog(this, "Updated");
//       }catch(Exception e){
//           e.printStackTrace();
//       }
//   }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String id, mediId, mediName, comName, qty, price, expDate, manuDate, buyingDate, sellingDate;
        id = txtID.getText();
        mediId = txtMedID.getText();
        mediName = cmbMediname.getSelectedItem().toString();
        comName = txtCompName.getText();
        qty = txtQty.getText();
        price = txtPrice.getText();
        expDate = txtExDate.getText();
        manuDate = txtManuDate.getText();
        buyingDate = txtBP.getText();
        sellingDate = txtSP.getText();

        Connection con = null;
        Statement st = null;
        try {
            con = DBConnection.getDBconnection();
            st = con.createStatement();
            int count = 0;
            count = st.executeUpdate("update stock set medi_id='" + mediId + "', medi_name='" + mediName + "',comp_name='" + comName + "', qty='" + qty + "', price='" + price + "', expairy_date='" + expDate + "',manu_date='" + manuDate + "', buying_price='" + buyingDate + "', selling_price='" + sellingDate + "' where id='" + id + "'");
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "updated successfully");

            } else {
                JOptionPane.showMessageDialog(null, "update failed", "error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                st.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
// Connection con=null;
// Statement st=null;
// ResultSet rs=null;
// int prevqty=0, newqty=0, finalqty=0;
// if(Integer.parseInt(txtQty.getText())>0){
//     try {
//         con=DBConnection.getDBconnection();
//         st=con.createStatement();
//         rs=st.executeQuery("select qty from stock where medi_name='"+cmbMediname.getSelectedItem().toString()+"'");
//         while(rs.next()){
//             prevqty=rs.getInt("qty");
//             
//         }
//         newqty=Integer.parseInt("qty");
//         finalqty=prevqty-newqty;
//         st.executeUpdate("update stock set qty='"+finalqty+"' where medi_name='"+cmbMediname.getSelectedItem().toString()+"'");
//         JOptionPane.showMessageDialog(this, "Updated");
//     } catch (Exception e) {
//     }
// }else{
//     JOptionPane.showMessageDialog(this, "selling price must be greater than Zero");
// }
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblMedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedMouseClicked
        int selectedrow = tblMed.getSelectedRow();
        try {
            txtID.setText(tblMed.getValueAt(selectedrow, 0).toString());
            txtMedID.setText(tblMed.getValueAt(selectedrow, 1).toString());
           
            txtCompName.setText(tblMed.getValueAt(selectedrow, 3).toString());
            txtQty.setText(tblMed.getValueAt(selectedrow, 4).toString());
            txtPrice.setText(tblMed.getValueAt(selectedrow, 5).toString());
            txtExDate.setText(tblMed.getValueAt(selectedrow, 6).toString());
            txtManuDate.setText(tblMed.getValueAt(selectedrow, 7).toString());
            txtBP.setText(tblMed.getValueAt(selectedrow, 8).toString());
            txtSP.setText(tblMed.getValueAt(selectedrow, 9).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblMedMouseClicked

    private void btnViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAllActionPerformed
//       show_user();
        displayInTable();
    }//GEN-LAST:event_btnViewAllActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
       
        DefaultTableModel table = (DefaultTableModel) tblMed.getModel();
        String search = txtSearch.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        tblMed.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_txtSearchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       setVisible(false);
       PharmacyItem pi= new PharmacyItem();
       pi.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbMedinameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMedinameItemStateChanged
       if(cmbMediname.getSelectedIndex()>0){
           Connection con=null;
           Statement st=null;
           ResultSet rs=null;
           try {
               con=DBConnection.getDBconnection();
               st=con.createStatement();
               rs=st.executeQuery("select * from stock where medi_name='"+cmbMediname.getSelectedItem().toString()+"' ");
               while(rs.next()){
                   txtMedID.setText(rs.getString("medi_id"));
                   txtCompName.setText(rs.getString("comp_name"));
                   txtQty.setText(rs.getString("comp_name"));
                   txtPrice.setText(rs.getString("price"));
                   txtExDate.setText(rs.getString(" expairy_date"));
                   txtManuDate.setText(rs.getString("manu_date"));
                   txtBP.setText(rs.getString("buying_price"));
                   txtSP.setText(rs.getString("selling_price"));
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    }//GEN-LAST:event_cmbMedinameItemStateChanged

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
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnViewAll;
    private javax.swing.JComboBox<String> cmbMediname;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_Date;
    private javax.swing.JLabel lbl_Time;
    private javax.swing.JTable tblMed;
    private javax.swing.JTextField txtBP;
    private javax.swing.JTextField txtCompName;
    private javax.swing.JTextField txtExDate;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtManuDate;
    private javax.swing.JTextField txtMedID;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSP;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

}

//    private static class User {
//
//       
//
//        private int id, medi_id;
//        private String medi_name, comp_name,qty,price, expairy_date, manu_date,buying_price, selling_price ;
//
//        public User(int id,int medi_id, String medi_name, String comp_name, String qty,String price, String exDate, String maDate, String buyingprice, String sellingprice ) {
//            
//            this.id = id;
//            this.medi_name = medi_name;
//            this.comp_name = comp_name;
//            this.qty = qty;
//            this.price = price;
//            this.expairy_date = expairy_date;
//            this.manu_date=manu_date;
//            this.buying_price=buying_price;
//            this.selling_price=selling_price;
//
//        }
//
//        public int getid() {
//            return id;
//        }
//        public int getmedi_id(){
//            return medi_id;
//        }
//
//        public String getmedi_name() {
//            return medi_name;
//        }
//
//        public String getcomp_name() {
//            return comp_name;
//        }
//
//        public String getqty() {
//            return qty;
//        }
//
//        public String getprice() {
//            return price;
//        }
//
//        public String getexpairy_date() {
//            return expairy_date;
//        }
//        public String getmanu_date(){
//            return manu_date;
//        }
//        public String getbuying_price(){
//            return buying_price;
//        }
//        public String getselling_price(){
//            return selling_price;
//        }

