/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecommerce.facade;

import edu.ecommerce.entity.SubCategoria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface SubCategoriaFacadeLocal {

    void create(SubCategoria subCategoria);

    void edit(SubCategoria subCategoria);

    void remove(SubCategoria subCategoria);

    SubCategoria find(Object id);

    List<SubCategoria> findAll();

    List<SubCategoria> findRange(int[] range);

    int count();

    public boolean ingresarSubCategoria(String nombre, int id_categoria);

    public List<SubCategoria> leerSubCategoria(int id_categoria);
    
}
