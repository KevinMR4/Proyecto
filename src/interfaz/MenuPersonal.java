package interfaz;

import ctrl.ManipulacionArchivos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import poo.Alumnos;
import poo.Datos;
import poo.HistorialClinico;
import poo.Personal;

/**
 *
 * @author alfredo
 */
public class MenuPersonal extends JPanel implements EstadoInicial
{

    private JPanel panelHerramientas;
    private JPanel panelAreaTrabajo;
    private JTabbedPane tabbedPane;
    

    private final String pathImagenes = "src/interfaz/imagenes/";

    private final JLabel[] iconos = new JLabel[9];
    private final JLabel separador = new JLabel(new ImageIcon(pathImagenes + "separador.png"));
    private final JComboBox padecimientos = new JComboBox(new String[]
    {
        "", "Desnutricion", "Sobrepeso", "Alergias", "Obesidad", "Diabetes", "Otra"
    });

    @Override
    public void establecerEstadoInicial()
    {
        tabbedPane.setSelectedIndex(0);
        formRegistro.limpiarFormulario();
    }

    private enum Herramienta
    {
        BUSCAR,
        GUARDAR,
        LIMPIAR,
        ACTUALIZAR,
        RESTABLEZER,
        ORDENAR,
        AMBOS,
        HOMBRE,
        MUJER,
        PADECIMIENTO
    }

    private FormularioDatos formRegistro;
    private FormularioDatos formModificar;
    private FormularioDatos formDatos;
    private FormularioMedico formularioMedico;
    private JPanel contenedorTabla;
    private DefaultTableModel model;
    private JTable tabla;
    private JScrollPane scrollTabla;

    private JButton btnGuardarCambios;
    private JButton btnGuardarConsulta;
    private Datos[] listaActualMostrar;
    private Datos[] arrayCompletoRegistros;
    private boolean tipoOrdenamiento = true;
    private final boolean tipoUsuaurio;
    private int indexRegistro;
    private int filtros;
            
    public MenuPersonal(boolean tipoUsuario)
    {
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.tipoUsuaurio = tipoUsuario;

        arrayCompletoRegistros = (Datos[]) ctrl.ManipulacionArchivos.cargaArch("datos.dat");

        initPanelNorth();
        initPanelSouth();

        this.add(panelHerramientas, BorderLayout.NORTH);
        this.add(panelAreaTrabajo, BorderLayout.CENTER);
    }

    private void initPanelNorth()
    {
        panelHerramientas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelHerramientas.setBackground(new Color(174, 214, 241));
        for (int i = 0; i < iconos.length; i++)
        {
            iconos[i] = new JLabel();
        }

        String[] txt =
        {
            "Buscar Registro", "Guardar Registro", "Limpiar Formulario", "Actualizar Registro", "Restablecer Registro", "Ordenar Frecuencia Descendente", "Mostar Ambos", "Mostrar Hombres", "mostrar Mujeres"
        };
        String[] nomIcon =
        {
            "buscar_registro.png", "guardar.png", "limpiar.png", "actualizar.png", "restablecer.png", "ordenar.png", "ambos.png", "hombre.png", "mujer.png"
        };
        //String[] hover = {"guardar_Hover.png", "limpiar_Hover.png", "buscar_registro_Hover.png", "actualizar_Hover.png", "restablecer_Hover.png", "ordenar_a-z_Hover.png"};

        initHerramientas(txt, nomIcon);
        verHerramientas(iconos[1], iconos[2]);
        separador.setVisible(false);
        padecimientos.setVisible(false);
        panelHerramientas.add(padecimientos);

        /**
         * Configuara cada herramienta de la barra de herramientas.
         */
        iconos[Herramienta.BUSCAR.ordinal()].addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent evt)
            {
                iconos[0].setIcon(new ImageIcon(pathImagenes + nomIcon[0]));
            }

            @Override
            public void mouseEntered(MouseEvent evt)
            {
                iconos[0].setIcon(new ImageIcon(pathImagenes + "buscar_registro_Hover.png"));
            }

