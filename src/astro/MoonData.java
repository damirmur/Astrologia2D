/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import swisseph.*;

//import swisseph.*;
public class MoonData {

    private double moonday;
    private double[] moonMonth;
    private double[] moonPhases;

    private static double degreeToDouble(int grade, int min) {
        return grade + min / 60;
    }

    public MoonData(LocalDateTime dt) {
        int day = dt.getDayOfMonth();
        int month = dt.getMonthValue();
        int year = dt.getYear();
        if (Setting.typeMoonCal == Setting.TypeMoonCal.Degree12) {
            this.moonday = Moon_Jyotiṣa(dt);
        } else {
            ;
        }
        System.out.println(dt + " " + this.moonday);
    }

    public static double Moon_Jyotiṣa(LocalDateTime dt) {
        double md = 0;
        int[] pl = {0, 1};
        Cosmogram cs = new Cosmogram(dt, pl);
        md = (int) Math.floor((cs.getPoints()[1].getPos() - cs.getPoints()[0].getPos()) / 12 + 1);
        return md;
    }

    public static double[] Moon_Phases(SwissEph sw, LocalDateTime dt, double tzOffset) {
        double mp[]=new double[4];
        int[] phase={0,90,180,270};
        if (sw == null) {
            sw = new SwissEph("./ephe");
        }
        SweDate jDate = new SweDate(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), dt.getHour() + dt.getMinute() / 60.0 + dt.getSecond() / 3600 - tzOffset, true);
        jDate.makeValidDate();
        System.out.println("                     (" + printDate(jDate.getJulDay()) + " UTC)");
        int fl = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE;
        TransitCalculator tc = new TCPlanetPlanet(sw, SweConst.SE_MOON, SweConst.SE_SUN, fl, 0);
        boolean backwards = true;
        double previousTransitUT = sw.getTransitUT(tc, jDate.getJulDay(), backwards);
        return mp;
    }

    public static Double[] Moon_Rise(SwissEph sw, LocalDateTime dt, double lat, double lon, double tzOffset) {
        Double[] md;
        ArrayList<Double> moondays = new ArrayList();
        if (sw == null) {
            sw = new SwissEph("./ephe");
        }
        SweDate jDate = new SweDate(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), dt.getHour() + dt.getMinute() / 60.0 + dt.getSecond() / 3600 - tzOffset, true);
        double date = jDate.getJulDay();
        System.out.println("Start searching on    " + printDate(jDate.getJulDay() + tzOffset / 24.) + " +3GMT");
        jDate.makeValidDate();
        System.out.println("                     (" + printDate(jDate.getJulDay()) + " UTC)");
        int fl = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE;
        TransitCalculator tc = new TCPlanetPlanet(
                sw,
                SweConst.SE_MOON,
                SweConst.SE_SUN,
                fl,
                0);
        boolean backwards = true;
        int numday = 1;
        System.out.println(numday);
        double previousTransitUT = sw.getTransitUT(tc, jDate.getJulDay(), backwards);//forward
        System.out.println("New moon backwards on " + printDate(previousTransitUT + tzOffset / 24.0) + " Msk");
        double nextTransitUT = sw.getTransitUT(tc, jDate.getJulDay(), false);//forward
        System.out.println("New moon forward on " + printDate(nextTransitUT + tzOffset / 24.0) + " Msk");
        int pl = SweConst.SE_MOON;
        fl = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE | SweConst.SEFLG_TRANSIT_SPEED;
        int rsmi = 0;
//        double lon = degreeToDouble(37, 35);
//        double lat = degreeToDouble(55, 45);
        double[] geopos = {lon, lat, 0};
        DblObj tret = new DblObj();
        StringBuffer serr = new StringBuffer();
        jDate.setJulDay(previousTransitUT);
        moondays.add(previousTransitUT);
        double monday = jDate.getJulDay();
        while (jDate.getJulDay() < nextTransitUT) {
            sw.swe_rise_trans(jDate.getJulDay(), pl, null, fl, SweConst.SE_CALC_RISE, geopos, 0, 0, tret, serr);
            if ((date >= monday) & (date < tret.val)) {
                System.out.println(numday + " MoonDay " + printDate(date) + " Msk");
            }
            monday = tret.val + 0.1;
            if (jDate.getJulDay() > tret.val) {
                continue;
            }
            if (nextTransitUT < tret.val) {
                continue;
            }

            numday++;
            moondays.add(tret.val);
            System.out.println(numday);
            System.out.println("Rise of moon on " + printDate(tret.val + tzOffset / 24.0) + " Msk");
            sw.swe_rise_trans(jDate.getJulDay(), pl, null, fl, SweConst.SE_CALC_SET, geopos, 0, 0, tret, serr);
            System.out.println("Set of moon on " + printDate(tret.val + tzOffset / 24.0) + " Msk");
            sw.swe_rise_trans(jDate.getJulDay(), pl, null, fl, SweConst.SE_CALC_ITRANSIT, geopos, 0, 0, tret, serr);
            System.out.println("Meridian of moon on " + printDate(tret.val + tzOffset / 24.0) + " Msk");
            sw.swe_rise_trans(jDate.getJulDay(), pl, null, fl, SweConst.SE_CALC_MTRANSIT, geopos, 0, 0, tret, serr);
            System.out.println("Meridian of moon on " + printDate(tret.val + tzOffset / 24.0) + " Msk");
            jDate.setJulDay(monday);
        }
        moondays.add(nextTransitUT);
        md = moondays.toArray(new Double[moondays.size()]);
        for (int i = 0; i < md.length; i++) {
            System.out.println(i + " " + md[i]);
        }
        return md;
    }

    public double getMonday() {
        return moonday;
    }

    static String printDate(double jd) {
        SweDate sd = new SweDate(jd);
        double time = sd.getHour();
        int hour = (int) time;
        time = 60 * (time - hour);
        int min = (int) time;
        double sec = 60 * (time - min);

        return String.format("%4s-%02d-%02d %2d:%02d:%05.2f", sd.getYear(), sd.getMonth(), sd.getDay(), hour, min, sec);
    }

}
