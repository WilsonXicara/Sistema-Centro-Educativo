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
import sce.principal.RegistroEntity;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = ActividadEntity.tableName)
public class ActividadEntity implements Serializable, RegistroEntity {

    private static final long serialVersionUID = 1L;
    public static final String tableName = "actividad";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long distribucion_nota_id;
    private String grupo_actividad, actividad;
    private Float esperado;

    public Long getId() {
        return id;
    }

    public Long getDistribucion_nota_id() {
        return distribucion_nota_id;
    }

    public String getGrupo_actividad() {
        return grupo_actividad;
    }

    public String getActividad() {
        return actividad;
    }

    public Float getEsperado() {
        return esperado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDistribucion_nota_id(Long distribucion_nota_id) {
        this.distribucion_nota_id = distribucion_nota_id;
    }

    public void setGrupo_actividad(String grupo_actividad) {
        this.grupo_actividad = grupo_actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public void setEsperado(Float esperado) {
        this.esperado = esperado;
    }

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
        return "sce.principal.entity.Actividad[ id=" + id + " ]";
    }

    @Override
    public boolean yaExiste() {
        return id != null;
    }
    
}
