/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package momentum_aml;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import momentum_aml.MomentumBack;
import momentum_aml.transferfunctions.SigmoidalTransfer;
import momentum_aml.GUILatihUji;

/**
 *
 * @author se7en
 */
public class GUIhandler implements ActionListener {

    static String[] dataLatih = new String[10];
    static String[] dataLatihNorm = new String[10];
    static String[] dataUji = new String[10];
    static String[] dataUjiHasil = new String[11];
    static String[] dataHasil = new String[9];
    static String[] dataMatrix = new String[16];
    int flagLatih;
    int flagUji;
    static int b, c;
    static double a, d, e;
    private JTable tabelPelatihan;
    private JTable tabelPengujian;
    private JTable tabelHasil;
    private JLabel jLabel23, jLabel24, jLabel25, jLabel26, jLabel27, jLabel28, jLabel31, jLabel102, jLabel103, jLabel104, jLabel105;
    private JTextField fHiddenLayer, fLearnRate, fMaxEpoch, fToleransi, fMomentum;
    private JButton btnLoadLatih, btnLoadUji, btnLatih, btnUji, btnNorm;
    private DefaultTableModel tabMode;
    private DefaultTableModel tabMode2;
    private DefaultTableModel tabMode4;
    private DefaultTableModel tabMode5;
    public static DefaultTableModel tabMode3;
    private int count = 0;

    public GUIhandler(JTable tabelPelatihan, JTable tabelPengujian, JTable tabelPengujian1, JTable tabelPengujian2, JTable tabelHasil,
            JButton btnLoadLatih, JButton btnLatih, JButton btnLoadUji, JButton btnUji, JButton btnNorm,
            JTextField fHiddenLayer, JTextField fLearnRate, JTextField fMaxEpoch, JTextField fToleransi, JTextField fMomentum,
            JLabel jLabel23, JLabel jLabel24, JLabel jLabel25, JLabel jLabel26, JLabel jLabel102, JLabel jLabel103, JLabel jLabel104, JLabel jLabel105, JLabel jLabel31) {
        this.tabelPelatihan = tabelPelatihan;
        this.tabelHasil = tabelHasil;
        this.btnLoadLatih = btnLoadLatih;
        this.btnLoadUji = btnLoadUji;
        this.btnLatih = btnLatih;
        this.btnUji = btnUji;
        this.btnNorm = btnNorm;
        this.fHiddenLayer = fHiddenLayer;
        this.fLearnRate = fLearnRate;
        this.fMaxEpoch = fMaxEpoch;
        this.fToleransi = fToleransi;
        this.fMomentum = fMomentum;
        this.jLabel23 = jLabel23;
        this.jLabel24 = jLabel24;
        this.jLabel25 = jLabel25;
        this.jLabel26 = jLabel26;
        this.jLabel27 = jLabel27;
        this.jLabel28 = jLabel28;
        this.jLabel31 = jLabel31;
        this.jLabel102 = jLabel102;
        this.jLabel103 = jLabel103;
        this.jLabel104 = jLabel104;
        this.jLabel105 = jLabel105;
        tabMode = (DefaultTableModel) tabelPelatihan.getModel();
        tabMode2 = (DefaultTableModel) tabelPengujian.getModel();
        tabMode4 = (DefaultTableModel) tabelPengujian1.getModel();
        tabMode5 = (DefaultTableModel) tabelPengujian2.getModel();
        tabMode3 = (DefaultTableModel) tabelHasil.getModel();
        setLebarRowAL();
    }

