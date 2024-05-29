
package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import poo.HistorialClinico;

/**
 *
 * @author admin
 */
public class VentanaHistorial extends JDialog
{

    JPanel contenedor;
    JPanel panelNorth;
    JPanel panelWest;
    JPanel panelCenter;
    FormularioMedico form;
    HistorialClinico[] historial;
    private DefaultTableModel model;
    
    public VentanaHistorial(String titulo, HistorialClinico[] historial)
    {
        super(new JFrame(), titulo, true);
        this.setBackground(Color.WHITE);
        this.historial = historial;
        this.setSize(1100, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setIconImage(null);
        this.setResizable(false);
        initComponets();
    }
    
    private void initComponets()
    {
        contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.X_AXIS));
        contenedor.setBorder(new EmptyBorder(20, 20, 20, 20));
        contenedor.setBackground(Color.WHITE);
        initPanelWest();
        initPanelCenter();
        this.add(contenedor);
    }  

    private void initPanelWest()
    {
        panelWest = new JPanel();
        panelWest.setBackground(Color.YELLOW);
        model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        model.addColumn("Fecha");
        Object[] fila;
        for (HistorialClinico historial1 : historial)
        {
            fila = new Object[]{historial1.getFecha()};
            model.addRow(fila);
        }
        
        JTable tabla = new JTable(model);
        
        int fixedWidth = 200; 

        TableColumn column = tabla.getColumnModel().getColumn(0);
        column.setMinWidth(fixedWidth);
        column.setMaxWidth(fixedWidth);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1)
                {
                    int filaSeleccionada = tabla.getSelectedRow();
                    if (filaSeleccionada != -1 && historial[filaSeleccionada] != null)
                    {
                        form.getFecha().setText(""+historial[filaSeleccionada].getFecha());
                        form.getPadecimientoActual().setSelected(!historial[filaSeleccionada].getPadecimientoAct().isBlank());
                        form.getPadecimientoCual().setText(historial[filaSeleccionada].getPadecimientoAct());
                        form.getAntecedentes().setSelected(!historial[filaSeleccionada].getAntecedentesPer().isBlank());
                        form.getAntecedentesCual().setText(historial[filaSeleccionada].getAntecedentesPer());
                        form.getMedicamento().setSelected(!historial[filaSeleccionada].getMedicamento().isBlank());
                        form.getMedicamentoCual().setText(historial[filaSeleccionada].getMedicamento());
                        form.getPlanTratamiento().setSelected(!historial[filaSeleccionada].getPlanTratamiento().isBlank());
                        form.getPlanTratamientoCual().setText(historial[filaSeleccionada].getPlanTratamiento());
                    }else
                    {
                        JOptionPane.showMessageDialog(null, "No se puede mostrar la consulta solicitada", "A ocurrido un problema", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setPreferredSize(new Dimension(fixedWidth, scrollTabla.getPreferredSize().height));
        scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelWest.add(scrollTabla);
        contenedor.add(panelWest);
    }
    
    private void initPanelCenter()
    {
        panelCenter = new JPanel();
        panelCenter.setBackground(Color.WHITE);
        form = new FormularioMedico();
        form.habilitarFormulario(false);
        panelCenter.add(form);
        contenedor.add(panelCenter);
    }
}
