/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo;

/**
 *
 * @author zoebe
 */
public class Alumnos extends Datos
{
    private int viveCon;
    private int carrera;

    public Alumnos() 
    {
        
    }

    public Alumnos(int viveCon, int carrera, String cve, String nom, String primerAp, String segundoAp, char sexo, boolean desnutriccion, boolean sobrepeso, boolean alergias, boolean obecidad, boolean diabetes, String otras)
    {
        super(cve, nom, primerAp, segundoAp, sexo, desnutriccion, sobrepeso, alergias, obecidad, diabetes, otras);
        this.viveCon = viveCon;
        this.carrera = carrera;
    }

    

    /**
     * @return the viveCon
     */
    public int getViveCon()
    {
        return viveCon;
    }

    /**
     * @param viveCon the viveCon to set
     */
    public void setViveCon(int viveCon) 
    {
        this.viveCon = viveCon;
    }

    /**
     * @return the carrera
     */
    public int getCarrera()
    {
        return carrera;
    }

    /**
     * @param carrera the carrera to set
     */
    public void setCarrera(int carrera)
    {
        this.carrera = carrera;
    }
    
   
    

    
    
}