    public static void scanMatrix(String x) {
        int j = 0;
        String A1 = null, B1= null, C1= null, D1= null;
        String A2 = null, B2= null, C2= null, D2= null;
        String A3 = null, B3= null, C3= null, D3= null;
        String A4 = null, B4= null, C4= null, D4= null;
        StringTokenizer st = new StringTokenizer(x, "$*$");
        while (st.hasMoreTokens()) {
            dataMatrix[j] = (String) st.nextElement();
            j++;
            if (j == 16) {
                A1 = (dataMatrix[0]);
                B1 = (dataMatrix[1]);
                C1 = (dataMatrix[2]);
                D1 = (dataMatrix[3]); 
                A2 = (dataMatrix[4]);
                B2 = (dataMatrix[5]);
                C2 = (dataMatrix[6]);
                D2 = (dataMatrix[7]);
                A3 = (dataMatrix[8]);
                B3 = (dataMatrix[9]);
                C3 = (dataMatrix[10]);
                D3 = (dataMatrix[11]);
                A4 = (dataMatrix[12]);
                B4 = (dataMatrix[13]);
                C4 = (dataMatrix[14]);
                D4 = (dataMatrix[15]); 
                j = 0;
            }            
            GUI.jLabel133.setText(A1);
            GUI.jLabel134.setText(B1);
            GUI.jLabel135.setText(C1);
            GUI.jLabel136.setText(D1);
            GUI.jLabel137.setText(A2);
            GUI.jLabel138.setText(B2);
            GUI.jLabel139.setText(C2);
            GUI.jLabel140.setText(D2);
            GUI.jLabel141.setText(A3);
            GUI.jLabel142.setText(B3);
            GUI.jLabel143.setText(C3);
            GUI.jLabel144.setText(D3);
            GUI.jLabel145.setText(A4);
            GUI.jLabel146.setText(B4);
            GUI.jLabel147.setText(C4);
            GUI.jLabel148.setText(D4);
        }
    }
    
    
    public static void scanDataHasil(String x) {
        int k = 0;
        int j = 0;
        int jN = 0, jE = 0, jB = 0, jL = 0, jM = 0;
        StringTokenizer st = new StringTokenizer(x, "$*$");
        while (st.hasMoreTokens()) {
            dataHasil[j] = (String) st.nextElement();
            j++;
            if (j == 9) {
                k++;
                String No = String.valueOf(k);
                String epoch = (dataHasil[0]);
                String hidden_layer = (dataHasil[1]);
                String learning_rate = (dataHasil[2]);
                String momentum = (dataHasil[3]);
                String waktu = (dataHasil[4]);
                String presisi = (dataHasil[5]);
                String sensitivitas = (dataHasil[6]);
                String spesifisitas = (dataHasil[7]);
                String akurasi = (dataHasil[8]);

                String data[] = {No, epoch, hidden_layer, learning_rate, momentum, waktu, presisi, sensitivitas, spesifisitas, akurasi};
                tabMode3.addRow(data);
                j = 0;
            }
        }

    }

