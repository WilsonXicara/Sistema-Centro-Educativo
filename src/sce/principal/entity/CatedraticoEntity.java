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
@Entity(name = CatedraticoEntity.tableName)
@Table(name = CatedraticoEntity.tableName)
public class CatedraticoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "catedratico";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dpi, nombres, apellidos, direccion;
    private Long asignacion_id = 0l;
    private String atributos_adicionales;
    private Boolean anulado = false;
    private String razon_anulacion;

    public Long getId() { return id; }
    public String getDpi() { return dpi; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getDireccion() { return direccion; }
    public Long getAsignacion_id() { return asignacion_id; }
    public String getAtributos_adicionales() { return atributos_adicionales; }
    public Boolean getAnulado() { return anulado; }
    public String getRazon_anulacion() { return razon_anulacion; }
    public void setId(Long id) { this.id = id; }
    public void setDpi(String dpi) { this.dpi = dpi; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setAsignacion_id(Long asignacion_id) { this.asignacion_id = asignacion_id; }
    public void setAtributos_adicionales(String atributos_adicionales) { this.atributos_adicionales = atributos_adicionales; }
    public void setAnulado(Boolean anulado) { this.anulado = anulado; }
    public void setRazon_anulacion(String razon_anulacion) { this.razon_anulacion = razon_anulacion; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatedraticoEntity)) {
            return false;
        }
        CatedraticoEntity other = (CatedraticoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "sce.principal.entity.CatedraticoEntity{" + "id=" + id + ", dpi=" + dpi + ", nombres=" + nombres + ", apellidos=" + apellidos + ", direccion=" + direccion + ", asignacion_id=" + asignacion_id + ", atributos_adicionales=" + atributos_adicionales + ", anulado=" + anulado + ", razon_anulacion=" + razon_anulacion + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof CatedraticoEntity) {
            CatedraticoEntity aux = (CatedraticoEntity)object;
            this.id = aux.id;
            this.dpi = aux.dpi;
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