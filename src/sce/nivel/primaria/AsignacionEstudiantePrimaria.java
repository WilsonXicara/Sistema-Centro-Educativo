/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.primaria;

import java.util.ArrayList;
import sce.principal.Asignacion;
import sce.principal.CicloEscolar;
import sce.principal.Estudiante;
import sce.principal.Grado;

/**
 *
 * @author juan_
 */
public class AsignacionEstudiantePrimaria implements Asignacion {
    
    private CicloEscolar cicloEscolar;
    private Grado grado;
    private ArrayList<Estudiante> estudiantes;

    public AsignacionEstudiantePrimaria() {
        this.estudiantes = new ArrayList();
        System.out.println("AsignacionEstudiantePrimaria->()");
    }
    
    public void setCicloEscolar(CicloEscolar ciclo){
        this.cicloEscolar = ciclo;
        System.out.println("AsignacionEstudiantePrimaria-setCicloEscolar>(ciclo)");

    };
    
    public void setGrado(Grado grado){
        this.grado = grado;
        System.out.println("AsignacionEstudiantePrimaria->setGrado(grado)");
    };
    
    public void addEstudiante(Estudiante estudiante){
        this.estudiantes.add(estudiante);
        System.out.println("AsignacionEstudiantePrimaria->addEstudiante(estudiante)");
    };
    
    @Override
    public void crearAsignacion(){
        System.out.println("AsignacionEstudiantePrimaria->crearAsignacion()");
    };
    
}
