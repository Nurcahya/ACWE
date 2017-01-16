/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package momentum_aml;

import  java.io.*;  
import  java.sql.*;
import javax.swing.JOptionPane;
import  org.apache.poi.hssf.usermodel.HSSFSheet;  
import  org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFCell;  


public class Excel{
    public Excel(int kode){
if (kode==1){
try{
String filename="E:/Region Grow/data_latih.xls" ;
HSSFWorkbook hwb=new HSSFWorkbook();
HSSFSheet sheet =  hwb.createSheet("new sheet");

HSSFRow rowhead=   sheet.createRow((short)0);
rowhead.createCell((short) 0).setCellValue("Nama");
rowhead.createCell((short) 1).setCellValue("Luas");
rowhead.createCell((short) 2).setCellValue("Tepi");
rowhead.createCell((short) 3).setCellValue("Kebundaran");
rowhead.createCell((short) 4).setCellValue("Rasio");
rowhead.createCell((short) 5).setCellValue("Jenis");

Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbc", "root", "");
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("Select * from pelatihan");
int i=1;
while(rs.next()){
HSSFRow row=   sheet.createRow((short)i);
//row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("id")));
row.createCell((short) 0).setCellValue(rs.getString("Nama"));
row.createCell((short) 1).setCellValue(Integer.toString(rs.getInt("Luas")));
row.createCell((short) 2).setCellValue(Integer.toString(rs.getInt("Tepi")));
row.createCell((short) 3).setCellValue(rs.getString("Kebundaran"));
row.createCell((short) 4).setCellValue(rs.getString("Rasio"));
row.createCell((short) 5).setCellValue(rs.getString("Jenis"));

i++;
}
FileOutputStream fileOut =  new FileOutputStream(filename);
hwb.write(fileOut);
fileOut.close();
System.out.println("File excel telah berhasil dibuat!");
JOptionPane.showMessageDialog(null, "File excel 'data_latih.xls' telah berhasil dibuat", "InfoBox ", JOptionPane.INFORMATION_MESSAGE);
} catch ( Exception ex ) {
    System.out.println(ex);

}
}

else if (kode==2){
try{
String filename="f:/bismillah ta/data_uji.xls" ;
HSSFWorkbook hwb=new HSSFWorkbook();
HSSFSheet sheet =  hwb.createSheet("new sheet");

HSSFRow rowhead=   sheet.createRow((short)0);
rowhead.createCell((short) 0).setCellValue("Nama");
rowhead.createCell((short) 1).setCellValue("Luas");
rowhead.createCell((short) 2).setCellValue("Tepi");
rowhead.createCell((short) 3).setCellValue("Kebundaran");
rowhead.createCell((short) 4).setCellValue("Rasio");
rowhead.createCell((short) 5).setCellValue("Jenis");

Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbc", "root", "");
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("Select * from pengujian");
int i=1;
while(rs.next()){
HSSFRow row=   sheet.createRow((short)i);
//row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("id")));
row.createCell((short) 0).setCellValue(rs.getString("Nama"));
row.createCell((short) 1).setCellValue(Integer.toString(rs.getInt("Luas")));
row.createCell((short) 2).setCellValue(Integer.toString(rs.getInt("Tepi")));
row.createCell((short) 3).setCellValue(rs.getString("Kebundaran"));
row.createCell((short) 4).setCellValue(rs.getString("Rasio"));
row.createCell((short) 5).setCellValue(rs.getString("Jenis"));

i++;
}
FileOutputStream fileOut =  new FileOutputStream(filename);
hwb.write(fileOut);
fileOut.close();
System.out.println("File excel telah berhasil dibuat!");
JOptionPane.showMessageDialog(null, "File excel 'data_uji.xls' telah berhasil dibuat", "InfoBox ", JOptionPane.INFORMATION_MESSAGE);
} catch ( Exception ex ) {
    System.out.println(ex);

}
}
   
else if (kode==3){
try{
String filename="f:/bismillah ta/data_hasil.xls" ;
HSSFWorkbook hwb=new HSSFWorkbook();
HSSFSheet sheet =  hwb.createSheet("new sheet");

HSSFRow rowhead=   sheet.createRow((short)0);
rowhead.createCell((short) 0).setCellValue("epoch");
rowhead.createCell((short) 1).setCellValue("hidden_layer");
rowhead.createCell((short) 2).setCellValue("learning_rate");
rowhead.createCell((short) 3).setCellValue("momentum");
rowhead.createCell((short) 4).setCellValue("MSE");
rowhead.createCell((short) 5).setCellValue("waktu");
rowhead.createCell((short) 6).setCellValue("status");
rowhead.createCell((short) 7).setCellValue("memorisasi");
rowhead.createCell((short) 8).setCellValue("generalisasi");

Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbc", "root", "");
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("Select * from hasil");
int i=1;
while(rs.next()){
HSSFRow row=   sheet.createRow((short)i);
//row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("id")));
row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("epoch")));
row.createCell((short) 1).setCellValue(Integer.toString(rs.getInt("hidden_layer")));
row.createCell((short) 2).setCellValue(Integer.toString(rs.getInt("learning_rate")));
row.createCell((short) 3).setCellValue(Integer.toString(rs.getInt("momentum")));
row.createCell((short) 4).setCellValue(rs.getString("MSE"));
row.createCell((short) 5).setCellValue(rs.getString("waktu"));
row.createCell((short) 6).setCellValue(rs.getString("status"));
row.createCell((short) 7).setCellValue(rs.getString("memorisasi"));
row.createCell((short) 8).setCellValue(rs.getString("generalisasi"));

i++;
}
FileOutputStream fileOut =  new FileOutputStream(filename);
hwb.write(fileOut);
fileOut.close();
System.out.println("File excel telah berhasil dibuat!");
JOptionPane.showMessageDialog(null, "File excel 'data_hasil.xls' telah berhasil dibuat", "InfoBox ", JOptionPane.INFORMATION_MESSAGE);
} catch ( Exception ex ) {
    System.out.println(ex);

}
}

    }}