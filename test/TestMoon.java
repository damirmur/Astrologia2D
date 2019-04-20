
import astro.Setting;
import java.util.ArrayList;
import swisseph.*;
import util.Utilites;

// When is Moon rising on May 8, 2014 in India
// 80.1 E, 13.08 N in Krishnamurti ayanamsa system
//
// - "Moon rising" means, ascendant is at 0 deg. longitude
// - This is a transit calculation.
// - Transit calculations over AC or similar are done with
//   the TCHouses TransitCalculator
// - Times are UTC always
public class TestMoon {

    private static double degreeToDouble(int grade, int min) {
        return grade + min / 60;
    }

    public static enum TypeMoonEvent {
        SIGN,
        TRANSIT,
        NOCOURSE,
        DAY
    }

    private static class MoonEvent {

        double dtev;
        TypeMoonEvent typeev;
        int el;
        double value;

        public MoonEvent(double dtev, TypeMoonEvent typeev, int el, double value) {
            this.dtev = dtev;
            this.typeev = typeev;
            this.el = el;
            this.value = value;
        }
    }

    private static final double tzOffset = 3.0;

    public static void main(String[] p) {
//        MoonMonth();
        MoonPeriod(4, 2019);

    }

    static void MoonPeriod(int month, int year) {
//MoonEvent moomev
        ArrayList<MoonEvent> moonev = new ArrayList();

        int month1 = month + 1;
        month1 = ((month1 < 13) ? month1 : 1);
        int year1 = ((month1 == 1) ? year + 1 : year);
        SweDate sd = new SweDate(year, month, 1, -tzOffset, true);
        sd.setCalendarType(sd.SE_GREG_CAL, sd.SE_KEEP_DATE);
        SweDate sd1 = new SweDate(year1, month1, 1, -tzOffset, true);
        sd1.setCalendarType(sd.SE_GREG_CAL, sd.SE_KEEP_DATE);
        double jt0 = sd.getJulDay();
        double jt1 = sd1.getJulDay() - 0.000000001;
        System.out.println(printDate(jt0) + " " + printDate(jt1));
        SwissEph sw = new SwissEph(Setting.swepath);

        //sign
        StringBuffer serr = new StringBuffer();
        double x2[] = new double[6];
        long iflag, iflgret;
        iflag = SweConst.SEFLG_SPEED;
        iflgret = sw.swe_calc_ut(jt0, SweConst.SE_MOON, (int) iflag, x2, serr);
        double moonpos = x2[0];
        int num = ((int) x2[0] / 30) * 30;
//        System.out.println(printDate(jt0) + " " + num);
        int fl = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE;
        boolean backwards = true;
        TransitCalculator tcz = new TCPlanet(sw, SweConst.SE_MOON, fl, 0);
        double tempdt = jt0;
        while (tempdt <= jt1) {
            num = (num >= 360) ? 0 : num;
            tcz.setOffset(num);
            tempdt = sw.getTransitUT(tcz, tempdt + 0.1, backwards);
            moonev.add(new MoonEvent(tempdt, TypeMoonEvent.SIGN, num, 0));
            backwards = false;
            num = num + 30;
        }
        //moon transit
//        iflgret = sw.swe_calc_ut(jt0, SweConst.SE_SUN, (int) iflag, x2, serr);
//        double sunpos = x2[0];
//        int angle = (int) ((sunpos > moonpos) ? (sunpos - moonpos) : (moonpos - sunpos));
//        System.out.println(printDate(jt0) + " " + angle);
//        num = 1;
//        for (int i = 1; i < asp2.length; i++) {
//            if (angle < asp2[i]) {
//                break;
//            }
//            num = i + 1;
//        }
//        if (num == asp2.length - 1) {
//            num = 0;
//        }
//        System.out.println(asp2[num] + " " + angle);
//
        fl = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE;
        int pl[] = Setting.pl_conf;
        for (int p = 0; p < pl.length - 1; p++) {
            if (pl[p] == 1) {
                continue;
            }
            TransitCalculator tc = new TCPlanetPlanet(sw, SweConst.SE_MOON, pl[p], fl, 0);
            double asp2[]=Utilites.asp2(Setting.aspMajor);
            for (int i = 0; i < asp2.length - 1; i++) {
                tempdt = jt0;
                while (tempdt <= jt1) {
                    tc.setOffset(asp2[i]);
                    tempdt = sw.getTransitUT(tc, tempdt + 0.01, false);
                    if (tempdt <= jt1) {
                        moonev.add(new MoonEvent(tempdt, TypeMoonEvent.TRANSIT, pl[p], asp2[i]));
                    }
                }
            }
        }
        //Moon without course
        //moon day
        for (int i = 0; i < moonev.size(); i++) {
            System.out.println(printDate(moonev.get(i).dtev + tzOffset / 24) + " " + moonev.get(i).typeev + " " + moonev.get(i).el + " " + moonev.get(i).value);
        }

    }

