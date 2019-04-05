/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextArea;

// Referenced classes of package com.ivstars.astrology.util:
//            AstrologyFonts

public class TestFonts
{

            public TestFonts()
            {
            }

            public static void main(String args[])
            {
/*  17*/        new AstrologyFonts();
/*  18*/        (new TestFonts()).testZodiacFont();
            }

            public void testZodiacFont()
            {
/*  22*/        JFrame frame = new JFrame("Font test");
/*  23*/        JTextArea ta = new JTextArea(5, 8);
/*  25*/        ta.setFont(AstrologyFonts.getFont("Astro.ttf").deriveFont(36F));
/*  26*/        ta.setText("abcdefg\nhijklnm\nopqrst\nuvwxyz".toUpperCase());
/*  27*/        frame.getContentPane().add(ta, "North");
/*  28*/        JTextArea tb = new JTextArea(5, 8);
/*  30*/        tb.setFont(AstrologyFonts.getFont("Astronomic Signs St.ttf").deriveFont(36F));
/*  31*/        tb.setText("abcdefg\nhijklnm\nopqrst\nuvwxyz".toUpperCase());
/*  32*/        frame.getContentPane().add(tb, "South");
/*  33*/        frame.pack();
/*  34*/        frame.setVisible(true);
/*  35*/        frame.setDefaultCloseOperation(2);
            }
}
