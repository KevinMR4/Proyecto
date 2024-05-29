package interfaz;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import javax.swing.JPanel;

/**
 *
 * @author alfredo
 */
class ImagenPanel extends JPanel
{
    final private Color colorInicial;
    final private Color colorFinal;

    public ImagenPanel(Color colorInicial)
    {
        this.colorInicial = colorInicial;
        this.colorFinal = new Color(255, 255, 255);
    }

    public ImagenPanel(Color colorInicial, Color colorFinal)
    {
        this.colorInicial = colorInicial;
        this.colorFinal = colorFinal;
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Crear un efecto de degradado
        Point2D start = new Point2D.Float(0, getHeight()); // Punto inicial del degradado (abajo)
        Point2D end = new Point2D.Float(0, 0); // Punto final del degradado (arriba)
        
        // Crear el objeto GradientPaint
        GradientPaint gradient = new GradientPaint(start, colorInicial, end, colorFinal);

        // Establecer el color del degradado en el contexto gráfico
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gradient);

        // Rellenar el panel con el degradado
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
