/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura;

/**
 *
 * @author Usuario
 */
public abstract class AbstractElementoAsignatura {
    protected Long idElementoAsignatura;
    
    public abstract ElementoAsignaturaEntity getElementoAsignaturaEntity();
    
    public void setIdElementoAsignatura(Long idElementoAsignatura) {
        if (this.idElementoAsignatura == null) {
            this.idElementoAsignatura = idElementoAsignatura;
        }
    }
    public Long getIdElementoAsignatura() { return this.idElementoAsignatura; }
}