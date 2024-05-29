package interfaz;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class FormularioDatos extends JPanel
{

    private JTextField cve;
    private JTextField nombre;
    private JTextField primerAp;
    private JTextField segundoAp;

    private ButtonGroup group;
    private JRadioButton rbtMasculino;
    private JRadioButton rbtFemenino;

    private JComboBox estatus;
    private JComboBox viveCon;

    private JCheckBox desnutricion;
    private JCheckBox sobrepeso;
    private JCheckBox alergias;
    private JCheckBox obesidad;
    private JCheckBox diabetes;
    private JCheckBox otras;
    private JTextField otrasCual;
    private JButton btn;

    private boolean tipoUsuario;

    private final String pathImagenes = "src/interfaz/imagenes/";
    
    public static final String[] ESTATUS =
    {
        "", "Base", "Temporal"
    };
    public static final String[] VIVECON =       
    {
        "", "Mamá", "Papá", "Ambos"
    };
    public static final String[] CARRERAS =
    {
        "", "Ing. en Software", "Ing. en Produccion Industrical", "Ing. en Plasticos", "Ing. Mecanica", "Lic. en Seguridad Ciudadana"
    };

    
    /**
     * Constructor para el formulario de registro
     *
     * @param tipoUsuario indica si es Personal (V) o Alumnos (F)
     */
    public FormularioDatos(boolean tipoUsuario, JButton btn)
    {
        this.setLayout(new GridBagLayout());
        this.tipoUsuario = tipoUsuario;
        this.btn = btn;
        initComponets();
    }

    public FormularioDatos(Color rgb, boolean tipoUsuario, JButton btn)
    {
        this.setLayout(new GridBagLayout());
        this.setBackground(rgb);
        this.tipoUsuario = tipoUsuario;
        this.btn = btn;
        initComponets();
    }

    /**
     * @return the cve
     */
    public JTextField getCve()
    {
        return cve;
    }

    /**
     * @return the nombre
     */
    public JTextField getNombre()
    {
        return nombre;
    }

    /**
     * @return the primerAp
     */
    public JTextField getPrimerAp()
    {
        return primerAp;
    }

    /**
     * @return the segundoAp
     */
    public JTextField getSegundoAp()
    {
        return segundoAp;
    }

    /**
     * @return the group
     */
    public ButtonGroup getGroup()
    {
        return group;
    }

    /**
     * @return the rbtMasculino
     */
    public JRadioButton getRbtMasculino()
    {
        return rbtMasculino;
    }

    /**
     * @return the rbtFemenino
     */
    public JRadioButton getRbtFemenino()
    {
        return rbtFemenino;
    }

    /**
     * @return the estatus
     */
    public JComboBox getEstatus()
    {
        return estatus;
    }

    /**
     * @return the carrera
     */
    public JComboBox getViveCon()
    {
        return viveCon;
    }

    /**
     * @return the desnutricion
     */
    public JCheckBox getDesnutricion()
    {
        return desnutricion;
    }

    /**
     * @return the sobrepeso
     */
    public JCheckBox getSobrepeso()
    {
        return sobrepeso;
    }

    /**
     * @return the alergias
     */
    public JCheckBox getAlergias()
    {
        return alergias;
    }

    /**
     * @return the obesidad
     */
    public JCheckBox getObesidad()
    {
        return obesidad;
    }

    /**
     * @return the diabetes
     */
    public JCheckBox getDiabetes()
    {
        return diabetes;
    }

    /**
     * @return the otras
     */
    public JCheckBox getOtras()
    {
        return otras;
    }

    /**
     * @return the otrasCual
     */
    public JTextField getOtrasCual()
    {
        return otrasCual;
    }

    /**
     * @return the type
     */
    public boolean isType()
    {
        return tipoUsuario;
    }

    /**
     * @param type the type to set
     */
    public void setType(boolean type)
    {
        this.tipoUsuario = type;
    }

    /**
     * Inicializando componentes.
     */
    private void initComponets()
    {
        cve = new JTextField(12);
        nombre = new JTextField(18);
        primerAp = new JTextField(18);
        segundoAp = new JTextField(18);
        desnutricion = new JCheckBox("Desnutricion");
        sobrepeso = new JCheckBox("Sobrepeso");
        alergias = new JCheckBox("Alergias");
        obesidad = new JCheckBox("Obesidad");
        diabetes = new JCheckBox("Diabetes");
        otras = new JCheckBox("Otro");
        otrasCual = new JTextField(18);
        otrasCual.setEnabled(false);

        String[] opcEstatus;
        if (isType())
        {
            opcEstatus = ESTATUS;
        } else
        {
            opcEstatus = CARRERAS;
        }
        String[] opcVive = VIVECON;

        viveCon = new JComboBox(opcVive);
        estatus = new JComboBox(opcEstatus);

        JPanel contenedor1 = new JPanel();
        contenedor1.setBorder(BorderFactory.createTitledBorder("Sexo"));
        group = new ButtonGroup();
        rbtMasculino = new JRadioButton("Masculino");
        rbtFemenino = new JRadioButton("Femenino");
        group.add(rbtMasculino);
        group.add(rbtFemenino);
        contenedor1.add(rbtMasculino);
        contenedor1.add(rbtFemenino);

        JPanel contenedor3 = new JPanel();
        JPanel contenedor4 = new JPanel();
        contenedor3.add(desnutricion);
        contenedor3.add(sobrepeso);
        contenedor3.add(alergias);
        contenedor3.add(obesidad);
        contenedor4.add(diabetes);
        contenedor4.add(otras);
        contenedor4.add(otrasCual);
        JPanel contenedor2 = new JPanel(new GridLayout(2, 1));
        contenedor2.setBorder(BorderFactory.createTitledBorder("Padeciminetos"));
        contenedor2.add(contenedor3);
        contenedor2.add(contenedor4);

        /**
         * Agregando componentes al JPane (CENTER) se asigna la poscion de
         * visualizacion.
         */
        this.add(new JLabel(new ImageIcon(pathImagenes + "avatar.png")),
                new GridBagConstraints(2, 0, 1, 2, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(10, 5, 5, 5), 0, 0));
        this.add(new JLabel("Clave"),
                new GridBagConstraints(0, 1, 2, 1, 0, 0, GridBagConstraints.SOUTH, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
        this.add(cve,
                new GridBagConstraints(0, 2, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
        this.add(new JLabel((tipoUsuario) ? "Estatus" : "Carrera"),
                new GridBagConstraints(3, 1, 1, 1, 0, 0, GridBagConstraints.SOUTH, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
        this.add(estatus,
                new GridBagConstraints(3, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
        this.add(new JLabel("Nombre(s)"),
                new GridBagConstraints(0, 3, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(20, 5, 5, 5), 0, 0));
        this.add(nombre,
                new GridBagConstraints(0, 4, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
        this.add(new JLabel("Apellido Paterno"),
                new GridBagConstraints(2, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(20, 5, 5, 5), 0, 0));
        this.add(primerAp,
                new GridBagConstraints(2, 4, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
        this.add(new JLabel("Apellido Materno"),
                new GridBagConstraints(3, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(20, 5, 5, 5), 0, 0));
        this.add(segundoAp,
                new GridBagConstraints(3, 4, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
        if (!isType())
        {
            this.add(new JLabel("Vive con"),
                    new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(20, 5, 5, 5), 0, 0));
            this.add(viveCon,
                    new GridBagConstraints(1, 6, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(20, 5, 5, 5), 0, 0));
        }
        this.add(contenedor1,
                new GridBagConstraints(0, 7, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
        this.add(contenedor2,
                new GridBagConstraints(2, 6, 2, 2, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(20, 5, 5, 5), 0, 0));

        /**
         * Asignando comportamiento de navegacion cambiando el foco tambien
         * validacion de entrada de caracteres ENTER solo cuando el campo no
         * esta vacio, si el campo contiene espacios puede continuar,
         * posteriormente se le indicara la validacion correspondiente.
         */
        cve.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                ctrl.Validaciones.validaAlfanumerico(e, 15, cve.getText());
                //System.out.println("Tecla Typed: " + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                enterKeyPressed(e, cve.getText(), estatus);
            }
        });
        estatus.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && estatus.getSelectedIndex() != 0)
                {
                    enterKeyPressed(e, " ", nombre);
                }
            }
        });
        nombre.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                ctrl.Validaciones.validaAlfabeticos(e, 30, nombre.getText());
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                enterKeyPressed(e, nombre.getText(), primerAp);
            }
        });
        primerAp.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                ctrl.Validaciones.validaAlfabeticos(e, 30, primerAp.getText());
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                enterKeyPressed(e, primerAp.getText(), segundoAp);
            }
        });
        segundoAp.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                ctrl.Validaciones.validaAlfabeticos(e, 30, segundoAp.getText());
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                enterKeyPressed(e, segundoAp.getText(), (isType()) ? rbtMasculino : viveCon);
            }
        });
        viveCon.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && viveCon.getSelectedIndex() != 0)
                {
                    enterKeyPressed(e, " ", rbtMasculino);
                }
            }
        });
        rbtMasculino.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && rbtMasculino.isSelected())
                {
                    enterKeyPressed(e, rbtMasculino.getText(), desnutricion);
                }
            }
        });
        rbtFemenino.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && getRbtFemenino().isSelected())
                {
                    enterKeyPressed(e, rbtFemenino.getText(), desnutricion);
                }
            }
        });
        otras.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                ctrl.CtrlInterfaz.itemStateChanged(otrasCual, e);
            }
        });
        otrasCual.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                ctrl.Validaciones.vanTxt(e);
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                enterKeyPressed(e, otrasCual.getText(), btn);
            }
        });
    }

    private void enterKeyPressed(KeyEvent e, String s, Object obj)
    {
        if (!s.isEmpty())
        {
            ctrl.Validaciones.enter(this, e, obj);
        }
    }

    public boolean validarFormulario()
    {
        if (!ctrl.Validaciones.validarInputCve(cve.getText()))
        {
            JOptionPane.showMessageDialog(null, "La clave no es valida, verifique y vuelva a intentar");
            ctrl.CtrlInterfaz.selecciona(cve);
            return false;
        }
        if (!ctrl.Validaciones.validarInputNombre(nombre.getText()))
        {
            JOptionPane.showMessageDialog(null, "El nombre no es valido,  verifique y vuelva a intentar");
            ctrl.CtrlInterfaz.selecciona(nombre);
            return false;
        }
        if (!ctrl.Validaciones.validarInputNombre(primerAp.getText()))
        {
            JOptionPane.showMessageDialog(null, "El apellido paterno no es valido, verifique y vuelva a intentar");
            ctrl.CtrlInterfaz.selecciona(primerAp);
            return false;
        }
        if (!ctrl.Validaciones.validarInputNombre(segundoAp.getText()))
        {
            JOptionPane.showMessageDialog(null, "El apellido materno no es valido, verifique y vuelva a intentar");
            ctrl.CtrlInterfaz.selecciona(segundoAp);
            return false;
        }
        return true;
    }

    public boolean camposVacios()
    {
        if (cve.getText().trim().isEmpty())
        {
            ctrl.CtrlInterfaz.selecciona(cve);
            return true;
        }
        if (nombre.getText().trim().isEmpty())
        {
            ctrl.CtrlInterfaz.selecciona(nombre);
            return true;
        }
        if (primerAp.getText().trim().isEmpty())
        {
            ctrl.CtrlInterfaz.selecciona(primerAp);
            return true;
        }
        if (segundoAp.getText().trim().isEmpty())
        {
            ctrl.CtrlInterfaz.selecciona(segundoAp);
            return true;
        }
        if (!rbtMasculino.isSelected() && !rbtFemenino.isSelected())
        {
            ctrl.CtrlInterfaz.cambia(rbtMasculino);
            return true;
        }
        if (estatus.getSelectedIndex() == 0)
        {
            ctrl.CtrlInterfaz.cambia(estatus);
            return true;
        }
        if (!isType() && viveCon.getSelectedIndex() == 0)
        {
            ctrl.CtrlInterfaz.cambia(viveCon);
            return true;
        }
        if (otras.isSelected() && otrasCual.getText().trim().isEmpty())
        {
            ctrl.CtrlInterfaz.selecciona(otrasCual);
            return true;
        }
        return false;
    }

    public void limpiarFormulario()
    {
        ctrl.CtrlInterfaz.limpiarComponentes(cve, cve, nombre, primerAp, segundoAp, estatus, viveCon, group, desnutricion, sobrepeso, alergias, obesidad, diabetes, otras, otrasCual);
    }

    public void habilitarComponentes(boolean enable)
    {
        getCve().setEnabled(false);
        getNombre().setEnabled(false);
        getPrimerAp().setEnabled(false);
        getSegundoAp().setEnabled(false);
        getEstatus().setEnabled(enable);
        if (!isType())
        {
            getViveCon().setEditable(enable);
        }
        getRbtMasculino().setEnabled(enable);
        getRbtFemenino().setEnabled(enable);
        // getFecha().setEnabled(enable);
        getDesnutricion().setEnabled(enable);
        getSobrepeso().setEnabled(enable);
        getAlergias().setEnabled(enable);
        getObesidad().setEnabled(enable);
        getDiabetes().setEnabled(enable);
        getOtras().setEnabled(enable);
        getOtrasCual().setEnabled(enable);
    }
}
