/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.basico;

import java.util.ArrayList;
import sce.principal.Asignacion;
import sce.principal.Curso;
import sce.principal.Grado;

/**
 *
 * @author Usuario
 */
public class AsignacionCursoBasico implements Asignacion {
    private Grado grado;
    private ArrayList<Curso> cursos;
    
    public AsignacionCursoBasico() {
        cursos = new ArrayList<>();
        System.out.println("AsignacionCursoBasico->()");
    }
    
    public void setGrado(Grado grado) {
        this.grado = grado;
        System.out.println("AsignacionCursoBasico->setGrado(grado)");
    }
    public void addCurso(Curso curso) {
        cursos.add(curso);
        System.out.println("AsignacionCursoBasico->addCurso(curso)");
    }

    @Override
    public void crearAsignacion() {
        System.out.println("AsignacionCursoBasico->crearAsignacion()");
    }
    
}