            @Override
            public void mouseClicked(MouseEvent evt)
            {
                if (iconos[0].isEnabled())
                {
                    arrayCompletoRegistros = (Datos[]) ctrl.ManipulacionArchivos.cargaArch("datos.dat");
                    switch (tabbedPane.getSelectedIndex())
                    {
                        case 1:
                            indexRegistro = buscarRegistroModificar(arrayCompletoRegistros, formModificar);
                            
                            ctrl.CtrlInterfaz.habilita((indexRegistro >= 0), formModificar.getEstatus(), formModificar.getDesnutricion(), formModificar.getSobrepeso(), formModificar.getAlergias(), formModificar.getObesidad(), formModificar.getDiabetes(), formModificar.getOtras());
                            ctrl.CtrlInterfaz.habilita((indexRegistro >= 0), btnGuardarCambios, iconos[3], iconos[4]);
                            if (!tipoUsuaurio)
                            {
                                ctrl.CtrlInterfaz.habilita((indexRegistro >= 0), formModificar.getViveCon());
                            }    
                            
                            if(indexRegistro < 0)
                            {
                                formModificar.limpiarFormulario();
                                formModificar.getCve().setEnabled(false);
                            }
                            break;
                        case 2:
                            indexRegistro = buscarRegistroModificar(arrayCompletoRegistros, null);
                            if(indexRegistro >= 0)
                            {
                                padecimientos.setSelectedIndex(0);
                                iconos[6].setIcon(new ImageIcon(pathImagenes + "ambos_Hover.png"));
                                iconos[7].setIcon(new ImageIcon(pathImagenes + "hombre.png"));
                                iconos[8].setIcon(new ImageIcon(pathImagenes + "mujer.png"));
                                filtros = 0;
                                filtrar(arrayCompletoRegistros, filtros, padecimientos.getSelectedIndex(), tipoUsuaurio);
                                selectRow(tabla, 0, arrayCompletoRegistros[indexRegistro].getCve());
                            }   break;
                        case 3:
                            indexRegistro = buscarRegistroModificar(arrayCompletoRegistros, formDatos);
                            if(indexRegistro >= 0)
                            {
                                ctrl.CtrlInterfaz.habilita(true, iconos[1], iconos[2]);
                                formularioMedico.habilitarFormulario(true);
                                btnGuardarConsulta.setEnabled(true);
                            }else
                            {
                                ctrl.CtrlInterfaz.habilita(false, iconos[1], iconos[2]);
                                formDatos.limpiarFormulario();
                                formularioMedico.limpiarFormulario(false);
                                btnGuardarConsulta.setEnabled(false);
                            }
                            break;
                    }
                }
            }
        });

        iconos[Herramienta.GUARDAR.ordinal()].addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent evt)
            {
                iconos[1].setIcon(new ImageIcon(pathImagenes + nomIcon[1]));
            }

            @Override
            public void mouseEntered(MouseEvent evt)
            {
                iconos[1].setIcon(new ImageIcon(pathImagenes + "guardar_Hover.png"));
            }

            @Override
            public void mouseClicked(MouseEvent evt)
            {
                if(tabbedPane.getSelectedIndex()==0)
                {
                    guardarRegistro(formRegistro, tipoUsuaurio);
                }else if(iconos[1].isEnabled() && tabbedPane.getSelectedIndex()==3)
                {
                    guardarConsulta(formularioMedico, indexRegistro);
                }
            }
        });
        iconos[Herramienta.LIMPIAR.ordinal()].addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent evt)
            {
                iconos[2].setIcon(new ImageIcon(pathImagenes + nomIcon[2]));
            }

            @Override
            public void mouseEntered(MouseEvent evt)
            {
                iconos[2].setIcon(new ImageIcon(pathImagenes + "limpiar_Hover.png"));
            }

            @Override
            public void mouseClicked(MouseEvent evt)
            {
                if (iconos[2].isEnabled())
                {
                    if (tabbedPane.getSelectedIndex() == 0)
                    {
                        formRegistro.limpiarFormulario();
                    } else if (tabbedPane.getSelectedIndex() == 3)
                    {
                        formularioMedico.limpiarFormulario(true);
                    }

                }
            }
        });

        iconos[Herramienta.ACTUALIZAR.ordinal()].addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent evt)
            {
                iconos[3].setIcon(new ImageIcon(pathImagenes + nomIcon[3]));
            }

            @Override
            public void mouseEntered(MouseEvent evt)
            {
                iconos[3].setIcon(new ImageIcon(pathImagenes + "actualizar_Hover.png"));
            }

            @Override
            public void mouseClicked(MouseEvent evt)
            {
                if (iconos[3].isEnabled())
                {
                    guardarCambiosRegistro(formModificar, arrayCompletoRegistros, tipoUsuaurio, indexRegistro);             
                }
            }
        });

        iconos[Herramienta.RESTABLEZER.ordinal()].addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent evt)
            {
                iconos[4].setIcon(new ImageIcon(pathImagenes + nomIcon[4]));
            }

            @Override
            public void mouseEntered(MouseEvent evt)
            {
                iconos[4].setIcon(new ImageIcon(pathImagenes + "restablecer_Hover.png"));
            }

            @Override
            public void mouseClicked(MouseEvent evt)
            {
                if (iconos[4].isEnabled() && arrayCompletoRegistros != null && indexRegistro < arrayCompletoRegistros.length && indexRegistro >= 0)
                {
                    mostrarDatosModificar(arrayCompletoRegistros[indexRegistro], formModificar);                   
                }
            }
        });

        iconos[Herramienta.ORDENAR.ordinal()].addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent evt)
            {
                iconos[5].setIcon(new ImageIcon(pathImagenes + nomIcon[5]));
            }

            @Override
            public void mouseEntered(MouseEvent evt)
            {
                iconos[5].setIcon(new ImageIcon(pathImagenes + "ordenar_Hover.png"));
            }

            @Override
            public void mouseClicked(MouseEvent evt)
            {
                if (iconos[5].isEnabled() && listaActualMostrar != null)
                {
                    Datos tmp;
                    for (int i = 0; i < listaActualMostrar.length; i++)
                    {
                        for (int j = 0; j < listaActualMostrar.length - 1; j++)
                        {
                            if (tipoOrdenamiento && listaActualMostrar[j].getNom().compareTo(listaActualMostrar[j + 1].getNom()) > 0)
                            {
                                tmp = listaActualMostrar[j];
                                listaActualMostrar[j] = listaActualMostrar[j + 1];
                                listaActualMostrar[j + 1] = tmp;
                            } else if (!tipoOrdenamiento && listaActualMostrar[j].getNom().compareTo(listaActualMostrar[j + 1].getNom()) < 0)
                            {
                                tmp = listaActualMostrar[j];
                                listaActualMostrar[j] = listaActualMostrar[j + 1];
                                listaActualMostrar[j + 1] = tmp;
                            }
                        }
                    }
                    tipoOrdenamiento = (!tipoOrdenamiento);
                    model.setRowCount(0);
                    llenarTabla(listaActualMostrar, tipoUsuaurio);
                }
            }
        });

        iconos[Herramienta.AMBOS.ordinal()].addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent evt)
            {
                if(filtros != 0)
                {
                    iconos[6].setIcon(new ImageIcon(pathImagenes + nomIcon[6]));
                }                
            }

            @Override
            public void mouseEntered(MouseEvent evt)
            {
                if(filtros != 0)
                {
                    iconos[6].setIcon(new ImageIcon(pathImagenes + "ambos_Hover.png"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent evt)
            {
                if(iconos[6].isEnabled() && filtros != 0)
                {
                    switch (filtros)
                    {
                        case 1:
                            iconos[7].setIcon(new ImageIcon(pathImagenes + "hombre.png"));
                            break;
                        case 2:
                            iconos[8].setIcon(new ImageIcon(pathImagenes + "mujer.png"));
                    }
                    iconos[6].setIcon(new ImageIcon(pathImagenes + "ambos_Hover.png"));
                    filtros = 0;
                    filtrar(arrayCompletoRegistros, filtros, padecimientos.getSelectedIndex(), tipoUsuaurio);                  
                }
            }
        });

        iconos[Herramienta.HOMBRE.ordinal()].addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent evt)
            {
                if(filtros != 1)
                {
                    iconos[7].setIcon(new ImageIcon(pathImagenes + nomIcon[7]));
                }
            }

            @Override
            public void mouseEntered(MouseEvent evt)
            {
                if(filtros != 1)
                {
                    iconos[7].setIcon(new ImageIcon(pathImagenes + "hombre_Hover.png"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent evt)
            {
                if(iconos[7].isEnabled() && filtros != 1)
                {
                    switch (filtros)
                    {
                        case 0:
                            iconos[6].setIcon(new ImageIcon(pathImagenes + "ambos.png"));
                            break;
                        case 2:
                            iconos[8].setIcon(new ImageIcon(pathImagenes + "mujer.png"));
                    }
                    iconos[7].setIcon(new ImageIcon(pathImagenes + "hombre_Hover.png"));                            
                    filtros = 1;
                    filtrar(arrayCompletoRegistros, filtros, padecimientos.getSelectedIndex(), tipoUsuaurio);
                }
            }
        });

        iconos[Herramienta.MUJER.ordinal()].addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent evt)
            {
                if(filtros != 2)
                {
                    iconos[8].setIcon(new ImageIcon(pathImagenes + nomIcon[8]));
                }
            }

            @Override
            public void mouseEntered(MouseEvent evt)
            {
                if(filtros != 2)
                {
                    iconos[8].setIcon(new ImageIcon(pathImagenes + "mujer_Hover.png"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent evt)
            {
                if (iconos[8].isEnabled() && filtros != 2)
                {
                    switch (filtros)
                    {
                        case 0:
                            iconos[6].setIcon(new ImageIcon(pathImagenes + "ambos.png"));
                            break;
                        case 1:
                            iconos[7].setIcon(new ImageIcon(pathImagenes + "hombre.png"));
                    }                           
                    iconos[8].setIcon(new ImageIcon(pathImagenes + "mujer_Hover.png"));
                    filtros = 2;
                    filtrar(arrayCompletoRegistros, filtros, padecimientos.getSelectedIndex(), tipoUsuaurio);
                }
            }
        });
        
        padecimientos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar(arrayCompletoRegistros, filtros, padecimientos.getSelectedIndex(), tipoUsuaurio);
            }
        });
    }

    private void initPanelSouth()
    {
        panelAreaTrabajo = new JPanel();
        panelAreaTrabajo.setBackground(Color.WHITE);
        panelAreaTrabajo.setBorder(new EmptyBorder(20, 0, 0, 0));
        panelAreaTrabajo.setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();

        initPanelRegistrar();
        initPanelModificar();
        initPanelConsultar();
        initConsultaMedica();

        tabbedPane.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                int selectedIndex = tabbedPane.getSelectedIndex();
                padecimientos.setVisible(false);
                separador.setVisible(false);
                switch (selectedIndex)
                {
                    case 0:
                        verHerramientas(iconos[1], iconos[2]);
                        ctrl.CtrlInterfaz.habilita(true, iconos[1], iconos[2]);
                        formRegistro.limpiarFormulario();
                        break;
                    case 1:
                        formModificar.limpiarFormulario();
                        verHerramientas(iconos[0], iconos[3], iconos[4]);
                        ctrl.CtrlInterfaz.habilita(false, formModificar.getCve(), formModificar.getNombre(), formModificar.getPrimerAp(), formModificar.getSegundoAp());
                        ctrl.CtrlInterfaz.habilita(false, formModificar.getEstatus(), formModificar.getRbtMasculino(), formModificar.getRbtFemenino());
                        ctrl.CtrlInterfaz.habilita(false, formModificar.getDesnutricion(), formModificar.getSobrepeso(), formModificar.getAlergias(), formModificar.getObesidad(), formModificar.getDiabetes(), formModificar.getOtras());
                        ctrl.CtrlInterfaz.habilita(false, btnGuardarCambios, iconos[3], iconos[4]);
                        ctrl.CtrlInterfaz.habilita(true, iconos[0]);
                        if (!tipoUsuaurio)
                        {
                            ctrl.CtrlInterfaz.habilita(false, formModificar.getViveCon());
                        }
                        break;
                    case 2:
                        verHerramientas(iconos[0], iconos[5], iconos[6], iconos[7], iconos[8]);
                        separador.setVisible(true);
                        padecimientos.setVisible(true);
                        iconos[6].setIcon(new ImageIcon(pathImagenes + "ambos_Hover.png"));
                        iconos[7].setIcon(new ImageIcon(pathImagenes + "hombre.png"));
                        iconos[8].setIcon(new ImageIcon(pathImagenes + "mujer.png"));
                        padecimientos.setSelectedIndex(0);
                        arrayCompletoRegistros = (Datos[]) ManipulacionArchivos.cargaArch(contenedorTabla, "datos.dat");
                        ctrl.CtrlInterfaz.habilita((arrayCompletoRegistros != null), iconos[0], iconos[5], iconos[6], iconos[7], iconos[8], padecimientos);
                        if (arrayCompletoRegistros != null)
                        {                           
                            iconos[6].setIcon(new ImageIcon(pathImagenes + "ambos_Hover.png"));
                            filtros = 0;
                            filtrar(arrayCompletoRegistros, filtros, padecimientos.getSelectedIndex(), tipoUsuaurio);
                            if(model.getRowCount() == 0)
                            {
                                ctrl.CtrlInterfaz.habilita(false, iconos[0], iconos[5], iconos[6], iconos[7], iconos[8], padecimientos);
                                JOptionPane.showMessageDialog(null, "No se han econtrado registros", "Registros Vacios", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        break;
                    case 3:
                        verHerramientas(iconos[0], iconos[1], iconos[2]);
                        iconos[0].setEnabled(true);
                        ctrl.CtrlInterfaz.habilita(false, iconos[1], iconos[2]);
                        formDatos.getCve().setEditable(false);
                        formDatos.getNombre().setEditable(false);
                        formDatos.getPrimerAp().setEditable(false);
                        formDatos.getSegundoAp().setEditable(false);
                        formDatos.getOtrasCual().setEditable(false);
                        ctrl.CtrlInterfaz.habilita(false, formDatos.getEstatus(), formDatos.getRbtMasculino(), formDatos.getRbtFemenino());
                        ctrl.CtrlInterfaz.habilita(false, formDatos.getDesnutricion(), formDatos.getSobrepeso(), formDatos.getAlergias(), formDatos.getObesidad(), formDatos.getDiabetes(), formDatos.getOtras());
                        if (!tipoUsuaurio)
                        {
                             ctrl.CtrlInterfaz.habilita(false,formDatos.getViveCon());
                        }
                        formDatos.limpiarFormulario();
                        formularioMedico.limpiarFormulario(false);
                        btnGuardarConsulta.setEnabled(false);
                }
            }
        });

        /**
         * Configuracion de los atajos de teclado para moverse por las pestañas
         * Ctrl + 1 => Nuevo Registro : Ctrl + 2 => Modificar Registro Ctrl + 3
         * => Consultar Registros : Ctrl + 4 => Nueva Consulta Medica.
         */
        KeyStroke keyCtrl1 = KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyCtrl2 = KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyCtrl3 = KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyCtrl4 = KeyStroke.getKeyStroke(KeyEvent.VK_4, KeyEvent.CTRL_DOWN_MASK);

        Action actionCtrl1 = new AbstractAction("Pestaña_1")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tabbedPane.setSelectedIndex(0);
            }
        };
        Action actionCtrl2 = new AbstractAction("Pestaña_2")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tabbedPane.setSelectedIndex(1);
            }
        };
        Action actionCtrl3 = new AbstractAction("Pestaña_3")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tabbedPane.setSelectedIndex(2);
            }
        };
        Action actionCtrl4 = new AbstractAction("Pestaña_4")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tabbedPane.setSelectedIndex(3);
            }
        };

        tabbedPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyCtrl1, "Pestaña_1");
        tabbedPane.getActionMap().put("Pestaña_1", actionCtrl1);
        tabbedPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyCtrl2, "Pestaña_2");
        tabbedPane.getActionMap().put("Pestaña_2", actionCtrl2);
        tabbedPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyCtrl3, "Pestaña_3");
        tabbedPane.getActionMap().put("Pestaña_3", actionCtrl3);
        tabbedPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyCtrl4, "Pestaña_4");
        tabbedPane.getActionMap().put("Pestaña_4", actionCtrl4);

        panelAreaTrabajo.add(tabbedPane, BorderLayout.CENTER);
    }

    private void initPanelRegistrar()
    {
        JPanel contenedor = new JPanel();
        JPanel contenedor1 = new JPanel();
        JPanel contenedor2 = new JPanel();

        JButton btnCancelar = new JButton("Cancelar");
        JButton btnGuardar = new JButton("Guardar Registro");
        formRegistro = new FormularioDatos(tipoUsuaurio, btnGuardar);

        contenedor1.add(formRegistro);
        contenedor2.add(btnCancelar);
        contenedor2.add(btnGuardar);

        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.add(contenedor1);
        contenedor.add(contenedor2);
        tabbedPane.addTab("Nuevo Registro", null, contenedor);

        btnGuardar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (tabbedPane.getSelectedIndex() == 0)
                {
                    guardarRegistro(formRegistro, tipoUsuaurio);
                }

            }
        });
        btnCancelar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                formRegistro.limpiarFormulario();
            }
        });
    }

    private void initPanelModificar()
    {
        JPanel contenedor = new JPanel();
        JPanel contenedor1 = new JPanel();
        JPanel contenedor2 = new JPanel();

        btnGuardarCambios = new JButton("Actualizar Registro");
        formModificar = new FormularioDatos(tipoUsuaurio, btnGuardarCambios);
        contenedor1.add(formModificar);
        contenedor2.add(btnGuardarCambios);

        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.add(contenedor1);
        contenedor.add(contenedor2);
        tabbedPane.addTab("Modificar Registro", null, contenedor);

        btnGuardarCambios.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                guardarCambiosRegistro(formModificar, arrayCompletoRegistros, tipoUsuaurio, indexRegistro);
            }
        });
    }

    private void initPanelConsultar()
    {
        String[] columnNames;
        if (tipoUsuaurio)
        {
            columnNames = new String[]
            {
                "Clave", "Nombre(s)", "Apellido Paterno", "Apellido Materno", "Sexo", "Estatus", "Desnutricion", "Sobrepeso", "Alergias", "Obesida", "Diabetes", "Otra"
            };
        } else
        {
            columnNames = new String[]
            {
                "Clave", "Nombre(s)", "Apellido Paterno", "Apellido Materno", "Sexo", "Carrera", "Vive Con", "Desnutricion", "Sobrepeso", "Alergias", "Obesida", "Diabetes", "Otra"
            };
        }
        contenedorTabla = new JPanel(new BorderLayout());
        model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        model.setColumnIdentifiers(columnNames);

        tabla = new JTable(model);
        tabla.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1)
                {
                    HistorialClinico[][] historial = (HistorialClinico[][]) ctrl.ManipulacionArchivos.cargaArch("historial.dat", true);
                    int filaSeleccionada = tabla.getSelectedRow();
                    int index = buscarRegistro(arrayCompletoRegistros, (String)tabla.getValueAt(filaSeleccionada, 0));
                    if (index != -1 && historial[index] != null)
                    {
                        VentanaHistorial ventanaEmergente = new VentanaHistorial(tabla.getValueAt(filaSeleccionada, 1) + " " + tabla.getValueAt(filaSeleccionada, 2) + " " + tabla.getValueAt(filaSeleccionada, 3), historial[index]);
                        ventanaEmergente.setVisible(true);
                    }else
                    {
                        JOptionPane.showMessageDialog(null, "El registro seleccionado no tiene consultas medicas", "No hay consultas medicas", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        scrollTabla = new JScrollPane(tabla);
        contenedorTabla.add(scrollTabla, BorderLayout.CENTER);
        tabbedPane.addTab("Consultar Registros", null, contenedorTabla);
    }

    private void initConsultaMedica()
    {
        JPanel contenedor = new JPanel();
        JPanel contenedor1 = new JPanel();
        JPanel contenedor2 = new JPanel();
        JPanel contenedor3 = new JPanel();

        btnGuardarConsulta = new JButton("Guardar Consulta Medica");
        formDatos = new FormularioDatos(tipoUsuaurio, null);
        formularioMedico = new FormularioMedico();
        formularioMedico.setBorder(BorderFactory.createTitledBorder(""));
        contenedor1.add(formDatos);
        contenedor2.add(formularioMedico);
        contenedor3.add(btnGuardarConsulta);

        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.add(contenedor1);
        contenedor.add(contenedor2);
        contenedor.add(contenedor3);
        tabbedPane.addTab("Nueva Consulta Medica", null, new JScrollPane(contenedor));

        btnGuardarConsulta.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //System.out.println(indexRegistro);
                guardarConsulta(formularioMedico, indexRegistro);
            }
        });
    }

    /**
     * inicializa los iconos de la barra de herramientas
     *
     * @param txt se asigna como setToolTipText
     * @param nomIcon contiene el nombre del icono de la herramienta
     */
    private void initHerramientas(String[] txt, String[] nomIcon)
    {
        if (txt.length == nomIcon.length && nomIcon.length == iconos.length)
        {
            ImageIcon img;
            for (int i = 0; i < txt.length; i++)
            {
                img = new ImageIcon(pathImagenes + "" + nomIcon[i]);
                iconos[i].setIcon(img);
                iconos[i].setBorder(new EmptyBorder(0, 0, 0, 5));
                iconos[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                iconos[i].setToolTipText(txt[i]);
                if (i == 6)
                {
                    panelHerramientas.add(separador);
                }
                panelHerramientas.add(iconos[i]);
            }
        }
    }

    /**
     * Habilitas las herramientas, dependera de la pestaña donde se encuentre
     * activara las herramientas que se ocupen
     *
     * @param s
     */
    private void verHerramientas(JLabel... s)
    {
        if (s.length <= iconos.length)
        {
            for (JLabel icono : iconos)
            {
                for (JLabel item : s)
                {
                    if (icono == item)
                    {
                        icono.setVisible(true);
                        break;
                    }
                    icono.setVisible(false);
                }
            }
        }
    }

    private void guardarRegistro(FormularioDatos form, boolean usurio)
    {
        if (form != null && form.camposVacios())
        {
            JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados");
        } else
        {
            if (form != null && form.validarFormulario())
            {
                Datos[] registros = (Datos[]) ctrl.ManipulacionArchivos.cargaArch("datos.dat");
                if (buscarRegistro(registros, form.getCve().getText()) < 0)
                {
                    if (ctrl.ManipulacionArchivos.guardarReg(null, insertarRegistro(registros, crearRegistro(form, usurio)), "datos.dat") && (ctrl.ManipulacionArchivos.guardarReg(crearRegistro(), "historial.dat")))
                    {
                        JOptionPane.showMessageDialog(panelAreaTrabajo, "Registro exitoso");
                        form.limpiarFormulario();
                        
                    } else
                    {
                        JOptionPane.showMessageDialog(panelAreaTrabajo, "No se a podido guardar el Registo", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
                    }
                } else
                {
                    JOptionPane.showMessageDialog(panelAreaTrabajo, "La clave ingresada no se encuentra disponible", "Clave duplicada", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
    
    private void guardarConsulta(FormularioMedico form, int index)
    {
        if (form != null && form.camposVacios())
        {
            JOptionPane.showMessageDialog(null, "Todos los campos especificados deben ser llenados");
        } else
        {
            if (form != null && form.validarFormulario())
            {
                HistorialClinico[][] historial = (HistorialClinico[][]) ctrl.ManipulacionArchivos.cargaArch("historial.dat", true);
                historial[index] = insertarRegistro(historial[index], crearRegistro(form));
                System.out.println(historial[index].length);
                if(ctrl.ManipulacionArchivos.guardarReg(historial, "historial.dat"))
                {
                    JOptionPane.showMessageDialog(panelAreaTrabajo, "Consulta guardada exitoso");
                    ctrl.CtrlInterfaz.habilita(false, iconos[1], iconos[2]);
                    formDatos.limpiarFormulario();
                    formularioMedico.limpiarFormulario(false);
                    btnGuardarConsulta.setEnabled(false);
                } else
                {
                    JOptionPane.showMessageDialog(panelAreaTrabajo, "No se a podido guardar la consulta", "Error al guardar la consulta", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private Datos crearRegistro(FormularioDatos form, boolean usuario)
    {
        if (usuario)
        {
            return new Personal(form.getEstatus().getSelectedIndex(),
                    form.getCve().getText(), form.getNombre().getText(),
                    form.getPrimerAp().getText(), form.getSegundoAp().getText(),
                    (form.getRbtMasculino().isSelected()) ? 'M' : 'F',
                    form.getDesnutricion().isSelected(), form.getSobrepeso().isSelected(),
                    form.getAlergias().isSelected(), form.getObesidad().isSelected(),
                    form.getDiabetes().isSelected(), (form.getOtras().isSelected()) ? form.getOtrasCual().getText() : "No");
        } else
        {
            return new Alumnos(form.getViveCon().getSelectedIndex(), form.getEstatus().getSelectedIndex(),
                    form.getCve().getText(), form.getNombre().getText(),
                    form.getPrimerAp().getText(), form.getSegundoAp().getText(),
                    (form.getRbtMasculino().isSelected()) ? 'M' : 'F',
                    form.getDesnutricion().isSelected(), form.getSobrepeso().isSelected(),
                    form.getAlergias().isSelected(), form.getObesidad().isSelected(),
                    form.getDiabetes().isSelected(), (form.getOtras().isSelected()) ? form.getOtrasCual().getText() : "No");
        }
    }
    
    private HistorialClinico [][] crearRegistro()
    {
        HistorialClinico[][] historial =(HistorialClinico[][]) ctrl.ManipulacionArchivos.cargaArch("historial.dat");
        if(historial == null)
        {
            historial = new HistorialClinico[1][];
            historial[0] = null;
        }else
        {
            HistorialClinico[][] tmphistorial = new HistorialClinico[historial.length + 1][];
            System.arraycopy(historial, 0, tmphistorial, 0, historial.length);
            tmphistorial[historial.length] =null;
            historial = tmphistorial;
        }
        System.out.println(historial.length);
        return historial;
    }

    private HistorialClinico crearRegistro(FormularioMedico form)
    {
        return new HistorialClinico(form.getPadecimientoCual().getText(), form.getAntecedentesCual().getText(), form.getMedicamentoCual().getText(), form.getPlanTratamientoCual().getText(), form.getDateFecha());
    }
    
    private Object[] insertarRegistro(Object[] registros, Object registro)
    {
        if (registros == null)
        {
            registros = new Datos[1];
        } else
        {
            Datos nvoArray[] = new Datos[registros.length + 1];
            System.arraycopy(registros, 0, nvoArray, 0, registros.length);
            registros = nvoArray;
        }
        registros[registros.length - 1] = registro;
        return registros;
    }
    
    private HistorialClinico[] insertarRegistro(HistorialClinico[] registros, HistorialClinico registro)
    {
        if (registros == null)
        {
            registros = new HistorialClinico[1];
        } else
        {
            HistorialClinico nvoArray[] = new HistorialClinico[registros.length + 1];
            System.arraycopy(registros, 0, nvoArray, 0, registros.length);
            registros = nvoArray;
        }
        registros[registros.length - 1] = registro;
        return registros;
    }

    private int buscarRegistroModificar(Datos[] registros, FormularioDatos form)
    {
        String clave = JOptionPane.showInputDialog("Ingrese clave del " + ((tipoUsuaurio) ? "Personal" : "Alumo"));
        int index = -1;
        if (clave != null && !clave.trim().isEmpty())
        {
            index = buscarRegistro(registros, clave);
            if (index >= 0 && ((tipoUsuaurio && registros[index] instanceof Personal) || (!tipoUsuaurio && registros[index] instanceof Alumnos)))
            {
               mostrarDatosModificar(registros[index], form);
            } else
            {
                JOptionPane.showMessageDialog(null, "Clave NO asociada a ningun registro de " + ((tipoUsuaurio) ? "Personal" : "Alumnos") + ".\nEs importante ingresar la clave tal y como aparecia al momento de realizar el registro", "Clave no encotrada", JOptionPane.ERROR_MESSAGE);             
                index = -1;
            }
        }
        return index;
    }
    
    private void mostrarDatosModificar(Datos dato, FormularioDatos form)
    {
        if(form != null)
        {
            form.getCve().setText(dato.getCve());
            form.getNombre().setText(dato.getNom());
            form.getPrimerAp().setText(dato.getPrimerAp());
            form.getSegundoAp().setText(dato.getSegundoAp());
            form.getEstatus().setSelectedIndex((tipoUsuaurio) ? ((Personal)dato).getEstatus() : ((Alumnos)dato).getCarrera());
            if(!tipoUsuaurio)
            {
                form.getViveCon().setSelectedIndex(((Alumnos)dato).getViveCon());
            }        
            form.getRbtMasculino().setSelected((dato.getSexo() == 'M'));
            form.getRbtFemenino().setSelected((dato.getSexo() == 'F'));
            form.getDesnutricion().setSelected(dato.isDesnutriccion());
            form.getSobrepeso().setSelected(dato.isSobrepeso());
            form.getAlergias().setSelected(dato.isAlergias());
            form.getObesidad().setSelected(dato.isObecidad());
            form.getDiabetes().setSelected(dato.isDiabetes());
            form.getOtras().setSelected(!dato.getOtras().equals("No"));
            if(!dato.getOtras().equals("No"))
            {
                form.getOtrasCual().setText(dato.getOtras());
            }
//            ctrl.CtrlInterfaz.habilita(true, form.getEstatus());
//            ctrl.CtrlInterfaz.habilita(true, form.getDesnutricion(), form.getSobrepeso(), form.getAlergias(), form.getObesidad(), form.getDiabetes(), form.getOtras());
    ///        ctrl.CtrlInterfaz.habilita(true, btnGuardarCambios, iconos[3], iconos[4]);
        }
    }

    private void guardarCambiosRegistro(FormularioDatos form, Datos[] registros, boolean usurio, int index)
    {
        if (form != null && form.camposVacios())
        {
            JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados");
        } else
        {
            if (form != null && form.validarFormulario())
            {
                if (ctrl.ManipulacionArchivos.guardarReg(null, modifcarRegistro(registros, crearRegistro(form, usurio), index), "datos.dat"))
                {
                    JOptionPane.showMessageDialog(panelAreaTrabajo, "Registro actualizado exitosamente");
                    //form.limpiarFormulario();
                } else
                {
                    JOptionPane.showMessageDialog(panelAreaTrabajo, "No se a podido actualizar el Registo", "Error al actualizar el registro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private Object[] modifcarRegistro(Object[] registros, Object registro, int index)
    {
        if (registros != null && index >= 0 && index < registros.length)
        {
            registros[index] = registro;
        }
        return registros;
    }

    private void llenarTabla(Datos[] lista, boolean usuario)
    {
        listaActualMostrar = lista;
        Object[] fila = null;
        for (Datos dato : lista)
        {
            if (usuario && dato instanceof Personal)
            {
                fila = new Object[]
                {
                    dato.getCve(), dato.getNom(), dato.getPrimerAp(), dato.getSegundoAp(),
                    dato.getSexo(), FormularioDatos.ESTATUS[((Personal) dato).getEstatus()],
                    dato.isDesnutriccion() ? "Si" : "No", dato.isSobrepeso() ? "Si" : "No", dato.isAlergias() ? "Si" : "No",
                    dato.isObecidad() ? "Si" : "No", dato.isDiabetes() ? "Si" : "No", dato.getOtras()
                };

            } else if (!usuario && dato instanceof Alumnos)
            {
                fila = new Object[]
                {
                    dato.getCve(), dato.getNom(), dato.getPrimerAp(), dato.getSegundoAp(),
                    dato.getSexo(), FormularioDatos.CARRERAS[((Alumnos) dato).getCarrera()], FormularioDatos.VIVECON[((Alumnos) dato).getViveCon()],
                    dato.isDesnutriccion() ? "Si" : "No", dato.isSobrepeso() ? "Si" : "No", dato.isAlergias() ? "Si" : "No",
                    dato.isObecidad() ? "Si" : "No", dato.isDiabetes() ? "Si" : "No", dato.getOtras()
                };

            }
            if (fila != null)
            {
                model.addRow(fila);
                fila = null;
            }
        }
    }

    private int buscarRegistro(Datos[] registros, String clave)
    {
        int index = -1;
        if (registros != null)
        {
            for (int i = 0; i < registros.length; i++)
            {
                if (registros[i].getCve().equals(clave))
                {
                    return i;
                }
            }
        }
        return index;
    }

    private void filtrar(Datos[] registros, int sexo, int padecimineto, boolean usuario)
    {
        Object[] fila = null;
        model.setRowCount(0);
        if (registros != null)
        {
            for (Datos dato : registros)
            {
                if ((sexo == 0 || (dato.getSexo()=='M' && sexo == 1) || (dato.getSexo()=='F' && sexo == 2))
                        && ((padecimineto == 0 || (dato.isDesnutriccion() && padecimineto == 1) || (dato.isSobrepeso() && padecimineto == 2)
                            || (dato.isAlergias() && padecimineto == 3) || (dato.isObecidad() && padecimineto == 4))
                            || (dato.isDiabetes() && padecimineto == 5) || (!dato.getOtras().equals("No")&& padecimineto == 6)))
                {
                    if (usuario && dato instanceof Personal)
                    {
                        fila = new Object[]
                        {
                            dato.getCve(), dato.getNom(), dato.getPrimerAp(), dato.getSegundoAp(),
                            dato.getSexo(), FormularioDatos.ESTATUS[((Personal) dato).getEstatus()],
                            dato.isDesnutriccion() ? "Si" : "No", dato.isSobrepeso() ? "Si" : "No", dato.isAlergias() ? "Si" : "No",
                            dato.isObecidad() ? "Si" : "No", dato.isDiabetes() ? "Si" : "No", dato.getOtras()
                        };

                    } else if (!usuario && dato instanceof Alumnos)
                    {
                        fila = new Object[]
                        {
                            dato.getCve(), dato.getNom(), dato.getPrimerAp(), dato.getSegundoAp(),
                            dato.getSexo(), FormularioDatos.CARRERAS[((Alumnos) dato).getCarrera()], FormularioDatos.VIVECON[((Alumnos) dato).getViveCon()],
                            dato.isDesnutriccion() ? "Si" : "No", dato.isSobrepeso() ? "Si" : "No", dato.isAlergias() ? "Si" : "No",
                            dato.isObecidad() ? "Si" : "No", dato.isDiabetes() ? "Si" : "No", dato.getOtras()
                        };

                    }
                }
                if (fila != null)
                {
                    model.addRow(fila);
                    fila = null;
                }
            }
        }
    }
    
    private void selectRow(JTable table, int columnIndex, String key) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        for (int row = 0; row < rowCount; row++) {
            String cellValue = String.valueOf(model.getValueAt(row, columnIndex));
            if (cellValue.equals(key)) {
                table.setRowSelectionInterval(row, row);
                break;
            }
        }
    }
}
