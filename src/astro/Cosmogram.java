/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import swisseph.*;
import java.time.LocalDateTime;
import astro.Calc.CalcAspect;

/**
 *
 * @author Admin
 */
public class Cosmogram {

    private LocalDateTime dt;
    private Point[] points;
    private int[] pnumbers;
    private Aspect[] aspects;


    public Cosmogram() {
        this.dt = LocalDateTime.now();
        this.pnumbers=Setting.pl_conf;
        this.points = new Point[Setting.pl_conf.length];
        CalcPoint();
        this.aspects=CalcAspect.SingleCardAspect(points,Setting.aspMajor,Setting.orbNat);
}
    public Cosmogram(LocalDateTime dt) {
        this.dt = dt;
        this.pnumbers=Setting.pl_conf;
        this.points = new Point[Setting.pl_conf.length];
        CalcPoint();
        this.aspects=CalcAspect.SingleCardAspect(points,Setting.aspMajor,Setting.orbNat);
}

    public Cosmogram(LocalDateTime dt, int[] pnumbers) {
        this.dt = dt;
        this.pnumbers = pnumbers;
        this.points = new Point[pnumbers.length];
        CalcPoint();
        this.aspects=CalcAspect.SingleCardAspect(points,Setting.aspMajor,Setting.orbNat);
}
    public void set(LocalDateTime dt) {
        this.dt = dt;
        this.pnumbers = Setting.pl_conf;
        this.points = new Point[Setting.pl_conf.length];
        CalcPoint();
        this.aspects=CalcAspect.SingleCardAspect(points,Setting.aspMajor,Setting.orbNat);
}
    public void set(LocalDateTime dt,int[] pnumbers) {
        this.dt = dt;
        this.pnumbers = pnumbers;
        this.points = new Point[pnumbers.length];
        CalcPoint();
        this.aspects=CalcAspect.SingleCardAspect(points,Setting.aspMajor,Setting.orbNat);
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
        StringBuffer serr = new StringBuffer();
        double jut = dt.getHour()+dt.getMinute()/60.0+dt.getSecond()/3600;
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

    }


}
