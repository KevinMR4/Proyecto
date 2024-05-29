package interfaz;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author alfredo
 */
public class VentanaPrincipal extends JFrame
{

    private JPanel panelPrincipal;
    private JPanel panelNorth;
    private JPanel panelWest;
    private JPanel panelCenter;
    private CardLayout cardLayout;

    private MenuPersonal mnuPersonal = new MenuPersonal(true);
    private MenuPersonal mnuAlumnos = new MenuPersonal(false);
    private MenuBuscar mnuBuscar = new MenuBuscar();
    
    private int mouseX, mouseY;

    private enum Menu
    {
        INICIO,
        PERSONAL,
        ESTUDIANTES,
        BUSCAR,
        AYUDA
    }
    private Menu menuActivo;

    final private JLabel[] opcMenu = new JLabel[5];
    final String imgMenu[] =
    {
        "inicio.png", "personal.png", "estudiantes.png", "buscar.png", "ayuda.png"
    };
    final String imgMenuActivo[] =
    {
        "inicio_Activo.png", "personal_Activo.png", "estudiantes_Activo.png", "buscar_Activo.png"
    };

    final String pathImagenes = "src/interfaz/imagenes/";

    private boolean maximized = true;

    public VentanaPrincipal()
    {
        initComponents();

        menuActivo = Menu.INICIO;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);
        this.add(panelPrincipal);
        this.setSize(1200, 700);
    }

    /**
     * Configura el panelPrincipal Y se agregan el resto de los paneles
     */
    private void initComponents()
    {
        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(new LineBorder(Color.GRAY, 2));

        panelNorth = new ImagenPanel(new Color(0, 145, 234));
        //panelNorth = new ImagenPanel(new Color(133, 193, 233));
        panelNorth.setBorder(new LineBorder(Color.GRAY, 1));
        panelWest = new JPanel();
        panelCenter = new JPanel();

        cardLayout = new CardLayout();
        panelCenter.setLayout(cardLayout);

        initPanelNorth();
        initPanelWest();
        initPanelCenter();

        panelPrincipal.add(panelNorth, BorderLayout.NORTH);
        panelPrincipal.add(panelWest, BorderLayout.WEST);
        panelPrincipal.add(panelCenter, BorderLayout.CENTER);
    }

    /**
     * Configura el panelNorth Trabaja como la barra de titulo de un JFrame se
     * agregan iconos basicos para la manipulacion de la ventana se configuaran
     * los eventos minimizar, redimencionar, cerrar, mover ventana.
     */
    private void initPanelNorth()
    {

        //panelNorth.setBackground(new Color(174, 214, 241));
        JLabel icono = new JLabel(new ImageIcon(pathImagenes + "logo.png"));
        JLabel titulo = new JLabel("Departamento de Servicios de Salud");
        JLabel minimizar = new JLabel(new ImageIcon(pathImagenes + "minimizar.png"));
        JLabel redimencionar = new JLabel(new ImageIcon(pathImagenes + "redimencionar.png"));
        JLabel closed = new JLabel(new ImageIcon(pathImagenes + "cerrar.png"));

        panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.X_AXIS));

        minimizar.setToolTipText("Minimizar");
        closed.setToolTipText("Cerrar");

        panelNorth.add(icono);
        panelNorth.add(Box.createHorizontalGlue());
        panelNorth.add(titulo);
        panelNorth.add(Box.createHorizontalGlue());
        panelNorth.add(minimizar);
        panelNorth.add(redimencionar);
        panelNorth.add(closed);

        icono.setBorder(new EmptyBorder(0, 25, 0, 0));

        minimizar.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                minimizar.setIcon(new ImageIcon(pathImagenes + "minimizar_Hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent evt)
            {
                minimizar.setIcon(new ImageIcon(pathImagenes + "minimizar.png"));
            }

            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    setExtendedState(JFrame.ICONIFIED);
                }
            }
        });

        redimencionar.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                String i = (maximized) ? "redimencionar_Hover.png" : "redimencionar2_Hover.png";
                redimencionar.setIcon(new ImageIcon(pathImagenes + i));
            }

            @Override
            public void mouseExited(MouseEvent evt)
            {
                String i = (maximized) ? "redimencionar.png" : "redimencionar2.png";
                redimencionar.setIcon(new ImageIcon(pathImagenes + i));
            }

            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    if (maximized)
                    {
                        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana
                        redimencionar.setToolTipText("Restaurar");
                    } else
                    {
                        setExtendedState(JFrame.NORMAL); // Restaurar la ventana al tamaño original
                        redimencionar.setToolTipText("Maximizar");
                    }
                    mouseExited(e);
                    maximized = !maximized;
                }
            }
        });

        closed.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                closed.setIcon(new ImageIcon(pathImagenes + "cerrar_Hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent evt)
            {
                closed.setIcon(new ImageIcon(pathImagenes + "cerrar.png"));
            }

            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    dispose();
                    System.exit(0);
                }
            }
        });

        panelNorth.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                // Captura las coordenadas del mouse cuando se presiona
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        panelNorth.addMouseMotionListener(new MouseAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                // Calcula las nuevas coordenadas del mouse
                int x = e.getXOnScreen() - mouseX;
                int y = e.getYOnScreen() - mouseY;
                // Mueve la ventana a las nuevas coordenadas
                setLocation(x, y);
            }
        });
    }

    /**
     * Configura el panelWest Trabaja como la barra de menu opciones: INICIO,
     * PERSONAL, ALUMNOS, BUSCAR, AYUDA
     */
    private void initPanelWest()
    {
        panelWest.setBackground(new Color(236, 236, 236));
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));

        final String[] toolTipText =
        {
            "Inicio (Ctrl + I)", "Personal (Ctrl + P)", "Estudiantes (Ctrl + E)", "Buscar (Ctrl + B)", "Ayuda (F1)"
        };
        for (int i = 0; i < imgMenu.length; i++)
        {
            opcMenu[i] = new JLabel(new ImageIcon(pathImagenes + imgMenu[i]));
            opcMenu[i].setToolTipText(toolTipText[i]);
            if (i == imgMenu.length - 1)
            {
                panelWest.add(Box.createVerticalGlue());
            }
            panelWest.add(opcMenu[i]);
        }
        opcMenu[Menu.INICIO.ordinal()].setIcon(new ImageIcon(pathImagenes + imgMenuActivo[0]));
        opcMenu[Menu.AYUDA.ordinal()].setCursor(new Cursor(Cursor.HAND_CURSOR));

        /**
         * Configuracion de los atajos de teclado Ctrl + (I)nicio : Ctrl +
         * (P)ersonal : Ctrl + (A)lumnos : Ctrl + (B)uscar, F1 para la ayuda.
         */
        KeyStroke keyInicio = KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyPersonal = KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyEstudiantes = KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyBuscar = KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyAyuda = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);

        Action actionInicio = new AbstractAction("Inicio")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                activarMenu(Menu.INICIO);
            }
        };
        Action actionPersonal = new AbstractAction("Personal")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                activarMenu(Menu.PERSONAL);
            }
        };
        Action actionEstudiantes = new AbstractAction("Estudiantes")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                activarMenu(Menu.ESTUDIANTES);
            }
        };
        Action actionBuscar = new AbstractAction("Buscar")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                activarMenu(Menu.BUSCAR);
            }
        };
        Action actionAyuda = new AbstractAction("Ayuda")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                activarAyuda();
            }
        };
        opcMenu[Menu.INICIO.ordinal()].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyInicio, "Inicio");
        opcMenu[Menu.INICIO.ordinal()].getActionMap().put("Inicio", actionInicio);
        opcMenu[Menu.PERSONAL.ordinal()].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyPersonal, "Personal");
        opcMenu[Menu.PERSONAL.ordinal()].getActionMap().put("Personal", actionPersonal);
        opcMenu[Menu.ESTUDIANTES.ordinal()].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyEstudiantes, "Estudiantes");
        opcMenu[Menu.ESTUDIANTES.ordinal()].getActionMap().put("Estudiantes", actionEstudiantes);
        opcMenu[Menu.BUSCAR.ordinal()].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyBuscar, "Buscar");
        opcMenu[Menu.BUSCAR.ordinal()].getActionMap().put("Buscar", actionBuscar);
        opcMenu[Menu.AYUDA.ordinal()].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyAyuda, "Ayuda");
        opcMenu[Menu.AYUDA.ordinal()].getActionMap().put("Ayuda", actionAyuda);

        opcMenu[Menu.INICIO.ordinal()].addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent evt)
            {
                if (menuActivo != Menu.INICIO)
                {
                    opcMenu[Menu.INICIO.ordinal()].setIcon(new ImageIcon(pathImagenes + imgMenu[0]));
                }
            }

            @Override
            public void mouseEntered(MouseEvent evt)
            {
                if (menuActivo != Menu.INICIO)
                {
                    opcMenu[Menu.INICIO.ordinal()].setIcon(new ImageIcon(pathImagenes + "inicio_Hover.png"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent evt)
            {
                if (evt.getButton() == MouseEvent.BUTTON1)
                {
                    activarMenu(Menu.INICIO);
                }
            }
        });

        opcMenu[Menu.PERSONAL.ordinal()].addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent evt)
            {
                if (menuActivo != Menu.PERSONAL)
                {
                    opcMenu[Menu.PERSONAL.ordinal()].setIcon(new ImageIcon(pathImagenes + imgMenu[1]));
                }
            }

            @Override
            public void mouseEntered(MouseEvent evt)
            {
                if (menuActivo != Menu.PERSONAL)
                {
                    opcMenu[Menu.PERSONAL.ordinal()].setIcon(new ImageIcon(pathImagenes + "personal_Hover.png"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent evt)
            {
                if (evt.getButton() == MouseEvent.BUTTON1)
                {
                    activarMenu(Menu.PERSONAL);
                }                
            }
        });

        opcMenu[Menu.ESTUDIANTES.ordinal()].addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent evt)
            {
                if (menuActivo != Menu.ESTUDIANTES)
                {
                    opcMenu[Menu.ESTUDIANTES.ordinal()].setIcon(new ImageIcon(pathImagenes + imgMenu[2]));
                }
            }

            @Override
            public void mouseEntered(MouseEvent evt)
            {
                if (menuActivo != Menu.ESTUDIANTES)
                {
                    opcMenu[Menu.ESTUDIANTES.ordinal()].setIcon(new ImageIcon(pathImagenes + "estudiantes_Hover.png"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent evt)
            {
                if (evt.getButton() == MouseEvent.BUTTON1)
                {
                    activarMenu(Menu.ESTUDIANTES);
                }                
            }
        });

        opcMenu[Menu.BUSCAR.ordinal()].addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent evt)
            {
                if (menuActivo != Menu.BUSCAR)
                {
                    opcMenu[Menu.BUSCAR.ordinal()].setIcon(new ImageIcon(pathImagenes + imgMenu[3]));
                }
            }

            @Override
            public void mouseEntered(MouseEvent evt)
            {
                if (menuActivo != Menu.BUSCAR)
                {
                    opcMenu[Menu.BUSCAR.ordinal()].setIcon(new ImageIcon(pathImagenes + "buscar_Hover.png"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent evt)
            {
                if (evt.getButton() == MouseEvent.BUTTON1)
                {
                    activarMenu(Menu.BUSCAR);
                }
            }

        });

        opcMenu[Menu.AYUDA.ordinal()].addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent evt)
            {
                opcMenu[Menu.AYUDA.ordinal()].setIcon(new ImageIcon(pathImagenes + imgMenu[4]));
            }

            @Override
            public void mouseEntered(MouseEvent evt)
            {
                opcMenu[Menu.AYUDA.ordinal()].setIcon(new ImageIcon(pathImagenes + "ayuda_Hover.png"));
            }

            @Override
            public void mousePressed(MouseEvent evt)
            {
                if (evt.getButton() == MouseEvent.BUTTON1)
                {
                    activarAyuda();
                }
            }
        });
    }

    /**
     * En el panelCenter se muestra la vista de cada opcion de menu.
     */
    private void initPanelCenter()
    {
        panelCenter.add(new MenuInicio(), "Panel 0");
        panelCenter.add(mnuPersonal, "Panel 1");
        panelCenter.add(mnuAlumnos, "Panel 2");
        panelCenter.add(mnuBuscar, "Panel 3");
    }

    /**
     * Permite iterar entre los menus este es visible en el cardLayoud agregado
     * en el panel central.
     */
    private void activarMenu(Menu menuIr)
    {
        if (menuIr == Menu.PERSONAL)
        {
            mnuPersonal.establecerEstadoInicial();
        } else if (menuIr == Menu.ESTUDIANTES)
        {
            mnuAlumnos.establecerEstadoInicial();
        } else if (menuIr == Menu.BUSCAR)
        {
            mnuBuscar.establecerEstadoInicial();
        }

        if (menuIr != menuActivo)
        {
            opcMenu[menuActivo.ordinal()].setIcon(new ImageIcon(pathImagenes + imgMenu[menuActivo.ordinal()]));
            opcMenu[menuIr.ordinal()].setIcon(new ImageIcon(pathImagenes + imgMenuActivo[menuIr.ordinal()]));
            menuActivo = menuIr;
            cardLayout.show(panelCenter, "Panel " + menuActivo.ordinal());
        }
    }

    /**
     * Abre un manual de usaurio en el navegador predeterminado.
     */
    private void activarAyuda()
    {
        try
        {
            String rutaRelativa = "src/Manual/C_+_+.pdf";
            File archivo = new File(rutaRelativa);
            URI uri = archivo.toURI();
            Desktop.getDesktop().browse(uri);
        } catch (IOException e)
        {
        }
    }
}
