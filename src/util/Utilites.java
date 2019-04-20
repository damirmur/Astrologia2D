/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import swisseph.SweDate;

/**
 *
 * @author Admin
 */
public class Utilites {

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
