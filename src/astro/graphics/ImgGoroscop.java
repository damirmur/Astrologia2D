/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro.graphics;

import astro.Aspect;
import astro.Calc.CalcAspect;
import astro.GUI.pc.GUIPanel;
import astro.Goroscop;
import astro.Houses;
import astro.Point;
import astro.Calc.PointPosMap;
import astro.Setting;
import static astro.Setting.c_green;
import astro.graphics.GoroscopImageSetting;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.AstrologyFonts;
import static util.Utilites.iconRezive;

/**
 *
 * @author Admin
 */
public class ImgGoroscop {

    private BufferedImage zod_img;
    private GoroscopImageSetting gs;
    private Graphics2D graphics2d;
    private static Font a2font = null;
    private static BufferedImage earth_img;
    BufferedImage p_img;

    private class ObjX1_Y2 {

        int x1, y1, x2, y2;

        public ObjX1_Y2(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    private class ObjSAsp {

        int x1, y1, x2, y2;
        Setting.TypeAsp typeasp;
        Setting.PrecisionAsp precisionasp;
        Setting.DirectionAsp directionasp;
        
        public ObjSAsp(int x1, int y1, int x2, int y2, Setting.TypeAsp typeasp) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.typeasp = typeasp;
            this.precisionasp = Setting.PrecisionAsp.PRECISION;
            this.directionasp = Setting.DirectionAsp.CONVERGENT;
        }

        public ObjSAsp(int x1, int y1, int x2, int y2, Setting.TypeAsp typeasp,Setting.PrecisionAsp precisionasp,Setting.DirectionAsp directionasp) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.typeasp = typeasp;
            this.precisionasp = precisionasp;
            this.directionasp = directionasp;
        }
    }

    private List<ObjX1_Y2> line_h = new ArrayList<ObjX1_Y2>();
    private List<ObjSAsp> line_sasp = new ArrayList<ObjSAsp>();
    private List<JLabel> labels_plDeg = new ArrayList<JLabel>();
    private List<JLabel> labels_p = new ArrayList<JLabel>();
    private List<JLabel> labels_pl = new ArrayList<JLabel>();
    private List<JLabel> labels_sasp = new ArrayList<JLabel>();

    public BufferedImage getZod_img() {
        return zod_img;
    }

    public Graphics2D getGraphics2d() {
        return graphics2d;
    }

