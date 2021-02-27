/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecommerce.facade;

import edu.ecommerce.entity.SubCategoria;
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
public class SubCategoriaFacade extends AbstractFacade<SubCategoria> implements SubCategoriaFacadeLocal {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubCategoriaFacade() {
        super(SubCategoria.class);
    }

    @Override
    public boolean ingresarSubCategoria(String nombre, int id_categoria) {
        try {
            Query q = em.createNativeQuery("INSERT INTO sub_categoria (nombre, fk_categoriaId) VALUES (?, ?)");
            q.setParameter(1, nombre);
            q.setParameter(2, id_categoria);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<SubCategoria> leerSubCategoria(int id_categoria) {
        try {
            Query q = em.createQuery("SELECT s FROM SubCategoria s WHERE s.fkcategoriaId.idcategoria = :id_categoria");
            q.setParameter("id_categoria", id_categoria);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

;

}
