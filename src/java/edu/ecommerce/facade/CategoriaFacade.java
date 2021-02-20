/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecommerce.facade;

import edu.ecommerce.entity.Categoria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> implements CategoriaFacadeLocal {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }

    @Override
    public Categoria buscarPorId(int categoriaId) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query u = em.createQuery("SELECT u FROM Categoria u WHERE u.idcategoria = :categoriaId");
            u.setParameter("categoriaId", categoriaId);
            return (Categoria) u.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
