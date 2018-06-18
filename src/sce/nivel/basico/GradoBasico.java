/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.basico;

import sce.principal.ElementoEducativo;
import sce.principal.Grado;

/**
 *
 * @author Usuario
 */
public class GradoBasico implements ElementoEducativo, Grado {
    
    public GradoBasico() {
        System.out.println("GradoBasico->()");
    }

    @Override
    public void consultarCursos() {
        System.out.println("GradoBasico->consultarCursos()");
    }

    @Override
    public void consultarCatedratico() {
        System.out.println("GradoBasico->consultarCatedratico()");
    }

    @Override
    public void consultarEstudiantes() {
        System.out.println("GradoBasico->consultarEstudiantes()");
    }
}
