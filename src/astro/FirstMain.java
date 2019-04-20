/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import java.io.FileNotFoundException;
import swisseph.*;

/**
 *
 * @author Admin
 */
public class FirstMain {


    SwissEph sw = new SwissEph();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
//        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
//        System.setOut(out);
        SwissEph sw = new SwissEph();
        sw.swe_set_ephe_path(Setting.swepath);
        if (args.length != 0) {
        ;}
        else {
            GlobalWindow glW = new GlobalWindow();
            glW.setVisible(true);

        };

    }

    public static void Close() {
//        sw.swe_close();
        System.exit(0);
    }

}
