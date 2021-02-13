/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecommerce.facade;

import edu.ecommerce.entity.Rol;
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
    
}
