/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.basico;

import sce.principal.Asignacion;
import sce.principal.Catedratico;
import sce.principal.CicloEscolar;
import sce.principal.Grado;

/**
 *
 * @author Usuario
 */
public class AsignacionCatedraticoBasico implements Asignacion {
    private CicloEscolar ciclo;
    private Grado grado;
    private Catedratico catedratico;
    
    public AsignacionCatedraticoBasico() {
        System.out.println("AsignacionCatedraticoBasico->()");
    }

    public void setCicloEscolar(CicloEscolar ciclo) {
        this.ciclo = ciclo;
        System.out.println("AsignacionCatedraticoBasico->setCicloEscolar(ciclo)");
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
        System.out.println("AsignacionCatedraticoBasico->setGrado(grado)");
    }

    public void setCatedratico(Catedratico catedratico) {
        this.catedratico = catedratico;
        System.out.println("AsignacionCatedraticoBasico->setCatedratico(catedratico)");
    }

    @Override
    public void crearAsignacion() {
        System.out.println("AsignacionCatedraticoBasico->crearAsignacion()");
    }
    
}
