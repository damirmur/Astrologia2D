/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;
import java.awt.Font;
import java.io.PrintStream;
import java.util.Hashtable;

public class AstrologyFonts
{

            public AstrologyFonts()
            {
/*  38*/        cache = new Hashtable(names.length);
/*  39*/        for(int i = 0; i < names.length; i++)
/*  40*/            cache.put(names[i], getFont(names[i]));

            }

            public static Font getFont(String name)
            {
/*  46*/        Font font = null;
/*  47*/        if(cache != null && (font = (Font)cache.get(name)) != null)
/*  49*/            return font;
/*  52*/        String fName = (new StringBuilder("/")).append(name).toString();
/*  54*/        try
                {
/*  54*/            java.io.InputStream is = AstrologyFonts.class.getResourceAsStream(fName);
/*  55*/            font = Font.createFont(0, is);
                }
/*  56*/        catch(Exception ex)
                {
/*  57*/            ex.printStackTrace();
/*  58*/            System.err.println((new StringBuilder(String.valueOf(fName))).append(" not loaded.  Using serif font.").toString());
/*  59*/            font = new Font("serif", 0, 24);
                }
/*  61*/        return font;
            }

            private String names[] = {
/*  33*/        "gezodiac.ttf", "syastro.ttf","Astro.ttf","Astronomic Signs St.ttf"
            };
            private static Hashtable cache;
}
