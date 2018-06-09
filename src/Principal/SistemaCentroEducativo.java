/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Clases.Asignacion;
import Clases.FabricaAsignaciones;

/**
 *
 * @author Usuario
 */
public class SistemaCentroEducativo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FabricaAsignaciones fabrica = new FabricaAsignaciones();
        Asignacion asignadorEST = fabrica.crearAsignador(FabricaAsignaciones.TIPO_ASIGNACION_ESTUDIANTE);
        asignadorEST.crearAsignacion();
    }
    
}
