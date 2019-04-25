/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import astro.Calc.CalcAspect;
import astro.Setting.TopCards;
import astro.Setting.TypeAsp;
import astro.Setting.TypeCards;
import static astro.Setting.c_green;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Cursor;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.util.stream.IntStream;
import javax.swing.JLabel;
import util.AstrologyFonts;
import static util.Utilites.iconRezive;



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

    private class arr_h {

        int x1, y1, x2, y2;

        public arr_h(int x1, int y1, int x2, int y2) {
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

    private  List<arr_s_asp> line_s_asp = new ArrayList<arr_s_asp>();
    private  List<arr_h> line_h = new ArrayList<arr_h>();
    private  List<arr_p_pl> line_p_pl = new ArrayList<arr_p_pl>();
    private  List<JLabel> labels_plDeg = new ArrayList<JLabel>();
    private  List<JLabel> labels_p = new ArrayList<JLabel>();
    private  List<JLabel> labels_pl = new ArrayList<JLabel>();
    private  List<JLabel> labels_sasp = new ArrayList<JLabel>();
    private  List<JLabel> labels_dasp = new ArrayList<JLabel>();
    double startGoroskop = 0;
    int[][] color_z;
    Graphics2D graphics2d;
    int[] c_blank = {250, 250};
    int r_zod_sym = 200;
    int r_out_z = r_zod_sym + 20;
    int r_h = r_out_z + 20;
    int r_in_z = r_zod_sym - 20;
    int r_in_p = r_in_z - 30;
    int r_in_p2 = r_in_p + 10;
    Color c_background = Color.WHITE;
    Color c_foreground = this.getBackground();
    Color c_info = new Color(0, 110, 255);

    Font a2font = null;
    BufferedImage p_img;
    BufferedImage earth_img;
    int size_pict = 20;
    int c_size_pict = (int) Math.round(Math.sqrt(2 * size_pict * size_pict) / 2);

    public GUIPanel() {
        a2font=AstrologyFonts.getFont("Astrologia2D.ttf");
        this.color_z = Setting.color_zod();
        this.setPreferredSize(new Dimension(500, 500));
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

    }


    private JLabel createLPointP(Point p, Color c, int x, int y) {
        int a = 4;
        ImageIcon p_icon = iconRezive(this.p_img.getSubimage(0, (int) (Math.random() * 4) * 8, 8, 8), a);
        JLabel lbl = new JLabel("");
        lbl.setForeground(c);
        lbl.setLocation(x, y);
        lbl.setSize(a, a);
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl.setIcon(p_icon);
        lbl.setFont(a2font.deriveFont(23F));
        lbl.setToolTipText(p.getSwe_name() + " " + p.getNote());
        lbl.setVisible(true);
        labels_p.add(lbl);
        return lbl;
    }

    private String afontPl(int numPl) {
        String a = "";
        if (Setting.pl_a2font.containsKey(numPl)) {
            a = Setting.pl_a2font.get(numPl);
        }
        return a;
    }

    private JLabel createLPointPl(Point p, Color c, int x, int y) {
        JLabel lbl = new JLabel(p.getSwe_name());
        lbl.setForeground(c);
        lbl.setLocation(x, y);
        lbl.setSize(size_pict + 2, size_pict);
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (afontPl(p.getSwe_n()) != "") {
            lbl.setForeground(Color.BLACK);
            lbl.setFont(a2font.deriveFont(Font.BOLD, 20));
            lbl.setText(afontPl(p.getSwe_n()));
            if (p.getRetro()) {
                lbl.setSize(size_pict + 5, size_pict);
                lbl.setText(afontPl(p.getSwe_n()));
                lbl.setText(afontPl(p.getSwe_n()) + Setting.sym_a2font.get("r"));
            }
        } else {
            lbl.setText(p.getSwe_name());
        }
        lbl.setToolTipText(p.getSwe_name() + " " + p.getNote());
        lbl.setVisible(true);
        labels_pl.add(lbl);
        return lbl;
    }

    private JLabel createLPointPlDeg(Point p, Color c, int x, int y) {
        int size = 12;
        JLabel lbl = new JLabel(p.getAfontNote());
        lbl.setForeground(Color.BLACK);
        lbl.setLocation(x, y);
        lbl.setFont(a2font.deriveFont(Font.BOLD, (size + 0.0F)));
        lbl.setSize(4 * (size), size + 2);
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl.setToolTipText(p.getSwe_name() + " " + p.getNote());
        lbl.setVisible(true);
        labels_plDeg.add(lbl);
        return lbl;
    }

   private void del_img() {
        while (line_h.size() > 0) {
            int index = line_h.size() - 1;
            arr_h arr = line_h.remove(index);
            arr = null;
        }
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
        while (labels_plDeg.size() > 0) {
            int index = labels_plDeg.size() - 1;
            JLabel label = labels_plDeg.remove(index);
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
        }
    }

    private void default_pl(Point[] ps) {
        double ad;
        int xp, yp;
        for (Point p : ps) {
            ad = Math.toRadians(p.getPos() - startGoroskop);
            xp = c_blank[0] + (int) Math.round(-Math.cos(ad) * (r_in_p + size_pict) - c_size_pict);
            yp = c_blank[1] + (int) Math.round(Math.sin(ad) * (r_in_p + size_pict) - c_size_pict);
            this.add(createLPointPl(p, c_info, xp, yp));
            if (Setting.viewDeg) {
                this.add(createLPointPlDeg(p, c_info, xp + size_pict, yp));
            };
        }
    }

    private void position_pl(PointPosMap[] aPPM) {
        double ad;
        int xp1, yp1;
        for (PointPosMap p : aPPM) {
            ad = Math.toRadians(p.getPosD() * 360 / region_pl() - startGoroskop);
            xp1 = c_blank[0] + (int) Math.round(-Math.cos(ad) * (r_in_p + size_pict / 2 * ((p.getPosR() + 1) * 2))) - c_size_pict;
            yp1 = c_blank[1] + (int) Math.round(Math.sin(ad) * (r_in_p + size_pict / 2 * ((p.getPosR() + 1) * 2))) - c_size_pict;
            this.add(createLPointPl(p.getPl(), c_info, xp1, yp1));
            if (Setting.viewDeg) {
                this.add(createLPointPlDeg(p.getPl(), c_info, xp1 + size_pict / 2, yp1 - size_pict / 2));
            };
        }
        createP_Pl(aPPM);
        createS_ASP(aPPM);

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
            ad1 = Math.toRadians(as.getP()[0].getPos() - startGoroskop);
            ad2 = Math.toRadians(as.getP()[1].getPos() - startGoroskop);
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
            ad_p = Math.toRadians(p.getPosGr() - startGoroskop);
            ad = Math.toRadians(p.getPosD() * 360 / region_pl() - startGoroskop);
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
            ad = Math.toRadians(p.getPos() - startGoroskop);
            xp = c_blank[0] + (int) Math.round(-Math.cos(ad) * r_in_p) - 2;
            yp = c_blank[1] + (int) Math.round(Math.sin(ad) * r_in_p) - 2;
            this.add(createLPointP(p, c_info, xp, yp));
        }
    }

    private void create_House(Houses hs) {
        if (hs == null) {
            return;
        }
        if (hs.h == null) {
            return;
        }
        double ad;
        int xp1, yp1;
        int xp2, yp2;
        for (int i = 0; i < hs.h.length; i++) {
            ad = Math.toRadians(hs.h[i] - startGoroskop);
            xp1 = c_blank[0] + (int) Math.round(-Math.cos(ad) * r_in_p);
            yp1 = c_blank[1] + (int) Math.round(Math.sin(ad) * r_in_p);
            xp2 = c_blank[0] + (int) Math.round(-Math.cos(ad) * r_h);
            yp2 = c_blank[1] + (int) Math.round(Math.sin(ad) * r_h);
            line_h.add(new arr_h(xp1, yp1, xp2, yp2));
        }
    }

    private void imgHouse(Graphics2D graphics2d) {
        if (line_h.size() > 0) {

            for (int i = 0; i < line_h.size(); i++) {
                switch (i) {
                    case 0:
                        graphics2d.setColor(Color.RED);
                        graphics2d.setStroke(new BasicStroke(2.0f));
                        break;
                    case 3:
                        graphics2d.setColor(Color.BLACK);
                        graphics2d.setStroke(new BasicStroke(2.0f));
                        break;
                    case 6:
                        graphics2d.setColor(Color.BLUE);
                        graphics2d.setStroke(new BasicStroke(2.0f));
                        break;
                    case 9:
                        graphics2d.setColor(c_green);
                        graphics2d.setStroke(new BasicStroke(2.0f));
                        break;
                    default:
                        graphics2d.setColor(Color.BLACK);
                        graphics2d.setStroke(new BasicStroke(1.0f));
                        break;
                }
                graphics2d.setFont(a2font.deriveFont(Font.BOLD, 22.0F));
                graphics2d.drawLine(line_h.get(i).x1, line_h.get(i).y1, line_h.get(i).x2, line_h.get(i).y2);
                graphics2d.drawString(Setting.house_a2font.get(i), line_h.get(i).x2, line_h.get(i).y2);

            }
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
                            Color clr = c_green;
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
        int a = (int) Math.round((r_in_p + size_pict) * 2 * Math.PI / (2*size_pict));
        return a;
    }

    private void circle_pl(Point[] ps) {
        PointPosMap[] aPPM = PointPosMap.sortD_pl(ps, region_pl());
        position_pl(aPPM);
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

    public void paintCosmogram(Point[] ps) {
        del_img();
        switch (Setting.goroscop_setting.getAllocation2D()) {
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

    public void paintGoroscop(Goroscop gs) {
        paintGorosсop(gs.getPoints(), gs.getHouse());
    }

    public void paintGorosсop(Point[] ps, Houses hs) {
        del_img();
        if (hs != null) {
            if (hs.h != null) {
                if (Setting.topcards == TopCards.ASC) {
                    startGoroskop = hs.getAsc();
                }
                create_House(hs);
            }
        }
        switch (Setting.goroscop_setting.getAllocation2D()) {
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

        graphics2d.setColor(c_background);
        //blank
        graphics2d.fillRect(0, 0, 2 * c_blank[0], 2 * c_blank[1]);

        int a = 180;
        double ad;
        int xz, yz;
        for (int i = 0; i < 12; i++) {
            graphics2d.setColor(new Color(color_z[i][0], color_z[i][1], color_z[i][2]));
            graphics2d.fillArc(c_blank[0] - r_out_z, c_blank[1] - r_out_z, 2 * r_out_z, 2 * r_out_z, (int) (a + i * 30 - startGoroskop), 30);
            graphics2d.setColor(c_info);
            ad = Math.toRadians(i * 30 + 15 - startGoroskop);
            xz = c_blank[0] + (int) Math.round(-Math.cos(ad) * r_zod_sym);
            yz = c_blank[1] + (int) Math.round(Math.sin(ad) * r_zod_sym);
            graphics2d.setFont(a2font.deriveFont(Font.BOLD, 20.0F));
            graphics2d.drawString(astro.Setting.zod_a2font.get(i), xz, yz + 10);
            graphics2d.setColor(c_info);
            ad = Math.toRadians(i * 30 - startGoroskop);
            xz = c_blank[0] + (int) Math.round(-Math.cos(ad) * r_out_z);
            yz = c_blank[1] + (int) Math.round(Math.sin(ad) * r_out_z);
            graphics2d.drawLine(xz, yz, c_blank[0], c_blank[1]);
        }
        graphics2d.setColor(c_background);
        graphics2d.fillOval(c_blank[0] - r_in_p, c_blank[1] - r_in_p, 2 * r_in_p, 2 * r_in_p);
        graphics2d.drawImage(iconRezive(earth_img, 30).getImage(), c_blank[0] - 15, c_blank[1] - 15, null);
        graphics2d.setColor(c_info);

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
            imgHouse(this.graphics2d);

        }
//System.out.println("repaint");
    }

}
