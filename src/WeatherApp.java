/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WeatherApp extends javax.swing.JFrame {

    private final String API_KEY = "MASUKKAN_API_KEY_ANDA";
    private final String API_URL = "http://api.openweathermap.org/data/2.5/weather";

    private DefaultTableModel tableModel;
    private ArrayList<String> daftarFavorit = new ArrayList<>();

    private String FILE_FAVORIT = "favorit.txt";
    private String FILE_RIWAYAT = "riwayat.csv";

    // ====== Tambahan penghubung komponen GUI ======
    private JTextField txtKota;
    private JLabel lblNamaKota, lblSuhu, lblDeskripsi, lblKelembaban, lblKecepatan, lblGambarCuaca;

    public WeatherApp() {
        initComponents();

        // Hubungkan GUI ke variabel logika
        txtKota = txtKo;
        lblNamaKota = jLabel7;
        lblSuhu = jLabel8;
        lblDeskripsi = jLabel9;
        lblKelembaban = jLabel10;
        lblKecepatan = jLabel11;
        lblGambarCuaca = jLabel6;

        setupTable();
        loadFavorit();
    }

    private void setupTable() {
        String[] columnNames = {"Waktu", "Kota", "Suhu (°C)", "Cuaca", "Kelembaban (%)"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JtblRiwayat.setModel(tableModel);
    }

    private void loadFavorit() {
        cmbFavorit.removeAllItems();
        cmbFavorit.addItem("-- Pilih Kota --");

        try {
            File file = new File(FILE_FAVORIT);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    daftarFavorit.add(line);
                    cmbFavorit.addItem(line);
                }
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getWeatherData(String city) {
        try {
            String urlString = API_URL + "?q=" + URLEncoder.encode(city, "UTF-8")
                    + "&appid=" + API_KEY + "&units=metric&lang=id";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return (JSONObject) new JSONParser().parse(response.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            return null;
        }
    }

    private void displayWeatherData(JSONObject data) {
        try {
            String cityName = (String) data.get("name");

            JSONObject main = (JSONObject) data.get("main");
            JSONObject weather = (JSONObject) ((org.json.simple.JSONArray) data.get("weather")).get(0);
            JSONObject wind = (JSONObject) data.get("wind");

            double temp = ((Number) main.get("temp")).doubleValue();
            long humidity = (Long) main.get("humidity");
            String description = (String) weather.get("description");
            String icon = (String) weather.get("icon");

            double windSpeed = ((Number) wind.get("speed")).doubleValue();

            lblNamaKota.setText("Kota: " + cityName);
            lblSuhu.setText("Suhu: " + temp + "°C");
            lblDeskripsi.setText("Cuaca: " + description);
            lblKelembaban.setText("Kelembaban: " + humidity + "%");
            lblKecepatan.setText("Kecepatan Angin: " + windSpeed + " m/s");

            loadWeatherIcon(icon);
            addToTable(cityName, temp, description, humidity);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error parsing data: " + e.getMessage());
        }
    }

    private void loadWeatherIcon(String iconCode) {
        try {
            String iconUrl = "http://openweathermap.org/img/wn/" + iconCode + "@2x.png";
            ImageIcon icon = new ImageIcon(new URL(iconUrl));
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

            lblGambarCuaca.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblGambarCuaca.setText("No Icon");
        }
    }

    private void addToTable(String city, double temp, String weather, long humidity) {
        String timestamp = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        tableModel.addRow(new Object[]{timestamp, city, temp, weather, humidity});
    }

    private void saveFavorit() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_FAVORIT));
            for (String city : daftarFavorit) {
                bw.write(city);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
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
        txtKo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnCekCuaca = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmbFavorit = new javax.swing.JComboBox<>();
        btnTambahFavorit = new javax.swing.JButton();
        btnHapusFavorit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tblRiwayat = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JtblRiwayat = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        btnSimpanCSV = new javax.swing.JButton();
        btnMuatData = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("        Aplikasi Cek Cuaca Sederhana");

        jLabel2.setText(" Masukkan Nama Kota:");

        jLabel3.setText("Nama Kota");

        btnCekCuaca.setText("CekCuaca");
        btnCekCuaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekCuacaActionPerformed(evt);
            }
        });

        jLabel4.setText("Atau Pilih dari Favorit :");

        cmbFavorit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnTambahFavorit.setText("tambah Favorit");
        btnTambahFavorit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahFavoritActionPerformed(evt);
            }
        });

        btnHapusFavorit.setText("Hapus Favorit");
        btnHapusFavorit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusFavoritActionPerformed(evt);
            }
        });

        jLabel5.setText("            Hasil Cuaca");

        jLabel6.setText("lblGambarCuaca");

        jLabel7.setText("lblNamaKota");
        jLabel7.setToolTipText("");

        jLabel8.setText("lblSuhu");

        jLabel9.setText("lblDeskripsi");

        jLabel10.setText("lblKelembapan");

        jLabel11.setText("lblKecAngin");

        tblRiwayat.setText("Riwayat Pencarian");
        tblRiwayat.setToolTipText("");

        JtblRiwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Waktu", "kota", "Suhu ", "Cuaca", "Kelembapan"
            }
        ));
        jScrollPane1.setViewportView(JtblRiwayat);

        btnSimpanCSV.setText("Simpan Ke CSV");
        btnSimpanCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanCSVActionPerformed(evt);
            }
        });

        btnMuatData.setText("Muat Data");
        btnMuatData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuatDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(6, 6, 6)
                                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addComponent(tblRiwayat, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(122, 122, 122))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtKo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnCekCuaca, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbFavorit, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTambahFavorit)
                                .addGap(18, 18, 18)
                                .addComponent(btnHapusFavorit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnSimpanCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btnMuatData, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtKo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCekCuaca))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbFavorit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambahFavorit)
                    .addComponent(btnHapusFavorit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(tblRiwayat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanCSV)
                    .addComponent(btnMuatData))
                .addGap(133, 133, 133))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCekCuacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekCuacaActionPerformed
        String city = txtKota.getText().trim();
        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan nama kota terlebih dahulu!");
            return;
        }
        
        JSONObject data = getWeatherData(city);
        if (data != null) {
            displayWeatherData(data);
        }
    }//GEN-LAST:event_btnCekCuacaActionPerformed

    private void btnTambahFavoritActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahFavoritActionPerformed
        String city = txtKota.getText().trim();
        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan nama kota!");
            return;
        }
        
        if (daftarFavorit == null) daftarFavorit = new ArrayList<>();
        
        if (daftarFavorit.contains(city)) {
            JOptionPane.showMessageDialog(this, "Kota sudah ada di favorit!");
            return;
        }
        
        daftarFavorit.add(city);
        cmbFavorit.addItem(city);
        saveFavorit();
        JOptionPane.showMessageDialog(this, "Kota berhasil ditambahkan ke favorit!");
    }//GEN-LAST:event_btnTambahFavoritActionPerformed

    private void btnHapusFavoritActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusFavoritActionPerformed
        String selected = (String) cmbFavorit.getSelectedItem();
        if (selected == null || selected.equals("-- Pilih Kota --")) {
            JOptionPane.showMessageDialog(this, "Pilih kota dari favorit!");
            return;
        }
        
        daftarFavorit.remove(selected);
        cmbFavorit.removeItem(selected);
        saveFavorit();
        JOptionPane.showMessageDialog(this, "Kota dihapus dari favorit!");
    }//GEN-LAST:event_btnHapusFavoritActionPerformed

    private void btnSimpanCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanCSVActionPerformed
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_RIWAYAT));
            
            // Header
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                bw.write(tableModel.getColumnName(i));
                if (i < tableModel.getColumnCount() - 1) bw.write(",");
            }
            bw.newLine();
            
            // Data
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    bw.write(tableModel.getValueAt(i, j).toString());
                    if (j < tableModel.getColumnCount() - 1) bw.write(",");
                }
                bw.newLine();
            }
            
            bw.close();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan ke " + FILE_RIWAYAT);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error menyimpan file: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSimpanCSVActionPerformed

    private void btnMuatDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuatDataActionPerformed
        try {
            File file = new File(FILE_RIWAYAT);
            if (!file.exists()) {
                JOptionPane.showMessageDialog(this, "File riwayat tidak ditemukan!");
                return;
            }
            
            tableModel.setRowCount(0); // Clear table
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            br.readLine(); // Skip header
            
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
            
            br.close();
            JOptionPane.showMessageDialog(this, "Data berhasil dimuat!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error memuat file: " + e.getMessage());
        }
    }//GEN-LAST:event_btnMuatDataActionPerformed

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
            java.util.logging.Logger.getLogger(WeatherApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WeatherApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WeatherApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WeatherApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WeatherApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JtblRiwayat;
    private javax.swing.JButton btnCekCuaca;
    private javax.swing.JButton btnHapusFavorit;
    private javax.swing.JButton btnMuatData;
    private javax.swing.JButton btnSimpanCSV;
    private javax.swing.JButton btnTambahFavorit;
    private javax.swing.JComboBox<String> cmbFavorit;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel tblRiwayat;
    private javax.swing.JTextField txtKo;
    // End of variables declaration//GEN-END:variables
}