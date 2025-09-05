package com.kitfox.svg.app.beans;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGUniverse;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * Una versión simplificada de SVGCanvas basada en Swing.
 */
public class SVGCanvas extends JPanel {
    private SVGUniverse universe;
    private SVGDiagram diagram;

    public SVGCanvas() {
        universe = new SVGUniverse();
    }

    /**
     * Carga un archivo SVG desde la ruta especificada.
     *
     * @param svgFile el archivo SVG que se desea cargar.
     */
    public void setSVGFile(File svgFile) {
        try {
            URI uri = svgFile.toURI();
            diagram = universe.getDiagram(uri);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga un archivo SVG desde un URI.
     *
     * @param uri URI del archivo SVG.
     */
    public void setSVGURI(URI uri) {
        try {
            diagram = universe.getDiagram(uri);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (diagram != null) {
            Graphics2D g2d = (Graphics2D) g;
            try {
                diagram.render(g2d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
