/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import astro.graphics.GoroscopImageSetting;
import java.awt.Color;
import java.util.Locale;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class Setting {

    public static String swepath = "./ephe";
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

//    public static        
    public static LocalDateTime ldt = LocalDateTime.of(1971, 11, 20, 0, 43, 0, 0);
//    public static double tzOffset = 5;
//    public static String stat = "Бузулук";
//    public static double lon = 52.2635000;//долгота
//    public static double lat = 52.7807000;//широта
    public static double tzOffset = 3;
    public static String stat = "Moscow";
    public static double lon = 37.5833;//долгота
    public static double lat = 55.45;//широта

//GUIPanel
    public static String filePoint = "res/point.png";
//    public static String fileZodiac="res/zodiac.gif";
//    public static String filePlanet="res/sun_hiro_black.png";
    public static String fileEarth = "res/earth.png";
    public static String fileMoonBl = "res/MoonBlack.png";
//    public static String fileMoonBl="res/MoonWhite.png";

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
    public static enum TypeMoonCal {
        Degree12,
        RISE,
    }
    public static TypeMoonCal typeMoonCal = TypeMoonCal.Degree12;

    //cards
    public static enum TypeCards {
        NATAL,
        TRANSIT,
        SYNASTRY
    }

    public static enum TypeGoroscop {
        COSMOGRAMMA,
        GOROSCOP
    }
    public static TypeCards typecards = TypeCards.NATAL;
    public static TypeGoroscop typegoroscop = TypeGoroscop.GOROSCOP;

    public static enum TypeAsp {
        GARMONIC,
        STRESS,
        MATCH
    }
    public static char houseSystem = 'P';
    public static String zodiac = "tropical";

    public static enum TopCards {
        ASC,
        OVEN
    }
    public static TopCards topcards = TopCards.ASC;
    //GUI
    public static String a2fname = "Astrologia2D.ttf";
    public static Map<String, String> sym_a2font = new HashMap<String, String>() {
        {
            put("r", new String(new int[]{917959}, 0, 1));
            put("d", new String(new int[]{917960}, 0, 1));
            put("s", new String(new int[]{917961}, 0, 1));
            put("t", new String(new int[]{917962}, 0, 1));
        }
    };

    public static Map<Integer, String> pl_a2font = new HashMap<Integer, String>() {
        {
            put(0, new String(new int[]{917943}, 0, 1));
            put(1, new String(new int[]{917944}, 0, 1));
            put(2, new String(new int[]{917945}, 0, 1));
            put(3, new String(new int[]{917946}, 0, 1));
            put(4, new String(new int[]{917947}, 0, 1));
            put(5, new String(new int[]{917948}, 0, 1));
            put(6, new String(new int[]{917949}, 0, 1));
            put(7, new String(new int[]{917950}, 0, 1));
            put(8, new String(new int[]{917951}, 0, 1));
            put(9, new String(new int[]{917952}, 0, 1));
            put(11, new String(new int[]{917953}, 0, 1));
            put(12, new String(new int[]{917955}, 0, 1));
            put(15, new String(new int[]{917957}, 0, 1));
            put(57, new String(new int[]{917958}, 0, 1));
        }
    };
    public static Map<Integer, String> zod_a2font = new HashMap<Integer, String>() {
        {
            put(0, new String(new int[]{917988}, 0, 1));
            put(1, new String(new int[]{917989}, 0, 1));
            put(2, new String(new int[]{917990}, 0, 1));
            put(3, new String(new int[]{917991}, 0, 1));
            put(4, new String(new int[]{917992}, 0, 1));
            put(5, new String(new int[]{917993}, 0, 1));
            put(6, new String(new int[]{917994}, 0, 1));
            put(7, new String(new int[]{917995}, 0, 1));
            put(8, new String(new int[]{917996}, 0, 1));
            put(9, new String(new int[]{917997}, 0, 1));
            put(10, new String(new int[]{917998}, 0, 1));
            put(11, new String(new int[]{917999}, 0, 1));
        }
    };
    public static Map<Integer, String> house_a2font = new HashMap<Integer, String>() {
        {
            put(0, new String(new int[]{917963}, 0, 1));
            put(1, new String(new int[]{917964}, 0, 1));
            put(2, new String(new int[]{917965}, 0, 1));
            put(3, new String(new int[]{917966}, 0, 1));
            put(4, new String(new int[]{917967}, 0, 1));
            put(5, new String(new int[]{917968}, 0, 1));
            put(6, new String(new int[]{917969}, 0, 1));
            put(7, new String(new int[]{917970}, 0, 1));
            put(8, new String(new int[]{917971}, 0, 1));
            put(9, new String(new int[]{917972}, 0, 1));
            put(10, new String(new int[]{917973}, 0, 1));
            put(11, new String(new int[]{917974}, 0, 1));
        }
    };

    public static Map<Integer, String> aspect_a2font = new HashMap<Integer, String>() {
        {
            put(0, new String(new int[]{917915}, 0, 1));
            //put(24,new String(new int[] {917920}, 0, 1));
            put(30, new String(new int[]{917921}, 0, 1));
            put(36, new String(new int[]{917922}, 0, 1));
            put(40, new String(new int[]{917923}, 0, 1));
            put(45, new String(new int[]{917924}, 0, 1));
            put(60, new String(new int[]{917916}, 0, 1));
            put(72, new String(new int[]{917925}, 0, 1));
            put(80, new String(new int[]{917926}, 0, 1));
            put(90, new String(new int[]{917917}, 0, 1));
            put(108, new String(new int[]{917927}, 0, 1));
            put(120, new String(new int[]{917918}, 0, 1));
            put(135, new String(new int[]{917928}, 0, 1));
            put(144, new String(new int[]{917929}, 0, 1));
            put(150, new String(new int[]{917930}, 0, 1));
            put(160, new String(new int[]{917931}, 0, 1));
            put(180, new String(new int[]{917919}, 0, 1));
        }
    };

    public static GoroscopImageSetting goroscop_setting=new GoroscopImageSetting();
        
        

    public static Color c_green = new Color(0, 127, 70);

    public static int[][] color_zod() {
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
    public static boolean viewDeg = true;

}