    public JPanel getJpanel() {
        class MyPanel extends JPanel {

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics2d = (Graphics2D) g;
                graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics2d.drawImage(zod_img, 0, 0, null);
            }
        }
        JPanel panel = new MyPanel();
        panel.setPreferredSize(new Dimension(gs.getZod_img_size(), gs.getZod_img_size()));
        return panel;
    }

    void paint(Goroscop gor, int size) {
        this.gs = new GoroscopImageSetting(size);
        init();
        int startGoroskop = (int) gor.getHouse().getAsc();
        paintZodiac(this.graphics2d, startGoroskop);
        create_House(gor.getHouse());
        paintHouse(this.graphics2d);
        createS_ASP(gor.getPoints(), startGoroskop);
        paintSAspects(this.getGraphics2d());
        switch (Setting.goroscop_setting.getAllocation2D()) {
            case (1): {
                circle_pl(gor.getPoints(), startGoroskop);
                break;
            }
            case (2): {
                rvector_pl(gor.getPoints(), startGoroskop);
                break;
            }
            case (0): {
                paint_default_img(gor.getPoints(), startGoroskop);
                break;
            }
        }
        createPointPl(gor.getPoints(), startGoroskop);
    }

    public ImgGoroscop(Goroscop gor) {
        paint(gor, Setting.goroscop_setting.getZod_img_size());
    }

    public ImgGoroscop(Goroscop gor, int size) {
        paint(gor, size);
    }

    private void init() {

        this.zod_img = new BufferedImage(gs.getZod_img_size(), gs.getZod_img_size(), BufferedImage.TYPE_INT_RGB);
        this.graphics2d = (Graphics2D) this.zod_img.createGraphics();
        this.graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        new AstrologyFonts();
        a2font = AstrologyFonts.getFont("Astrologia2D.ttf");
        try {
            earth_img = (ImageIO.read(getClass().getClassLoader().getResource(Setting.fileEarth)));
        } catch (IOException ex) {
            Logger.getLogger(GUIPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.p_img = (ImageIO.read(getClass().getClassLoader().getResource(Setting.filePoint)));
        } catch (IOException ex) {
            Logger.getLogger(GUIPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void paintZodiac(Graphics2D graphics2d, int startGoroskop) {
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setStroke(new BasicStroke(2.0f));
        int[][] color_z = Setting.color_zod();
        graphics2d.setColor(gs.getC_background());
        //blank
        graphics2d.fillRect(0, 0, gs.getC_blank()[0] * 2, gs.getC_blank()[1] * 2);
        int a = 180;
        double ad;
        int xz, yz;
        for (int i = 0; i < 12; i++) {
            graphics2d.setColor(new Color(color_z[i][0], color_z[i][1], color_z[i][2]));
            graphics2d.fillArc(gs.getC_blank()[0] - gs.getR_out_z(), gs.getC_blank()[1] - gs.getR_out_z(), 2 * gs.getR_out_z(), 2 * gs.getR_out_z(), (int) (a + i * 30 - startGoroskop), 30);
            graphics2d.setColor(gs.getC_info());
            ad = Math.toRadians(i * 30 + 15 - startGoroskop);
            xz = gs.getC_blank()[0] + (int) Math.round(-Math.cos(ad) * gs.getR_zod_sym());
            yz = gs.getC_blank()[1] + (int) Math.round(Math.sin(ad) * gs.getR_zod_sym());
            graphics2d.setFont(a2font.deriveFont(Font.BOLD, 20.0F));
            graphics2d.drawString(astro.Setting.zod_a2font.get(i), xz - 5, yz + 5);
            graphics2d.setColor(gs.getC_info());
            ad = Math.toRadians(i * 30 - startGoroskop);
            xz = gs.getC_blank()[0] + (int) Math.round(-Math.cos(ad) * gs.getR_out_z());
            yz = gs.getC_blank()[1] + (int) Math.round(Math.sin(ad) * gs.getR_out_z());
            graphics2d.drawLine(xz, yz, gs.getC_blank()[0], gs.getC_blank()[1]);
        }
        graphics2d.setColor(gs.getC_background());
        graphics2d.fillOval(gs.getC_blank()[0] - gs.getR_in_p(), gs.getC_blank()[1] - gs.getR_in_p(), 2 * gs.getR_in_p(), 2 * gs.getR_in_p());
//        graphics2d.fillOval(c_blank[0] - r_in_z, c_blank[1] - r_in_z, 2 * r_in_z, 2 * r_in_z);
        graphics2d.drawImage(iconRezive(earth_img, 30).getImage(), gs.getC_blank()[0] - 15, gs.getC_blank()[1] - 15, null);
        graphics2d.setColor(gs.getC_info());

//        graphics2d.drawOval(gs.getC_blank()[0] - gs.getR_in_z(), gs.getC_blank()[1] - gs.getR_in_z(), 2 * gs.getR_in_z(), 2 * gs.getR_in_z());
        graphics2d.drawOval(gs.getC_blank()[0] - gs.getR_out_z(), gs.getC_blank()[1] - gs.getR_out_z(), 2 * gs.getR_out_z(), 2 * gs.getR_out_z());
        Setting.TypeCards tC = Setting.typecards;
        switch (tC) {
            case NATAL:
                graphics2d.drawOval(gs.getC_blank()[0] - gs.getR_in_p(), gs.getC_blank()[1] - gs.getR_in_p(), 2 * gs.getR_in_p(), 2 * gs.getR_in_p());
                break;
            case TRANSIT:
                graphics2d.drawOval(gs.getC_blank()[0] - gs.getR_in_p(), gs.getC_blank()[1] - gs.getR_in_p(), 2 * gs.getR_in_p(), 2 * gs.getR_in_p());
                graphics2d.drawOval(gs.getC_blank()[0] - gs.getR_in_p2(), gs.getC_blank()[1] - gs.getR_in_p2(), 2 * gs.getR_in_p2(), 2 * gs.getR_in_p2());
                break;
            case SYNASTRY:
                graphics2d.drawOval(gs.getC_blank()[0] - gs.getR_in_p(), gs.getC_blank()[1] - gs.getR_in_p(), 2 * gs.getR_in_p(), 2 * gs.getR_in_p());
                graphics2d.drawOval(gs.getC_blank()[0] - gs.getR_in_p2(), gs.getC_blank()[1] - gs.getR_in_p2(), 2 * gs.getR_in_p2(), 2 * gs.getR_in_p2());
                break;
        }
    }

    private void create_House(Houses hs) {
        if (hs == null) {
            return;
        }
        if (hs.getH() == null) {
            return;
        }
        double ad;
        int xp1, yp1;
        int xp2, yp2;
        for (int i = 0; i < hs.getH().length; i++) {
            ad = Math.toRadians(hs.getH()[i] - hs.getAsc());
            xp1 = gs.getC_blank()[0] + (int) Math.round(-Math.cos(ad) * gs.getR_in_p());
            yp1 = gs.getC_blank()[1] + (int) Math.round(Math.sin(ad) * gs.getR_in_p());
            xp2 = gs.getC_blank()[0] + (int) Math.round(-Math.cos(ad) * gs.getR_h());
            yp2 = gs.getC_blank()[1] + (int) Math.round(Math.sin(ad) * gs.getR_h());
            line_h.add(new ObjX1_Y2(xp1, yp1, xp2, yp2));
        }
    }

    private void paintHouse(Graphics2D graphics2d) {
        if (line_h.size() > 0) {
            graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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

    private JLabel createLabelPointPl(Point p, Color c, int x, int y) {
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

    private void paintPointPl(Graphics2D graphics2d, Point p, Color c, int x, int y) {
        int a = 6;
        ImageIcon p_icon = iconRezive(this.p_img.getSubimage(0, (int) (Math.random() * 4) * 8, 8, 8), a);
        graphics2d.setColor(c);
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setStroke(new BasicStroke(2.0f));
        graphics2d.drawImage(p_icon.getImage(), x, y, null);
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
        lbl.setSize(gs.getSize_pict() + 2, gs.getSize_pict());
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (afontPl(p.getSwe_n()) != "") {
            lbl.setForeground(Color.BLACK);
            lbl.setFont(a2font.deriveFont(Font.BOLD, 20));
            lbl.setText(afontPl(p.getSwe_n()));
            if (p.getRetro()) {
                lbl.setSize(gs.getSize_pict() + 5, gs.getSize_pict());
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

    private void paintSignPl(Graphics2D graphics2d, Point p, Color c, int x, int y) {
        graphics2d.setColor(c);
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setStroke(new BasicStroke(2.0f));
        graphics2d.setColor(c);
        graphics2d.setFont(a2font.deriveFont(Font.BOLD, 20));
        if (afontPl(p.getSwe_n()) != "") {
            if (p.getRetro()) {
                graphics2d.drawString(afontPl(p.getSwe_n()) + Setting.sym_a2font.get("r"), x, y);
            } else {
                graphics2d.drawString(afontPl(p.getSwe_n()), x, y);
            }
        } else {
            if (p.getRetro()) {
                graphics2d.drawString(p.getSwe_n() + Setting.sym_a2font.get("r"), x, y);
            } else {
                graphics2d.drawString(p.getSwe_name(), x, y);
            }
        }
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

    private void createPointPl(Point[] ps, int startGoroskop) {
        double ad;
        int xp, yp;
        for (Point p : ps) {
            ad = Math.toRadians(p.getPos() - startGoroskop);
            xp = gs.getC_blank()[0] + (int) Math.round(-Math.cos(ad) * gs.getR_in_p()) - 2;
            yp = gs.getC_blank()[1] + (int) Math.round(Math.sin(ad) * gs.getR_in_p()) - 2;
            paintPointPl(this.getGraphics2d(), p, gs.getC_info(), xp, yp);
//            this.add(createLPointP(p, gs.getC_info(), xp, yp));

        }
    }

    private void paint_default_img(Point[] ps, int startGoroskop) {
        double ad;
        int xp, yp;
        for (Point p : ps) {
            ad = Math.toRadians(p.getPos() - startGoroskop);
            xp = gs.getC_blank()[0] + (int) Math.round(-Math.cos(ad) * (gs.getR_in_p2()));
            yp = gs.getC_blank()[1] + (int) Math.round(Math.sin(ad) * (gs.getR_in_p2()));
            paintSignPl(this.getGraphics2d(), p, Color.BLACK, xp, yp + 10);//paintSignPl
//            this.add(createLPointPl(p, gs.getC_info(), xp, yp));
            if (Setting.viewDeg) {
//                this.add(createLPointPlDeg(p, gs.getC_info(), xp + gs.getSize_pict(), yp));
            };
        }
    }

    public int region_pl() {
        int a = (int) Math.round((2 * Math.PI * gs.getR_in_p() + gs.getC_size_pict()) / (gs.getC_size_pict()));
        return a;
    }

    private void circle_pl(Point[] ps, int startGoroskop) {
        PointPosMap[] aPPM = PointPosMap.sortD_pl(ps, region_pl());
        position_pl(aPPM, startGoroskop);
        //            CreateAsp();

    }

    private void rvector_pl(Point[] ps, int startGoroskop) {
        PointPosMap[] aPPM = PointPosMap.sortRD_pl(ps, region_pl());
        double chord = 360 + aPPM[0].getPosGr() - aPPM[aPPM.length - 1].getPosGr();
        if ((chord) < 360 / region_pl()) {
            aPPM[0].setPosR(aPPM[aPPM.length - 1].getPosR() + 1);
        }
        position_pl(aPPM, startGoroskop);
    }

    private void position_pl(PointPosMap[] aPPM, int startGoroskop) {
        double ad;
        int xp1, yp1;
        for (PointPosMap p : aPPM) {
            ad = Math.toRadians(p.getPosD() * 360 / region_pl() - startGoroskop);
            xp1 = gs.getC_blank()[0] + (int) Math.round(-Math.cos(ad) * (gs.getR_in_p() + gs.getC_size_pict() * ((p.getPosR() + 1) * 2)));
            yp1 = gs.getC_blank()[1] + (int) Math.round(Math.sin(ad) * (gs.getR_in_p() + gs.getC_size_pict() * ((p.getPosR() + 1) * 2)));
            paintSignPl(this.getGraphics2d(), p.getPl(), Color.BLACK, xp1 - gs.getC_size_pict(), yp1 + gs.getC_size_pict());
//            this.add(createLPointPl(p.getPl(), gs.getC_info(), xp1, yp1));
            if (Setting.viewDeg) {
//                this.add(createLPointPlDeg(p.getPl(), gs.getC_info(), xp1 + gs.getSize_pict() / 2, yp1 - gs.getSize_pict() / 2));
            };
        }
//        createP_Pl(aPPM);

    }
        private void createS_ASP(Point[] ps, int startGoroskop) {
        Aspect[] SingleCardAspect = CalcAspect.SingleCardAspect(ps, Setting.aspMajor, Setting.orbNat);
        double ad1, ad2;
        int xp1, yp1;
        int xp2, yp2;
        for (Aspect as : SingleCardAspect) {
            ad1 = Math.toRadians(as.getP()[0].getPos() - startGoroskop);
            ad2 = Math.toRadians(as.getP()[1].getPos() - startGoroskop);
            xp1 = gs.getC_blank()[0] + (int) Math.round(-Math.cos(ad1) * gs.getR_in_p()) - 2;
            yp1 = gs.getC_blank()[1] + (int) Math.round(Math.sin(ad1) * gs.getR_in_p()) - 2;
            xp2 = gs.getC_blank()[0] + (int) Math.round(-Math.cos(ad2) * gs.getR_in_p()) - 2;
            yp2 = gs.getC_blank()[1] + (int) Math.round(Math.sin(ad2) * gs.getR_in_p()) - 2;
            if (0 == as.getAsp()) {
                line_sasp.add(new ObjSAsp(xp1, yp1, xp2, yp2, Setting.TypeAsp.MATCH));
            } else if (IntStream.of(Setting.aspGarmonic).anyMatch(x -> x == as.getAsp())) {
                line_sasp.add(new ObjSAsp(xp1, yp1, xp2, yp2, Setting.TypeAsp.GARMONIC));
            } else if (IntStream.of(Setting.aspStress).anyMatch(x -> x == as.getAsp())) {
                line_sasp.add(new ObjSAsp(xp1, yp1, xp2, yp2, Setting.TypeAsp.STRESS));
            }
        }
    }
    private void paintSAspects(Graphics2D graphics2d) {
        if (line_sasp.size() > 0) {
            line_sasp.forEach((line)
                    -> {
                Setting.TypeAsp tasp = line.typeasp;
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
                            graphics2d.setColor(gs.getC_info());
                            break;
                    }
                }
                graphics2d.setStroke(new BasicStroke(2.0f));
                graphics2d.drawLine(line.x1, line.y1, line.x2, line.y2);
            }
            );
        }
    }


}
