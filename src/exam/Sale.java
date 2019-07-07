/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.*;
import java.awt.print.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author tofail
 */
public class Sale extends javax.swing.JFrame {
    DefaultTableModel model=null;
   
     private java.util.Date currDate = new java.util.Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String d;


    /**
     * Creates new form Sale
     */
    public Sale() {
        initComponents();
       // fillCombo();
       loadCombo();
         d = sdf.format(currDate);
        txtdate.setText(d);
        model= new DefaultTableModel();
        tblSale.setModel(model);
        model.addColumn("id");
        model.addColumn("medi_name");
        model.addColumn("qty");
        model.addColumn("price");
        model.addColumn("amount");
        model.addColumn("discount");
        model.addColumn("netamount");
        model.addColumn("date");
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
//   private void fillCombo(){
//       Connection con=null;
//       PreparedStatement pst=null;
//       ResultSet rs=null; 
//       try {
//           String sql= "select * from sale";
//           pst=con.prepareStatement(sql);
//           rs=pst.executeQuery();
//           while(rs.next()){
//               String medi_name=rs.getString("medi_name");
//           }
//                   
//           
//       } catch (Exception e) {
//           JOptionPane.showMessageDialog(null, e);
//       }
//              
//   }
    
    public void loadCombo(){
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            con=DBConnection.getDBconnection();
            st=con.createStatement();
            List pList=new ArrayList();
            rs=st.executeQuery("select medi_name from sale order by medi_name");
            while(rs.next()){
                pList.add(rs.getString("medi_name"));
            }
            cmbmd.setModel(new javax.swing.DefaultComboBoxModel(pList.toArray()));
            cmbmd.insertItemAt("Select one", 0);
            cmbmd.setSelectedIndex(0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void sh() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
           
            Connection con = DBConnection.getDBconnection();
            String sql = "select id, medi_name, qty, price, amount, discount, netamount, date from sale where medi_name='" + btnSearch.getText() + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtid.setText(rs.getString(1));
                cmbmd.setSelectedItem(rs.getString(2));
                txtqty.setText(rs.getString(3));
                txtprice.setText(rs.getString(4));
                txtamount.setText(rs.getString(5));
                txtdiscount.setText(rs.getString(6));
                txtdate.setText(rs.getString(6));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    public void deleteRecord(){
        String id= txtid.getText();
        Connection con=null;
        Statement st=null;
        try {
            con=DBConnection.getDBconnection();
            st=con.createStatement();
            int count=0;
            count=st.executeUpdate("delete from sale where id='"+id+"'");
            if(count>0){
                JOptionPane.showMessageDialog(null, "Deleted successfully");
            }else{
                JOptionPane.showMessageDialog(null, "Deleted failed", "error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void displayInTable(){
        DefaultTableModel model=(DefaultTableModel)tblSale.getModel();
         int row =tblSale.getRowCount();
         
        if(row>0){
            for (int i =row-1;i>-1; i--) {
               model.removeRow(i);
            }
        }
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            con=DBConnection.getDBconnection();
            st=con.createStatement();
            rs=st.executeQuery("select * from sale");
//            DefaultTableModel tm= (DefaultTableModel)tblSale.getModel();
//            tm.setRowCount(0);
//            while(rs.next()){
//                Object o[]={rs.getInt("id"), rs.getString("medi_name"), rs.getString("qty"), rs.getString("price"), rs.getString("amount"),rs.getString("discount"), rs.getString("netamount"), rs.getDate("date")};
//               tm.addRow(o);
            while(rs.next()){
            model.addRow(new Object[]{rs.getString("id"), rs.getString("medi_name"), rs.getString("qty"), rs.getString("price"), rs.getString("amount"),rs.getString("discount"), rs.getString("netamount"),rs.getDate("date")});
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        txtid = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cmbmd = new javax.swing.JComboBox<>();
        txtqty = new javax.swing.JTextField();
        txtprice = new javax.swing.JTextField();
        txtamount = new javax.swing.JTextField();
        txtdiscount = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();
        btnCalculate = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtdate = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtsprice = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lbl_Time = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSale = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnView = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTa = new javax.swing.JTextField();
        txtDis = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("id");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 0));
        jLabel2.setText("medi_name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setText("qty");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 0));
        jLabel4.setText("price");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 0));
        jLabel5.setText("amount");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 0));
        jLabel6.setText("discount");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 0));
        jLabel7.setText("netamount");

        cmbmd.setEditable(true);
        cmbmd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbmd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Napa", "Econate+", "paracetamol\t", "Ace\t", " " }));
        cmbmd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmdItemStateChanged(evt);
            }
        });
        cmbmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbmdActionPerformed(evt);
            }
        });

        txttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalActionPerformed(evt);
            }
        });

        btnCalculate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCalculate.setForeground(new java.awt.Color(51, 51, 51));
        btnCalculate.setText("Calculate");
        btnCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(51, 51, 255));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReset.setForeground(new java.awt.Color(51, 0, 51));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 102, 0));
        jLabel12.setText("Date");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 0));
        jLabel13.setText("Sprice");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCalculate)
                                .addGap(39, 39, 39)
                                .addComponent(btnAdd)
                                .addGap(38, 38, 38)
                                .addComponent(btnReset))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel1)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel6)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel13)
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel4)
                                                                .addComponent(jLabel3)))
                                                        .addGap(25, 25, 25))))
                                            .addGap(7, 7, 7)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel12)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txttotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addComponent(txtprice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addComponent(txtqty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addComponent(txtid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdate, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtdiscount, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addComponent(txtamount, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addComponent(txtsprice)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(cmbmd, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbmd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtsprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalculate)
                    .addComponent(btnAdd)
                    .addComponent(btnReset))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 255));
        jLabel8.setText("SALE INFORMATION");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(102, 102, 0));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 0, 0));

        lbl_Time.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        lbl_Date.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel8)
                .addGap(35, 35, 35)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addGap(18, 18, 18)
                .addComponent(lbl_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearch)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_Time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        tblSale.setBackground(new java.awt.Color(255, 153, 153));
        tblSale.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "medi_name", "qty", "price", "amount", "discount", "total", "date"
            }
        ));
        tblSale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSaleMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSale);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        btnView.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnView.setForeground(new java.awt.Color(153, 51, 0));
        btnView.setText("View");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 0, 51));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 51, 0));
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("Print");
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
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(btnView)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnView)
                    .addComponent(btnDelete)
                    .addComponent(btnExit)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(51, 255, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("total amount");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("discount");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDis, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTa, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtDis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
 private JFrame frame ;
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
      frame= new JFrame();
        if(JOptionPane.showConfirmDialog(this, "Do you want to Exit", "pharmacy management system", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
        
       System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void txttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       deleteRecord();
       DefaultTableModel model= (DefaultTableModel)tblSale.getModel();
        try {
            int selectedRowIndex= tblSale.getSelectedRow();
            model.removeRow(selectedRowIndex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
       displayInTable();
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
//       String medi_name, qty, price, amount, discount, netamount;
//       medi_name=cmbmd.getSelectedItem().toString();
//       qty= txtqty.getText();
//       price=txtprice.getText();
//       amount=txtamount.getText();
//       discount =txtdiscount.getText();
//       netamount=txttotal.getText();
//       Connection con=null;
//       Statement st=null;
//        try {
//            con=DBConnection.getDBconnection();
//            st=con.createStatement();
//            int count=0;
//            count=st.executeUpdate("insert into sale(medi_name, qty, price, amount, discount, netamount) values('"+medi_name+"','"+qty+"','"+price+"', '"+amount+"','"+discount+"','"+netamount+"')");
//            if(count>0){
//                JOptionPane.showMessageDialog(null, "success");
//            }
//        } catch (Exception e) {
//        }
       DefaultTableModel model= (DefaultTableModel)tblSale.getModel();
       model.addRow(new Object[]{txtid.getText(), cmbmd.getSelectedItem().toString(), txtqty.getText(), txtprice.getText(), txtamount.getText(), txtdiscount.getText(), txttotal.getText(),txtdate.getText()});
        
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateActionPerformed
       double price=Double.parseDouble(txtprice.getText());
       double qty=Double.parseDouble(txtqty.getText());
       double discount= Double.parseDouble(txtdiscount.getText());
       double amount=price*qty;
       double dis=amount*discount/100;
       double netamount=amount-discount;
       txtamount.setText(""+amount);
       txttotal.setText(""+netamount);
       txtTa.setText(""+amount);
       txtDis.setText(""+dis);
       
       
       
    }//GEN-LAST:event_btnCalculateActionPerformed

    private void cmbmdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbmdItemStateChanged
        if(cmbmd.getSelectedIndex()>0){
            Connection con=null;
            Statement st=null;
            ResultSet rs=null;
            try {
                con=DBConnection.getDBconnection();
                st=con.createStatement();
                rs=st.executeQuery("select * from sale where medi_name='"+cmbmd.getSelectedItem().toString()+"'");
                while(rs.next()){
                    txtid.setText(rs.getString("id"));
                    txtqty.setText(rs.getString("qty"));
                    txtprice.setText(rs.getString("price"));
                   txtamount.setText(rs.getString("amount"));
                   txtdiscount.setText(rs.getString("discount"));
                   txttotal.setText(rs.getString("netamount"));
                   txtdate.setText(rs.getString("date"));
                   
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_cmbmdItemStateChanged

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
       txtid.setText("");
       cmbmd.setSelectedItem("");
       txtqty.setText("");
       txtprice.setText("");
       txtamount.setText("");
       txtdiscount.setText("");
       txttotal.setText("");
       txtdate.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void cmbmdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbmdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbmdActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        sh();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tblSaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSaleMouseClicked
        int selectedrow = tblSale.getSelectedRow();
        try {
            txtid.setText(tblSale.getValueAt(selectedrow, 0).toString());

            txtqty.setText(tblSale.getValueAt(selectedrow, 2).toString());
            txtprice.setText(tblSale.getValueAt(selectedrow, 3).toString());
            txtamount.setText(tblSale.getValueAt(selectedrow, 4).toString());
            txtdiscount.setText(tblSale.getValueAt(selectedrow, 5).toString());
            txtdate.setText(tblSale.getValueAt(selectedrow, 6).toString());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblSaleMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        DefaultTableModel table = (DefaultTableModel) tblSale.getModel();
        String search = txtSearch.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        tblSale.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_txtSearchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String medi_name, qty, price, amount, discount, netamount, date;
        medi_name = cmbmd.getSelectedItem().toString();
        qty = txtqty.getText();
        price = txtprice.getText();
        amount = txtamount.getText();
        discount = txtdiscount.getText();
        netamount = txttotal.getText();
        date = txtdate.getText();
        ;
        Connection con = null;
        Statement st = null;
        try {

            con = DBConnection.getDBconnection();
            st = con.createStatement();
            int count = 0;
            count = st.executeUpdate("insert into sale (medi_name,qty,price,amount,discount, netamount, date) values('" + medi_name + "','" + qty + "','" + price + "','" + amount + "','" + discount + "','"+netamount+"','"+date+"')");

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
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MessageFormat header = new MessageFormat("Report Print");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {
            tblSale.print(JTable.PrintMode.NORMAL,header, footer);
            
        }catch(java.awt.print.PrinterException e) {
            System.err.format("cannot print %s%n", e.getMessage());
        }
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
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCalculate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnView;
    private javax.swing.JComboBox<String> cmbmd;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_Date;
    private javax.swing.JLabel lbl_Time;
    private javax.swing.JTable tblSale;
    private javax.swing.JTextField txtDis;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTa;
    private javax.swing.JTextField txtamount;
    private javax.swing.JTextField txtdate;
    private javax.swing.JTextField txtdiscount;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtqty;
    private javax.swing.JTextField txtsprice;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
