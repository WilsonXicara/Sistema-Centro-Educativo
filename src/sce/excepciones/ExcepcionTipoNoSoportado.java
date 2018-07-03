/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.excepciones;

/**
 *
 * @author Usuario
 */
public class ExcepcionTipoNoSoportado extends Exception {
    public ExcepcionTipoNoSoportado(String message) {
        super(message);
    }
    public ExcepcionTipoNoSoportado(String message, Throwable cause) {
        super(message, cause);
    }
}