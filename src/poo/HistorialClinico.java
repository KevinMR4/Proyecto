/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author zoebe
 */
public class HistorialClinico implements Serializable
{
    private String padecimientoAct;
    private String antecedentesPer;
    private String medicamento;
    private String planTratamiento;
    private Date fecha;

    public HistorialClinico()
    {
    }

    public HistorialClinico(String padecimientoAct, String antecedentesPer, String medicamento, String planTratamiento, Date fecha)
    {
        this.padecimientoAct = padecimientoAct;
        this.antecedentesPer = antecedentesPer;
        this.medicamento = medicamento;
        this.planTratamiento = planTratamiento;
        this.fecha = fecha;
    }

    /**
     * @return the padecimientoAct
     */
    public String getPadecimientoAct()
    {
        return padecimientoAct;
    }

    /**
     * @param padecimientoAct the padecimientoAct to set
     */
    public void setPadecimientoAct(String padecimientoAct)
    {
        this.padecimientoAct = padecimientoAct;
    }

    /**
     * @return the antecedentesPer
     */
    public String getAntecedentesPer()
    {
        return antecedentesPer;
    }

    /**
     * @param antecedentesPer the antecedentesPer to set
     */
    public void setAntecedentesPer(String antecedentesPer)
    {
        this.antecedentesPer = antecedentesPer;
    }

    /**
     * @return the medicamento
     */
    public String getMedicamento()
    {
        return medicamento;
    }

    /**
     * @param medicamento the medicamento to set
     */
    public void setMedicamento(String medicamento)
    {
        this.medicamento = medicamento;
    }

    /**
     * @return the planTratamiento
     */
    public String getPlanTratamiento()
    {
        return planTratamiento;
    }

    /**
     * @param planTratamiento the planTratamiento to set
     */
    public void setPlanTratamiento(String planTratamiento)
    {
        this.planTratamiento = planTratamiento;
    }

    /**
     * @return the fecha
     */
    public Date getFecha()
    {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }
    
    
}
