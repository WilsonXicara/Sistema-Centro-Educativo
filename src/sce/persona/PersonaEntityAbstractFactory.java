/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona;

/**
 *
 * @author Usuario
 */
public interface PersonaEntityAbstractFactory {
    public static final int ESTUDIANTE = 300;
    public static final int CATEDRATICO = 301;
    public PersonaEntity obtenerPersonaEntity(int tipo);
    public PersonaEntity obtenerPersonaEntity(int tipo, Long id);
}
