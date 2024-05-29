package interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class FormularioMedico extends JPanel
{
    private JCheckBox padecimientoActual;
    private JTextArea padecimientoCual;
    private JCheckBox antecedentes;
    private JTextArea antecedentesCual;
    private JCheckBox medicamento;
    private JTextArea medicamentoCual;
    private JCheckBox planTratamiento;
    private JTextArea planTratamientoCual;
    private JTextField fecha;
    private Date dateFecha;
    
    public FormularioMedico()
    {
        this.setLayout(new GridBagLayout());
        initComponets();
    }

    /**
     * @return the padecimientoActual
     */
    public JCheckBox getPadecimientoActual()
    {
        return padecimientoActual;
    }

    /**
     * @return the padecimientoCual
     */
    public JTextArea getPadecimientoCual()
    {
        return padecimientoCual;
    }

    /**
     * @return the antecedentes
     */
    public JCheckBox getAntecedentes()
    {
        return antecedentes;
    }

    /**
     * @return the antecedentesCual
     */
    public JTextArea getAntecedentesCual()
    {
        return antecedentesCual;
    }

    /**
     * @return the medicamento
     */
    public JCheckBox getMedicamento()
    {
        return medicamento;
    }

    /**
     * @return the medicamentoCual
     */
    public JTextArea getMedicamentoCual()
    {
        return medicamentoCual;
    }

    /**
     * @return the planTratamiento
     */
    public JCheckBox getPlanTratamiento()
    {
        return planTratamiento;
    }

    /**
     * @return the planTratamientoCual
     */
    public JTextArea getPlanTratamientoCual()
    {
        return planTratamientoCual;
    }

    /**
     * @return the fecha
     */
    public JTextField getFecha()
    {
        return fecha;
    }
    
    /**
     * @return the dateFecha
     */
    public Date getDateFecha()
    {
        return dateFecha;
    }

    private void initComponets()
    {
        fecha = new JTextField(17);
        padecimientoActual = new JCheckBox("Padecimineto Actual");
        padecimientoCual = new JTextArea(7, 35);
        padecimientoCual.setEnabled(false);
        antecedentes = new JCheckBox("Antecedentes");
        antecedentesCual = new JTextArea(7, 35);
        antecedentesCual.setEnabled(false);
        medicamento = new JCheckBox("Medicamento");
        medicamentoCual = new JTextArea(7, 35);
        medicamentoCual.setEnabled(false);
        planTratamiento = new JCheckBox("Plan de tratamiento");
        planTratamientoCual = new JTextArea(7, 35);
        planTratamientoCual.setEnabled(false);
        
        fecha.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                ctrl.Validaciones.validarFecha(e, fecha.getText(), 10);
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                enterKeyPressed(e, fecha.getText(), padecimientoActual);
            }
        });
        padecimientoActual.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                ctrl.CtrlInterfaz.itemStateChanged(padecimientoCual, e);
            }
        });
        padecimientoCual.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    if (e.isShiftDown()) {
                        ctrl.CtrlInterfaz.cambia(padecimientoActual);
                    } else {
                        e.consume();
                        ctrl.CtrlInterfaz.cambia(planTratamiento);
                    }
                }
            }
        });
        planTratamiento.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                ctrl.CtrlInterfaz.itemStateChanged(planTratamientoCual, e);
            }
        });
        planTratamientoCual.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    if (e.isShiftDown()) {
                        ctrl.CtrlInterfaz.cambia(planTratamiento);                        
                    } else {
                        e.consume();
                        ctrl.CtrlInterfaz.cambia(medicamento);
                    }
                }
            }
        });
        medicamento.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                ctrl.CtrlInterfaz.itemStateChanged(medicamentoCual, e);
            }
        });
        medicamentoCual.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    if (e.isShiftDown()) {
                        ctrl.CtrlInterfaz.cambia(medicamento);                        
                    } else {
                        e.consume();
                        ctrl.CtrlInterfaz.cambia(antecedentes);
                    }
                }
            }
        });
        antecedentes.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                ctrl.CtrlInterfaz.itemStateChanged(antecedentesCual, e);
            }
        });
        antecedentesCual.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    if (e.isShiftDown()) {
                        KeyboardFocusManager.getCurrentKeyboardFocusManager().focusPreviousComponent();
                    } else {
                        e.consume();
                        KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                    }
                }
            }
        });
        JScrollPane scrollUno = new JScrollPane(padecimientoCual);
        JScrollPane scrollDos = new JScrollPane(planTratamientoCual);
        JScrollPane scrollTres = new JScrollPane(medicamentoCual);
        JScrollPane scrollCuatro = new JScrollPane(antecedentesCual);
        this.add( new JLabel("Fecha"), 
                new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.CENTER, new Insets(15, 5, 5, 5), 0, 0));
        this.add( fecha, 
                new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.CENTER, new Insets(15, 5, 5, 5), 0, 0));
        this.add( padecimientoActual, 
                new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.CENTER, new Insets(15, 15, 5, 5), 0, 0));
        this.add(scrollUno, 
                new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.SOUTH, GridBagConstraints.CENTER, new Insets(5, 15, 5, 5), 0, 0));
        this.add(planTratamiento, 
                new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.CENTER, new Insets(15, 15, 5, 5), 0, 0));
        this.add(scrollDos, 
                new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.SOUTH, GridBagConstraints.CENTER, new Insets(5, 15, 5, 15), 0, 0));
        this.add(medicamento, 
                new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.CENTER, new Insets(15, 15, 5, 5), 0, 0));
        this.add(scrollTres, 
                new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 15, 15, 5), 0, 0));
        this.add(antecedentes, 
                new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.CENTER, new Insets(15, 15, 5, 5), 0, 0));
        this.add(scrollCuatro, 
                new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.SOUTH, GridBagConstraints.CENTER, new Insets(5, 15, 15, 15), 0, 0));
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
        SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formato2 = new SimpleDateFormat("dd-MM-yyyy");
        formato1.setLenient(false);
        formato2.setLenient(false);

        try
        {
            dateFecha = formato1.parse(this.getFecha().getText());
        } catch (ParseException ex1)
        {
            try
            {
                dateFecha = formato2.parse(this.getFecha().getText());
            } catch (ParseException ex2)
            {
                JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto");
                ctrl.CtrlInterfaz.selecciona(this.fecha);
                return false;
            }
        }
        return true;
    }
    
    public boolean camposVacios()
    {
        if(this.getFecha().getText().trim().isEmpty())
        {
            ctrl.CtrlInterfaz.selecciona(fecha);
            return true;
        }
        if(this.getPadecimientoActual().isSelected() && this.getPadecimientoCual().getText().trim().isEmpty())
        {
            ctrl.CtrlInterfaz.selecciona(padecimientoCual);
            return true;
        }
        if(this.getPlanTratamiento().isSelected() && this.getPlanTratamientoCual().getText().trim().isEmpty())
        {
            ctrl.CtrlInterfaz.selecciona(planTratamientoCual);
            return true;
        }
        if(this.getMedicamento().isSelected() && this.getMedicamentoCual().getText().trim().isEmpty())
        {
            ctrl.CtrlInterfaz.selecciona(medicamentoCual);
            return true;
        }
        if(this.getAntecedentes().isSelected() && this.getAntecedentesCual().getText().trim().isEmpty())
        {
            ctrl.CtrlInterfaz.selecciona(antecedentesCual);
            return true;
        }
        return false;
    }
    
    public void limpiarFormulario(boolean b)
    {
        ctrl.CtrlInterfaz.limpiarComponentes(fecha, fecha, padecimientoActual, padecimientoCual, antecedentes, antecedentesCual, medicamento, medicamentoCual, planTratamiento, planTratamientoCual);
        habilitarFormulario(b);
    }
    
    public void habilitarFormulario(boolean b)
    {
        fecha.setEditable(b);
        padecimientoActual.setEnabled(b);
        padecimientoCual.setEditable(b);
        antecedentes.setEnabled(b);
        antecedentesCual.setEditable(b);
        medicamento.setEnabled(b);
        medicamentoCual.setEditable(b);
        planTratamiento.setEnabled(b);
        planTratamientoCual.setEditable(b);
    }
}
