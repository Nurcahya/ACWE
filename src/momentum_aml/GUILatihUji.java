/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package momentum_aml;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static momentum_aml.DBConnector.insertHasil;
import momentum_aml.MomentumBack;
import momentum_aml.transferfunctions.SigmoidalTransfer;

/**
 *
 * @author se7en
 */
public class GUILatihUji {
    public static int jmlmemo;
    public static double memorisasi;
    public static double hasil;
    public static int jmlg;
    public static double err;
    public static String stat;
    public static String tampilerror;

    
  
        String act;
        static String[] dataLatihLatih = new String[14];    
        static String[] dataUjiUji = new String[12];
        public static void latihuji(String y) throws ParseException {
  
        GUI.redirectSystemStreams();
        int[] layers = new int[]{6, GUIhandler.b, 3 };
	MomentumBack net = new MomentumBack(layers, GUIhandler.a, new SigmoidalTransfer(), GUIhandler.e);
		
        //proses learning
	int flag = 1 ;	
        final double startTime = System.currentTimeMillis();
        double error1 = 0;
        double error2 = 0;
        double error3 = 0;
        
        //fold pertama----------------------------------------------------------------------------
        //hitung latih
        for(int i = 0; i < GUIhandler.c; i++)
		{
                
                if (flag==1){
                int j = 0;
                StringTokenizer st1 = new StringTokenizer(y, "$*$");
                while (st1.hasMoreTokens()) {
                    dataLatihLatih[j] = (String) st1.nextElement();
                    j++;
                    if (j == 14) {
                        
                        double Area = Double.parseDouble(dataLatihLatih[0]);
                        double Tepi = Double.parseDouble(dataLatihLatih[1]);
                        double Kebundaran = Double.parseDouble(dataLatihLatih[2]);
                        double Rasio = Double.parseDouble(dataLatihLatih[3]);
                        double Mean = Double.parseDouble(dataLatihLatih[4]);
                        double Stdev = Double.parseDouble(dataLatihLatih[5]);
                        double M1 = Double.parseDouble(dataLatihLatih[6]);
                        double M2 = Double.parseDouble(dataLatihLatih[7]);
                        double M3 = Double.parseDouble(dataLatihLatih[8]);
                        String Jenis = dataLatihLatih[9];
                        String NamaObj = dataLatihLatih[10];
                        String NamaCitra = dataLatihLatih[11];
                        String fold = dataLatihLatih[12];
                        String id = dataLatihLatih[13];
                        j = 0;
                            if (!"1".equals(fold)){
                                double[] inputs = new double[]{Area, Tepi, Kebundaran, Rasio, Mean, Stdev};
                                double[] output = new double[]{M1, M2, M3};

                                error1 = net.backPropagate(inputs, output, i);

                                if (error1<=GUIhandler.d){ GUI.jLabel57.setText(": Konvergen ("+i+")");  
                                 stat = "Konvergen ("+i+")";
                                flag = 0;
                                break;
                                }
                                else{
                                    GUI.jLabel57.setText(": Belum Konvergen"); 
                                    stat = "Belum Konvergen";
                                }
                            }
                        }
                    }
                }
                }        
         
         //hitung uji
         int j = 0;
         int jml = 0;
         int TP1 = 0;
         int TN1 = 0;
         int FP1 = 0;
         int FN1 = 0;
         int jmlmemo1=0;
                StringTokenizer st1 = new StringTokenizer(y, "$*$");
                while (st1.hasMoreTokens()) {
                    dataLatihLatih[j] = (String) st1.nextElement();
                    j++;
                    if (j == 14) {
                        
                        double Area = Double.parseDouble(dataLatihLatih[0]);
                        double Tepi = Double.parseDouble(dataLatihLatih[1]);
                        double Kebundaran = Double.parseDouble(dataLatihLatih[2]);
                        double Rasio = Double.parseDouble(dataLatihLatih[3]);
                        double Mean = Double.parseDouble(dataLatihLatih[4]);
                        double Stdev = Double.parseDouble(dataLatihLatih[5]);
                        double M1 = Double.parseDouble(dataLatihLatih[6]);
                        double M2 = Double.parseDouble(dataLatihLatih[7]);
                        double M3 = Double.parseDouble(dataLatihLatih[8]);
                        String Jenis = dataLatihLatih[9];
                        String NamaObj = dataLatihLatih[10];
                        String NamaCitra = dataLatihLatih[11];
                        String fold = dataLatihLatih[12];
                        String id = dataLatihLatih[13];
                        j = 0;
                        
                        if ("1".equals(fold)){
                            double[] inputs = new double[]{Area, Tepi, Kebundaran, Rasio, Mean, Stdev};
                            double[] output = net.execute(inputs);

                            double target=output[0];
                            for(int x=0; x<output.length; x++){
                                if(output[x]>target){
                                target = output[x];
                                }
                            }
                             String klasifikasi="empty";
                            if (target == output[0]){
                            klasifikasi = "M1";
                            }
                            else if (target == output[1]){
                            klasifikasi = "M2";
                            }
                            else if (target == output[2]){
                            klasifikasi = "M3";
                            }

                           // System.out.println(NamaObj+" "+NamaCitra+"Hasilnya : "+inputs[0]+" "+inputs[1]+" "+inputs[2]+" "+inputs[3]+ " "+inputs[4]+ ""+inputs[5]+ "("+Jenis+") = "+output[0]+" "+output[1]+" "+output[2]+" ("+jenismemorisasi+")");
                            try {
                                DBConnector.updateUji(id, Jenis, klasifikasi); 
                            } catch (SQLException ex) {
                                Logger.getLogger(GUILatihUji.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (Jenis.equals("M1")&&klasifikasi.equals("M1")){ TP1 = TP1+1;  }
                            else if(!Jenis.equals("M1")&&klasifikasi.equals("M1")){ FP1 = FP1+1;  }
                            else if(Jenis.equals("M1")&&!klasifikasi.equals("M1")){ FN1 = FN1+1;  }
                            else if(!Jenis.equals("M1")&&!klasifikasi.equals("M1")){ TN1 = TN1+1;  }
                            jml = jml+1;

                        }
                        
                        }
                    
                    }
                
         //fold kedua----------------------------------------------------------------------------
        //hitung latih
        for(int i = 0; i < GUIhandler.c; i++)
		{
                
                if (flag==1){
                j = 0;
                StringTokenizer st2 = new StringTokenizer(y, "$*$");
                while (st2.hasMoreTokens()) {
                    dataLatihLatih[j] = (String) st2.nextElement();
                    j++;
                    if (j == 14) {
                        
                        double Area = Double.parseDouble(dataLatihLatih[0]);
                        double Tepi = Double.parseDouble(dataLatihLatih[1]);
                        double Kebundaran = Double.parseDouble(dataLatihLatih[2]);
                        double Rasio = Double.parseDouble(dataLatihLatih[3]);
                        double Mean = Double.parseDouble(dataLatihLatih[4]);
                        double Stdev = Double.parseDouble(dataLatihLatih[5]);
                        double M1 = Double.parseDouble(dataLatihLatih[6]);
                        double M2 = Double.parseDouble(dataLatihLatih[7]);
                        double M3 = Double.parseDouble(dataLatihLatih[8]);
                        String Jenis = dataLatihLatih[9];
                        String NamaObj = dataLatihLatih[10];
                        String NamaCitra = dataLatihLatih[11];
                        String fold = dataLatihLatih[12];
                        String id = dataLatihLatih[13];
                        j = 0;
                            if (!"2".equals(fold)){
                                double[] inputs = new double[]{Area, Tepi, Kebundaran, Rasio, Mean, Stdev};
                                double[] output = new double[]{M1, M2, M3};

                                error2 = net.backPropagate(inputs, output, i);

                                if (error2<=GUIhandler.d){ GUI.jLabel57.setText(": Konvergen ("+i+")");  
                                 stat = "Konvergen ("+i+")";
                                flag = 0;
                                break;
                                }
                                else{
                                    GUI.jLabel57.setText(": Belum Konvergen"); 
                                    stat = "Belum Konvergen";
                                }
                            }
                        }
                    }
                }
                }        
         
         //hitung uji
         j = 0;
         int TP2 = 0;
         int TN2 = 0;
         int FP2 = 0;
         int FN2 = 0;
         int jmlmemo2=0;
                StringTokenizer st2 = new StringTokenizer(y, "$*$");
                while (st2.hasMoreTokens()) {
                    dataLatihLatih[j] = (String) st2.nextElement();
                    j++;
                    if (j == 14) {
                        
                        double Area = Double.parseDouble(dataLatihLatih[0]);
                        double Tepi = Double.parseDouble(dataLatihLatih[1]);
                        double Kebundaran = Double.parseDouble(dataLatihLatih[2]);
                        double Rasio = Double.parseDouble(dataLatihLatih[3]);
                        double Mean = Double.parseDouble(dataLatihLatih[4]);
                        double Stdev = Double.parseDouble(dataLatihLatih[5]);
                        double M1 = Double.parseDouble(dataLatihLatih[6]);
                        double M2 = Double.parseDouble(dataLatihLatih[7]);
                        double M3 = Double.parseDouble(dataLatihLatih[8]);
                        String Jenis = dataLatihLatih[9];
                        String NamaObj = dataLatihLatih[10];
                        String NamaCitra = dataLatihLatih[11];
                        String fold = dataLatihLatih[12];
                        String id = dataLatihLatih[13];
                        j = 0;
                        
                        if ("2".equals(fold)){
                            double[] inputs = new double[]{Area, Tepi, Kebundaran, Rasio, Mean, Stdev};
                            double[] output = net.execute(inputs);

                            double target=output[0];
                            for(int x=0; x<output.length; x++){
                                if(output[x]>target){
                                target = output[x];
                                }
                            }
                             String klasifikasi="empty";
                            if (target == output[0]){
                            klasifikasi = "M1";
                            }
                            else if (target == output[1]){
                            klasifikasi = "M2";
                            }
                            else if (target == output[2]){
                            klasifikasi = "M3";
                            }

                            //System.out.println(NamaObj+" "+NamaCitra+"Hasilnya : "+inputs[0]+" "+inputs[1]+" "+inputs[2]+" "+inputs[3]+ " "+inputs[4]+ ""+inputs[5]+ "("+Jenis+") = "+output[0]+" "+output[1]+" "+output[2]+" ("+jenismemorisasi+")");
                            try {
                                DBConnector.updateUji(id, Jenis, klasifikasi); 
                            } catch (SQLException ex) {
                                Logger.getLogger(GUILatihUji.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (Jenis.equals("M2")&&klasifikasi.equals("M2")){ TP2 = TP2+1;  }
                            else if(!Jenis.equals("M2")&&klasifikasi.equals("M2")){ FP2 = FP2+1;  }
                            else if(Jenis.equals("M2")&&!klasifikasi.equals("M2")){ FN2 = FN2+1;  }
                            else if(!Jenis.equals("M2")&&!klasifikasi.equals("M2")){ TN2 = TN2+1;  }
                            jml = jml+1;

                        }
                        
                        }
                    
                    }       
        
        
        //fold ketiga----------------------------------------------------------------------------
        //hitung latih
        for(int i = 0; i < GUIhandler.c; i++)
		{
                
                if (flag==1){
                j = 0;
                StringTokenizer st3 = new StringTokenizer(y, "$*$");
                while (st3.hasMoreTokens()) {
                    dataLatihLatih[j] = (String) st3.nextElement();
                    j++;
                    if (j == 14) {
                        
                        double Area = Double.parseDouble(dataLatihLatih[0]);
                        double Tepi = Double.parseDouble(dataLatihLatih[1]);
                        double Kebundaran = Double.parseDouble(dataLatihLatih[2]);
                        double Rasio = Double.parseDouble(dataLatihLatih[3]);
                        double Mean = Double.parseDouble(dataLatihLatih[4]);
                        double Stdev = Double.parseDouble(dataLatihLatih[5]);
                        double M1 = Double.parseDouble(dataLatihLatih[6]);
                        double M2 = Double.parseDouble(dataLatihLatih[7]);
                        double M3 = Double.parseDouble(dataLatihLatih[8]);
                        String Jenis = dataLatihLatih[9];
                        String NamaObj = dataLatihLatih[10];
                        String NamaCitra = dataLatihLatih[11];
                        String fold = dataLatihLatih[12];
                        String id = dataLatihLatih[13];
                        j = 0;
                            if (!"3".equals(fold)){
                                double[] inputs = new double[]{Area, Tepi, Kebundaran, Rasio, Mean, Stdev};
                                double[] output = new double[]{M1, M2, M3};

                                error3 = net.backPropagate(inputs, output, i);

                                if (error3<=GUIhandler.d){
                                 GUI.jLabel57.setText(": Konvergen ("+i+")");  
                                 stat = "Konvergen ("+i+")";
                                flag = 0;
                                break;
                                }
                                else{
                                    GUI.jLabel57.setText(": Belum Konvergen"); 
                                    stat = "Belum Konvergen";
                                }
                            }
                        }
                    }
                }
                }        
         
         //hitung uji
         j = 0;
         int TP3 = 0;
         int TN3 = 0;
         int FP3 = 0;
         int FN3 = 0;
         int jmlmemo3=0;
                StringTokenizer st3 = new StringTokenizer(y, "$*$");
                while (st3.hasMoreTokens()) {
                    dataLatihLatih[j] = (String) st3.nextElement();
                    j++;
                    if (j == 14) {
                        
                        double Area = Double.parseDouble(dataLatihLatih[0]);
                        double Tepi = Double.parseDouble(dataLatihLatih[1]);
                        double Kebundaran = Double.parseDouble(dataLatihLatih[2]);
                        double Rasio = Double.parseDouble(dataLatihLatih[3]);
                        double Mean = Double.parseDouble(dataLatihLatih[4]);
                        double Stdev = Double.parseDouble(dataLatihLatih[5]);
                        double M1 = Double.parseDouble(dataLatihLatih[6]);
                        double M2 = Double.parseDouble(dataLatihLatih[7]);
                        double M3 = Double.parseDouble(dataLatihLatih[8]);
                        String Jenis = dataLatihLatih[9];
                        String NamaObj = dataLatihLatih[10];
                        String NamaCitra = dataLatihLatih[11];
                        String fold = dataLatihLatih[12];
                        String id = dataLatihLatih[13];
                        j = 0;
                        
                        if ("3".equals(fold)){
                            double[] inputs = new double[]{Area, Tepi, Kebundaran, Rasio, Mean, Stdev};
                            double[] output = net.execute(inputs);

                            double target=output[0];
                            for(int x=0; x<output.length; x++){
                                if(output[x]>target){
                                target = output[x];
                                }
                            }
                             String klasifikasi="empty";
                            if (target == output[0]){
                            klasifikasi = "M1";
                            }
                            else if (target == output[1]){
                            klasifikasi = "M2";
                            }
                            else if (target == output[2]){
                            klasifikasi = "M3";
                            }

                            //System.out.println(NamaObj+" "+NamaCitra+"Hasilnya : "+inputs[0]+" "+inputs[1]+" "+inputs[2]+" "+inputs[3]+ " "+inputs[4]+ ""+inputs[5]+ "("+Jenis+") = "+output[0]+" "+output[1]+" "+output[2]+" ("+jenismemorisasi+")");
                            try {
                                DBConnector.updateUji(id, Jenis, klasifikasi); 
                            } catch (SQLException ex) {
                                Logger.getLogger(GUILatihUji.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (Jenis.equals("M3")&&klasifikasi.equals("M3")){ TP3 = TP3+1;  }
                            else if(!Jenis.equals("M3")&&klasifikasi.equals("M3")){ FP3 = FP3+1;  }
                            else if(Jenis.equals("M3")&&!klasifikasi.equals("M3")){ FN3 = FN3+1;  }
                            else if(!Jenis.equals("M3")&&!klasifikasi.equals("M3")){ TN3 = TN3+1;  }
                            jml = jml+1;

                        }
                        
                        }
                    
                    }    
        //hitung waktu
         final double endTime = System.currentTimeMillis();
         hasil = (endTime - startTime)/1000;
         DecimalFormat df = new DecimalFormat("####0.000"); 
         GUI.jLabel31.setText(": "+String.valueOf(df.format(hasil))+ " dtk");    
         
        err = (error1+error2+error3)/3;
        System.out.println("Nilai MSE : "+err);
        DecimalFormat desimal = new DecimalFormat("####0.00000000"); 
        tampilerror=String.valueOf(desimal.format(err));
       
        GUI.jLabel32.setText(": "+tampilerror);
         
         
         System.out.println(jmlmemo1);
         System.out.println(jmlmemo2);
         System.out.println(jmlmemo3);
         System.out.println(jml);
         jmlmemo = jmlmemo1+jmlmemo2+jmlmemo3;
         memorisasi = (double)jmlmemo/(double)jml * 100;
         
         JOptionPane.showMessageDialog(null, "Proses pelatihan telah selesai", "InfoBox ", JOptionPane.INFORMATION_MESSAGE);
	 System.out.println("Pelatihan Selesai!");
         System.out.println(memorisasi); 
        }
}
