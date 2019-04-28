/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Dimension;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *WebBrouser
 * @author Admin
 */
public class WebBrouser implements Runnable {
    private WebEngine webEngine;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new WebBrouser());
    }

    public void loadURL(final String url) {
        Platform.runLater(() -> {
            webEngine.load(url);
        });
    }

    @Override
    public void run() {
        // setup UI
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(1024, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFXPanel jfxPanel = new JFXPanel();
        frame.getContentPane().add(jfxPanel);
        frame.pack();

        Platform.runLater(() -> {
            WebView view = new WebView();
            webEngine = view.getEngine();

            jfxPanel.setScene(new Scene(view));
        });

        loadURL("http://www.google.com");
    }
}