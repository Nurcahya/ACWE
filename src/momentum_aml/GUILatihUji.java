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
    public static double pres1, sens1, spes1, acc1;
    public static double pres2, sens2, spes2, acc2;
    public static double pres3, sens3, spes3, acc3;
    public static double totpres, totsens, totspes, totacc;
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
        
        if(TP1+FP1==0) pres1= 0;
        else pres1= (double)TP1/((double)TP1+(double)FP1)*100;
        if(TP1+FP1==0) sens1=0;
        else sens1= (double)TP1/((double)TP1+(double)FN1)*100;
        if(TN1+FP1==0) spes1=0;
        else spes1= (double)TN1/((double)TN1+(double)FP1)*100;
        if(TP1+TN1+FP1+FN1==0) acc1=0;
        else acc1= ((double)TP1+(double)TN1)/((double)TP1+(double)TN1+(double)FP1+(double)FN1)*100;
        
        if(TP2+FP2==0) pres2= 0;
        else pres2=(double)TP2/((double)TP2+(double)FP2)*100;
        if(TP2+FP2==0) sens2=0;
        else sens2= (double)TP2/((double)TP2+(double)FN2)*100;
        if(TN2+FP2==0) spes2=0;
        else spes2= (double)TN2/((double)TN2+(double)FP2)*100;
        if(TP2+TN2+FP2+FN2==0) acc2=0;
        else acc2= ((double)TP2+(double)TN2)/((double)TP2+(double)TN2+(double)FP2+(double)FN2)*100;
        
        if(TP3+FP3==0) pres3= 0;
        else pres3= (double)TP3/((double)TP3+(double)FP3)*100;
        if(TP3+FP3==0) sens3=0;
        else sens3= (double)TP3/((double)TP3+(double)FN3)*100;
        if(TN3+FP3==0) spes3=0;
        else spes3= (double)TN3/((double)TN3+(double)FP3)*100;
        if(TP3+TN3+FP3+FN3==0) acc3=0;
        else acc3= ((double)TP3+(double)TN3)/((double)TP3+(double)TN3+(double)FP3+(double)FN3)*100;
        
        
        totpres= (pres1+pres2+pres3)/3;
        totsens= (sens1+sens2+sens3)/3;
        totspes= (spes1+spes2+spes3)/3;
        totacc= (acc1+acc2+acc3)/3;
        
        
        System.out.println("Nilai Rata Presisi : "+totpres);
        System.out.println("Nilai Rata Sensitivitas : : "+totsens);
        System.out.println("Nilai Rata Spesifisitas : "+totspes);
        System.out.println("Nilai Rata Akurasi : "+totacc);
        
        GUI.jLabel32.setText(": "+tampilerror);
        GUI.jLabel43.setText(": "+TP1);
        GUI.jLabel44.setText(": "+TN1);
        GUI.jLabel45.setText(": "+FP1);
        GUI.jLabel46.setText(": "+FN1);
        GUI.jLabel86.setText(": "+TP2);
        GUI.jLabel87.setText(": "+TN2);
        GUI.jLabel88.setText(": "+FP2);
        GUI.jLabel89.setText(": "+FN2);
        GUI.jLabel94.setText(": "+TP3);
        GUI.jLabel95.setText(": "+TN3);
        GUI.jLabel96.setText(": "+FP3);
        GUI.jLabel97.setText(": "+FN3);
        
        GUI.jLabel52.setText(": "+String.valueOf(df.format(pres1)));
        GUI.jLabel53.setText(": "+String.valueOf(df.format(sens1)));
        GUI.jLabel49.setText(": "+String.valueOf(df.format(spes1)));
        GUI.jLabel85.setText(": "+String.valueOf(df.format(acc1)));
        
        GUI.jLabel90.setText(": "+String.valueOf(df.format(pres2)));
        GUI.jLabel91.setText(": "+String.valueOf(df.format(sens2)));
        GUI.jLabel92.setText(": "+String.valueOf(df.format(spes2)));
        GUI.jLabel93.setText(": "+String.valueOf(df.format(acc2)));
        
        GUI.jLabel98.setText(": "+String.valueOf(df.format(pres3)));
        GUI.jLabel99.setText(": "+String.valueOf(df.format(sens3)));
        GUI.jLabel100.setText(": "+String.valueOf(df.format(spes3)));
        GUI.jLabel101.setText(": "+String.valueOf(df.format(acc3)));
                 
         
         JOptionPane.showMessageDialog(null, "Proses pelatihan telah selesai", "InfoBox ", JOptionPane.INFORMATION_MESSAGE);
	 System.out.println("Pelatihan Selesai!");
        }
}
