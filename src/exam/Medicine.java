package exam;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
import java.util.Date;

/**
 *
 * @author B10
 */
public class Medicine extends javax.swing.JFrame {

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    DefaultTableModel model;

    /**
     * Creates new form Medicine
     */
    public Medicine() {
        initComponents();
        Date d= new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yy");
        lbl_date.setText(sdf.format(d));
        showTime();
    loadCombo();
    model  = new DefaultTableModel();

    tblMed.setModel (model);

    model.addColumn ("medi_id");
    model.addColumn ("MediName");
    model.addColumn ("CompName");
    model.addColumn ("ExpiryDate");
    model.addColumn ("ManuDate");
    model.addColumn("qty");
    model.addColumn ("Price");
        
    }
    void showTime(){
        new Timer(0, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Date d= new Date();
                SimpleDateFormat sdf= new SimpleDateFormat("hh:mm:ss a");
                lblTime.setText(sdf.format(d));
                
                
            }
            
        }).start();
    }
   

    public void loadCombo() {
        Connection con = null;
        Statement stm = null;
        try {
            con = DBConnection.getDBconnection();
            stm = con.createStatement();

            List plist = new ArrayList();
            ResultSet rs1 = stm.executeQuery("select MediName from medicine order by MediName");
            while (rs1.next()) {
                plist.add(rs1.getString("MediName"));
            }
            cmbMedN.setModel(new javax.swing.DefaultComboBoxModel(plist.toArray()));
            cmbMedN.insertItemAt("Select one", 0);
            cmbMedN.setSelectedIndex(0);
//    if(cbmItem.getSelectedItem().equals("mouse"));{
//            txtaqty.setText("4");
//             txtpprice.setText("500");
//          txtsprice.setText("600");
//           txtqty.setText("");
            //   }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    public void displayInTable(){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            /*
            con=DBConnection.getDBconnection();
            String sql= "select * from medicine";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery(sql);
            DefaultTableModel tr=(DefaultTableModel)tblMed.getModel();
            tr.setRowCount(0);
            Object o[]={rs.getInt("Id"), rs.getString("MediName"), rs.getString("CompName"), rs.getString("ExpairyDate"), rs.getString("ManuDate"), rs.getString("Price")};
            tr.addRow(o);
            */
            
            con=DBConnection.getDBconnection();
            stmt = con.createStatement();

            //Execute the query
            rs = stmt.executeQuery("select * from medicine");
            
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("medi_id"), rs.getString("MediName"), rs.getString("CompName"), rs.getString("ExpairyDate"), rs.getString("ManuDate"),rs.getString("qty"), rs.getString("Price")});

            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public void deleteRecord(){
    String id;
        id = txtId.getText();

        Connection con = null;
        Statement stmt = null;
        try {
            con = DBConnection.getDBconnection();
            //Create the Statement object
            stmt = con.createStatement();
            int count = 0;
            //Execute the query
            count = stmt.executeUpdate("delete from medicine where medi_id='" + id + "' ");
            if (count > 0) {
//                System.out.println("inserted successfully");
                JOptionPane.showMessageDialog(this, "Deleted successfully");
                clear();
            } else {
//                System.out.println("insertion failed");
                JOptionPane.showMessageDialog(this, "Delete failed", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //Close the connection and statement object
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    
    
    
    
    }

//    public ArrayList<User> userList() {
//        ArrayList<User> usersList = new ArrayList<>();
//        try {
//            Connection con = DBConnection.getDBconnection();
//            Statement st = con.createStatement();
//            String st1 = "select * from medicine";
//            ResultSet rs = st.executeQuery(st1);
//            User user;
//            while (rs.next()) {
//                user = new User(rs.getInt("Id"), rs.getString("MediName"), rs.getString("CompName"), rs.getString("ExpairyDate"), rs.getString("ManuDate"), rs.getString("price"));
//                usersList.add(user);
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        return usersList;
//    }
//
//    public void show_user() {
//        ArrayList<User> list = userList();
//        DefaultTableModel model = (DefaultTableModel) tblMed.getModel();
//        Object[] row = new Object[6];
//        for (int i = 0; i < list.size(); i++) {
//            row[0] = list.get(i).getid();
//            row[1] = list.get(i).getname();
//            row[2] = list.get(i).getcompname();
//            row[3] = list.get(i).getexDate();
//            row[4] = list.get(i).getmaDate();
//            row[5] = list.get(i).getprice();
//            model.addRow(row);
//
//        }
//    }

    public void sh() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
           
            Connection con = DBConnection.getDBconnection();
            String sql = "select medi_id, MediName, CompName, ExpairyDate, ManuDate,Price from medicine where MediName='" + txtse.getText() + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtId.setText(rs.getString(1));
                cmbMedN.setSelectedItem(rs.getString(2));
                txtComp.setText(rs.getString(3));
                txtExDate.setText(rs.getString(4));
                txtManuDate.setText(rs.getString(5));
                txtqty.setText(rs.getString(6));
                txtPrice.setText(rs.getString(7));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
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
        txtId = new javax.swing.JTextField();
        cmbMedN = new javax.swing.JComboBox<>();
        txtComp = new javax.swing.JTextField();
        txtExDate = new javax.swing.JTextField();
        txtManuDate = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtqty = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtse = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        lblDate = new javax.swing.JLabel();
        tblTime = new javax.swing.JLabel();
        lbl_time = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lbl_date = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMed = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnViews = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Id");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("MediName");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("CompName");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("ExpairyDate");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 255));
        jLabel5.setText("ManuDate");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setText("Price");

        cmbMedN.setEditable(true);
        cmbMedN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbMedN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One", "Paracetamol", "Napa", "Maxpro", "Esolock" }));
        cmbMedN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMedNItemStateChanged(evt);
            }
        });
        cmbMedN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMedNActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 255));
        jLabel8.setText("Qty");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2))
                                            .addGap(10, 10, 10)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel8))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtId)
                                    .addComponent(cmbMedN, 0, 220, Short.MAX_VALUE)
                                    .addComponent(txtComp)
                                    .addComponent(txtExDate)
                                    .addComponent(txtManuDate)
                                    .addComponent(txtqty))))
                        .addGap(59, 59, 59))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbMedN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtExDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtManuDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 0));

        jLabel7.setBackground(new java.awt.Color(0, 204, 51));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Medicine Information");

        txtse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtseKeyReleased(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(0, 102, 102));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lblTime.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTime.setForeground(new java.awt.Color(102, 51, 0));

        lbl_date.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_date.setForeground(new java.awt.Color(102, 51, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_time, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtse, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(512, 512, 512)
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lbl_date, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(795, 795, 795)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(tblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtse, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(lbl_time, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        tblMed.setBackground(new java.awt.Color(0, 204, 255));
        tblMed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "MediName", "CompName", "ExpairyDate", "ManuDate", "qty", "price"
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(51, 51, 255));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        btnUpdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnUpdateKeyReleased(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(204, 0, 0));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnViews.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnViews.setForeground(new java.awt.Color(0, 102, 102));
        btnViews.setText("Views");
        btnViews.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewsActionPerformed(evt);
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
        btnClear.setForeground(new java.awt.Color(0, 102, 0));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnSave.setForeground(new java.awt.Color(153, 153, 153));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 51));
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(33, 33, 33)
                .addComponent(btnViews)
                .addGap(42, 42, 42)
                .addComponent(btnClear)
                .addGap(51, 51, 51)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton2)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViews)
                    .addComponent(btnDelete)
                    .addComponent(btnUpdate)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave)
                    .addComponent(btnExit)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(298, 298, 298))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 975, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   private JFrame frame;
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        frame = new JFrame();
        if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Pharmacy management system", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnViewsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewsActionPerformed
