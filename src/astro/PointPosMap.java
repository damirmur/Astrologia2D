/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import java.util.Arrays;
import java.util.Comparator;
import astro.Point;

/**
 *
 * @author Admin
 */
public class PointPosMap {

    public PointPosMap() {
        this.pl = null;
        this.PosGr = 0;
        this.PosD = 0;
        this.PosR = 0;

    }

    public PointPosMap(Point pl, int PosD, int PosR) {
        this.pl = pl;
        this.PosGr = pl.getPos();
        this.PosD = PosD;
        this.PosR = PosR;

    }
    private Point pl;
    private int PosD;
    private int PosR;
    private double PosGr;

    public Point getPl() {
        return pl;
    }

    public void setPl(Point pl) {
        this.pl = pl;
    }

    public int getPosD() {
        return PosD;
    }

    public int getPosR() {
        return PosR;
    }

    public void setPos(int PosD, int PosR) {
        this.setPosD(PosD);
        this.setPosR(PosR);
    }

    /**
     * @return the PosGr
     */
    public double getPosGr() {
        return pl.getPos();
    }

    /**
     * @param PosGr the PosGr to set
     */
    public void setPosGr(Point pl) {
        this.PosGr = pl.getPos();
    }

    public static PointPosMap[] allPointPosMap(Point[] pls) {
        PointPosMap[] aPPM = new PointPosMap[pls.length];
        for (int i = 0; i < pls.length; i++) {
            aPPM[i] = new PointPosMap(pls[i], 0, 0);
        }
        Arrays.sort(aPPM, (o1, o2) -> {
            return Double.compare(o1.getPosGr(), (o2.getPosGr()));
        });

        return aPPM;
    }

    /**
     * @param PosD the PosD to set
     */
    public void setPosD(int PosD) {
        this.PosD = PosD;
    }

    /**
     * @param PosR the PosR to set
     */
    public void setPosR(int PosR) {
        this.PosR = PosR;
    }

    public static PointPosMap[] sortRD_pl(Point[] ps, int a) {
        PointPosMap[] aPPM = allPointPosMap(ps);
        int[] beforePPM = {-1, 0};
        for (PointPosMap aPPM1 : aPPM) {
            aPPM1.setPosD((int) Math.floor(a * aPPM1.getPosGr() / 360));
            if (aPPM1.getPosD() == beforePPM[0]) {
                beforePPM[1] = beforePPM[1] + 1;
            } else {
                beforePPM[1] = 0;
            }
            beforePPM[0] = aPPM1.getPosD();
            aPPM1.setPosR(beforePPM[1]);
        }
        return aPPM;
    }

    public static void bubbleSort(PointPosMap[] arr) {
        /*Внешний цикл каждый раз сокращает фрагмент массива, 
      так как внутренний цикл каждый раз ставит в конец
      фрагмента максимальный элемент*/
        PointPosMap tmp = new PointPosMap();
        for (int i = arr.length - 1; i == 0; i--) {
            if (arr[i] == null) {
                continue;
            }
            for (int j = 0; j < i; j++) {
                /*Сравниваем элементы попарно, 
              если они имеют неправильный порядок, 
              то меняем местами*/
                if (arr[i].getPosD() < arr[j].getPosD()) {
                    tmp = arr[j];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    private static void rec_map(PointPosMap[][] arr, int ar_up, int ar_num, int ar_top) {
        int i_up = ((ar_up + 1) >= arr.length) ? 0 : (ar_up + 1);
        if (arr[i_up][ar_top - 1] == null) {
            for (int i = 0; i < ar_top; i++) {
                if (arr[i_up][i] == null) {
                    arr[i_up][i] = arr[ar_num][ar_top];
                    arr[ar_num][ar_top] = null;
                    if (i > 0) {
                        bubbleSort(arr[i_up]);
                    }
                    break;
                }
            }
        } else {
            PointPosMap tmp = new PointPosMap();
            tmp = arr[i_up][0];
            arr[i_up][0] = arr[ar_num][ar_top];
            arr[ar_num][ar_top] = tmp;
            rec_map(arr, i_up, ar_num, ar_top);
        }

    }

    private static void collision_pl(PointPosMap[][] ar) {

        int a_max = 0;
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[0].length; j++) {
                if (ar[i][j] != null) {
                    a_max = (a_max < j) ? j : a_max;
                } else {
                    continue;
                }
            }
        }
        if (a_max < 1) {
            return;
        }
        int a_top = a_max;
        do {
            for (int i = 0; i < ar.length; i++) {
                if (ar[i][a_top] != null) {
                    rec_map(ar, i, i, a_top);
                }
            }
            a_top = a_top - 1;
        } while (a_top > 0);
    }

    public static PointPosMap[] sortD_pl(Point[] ps, int a) {
        int top = 0;
        PointPosMap[] aPPM = sortRD_pl(ps, a);
        for (PointPosMap p : aPPM) {
            if (p.getPosR() > top) {
                top = p.getPosR();
            }
        }
        if (top < 1) {
            return aPPM;
        }

        PointPosMap[][] arr = new PointPosMap[a][top + 1];
        for (int i = 0; i < aPPM.length; i++) {
            arr[aPPM[i].getPosD()][aPPM[i].getPosR()] = aPPM[i];
        }
        collision_pl(arr);
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j][0] == null) {
                continue;
            }
            aPPM[i] = arr[j][0];
            aPPM[i].setPos(j, 0);
            i = i + 1;
        }

        return aPPM;
    }

}
