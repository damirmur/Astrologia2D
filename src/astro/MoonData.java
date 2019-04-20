/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import swisseph.*;
import util.Utilites;

//import swisseph.*;
public class MoonData {

    private SwissEph sw;
    private SweDate sd;
    private double lat;
    private double lon;
    private double tzOffset;
    private int moonday;
    private double[] moonMonth;
    private double[] moonPhases;
//    private double[] moonZodiacPhases;

    public int getPhase() {
        int ph = 1;
        double jd = this.sd.getJulDay();
        for (int i = 1; i < this.moonPhases.length; i++) {
            if (jd < this.moonPhases[i]) {
                break;
            }
            ph = i + 1;
        }
        return ph;
    }

    private int getmoondayRise() {
        int md = 0;
        double jd = this.sd.getJulDay();
        for (int i = 1; i < this.moonMonth.length; i++) {
            if (jd < this.moonMonth[i]) {
                break;
            }
            md = i + 1;
        }
        return md;
    }

    private static double degreeToDouble(int grade, int min) {
        return grade + min / 60;
    }

    private static double[] new_Moon(SwissEph sw, SweDate sd) {
        double[] nm = new double[2];
        int fl = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE;
        TransitCalculator tc = new TCPlanetPlanet(
                sw,
                SweConst.SE_MOON,
                SweConst.SE_SUN,
                fl,
                0);
        int numday = 1;
        System.out.println(numday);
        nm[0] = sw.getTransitUT(tc, sd.getJulDay(), true);//backwards
        nm[1] = sw.getTransitUT(tc, sd.getJulDay(), false);//forward
        return nm;
    }

    private static double[] month_Jyotiṣa(SwissEph sw, SweDate sd) {
        double[] monthJ;
        ArrayList<Double> lmonthJ = new ArrayList();
        int fl = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE;
        TransitCalculator tc = new TCPlanetPlanet(
                sw,
                SweConst.SE_MOON,
                SweConst.SE_SUN,
                fl,
                0);
        double daysmoon = sw.getTransitUT(tc, sd.getJulDay(), true);
        lmonthJ.add(daysmoon);//backwards
        for (int i = 1; i < 30; i++) {
            tc.setOffset(12 * i);
            daysmoon = sw.getTransitUT(tc, daysmoon, false);
            lmonthJ.add(daysmoon);//forward
        }
        monthJ = new double[lmonthJ.size()];
        for (int i = 0; i < monthJ.length; i++) {
            monthJ[i] = lmonthJ.get(i);
//            System.out.println(i + " " + printDate(monthJ[i] + Setting.tzOffset / 24));
        }

        return monthJ;
    }

