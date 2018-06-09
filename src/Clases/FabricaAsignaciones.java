/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Usuario
 */
public class FabricaAsignaciones implements Asignaciones {
    public static final int TIPO_ASIGNACION_ESTUDIANTE = 0;
    public static final int TIPO_ASIGNACION_CATEDRATICO = 1;

    @Override
    public Asignacion crearAsignador(int tipoAsignacion) {
        switch(tipoAsignacion) {
            case TIPO_ASIGNACION_ESTUDIANTE:
                return new AsignacionEstudiante();
            case TIPO_ASIGNACION_CATEDRATICO:
                return new AsignacionCatedratico();
        }
        return null;
    }
}
