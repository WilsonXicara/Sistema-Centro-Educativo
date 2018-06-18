/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.primaria;

import java.util.ArrayList;
import sce.principal.Asignacion;
import sce.principal.Curso;
import sce.principal.Grado;

/**
 *
 * @author juan_
 */
public class AsignacionCursoPrimaria implements Asignacion {
    
    private Grado grado;
    private ArrayList<Curso> cursos;

    public AsignacionCursoPrimaria() {
        this.cursos = new ArrayList();
         System.out.println("AsignacionCursoPrimaria->()");
    }
    
    public void setGrado(Grado grado){
        this.grado = grado;
         System.out.println("AsignacionCursoPrimaria->setGrado(grado)");
    };
    
    public void addCurso(Curso curso){
        this.cursos.add(curso);
         System.out.println("AsignacionCursoPrimaria->addCurso(curso");
    };
    
    @Override
    public void crearAsignacion() {
    System.out.println("AsignacionCursoPrimaria->crearAsignacion()");
    };
    
}
