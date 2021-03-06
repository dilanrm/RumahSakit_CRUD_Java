package rumahsakit;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
//import java.beans.Statement;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.Connection;
import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//import com.mysql.jdbc.*;
//import com.mysql.jdbc.Driver;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author dilan
 */
public class RusakFrame extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    Connection conn; //JDBC
    Statement st; //JDBC
    ResultSet rs; //Java.Sql


    /**
     * Creates new form RusakFrame
     */
    public RusakFrame() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2
                - this.getSize().width / 2, dim.height / 6
                - this.getSize().height / 6);

        conn = null;
        st = null;
        rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/dbhospital";
            String user = "root";
            String pwd = "";

            conn = (Connection) DriverManager.getConnection(url, user, pwd);
            // System.out.println("Terkoneksi DB");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed connect to Database :" + e,
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            //  System.out.println("Error Connection Karena :" + e);
        }

        rusakTbl.setModel(model);
        model.addColumn("No");
        model.addColumn("ID Pasien");
        model.addColumn("Nama Pasien");
        model.addColumn("Tanggal Datang");
        model.addColumn("Biaya");
        model.addColumn("Tipe");
        model.addColumn("Tanggal Cek-Up");
        model.addColumn("Tanggal Pulang");
        
        cekupFF.setEnabled(false);
        pulangFF.setEnabled(true);
        
        btnTambah.setEnabled(true);
        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);
        btnCari.setEnabled(true);
        btnBatal.setEnabled(true);
        btnKeluar.setEnabled(true);

        tampil();

    }
    
    private void tampil() {
        model.fireTableDataChanged();
        model.getDataVector().removeAllElements();
        String query = "select * from `hospital` order by `idpasien` ASC";
        try {
            st = (Statement) conn.createStatement();
            rs = st.executeQuery(query);
            int no = 1;
            while (rs.next()) {
                Object[] o = new Object[]{
                    no++,
                    rs.getString("idpasien"),
                    rs.getString("nama"),
                    rs.getString("tgldatang"),
                    rs.getInt("bayar"),
                    rs.getString("tipe"),
                    rs.getString("cekup"),
                    rs.getString("tglpulang")
                };
                model.addRow(o);
            }
        } catch (Exception e) {
            System.out.println("Error Tampil :" + e);
        }
    }

    private void tampilkan() {
        if (rusakTbl.getSelectedRow() > -1) {
            idPasienF.setText(model.getValueAt(rusakTbl.getSelectedRow(), 1).toString());
            namaF.setText(model.getValueAt(rusakTbl.getSelectedRow(), 2).toString());
            datangF.setText((model.getValueAt(rusakTbl.getSelectedRow(), 3).toString()));
            bayarFF.setValue(Integer.parseInt(model.getValueAt(rusakTbl.getSelectedRow(), 4).toString()));
            tipeC.setSelectedItem(model.getValueAt(rusakTbl.getSelectedRow(), 5).toString());
            cekupFF.setText((model.getValueAt(rusakTbl.getSelectedRow(), 6).toString()));
            pulangFF.setText((model.getValueAt(rusakTbl.getSelectedRow(), 7).toString()));
            btnTambah.setEnabled(true);
            btnEdit.setEnabled(true);
            btnHapus.setEnabled(true);
            btnCari.setEnabled(true);
            btnBatal.setEnabled(true);
            btnKeluar.setEnabled(true);
        }
    }
    
    private void hapus(){
        idPasienF.setText("");
        namaF.setText("");
        datangF.setText("");
        bayarFF.setValue(0);
        cekupFF.setText("");
        tipeC.setSelectedItem("Inap");
        pulangFF.setText("");
    }

    private void tabelMouseClicked() {                                   
        // TODO add your handling code here:
        tampilkan();
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
        idPasienF = new javax.swing.JTextField();
        namaF = new javax.swing.JTextField();
        tipeC = new javax.swing.JComboBox<>();
        bayarFF = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        rusakTbl = new javax.swing.JTable();
        btnCari = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        datangF = new javax.swing.JTextField();
        cekupFF = new javax.swing.JTextField();
        pulangFF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Rumah Sakit");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID Pasien");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Datang");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Bayar");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tipe");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cek-Up");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Pulang");

        tipeC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inap", "Pulang" }));
        tipeC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipeCActionPerformed(evt);
            }
        });

        bayarFF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        rusakTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Pasien", "Nama Pasien", "Tanggal Datang", "Biaya", "Tipe", "Tanggal Cek-Up", "Tanggal Pulang"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        rusakTbl.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                rusakTblFocusGained(evt);
            }
        });
        rusakTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rusakTblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(rusakTbl);
        if (rusakTbl.getColumnModel().getColumnCount() > 0) {
            rusakTbl.getColumnModel().getColumn(2).setResizable(false);
        }

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        cekupFF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekupFFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tipeC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bayarFF)
                            .addComponent(namaF)
                            .addComponent(idPasienF)
                            .addComponent(datangF)
                            .addComponent(cekupFF)
                            .addComponent(pulangFF)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(289, 289, 289))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnTambah)
                .addGap(36, 36, 36)
                .addComponent(btnHapus)
                .addGap(48, 48, 48)
                .addComponent(btnEdit)
                .addGap(58, 58, 58)
                .addComponent(btnCari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(btnRefresh)
                .addGap(37, 37, 37)
                .addComponent(btnBatal)
                .addGap(38, 38, 38)
                .addComponent(btnKeluar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(idPasienF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(namaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(datangF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bayarFF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tipeC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cekupFF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(pulangFF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnHapus)
                        .addComponent(btnTambah)
                        .addComponent(btnEdit)
                        .addComponent(btnCari))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBatal)
                        .addComponent(btnKeluar)
                        .addComponent(btnRefresh)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        int pesan = JOptionPane.showConfirmDialog(null, "Anda Yakin Akan Keluar?", "Question", JOptionPane.OK_CANCEL_OPTION);
        if (pesan == 0) {
            dispose();
        }
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:   
        hapus();
        
        btnTambah.setEnabled(true);
        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);
        btnCari.setEnabled(true);
        btnBatal.setEnabled(true);
        btnKeluar.setEnabled(true);
        btnTambah.setText("Tambah");
        btnEdit.setText("Edit");
        btnTambah.requestFocus();

    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        if (btnTambah.getText().equals("Tambah")) {
            btnTambah.setText("Simpan");
            btnTambah.setEnabled(true);
            btnEdit.setEnabled(false);
            btnHapus.setEnabled(false);
            btnCari.setEnabled(false);
            btnBatal.setEnabled(true);
            btnKeluar.setEnabled(false);
            namaF.requestFocus();

        } else if (btnTambah.getText().equals("Simpan")) {
            if (!idPasienF.getText().equals("") && !namaF.getText().equals("")
                    && tipeC.getSelectedIndex() > -1 && !datangF.getText().equals("")
                    && !bayarFF.getText().equals("")) {
                
                String query = "";
                
                if("".equals(cekupFF.getText())){
                    query = "insert into `hospital` set "
                            + " idpasien='" + idPasienF.getText() + "',"
                            + " nama='" + namaF.getText() + "',"
                            + " tgldatang='" + datangF.getText() + "',"
                            + " bayar=" + bayarFF.getValue().toString() + ","
                            + " tipe='" + tipeC.getSelectedItem().toString() + "',"
                            + " cekup=' ',"
                            + " tglpulang='" + pulangFF.getText() + "'";
                }else if("".equals(pulangFF.getText())){
                    query = "insert into `hospital` set "
                        + " idpasien='" + idPasienF.getText() + "',"
                        + " nama='" + namaF.getText() + "',"
                        + " tgldatang='" + datangF.getText() + "',"
                        + " bayar=" + bayarFF.getValue().toString() + ","
                        + " tipe='" + tipeC.getSelectedItem().toString() + "',"
                        + " cekup='"+ cekupFF.getText() +"',"
                        + " tglpulang=''";
                }
                try {
                    st = (Statement) conn.createStatement();
                    st.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Berhasil Menyimpan Data Pasien", "Sukses",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, "Gagal Menyimpan Data Pasien", "Gagal",
                            JOptionPane.ERROR_MESSAGE);
                    System.out.println("Error Simpan =" + e);
                }

                hapus();
                tampil();
                btnTambah.setEnabled(true);
                btnEdit.setEnabled(false);
                btnHapus.setEnabled(false);
                btnCari.setEnabled(true);
                btnBatal.setEnabled(true);
                btnKeluar.setEnabled(true);
                btnTambah.requestFocus();
                btnTambah.setText("Tambah");

            } else {
                JOptionPane.showMessageDialog(null, "Gagal Menyimpan Data Karena Data Belum Lengkap", "Gagal",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        String cari = JOptionPane.showInputDialog("Pencarian Berdasarkan "
                + "ID dan Nama Pasien :");
        if (cari != null) {
            String query = "select * from `hospital` where `idpasien` like '%" + cari.replace("'", "\\'") + "%' or `nama` like '%" + cari.replace("'", "\\'") + "%' order by `idpasien` ASC";
            try {
                model.fireTableDataChanged();
                model.getDataVector().removeAllElements();
                st = (Statement) conn.createStatement();
                rs = st.executeQuery(query);
                int no = 1;
                while (rs.next()) {
                    Object[] o = new Object[]{
                        no++,
                       rs.getString("idpasien"),
                       rs.getString("nama"),
                       rs.getString("tgldatang"),
                       rs.getInt("bayar"),
                       rs.getString("tipe"),
                       rs.getString("cekup"),
                       rs.getString("tglpulang")
                    };
                    model.addRow(o);
                }
            } catch (Exception e) {
                System.out.println("Error Pencarian :" + e);
            }
            btnBatal.setEnabled(true);
        }

    }//GEN-LAST:event_btnCariActionPerformed

    private void tipeCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipeCActionPerformed
        // TODO add your handling code here:
        if(tipeC.getSelectedItem().equals("Inap")){
            cekupFF.setEnabled(false);
            pulangFF.setEnabled(true);
        }else if(tipeC.getSelectedItem().equals("Pulang")){
            cekupFF.setEnabled(true);
            pulangFF.setEnabled(false);
        }else{
            cekupFF.setEnabled(false);
            pulangFF.setEnabled(false);
        }
    }//GEN-LAST:event_tipeCActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        int pesan = JOptionPane.showConfirmDialog(null, "Anda Yakin Akan Menghapus Data Ini ?", "Question", JOptionPane.OK_CANCEL_OPTION);
        if (pesan == 0) {
            int jumlah = 0;
            String query = "select count(idpasien) from hospital where idpasien='" + idPasienF.getText() + "'";
            try {
                st = (Statement) conn.createStatement();
                rs = st.executeQuery(query);

                while (rs.next()) {
                    jumlah = rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println("Error pada hapus kode "
                        + " =  " + e);
            }
            //tinggal tambahkan tabel relasi lain jika ada
//            if (jumlah == 0) {
                query = "delete from hospital where idpasien='" + idPasienF.getText() + "'";
                try {
                    st = (Statement) conn.createStatement();
                    st.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data Pasien", "Sukses",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, "Gagal Menghapus Data Pasien", "Gagal",
                            JOptionPane.ERROR_MESSAGE);
                }
//            } else {
//                JOptionPane.showMessageDialog(null, "Gagal Menghapus Data Karena Data Telah Menjadi Referensi Tabel Lain", "Gagal",
//                        JOptionPane.WARNING_MESSAGE);
//            }
            hapus();
            tampil();
            btnTambah.setEnabled(true);
            btnEdit.setEnabled(false);
            btnHapus.setEnabled(false);
            btnCari.setEnabled(true);
            btnBatal.setEnabled(true);
            btnKeluar.setEnabled(true);
            btnTambah.requestFocus();

        }

    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        tampil();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void rusakTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rusakTblMouseClicked
        // TODO add your handling code here:
        tabelMouseClicked();
    }//GEN-LAST:event_rusakTblMouseClicked

    private void rusakTblFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rusakTblFocusGained
        // TODO add your handling code here:
        tabelMouseClicked();
    }//GEN-LAST:event_rusakTblFocusGained

    private void cekupFFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekupFFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekupFFActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (btnEdit.getText().equals("Edit")) {
            btnEdit.setText("Update");
            btnTambah.setEnabled(false);
            btnEdit.setEnabled(true);
            btnHapus.setEnabled(false);
            btnCari.setEnabled(false);
            btnBatal.setEnabled(true);
            btnKeluar.setEnabled(false);
            namaF.requestFocus();
            idPasienF.setEnabled(false);

        } else if (btnEdit.getText().equals("Update")) {
            if (!idPasienF.getText().equals("") && !namaF.getText().equals("")
                    && tipeC.getSelectedIndex() > -1 && !datangF.getText().equals("")
                    && !bayarFF.getText().equals("")) {

               String query = "";
                
                if("".equals(cekupFF.getText())){
                    query = "update `hospital` set "
                        + " nama='" + namaF.getText() + "',"
                        + " tgldatang='" + datangF.getText() + "',"
                        + " bayar=" + bayarFF.getValue().toString() + ","
                        + " tipe='" + tipeC.getSelectedItem().toString() + "',"
                        + " tglpulang='" + pulangFF.getText() + "'"
                        + " where `idpasien`='" + idPasienF.getText() + "'";
                }else if("".equals(pulangFF.getText())){
                    query = "update `hospital` set "
                        + " `nama`='" + namaF.getText() + "',"
                        + " `tgldatang`='" + datangF.getText() + "',"
                        + " `bayar`=" + bayarFF.getValue().toString() + ","
                        + " `tipe`='" + tipeC.getSelectedItem().toString() + "',"
                        + " `cekup`='"+ cekupFF.getText() +"' "
                        + " where `idpasien`='" + idPasienF.getText() + "'";
                }
                try {
                    st = (Statement) conn.createStatement();
                    st.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Berhasil Merubah Data Pasien", "Sukses",
                            JOptionPane.INFORMATION_MESSAGE);
                    hapus();
                    tampil();
                    btnTambah.setEnabled(true);
                    btnEdit.setEnabled(false);
                    btnHapus.setEnabled(false);
                    btnCari.setEnabled(true);
                    btnBatal.setEnabled(true);
                    btnKeluar.setEnabled(true);
                    btnTambah.requestFocus();
                    btnEdit.setText("Edit");
                    idPasienF.setEnabled(true);
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, "Gagal Merubah Data Pasien", "Gagal",
                            JOptionPane.ERROR_MESSAGE);
                    System.out.println("Error Update =" + e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Gagal Mengubah Data Karena Data Belum Lengkap", "Gagal",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnEditActionPerformed

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
            java.util.logging.Logger.getLogger(RusakFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RusakFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RusakFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RusakFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RusakFrame rf = new RusakFrame();
                rf.setTitle("Data Pasien Rumah Sakit Jl. Sehat");
                rf.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField bayarFF;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnTambah;
    private javax.swing.JTextField cekupFF;
    private javax.swing.JTextField datangF;
    private javax.swing.JTextField idPasienF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField namaF;
    private javax.swing.JTextField pulangFF;
    private javax.swing.JTable rusakTbl;
    private javax.swing.JComboBox<String> tipeC;
    // End of variables declaration//GEN-END:variables
}
