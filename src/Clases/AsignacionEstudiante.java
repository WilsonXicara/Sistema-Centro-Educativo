/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class AsignacionEstudiante implements Asignacion {
    // Variables propias del objeto
    private CicloEscolar cicloEscolar;
    private Estudiante estudiante;
    private Grado grado;
    private ArrayList<Curso> cursos;
    
    public AsignacionEstudiante() {}
    public AsignacionEstudiante(CicloEscolar cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }

    @Override
    public boolean crearAsignacion() {
        // CÓDIGO PARA CREAR LA ASIGNACIÓN DE UN ESTUDIANTE
        return true;
    }

    @Override
    public boolean setAtributo(int tipoAtributo, Object valor) {
        // CÓDIGO PARA INSERTAR UN ATRIBUTO A UN ESTUDIANTE
        return true;
    }
    
}
