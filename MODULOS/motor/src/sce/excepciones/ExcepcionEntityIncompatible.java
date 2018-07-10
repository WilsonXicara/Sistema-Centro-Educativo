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
public class ExcepcionEntityIncompatible extends Exception {
    public ExcepcionEntityIncompatible(String message) {
        super(message);
    }
    public ExcepcionEntityIncompatible(String message, Throwable cause) {
        super(message, cause);
    }
}