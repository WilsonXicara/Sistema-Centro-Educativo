/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion;

import sce.excepciones.ExcepcionParametrosIncompletos;
import sce.excepciones.ExcepcionEntityAnulado;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public interface AsignacionCommand {
    public void crearAsignacion() throws ExcepcionParametrosIncompletos, NonexistentEntityException, ExcepcionEntityAnulado;
}
