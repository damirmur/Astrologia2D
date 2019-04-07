/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import java.util.ArrayList;
import swisseph.SwissEph;

/**
 *
 * @author Admin
 */
public class Aspect {

    private int asp;
    private Point[] ps = new Point[2];

    public Aspect(int asp, Point[] ps) {
        this.asp = asp;
        this.ps = ps;
    }

    public Aspect(int asp, Point ps0, Point ps1) {
        this.asp = asp;
        this.ps[0] = ps0;
        this.ps[1] = ps1;
    }

    public Aspect() {
    }
    public static String getSym(int i) {
        String a = Integer.toString(i);
        if (Setting.aspect_a2font.containsKey(i)) {
            a = Setting.aspect_a2font.get(i);
        }
        return a;
    }
    public String getSym() {
        String a = Integer.toString(this.asp);
        if (Setting.aspect_a2font.containsKey(this.asp)) {
            a = Setting.aspect_a2font.get(this.asp);
        }
        return a;
    }

    public static Aspect[] Aspects(ArrayList<Aspect> aspects) {
        Aspect[] asp = aspects.toArray(new Aspect[aspects.size()]);
        return asp;
    }

    /**
     * @return the asp
     */
    public int getAsp() {
        return asp;
    }

    /**
     * @return the p
     */
    public Point[] getP() {
        return ps;
    }

}
