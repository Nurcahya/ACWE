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
            String data = "jdbc:mysql://localhost:3306/thesis";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();
	} catch (ClassNotFoundException cnf) {
            JOptionPane.showMessageDialog(null,"Driver Java:MySQL tidak ditemukan","Driver Error", JOptionPane.WARNING_MESSAGE);
            System.exit(1);
	} catch (SQLException se) {
            JOptionPane.showMessageDialog(null,"Database tidak bisa ditemukan","Database Error", JOptionPane.WARNING_MESSAGE);
             System.exit(1);
	}
    }
    
       
    public static String dataHasil() {
        Connection con = null;
        Statement exe = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/thesis";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String data = "";
        try {
            String sql = "SELECT * FROM hasil";
            ResultSet rs = exe.executeQuery(sql);
            while (rs.next()) {
                 data = data + rs.getString("epoch") + "$*$"
                        + rs.getString("hidden_layer") + "$*$"
                        + rs.getString("learning_rate") + "$*$"
                        + rs.getString("momentum") + "$*$"
                        + rs.getString("MSE") + "$*$"
                        + rs.getString("waktu") + "$*$"
                        + rs.getString("status") + "$*$"
                        + rs.getString("memorisasi") + "$*$"
                        + rs.getString("generalisasi") + "$*$"
                       ;
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
            String data = "jdbc:mysql://localhost:3306/thesis";
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
                       ;
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
            String data = "jdbc:mysql://localhost:3306/thesis";
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
                       ;
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
            String data = "jdbc:mysql://localhost:3306/thesis";
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
                        + rs.getString("aml1") + "$*$"
                        + rs.getString("aml2") + "$*$"
                        + rs.getString("aml3") + "$*$"   
                        + rs.getString("jenis") + "$*$"                      
                        + rs.getString("nama_objek") + "$*$"
                        + rs.getString("nama_citra") + "$*$"
                        + rs.getString("fold") + "$*$"
                        + rs.getString("id_objek") + "$*$"
                         ;
                 
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
            String data = "jdbc:mysql://localhost:3306/thesis";
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
                        + rs.getString("jenis") + "$*$"
                        + rs.getString("fold") + "$*$"
                       ;
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
            String data = "jdbc:mysql://localhost:3306/thesis";
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
                        + rs.getString("jenis") + "$*$"
                        + rs.getString("fold") + "$*$"
                        + rs.getString("hasil") + "$*$"
                       ;
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
            String data = "jdbc:mysql://localhost:3306/thesis";
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
                        + rs.getString("Nama") + "$*$" 
                       ;
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
            String data = "jdbc:mysql://localhost:3306/thesis";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean berhasilupdate = false;
        try {
            
             String sql = "SELECT MAX(area), MIN(area), MAX(tepi), MIN(tepi), MAX(kebundaran), MIN(kebundaran), MAX(rasio), MIN(rasio) , MAX(mean), MIN(mean) , MAX(st_dev), MIN(st_dev) FROM t_citra where isused='1'";
             ResultSet rs = exe.executeQuery(sql);
               while (rs.next()) {
             double maxarea = rs.getDouble("MAX(area)") ;
             double minarea = rs.getDouble("MIN(area)") ;
             double maxtepi = rs.getDouble("MAX(tepi)") ;
             double mintepi = rs.getDouble("MIN(tepi)") ;
             double maxkebundaran = rs.getDouble("MAX(kebundaran)") ;
             double minkebundaran = rs.getDouble("MIN(kebundaran)") ;
             double maxrasio = rs.getDouble("MAX(rasio)") ;
             double minrasio = rs.getDouble("MIN(rasio)") ;
             double maxmean = rs.getDouble("MAX(mean)") ;
             double minmean = rs.getDouble("MIN(mean)") ;
             double maxstdev = rs.getDouble("MAX(st_dev)") ;
             double minstdev = rs.getDouble("MIN(st_dev)") ;
             System.out.println("Data luas dan tepi dari tabel 'latih' akan dinormalisasi!");
             
            String sql2 =  "DELETE from t_citra_norm WHERE 1";
            pstmt = con.prepareStatement(sql2); 
            pstmt.executeUpdate();
             System.out.println("maks luas = "+maxarea);
             System.out.println("min luas = "+minarea);
             System.out.println("maks tepi = "+maxtepi);
             System.out.println("min tepi = "+mintepi);
             System.out.println("max kebundaran = "+maxkebundaran);
             System.out.println("min kebundaran = "+minkebundaran);
             System.out.println("max rasio = "+maxrasio);
             System.out.println("min rasio = "+minrasio);
             System.out.println("max mean = "+maxmean);
             System.out.println("min mean = "+minmean);
             System.out.println("max standar deviasi = "+maxstdev);
             System.out.println("min standar deviasi = "+minstdev);
                   
            String sql3 = "SELECT * FROM t_citra";
            ResultSet rs3 = exe.executeQuery(sql3);
            while (rs3.next()) {
//                int nomor = rs2.getInt("id_objek");   
                char aml1 = '0';
                char aml2 = '0';
                char aml3 = '0';
                 String nama_objek= rs3.getString("nama_objek");
                 String nama_citra= rs3.getString("nama_citra");
                 double normarea = (rs3.getDouble("area") - minarea)/ (maxarea - minarea);
                 double normtepi = (rs3.getDouble("tepi") - mintepi)/ (maxtepi - mintepi);
                 double normkebundaran = (rs3.getDouble("kebundaran") - minkebundaran)/ (maxkebundaran - minkebundaran);
                 double normrasio = (rs3.getDouble("rasio") - minrasio)/ (maxrasio - minrasio);
                 double normmean = (rs3.getDouble("mean") - minmean)/ (maxmean - minmean);
                 double normstdev = (rs3.getDouble("st_dev") - minstdev)/ (maxstdev - minstdev);
                 String jenis= rs3.getString("jenis");
                 if (jenis.equals("M1")){
                 aml1 = '1';
                 } else if (jenis.equals("M2")){
                 aml2 = '1';
                 } else if (jenis.equals("M3")){
                 aml3 = '1';
                 }
                 
                int fold = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                String sql4 =  "INSERT INTO t_citra_norm (`nama_objek`, `nama_citra`, `area`, `tepi`, `kebundaran`, `rasio`, `mean`, `st_dev`, `jenis`, `aml1`, `aml2`, `aml3`, `fold`)  VALUES('" + 
                                              nama_objek + "','"+ nama_citra +"','"+ normarea +"','" + normtepi + "','" + normkebundaran + "','" + normrasio + "','" + normmean + "','" + normstdev + "','" + jenis + "','" + aml1 + "','" + aml2 + "','" + aml3 + "','" + fold + "')";
           
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
            String data = "jdbc:mysql://localhost:3306/thesis";
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
                int iduji= rs.getInt("id_uji")+1;
                String sql2 =  "INSERT INTO `thesis`.`t_hasil` (`id_objek`, `id_uji`, `jenis`, `hasil`) VALUES ('"+id_objek+"', '"+Integer.toString(iduji)+"', '"+jenis+"', '"+jenis_testing+"')";
                pstmt = con.prepareStatement(sql2); 
                pstmt.executeUpdate();
                exe.executeQuery(sql2);
            }      
            berhasilupdate = true;
        } catch (SQLException ex) {
            berhasilupdate = false;
        }
        
        finally {
      pstmt.close();
      con.close();
        }
    }
      
     public static void insertHasil(String epoch, String learning_rate, String hidden_layer, String momentum, String MSE, String waktu, String akurasi ) throws SQLException {
        Connection con = null;
        Statement exe = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/thesis";
            con = DriverManager.getConnection(data, "root", "");
            exe = con.createStatement();  
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean berhasilinsert = false;
        try {     
            
//           String sql =  "INSERT INTO t_uji (`epoch`,`learn_rate`,`hid_layer`,`momentum`,`MSE`,`waktu`,`konvergensi`,`akurasi`)  VALUES('" + 
//                                              epoch + "','"+ learning_rate +"','"+ hidden_layer +"','" + momentum + "','" + MSE + "','" + waktu + "','" + status + "','" + akurasi + "')";
         String sql =  "INSERT INTO `thesis`.`t_uji` (`id_uji`, `epoch`, `learn_rate`, `hid_layer`, `momentum`, `MSE`, `waktu`, `presisi`, `sensitifitas`, `spesifisitas`, `akurasi`) VALUES (NULL, '"+epoch+"', '"+learning_rate+"', '"+hidden_layer+"', '"+momentum+"', '"+MSE+"', '"+waktu+"', '', '', '', '"+akurasi+"')";
          
            pstmt = con.prepareStatement(sql); 
            pstmt.executeUpdate();
            exe.executeQuery(sql);
            berhasilinsert = true;
           } catch (SQLException ex) {
            berhasilinsert = false;
        }

    }
       
      
}