    public TestMoon() {
    }

    static void MoonMonth() {
        Double[] md;
        ArrayList<Double> moondays = new ArrayList();
        SwissEph sw = new SwissEph("./ephe");
        SweDate sd = new SweDate(2019, 04, 15, 12 - tzOffset, true);
        double date = sd.getJulDay();
        System.out.println("Start searching on    " + printDate(sd.getJulDay() + tzOffset / 24.) + " +3GMT");
        sd.makeValidDate();
        System.out.println("                     (" + printDate(sd.getJulDay()) + " UTC)");
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
        double previousTransitUT = sw.getTransitUT(tc, sd.getJulDay(), backwards);//forward
        System.out.println("New moon backwards on " + printDate(previousTransitUT + tzOffset / 24.0) + " Msk");
        double nextTransitUT = sw.getTransitUT(tc, sd.getJulDay(), false);//forward
        System.out.println("New moon forward on " + printDate(nextTransitUT + tzOffset / 24.0) + " Msk");
        int pl = SweConst.SE_MOON;
        fl = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE | SweConst.SEFLG_TRANSIT_SPEED;
        int rsmi = 0;
        double lon = degreeToDouble(37, 35);
        double lat = degreeToDouble(55, 45);
        double[] geopos = {lon, lat, 0};
//        double[] geopos = {55.45,37.35, 0};
        DblObj tret = new DblObj();
        StringBuffer serr = new StringBuffer();
        sd.setJulDay(previousTransitUT);
        moondays.add(previousTransitUT);
        double monday = sd.getJulDay();
        while (sd.getJulDay() < nextTransitUT) {
            sw.swe_rise_trans(sd.getJulDay(), pl, null, fl, SweConst.SE_CALC_RISE, geopos, 0, 0, tret, serr);
            if ((date >= monday) & (date < tret.val)) {
                System.out.println(numday + " MoonDay " + printDate(date) + " Msk");
            }
            monday = tret.val + 0.1;
            if (sd.getJulDay() > tret.val) {
                continue;
            }
            if (nextTransitUT < tret.val) {
                moondays.add(nextTransitUT);
                break;
            }
            moondays.add(tret.val);

            numday++;
            System.out.println(numday);
            System.out.println("Rise of moon on " + printDate(tret.val + tzOffset / 24.0) + " Msk");
            sw.swe_rise_trans(sd.getJulDay(), pl, null, fl, SweConst.SE_CALC_SET, geopos, 0, 0, tret, serr);
            System.out.println("Set of moon on " + printDate(tret.val + tzOffset / 24.0) + " Msk");
            sw.swe_rise_trans(sd.getJulDay(), pl, null, fl, SweConst.SE_CALC_ITRANSIT, geopos, 0, 0, tret, serr);
            System.out.println("Meridian of moon on " + printDate(tret.val + tzOffset / 24.0) + " Msk");
            sw.swe_rise_trans(sd.getJulDay(), pl, null, fl, SweConst.SE_CALC_MTRANSIT, geopos, 0, 0, tret, serr);
            System.out.println("Meridian of moon on " + printDate(tret.val + tzOffset / 24.0) + " Msk");
            sd.setJulDay(monday);
        }
        md = moondays.toArray(new Double[moondays.size()]);
        for (int i = 0; i < md.length; i++) {
            System.out.println(i + " " + printDate(md[i]));
        }
        System.out.println("Noon to point");
//sign
        ArrayList<Double> lzod = new ArrayList();
        fl = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE;
        backwards = true;
        TransitCalculator tcz = new TCPlanet(sw, SweConst.SE_MOON, fl, 0);
        double daysmoon = sw.getTransitUT(tcz, date, backwards);
        double point = 0.0;
        backwards = false;
        for (int i = 0; i < 13; i++) {
            point = i * 30;
            tcz.setOffset(point);
            daysmoon = sw.getTransitUT(tcz, daysmoon - 0.1, false);
            lzod.add(daysmoon);//forward
            System.out.println(point + " " + printDate(daysmoon));
        }
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
