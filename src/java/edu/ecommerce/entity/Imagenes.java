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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "imagenes")
@NamedQueries({
    @NamedQuery(name = "Imagenes.findAll", query = "SELECT i FROM Imagenes i")})
public class Imagenes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idimagenes")
    private Integer idimagenes;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 45)
    @Column(name = "ruta")
    private String ruta;
    @JoinTable(name = "productos_has_imagenes", joinColumns = {
        @JoinColumn(name = "imagenes_idimagenes", referencedColumnName = "idimagenes")}, inverseJoinColumns = {
        @JoinColumn(name = "productos_idProductos", referencedColumnName = "idProductos")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Productos> productosCollection;

    public Imagenes() {
    }

    public Imagenes(Integer idimagenes) {
        this.idimagenes = idimagenes;
    }

    public Integer getIdimagenes() {
        return idimagenes;
    }

    public void setIdimagenes(Integer idimagenes) {
        this.idimagenes = idimagenes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
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
        hash += (idimagenes != null ? idimagenes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagenes)) {
            return false;
        }
        Imagenes other = (Imagenes) object;
        if ((this.idimagenes == null && other.idimagenes != null) || (this.idimagenes != null && !this.idimagenes.equals(other.idimagenes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.ecommerce.entity.Imagenes[ idimagenes=" + idimagenes + " ]";
    }
    
}
