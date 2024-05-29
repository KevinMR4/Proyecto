package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author alfredo
 */
public class MenuInicio extends JPanel
{

    JPanel panelA;
    JPanel panelB;
    JPanel panelC;
    JPanel panelD;
    private final Color[] relleno =
        {
            new Color(4431943), new Color(16757504), new Color(48340), new Color(14794471), new Color(49061), new Color(16733986)
        };
    public MenuInicio()
    {
        this.setLayout(new GridLayout(2, 2));
        this.setBackground(Color.WHITE);
        initComponents();
    }

    private void initComponents()
    {
        panelA = new JPanel(new BorderLayout());
        panelB = new JPanel(new BorderLayout());
        panelC = new JPanel(new BorderLayout());
        panelD = new JPanel(new BorderLayout());

        initPanelA();
        initPanelB();
        initPanelC();
        initPanelD();
        
        this.add(panelA);
        this.add(panelB);
        this.add(panelC);
        this.add(panelD);
    }

    private void initPanelA()
    {
        int[] datosX =
        {
            26, 10, 20, 30, 40, 50
        };
        int[] datosY =
        {
            20, 45, 30, 70, 90, 75
        };
        GraficoBarras graficoBarras = new GraficoBarras("Padecimientos Alumnos", datosX, datosY);
        panelA.add(graficoBarras);
    }

    private void initPanelB()
    {
        int[] datosX =
        {
            0, 10, 20, 30, 40, 50
        };
        int[] datosY =
        {
            20, 45, 30, 62, 23, 57
        };
        GraficoBarras graficoBarras = new GraficoBarras("Padecimientos Personal", datosX, datosY);
        add(graficoBarras);
        panelB.add(graficoBarras);
    }

    private void initPanelC()
    {
        int[][] datos =
        {
            {
                14, 80, 20, 30, 40, 60
            },
            {
                0, 23, 12, 4, 1, 4
            },
            {
                0, 5, 23, 4, 1, 5
            },
            {
                0, 52, 3, 4, 1, 4
            },
            {
                0, 14, 20, 20, 30, 6
            },
            {
                1, 20, 0, 10, 4, 10
            }
        };
        GraficoDeLineas graficoBarras = new GraficoDeLineas("Comparacion de consultas semanales", datos, relleno);
        panelC.add(graficoBarras);
    }

    private void initPanelD()
    {
        String[] encabezados =
        {
            "Ing. en Software", "Ing. en P.I.", "Ing. en Plasticos", "Ing. Mecanica", "Lic. en S. Ciudadana", "Personal"
        };        
        int[] datos =
        {
            14, 80, 20, 30, 40, 60
        };
        GraficoPastel graficoBarras = new GraficoPastel("Cantidad de registros", encabezados, datos, relleno);
        panelD.add(graficoBarras);
    }

}

class GraficoBarras extends JPanel
{

    final private int[] mujeres;
    final private int[] hombres;
    private int datoMayor;

    public GraficoBarras(String titulo, int[] hombres, int[] mujeres)
    {
        this.hombres = hombres;
        this.mujeres = mujeres;
        this.setBackground(Color.WHITE);
        this.add(new JLabel(titulo));
        // this.setBorder(new LineBorder(Color.GRAY, 1));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        obtenerMayoDato();

        g.setColor(new Color(4367861));
        //g.setColor(new Color(6600182));
        g.fillRect(57, 5, 10, 10);
        g.drawString("Hombres", 70, 15);
        g.setColor(new Color(15753874));
        g.fillRect(130, 5, 10, 10);
        g.drawString("Mujeres", 143, 15);

        // Dibujar ejes X y Y
        g.setColor(new Color(51, 51, 51));
        g.drawLine(30, height - 30, width - 30, height - 30);
        g.drawLine(30, 30, 30, height - 30);

        // Dibujar graduaciones en el eje Y
        int incrementY = (height - 60) / 10;
        int tamanoParte = (int) datoMayor / 10;
        for (int i = 0; i <= 10; i++)
        {
            int numero = (int) Math.round(i * tamanoParte);
            int y = height - 30 - i * incrementY;
            g.drawLine(25, y, 35, y);
            //g.drawLine(30, y, width - 30, y);
            g.drawString(String.valueOf(numero), 5, y + 5);
        }

        // Dibujar las barras
        String[] tipo =
        {
            "Desnutrición", "Sobrepeso", "Alergias", "Obesidad", "Diabetes", "Otros"
        };
        int incrementX = (width - 60) / 18;
        int x = 30 + incrementX;
        for (int i = 0; i < 6; i++)
        {
            int heightHombres = (int) (hombres[i] * ((double) (height - 60) / datoMayor));
            int heightMujeres = (int) (mujeres[i] * ((double) (height - 60) / datoMayor));

            g.setColor(new Color(51, 51, 51));
            g.drawString(tipo[i], x, height - 15);

            g.drawString(String.valueOf(hombres[i]), x + 5, height - 30 - heightHombres);
            g.drawString(String.valueOf(mujeres[i]), x + 5 + incrementX, height - 30 - heightMujeres);

            //g.setColor(new Color(6600182));
            g.setColor(new Color(4367861));
            g.fillRect(x, height - 30 - heightHombres, incrementX, heightHombres);
            x += incrementX;

            g.setColor(new Color(15753874));
            //g.setColor(new Color(16027569));
            g.fillRect(x, height - 30 - heightMujeres, incrementX, heightMujeres);
            x += incrementX * 2;
        }
    }

