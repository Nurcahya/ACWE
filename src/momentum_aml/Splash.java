/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package momentum_aml;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 *
 * @author NEBULA
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class Splash extends JFrame{
    int a = 0;
    JProgressBar progressBar = new JProgressBar(0, 150);
    JLabel label = new JLabel();
    JLabel label2 = new JLabel();
    private JLabel background;
    Border border = BorderFactory.createTitledBorder("Loading…");

    public Splash() {
        progressBar.setStringPainted(true);
        progressBar.setBorder(border);
        label.setText("KLASIFIKASI AML : M1 M2 M3");
        label.setFont(new Font("Tahoma", 1, 28));
        label.setForeground(Color.RED);
        label2.setText("Nurcahya Pradana T.P. / 388492");
        label2.setFont(new Font("Arial", 1, 20));
        label2.setForeground(Color.BLACK);
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.WHITE);
        Color warnaBorder =  Color.BLACK;
        content.setBorder(BorderFactory.createLineBorder(warnaBorder, 5));
        setLayout(null);
        
        
        background = new JLabel();
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("gambar/splashvert.jpg")));
        background.setBounds(new Rectangle(5, 36, 528, 700));
        
        add(background);
        add(label);
        add(label2);
        label.setBounds(20, 3, 550, 100);
        label2.setBounds(20, 34, 350, 100);
        Component add = add(progressBar);
        progressBar.setBounds(120, 480, 315, 40);
        pack();
        setSize(550, 700);
        
        setVisible(true);
        progressBar.setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        while (a <= 150) {
            progressBar.setValue(a);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
            a += 1;
        }
        this.dispose();
      }

}
    


       
        
        
        
