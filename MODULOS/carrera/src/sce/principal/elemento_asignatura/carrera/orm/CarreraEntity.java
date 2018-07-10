package sce.principal.elemento_asignatura.carrera.orm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
@Entity(name = CarreraEntity.tableName)
@NamedQuery(name ="Carrera.buscarPorNombre", query ="SELECT c FROM "+CarreraEntity.tableName +" AS c WHERE c.nombre = :nombreCarrera")
@Table(name = CarreraEntity.tableName)
public class CarreraEntity implements Serializable, ElementoAsignaturaEntity{
    private static final long serialVersionUID = 1L;
    public static final String tableName = "carrera";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre, descripcion;
    private Long pensum_id;

    public CarreraEntity() {
    }

    public CarreraEntity(String nombre, String descripcion, Long pensum_id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.pensum_id = pensum_id;
    }

    
    
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public Long getPensum_id() { return pensum_id; }
    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPensum_id(Long pensum_id) { this.pensum_id = pensum_id; }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarreraEntity)) {
            return false;
        }
        CarreraEntity other = (CarreraEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "sce.principal.entity.CarreraEntity{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", pensum_id=" + pensum_id + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof CarreraEntity) {
            CarreraEntity aux = (CarreraEntity)object;
            this.id = aux.id;
            this.nombre = aux.nombre;
            this.descripcion = aux.descripcion;
            this.pensum_id = aux.pensum_id;
        }
    }
}