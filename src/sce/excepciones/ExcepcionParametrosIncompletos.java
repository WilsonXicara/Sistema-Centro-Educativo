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
public class ExcepcionParametrosIncompletos extends Exception {
    public ExcepcionParametrosIncompletos(String message) {
        super(message);
    }
    public ExcepcionParametrosIncompletos(String message, Throwable cause) {
        super(message, cause);
    }
}