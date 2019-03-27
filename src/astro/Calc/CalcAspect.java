/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro.Calc;

import astro.Point;
import astro.Aspect;
import java.util.ArrayList;


/**
 *
 * @author Admin
 */
public class CalcAspect {

    public static Aspect[] SingleCardAspect(Point[] ps, int[] asp, int orb) {
        ArrayList<Aspect> aspects = new ArrayList();
        double a;
        for (int i = 0; i < (ps.length - 1); i++) {
            for (int j = i + 1; j < ps.length; j++) {
                a = ((ps[i].getPos() - ps[j].getPos()) > 0) ? (ps[i].getPos() - ps[j].getPos()) : (ps[j].getPos() - ps[i].getPos());
                if (a > (180 + orb)) {
                    a = a - 180;
                };
//                System.out.print(ps[j].getSwe_n() + " ");
                for (int a1 = 0; a1 < (asp.length); a1++) {
                    if ((a >= (asp[a1] - orb)) & (a <= (asp[a1] + orb))) {

                        aspects.add(new Aspect(asp[a1], ps[i], ps[j]));
//                        System.out.print(+a + " ");
                    }
                };
            }
            //        System.out.println(ps[i].getSwe_n());
        }
        Aspect[] arrasp =Aspect.Aspects(aspects);
        return arrasp;
    }

    public static Aspect[] DualCardsAspect(Point[] ps1, Point[] ps2, int[] asp, int orb) {
        ArrayList<Aspect> aspects = new ArrayList();
        double a;
        for (int i = 0; i < (ps1.length); i++) {
            for (int j = 0; j < ps2.length; j++) {
                a = ((ps1[i].getPos() - ps2[j].getPos()) > 0) ? (ps1[i].getPos() - ps2[j].getPos()) : (ps2[j].getPos() - ps1[i].getPos());
                if (a > (180 + orb)) {
                    a = a - 180;
                };
//                System.out.print(ps[j].getSwe_n() + " ");
                for (int a1 = 0; a1 < (asp.length); a1++) {
                    if ((a >= (asp[a1] - orb)) & (a <= (asp[a1] + orb))) {

                        aspects.add(new Aspect(asp[a1], ps1[i], ps2[j]));
//                        System.out.print(+a + " ");
                    }
                };
            }
            //        System.out.println(ps[i].getSwe_n());
        }
        Aspect[] arrasp =Aspect.Aspects(aspects);
        return arrasp;
    }


}
