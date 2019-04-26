/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro.graphics;

import java.awt.Color;

/**
 *
 * @author Admin
 */
public class GoroscopImageSetting {
//размещение планет на карте:
//0 - неотсортировано; 1 - циркулярно;2 - радиус-вектор;    

    private int allocation2D = 2;
    private int zod_img_size = 600;
    private int[] c_blank = {(int) zod_img_size / 2, (int) zod_img_size / 2};

    private int r_zod_sym = (int) (zod_img_size / 2) - 50;
    private int r_out_z = r_zod_sym + 20;
    private int r_h = r_out_z + 20;
    private int r_in_z = r_zod_sym - 20;
    private int r_in_p = r_in_z - 40;
    private int r_in_p2 = r_in_p + 30;
    private Color c_background = Color.WHITE;
    private Color c_info = new Color(0, 110, 255);
    private int size_pict = 20;
    private int c_size_pict = (int) Math.round(Math.sqrt(2 * size_pict * size_pict) / 2);

    //    Color c_foreground = this.getBackground();
    public GoroscopImageSetting() {
    }

    public GoroscopImageSetting(int size) {
        setZod_img_size(size);
        setSize_pict(20);
    }

    public int getAllocation2D() {
        return allocation2D;
    }

    public void setAllocation2D(int allocation2D) {
        this.allocation2D = allocation2D;
    }

    public int getZod_img_size() {
        return zod_img_size;
    }

    public void setSize_pict(int size) {
        this.c_size_pict = size;
        this.c_size_pict = (int) Math.round(Math.sqrt(2 * size_pict * size_pict) / 2);
    }

    public void setZod_img_size(int zod_img_size) {
        this.zod_img_size = zod_img_size;
        this.c_blank[0] = (int) zod_img_size / 2;
        this.c_blank[1] = (int) zod_img_size / 2;

        this.r_zod_sym = (int) (zod_img_size / 2) - 50;
        this.r_out_z = r_zod_sym + 20;
        this.r_h = r_out_z + 20;
        this.r_in_z = r_zod_sym - 20;
        this.r_in_p = r_in_z - 40;
        this.r_in_p2 = r_in_p + 20;
    }

    public int[] getC_blank() {
        return c_blank;
    }

    public void setC_blank(int[] c_blank) {
        this.c_blank = c_blank;
    }

    public int getR_zod_sym() {
        return r_zod_sym;
    }

    public int getR_out_z() {
        return r_out_z;
    }

    public int getR_h() {
        return r_h;
    }

    public int getR_in_z() {
        return r_in_z;
    }

    public int getR_in_p() {
        return r_in_p;
    }

    public int getR_in_p2() {
        return r_in_p2;
    }

    public Color getC_background() {
        return c_background;
    }

    public void setC_background(Color c_background) {
        this.c_background = c_background;
    }

    public Color getC_info() {
        return c_info;
    }

    public void setC_info(Color c_info) {
        this.c_info = c_info;
    }

    public int getSize_pict() {
        return size_pict;
    }

    public int getC_size_pict() {
        return c_size_pict;
    }

}
