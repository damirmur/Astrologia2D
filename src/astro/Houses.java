/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import java.time.LocalDateTime;
import swisseph.*;

/**
 *
 * @author Admin
 */
public class Houses {

    public void set(double[] cusps, char houseSystem) {
        this.houseSystem = houseSystem;
        if (this.h == null) {
            this.h = new double[cusps.length - 1];
        }
        if (cusps.length != this.h.length) {
            this.h = new double[cusps.length - 1];
        }
        for (int i = 1; i < cusps.length; i++) {
            this.h[i - 1] = cusps[i];
        }
    }

    double h[];
    char houseSystem;

    public double getAsc() {
        double asc = 0;
        if (h != null) {
            asc = h[0];
        }
        return asc;
    }

    public Houses(double[] cusps, char houseSystem) {
        this.houseSystem = houseSystem;
        if (this.h == null) {
            this.h = new double[cusps.length - 1];
        }
        if (cusps.length != this.h.length) {
            this.h = new double[cusps.length - 1];
        }
        for (int i = 1; i < cusps.length; i++) {
            this.h[i - 1] = cusps[i];
        }
    }

    public Houses(double[] cusps) {
        this.houseSystem = Setting.houseSystem;
        if (cusps.length != this.h.length) {
            this.h = new double[cusps.length - 1];
        }
        for (int i = 1; i < h.length; i++) {
            this.h[i - 1] = cusps[i];
        }
    }

    public Houses(char houseSystem) {
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
                hl = 12;

//                default_pl(ps);
                break;
            }
        }
        this.h = new double[hl];
    }

    public static Houses houses_position(LocalDateTime dt, double longitude, double lattitude, char houseSys) {
        SwissEph sw = new SwissEph();
        SweDate sd = new SweDate();

        /*intialize program variables */
        double[] cusps = new double[13];
        double[] acsc = new double[10];
        double[] ipl = new double[6];
        StringBuffer serr = new StringBuffer();
        double jut = dt.getHour() + dt.getMinute() / 60.0 + dt.getSecond() / 3600;
        double tjd, te;
        long iflag, iflgret;
        iflag = SweConst.SEFLG_SPEED;
        sd.setDate(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), jut);
        sd.setCalendarType(sd.SE_GREG_CAL, sd.SE_KEEP_DATE);
        tjd = sd.getJulDay();
        te = tjd + sd.getDeltaT(tjd);
        int result = sw.swe_houses(te, 0, lattitude, longitude, houseSys, cusps, acsc);
        if (result == -1) {
            throw new SwissephException(sd.getJulDay(), "Calculation was not possible due to nearness to the polar circle in Koch or Placidus house system or when requesting Gauquelin sectors. Calculation automatically switched to Porphyry house calculation method in this case");
        } else {
            return new Houses(cusps, houseSys);
        }

    }
}
