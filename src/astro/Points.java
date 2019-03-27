/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import java.time.LocalDateTime;

/**
 *
 * @author Admin
 */
public class Points {
    LocalDateTime dt;
    Point[] points;
    public int length;

    public Points() {
        this.dt = LocalDateTime.now();
        this.points = new Point[Setting.pl_conf.length];
        this.length=this.points.length;
//        CalcPoint();
}
    public Points(LocalDateTime dt) {
        this.dt = dt;
        this.points = new Point[Setting.pl_conf.length];
        this.length=this.points.length;

//        CalcPoint();
}

    public Points(LocalDateTime dt, int[] pnumbers) {
        this.dt = dt;
        this.points = new Point[pnumbers.length];
        this.length=this.points.length;
//        CalcPoint();
}
    public void set(LocalDateTime dt) {
        this.dt = dt;
        this.points = new Point[Setting.pl_conf.length];
        this.length=this.points.length;
//        CalcPoint();
}
    public void set(LocalDateTime dt,int[] pnumbers) {
        this.dt = dt;
        this.points = new Point[pnumbers.length];
        this.length=this.points.length;
//        CalcPoint();
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
    
}
