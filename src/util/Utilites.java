/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import swisseph.SweDate;

/**
 *
 * @author Admin
 */
public class Utilites {
    public static ImageIcon iconRezive(BufferedImage src, int size) {
        ImageIcon imageIcon = new ImageIcon(src.getScaledInstance(size, size, Image.SCALE_DEFAULT));
        return imageIcon;
    }
    public static ImageIcon iconRezive(ImageIcon src, int size) {
        ImageIcon imageIcon = new ImageIcon(src.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
        return imageIcon;
    }

    public static BufferedImage iconSprite(BufferedImage src, int n) {
        BufferedImage p_icon;
        try {
            p_icon = src.getSubimage(0, (int) n * 52, 52, 52);
        } catch(java.awt.image.RasterFormatException e){
            n = 0;
            p_icon = src.getSubimage(0, n * 52, 52, 52);
        }
        return p_icon;
    }

    public static String printDate(double jd) {
        SweDate sd = new SweDate(jd);
        double time = sd.getHour();
        int hour = (int) time;
        time = 60 * (time - hour);
        int min = (int) time;
        double sec = 60 * (time - min);
        if (sec >= 59.99) {
            sec = 0;
            min = min + 1;
        }
        if (min >= 60) {
            min = 0;
            hour = hour + 1;
        }
//        if (hour==24){hour=0; sd=sd.;}

        return String.format("%4s-%02d-%02d %2d:%02d:%05.2f", sd.getYear(), sd.getMonth(), sd.getDay(), hour, min, sec);
    }

    public static int nextcircle(int length, int num) {
        int nnext;
        nnext = ((length - 1) >= num) ? (0) : (num + 1);
        return nnext;
    }

    public static double[] asp2(int[] asp) {
        double[] aspd = new double[asp.length];
        for (int i = 0; i < asp.length; i++) {
            aspd[i] = asp[i];
        }
        return asp2(aspd);
    }

    public static double[] asp2(double[] asp) {
        double[] aspd2 = new double[asp.length * 2 - 1];
        int ij;
        for (int j = 1; j < 3; j++) {
            for (int i = 0; i < asp.length; i++) {
                if (j == 1) {
                    ij = i;
                    aspd2[ij] = asp[i];
                } else {
                    if (i == 0) {
                        continue;
                    }
                    ij = (asp.length + i) - 1;
                    aspd2[ij] = asp[i] + 180;
                }
            }
        }
        return aspd2;
    }

}