    public void scanDataLatih(String x) {
        if (flagLatih == 1) {
            int k = 0;
            int j = 0;
            int j1 = 0, j2 = 0, j3 = 0;
            int t1 = 0, t2 = 0, t3 = 0, t4 = 0;
            StringTokenizer st = new StringTokenizer(x, "$*$");
            while (st.hasMoreTokens()) {
                dataLatih[j] = (String) st.nextElement();
                j++;
                if (j == 10) {
                    k++;
                    String No = String.valueOf(k);
                    String Objek = (dataLatih[0]);
                    String Nama = (dataLatih[1]);
                    String Area = (dataLatih[2]);
                    String Tepi = (dataLatih[3]);
                    String Kebundaran = (dataLatih[4]);
                    String Rasio = (dataLatih[5]);
                    String Mean = (dataLatih[6]);
                    String Stdev = (dataLatih[7]);
                    String Jenis = (dataLatih[8]);
                    String Tipe = (dataLatih[9]);

                    if (Jenis.equals("M1")) {
                        j1++;
                    } else if (Jenis.equals("M2")) {
                        j2++;
                    } else if (Jenis.equals("M3")) {
                        j3++;
                    }
                    
                    
                    if (Tipe.equals("myeloblast")) {
                        t1++;
                    } else if (Tipe.equals("promyelosit")) {
                        t2++;
                    } else if (Tipe.equals("monosit")) {
                        t3++;
                    } else if (Tipe.equals("non-blast")) {
                        t4++;
                    }
                    

                    String data[] = {No, Objek, Nama, Area, Tepi, Kebundaran, Rasio, Mean, Stdev, Jenis, Tipe};
                    tabMode.addRow(data);
                    j = 0;
                }
            }
            jLabel23.setText(": " + Integer.toString(j1 + j2 + j3));
            jLabel24.setText(": " + Integer.toString(j1));
            jLabel25.setText(": " + Integer.toString(j2));
            jLabel26.setText(": " + Integer.toString(j3));
            jLabel102.setText(": " + Integer.toString(t1));
            jLabel103.setText(": " + Integer.toString(t2));
            jLabel104.setText(": " + Integer.toString(t3));
            jLabel105.setText(": " + Integer.toString(t4));

        } else if (flagLatih == 2) {
            int k = 0;
            int j = 0;
            StringTokenizer st = new StringTokenizer(x, "$*$");
            while (st.hasMoreTokens()) {
                dataLatihNorm[j] = (String) st.nextElement();
                j++;
                if (j == 10) {
                    k++;
                    String No = String.valueOf(k);
                    String Objek = (dataLatihNorm[0]);
                    String Nama = (dataLatihNorm[1]);
                    String Area = (dataLatihNorm[2]);
                    String Tepi = (dataLatihNorm[3]);
                    String Kebundaran = (dataLatihNorm[4]);
                    String Rasio = (dataLatihNorm[5]);
                    String Mean = (dataLatihNorm[6]);
                    String Stdev = (dataLatihNorm[7]);
                    String Jenis = (dataLatihNorm[8]);
                    String Tipe = (dataLatihNorm[9]);

                    String data[] = {No, Objek, Nama, Area, Tepi, Kebundaran, Rasio, Mean, Stdev, Jenis, Tipe};
                    tabMode.addRow(data);
                    j = 0;
                }
            }
        }
    }

