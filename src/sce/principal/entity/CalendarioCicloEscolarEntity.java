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
@Entity
@Table(name = CalendarioCicloEscolarEntity.tableName)
public class CalendarioCicloEscolarEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String tableName = "calendario_ciclo_escolar";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ciclo_escolar_id;
    private Integer mes, dia_inicio, dia_fin;
    private String actividad;

    public Long getId() {
        return id;
    }

    public Long getCiclo_escolar_id() {
        return ciclo_escolar_id;
    }

    public Integer getMes() {
        return mes;
    }

    public Integer getDia_inicio() {
        return dia_inicio;
    }

    public Integer getDia_fin() {
        return dia_fin;
    }

    public String getActividad() {
        return actividad;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCiclo_escolar_id(Long ciclo_escolar_id) {
        this.ciclo_escolar_id = ciclo_escolar_id;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public void setDia_inicio(Integer dia_inicio) {
        this.dia_inicio = dia_inicio;
    }

    public void setDia_fin(Integer dia_fin) {
        this.dia_fin = dia_fin;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
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
        if (!(object instanceof CalendarioCicloEscolarEntity)) {
            return false;
        }
        CalendarioCicloEscolarEntity other = (CalendarioCicloEscolarEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sce.principal.entity.CalendarioCicloEscolarEntity[ id=" + id + " ]";
    }
    
}
