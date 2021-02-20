/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecommerce.facade;

import edu.ecommerce.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
     public Usuario validarUsuario(String correoIn , String claveIn){
         
         try {
             Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correoIn AND u.clave = :claveIn");
             q.setParameter("correoIn", correoIn);
             q.setParameter("claveIn", claveIn);
             Usuario usulog = (Usuario) q.getSingleResult();
             return  usulog;
         } catch (Exception e) {
           return  null;
         }
     }
     
    @Override
    public boolean cambioEstado(int id_usuario , int estado){
        try {
            Query q = em.createNativeQuery("UPDATE `ecommerce`.`usuario` SET `estado` = ? WHERE (`idUsuario` = ?)");
            q.setParameter(1, estado);
            q.setParameter(2, id_usuario);
            q.executeUpdate();
            return  true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    @Override
    public List<Usuario> listarTodos(){
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query u = em.createQuery("SELECT u FROM Usuario u");
            return u.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    
    
    @Override
    public Usuario buscarPorId(int usuarioId){
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query u = em.createQuery("SELECT u FROM Usuario u WHERE u.idUsuario = :usuarioId");
            u.setParameter("usuarioId", usuarioId);
            return (Usuario) u.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
     @Override
    public Usuario buscarPorCorreo(String correo){
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query u = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo");
            u.setParameter("correo", correo);
            return (Usuario) u.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    
}
