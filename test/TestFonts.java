/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Container;
import java.awt.Font;
import java.io.UnsupportedEncodingException;
import java.nio.charset.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import util.AstrologyFonts;

// Referenced classes of package com.ivstars.astrology.util:
//            AstrologyFonts
public class TestFonts {

    public TestFonts() {
    }

    public static void main(String args[]) {
        /*  17*/ new AstrologyFonts();
        /*  18*/ (new TestFonts()).testZodiacFont();
    }

    public void testZodiacFont() {
            //U+E01B7
            /*  22*/ JFrame frame = new JFrame("Font test");
            /*  23*/ JTextArea ta = new JTextArea(5, 8);
            
            /*  25*/ ta.setFont(AstrologyFonts.getFont("Astrologia2D.ttf").deriveFont(36F));
            int[]i={917915};
            String q="";
            while (i[0]<=917999){
                q=q+new String(i, 0, 1);
                i[0]=i[0]+1;
            }
//        try {
//            String q = new String(new int[]{917944}, 0, 1);
//            String q = new String(new byte[]{0, -2}, "UTF-16");
            /*  26*/ ta.setText(q+"\n");
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(TestFonts.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        ta.setText("abcdefg\nhijklnm\nopqrst\nuvwxyz");
/*  27*/ frame.getContentPane().add(ta, "North");
/*  28*/ JTextArea tb = new JTextArea(5, 8);
/*  30*/ tb.setFont(AstrologyFonts.getFont("Astrologia2D.ttf").deriveFont(36F));
/*  31*/ tb.setText("abcdefg\nhijklnm\nopqrst\nuvwxyz".toUpperCase());
/*  32*/ frame.getContentPane().add(tb, "South");
/*  33*/ frame.pack();
/*  34*/ frame.setVisible(true);
/*  35*/ frame.setDefaultCloseOperation(2);

    }
}
