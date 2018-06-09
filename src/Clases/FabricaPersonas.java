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
public class FabricaPersonas implements Personas {
    private static final int TIPO_PERSONA_ESTUDIANTE = 0;
    private static final int TIPO_PERSONA_CATEDRTICO = 1;
    private static final int TIPO_PERSONA_PARTICULAR = 2;

    @Override
    public Persona crearPersona(int tipoPersona) {
        switch(tipoPersona) {
            case TIPO_PERSONA_ESTUDIANTE:
                return new PersonaEstudiante();
            case TIPO_PERSONA_CATEDRTICO:
                return new PersonaCatedratico();
            case TIPO_PERSONA_PARTICULAR:
                return new PersonaParticular();
        }
        return null;
    }
    
}
