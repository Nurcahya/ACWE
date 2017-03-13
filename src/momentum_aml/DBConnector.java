/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package momentum_aml;

import java.sql.*;
import javax.swing.JOptionPane;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author se7en
 */
public class DBConnector {

    public static void connectDB() {
        Connection con = null;
        Statement exe = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();
        } catch (ClassNotFoundException cnf) {
            JOptionPane.showMessageDialog(null, "Driver Java:MySQL tidak ditemukan", "Driver Error", JOptionPane.WARNING_MESSAGE);
            System.exit(1);
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Database tidak bisa ditemukan", "Database Error", JOptionPane.WARNING_MESSAGE);
            System.exit(1);
        }
    }

    public static String dataHasil() {
        Connection con = null;
        Statement exe = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        String data = "";
        try {
            String sql = "SELECT * FROM t_uji";
            ResultSet rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("epoch") + "$*$"
                        + rs.getString("hid_layer") + "$*$"
                        + rs.getString("learn_rate") + "$*$"
                        + rs.getString("momentum") + "$*$"
                        + rs.getString("waktu") + "$*$"
                        + rs.getString("presisi") + "$*$"
                        + rs.getString("sensitifitas") + "$*$"
                        + rs.getString("spesifisitas") + "$*$"
                        + rs.getString("akurasi") + "$*$";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String dataLatih() {
        Connection con = null;
        Statement exe = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // String data = "jdbc:mysql://"+ServerForm.IP+ ":3306/"+ServerForm.DB;
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        String data = "";
        try {
            String sql = "SELECT * FROM t_citra where isused='1' order by jenis, nama_citra asc ";
            ResultSet rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("nama_objek") + "$*$"
                        + rs.getString("nama_citra") + "$*$"
                        + rs.getString("area") + "$*$"
                        + rs.getString("tepi") + "$*$"
                        + rs.getString("kebundaran") + "$*$"
                        + rs.getString("rasio") + "$*$"
                        + rs.getString("mean") + "$*$"
                        + rs.getString("st_dev") + "$*$"
                        + rs.getString("jenis") + "$*$"
                        + rs.getString("tipe_sel") + "$*$";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String dataLatihNorm() {
        Connection con = null;
        Statement exe = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // String data = "jdbc:mysql://"+ServerForm.IP+ ":3306/"+ServerForm.DB;
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        String data = "";
        try {
            String sql = "SELECT * FROM t_citra_norm order by jenis, nama_citra asc";
            ResultSet rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("nama_objek") + "$*$"
                        + rs.getString("nama_citra") + "$*$"
                        + rs.getString("area") + "$*$"
                        + rs.getString("tepi") + "$*$"
                        + rs.getString("kebundaran") + "$*$"
                        + rs.getString("rasio") + "$*$"
                        + rs.getString("mean") + "$*$"
                        + rs.getString("st_dev") + "$*$"
                        + rs.getString("jenis") + "$*$"
                        + rs.getString("tipe_sel") + "$*$";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String dataLatihLatih() {
        Connection con = null;
        Statement exe = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // String data = "jdbc:mysql://"+ServerForm.IP+ ":3306/"+ServerForm.DB;
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        String data = "";
        try {
            String sql = "SELECT * FROM t_citra_norm order by jenis, nama_citra asc";
            ResultSet rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("area") + "$*$"
                        + rs.getString("tepi") + "$*$"
                        + rs.getString("kebundaran") + "$*$"
                        + rs.getString("rasio") + "$*$"
                        + rs.getString("mean") + "$*$"
                        + rs.getString("st_dev") + "$*$"
                        + rs.getString("myeloblast") + "$*$"
                        + rs.getString("promyelosit") + "$*$"
                        + rs.getString("monosit") + "$*$"
                        + rs.getString("non-blast") + "$*$"
                        + rs.getString("jenis") + "$*$"
                        + rs.getString("nama_objek") + "$*$"
                        + rs.getString("nama_citra") + "$*$"
                        + rs.getString("fold") + "$*$"
                        + rs.getString("id_objek") + "$*$"
                        + rs.getString("tipe_sel") + "$*$";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String dataUji() {
        Connection con = null;
        Statement exe = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // String data = "jdbc:mysql://"+ServerForm.IP+ ":3306/"+ServerForm.DB;
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        String data = "";
        try {
            String sql = "SELECT * FROM t_citra_norm order by id_objek asc";
            ResultSet rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("nama_objek") + "$*$"
                        + rs.getString("nama_citra") + "$*$"
                        + rs.getString("area") + "$*$"
                        + rs.getString("tepi") + "$*$"
                        + rs.getString("kebundaran") + "$*$"
                        + rs.getString("rasio") + "$*$"
                        + rs.getString("mean") + "$*$"
                        + rs.getString("st_dev") + "$*$"
                        + rs.getString("tipe_sel") + "$*$"
                        + rs.getString("fold") + "$*$";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String dataUjiHasil() {
        Connection con = null;
        Statement exe = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // String data = "jdbc:mysql://"+ServerForm.IP+ ":3306/"+ServerForm.DB;
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        String data = "";
        try {
            String sql = "SELECT * FROM `t_hasil` inner join t_citra_norm using(id_objek)";
            ResultSet rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("nama_objek") + "$*$"
                        + rs.getString("nama_citra") + "$*$"
                        + rs.getString("area") + "$*$"
                        + rs.getString("tepi") + "$*$"
                        + rs.getString("kebundaran") + "$*$"
                        + rs.getString("rasio") + "$*$"
                        + rs.getString("mean") + "$*$"
                        + rs.getString("st_dev") + "$*$"
                        + rs.getString("tipe_sel") + "$*$"
                        + rs.getString("fold") + "$*$"
                        + rs.getString("hasil") + "$*$";
                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String dataUjiUji() {
        Connection con = null;
        Statement exe = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        String data = "";
        try {
            String sql = "SELECT * FROM pengujian order by Jenis, Nama asc";
            ResultSet rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("N_luas") + "$*$"
                        + rs.getString("N_tepi") + "$*$"
                        + rs.getString("Kebundaran") + "$*$"
                        + rs.getString("Rasio") + "$*$"
                        + rs.getString("Neutrofil") + "$*$"
                        + rs.getString("Eosinofil") + "$*$"
                        + rs.getString("Basofil") + "$*$"
                        + rs.getString("Limfosit") + "$*$"
                        + rs.getString("Monosit") + "$*$"
                        + rs.getString("Jenis") + "$*$"
                        + rs.getString("Nama") + "$*$";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void normalisasiLatih() throws SQLException {
        Connection con = null;
        Statement exe = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean berhasilupdate = false;
        try {

            String sql = "SELECT MAX(CAST(area as DECIMAL(10,4))), MIN(CAST(area as DECIMAL(10,4))), MAX(CAST(tepi as DECIMAL(10,4))), MIN(CAST(tepi as DECIMAL(10,4))), MAX(CAST(kebundaran as DECIMAL(10,4))), MIN(CAST(kebundaran as DECIMAL(10,4))), MAX(CAST(rasio as DECIMAL(10,4))), MIN(CAST(rasio as DECIMAL(10,4))) , MAX(CAST(mean as DECIMAL(10,4))), MIN(CAST(mean as DECIMAL(10,4))) , MAX(CAST(st_dev as DECIMAL(10,4))), MIN(CAST(st_dev as DECIMAL(10,4))) FROM t_citra where isused='1'";
            ResultSet rs = exe.executeQuery(sql);
            while (rs.next()) {
                double maxarea = rs.getDouble("MAX(CAST(area as DECIMAL(10,4)))");
                double minarea = rs.getDouble("MIN(CAST(area as DECIMAL(10,4)))");
                double maxtepi = rs.getDouble("MAX(CAST(tepi as DECIMAL(10,4)))");
                double mintepi = rs.getDouble("MIN(CAST(tepi as DECIMAL(10,4)))");
                double maxkebundaran = rs.getDouble("MAX(CAST(kebundaran as DECIMAL(10,4)))");
                double minkebundaran = rs.getDouble("MIN(CAST(kebundaran as DECIMAL(10,4)))");
                double maxrasio = rs.getDouble("MAX(CAST(rasio as DECIMAL(10,4)))");
                double minrasio = rs.getDouble("MIN(CAST(rasio as DECIMAL(10,4)))");
                double maxmean = rs.getDouble("MAX(CAST(mean as DECIMAL(10,4)))");
                double minmean = rs.getDouble("MIN(CAST(mean as DECIMAL(10,4)))");
                double maxstdev = rs.getDouble("MAX(CAST(st_dev as DECIMAL(10,4)))");
                double minstdev = rs.getDouble("MIN(CAST(st_dev as DECIMAL(10,4)))");
                System.out.println("Data luas dan tepi dari tabel 'latih' akan dinormalisasi!");

                String sql2 = "DELETE from t_citra_norm WHERE 1";
                pstmt = con.prepareStatement(sql2);
                pstmt.executeUpdate();
                System.out.println("maks luas = " + maxarea);
                System.out.println("min luas = " + minarea);
                System.out.println("maks tepi = " + maxtepi);
                System.out.println("min tepi = " + mintepi);
                System.out.println("max kebundaran = " + maxkebundaran);
                System.out.println("min kebundaran = " + minkebundaran);
                System.out.println("max rasio = " + maxrasio);
                System.out.println("min rasio = " + minrasio);
                System.out.println("max mean = " + maxmean);
                System.out.println("min mean = " + minmean);
                System.out.println("max standar deviasi = " + maxstdev);
                System.out.println("min standar deviasi = " + minstdev);

                String sql3 = "SELECT * FROM t_citra where isused='1'";
                ResultSet rs3 = exe.executeQuery(sql3);
                while (rs3.next()) {
//                int nomor = rs2.getInt("id_objek");   
                    char tipe1 = '0';
                    char tipe2 = '0';
                    char tipe3 = '0';
                    char tipe4 = '0';
                    String nama_objek = rs3.getString("nama_objek");
                    String nama_citra = rs3.getString("nama_citra");
                    double normarea = (rs3.getDouble("area") - minarea) / (maxarea - minarea);
                    double normtepi = (rs3.getDouble("tepi") - mintepi) / (maxtepi - mintepi);
                    double normkebundaran = (rs3.getDouble("kebundaran") - minkebundaran) / (maxkebundaran - minkebundaran);
                    double normrasio = (rs3.getDouble("rasio") - minrasio) / (maxrasio - minrasio);
                    double normmean = (rs3.getDouble("mean") - minmean) / (maxmean - minmean);
                    double normstdev = (rs3.getDouble("st_dev") - minstdev) / (maxstdev - minstdev);
                    String jenis = rs3.getString("jenis");
                    String tipe = rs3.getString("tipe_sel");
                    if (tipe.equals("myeloblast")) {
                        tipe1 = '1';
                    } else if (tipe.equals("promyelosit")) {
                        tipe2 = '1';
                    } else if (tipe.equals("monosit")) {
                        tipe3 = '1';
                    } else if (tipe.equals("non-blast")) {
                        tipe4 = '1';
                    }

                    int fold = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                    String sql4 = "INSERT INTO t_citra_norm (`nama_objek`, `nama_citra`, `area`, `tepi`, `kebundaran`, `rasio`, `mean`, `st_dev`, `jenis`, `myeloblast`, `promyelosit`, `monosit`, `non-blast`, `fold`, `tipe_sel`)  VALUES('"
                            + nama_objek + "','" + nama_citra + "','" + normarea + "','" + normtepi + "','" + normkebundaran + "','" + normrasio + "','" + normmean + "','" + normstdev + "','" + jenis + "','" + tipe1 + "','" + tipe2 + "','" + tipe3 + "','" + tipe4 + "','" + fold + "','" + tipe + "')";

                    pstmt = con.prepareStatement(sql4);
                    pstmt.executeUpdate();
                }
            }
            berhasilupdate = true;
        } catch (SQLException ex) {
            berhasilupdate = false;
        }
    }

    public static void updateUji(String id_objek, String jenis, String jenis_testing) throws SQLException {
        Connection con = null;
        Statement exe = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean berhasilupdate = false;
        try {
            String sql = "SELECT max(id_uji) as id_uji FROM `t_uji`";
            ResultSet rs = exe.executeQuery(sql);
            while (rs.next()) {
                int iduji = rs.getInt("id_uji") + 1;
                String sql2 = "INSERT INTO `thesis_ugm`.`t_hasil` (`id_objek`, `id_uji`, `tipe_sel`, `hasil`) VALUES ('" + id_objek + "', '" + Integer.toString(iduji) + "', '" + jenis + "', '" + jenis_testing + "')";
                pstmt = con.prepareStatement(sql2);
                pstmt.executeUpdate();
                exe.executeQuery(sql2);
            }
            berhasilupdate = true;
        } catch (SQLException ex) {
            berhasilupdate = false;
        } finally {
            pstmt.close();
            con.close();
        }
    }

    public static void insertHasil(String epoch, String learning_rate, String hidden_layer, String momentum, String MSE, String waktu, String presisi, String sensitivitas, String spesifisitas, String akurasi) throws SQLException {
        Connection con = null;
        Statement exe = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean berhasilinsert = false;
        try {
            String sql = "INSERT INTO `thesis_ugm`.`t_uji` (`id_uji`, `epoch`, `learn_rate`, `hid_layer`, `momentum`, `MSE`, `waktu`, `presisi`, `sensitifitas`, `spesifisitas`, `akurasi`) VALUES (NULL, '" + epoch + "', '" + learning_rate + "', '" + hidden_layer + "', '" + momentum + "', '" + MSE + "', '" + waktu + "', '" + presisi + "', '" + sensitivitas + "', '" + spesifisitas + "', '" + akurasi + "')";
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
            exe.executeQuery(sql);
            berhasilinsert = true;
        } catch (SQLException ex) {
            berhasilinsert = false;
        }
    }

    public static void insertKelasHasilMyel(double presisi, double sensitivitas, double spesifisitas, double akurasi) throws SQLException {
        Connection con = null;
        Statement exe = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean berhasilinsert = false;
        boolean berhasilupdate = false;
        try {
            String sql = "SELECT max(id_uji) as id_uji FROM `t_uji`";
            ResultSet rs = exe.executeQuery(sql);
            while (rs.next()) {
                int id_uji = rs.getInt("id_uji");
                String sql1 = "INSERT INTO `thesis_ugm`.`t_pa_myel` (`id_PA`, `id_uji`, `presisi`, `sensitivitas`, `spesifisitas`, `akurasi`) VALUES (NULL, '" + id_uji + "', '" + presisi + "', '" + sensitivitas + "', '" + spesifisitas + "', '" + akurasi + "')";
                pstmt = con.prepareStatement(sql1);
                pstmt.executeUpdate();
                exe.executeQuery(sql1);
                berhasilinsert = true;
            }
            berhasilupdate = true;
        } catch (SQLException ex) {
            berhasilinsert = false;
        } finally {
            pstmt.close();
            con.close();
        }
    }

    public static void insertKelasHasilPro(double presisi, double sensitivitas, double spesifisitas, double akurasi) throws SQLException {
        Connection con = null;
        Statement exe = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean berhasilinsert = false;
        boolean berhasilupdate = false;
        try {
            String sql = "SELECT max(id_uji) as id_uji FROM `t_uji`";
            ResultSet rs = exe.executeQuery(sql);
            while (rs.next()) {
                int id_uji = rs.getInt("id_uji");
                String sql1 = "INSERT INTO `thesis_ugm`.`t_pa_pro` (`id_PA`, `id_uji`, `presisi`, `sensitivitas`, `spesifisitas`, `akurasi`) VALUES (NULL, '" + id_uji + "', '" + presisi + "', '" + sensitivitas + "', '" + spesifisitas + "', '" + akurasi + "')";
                pstmt = con.prepareStatement(sql1);
                pstmt.executeUpdate();
                exe.executeQuery(sql1);
                berhasilinsert = true;
            }
            berhasilupdate = true;
        } catch (SQLException ex) {
            berhasilinsert = false;
        } finally {
            pstmt.close();
            con.close();
        }
    }

    public static void insertKelasHasilMono(double presisi, double sensitivitas, double spesifisitas, double akurasi) throws SQLException {
        Connection con = null;
        Statement exe = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean berhasilinsert = false;
        boolean berhasilupdate = false;
        try {
            String sql = "SELECT max(id_uji) as id_uji FROM `t_uji`";
            ResultSet rs = exe.executeQuery(sql);
            while (rs.next()) {
                int id_uji = rs.getInt("id_uji");
                String sql1 = "INSERT INTO `thesis_ugm`.`t_pa_mono` (`id_PA`, `id_uji`, `presisi`, `sensitivitas`, `spesifisitas`, `akurasi`) VALUES (NULL, '" + id_uji + "', '" + presisi + "', '" + sensitivitas + "', '" + spesifisitas + "', '" + akurasi + "')";
                pstmt = con.prepareStatement(sql1);
                pstmt.executeUpdate();
                exe.executeQuery(sql1);
                berhasilinsert = true;
            }
            berhasilupdate = true;
        } catch (SQLException ex) {
            berhasilinsert = false;
        } finally {
            pstmt.close();
            con.close();
        }
    }
    public static void insertKelasHasilNon(double presisi, double sensitivitas, double spesifisitas, double akurasi) throws SQLException {
        Connection con = null;
        Statement exe = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean berhasilinsert = false;
        boolean berhasilupdate = false;
        try {
            String sql = "SELECT max(id_uji) as id_uji FROM `t_uji`";
            ResultSet rs = exe.executeQuery(sql);
            while (rs.next()) {
                int id_uji = rs.getInt("id_uji");
                String sql1 = "INSERT INTO `thesis_ugm`.`t_pa_non` (`id_PA`, `id_uji`, `presisi`, `sensitivitas`, `spesifisitas`, `akurasi`) VALUES (NULL, '" + id_uji + "', '" + presisi + "', '" + sensitivitas + "', '" + spesifisitas + "', '" + akurasi + "')";
                pstmt = con.prepareStatement(sql1);
                pstmt.executeUpdate();
                exe.executeQuery(sql1);
                berhasilinsert = true;
            }
            berhasilupdate = true;
        } catch (SQLException ex) {
            berhasilinsert = false;
        } finally {
            pstmt.close();
            con.close();
        }
    }
    
       public static String matrix(String id) {
        Connection con = null;
        Statement exe = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/thesis_ugm";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        String data = "";
        try {
            String sql = "SELECT count(tipe_sel) as A from t_hasil where tipe_sel='myeloblast' and hasil='myeloblast' and id_uji='"+id+"'";
            ResultSet rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("A") + "$*$";
            }
            sql = "SELECT count(tipe_sel) as B from t_hasil where tipe_sel='myeloblast' and hasil='promyelosit' and id_uji='"+id+"'";
            rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("B") + "$*$";
            }
            sql = "SELECT count(tipe_sel) as C from t_hasil where tipe_sel='myeloblast' and hasil='monosit' and id_uji='"+id+"'";
            rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("C") + "$*$";
            }
            sql = "SELECT count(tipe_sel) as D from t_hasil where tipe_sel='myeloblast' and hasil='non-blast' and id_uji='"+id+"'";
            rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("D") + "$*$";
            }
            sql = "SELECT count(tipe_sel) as E from t_hasil where tipe_sel='promyelosit' and hasil='myeloblast' and id_uji='"+id+"'";
            rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("E") + "$*$";
            }
            sql = "SELECT count(tipe_sel) as F from t_hasil where tipe_sel='promyelosit' and hasil='promyelosit' and id_uji='"+id+"'";
            rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("F") + "$*$";
            }
            sql = "SELECT count(tipe_sel) as G from t_hasil where tipe_sel='promyelosit' and hasil='monosit' and id_uji='"+id+"'";
            rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("G") + "$*$";
            }
            sql = "SELECT count(tipe_sel) as H from t_hasil where tipe_sel='promyelosit' and hasil='non-blast' and id_uji='"+id+"'";
            rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("H") + "$*$";
            }
            sql = "SELECT count(tipe_sel) as I from t_hasil where tipe_sel='monosit' and hasil='myeloblast' and id_uji='"+id+"'";
            rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("I") + "$*$";
            }
            sql = "SELECT count(tipe_sel) as J from t_hasil where tipe_sel='monosit' and hasil='promyelosit' and id_uji='"+id+"'";
            rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("J") + "$*$";
            }
            sql = "SELECT count(tipe_sel) as K from t_hasil where tipe_sel='monosit' and hasil='monosit' and id_uji='"+id+"'";
            rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("K") + "$*$";
            }
            sql = "SELECT count(tipe_sel) as L from t_hasil where tipe_sel='monosit' and hasil='non-blast' and id_uji='"+id+"'";
            rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("L") + "$*$";
            }
            sql = "SELECT count(tipe_sel) as M from t_hasil where tipe_sel='non-blast' and hasil='myeloblast' and id_uji='"+id+"'";
            rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("M") + "$*$";
            }
            sql = "SELECT count(tipe_sel) as N from t_hasil where tipe_sel='non-blast' and hasil='promyelosit' and id_uji='"+id+"'";
            rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("N") + "$*$";
            }
            sql = "SELECT count(tipe_sel) as O from t_hasil where tipe_sel='non-blast' and hasil='monosit' and id_uji='"+id+"'";
            rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("O") + "$*$";
            }
            sql = "SELECT count(tipe_sel) as P from t_hasil where tipe_sel='non-blast' and hasil='non-blast' and id_uji='"+id+"'";
            rs = exe.executeQuery(sql);
            while (rs.next()) {
                data = data + rs.getString("P") + "$*$";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    
}
