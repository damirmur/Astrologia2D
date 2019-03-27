/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import java.util.ArrayList;
import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.*;
import swisseph.SwissLib;

/**
 *
 * @author Admin
 */
public class House {

    double h[];
    char houseSystem;
    static double ayansamaConst = 0.0;

    public House(char houseSystem) {
        this.houseSystem = houseSystem;
        int hl = 12;
        switch (houseSystem) {
            case ('0'): {
                break;
            }
            case ('1'): {
                break;
            }
            default: {
//                default_pl(ps);
                break;
            }
        }
        this.h = new double[hl];
        int year = 2011;
        int month = 9;
        int day = 3;
        double longitude = 80 + 17 / 60.0;
        double lattitude = 13 + 5 / 60.0;
        double time = 23 + 55 / 60.0;

        /*Instances of Utility Classes */
        SwissEph swissEph = new SwissEph();
        SweDate sweDate = new SweDate(year, month, day, time, SweDate.SE_GREG_CAL);
        SwissLib swiislib = new SwissLib();
        swissEph.swe_set_sid_mode(SweConst.SE_SIDM_LAHIRI, 0, 0);

    }

    private static void planet_position(double longitude, double lattitude,
            SwissEph swissEph, SweDate sweDate, SwissLib swiislib, int houseSys) {

        /*intialize program variables */
        double[] cusps = new double[13];
        double[] acsc = new double[10];
        double[] xx = new double[6];
        double[] xp = new double[6];
        double[] ipl = new double[6];
        StringBuffer serr = new StringBuffer();
        StringBuffer serr1 = new StringBuffer();
        double planetposition = 0;
        /*Temporary variables*/
        double julDay = sweDate.getJulDay();
        double sidetime = swiislib.swe_sidtime(julDay);

        /*Print input Details */
        System.out.println(sweDate);

        /*Print Sidereal Time*/
        System.out.print("Sidereal Time:");
        System.out.println(sidetime);

        /* Calculate Ascendant */
        double ayansama = swissEph.swe_get_ayanamsa_ut(julDay);
        System.out.print("Ayansama");
        System.out.println(ayansama);

        ayansamaConst = ayansama / 100;

        int iflagConst = SweConst.SEFLG_SIDEREAL;

        int result = swissEph.swe_houses(julDay, iflagConst, lattitude, longitude, houseSys, cusps, acsc);

        System.out.println("Cusp" + cusps[0] + "::" + cusps[1]);

        System.out.print("Ascendant :");
        System.out.println(acsc[0]);
        sidetime = acsc[2];
        System.out.print("ARMC/Sidereal Time:");
        System.out.println(acsc[2]);

        /*get the position of ascendant */
        double ascendant = acsc[0];
        int ascPos = (int) (Math.floor(ascendant / 30));
        System.out.println("Ascendant Position: " + ascPos);

        /* Calculate Ecliptic Obliquity as xp[0] */
        int resultnew = swissEph.swe_calc_ut(julDay, SweConst.SE_ECL_NUT, 0, xp, serr);
        System.out.println(xp[0]);
    }
}
