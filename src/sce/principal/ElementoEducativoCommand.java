/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal;

/**
 *
 * @author Usuario
 */
public interface ElementoEducativoCommand {
    public void guardarPersona(Persona persona);
    public void guardarElementoAsignatura(ElementoAsignatura elemento);
    public void guardarAsignacion(Asignacion asignacion);
}
