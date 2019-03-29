/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import java.util.Locale;
import java.time.LocalDateTime;

/**
 *
 * @author Admin
 */
public class Setting {

    //number planet in SweConst
    public static int[] pl_conf = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 57};
    public static Locale locale_ru = new Locale("ru");
    public static String[] zodName = {"Ari", "Tau", "Gem", "Can", "Leo", "Vir", "Lib", "Sco", "Sag", "Cap", "Aqu", "Pis"};
    public static int[] zodGradus = {0, 30, 60, 90, 120, 150, 180, 210, 240, 270, 300, 330};
    public static String[] elementName = {"Fire", "Earth", "Wind", "Water"};
    public static String[] signZod = {"Cardinal", "Fixed", "Mutable"};
    public static String[] quadrantZod = {"I", "II", "III", "IV"};
    public static String[] hemisphere = {"North", "South", "East", "West"};
    public static int[] aspMajor = {0, 60, 90, 120, 180};
    public static int[] aspMinor = {24, 30, 36, 40, 45, 72, 80, 108, 135, 144, 150, 160};
    public static int[] aspGarmonic = {0, 60, 120, 24, 30, 36, 72, 108, 144, 150};
    public static int[] aspStress = {90, 180, 40, 45, 80, 135, 160};
    public static int orbNat = 5;
    public static int orbTransit = 1;

    public static char houseSystem = 'P';
    public static String zodiac = "tropical";

//    public static        
    public static LocalDateTime ldt = LocalDateTime.of(1971, 11, 20, 0, 43, 0, 0);
    public static double gmt = 5;
    public static String stat = "Бузулук";
    public static double lon = 52.2635000;//долгота
    public static double lat = 52.7807000;//широта
    
//GUIPanel
    public static String filePoint="res/point.png";
    public static String fileZodiac="res/zodiac.gif";
    public static String filePlanet="res/sun_hiro_black.png";
    public static String fileEarth="res/earth.png";
//размещение планет на карте:
//0 - неотсортировано; 1 - циркулярно;2 - радиус-вектор;    
    public static int allocation2D = 1;
    static int[][] color_zod( ) {
        int[][] clr_z = new int[12][3];
        clr_z[0][0] = 255;
        clr_z[0][1] = 175;
        clr_z[0][2] = 175;

        clr_z[1][0] = 255;
        clr_z[1][1] = 255;
        clr_z[1][2] = 200;

        clr_z[2][0] = 225;
        clr_z[2][1] = 255;
        clr_z[2][2] = 255;

        clr_z[3][0] = 175;
        clr_z[3][1] = 255;
        clr_z[3][2] = 175;

        clr_z[4][0] = 255;
        clr_z[4][1] = 200;
        clr_z[4][2] = 200;

        clr_z[5][0] = 255;
        clr_z[5][1] = 255;
        clr_z[5][2] = 225;

        clr_z[6][0] = 175;
        clr_z[6][1] = 255;
        clr_z[6][2] = 255;

        clr_z[7][0] = 200;
        clr_z[7][1] = 255;
        clr_z[7][2] = 200;

        clr_z[8][0] = 255;
        clr_z[8][1] = 225;
        clr_z[8][2] = 225;

        clr_z[9][0] = 255;
        clr_z[9][1] = 255;
        clr_z[9][2] = 175;

        clr_z[10][0] = 200;
        clr_z[10][1] = 255;
        clr_z[10][2] = 255;

        clr_z[11][0] = 225;
        clr_z[11][1] = 255;
        clr_z[11][2] = 225;
        return clr_z;
    }

//            SUN = 0;
//            MOON = 1;
//            MERCURY = 2;
//            VENUS = 3;
//            MARS = 4;
//            JUPITER = 5;
//            SATURN = 6;
//            URANUS = 7;
//            NEPTUNE = 8;
//            PLUTO = 9;
//            MEAN_NODE = 10;
//            TRUE_NODE = 11;
//            MEAN_APOG = 12;
//            OSCU_APOG = 13;
//            EARTH = 14;
//            CHIRON = 15;
//            PHOLUS = 16;
//            CERES = 17;
//            PALLAS = 18;
//            JUNO = 19;
//            VESTA = 20;
//            INTP_APOG = 21;
//            INTP_PERG = 22;
//            AST_OFFSET = 10000;
//            VARUNA = 30000;
//            CUPIDO = 40;
//            HADES = 41;
//            ZEUS = 42;
//            KRONOS = 43;
//            APOLLON = 44;
//            ADMETOS = 45;
//            VULKANUS = 46;
//            POSEIDON = 47;
//            ISIS = 48;
//            NIBIRU = 49;
//            HARRINGTON = 50;
//            NEPTUNE_LEVERRIER = 51;
//            NEPTUNE_ADAMS = 52;
//            PLUTO_LOWELL = 53;
//            PLUTO_PICKERING = 54;
//            VULCAN = 55;
//            WHITE_MOON = 56;
//            PROSERPINA = 57;
    //cards
    public static enum TypeCards {
    NATAL,
    TRANSIT,
    SYNASTRY
  }
    public static TypeCards typecards=TypeCards.NATAL;

    public static enum TypeAsp {
    GARMONIC,
    STRESS,
    MATCH
  }
}