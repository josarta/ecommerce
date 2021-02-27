/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecommerce.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "sub_categoria")
@NamedQueries({
    @NamedQuery(name = "SubCategoria.findAll", query = "SELECT s FROM SubCategoria s")})
public class SubCategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsub_categoria")
    private Integer idsubCategoria;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "fk_categoriaId", referencedColumnName = "idcategoria")
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria fkcategoriaId;
    @OneToMany(mappedBy = "fksubcategoriaId", fetch = FetchType.EAGER)
    private Collection<Productos> productosCollection;

    public SubCategoria() {
    }

    public SubCategoria(Integer idsubCategoria) {
        this.idsubCategoria = idsubCategoria;
    }

    public Integer getIdsubCategoria() {
        return idsubCategoria;
    }

    public void setIdsubCategoria(Integer idsubCategoria) {
        this.idsubCategoria = idsubCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getFkcategoriaId() {
        return fkcategoriaId;
    }

    public void setFkcategoriaId(Categoria fkcategoriaId) {
        this.fkcategoriaId = fkcategoriaId;
    }

    public Collection<Productos> getProductosCollection() {
        return productosCollection;
    }

    public void setProductosCollection(Collection<Productos> productosCollection) {
        this.productosCollection = productosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsubCategoria != null ? idsubCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubCategoria)) {
            return false;
        }
        SubCategoria other = (SubCategoria) object;
        if ((this.idsubCategoria == null && other.idsubCategoria != null) || (this.idsubCategoria != null && !this.idsubCategoria.equals(other.idsubCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.ecommerce.entity.SubCategoria[ idsubCategoria=" + idsubCategoria + " ]";
    }
    
}
