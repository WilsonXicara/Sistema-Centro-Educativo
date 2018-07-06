/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase con patrón SINGLETON para manejar la conexión a la Base de datos y las transacciones.
 * @author Usuario
 */
public class GestorConexion {
    public static final int NUEVO_ENTITY_MANAGER = -1;
    private static String NOMBRE_PERSISTENCE_UNIT=null;
    private static EntityManagerFactory emf;
    private static ArrayList<EntityManager> listaEntityManagers;
    private static ArrayList<Integer> listaHashCodeEntityManagers, listaHashCodeInvocadores;
    private static ArrayList<Boolean> listaRollbackConfirmado, listaCommitConfirmado;
    
    public static void definirNombrePersistenceUnit(String nombrePersistenceUnit) {
        if (NOMBRE_PERSISTENCE_UNIT == null) {
            NOMBRE_PERSISTENCE_UNIT = nombrePersistenceUnit;
        }
    }
    public static EntityManagerFactory crearEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(NOMBRE_PERSISTENCE_UNIT);
            if (emf == null) {
                NOMBRE_PERSISTENCE_UNIT = null; // Puede que el nombre sea el problema
                return emf;
            }
            listaEntityManagers = new ArrayList<>();
            listaHashCodeEntityManagers = new ArrayList<>();
            listaHashCodeInvocadores = new ArrayList<>();
            listaRollbackConfirmado = new ArrayList<>();
            listaCommitConfirmado = new ArrayList<>();
        }
        return emf;
    }
    private static EntityManager solicitarEntityManager() {
        EntityManager em = crearEntityManagerFactory().createEntityManager();
        listaEntityManagers.add(em);
        listaHashCodeEntityManagers.add(em.hashCode());
        listaHashCodeInvocadores.add(-1);
        listaRollbackConfirmado.add(false);
        listaCommitConfirmado.add(false);
        return em;
    }
    /**
     * 
     * @param hashCodeEntityManager el hashCode del EntityManager que se desea obtener;
     * {@class}GestorConexion.NUEVO_ENTITY_MANAGER si se quiere obtener uno nuevo
     * @return 
     */
    public static EntityManager solicitarEntityManager(int hashCodeEntityManager) {
        if (hashCodeEntityManager == NUEVO_ENTITY_MANAGER) {
            return solicitarEntityManager();
        }
        int index = listaHashCodeEntityManagers.indexOf(hashCodeEntityManager);
        if (index == -1) {
            return null;
        }
        EntityManager em = listaEntityManagers.get(index);
        return em;
    }
    public static boolean transaccionBEGIN(int hashCodeEntityManager, int hashCodeInvocador) {
        int index = listaHashCodeEntityManagers.indexOf(hashCodeEntityManager);
        if (index == -1) {
            return false;
        }
        // Si se encuentra, se verifica si el EntityManager ya tiene una transacción abierta
        EntityManager em = listaEntityManagers.get(index);
        if (em.getTransaction().isActive()) {
            return false;
        }
        em.getTransaction().begin();    // Inicio de la transacción
        listaHashCodeInvocadores.set(index, hashCodeInvocador);
        return true;
    }
    public static boolean transaccionROLLBACK(int hashCodeEntityManager, int hashCodeInvocador) {
        int index = listaHashCodeEntityManagers.indexOf(hashCodeEntityManager);
        if (index == -1) {
            return false;
        }
        // Si se encuentra, se verifica que el EntityManager tenga una transacción abierta
        EntityManager em = listaEntityManagers.get(index);
        if (!em.getTransaction().isActive()) {
            return false;
        }
        if (!listaRollbackConfirmado.get(index)) {
            listaRollbackConfirmado.set(index, true);   // Se agrega la petición de Rollback
        }
        if (listaHashCodeInvocadores.get(index) == hashCodeInvocador) {
            em.getTransaction().rollback();
        }
        return true;
    }
    public static boolean transaccionCOMMIT(int hashCodeEntityManager, int hashCodeInvocador) {
        int index = listaHashCodeEntityManagers.indexOf(hashCodeEntityManager);
        if (index == -1) {
            return false;
        }
        // Si se encuentra, se verifica que el EntityManager tenga una transacción abierta
        EntityManager em = listaEntityManagers.get(index);
        if (!em.getTransaction().isActive()) {
            return false;
        }
        if (!listaCommitConfirmado.get(index) && !listaRollbackConfirmado.get(index)) {
            listaCommitConfirmado.set(index, true); // Se agrega la petición de Commit, siempre que no se haya solicidato un Rollback
        }
        if (listaHashCodeInvocadores.get(index) == hashCodeInvocador) {
            em.getTransaction().commit();
        }
        return true;
    }
    public static boolean eliminarEntityManager(int hashCodeEntityManager, int hashCodeInvocador) {
        int index = listaHashCodeEntityManagers.indexOf(hashCodeEntityManager);
        if (index == -1) {
            return false;
        }
        // Si se encuentra, tiene una acción pendiente (commit o rollback) y es llamado por el Invocador,
        // se ejecuta la acción pendiente
        if (listaHashCodeInvocadores.get(index) == hashCodeInvocador) {
            EntityManager em = listaEntityManagers.get(index);
            if (listaRollbackConfirmado.get(index) && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            } else if (listaCommitConfirmado.get(index) && em.getTransaction().isActive()) {
                em.getTransaction().commit();
            }
            em.close();
            listaEntityManagers.remove(index);
            listaHashCodeEntityManagers.remove(index);
            listaHashCodeInvocadores.remove(index);
            listaRollbackConfirmado.remove(index);
            listaCommitConfirmado.remove(index);
            return true;
        }
        return false;
    }
}