//        show_user();
displayInTable();
    }//GEN-LAST:event_btnViewsActionPerformed
    

    
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();

    }//GEN-LAST:event_btnClearActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteRecord();
//      Connection con=null;
//      PreparedStatement pst=null;
//     int p= JOptionPane.showConfirmDialog(null, "Do you want to delete", "delete", JOptionPane.YES_NO_OPTION);
//      if(p==0){
//        String sql="select from medicine where Id=?";
//        try {
//            pst=con.prepareStatement(sql);
//            pst.setString(1, txtId.getText());
//            pst.execute();
//            JOptionPane.showMessageDialog(null, "Delectd");
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//      
//      }
        DefaultTableModel model = (DefaultTableModel) tblMed.getModel();
        try {
            int selectedRowIndex = tblMed.getSelectedRow();
            model.removeRow(selectedRowIndex);
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String id, name, comname, exDate, maDate, qty, price;
        id = txtId.getText();
        name = cmbMedN.getSelectedItem().toString();
        comname = txtComp.getText();
        exDate = txtExDate.getText();
        maDate = txtManuDate.getText();
        qty= txtqty.getText();
        price = txtPrice.getText();

        Connection con = null;
        Statement st = null;
        try {
            con = DBConnection.getDBconnection();
            st = con.createStatement();
            int count = 0;
            count = st.executeUpdate("update medicine set MediName='" + name + "',CompName='" + comname + "', ExpairyDate='" + exDate + "', ManuDate='" + maDate + "',qty='"+qty+"', Price='" + price + "'where medi_id='" + id + "'");
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "updated successfully");
                show();
                clear();
                
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
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String MediName, CompName, ExpiryDate, ManuDate, qty, Price;
        MediName = cmbMedN.getSelectedItem().toString();
        CompName = txtComp.getText();
        ExpiryDate = txtExDate.getText();
        ManuDate = txtManuDate.getText();
        qty= txtqty.getText();
        Price = txtPrice.getText();
        Connection con = null;
        Statement st = null;
        try {

            con = DBConnection.getDBconnection();
            st = con.createStatement();
            int count = 0;
            count = st.executeUpdate("insert into medicine (MediName,CompName,ExpairyDate,ManuDate,qty, Price) values('" + MediName + "','" + CompName + "','" + ExpiryDate + "','" + ManuDate + "','" + qty + "','" + Price + "')");

            if (count > 0) {
                JOptionPane.showMessageDialog(this, "Saved");
                System.out.println("");
                loadCombo();
                
           
                
            } else {
                JOptionPane.showMessageDialog(this, "Inserted failed", "error", JOptionPane.ERROR_MESSAGE);
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
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtseKeyReleased
        DefaultTableModel table = (DefaultTableModel) tblMed.getModel();
        String search = txtse.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        tblMed.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));

    }//GEN-LAST:event_txtseKeyReleased

    private void tblMedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedMouseClicked
        int selectedrow = tblMed.getSelectedRow();
        try {
            txtId.setText(tblMed.getValueAt(selectedrow, 0).toString());

            txtComp.setText(tblMed.getValueAt(selectedrow, 2).toString());
            txtExDate.setText(tblMed.getValueAt(selectedrow, 3).toString());
            txtManuDate.setText(tblMed.getValueAt(selectedrow, 4).toString());
            txtqty.setText(tblMed.getValueAt(selectedrow, 5).toString());
                   
            txtPrice.setText(tblMed.getValueAt(selectedrow, 6).toString());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblMedMouseClicked

    private void cmbMedNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMedNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMedNActionPerformed

    private void cmbMedNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMedNItemStateChanged
        if (cmbMedN.getSelectedIndex() > 0) {
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                con = DBConnection.getDBconnection();
                st = con.createStatement();
                rs = st.executeQuery("select * from medicine where MediName='" + cmbMedN.getSelectedItem().toString() + "'");
                while (rs.next()) {
                    txtId.setText(rs.getString("medi_id"));
                    txtComp.setText(rs.getString("CompName"));
                    txtExDate.setText(rs.getString("ExpairyDate"));
                    txtManuDate.setText(rs.getString("ManuDate"));
                    txtqty.setText(rs.getString("qty"));
                    txtPrice.setText(rs.getString("Price"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_cmbMedNItemStateChanged

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        sh();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnUpdateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnUpdateKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(false);
        PharmacyItem pi= new PharmacyItem();
        pi.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Medicine.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Medicine.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Medicine.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Medicine.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Medicine().setVisible(true);
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
    private javax.swing.JButton btnViews;
    private javax.swing.JComboBox<String> cmbMedN;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_time;
    private javax.swing.JTable tblMed;
    private javax.swing.JLabel tblTime;
    private javax.swing.JTextField txtComp;
    private javax.swing.JTextField txtExDate;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtManuDate;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtqty;
    private javax.swing.JTextField txtse;
    // End of variables declaration//GEN-END:variables
public void clear() {
        txtId.setText("");
        cmbMedN.setToolTipText("");
        txtComp.setText("");
        txtExDate.setText("");
        txtManuDate.setText("");
        txtqty.setText("");
        txtPrice.setText("");
    

}

   
}
