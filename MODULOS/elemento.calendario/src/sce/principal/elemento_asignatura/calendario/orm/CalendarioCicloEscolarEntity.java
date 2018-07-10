/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.calendario.orm;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import sce.principal.elemento_asignatura.ElementoAsignaturaEntity;

/**
 *
 * @author Usuario
 */
@Entity(name = CalendarioCicloEscolarEntity.tableName)
@NamedQuery(name="CalendarioCicloEscolar.buscarParametros",query="SELECT p FROM "+CalendarioCicloEscolarEntity.tableName+" AS p WHERE "
        + "p.asignacion_carrera_id = :idAsigCarrera AND p.mes = :mes AND p.dia_inicio = :diainicio AND p.dia_fin = :diafin AND p.actividad = :actividad")
@Table(name = CalendarioCicloEscolarEntity.tableName)
public class CalendarioCicloEscolarEntity implements Serializable, ElementoAsignaturaEntity {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "calendario_ciclo_escolar";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long asignacion_carrera_id;
    private Integer mes, dia_inicio, dia_fin;
    private String actividad;

    
    public CalendarioCicloEscolarEntity(){
        
    }
    
    public CalendarioCicloEscolarEntity(Long asignacion_carrera_id, Integer mes, Integer dia_inicio, Integer dia_fin, String actividad) {
        this.asignacion_carrera_id = asignacion_carrera_id;
        this.mes = mes;
        this.dia_inicio = dia_inicio;
        this.dia_fin = dia_fin;
        this.actividad = actividad;
    }

    public Long getId() { return id; }
    public Long getAsignacion_carrera_id() { return asignacion_carrera_id; }
    public Integer getMes() { return mes; }
    public Integer getDia_inicio() { return dia_inicio; }
    public Integer getDia_fin() { return dia_fin; }
    public String getActividad() { return actividad; }
    public void setId(Long id) { this.id = id; }
    public void setAsignacion_carrera_id(Long asignacion_carrera_id) { this.asignacion_carrera_id = asignacion_carrera_id; }
    public void setMes(Integer mes) { this.mes = mes; }
    public void setDia_inicio(Integer dia_inicio) { this.dia_inicio = dia_inicio; }
    public void setDia_fin(Integer dia_fin) { this.dia_fin = dia_fin; }
    public void setActividad(String actividad) { this.actividad = actividad; }

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
        return "sce.principal.entity.CalendarioCicloEscolarEntity{" + "id=" + id + ", asignacion_carrera_id=" + asignacion_carrera_id + ", mes=" + mes + ", dia_inicio=" + dia_inicio + ", dia_fin=" + dia_fin + ", actividad=" + actividad + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof CalendarioCicloEscolarEntity) {
            CalendarioCicloEscolarEntity aux = (CalendarioCicloEscolarEntity)object;
            this.id = aux.id;
            this.asignacion_carrera_id = aux.asignacion_carrera_id;
            this.mes = aux.mes;
            this.dia_inicio = aux.dia_inicio;
            this.dia_fin = aux.dia_fin;
            this.actividad = aux.actividad;
        }
    }
}