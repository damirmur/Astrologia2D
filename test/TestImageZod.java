
import astro.Goroscop;
import astro.graphics.ImgGoroscop;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JRootPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author Admin
 */
public class TestImageZod {

    public static int num = 0;
    public static BufferedImage bimg;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("Пример украшения        ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        
        frame.add(new ImgGoroscop(new Goroscop(),600).getJpanel());
        frame.pack();
        frame.repaint();
        frame.revalidate();
        frame.setVisible(true);
    }

}
