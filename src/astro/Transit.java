/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;
import swisseph.*;

// Referenced classes of package ru.strijar.astro:
//            Date
public class Transit {
            protected TransitCalculator tc;
            private SwissEph eph;

            protected Transit(SwissEph eph)
            {
/*  18*/        this.eph = eph;
            }

            protected void speed(int spot)
            {
/*  22*/        tc = new TCPlanet(eph, spot, 0x120002, 0.0D);
            }

            protected void lon(int spot)
            {
/*  31*/        tc = new TCPlanet(eph, spot, 0x20002, 0.0D);
            }

            protected void lat(int spot)
            {
/*  40*/        tc = new TCPlanet(eph, spot, 0x40002, 0.0D);
            }

            protected void dec(int spot)
            {
/*  49*/        tc = new TCPlanet(eph, spot, 0x40802, 0.0D);
            }

            protected void angle(int spot1, int spot2)
            {
/*  58*/        tc = new TCPlanetPlanet(eph, spot2, spot1, 0x20002, 0.0D);
            }

            public double nextJD(double jd, boolean back)
            {
/*  74*/        return eph.getTransitET(tc, jd, back);
            }

//            public void next(double jd, boolean back, Date res)
//            {
///*  85*/        res.setJD(nextJD(jd, back));
//            }
//
//            public Date next(double jd, boolean back)
//            {
///*  96*/        Date res = new Date();
///*  98*/        res.setJD(nextJD(jd, back));
///* 100*/        return res;
//            }

            public void setValue(double data)
            {
/* 109*/        tc.setOffset(data);
            }

            public double getValue()
            {
/* 118*/        return tc.getOffset();
            }

}
