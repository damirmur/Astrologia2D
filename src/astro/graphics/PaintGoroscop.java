/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro.graphics;

import astro.GUIPanel;
import astro.Setting;
import astro.graphics.GoroscopImageSetting;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import util.AstrologyFonts;
import static util.Utilites.iconRezive;

/**
 *
 * @author Admin
 */
public class PaintGoroscop {

    private BufferedImage zod_img;
    private GoroscopImageSetting gs;
    private Graphics2D graphics2d;
    private static Font a2font = null;
    private static BufferedImage earth_img;

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
                graphics2d.drawImage(zod_img, 0, 0, null);
            }
        }
        JPanel panel = new MyPanel();
        panel.setPreferredSize(new Dimension(gs.getZod_img_size(), gs.getZod_img_size()));
        return panel;
    }

    public PaintGoroscop() {
        this.gs = Setting.goroscop_setting;
        init();
    }

    public PaintGoroscop(int size) {
        this.gs = new GoroscopImageSetting(size);
        init();
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
        int startGoroskop = 90;
        paintZodiac((Graphics2D) this.zod_img.getGraphics(), startGoroskop);
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
            graphics2d.drawString(astro.Setting.zod_a2font.get(i), xz-5, yz + 5);
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

}
