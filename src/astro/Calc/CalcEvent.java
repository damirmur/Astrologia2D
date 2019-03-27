/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro.Calc;

import astro.Setting;
import astro.Calc.CalcAspect;
import astro.Point;
import astro.Aspect;
import java.time.LocalDate;
import java.util.ArrayList;
import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissData;
import swisseph.SwissEph;

/**
 *
 * @author Admin
 */
public class CalcEvent {

    public static void PeriodZod(LocalDate dateB, LocalDate dateE, int interval, int[] pls, int[] zods) {
        ArrayList<LocalDate> datePer = new ArrayList();
        if ((dateB == null | dateE == null) | (dateB.isAfter(dateE))) {
            return;
        }
        LocalDate dt = dateB;
        SwissEph sw = new SwissEph();
        SweDate sd = new SweDate();
        SwissData swed = new SwissData();
        StringBuffer serr = new StringBuffer();
        double jut;
        double tjd, te, x2[] = new double[6];
        long iflag, iflgret;
        iflag = SweConst.SEFLG_SPEED;
        sd.setCalendarType(sd.SE_GREG_CAL, sd.SE_KEEP_DATE);
//        System.out.print(dt + "\n");
//        System.out.print(dateE + "\n");
//        long startTime = System.currentTimeMillis();
        while (dt.isBefore(dateE)) {
            jut = 0;
            sd.setDate(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), jut);
            tjd = sd.getJulDay();
            te = tjd + sd.getDeltaT(tjd);
            for (int pl : pls) {
                iflgret = sw.swe_calc(te, pl, (int) iflag, x2, serr);
                if (iflgret < 0) {
                    System.out.print("error: " + serr.toString() + "\n");
                } else if (iflgret != iflag) {
                    System.out.print("warning: iflgret != iflag. " + serr.toString() + "\n");
                }
                for (int zd : zods) {
                    if ((x2[0] >= Setting.zodGradus[zd]) & (x2[0] < (Setting.zodGradus[zd] + 30))) {
//                        System.out.print(pl + " " + zd + " " + dt + "\n");
                    }
                }
            }
            dt = dt.plusDays(interval);
        }
//        long timeSpent = System.currentTimeMillis() - startTime;
//        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
    }

    public static void PeriodAsp(LocalDate dateB, LocalDate dateE, int interval, int[] pls, int[] asps) {
        ArrayList<LocalDate> datePer = new ArrayList();
        if ((dateB == null | dateE == null) | (dateB.isAfter(dateE))) {
            return;
        }
        LocalDate dt = dateB;
        SwissEph sw = new SwissEph();
        SweDate sd = new SweDate();
        SwissData swed = new SwissData();
        StringBuffer serr = new StringBuffer();
        double jut;
        double tjd, te, x2[] = new double[6];
        long iflag, iflgret;
        iflag = SweConst.SEFLG_SPEED;
        sd.setCalendarType(sd.SE_GREG_CAL, sd.SE_KEEP_DATE);
        Point[] ps = new Point[pls.length];
        for (int x = 0; x < pls.length; x++) {
            ps[x] = new Point(pls[x]);
        }
//        System.out.print(dt + "\n");
//        System.out.print(dateE + "\n");
//        long startTime = System.currentTimeMillis();
        while (dt.isBefore(dateE)) {
            jut = 0;
            sd.setDate(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), jut);
            tjd = sd.getJulDay();
            te = tjd + sd.getDeltaT(tjd);
            for (int x = 0; x < pls.length; x++) {
                iflgret = sw.swe_calc(te, pls[x], (int) iflag, x2, serr);
                ps[x].setXx(x2);
                if (iflgret < 0) {
                    System.out.print("error: " + serr.toString() + "\n");
                } else if (iflgret != iflag) {
                    System.out.print("warning: iflgret != iflag. " + serr.toString() + "\n");
                }
//                System.out.print(ps[x].getSwe_name() + " " + ps[x].getPos() + " ");
            }
//            System.out.println(dt);
            Aspect[] tasp = CalcAspect.SingleCardAspect(ps, asps, Setting.orbNat);
//            for (Aspect asp : tasp) {
//                System.out.println(asp.getAsp() + " " + asp.getP()[0].getSwe_name() + " " + asp.getP()[1].getSwe_name());
//            };

            dt = dt.plusDays(interval);
        }
//        long timeSpent = System.currentTimeMillis() - startTime;
//        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
    }
    public static void PeriodTrAsp(LocalDate dateB, LocalDate dateE, int interval,Point[] ps1, int[] pls, int[] asps) {
        ArrayList<LocalDate> datePer = new ArrayList();
        if ((dateB == null | dateE == null) | (dateB.isAfter(dateE))) {
            return;
        }
        LocalDate dt = dateB;
        SwissEph sw = new SwissEph();
        SweDate sd = new SweDate();
        SwissData swed = new SwissData();
        StringBuffer serr = new StringBuffer();
        double jut;
        double tjd, te, x2[] = new double[6];
        long iflag, iflgret;
        iflag = SweConst.SEFLG_SPEED;
        sd.setCalendarType(sd.SE_GREG_CAL, sd.SE_KEEP_DATE);
        Point[] ps = new Point[pls.length];
        for (int x = 0; x < pls.length; x++) {
            ps[x] = new Point(pls[x]);
        }
//        System.out.print(dt + "\n");
//        System.out.print(dateE + "\n");
//        long startTime = System.currentTimeMillis();
        while (dt.isBefore(dateE)) {
            jut = 0;
            sd.setDate(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), jut);
            tjd = sd.getJulDay();
            te = tjd + sd.getDeltaT(tjd);
            for (int x = 0; x < pls.length; x++) {
                iflgret = sw.swe_calc(te, pls[x], (int) iflag, x2, serr);
                ps[x].setXx(x2);
                if (iflgret < 0) {
                    System.out.print("error: " + serr.toString() + "\n");
                } else if (iflgret != iflag) {
                    System.out.print("warning: iflgret != iflag. " + serr.toString() + "\n");
                }
//                System.out.print(ps[x].getSwe_name() + " " + ps[x].getPos() + " ");
            }
//            System.out.println(dt);
            Aspect[] tasp = CalcAspect.DualCardsAspect(ps1, ps, asps, Setting.orbNat);
//            for (Aspect asp : tasp) {
//                System.out.println(asp.getAsp() + " " + asp.getP()[0].getSwe_name() + " " + asp.getP()[1].getSwe_name());
//            };

            dt = dt.plusDays(interval);
        }
//        long timeSpent = System.currentTimeMillis() - startTime;
//        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
    }


}
