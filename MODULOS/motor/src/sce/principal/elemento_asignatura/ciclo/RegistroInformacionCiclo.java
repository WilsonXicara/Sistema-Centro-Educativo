/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.ciclo;

/**
 *
 * @author juan_
 */
public class RegistroInformacionCiclo {
    private Long idCiclo;
    private String ciclo_escolar;
    private Boolean listo, cerrado;

    public Long getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Long idCiclo) {
        this.idCiclo = idCiclo;
    }

    public String getCiclo_escolar() {
        return ciclo_escolar;
    }

    public void setCiclo_escolar(String ciclo_escolar) {
        this.ciclo_escolar = ciclo_escolar;
    }

    public Boolean isListo() {
        return listo;
    }

    public void setListo(Boolean listo) {
        this.listo = listo;
    }

    public Boolean isCerrado() {
        return cerrado;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }
   
}
