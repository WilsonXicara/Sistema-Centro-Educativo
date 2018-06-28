/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity(name = ActividadEntity.tableName)
@Table(name = ActividadEntity.tableName)
public class ActividadEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "actividad";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long distribucion_notas_id;
    private String grupo_actividad, actividad;
    private Float esperado;

    public Long getId() { return id; }
    public Long getDistribucion_notas_id() { return distribucion_notas_id; }
    public String getGrupo_actividad() { return grupo_actividad; }
    public String getActividad() { return actividad; }
    public Float getEsperado() { return esperado; }
    public void setId(Long id) { this.id = id; }
    public void setDistribucion_notas_id(Long distribucion_notas_id) { this.distribucion_notas_id = distribucion_notas_id; }
    public void setGrupo_actividad(String grupo_actividad) { this.grupo_actividad = grupo_actividad; }
    public void setActividad(String actividad) { this.actividad = actividad; }
    public void setEsperado(Float esperado) { this.esperado = esperado; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadEntity)) {
            return false;
        }
        ActividadEntity other = (ActividadEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "sce.principal.ActividadEntity{" + "id=" + id + ", distribucion_notas_id=" + distribucion_notas_id + ", grupo_actividad=" + grupo_actividad + ", actividad=" + actividad + ", esperado=" + esperado + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof ActividadEntity) {
            ActividadEntity aux = (ActividadEntity)object;
            this.id = aux.id;
            this.distribucion_notas_id = aux.distribucion_notas_id;
            this.grupo_actividad = aux.grupo_actividad;
            this.actividad = aux.actividad;
            this.esperado = aux.esperado;
        }
    }
}