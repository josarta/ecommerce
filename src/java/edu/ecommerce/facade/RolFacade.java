/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecommerce.facade;

import edu.ecommerce.entity.Rol;
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
public class RolFacade extends AbstractFacade<Rol> implements RolFacadeLocal {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }
    
    @Override
    public boolean ingresarRol(int id_usuario , int id_rol){
        try {
            Query q = em.createNativeQuery("INSERT INTO `ecommerce`.`usuario_has_rol` (`usuario_idUsuario`, `rol_idrol`) VALUES (?, ?)");
            q.setParameter(1, id_usuario);
            q.setParameter(2, id_rol);
            q.executeUpdate();
            return  true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    
    @Override
    public List<Rol> noRoles(int id_usuario){
        try {
            String consulta = "SELECT * FROM rol WHERE rol.idrol NOT IN ( "
                    + "SELECT rol.idrol FROM rol right join usuario_has_rol on rol.idrol = usuario_has_rol.rol_idrol"
                    + " where usuario_has_rol.usuario_idUsuario = " + id_usuario + ")";
            Query q = em.createNativeQuery(consulta, Rol.class);
            return  q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
     
    @Override
    public boolean removerRol(int id_usuario , int id_rol){
        try {
            Query q = em.createNativeQuery("DELETE FROM usuario_has_rol WHERE (usuario_idUsuario = ? ) and (rol_idrol = ?)");
            q.setParameter(1, id_usuario);
            q.setParameter(2, id_rol);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
     
    
    
    
    
    
    
    
    
    
}
