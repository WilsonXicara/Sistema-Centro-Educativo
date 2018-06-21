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
@Table(name = NotaActividadEntity.tableName)
public class NotaActividadEntity implements Serializable, RegistroEntity {

    private static final long serialVersionUID = 1L;
    public static final String tableName = "nota_actividad";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long actividad_id, nota_distribucion_notas_id;
    private Float obtenido;

    public Long getId() {
        return id;
    }

    public Long getActividad_id() {
        return actividad_id;
    }

    public Long getNota_distribucion_notas_id() {
        return nota_distribucion_notas_id;
    }

    public Float getObtenido() {
        return obtenido;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setActividad_id(Long actividad_id) {
        this.actividad_id = actividad_id;
    }

    public void setNota_distribucion_notas_id(Long nota_distribucion_notas_id) {
        this.nota_distribucion_notas_id = nota_distribucion_notas_id;
    }

    public void setObtenido(Float obtenido) {
        this.obtenido = obtenido;
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
        if (!(object instanceof NotaActividadEntity)) {
            return false;
        }
        NotaActividadEntity other = (NotaActividadEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sce.principal.entity.NotaActividadEntity[ id=" + id + " ]";
    }

    @Override
    public boolean yaExiste() {
        return id != null;
    }
    
}
