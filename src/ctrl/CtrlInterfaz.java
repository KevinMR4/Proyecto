/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.awt.Color;
import java.awt.event.ItemEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 * Clase que facilita operaciones básicas con objetos gráficos, para dar una
 * mejor apariencia en uso
 *
 * @author Lenovo
 */
public class CtrlInterfaz
{

    /**
     * Método para limpiar cajas de texto
     *
     * @param jts Arreglo no definido de cajas de texto o areas de texto
     */
    public static void limpia(Object... jts)
    {
        for (Object x : jts)
        {
            if (x instanceof JTextField)
            {
                ((JTextField) x).setText("");
            }
            if (x instanceof JTextArea)
            {
                ((JTextArea) x).setText("");
            }
        }
    }

    /**
     * Método para habilitar o deshabilitar una cajas de texto, botones,
     * comboBox, menuItems y/o toolBars, area de texto
     *
     * @param b Valor boleano que especifica si habilita o deshabilita un obj
     * dependiendo de su valor de true=habilita o false=deshabilita
     * @param objs Arreglo no definido de objetos como cajas de texto, botones,
     * comboBox, menuItems y/o toolBars, area de texto
     */
    public static void habilita(boolean b, Object... objs)
    {
        for (Object obj : objs)
        {
            if (obj instanceof JTextField)
            {
                ((JTextField) obj).setEnabled(b);
            }
            if (obj instanceof JButton)
            {
                ((JButton) obj).setEnabled(b);
            }
            if (obj instanceof JComboBox)
            {
                ((JComboBox) obj).setEnabled(b);
            }
            if (obj instanceof JMenuItem)
            {
                ((JMenuItem) obj).setEnabled(b);
            }
            if (obj instanceof JToolBar)
            {
                ((JToolBar) obj).setEnabled(b);
            }
            if (obj instanceof JTextArea)
            {
                ((JTextArea) obj).setEnabled(b);
            }
            if (obj instanceof JLabel)
            {
                ((JLabel) obj).setEnabled(b);
            }
            if (obj instanceof JCheckBox)
            {
                ((JCheckBox) obj).setEnabled(b);
            }
            if (obj instanceof JRadioButton)
            {
                ((JRadioButton) obj).setEnabled(b);
            }
        }
    }

    /**
     * Método que permite cambiar el cursor a un objeto de tipo caja, botón,
     * comboBox, menuItem y/o toolBar, area de texto
     *
     * @param obj Objeto de tipo caja, botón, comboBox, menuItem o toolBar, area
     * de texto
     */
    public static void cambia(Object obj)
    {
        habilita(true, obj);
        if (obj instanceof JTextField)
        {
            selecciona((JTextField) obj);
        }
        if (obj instanceof JButton)
        {
            ((JButton) obj).requestFocus();
        }
        if (obj instanceof JComboBox)
        {
            ((JComboBox) obj).requestFocus();
        }
        if (obj instanceof JMenuItem)
        {
            ((JMenuItem) obj).requestFocus();
        }
        if (obj instanceof JToolBar)
        {
            ((JToolBar) obj).requestFocus();
        }
        if (obj instanceof JTextArea)
        {
            ((JTextArea) obj).requestFocus();
        }
        if (obj instanceof JRadioButton)
        {
            ((JRadioButton) obj).requestFocus();
        }
        if (obj instanceof JCheckBox)
        {
            ((JCheckBox) obj).requestFocus();
        }
    }

    /**
     * Método que permite la selección de todo el texto contenido en una caja de
     * texto
     *
     * @param jt Objeto de tipo caja de texto
     */
    public static void selecciona(JTextField jt)
    {
        habilita(true, jt);
        jt.setSelectionStart(0);
        jt.setSelectionEnd(jt.getText().length());
        jt.requestFocus();
    }

    /**
     * Método que permite la selección de todo el texto contenido en una area de
     * texto
     *
     * @param jt Objeto de tipo area de texto
     */
    public static void selecciona(JTextArea jt)
    {
        habilita(true, jt);
        jt.setSelectionStart(0);
        jt.setSelectionEnd(jt.getText().length());
        jt.requestFocus();
    }

    /**
     * Método que permite habilitar o deshabilitar un check Box o bien marcarlo
     * como seleccionado o no seleccionado
     *
     * @param jc objeto de tipo ChecKBox
     * @param tipo 0 si lo quiere es deshabilitarlo o habilitarlo y 1 si lo que
     * quiere es seleccionar o deseleccionarlo
     * @param b verdadero para habilirar o seleccionar y falso para deshabilitar
     * o deseleccionar
     */
    public static void habilitaChB(int tipo, boolean b, JCheckBox... jc)
    {
        for (JCheckBox x : jc)
        {
            if (tipo == 0)
            {
                x.setEnabled(b);
            } else
            {
                x.setSelected(b);
            }
        }
    }
    
    /**
     * Método para cambiar el color de fondo de un panel, frame o dialog
     * este método se debe invocar en la clase de initcompoent
     * @param jf Objecto que puede ser un dialog, frame o panel
     * @param c color elegido para fondo tomado de la clase Color
     */
    public static void cambiaColorFondo(Object jf, Color c)
    {
        if(jf instanceof JFrame)
        {
            ((JFrame)jf).getContentPane().setBackground(c);
        }
        if(jf instanceof JDialog)
        {
            ((JDialog)jf).getContentPane().setBackground(c);
        }
        if(jf instanceof JPanel)
        {
            ((JPanel)jf).setBackground(c);
        }
            
    }
    
    public static void itemStateChanged(Object obj, ItemEvent e)
    {
        if(obj instanceof JTextArea)
        {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                ((JTextArea)obj).setEnabled(true);
                ctrl.CtrlInterfaz.cambia(obj);
            } else {
                ((JTextArea)obj).setEnabled(false);
                ((JTextArea)obj).setText("");
            }
        }
        if (obj instanceof JTextField)
        {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                ((JTextField)obj).setEnabled(true);
                ctrl.CtrlInterfaz.cambia(obj);
            } else {
                ((JTextField)obj).setEnabled(false);
                ((JTextField)obj).setText("");
            }
        }
    }
    
    public static void limpiarComponentes(Object obj, Object... componentes)
    {
        for (Object componente : componentes)
        {
            if(componente == null)
            {
                continue;
            }
            if (componente instanceof JTextField)
            {
                ((JTextField) componente).setText("");
            } else if (componente instanceof JComboBox)
            {
                ((JComboBox) componente).setSelectedIndex(0);
            } else if (componente instanceof JTextArea)
            {
                ((JTextArea) componente).setText("");
            }else if (componente instanceof JRadioButton)
            {
                ((JRadioButton) componente).setSelected(true);
            }else if (componente instanceof ButtonGroup)
            {
                ((ButtonGroup) componente).clearSelection();
            }else if (componente instanceof JCheckBox)
            {
                ((JCheckBox) componente).setSelected(false);
            }
        }
        if(obj != null)
        {
            cambia(obj);
        }
    }
}