    public MoonData(SwissEph sw, LocalDateTime dt) {
        if (sw == null) {
            this.sw = new SwissEph("./ephe");
        }
        this.tzOffset = Setting.tzOffset;
        this.lat = Setting.lat;
        this.lon = Setting.lon;
        this.sd = new SweDate(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), dt.getHour() + dt.getMinute() / 60.0 + dt.getSecond() / 3600 - this.tzOffset, true);
        this.sd.makeValidDate();
        this.moonPhases = moon_Phases(this.sw, this.sd, this.tzOffset);
        if (Setting.typeMoonCal == Setting.TypeMoonCal.Degree12) {
            this.moonday = (int) Math.floor(day_Jyotiṣa(dt));
            this.moonMonth = month_Jyotiṣa(this.sw, this.sd);
        } else {
            this.moonMonth = moon_Rise(this.sw, this.sd, this.lat, this.lon, this.tzOffset);
            this.moonday = getmoondayRise();
        }
//        System.out.println(dt + " " + this.moonday);
    }

    public static int day_Jyotiṣa(LocalDateTime dt) {
        int md = 0;
        int[] pl = {0, 1};
        Cosmogram cs = new Cosmogram(dt, pl);
        if (cs.getPoints()[1].getPos() >= cs.getPoints()[0].getPos()) {
            md = (int) Math.floor((cs.getPoints()[1].getPos() - cs.getPoints()[0].getPos()) / 12 + 1);
        } else {
            md = (int) Math.floor(30 + (cs.getPoints()[1].getPos() - cs.getPoints()[0].getPos()) / 12 + 1);
        }
        return md;
    }

    public static double[] moon_Phases(SwissEph sw, SweDate sd, double tzOffset) {
        double[] phmd;
        ArrayList<Double> lphase = new ArrayList();
        int fl = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE;
        TransitCalculator tc = new TCPlanetPlanet(
                sw,
                SweConst.SE_MOON,
                SweConst.SE_SUN,
                fl,
                0);
        double daysmoon = sw.getTransitUT(tc, sd.getJulDay(), true);
        lphase.add(daysmoon);//backwards
        for (int i = 1; i <= 4; i++) {
            tc.setOffset(90 * i);
            daysmoon = sw.getTransitUT(tc, daysmoon, false);
            lphase.add(daysmoon);//forward
        }
        phmd = new double[lphase.size()];
        for (int i = 0; i < phmd.length; i++) {
            phmd[i] = lphase.get(i);
        }

        return phmd;
    }
    public double[] moon_zod(SwissEph sw, double jd, double tzOffset) {
        double[] zodm;
        ArrayList<Double> lzod = new ArrayList();
        int fl = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE;
        boolean backwards = true;
        TransitCalculator tcz = new TCPlanet(sw, SweConst.SE_MOON, fl, 0);
        double daysmoon = sw.getTransitUT(tcz, jd, backwards);
        double point = 0.0;
        backwards = false;
        for (int i = 0; i < 13; i++) {
            point = i * 30;
            tcz.setOffset(point);
            daysmoon = sw.getTransitUT(tcz, daysmoon - 0.1, false);
            lzod.add(daysmoon);//forward
//            System.out.println(point + " " + printDate(daysmoon));
        }
        zodm = new double[lzod.size()];
        for (int i = 0; i < zodm.length; i++) {
            zodm[i] = lzod.get(i);
        }

        return zodm;
    }

    public static double[] moon_Rise(SwissEph sw, SweDate sd, double lat, double lon, double tzOffset) {
        double[] md;
        ArrayList<Double> moondays = new ArrayList();
        if (sw == null) {
            sw = new SwissEph("./ephe");
        }
        double date = sd.getJulDay();
        sd.makeValidDate();
        int fl = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE;
        TransitCalculator tc = new TCPlanetPlanet(
                sw,
                SweConst.SE_MOON,
                SweConst.SE_SUN,
                fl,
                0);
        boolean backwards = true;
        double previousTransitUT = sw.getTransitUT(tc, sd.getJulDay(), backwards);//forward
        double nextTransitUT = sw.getTransitUT(tc, sd.getJulDay(), false);//forward
        int pl = SweConst.SE_MOON;
        fl = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE | SweConst.SEFLG_TRANSIT_SPEED;
        int rsmi = 0;
        double[] geopos = {lon, lat, 0};
        DblObj tret = new DblObj();
        StringBuffer serr = new StringBuffer();
        date = previousTransitUT;
        moondays.add(previousTransitUT);
        double monday = sd.getJulDay();
        while (date < nextTransitUT) {
            sw.swe_rise_trans(date, pl, null, fl, SweConst.SE_CALC_RISE, geopos, 0, 0, tret, serr);
            monday = tret.val + 0.1;
            if (date > tret.val) {
                continue;
            }
            if (nextTransitUT < tret.val) {
                break;
            }
            moondays.add(tret.val);
            sw.swe_rise_trans(sd.getJulDay(), pl, null, fl, SweConst.SE_CALC_SET, geopos, 0, 0, tret, serr);
            date = monday;
        }
        moondays.add(nextTransitUT);
        md = new double[moondays.size()];
        for (int i = 0; i < md.length; i++) {
            md[i] = moondays.get(i);
//            System.out.println(i + " " + md[i]);
        }
        return md;
    }

    public double getMonday() {
        return moonday;
    }

    public String getAllMonth() {
        String text = "\r\n";
        text = text + "Local date " + Utilites.printDate(this.sd.getJulDay()+ this.tzOffset / 24.0) + "\r\n";
        text = text + "" + "\r\n";
        text = text + this.moonday + " day Moon \r\n";
        text = text + "" + "\r\n";
        text = text + "Phases Moon" + "\r\n";
        text = text + "" + "\r\n";
        for (int i = 0; i < this.moonPhases.length - 1; i++) {
            text = text + (i + 1) + " phasa Moon " + Utilites.printDate(this.moonPhases[i]+ this.tzOffset / 24.0) + "\r\n";
        }
        text = text + "" + "\r\n";
        text = text + "Days Moon " +Setting.typeMoonCal.toString()+ "\r\n";
        text = text + "" + "\r\n";
        for (int i = 0; i < this.moonMonth.length - 1; i++) {
            text = text + (i + 1) + " day Moon " + Utilites.printDate(this.moonMonth[i]+ this.tzOffset / 24.0) + "\r\n";
        }
        text = text + "" + "\r\n";
        text = text + "Zodiac Moon" + "\r\n";
        text = text + "" + "\r\n";
        double mz[]=moon_zod(this.sw,this.sd.getJulDay(),this.tzOffset);
        for (int i = 0; i < mz.length - 1; i++) {
            text = text + String.format("%-20s%40s%n",Setting.zodName[i] + " --- Moon ", Utilites.printDate(mz[i]+ this.tzOffset / 24.0));
        }
      
        text = text + "" + "\r\n";
        return text;
    }


}
