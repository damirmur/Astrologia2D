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
import swisseph.SwissephException;

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
    private char houseSystem;
    private double lon;
    private double lat;

    public Goroscop() {
        this.dt = LocalDateTime.now();
        this.pnumbers = Setting.pl_conf;
        this.points = new Point[Setting.pl_conf.length];
        this.house = new Houses(Setting.houseSystem);
        this.lat = Setting.lat;
        this.lon = Setting.lon;
        this.houseSystem = Setting.houseSystem;
        CalcPoint(this.dt, this.pnumbers, this.points, this.houseSystem, this.lat, this.lon, this.house);
        this.aspects = CalcAspect.SingleCardAspect(points, Setting.aspMajor, Setting.orbNat);
    }

    public Goroscop(LocalDateTime dt) {
        this.dt = dt;
        this.pnumbers = Setting.pl_conf;
        this.points = new Point[Setting.pl_conf.length];
        this.house = new Houses(Setting.houseSystem);
        this.lat = Setting.lat;
        this.lon = Setting.lon;
        this.houseSystem = Setting.houseSystem;
        CalcPoint(this.dt, this.pnumbers, this.points, this.houseSystem, this.lat, this.lon, this.house);
        this.aspects = CalcAspect.SingleCardAspect(points, Setting.aspMajor, Setting.orbNat);
    }

    public Goroscop(LocalDateTime dt, Houses house, int[] pnumbers) {
        this.house = house;
        this.dt = dt;
        this.pnumbers = pnumbers;
        this.points = new Point[pnumbers.length];
        this.lat = Setting.lat;
        this.lon = Setting.lon;
        this.houseSystem = Setting.houseSystem;
        CalcPoint(this.dt, this.pnumbers, this.points, this.houseSystem, this.lat, this.lon, this.house);
        this.aspects = CalcAspect.SingleCardAspect(points, Setting.aspMajor, Setting.orbNat);
    }

    public Goroscop(LocalDateTime dt, int[] pnumbers, char houseSystem, double lat, double lon) {
        this.house = house;
        this.dt = dt;
        this.pnumbers = pnumbers;
        this.points = new Point[pnumbers.length];
        this.lat = lat;
        this.lon = lon;
        this.houseSystem = houseSystem;
        CalcPoint(this.dt, this.pnumbers, this.points, this.houseSystem, this.lat, this.lon, this.house);
        this.aspects = CalcAspect.SingleCardAspect(points, Setting.aspMajor, Setting.orbNat);
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

    private static void CalcPoint(LocalDateTime dt, int[] pnumbers, Point[] points, char hs, double lat, double lon, Houses house) {
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
        if (house != null) {
            double[] cusps = new double[13];
            double[] acsc = new double[10];
            int result = sw.swe_houses(tjd, 0, lat, lon, hs, cusps, acsc);
            if (result == -1) {
                throw new SwissephException(sd.getJulDay(), "Calculation was not possible due to nearness to the polar circle in Koch or Placidus house system or when requesting Gauquelin sectors. Calculation automatically switched to Porphyry house calculation method in this case");
            } else {
                house.set(cusps, hs);
            }
        }

    }

}
