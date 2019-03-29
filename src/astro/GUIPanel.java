/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import astro.Calc.CalcAspect;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Cursor;
;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import astro.Setting;
import astro.Setting.TypeAsp;
import astro.Setting.TypeCards;
//import com.sun.glass.ui.Cursor;
import java.awt.BasicStroke;
import java.awt.RenderingHints;

import java.util.stream.IntStream;

/**
 *
 * @author Admin
 */


public class GUIPanel extends javax.swing.JPanel {

    private class arr_p_pl {

        int x1, y1, x2, y2;

        public arr_p_pl(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    private class arr_s_asp {

        int x1, y1, x2, y2;
        Setting.TypeAsp typeasp;

        public arr_s_asp(int x1, int y1, int x2, int y2, Setting.TypeAsp typeasp) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.typeasp = typeasp;
        }
    }

    private static List<arr_s_asp> line_s_asp = new ArrayList<arr_s_asp>();
    private static List<arr_p_pl> line_p_pl = new ArrayList<arr_p_pl>();
    private static List<JLabel> labels_p = new ArrayList<JLabel>();
    private static List<JLabel> labels_pl = new ArrayList<JLabel>();
    private static List<JLabel> labels_sasp = new ArrayList<JLabel>();
    private static List<JLabel> labels_dasp = new ArrayList<JLabel>();
    int[][] color_z;
    Graphics2D graphics2d;
    int[] c_blank = {300, 250};
    int r_zod_sym = 200;
    int r_out_z = r_zod_sym + 20;
    int r_in_z = r_zod_sym - 20;
    int r_in_p = r_in_z - 100;
    int r_in_p2 = r_in_z - 50;
    Color c_background = Color.WHITE;
    Color c_foreground = this.getBackground();
    Color c_info = new Color(0, 110, 255);

    Image zod_img;
    BufferedImage pl_img;
    BufferedImage p_img;
    BufferedImage earth_img;
    int size_pict = 20;
    int c_size_pict = (int) Math.round(Math.sqrt(2 * size_pict * size_pict) / 2);
    Font astr_f = this.getFont();
//  Хотелось бы выводить астрологическим шрифтом
//        Font astr_f=new Font("WinstarTT",0,12);
//        Font astr_f=new Font("ASTRO-Z",0,12);

