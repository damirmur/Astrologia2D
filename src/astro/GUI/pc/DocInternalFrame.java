/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro.GUI.pc;

import astro.FirstMain;
import astro.Goroscop;
import astro.MoonData;
import astro.Setting;
import astro.graphics.ImgGoroscop;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.reflect.Array.set;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.text.BadLocationException;
import javax.swing.SwingUtilities;
import util.AstrologyFonts;
import util.Utilites;

/**
 *
 * @author Admin
 */
public class DocInternalFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form DocInternalFrame
     */
    public DocInternalFrame() {
        initComponents();
    }

    public void moonMonthInfo(LocalDateTime dt) {
//    LocalDateTime dt=LocalDateTime.now();
        BufferedImage moonBlack_img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        try {
            moonBlack_img = (ImageIO.read(getClass().getClassLoader().getResource(Setting.fileMoonBl)));
        } catch (IOException ex) {
            Logger.getLogger(GUIPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        MoonData md = new MoonData(null, dt);
        ImageIcon ii = new ImageIcon(Utilites.iconSprite(moonBlack_img, (int) md.getMonday()));
        ii = Utilites.iconRezive(ii, 100);
        jTextPane1.insertIcon(ii);
        String cr = "\r\n";
        try {
            jTextPane1.getDocument().insertString(jTextPane1.getDocument().getLength(), cr, null);
        } catch (BadLocationException ex) {
            Logger.getLogger(DocInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            jTextPane1.getDocument().insertString(jTextPane1.getDocument().getLength(), md.getAllMonth(), null);
            this.title = jTextPane1.getDocument().getText(2, 35);
        } catch (BadLocationException ex) {
            Logger.getLogger(DocInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextPane1.setCaretPosition(0);
    }

    public void goroscopInfo(Goroscop gs) {
        jTextPane1.insertComponent(new ImgGoroscop(gs, 500).getJpanel());
        jTextPane1.setCaretPosition(0);
    }

    public void newURL() {
//        jTextPane1.insertComponent(new ImgGoroscop(gs,500).getJpanel());
//        jTextPane1.setCaretPosition(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuNewTab = new javax.swing.JMenuItem();
        jMenuCancel = new javax.swing.JMenuItem();
        jMenuExit = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuNewURL = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        jPopupMenu1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                jPopupMenu1PopupMenuCanceled(evt);
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("jMenuItem2");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        jMenuItem3.setText("jMenuItem3");
        jPopupMenu1.add(jMenuItem3);

        jMenuItem4.setText("jMenuItem4");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem4);
        jPopupMenu1.add(jSeparator1);

        jMenuNewTab.setText("New Blank");
        jMenuNewTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNewTabActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuNewTab);

        jMenuCancel.setText("Cancel");
        jMenuCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCancelActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuCancel);

        jMenuExit.setText("Exit");
        jMenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExitActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuExit);
        jPopupMenu1.add(jSeparator2);

        jMenuNewURL.setText("new URL");
        jMenuNewURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNewURLActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuNewURL);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(410, 460));

        jTextPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextPane1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTextPane1);
        jTextPane1.getAccessibleContext().setAccessibleName("TextPane");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextPane1MouseClicked
        jMenuItem1.setText("Goroskop Now");
        jMenuItem2.setText("Goroskop Setting");
        jMenuItem3.setText("Goroskop Date..");
        jMenuItem4.setText("Moon Calendar");
        // TODO add your handling code here:
        if (SwingUtilities.isRightMouseButton(evt)) {
            jPopupMenu1.setLocation(evt.getLocationOnScreen());
            jPopupMenu1.setVisible(true);
        }
    }//GEN-LAST:event_jTextPane1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        jTextPane1.setCaretPosition(jTextPane1.getDocument().getEndPosition().getOffset() - 1);
        Goroscop gs = new Goroscop(LocalDateTime.now().minusHours((long) Setting.tzOffset));
        jTextPane1.insertComponent(new ImgGoroscop(gs, 500).getJpanel());
        Font a2font = AstrologyFonts.getFont("Astrologia2D.ttf");
        jTextPane1.setFont(a2font.deriveFont(Font.BOLD, 13.0F));

        try {
            jTextPane1.getDocument().insertString(jTextPane1.getDocument().getLength(), gs.getText(), null);
        } catch (BadLocationException ex) {
            Logger.getLogger(DocInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        jTextPane1.setCaretPosition(0);

        jPopupMenu1.setVisible(false);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jPopupMenu1PopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jPopupMenu1PopupMenuCanceled
    }//GEN-LAST:event_jPopupMenu1PopupMenuCanceled

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        jTextPane1.setCaretPosition(jTextPane1.getDocument().getEndPosition().getOffset() - 1);
        Goroscop gs = new Goroscop(Setting.ldt.minusHours((long) Setting.tzOffset));
        jTextPane1.insertComponent(new ImgGoroscop(gs, 500).getJpanel());
        Font a2font = AstrologyFonts.getFont("Astrologia2D.ttf");
        jTextPane1.setFont(a2font.deriveFont(Font.BOLD, 13.0F));

        try {
            jTextPane1.getDocument().insertString(jTextPane1.getDocument().getLength(), gs.getText(), null);
        } catch (BadLocationException ex) {
            Logger.getLogger(DocInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextPane1.setCaretPosition(0);

        jPopupMenu1.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        moonMonthInfo(LocalDateTime.now().minusHours((long) Setting.tzOffset));
        jPopupMenu1.setVisible(false);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCancelActionPerformed
        jPopupMenu1.setVisible(false);
    }//GEN-LAST:event_jMenuCancelActionPerformed

    private void jMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExitActionPerformed
        FirstMain.Close();
    }//GEN-LAST:event_jMenuExitActionPerformed

    private void jMenuNewTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNewTabActionPerformed
        try {
            JInternalFrame newblank = super.getClass().newInstance();
            super.getParent().add(newblank);
            newblank.setVisible(true);
        } catch (InstantiationException ex) {
            Logger.getLogger(DocInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DocInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        jPopupMenu1.setVisible(false);

    }//GEN-LAST:event_jMenuNewTabActionPerformed

    private void jMenuNewURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNewURLActionPerformed
        jTextPane1.setContentType("text/html");
        jTextPane1.setEditable(false);

        try {
            jTextPane1.setPage("https://google.com");
//            jTextPane1.setPage("https://mail.ru");
        } catch (IOException ex) {
            Logger.getLogger(DocInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        jPopupMenu1.setVisible(false);
    }//GEN-LAST:event_jMenuNewURLActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuCancel;
    private javax.swing.JMenuItem jMenuExit;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuNewTab;
    private javax.swing.JMenuItem jMenuNewURL;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}