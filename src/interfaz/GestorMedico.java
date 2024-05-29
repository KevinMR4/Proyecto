
package interfaz;

import java.awt.EventQueue;

/**
 *
 * @author alfredo
 */

public class GestorMedico
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            try
            {
                VentanaPrincipal frame = new VentanaPrincipal();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } catch (Exception e)
            {
            }
        });
    }

}
