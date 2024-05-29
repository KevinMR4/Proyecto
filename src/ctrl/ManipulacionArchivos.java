package ctrl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import poo.Alumnos;
import poo.Datos;
import poo.Personal;

/**
 *
 * @author alfredo
 */
public class ManipulacionArchivos
{
    private static final String RUTA= "src/BD/";
    //private static Path path = Paths.get(ruta);
    //private static boolean existe = Files.exists(path);
    
    public static void guardar(JFrame jf, Object obj, String s)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(RUTA + s);
            ObjectOutputStream arch = new ObjectOutputStream(fos);
            arch.writeObject(obj);
            arch.close();
        } catch (FileNotFoundException ex)
        {
            Mensajes.error(jf, "No se encontro el archivo");
        } catch(Exception ex)
        {
            Mensajes.error(jf, "Error..." + ex.toString());
        }
    }
    
    public static boolean guardar(JFrame jf, Object[]obj, String s)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(RUTA + s);
            ObjectOutputStream arch = new ObjectOutputStream(fos);
            arch.writeObject(obj);
            arch.close();
            return true;
        } catch (FileNotFoundException ex)
        {
           // Mensajes.error(jf,"No se encontro el archivo");
        } catch(Exception ex)
        {
           // Mensajes.error(jf,"Error..." + ex.toString());
        }
        return false;
    }
    
    public static Object carga(JFrame jf, String s)
    {
        Object obj = null;
        try
        {
            FileInputStream fis = new FileInputStream(RUTA + s);
            ObjectInputStream arch = new ObjectInputStream(fis);
            obj = arch.readObject();
            arch.close();
        } catch (FileNotFoundException ex)
        {
            //Mensajes.error(jf, "No se econtro el archivo");
        } catch(Exception ex)
        {
            //Mensajes.error(jf,"Error..." + ex.toString());
        }
        return obj;
    }
    
//    public static Object[] cargaArch(JPanel pn, String s)
//    {
//        Object[] obj = null;
//        try
//        {
//            FileInputStream fis = new FileInputStream(RUTA + s);
//            ObjectInputStream arch = new ObjectInputStream(fis);
//            obj = (Object[]) arch.readObject();
//            arch.close();
//        } catch (FileNotFoundException ex)
//        {
//            JOptionPane.showMessageDialog(pn,"No se econtro el archivo", "Error de 2 conexion", JOptionPane.ERROR_MESSAGE);
//        } catch(Exception ex)
//        {
//            JOptionPane.showMessageDialog(pn,"Error...", "Error..." + ex.toString(), JOptionPane.ERROR_MESSAGE);
//        }
//        return obj;
//    }
    
    /**
     * Carga silenciosa
     * @param s
     * @return 
     */
    public static Object[] cargaArch(String s)
    {
        Object[] obj = null;
        try
        {
            FileInputStream fis = new FileInputStream(RUTA + s);
            ObjectInputStream arch = new ObjectInputStream(fis);
            obj = (Object[]) arch.readObject();
            arch.close();
        } catch (FileNotFoundException ex)
        {
        } catch(Exception ex)
        {
        }
        return obj;
    }
    
    public static Object[][] cargaArch(String s, boolean t)
    {
        Object[][] obj = null;
        try
        {
            FileInputStream fis = new FileInputStream(RUTA + s);
            ObjectInputStream arch = new ObjectInputStream(fis);
            obj = (Object[][]) arch.readObject();
            arch.close();
        } catch (FileNotFoundException ex)
        {
        } catch(Exception ex)
        {
        }
        return obj;
    }
    
    public static Object[] cargaArch(JPanel pn, String s)
    {
        Object[] obj = null;
        try
        {
            FileInputStream fis = new FileInputStream(RUTA + s);
            ObjectInputStream arch = new ObjectInputStream(fis);
            obj = (Object[])arch.readObject();
            arch.close();
        } catch (FileNotFoundException ex)
        {
//            if (tipoError)
//            {
//                JOptionPane.showMessageDialog(pn, "Si es la primera vez que realiza un registro\neste mensaje es normal.\nSi no es a si consulte al administrador ", "No se econtro el archivo", JOptionPane.WARNING_MESSAGE);
//            }else
//            {
                JOptionPane.showMessageDialog(pn, "No se han econtrado registros", "Registros Vacios", JOptionPane.WARNING_MESSAGE);
            ///}
        } catch(Exception ex)
        {
            JOptionPane.showMessageDialog(pn, "Error..." + ex.toString(), "Error de conexion...", JOptionPane.ERROR_MESSAGE);
        }
        return obj;
    }
    
    public static boolean guardarReg(JPanel pn, Object[] obj, String s)
    {        
        try
        {
            FileOutputStream fos = new FileOutputStream(RUTA + s);
            ObjectOutputStream arch = new ObjectOutputStream(fos);
            arch.writeObject(obj);
            arch.close();
        } catch (FileNotFoundException ex)
        {
            JOptionPane.showMessageDialog(pn,"No se econtro el archivo", "Error de conexion", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch(Exception ex)
        {
            JOptionPane.showMessageDialog(pn, "Error..." + ex.toString(), "Error...",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public static boolean guardarReg(Object[][] obj, String s)
    {        
        try
        {
            FileOutputStream fos = new FileOutputStream(RUTA + s);
            ObjectOutputStream arch = new ObjectOutputStream(fos);
            arch.writeObject(obj);
            arch.close();
        } catch (FileNotFoundException ex)
        {
            //JOptionPane.showMessageDialog(null,"No se econtro el archivo", "Error de conexion", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch(Exception ex)
        {
            ///JOptionPane.showMessageDialog(null, "Error..." + ex.toString(), "Error...",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
