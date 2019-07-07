/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Stuff extends javax.swing.JFrame {

    Connection con;
    Statement st;
    ResultSet rs;
    DefaultTableModel model;

    /**
     * Creates new form Stuff
     */
    public Stuff() {
        initComponents();
        loadCombo();
        model = new DefaultTableModel();
        tblStuff.setModel(model);
        model.addColumn("id");
        model.addColumn("s_name");
        model.addColumn("address");
        model.addColumn("age");
        model.addColumn("salary");
        model.addColumn("joining_date");
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
    

    public void loadCombo() {
        Connection con = null;
        Statement stm = null;
        try {
            con = DBConnection.getDBconnection();
            stm = con.createStatement();

            List plist = new ArrayList();
            ResultSet rs1 = stm.executeQuery("select s_name from stuff order by s_name");
            while (rs1.next()) {
                plist.add(rs1.getString("s_name"));
            }
            cmbSName.setModel(new javax.swing.DefaultComboBoxModel(plist.toArray()));
            cmbSName.insertItemAt("Select any Stuff's Name", 0);
            cmbSName.setSelectedIndex(0);
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
      public void sh() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
           
            Connection con = DBConnection.getDBconnection();
            String sql = "select id, s_name, address, age, salary, joining_date from stuff where s_name='" + btnSe.getText() + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtID.setText(rs.getString(1));
                cmbSName.setSelectedItem(rs.getString(2));
                txtAreaAdd.setText(rs.getString(3));
                txtAge.setText(rs.getString(4));
                txtSalary.setText(rs.getString(5));
                txtJoinDate.setText(rs.getString(6));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    

//    public ArrayList<User> userList() {
//        ArrayList<User> usersList = new ArrayList<>();
//        try {
//            Connection con = DBConnection.getDBconnection();
//            Statement st = con.createStatement();
//            String st1 = "select * from stuff";
//            ResultSet rs = st.executeQuery(st1);
//            User user;
//            while (rs.next()) {
//                user = new User(rs.getInt("id"), rs.getString("s_name"), rs.getString("address"), rs.getString("age"), rs.getString("salary"), rs.getString("joining_date"));
//                System.out.println(user.getSname());
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
//        DefaultTableModel model = (DefaultTableModel) tblStuff.getModel();
//        Object[] row = new Object[6];
//        for (int i = 0; i < list.size(); i++) {
//            row[0] = list.get(i).getid();
//            row[1] = list.get(i).getSname();
//            row[2] = list.get(i).getaddress();
//            row[3] = list.get(i).getage();
//            row[4] = list.get(i).getsalary();
//            row[5] = list.get(i).getjoiningDate();
//            model.addRow(row);
//
//        }
//    }
//    private static class User {
//
//        private int id;
//        private String Sname, address, age, salary, joiningDate;
//
//        public User(int id, String Sname, String address, String age , String salary, String joiningDate) {
//            this.id = id;
//            this.Sname = Sname;
//            this.address = address;
//            this.age = age;
//            this.salary = salary;
//            this.joiningDate = joiningDate;
//
//        }
//
//        public int getid() {
//            return id;
//        }
//
//        public String getSname() {
//            return Sname;
//        }
//
//        public String getaddress() {
//            return address;
//        }
//
//        public String getage() {
//            return age;
//        }
//
//        public String getsalary() {
//            return salary;
//        }
//
//        public String getjoiningDate() {
//            return joiningDate;
//        }
//    }
     public void displayInTable(){
         if(tblStuff.getRowCount()>0){
    for(int i=tblStuff.getRowCount()-1;i>-1;i--){
        model.removeRow(i);
    }
    }
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        try {
            con=DBConnection.getDBconnection();
            String sql= "select * from stuff";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            DefaultTableModel tm= (DefaultTableModel)tblStuff.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getInt("id"), rs.getString("s_name"), rs.getString("address"), rs.getString("age"), rs.getString("salary"),rs.getString("joining_date")};
               tm.addRow(o);
            }
            
        } catch (Exception e) {
        }
    }
    public void deleteRecord(){
        String id;
        id=txtID.getText();
        Connection con=null;
        Statement st=null;
        try {
            con=DBConnection.getDBconnection();
            st=con.createStatement();
            int count=0;
            
            count=st.executeUpdate("delete from stuff where id='"+id+"'");
            if(count>0){
              //  System.out.println("");
              JOptionPane.showMessageDialog(this, "Deleted successfully");
            }else{
                JOptionPane.showMessageDialog(this, "Deleted failed", "error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
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
        txtID = new javax.swing.JTextField();
        txtAge = new javax.swing.JTextField();
        txtSalary = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtJoinDate = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaAdd = new javax.swing.JTextArea();
        cmbSName = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStuff = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        lbl_Time = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JLabel();
        btnSe = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 51, 0));
        jLabel1.setText("ID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 51, 0));
        jLabel2.setText("s_name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 51, 0));
        jLabel3.setText("Address");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 51, 0));
        jLabel4.setText("Age");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 51, 0));
        jLabel5.setText("Salary");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 51, 0));
        jLabel6.setText("JoiningDate");

        txtAreaAdd.setColumns(20);
        txtAreaAdd.setRows(5);
        jScrollPane1.setViewportView(txtAreaAdd);

        cmbSName.setEditable(true);
        cmbSName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmbSName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select any Stuff's name", "Monir", "Sazzad", "Sayem\t", "Tarique" }));
        cmbSName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSNameItemStateChanged(evt);
            }
        });
        cmbSName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSNameActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAge)
                    .addComponent(txtID)
                    .addComponent(txtSalary)
                    .addComponent(txtJoinDate)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbSName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtJoinDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(36, 36, 36))))
        );

        tblStuff.setBackground(new java.awt.Color(153, 153, 0));
        tblStuff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "s_name", "address", "age", "salary", "joining_date"
            }
        ));
        tblStuff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStuffMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblStuff);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBackground(new java.awt.Color(204, 0, 0));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel7.setText("Stuff Information");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        btnSe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSe.setText("Search");
        btnSe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(51, 51, 51)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSe)
                .addGap(18, 18, 18)
                .addComponent(lbl_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 14, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSe)))
                            .addComponent(jLabel7)))
                    .addComponent(lbl_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 0, 0));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnClear.setForeground(new java.awt.Color(0, 204, 102));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnSave.setForeground(new java.awt.Color(153, 0, 102));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 153, 0));
        jButton1.setText("View");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(153, 0, 153));
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
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnUpdate)
                .addGap(31, 31, 31)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnDelete)
                .addGap(34, 34, 34)
                .addComponent(btnClear)
                .addGap(32, 32, 32)
                .addComponent(btnExit)
                .addGap(37, 37, 37)
                .addComponent(jButton2)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete)
                    .addComponent(jButton2)
                    .addComponent(btnClear)
                    .addComponent(btnExit))
                .addGap(0, 21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(497, 497, 497))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String id, s_name, address, age, salary, joining_date;
        id = txtID.getText();
        s_name = cmbSName.getSelectedItem().toString();
        address = txtAreaAdd.getText();
        age = txtAge.getText();
        salary = txtSalary.getText();
        joining_date = txtJoinDate.getText();
        Connection con = null;
        Statement st = null;
        try {
            con = DBConnection.getDBconnection();
            st = con.createStatement();
            int count = 0;
            count = st.executeUpdate("update stuff set s_name ='" + id + "',address='" + address + "', age='" + age + "', salary='" + salary + "', joining_date='" + joining_date + "' where id='" + id + "'");
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "updated successfully");
                loadCombo();

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
    private JFrame frame;
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        frame = new JFrame();
        if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Pharmacy management system", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {

            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
  deleteRecord();
        DefaultTableModel model = (DefaultTableModel) tblStuff.getModel();
        try {
            int selectedRowIndex = tblStuff.getSelectedRow();
            model.removeRow(selectedRowIndex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String s_name, address, age, salary, joining_date;
        s_name = cmbSName.getSelectedItem().toString();
        address = txtAreaAdd.getText();
        age = txtAge.getText();
        salary = txtSalary.getText();
        joining_date = txtJoinDate.getText();
        Connection con = null;
        Statement st = null;
        try {

            con = DBConnection.getDBconnection();
            st = con.createStatement();
            int count = 0;
            count = st.executeUpdate("insert into stuff (s_name,address,age,salary,joining_date) values('" + s_name + "','" + address + "','" + age + "','" + salary + "','" + joining_date + "')");

            if (count > 0) {
                JOptionPane.showMessageDialog(this, "Saved");
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        show_user();
displayInTable();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblStuffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStuffMouseClicked
        int selectedrow = tblStuff.getSelectedRow();
        try {
            txtID.setText(tblStuff.getValueAt(selectedrow, 0).toString());

            txtAreaAdd.setText(tblStuff.getValueAt(selectedrow, 2).toString());
            txtAge.setText(tblStuff.getValueAt(selectedrow, 3).toString());
            txtSalary.setText(tblStuff.getValueAt(selectedrow, 4).toString());
            txtJoinDate.setText(tblStuff.getValueAt(selectedrow, 5).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblStuffMouseClicked

    private void cmbSNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSNameItemStateChanged
        if (cmbSName.getSelectedIndex() > 0) {
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                con = DBConnection.getDBconnection();
                st = con.createStatement();
                rs = st.executeQuery("select * from stuff where s_name='" + cmbSName.getSelectedItem().toString() + "'");
                while (rs.next()) {
                    txtID.setText(rs.getString("id"));
                    txtAreaAdd.setText(rs.getString("address"));
                    txtAge.setText(rs.getString("age"));
                    txtSalary.setText(rs.getString("salary"));
                    txtJoinDate.setText(rs.getString("joining_date"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_cmbSNameItemStateChanged

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        DefaultTableModel table = (DefaultTableModel) tblStuff.getModel();
        String search = txtSearch.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        tblStuff.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));

    }//GEN-LAST:event_txtSearchKeyReleased

    private void cmbSNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSNameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(false);
        PharmacyItem pi= new PharmacyItem();
        pi.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         DefaultTableModel model= (DefaultTableModel)tblStuff.getModel();
       model.addRow(new Object[]{txtID.getText(), cmbSName.getSelectedItem().toString(), txtAreaAdd.getText(), txtAge.getText(), txtSalary.getText(), txtJoinDate.getText()});
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
      // sh();
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnSeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeActionPerformed
       sh();
    }//GEN-LAST:event_btnSeActionPerformed

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
            java.util.logging.Logger.getLogger(Stuff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stuff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stuff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stuff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stuff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSe;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbSName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_Date;
    private javax.swing.JLabel lbl_Time;
    private javax.swing.JTable tblStuff;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextArea txtAreaAdd;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtJoinDate;
    private javax.swing.JTextField txtSalary;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    

    public void clear() {
        txtID.setText("");
        cmbSName.setToolTipText("");
        txtAreaAdd.setText("");
        txtAge.setText("");
        txtSalary.setText("");
        txtJoinDate.setText("");
    }

}
