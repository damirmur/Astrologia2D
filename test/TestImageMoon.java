
import astro.Setting;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import util.Utilites;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class TestImageMoon {


    public static int num = 0;
    public static BufferedImage moonBlack_img;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("Пример украшения        ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        frame.setSize(100, 100);
        moonBlack_img = new BufferedImage(52, 52, BufferedImage.TYPE_INT_RGB);
        try {
            moonBlack_img = (ImageIO.read(TestImageMoon.class.getClassLoader().getResource(Setting.fileMoonBl)));
        } catch (IOException ex) {
            Logger.getLogger(TestImageMoon.class.getName()).log(Level.SEVERE, null, ex);
        }
        JLabel lbl = new JLabel(new ImageIcon(Utilites.iconSprite(moonBlack_img, num)));
        lbl.setSize(52, 52);
        lbl.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                num++;
                lbl.setIcon(new ImageIcon(Utilites.iconSprite(moonBlack_img, num)));
                System.out.println(num);
            }
        });
        frame.add(lbl);
        frame.setVisible(true);

    }

}
