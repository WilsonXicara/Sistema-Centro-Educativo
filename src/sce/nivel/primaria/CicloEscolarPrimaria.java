/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.primaria;

import sce.principal.CicloEscolar;
import sce.principal.ElementoEducativo;

/**
 *
 * @author juan_
 */
public class CicloEscolarPrimaria implements CicloEscolar, ElementoEducativo {
    
    private int duracion;

    public CicloEscolarPrimaria() {
        System.out.println("CicloEscolarPrimaria->()");

    }
    
    @Override
    public void abrirCiclo() {
        System.out.println("CicloEscolarPrimaria->abrirCiclo()");
    }

    @Override
    public void cerrarCiclo() {
        System.out.println("CicloEscolarPrimaria->cerrarCiclo()");
    }

    @Override
    public void consultarGrados() {
        System.out.println("CicloEscolarPrimaria->consultarGrados()");
    }

    @Override
    public void consultarCalendario() {
        System.out.println("CicloEscolarPrimaria->consularCalendario()");
    }
    
}
