/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.basico;

import java.util.ArrayList;
import sce.principal.Asignacion;
import sce.principal.CicloEscolar;
import sce.principal.Estudiante;
import sce.principal.Grado;

/**
 *
 * @author Usuario
 */
public class AsignacionEstudianteBasico implements Asignacion {
    private CicloEscolar ciclo;
    private Grado grado;
    private ArrayList<Estudiante> estudiantes;
    
    public AsignacionEstudianteBasico() {
        estudiantes = new ArrayList<>();
        System.out.println("AsignacionEstudianteBasico->()");
    }
    
    public void setCicloEscolar(CicloEscolar ciclo) {
        this.ciclo = ciclo;
        System.out.println("AsignacionEstudianteBasico->setCicloEscolar(ciclo)");
    }
    public void setGrado(Grado grado) {
        this.grado = grado;
        System.out.println("AsignacionEstudianteBasico->setGrado(grado)");
    }
    public void addEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
        System.out.println("AsignacionEstudianteBasico->addEstudiante(estudiante)");
    }

    @Override
    public void crearAsignacion() {
        System.out.println("AsignacionEstudianteBasico->crearAsignacion()");
    }
    
}