    public void scanDataUji(String x) {
        if (flagUji == 1) {
            int k = 0;
            int j = 0;
            int no1 = 0, no2 = 0, no3 = 0;
            int t1 = 0, t2 = 0, t3 = 0, t4 = 0;
            StringTokenizer st = new StringTokenizer(x, "$*$");
            while (st.hasMoreTokens()) {
                dataUji[j] = (String) st.nextElement();
                j++;
                if (j == 10) {
                    k++;
                    String Objek = (dataUji[0]);
                    String Nama = (dataUji[1]);
                    String Area = (dataUji[2]);
                    String Tepi = (dataUji[3]);
                    String Kebundaran = (dataUji[4]);
                    String Rasio = (dataUji[5]);
                    String Mean = (dataUji[6]);
                    String Stdev = (dataUji[7]);
                    String Tipe = (dataUji[8]);
                    String Fold = (dataUji[9]);
                    String Jenis_hasil = "Undefined";
                     if (Tipe.equals("myeloblast")) {
                        t1++;
                    } else if (Tipe.equals("promyelosit")) {
                        t2++;
                    } else if (Tipe.equals("monosit")) {
                        t3++;
                    } else if (Tipe.equals("non-blast")) {
                        t4++;
                    }

                    if ("1".equals(Fold)) {
                        no1 = no1 + 1;
                        String data1[] = {Integer.toString(no1), Objek, Nama, Area, Tepi, Kebundaran, Rasio, Mean, Stdev, Tipe, Jenis_hasil};
                        tabMode2.addRow(data1);
                    } else if ("2".equals(Fold)) {
                        no2 = no2 + 1;
                        String data2[] = {Integer.toString(no2), Objek, Nama, Area, Tepi, Kebundaran, Rasio, Mean, Stdev, Tipe, Jenis_hasil};
                        tabMode4.addRow(data2);
                    } else if ("3".equals(Fold)) {
                        no3 = no3 + 1;
                        String data3[] = {Integer.toString(no3), Objek, Nama, Area, Tepi, Kebundaran, Rasio, Mean, Stdev, Tipe, Jenis_hasil};
                        tabMode5.addRow(data3);
                    }
                    j = 0;
                }
            }
        } else if (flagUji == 2) {
            int k = 0;
            int j = 0;
            int no1 = 0, no2 = 0, no3 = 0;
            StringTokenizer st = new StringTokenizer(x, "$*$");
            while (st.hasMoreTokens()) {
                dataUjiHasil[j] = (String) st.nextElement();
                j++;
                if (j == 11) {
                    k++;
                    String Objek = (dataUjiHasil[0]);
                    String Nama = (dataUjiHasil[1]);
                    String Area = (dataUjiHasil[2]);
                    String Tepi = (dataUjiHasil[3]);
                    String Kebundaran = (dataUjiHasil[4]);
                    String Rasio = (dataUjiHasil[5]);
                    String Mean = (dataUjiHasil[6]);
                    String Stdev = (dataUjiHasil[7]);
                    String Jenis = (dataUjiHasil[8]);
                    String Fold = (dataUjiHasil[9]);
                    String Jenis_hasil = (dataUjiHasil[10]);

                    if ("1".equals(Fold)) {
                        no1 = no1 + 1;
                        String data1[] = {Integer.toString(no1), Objek, Nama, Area, Tepi, Kebundaran, Rasio, Mean, Stdev, Jenis, Jenis_hasil};
                        tabMode2.addRow(data1);
                    } else if ("2".equals(Fold)) {
                        no2 = no2 + 1;
                        String data2[] = {Integer.toString(no2), Objek, Nama, Area, Tepi, Kebundaran, Rasio, Mean, Stdev, Jenis, Jenis_hasil};
                        tabMode4.addRow(data2);
                    } else if ("3".equals(Fold)) {
                        no3 = no3 + 1;
                        String data3[] = {Integer.toString(no3), Objek, Nama, Area, Tepi, Kebundaran, Rasio, Mean, Stdev, Jenis, Jenis_hasil};
                        tabMode5.addRow(data3);
                    }
                    j = 0;
                }
            }
        }

    }

    private GUILatihUji classAInstance;

