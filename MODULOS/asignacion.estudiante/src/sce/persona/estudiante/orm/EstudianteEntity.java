/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.estudiante.orm;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import sce.persona.PersonaEntity;

/**
 *
 * @author Usuario
 */
@Entity(name = EstudianteEntity.tableName)
@NamedQueries({
    @NamedQuery(name="Estudiante.buscarPorCui", query="SELECT e FROM "+EstudianteEntity.tableName+" AS e WHERE e.cui = :cuiEstudiante"),
    @NamedQuery(name="Estudiante.buscarPorAsignacionId", query="SELECT e FROM "+EstudianteEntity.tableName+" AS e WHERE e.asignacion_id = :idAsignacion"),
    @NamedQuery(name="Estudiante.buscarAnulados", query="SELECT e FROM "+EstudianteEntity.tableName+" AS e WHERE e.anulado = 1"),
    @NamedQuery(name="Estudiante.buscarNoAsignados", query="SELECT e FROM "+EstudianteEntity.tableName+" AS e WHERE e.asignacion_id = 0"),
    /*@NamedQuery(
            name="Estudiante.buscarPorActividad",
            query="SELECT e, ae FROM asignacion_estudiante_cursos AS aec "
                    + "INNER JOIN asignacion_estudiante AS ae ON aec.asignacion_estudiante_id = ae.id "
                    + "INNER JOIN estudiante AS e ON ae.estudiante_id = e.id "
                    + "WHERE aec.asignacion_curso_id = :idAsignacionCurso")*/
})
@Table(name = EstudianteEntity.tableName)
public class EstudianteEntity implements Serializable, PersonaEntity {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "estudiante";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cui, nombres, apellidos, direccion;
    private Long asignacion_id=0l;
    private String atributos_adicionales = "{}";
    private Boolean anulado = false;
    private String razon_anulacion;

    public Long getId() { return id; }
    public String getCui() { return cui; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getDireccion() { return direccion; }
    public Long getAsignacion_id() { return asignacion_id; }
    public String getAtributos_adicionales() { return atributos_adicionales; }
    public Boolean getAnulado() { return anulado; }
    public String getRazon_anulacion() { return razon_anulacion; }
    public String getAtributoUnico() { return "cui"; }
    public String[] getAtributos() { return new String[]{"cui","nombres","apellidos","direccion"}; }
    public String getValorAtributo(String atributo) {
        switch(atributo) {
            case "cui":         return cui;
            case "nombres":     return nombres;
            case "apellidos":   return apellidos;
            case "direccion":   return direccion;
        }
        return null;
    }
    public void setId(Long id) { this.id = id; }
    public boolean setCui(String cui) {
        if (this.id == null) {
            this.cui = cui;
            return true;
        }
        return false;
    }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setAsignacion_id(Long asignacion_id) { this.asignacion_id = asignacion_id; }
    public void setAtributos_adicionales(String atributos_adicionales) { this.atributos_adicionales = atributos_adicionales; }
    public void setAnulado(Boolean anulado) { this.anulado = anulado; }
    public void setRazon_anulacion(String razon_anulacion) { this.razon_anulacion = razon_anulacion; }
    public boolean setValorAtributo(String atributo, String valor) {
        switch(atributo) {
            case "cui":     return setCui(valor);
            case "nombres":     setNombres(valor);      break;
            case "apellidos":   setApellidos(valor);    break;
            case "direccion":   setDireccion(valor);    break;
            default:        return false;
        }
        return true;
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
        if (!(object instanceof EstudianteEntity)) {
            return false;
        }
        EstudianteEntity other = (EstudianteEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "sce.principal.entity.EstudianteEntity{" + "id=" + id + ", cui=" + cui + ", nombres=" + nombres + ", apellidos=" + apellidos + ", direccion=" + direccion + ", asignacion_id=" + asignacion_id + ", atributos_adicionales=" + atributos_adicionales + ", anulado=" + anulado + ", razon_anulacion=" + razon_anulacion + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof EstudianteEntity) {
            EstudianteEntity aux = (EstudianteEntity)object;
            this.id = aux.id;
            this.cui = aux.cui;
            this.nombres = aux.nombres;
            this.apellidos = aux.apellidos;
            this.direccion = aux.direccion;
            this.asignacion_id = aux.asignacion_id;
            this.atributos_adicionales = aux.atributos_adicionales;
            this.anulado = aux.anulado;
            this.razon_anulacion = aux.razon_anulacion;
        }
    }
}