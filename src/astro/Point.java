/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;
import static astro.Setting.zodName;
import swisseph.*;

/**
 *
 * @author Admin
 */
public class Point {
    private int swe_n;
    private double xx[];

    public Point(int swe_n) {
        this.xx = new double[6];
        this.swe_n = swe_n;
    }
    public Point(int swe_n, double[] xx) {
        this.xx = new double[6];
        this.swe_n = swe_n;
        this.xx[0] = xx[0];
        this.xx[1] = xx[1];
        this.xx[2] = xx[2];
        this.xx[3] = xx[3];
        this.xx[4] = xx[4];
        this.xx[5] = xx[5];
    }


    public int getSwe_n() {
        return swe_n;
    }
    public static String getSwe_name(int i) {
          SwissEph sw = new SwissEph();

        return sw.swe_get_planet_name(i);
    }
    public String getSwe_name() {
          SwissEph sw = new SwissEph();

        return sw.swe_get_planet_name(swe_n);
    }

    public double getPos() {
        return xx[0];
    }
    public double[] getXX() {
        return xx;
    }

    public double getSpeed() {
        return xx[3];
    }
    
    public String getNote() {
        int num=((int)this.xx[0])/30;
        int deg=(int)this.xx[0]-num*30;
        double y=(this.xx[0]-Math.floor(this.xx[0]));
        int min=(int)Math.round(y*60);
        String note=((deg<10)?(" "):(""))+Integer.toString(deg);
        note=note+zodName[num];
        note=note+Integer.toString(min);
        note=note+((this.xx[3]<0)?("R"):(" "));
        return note;
    }
    public String getAfontNote() {
        int num=((int)this.xx[0])/30;
        int deg=(int)this.xx[0]-num*30;
        double y=(this.xx[0]-Math.floor(this.xx[0]));
        int min=(int)Math.round(y*60);
        String note=((deg<10)?(" "):(""))+Integer.toString(deg);
        note=note+Setting.zod_afont.get(num);
        note=note+Integer.toString(min);
//        note=note+((this.xx[3]<0)?("R"):(" "));
        return note;
    }
    public boolean getRetro(){
    boolean r=((this.xx[3]<0)?(true):(false));
    return r;
    }
    /**
     * @param swe_n the swe_n to set
     */
    public void setSwe_n(int swe_n) {
        this.swe_n = swe_n;
    }

    public void set(int swe_n, double[] xx) {
        this.swe_n = swe_n;
        this.xx[0] = xx[0];
        this.xx[1] = xx[1];
        this.xx[2] = xx[2];
        this.xx[3] = xx[3];
        this.xx[4] = xx[4];
        this.xx[5] = xx[5];
    }

    /**
     * @param xx the xx to set
     */
    public void setXx(double[] xx) {
        this.xx[0] = xx[0];
        this.xx[1] = xx[1];
        this.xx[2] = xx[2];
        this.xx[3] = xx[3];
        this.xx[4] = xx[4];
        this.xx[5] = xx[5];
    }


}