    private void setLebarRowAL() {
        tabelPelatihan.setFont(new java.awt.Font("Tahoma", 0, 12));
        tabelPelatihan.getColumnModel().getColumn(0).setMaxWidth(200);
        tabelPelatihan.getColumnModel().getColumn(1).setMaxWidth(200);
        tabelPelatihan.getColumnModel().getColumn(2).setMaxWidth(200);
        tabelPelatihan.getColumnModel().getColumn(3).setMaxWidth(200);
        tabelPelatihan.getColumnModel().getColumn(4).setMaxWidth(200);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {

        GUI.redirectSystemStreams();
        if (ev.getSource() == btnLoadLatih) {
            if (flagLatih == 0) {
                GUI.redirectSystemStreams();
                System.out.println("Data latih berhasil diload dari database!");
                flagLatih = 1;
                tabMode.getDataVector().removeAllElements();
                scanDataLatih(DBConnector.dataLatih());
            } else {
                JOptionPane.showMessageDialog(null, "Data pelatihan sudah diload");
            }

        } else if (ev.getSource() == btnLatih) {
            if (flagLatih == 2 || flagLatih == 3) {
                flagLatih = 3;
                try {
                    this.a = Double.parseDouble(fLearnRate.getText());
                    this.b = Integer.parseInt(fHiddenLayer.getText());
                    this.c = Integer.parseInt(fMaxEpoch.getText());
                    this.d = Double.parseDouble(fToleransi.getText());
                    this.e = Double.parseDouble(fMomentum.getText());
                    GUILatihUji.latihuji(DBConnector.dataLatihLatih());

                } catch (ParseException ex) {
                    Logger.getLogger(GUIhandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (flagLatih == 0) {
                JOptionPane.showMessageDialog(null, "Data pelatihan belum diload");
            } else if (flagLatih == 1) {
                JOptionPane.showMessageDialog(null, "Data pelatihan belum dinormalisasi");
            }

        } else if (ev.getSource() == btnLoadUji) {
            if (flagLatih == 3) {
                flagUji = 1;
                tabMode2.getDataVector().removeAllElements();
                tabMode4.getDataVector().removeAllElements();
                tabMode5.getDataVector().removeAllElements();
                scanDataUji(DBConnector.dataUji());

                GUI.redirectSystemStreams();
                System.out.println("Data uji berhasil diload dari database!");
            } else {
                JOptionPane.showMessageDialog(null, "Data pengujian sudah diload");
            }

        } else if (ev.getSource() == btnUji) {
            if (flagUji == 1 && flagLatih == 3) {
                DecimalFormat df = new DecimalFormat("####0.000");

                String a = fLearnRate.getText();
                String b = fHiddenLayer.getText();
                String c = fMaxEpoch.getText();
                String d = fToleransi.getText();
                String e = fMomentum.getText();

                try {
                    DBConnector.insertHasil(c, a, b, e, GUILatihUji.tampilerror, String.valueOf(df.format(GUILatihUji.hasil)), String.valueOf(df.format(GUILatihUji.totpres)), String.valueOf(df.format(GUILatihUji.totsens)), String.valueOf(df.format(GUILatihUji.totspes)), String.valueOf(df.format(GUILatihUji.totacc)));
                    DBConnector.insertKelasHasilMyel(GUILatihUji.pres1, GUILatihUji.sens1, GUILatihUji.spes1, GUILatihUji.acc1);
                    DBConnector.insertKelasHasilPro(GUILatihUji.pres2, GUILatihUji.sens2, GUILatihUji.spes2, GUILatihUji.acc2);
                    DBConnector.insertKelasHasilMono(GUILatihUji.pres3, GUILatihUji.sens3, GUILatihUji.spes3, GUILatihUji.acc3);
                    DBConnector.insertKelasHasilNon(GUILatihUji.pres4, GUILatihUji.sens4, GUILatihUji.spes4, GUILatihUji.acc4);
                } catch (SQLException ex) {
                    Logger.getLogger(GUIhandler.class.getName()).log(Level.SEVERE, null, ex);
                }

                //System.out.println(GUILatihUji.jmlgene+" "+GUILatihUji.jmlg+" "+generalisasi);
                JOptionPane.showMessageDialog(null, "Proses pengujian telah selesai", "InfoBox ", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Pengujian Selesai!");
                flagUji = 2;
                tabMode2.getDataVector().removeAllElements();
                tabMode4.getDataVector().removeAllElements();
                tabMode5.getDataVector().removeAllElements();
                scanDataUji(DBConnector.dataUjiHasil());
            } else if (flagLatih != 3) {
                JOptionPane.showMessageDialog(null, "Data belum dilatih!");
            } else if (flagUji == 0) {
                JOptionPane.showMessageDialog(null, "Data pengujian belum diload");
            }
        } else if (ev.getSource() == btnNorm) {
            if (flagLatih == 1) {
                flagLatih = 2;
                tabMode.getDataVector().removeAllElements();
                try {
                    DBConnector.normalisasiLatih();
                } catch (SQLException ex) {
                    Logger.getLogger(GUIhandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                scanDataLatih(DBConnector.dataLatihNorm());
            } else if (flagLatih == 0) {
                JOptionPane.showMessageDialog(null, "Data pelatihan belum diload");
            } else {
                JOptionPane.showMessageDialog(null, "Data pelatihan sudah selesai dinormalisasi");
            }
        }

    }

}