    public GUIPanel() {
        this.color_z = Setting.color_zod();
        this.setPreferredSize(new Dimension(600, 600));
        try {
            this.zod_img = ImageIO.read(getClass().getClassLoader().getResource(Setting.fileZodiac));
        } catch (IOException ex) {
            Logger.getLogger(GUIPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            this.earth_img = (ImageIO.read(getClass().getClassLoader().getResource(Setting.fileEarth)));
        } catch (IOException ex) {
            Logger.getLogger(GUIPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.p_img = new BufferedImage(40, 40, BufferedImage.TYPE_INT_RGB);
        try {
            this.p_img = (ImageIO.read(getClass().getClassLoader().getResource(Setting.filePoint)));
        } catch (IOException ex) {
            Logger.getLogger(GUIPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.pl_img = new BufferedImage(40, 40, BufferedImage.TYPE_INT_RGB);
        try {
            this.pl_img = (ImageIO.read(getClass().getClassLoader().getResource(Setting.filePlanet)));
        } catch (IOException ex) {
            Logger.getLogger(GUIPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private ImageIcon iconRezive(BufferedImage src, int size) {
        ImageIcon imageIcon = new ImageIcon(src.getScaledInstance(size, size, Image.SCALE_DEFAULT));
        return imageIcon;
    }

    private JLabel CreateLPointP(Point p, Color c, int x, int y) {
        int a = 4;
        ImageIcon p_icon = iconRezive(this.p_img.getSubimage(0, (int) (Math.random() * 4) * 8, 8, 8), a);
        JLabel lbl = new JLabel("");
        lbl.setForeground(c);
        lbl.setLocation(x, y);
        lbl.setSize(a, a);
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl.setIcon(p_icon);
        lbl.setToolTipText(p.getSwe_name() + " " + p.getNote());
        lbl.setVisible(true);
        labels_p.add(lbl);
        return lbl;
    }

    private JLabel CreateLPointPl(Point p, Color c, int x, int y) {
        JLabel lbl = new JLabel(p.getSwe_name());
        lbl.setForeground(c);
        lbl.setLocation(x, y);
        lbl.setSize(size_pict, size_pict);
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl.setIcon(imgPlanet(p.getSwe_n()));
        lbl.setText(p.getSwe_name());
        lbl.setToolTipText(p.getSwe_name() + " " + p.getNote());
        lbl.setVisible(true);
        labels_pl.add(lbl);
        return lbl;
    }

    private void CreateAspLabel(Graphics g) {
        JLabel lbl = new JLabel("");
        lbl.setForeground(Color.red);
        lbl.setOpaque(true);
        lbl.setBackground(Color.BLACK);
        lbl.setLocation(15, 15);
        lbl.setSize(20, 2);
        lbl.setText("");
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl.setToolTipText("Interactive Aspect");
        lbl.setVisible(true);
        labels_sasp.add(lbl);
        this.add(lbl);
    }

    private ImageIcon imgPlanet(int num) {
        BufferedImage src;
        int img_n = -1000;
        int file_n = -1000;
        if ((num >= 0) & (num < 10)) {
            file_n = 0;
            img_n = num;
        }
        if (num == 11) {
            file_n = 0;
            img_n = 10;
        }
        if (num == 15) {
            file_n = 0;
            img_n = 12;
        }
        if (img_n == -1000) {
            return new ImageIcon("");
        }
        src = this.pl_img.getSubimage(img_n * 40, 0, 40, 40);
        return iconRezive(src, size_pict);
    }

    private void del_img() {
        while (labels_sasp.size() > 0) {
            int index = labels_sasp.size() - 1;
            JLabel label = labels_sasp.remove(index);
            this.remove(label);
        }
        //labels_pl
        while (labels_pl.size() > 0) {
            int index = labels_pl.size() - 1;
            JLabel label = labels_pl.remove(index);
            this.remove(label);
        }
        while (labels_p.size() > 0) {
            int index = labels_p.size() - 1;
            JLabel label = labels_p.remove(index);
            this.remove(label);
        }
        while (line_p_pl.size() > 0) {
            int index = line_p_pl.size() - 1;
            arr_p_pl arr = line_p_pl.remove(index);
            arr = null;
        }
        while (line_s_asp.size() > 0) {
            int index = line_s_asp.size() - 1;
            arr_s_asp arr = line_s_asp.remove(index);
            arr = null;
        }//line_s_asp
    }

    private void default_pl(Point[] ps) {
        double ad;
        int xp, yp;
        for (Point p : ps) {
            ad = Math.toRadians(p.getPos());
            xp = c_blank[0] + (int) Math.round(-Math.cos(ad) * (r_in_p + size_pict) - c_size_pict);
            yp = c_blank[1] + (int) Math.round(Math.sin(ad) * (r_in_p + size_pict) - c_size_pict);
            this.add(CreateLPointPl(p, c_info, xp, yp));
        }
    }

    private void position_pl(PointPosMap[] aPPM) {
        double ad;
        int xp1, yp1;
        for (PointPosMap p : aPPM) {
            ad = Math.toRadians(p.getPosD() * 360 / region_pl());
            xp1 = c_blank[0] + (int) Math.round(-Math.cos(ad) * (r_in_p + size_pict / 2 * ((p.getPosR() + 1) * 2))) - c_size_pict;
            yp1 = c_blank[1] + (int) Math.round(Math.sin(ad) * (r_in_p + size_pict / 2 * ((p.getPosR() + 1) * 2))) - c_size_pict;
            this.add(CreateLPointPl(p.getPl(), c_info, xp1, yp1));
        }
    }

    private void createS_ASP(PointPosMap[] aPPM) {
        if (aPPM == null) {
            return;
        }
        Point[] ps = new Point[aPPM.length];
        for (int i = 0; i < aPPM.length; i++) {
            ps[i] = aPPM[i].getPl();
        }
        Aspect[] SingleCardAspect = CalcAspect.SingleCardAspect(ps, Setting.aspMajor, Setting.orbNat);
        double ad1, ad2;
        int xp1, yp1;
        int xp2, yp2;
        for (Aspect as : SingleCardAspect) {
            ad1 = Math.toRadians(as.getP()[0].getPos());
            ad2 = Math.toRadians(as.getP()[1].getPos());
            xp1 = c_blank[0] + (int) Math.round(-Math.cos(ad1) * r_in_p) - 2;
            yp1 = c_blank[1] + (int) Math.round(Math.sin(ad1) * r_in_p) - 2;
            xp2 = c_blank[0] + (int) Math.round(-Math.cos(ad2) * r_in_p) - 2;
            yp2 = c_blank[1] + (int) Math.round(Math.sin(ad2) * r_in_p) - 2;
            if (0 == as.getAsp()) {
                line_s_asp.add(new arr_s_asp(xp1, yp1, xp2, yp2, Setting.TypeAsp.MATCH));
            } else if (IntStream.of(Setting.aspGarmonic).anyMatch(x -> x == as.getAsp())) {
                line_s_asp.add(new arr_s_asp(xp1, yp1, xp2, yp2, Setting.TypeAsp.GARMONIC));
            } else if (IntStream.of(Setting.aspStress).anyMatch(x -> x == as.getAsp())) {
                line_s_asp.add(new arr_s_asp(xp1, yp1, xp2, yp2, Setting.TypeAsp.STRESS));
            }
        }
    }

    private void createP_Pl(PointPosMap[] aPPM) {
        if (aPPM == null) {
            return;
        }
        double ad, ad_p;
        int xp1, yp1;
        int xp2, yp2;
        for (PointPosMap p : aPPM) {
            ad_p = Math.toRadians(p.getPosGr());
            ad = Math.toRadians(p.getPosD() * 360 / region_pl());
            xp1 = c_blank[0] + (int) Math.round(-Math.cos(ad) * (r_in_p + size_pict / 2 * ((p.getPosR() + 1) * 2)));
            yp1 = c_blank[1] + (int) Math.round(Math.sin(ad) * (r_in_p + size_pict / 2 * ((p.getPosR() + 1) * 2)));
            xp2 = c_blank[0] + (int) Math.round(-Math.cos(ad_p) * r_in_p) - 2;
            yp2 = c_blank[1] + (int) Math.round(Math.sin(ad_p) * r_in_p) - 2;
            line_p_pl.add(new arr_p_pl(xp1, yp1, xp2, yp2));
        }
    }

    private void imgPoints(Point[] ps) {
        double ad;
        int xp, yp;
        for (Point p : ps) {
            ad = Math.toRadians(p.getPos());
            xp = c_blank[0] + (int) Math.round(-Math.cos(ad) * r_in_p) - 2;
            yp = c_blank[1] + (int) Math.round(Math.sin(ad) * r_in_p) - 2;
            this.add(CreateLPointP(p, c_info, xp, yp));
        }
    }

    private void imgSAspects(Graphics2D graphics2d) {
        if (line_s_asp.size() > 0) {
            line_s_asp.forEach((line)
                    -> {
                TypeAsp tasp = line.typeasp;
                if (null != tasp) {
                    switch (tasp) {
                        case MATCH: {
                            Color clr = Color.BLUE;
                            graphics2d.setColor(clr);
                            break;
                        }
                        case GARMONIC: {
                            Color clr = Color.GREEN;
                            graphics2d.setColor(clr);
                            break;
                        }
                        case STRESS: {
                            Color clr = Color.RED;
                            graphics2d.setColor(clr);
                            break;
                        }
                        default:
                            graphics2d.setColor(c_info);
                            break;
                    }
                }
                graphics2d.setStroke(new BasicStroke(2.0f));
                graphics2d.drawLine(line.x1, line.y1, line.x2, line.y2);
            }
            );
        }
    }

    public int region_pl() {
        int a = (int) Math.round((r_in_p + size_pict) * 2 * Math.PI / (size_pict));
        return a;
    }

    private void circle_pl(Point[] ps) {
        PointPosMap[] aPPM = PointPosMap.sortD_pl(ps, region_pl());
        position_pl(aPPM);
        createP_Pl(aPPM);
        createS_ASP(aPPM);
        //            CreateAsp();

    }

    private void rvector_pl(Point[] ps) {
        PointPosMap[] aPPM = PointPosMap.sortRD_pl(ps, region_pl());
        double chord = 360 + aPPM[0].getPosGr() - aPPM[aPPM.length - 1].getPosGr();
        if ((chord) < 360 / region_pl()) {
            aPPM[0].setPosR(aPPM[aPPM.length - 1].getPosR() + 1);
        }
        position_pl(aPPM);
    }

    public void imgObjGoroskop(Point[] ps) {
        del_img();
        CreateAspLabel(this.graphics2d);
        switch (Setting.allocation2D) {
            case (1): {
                circle_pl(ps);
                break;
            }
            case (2): {
                rvector_pl(ps);
                break;
            }
            case (0): {
                default_pl(ps);
                break;
            }
        }
        imgPoints(ps);

        this.revalidate();
        this.repaint();

    }

    void paintZodiac(Graphics2D graphics2d) {

//        System.out.println(astr_f.getStyle());
//  Хотелось бы выводить астрологическим шрифтом
//        char[] sym={'1','2','3','4','5','6','7','8','9','a','b','c'};
//        char[] sym_num=new char[1];
        graphics2d.setColor(c_background);
        //blank
        graphics2d.fillRect(0, 0, 2 * c_blank[0], 2 * c_blank[1]);

        int a = 180;
        double ad;
        int xz, yz;
        for (int i = 0; i < 12; i++) {
            graphics2d.setColor(new Color(color_z[i][0], color_z[i][1], color_z[i][2]));
            graphics2d.fillArc(c_blank[0] - r_out_z, c_blank[1] - r_out_z, 2 * r_out_z, 2 * r_out_z, a + i * 30, 30);
            graphics2d.setColor(c_info);
            graphics2d.setFont(astr_f);
            ad = Math.toRadians(i * 30 + 15);
            xz = c_blank[0] + (int) Math.round(-Math.cos(ad) * r_zod_sym);
            yz = c_blank[1] + (int) Math.round(Math.sin(ad) * r_zod_sym);
//  Хотелось бы выводить астрологическим шрифтом
//                sym_num[0]=sym[i];
//                graphics2d.drawChars(sym_num, 0, sym_num.length, xz, yz);
//                graphics2d.drawString(astro.Setting.zodName[i], xz, yz);
            graphics2d.drawImage(zod_img, xz - 10, yz - 10, xz + 10, yz + 10, i * 40, 0, i * 40 + 40, 40, null);
            graphics2d.setColor(c_info);
            ad = Math.toRadians(i * 30);
            xz = c_blank[0] + (int) Math.round(-Math.cos(ad) * r_out_z);
            yz = c_blank[1] + (int) Math.round(Math.sin(ad) * r_out_z);
            graphics2d.drawLine(xz, yz, c_blank[0], c_blank[1]);
        }
        graphics2d.setColor(c_background);
        graphics2d.fillOval(c_blank[0] - r_in_z, c_blank[1] - r_in_z, 2 * r_in_z, 2 * r_in_z);
        graphics2d.drawImage(iconRezive(earth_img, 30).getImage(), c_blank[0] - 15, c_blank[1] - 15, null);
        graphics2d.setColor(c_info);

        graphics2d.drawOval(c_blank[0] - r_in_z, c_blank[1] - r_in_z, 2 * r_in_z, 2 * r_in_z);
        graphics2d.drawOval(c_blank[0] - r_out_z, c_blank[1] - r_out_z, 2 * r_out_z, 2 * r_out_z);
        TypeCards tC = Setting.typecards;
        switch (tC) {
            case NATAL:
                graphics2d.drawOval(c_blank[0] - r_in_p, c_blank[1] - r_in_p, 2 * r_in_p, 2 * r_in_p);
                break;
            case TRANSIT:
                graphics2d.drawOval(c_blank[0] - r_in_p, c_blank[1] - r_in_p, 2 * r_in_p, 2 * r_in_p);
                graphics2d.drawOval(c_blank[0] - r_in_p2, c_blank[1] - r_in_p2, 2 * r_in_p2, 2 * r_in_p2);
                break;
            case SYNASTRY:
                graphics2d.drawOval(c_blank[0] - r_in_p, c_blank[1] - r_in_p, 2 * r_in_p, 2 * r_in_p);
                graphics2d.drawOval(c_blank[0] - r_in_p2, c_blank[1] - r_in_p2, 2 * r_in_p2, 2 * r_in_p2);
                break;
        };

//        System.out.println(this.jLabel1.getLayout());
    }

    @Override
    public void paintComponent(Graphics g
    ) {
        super.paintComponent(g);
        this.graphics2d = (Graphics2D) g;
        this.graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.graphics2d.setStroke(new BasicStroke(2.0f));
        paintZodiac(this.graphics2d);
        if (line_p_pl.size() > 0) {
            this.graphics2d.setStroke(new BasicStroke(1.0f));
            this.graphics2d.setColor(c_info);
            line_p_pl.forEach((line) -> {
                this.graphics2d.drawLine(line.x1, line.y1, line.x2, line.y2);
            });
            imgSAspects(this.graphics2d);

        }
//System.out.println("repaint");
    }

}