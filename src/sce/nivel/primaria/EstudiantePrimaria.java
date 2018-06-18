/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.primaria;

import sce.principal.Estudiante;
import sce.principal.Persona;

/**
 *
 * @author juan_
 */
public class EstudiantePrimaria implements Estudiante, Persona {

    public EstudiantePrimaria() {
        System.out.println("EstudiantePrimaria");
    }

    @Override
    public void consultarCicloEscolar() {
       System.out.println("EstudiantePrimaria->consultarCicloEscolar");
    }

    @Override
    public void consultarGrado() {
       System.out.println("EstudiantePrimaria->consultarGrado");
    }

    @Override
    public void consultarCursos() {
       System.out.println("EstudiantePrimaria->consultarCursos");
    }
    
}