    private void obtenerMayoDato()
    {
        datoMayor = 0;
        for (int i = 0; i < 6; i++)
        {
            if (datoMayor < hombres[i])
            {
                datoMayor = hombres[i];
            }
            if (datoMayor < mujeres[i])
            {
                datoMayor = mujeres[i];
            }
        }
    }

}

class GraficoDeLineas extends JPanel
{

    final private int[][] datos;
    final private Color[] color;
    private int datoMayor;

    public GraficoDeLineas(String titulo, int[][] datos, Color[] color)
    {
        this.datos = datos;
        this.color = color;
        this.setBackground(Color.WHITE);
        this.add(new JLabel(titulo));
        //this.setBorder(new LineBorder(Color.GRAY, 1));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        obtenerMayoDato();

        // Dibujar ejes X y Y
        g.setColor(new Color(51, 51, 51));
        g.drawLine(30, height - 30, width - 30, height - 30);
        g.drawLine(30, 30, 30, height - 30);

        // Dibujar graduaciones en el eje Y
        int incrementY = (height - 60) / 10;
        int tamanoParte = (int) datoMayor / 10;
        for (int i = 0; i <= 10; i++)
        {
            int numero = (int) Math.round(i * tamanoParte);
            int y = height - 30 - i * incrementY;
            //g.drawLine(25, y, 35, y);
            g.drawLine(30, y, width - 30, y);
            g.drawString(String.valueOf(numero), 5, y + 5);
        }
        
        String[] tipo =
        {
            "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"

        };
        int incrementX = (width - 60) / 6;
        int x;

        for (int i = 0; i < datos.length; i++)
        {
            x = 30;
            for (int j = 0; j < datos[i].length; j++)
            {
                int heightMarca = (int) (datos[i][j] * ((double) (height - 60) / datoMayor));
                g.setColor(color[i]);
                g.fillRect(x - 2, height - 30 - heightMarca - 2, 5, 5);
                g.setColor(new Color(51, 51, 51));
                //g.drawString(String.valueOf(datos[i][j]), x + 1, height - 30 - heightMarca);
                if (j > 0)
                {
                    // Conectar líneas con el punto anterior
                    g.setColor(color[i]);
                    g.drawLine(x - incrementX, height - 30 - (int) (datos[i][j - 1] * ((double) (height - 60) / datoMayor)),
                    x, height - 30 - heightMarca);
                }
                x += incrementX;
            }
        }

        x = 30;
        for (int i = 0; i < 7; i++)
        {
            g.setColor(new Color(51, 51, 51));
            g.drawString(tipo[i], x - 10, height - 15);
            x += incrementX;
        }
    }

    private void obtenerMayoDato()
    {
        datoMayor = 0;
        for (int[] dato : datos)
        {
            for (int j = 0; j < dato.length; j++)
            {
                if (datoMayor < dato[j])
                {
                    datoMayor = dato[j];
                }
            }
        }
    }
}

class GraficoPastel extends JPanel
{
    private final int[] datos;
    String[] encabezados;
    Color[] relleno;

    public GraficoPastel(String titulo, String[] encabezados, int[] datos, Color[] relleno)
    {
        this.datos = datos;
        this.encabezados = encabezados;
        this.relleno = relleno;
        this.setBackground(Color.WHITE);
        this.add(new JLabel(titulo));
        //this.setBorder(new LineBorder(Color.GRAY, 1));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int total = sumaTotal();
        int startAngle = 0;

        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height) - 40;
        //System.out.println("D : " + diameter + " H : " + getHeight() + " W : " + getWidth());
        int x = (width - diameter) / 2;
        int y = (height - diameter) / 2 + 10;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < datos.length; i++)
        {
            int arcAngle = (int) Math.round(360.0 * datos[i] / total);
            drawPieSlice(g2d, x, y, diameter, startAngle, arcAngle, encabezados[i], datos[i], relleno[i], 10 * i);
            startAngle += arcAngle;
        }
    }

    private int sumaTotal()
    {
        int sumaT = 0;
        for (int dato : datos)
        {
            sumaT += dato;
        }
        return sumaT;
    }

    private void drawPieSlice(Graphics2D g, int x, int y, int diameter, int startAngle, int arcAngle, String label, int dato, Color color, int pY)
    {
        g.setColor(color);
        g.fillArc(x, y, diameter, diameter, startAngle, arcAngle);
        g.fillRect(0, pY * 3 + 30, 10, 10);
        g.fillRect(getWidth() - 25, pY * 3 + 30, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString(dato + " = " + formatPercentage(arcAngle), getWidth() - 110, pY * 3 + 40);
        g.drawString(label, 20, pY * 3 + 40);
    }

    private String formatPercentage(int value)
    {
        DecimalFormat df = new DecimalFormat("0.##");
        return df.format((double) value / 360 * 100) + "%";
    }
}
