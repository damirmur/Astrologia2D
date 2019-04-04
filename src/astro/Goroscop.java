/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import astro.Calc.CalcAspect;
import java.time.LocalDateTime;
import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissData;
import swisseph.SwissEph;

/**
 *
 * @author Admin
 */
public class Goroscop {

    private LocalDateTime dt;
    private Point[] points;
    private int[] pnumbers;
    private Aspect[] aspects;
    private Houses house;

    public Goroscop() {
        this.dt = LocalDateTime.now();
        this.pnumbers = Setting.pl_conf;
        this.points = new Point[Setting.pl_conf.length];
        this.house = new Houses(Setting.houseSystem);
        CalcPoint();
        this.aspects = CalcAspect.SingleCardAspect(points, Setting.aspMajor, Setting.orbNat);
    }

    public Goroscop( LocalDateTime dt) {
        this.dt = dt;
        this.pnumbers = Setting.pl_conf;
        this.points = new Point[Setting.pl_conf.length];
        this.house = new Houses(Setting.houseSystem);
        CalcPoint();
        this.aspects = CalcAspect.SingleCardAspect(points, Setting.aspMajor, Setting.orbNat);
    }

    public Goroscop(Houses house, LocalDateTime dt, int[] pnumbers) {
        this.house = house;
    }

    public Houses getHouse() {
        return house;
    }

    public void setHouse(Houses house) {
        this.house = house;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public Point[] getPoints() {
        return points;
    }

    public double[] getPoint_Pos(int i) {
        return points[i].getXX();
    }

    public Aspect[] getAspects() {
        return aspects;
    }

    private void CalcPoint() {
        SwissEph sw = new SwissEph();
        SweDate sd = new SweDate();
        SwissData swed = new SwissData();
        StringBuffer serr = new StringBuffer();
        double jut = dt.getHour() + dt.getMinute() / 60.0 + dt.getSecond() / 3600;
        double tjd, te, x2[] = new double[6];
        long iflag, iflgret;
        iflag = SweConst.SEFLG_SPEED;
        sd.setDate(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), jut);
        sd.setCalendarType(sd.SE_GREG_CAL, sd.SE_KEEP_DATE);
        tjd = sd.getJulDay();
        te = tjd + sd.getDeltaT(tjd);
        for (int i = 0; i < pnumbers.length; i++) {
            iflgret = sw.swe_calc(te, pnumbers[i], (int) iflag, x2, serr);
            if (iflgret < 0) {
                System.out.print("error: " + serr.toString() + "\n");
            } else if (iflgret != iflag) {
                System.out.print("warning: iflgret != iflag. " + serr.toString() + "\n");
            }
            points[i] = new Point(pnumbers[i], x2);
        }
        if (this.house != null) {
            double[] cusps = new double[13];
            double[] acsc = new double[10];
            int result = sw.swe_houses(tjd, 0, Setting.lat, Setting.lon, this.house.houseSystem, cusps, acsc);
            System.out.println("Cusp" + cusps[0] + "::" + cusps[1]);
           System.out.println("Cusp" + cusps[2] + "::" + cusps[3]);
           System.out.println("Cusp" + cusps[4] + "::" + cusps[5]);
           System.out.println("Cusp" + cusps[6] + "::" + cusps[7]);
           System.out.println("Cusp" + cusps[8] + "::" + cusps[9]);
           System.out.println("Cusp" + cusps[10] + "::" + cusps[11]);
           System.out.println("Cusp" + cusps[12]);
            System.out.print("Ascendant :");
            System.out.println(acsc[0]);
            System.out.println(acsc[1]);
            System.out.println(acsc[2]);
            System.out.println(acsc[3]);
            System.out.println(acsc[4]);
            System.out.println(acsc[5]);
            System.out.println(acsc[6]);
            System.out.println(acsc[7]);
            System.out.println(acsc[8]);
            System.out.println(acsc[9]);

        }

    }

}
