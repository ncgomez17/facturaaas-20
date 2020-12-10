/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "FACTURA")
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private EstadoFactura estadoFactura;
    
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
    
    private String nombre;
    private String nif;
    private double importe;
    private double sumaTotal;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura", fetch = FetchType.EAGER)
    private List<LineaFactura> lineasDeFactura;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "factura", fetch = FetchType.EAGER)
    private Pago pago;
    
    public Factura(){
        
    }

    public Factura(Long id, EstadoFactura estadoFactura, Cliente cliente, String nombre, String nif, double importe, double sumaTotal, List<LineaFactura> lineasDeFactura) {
        this.id = id;
        this.estadoFactura = estadoFactura;
        this.cliente = cliente;
        this.nombre = nombre;
        this.nif = nif;
        this.importe = importe;
        this.sumaTotal = sumaTotal;
        this.lineasDeFactura = lineasDeFactura;
    }

    public Long getId() {
        return id;
    }

    public EstadoFactura getEstadoFactura() {
        return estadoFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNif() {
        return nif;
    }

    public double getImporte() {
        return importe;
    }

    public double getSumaTotal() {
        return sumaTotal;
    }

    public List<LineaFactura> getLineasDeFactura() {
        return lineasDeFactura;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEstadoFactura(EstadoFactura estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public void setSumaTotal(double sumaTotal) {
        this.sumaTotal = sumaTotal;
    }

    public void setLineasDeFactura(List<LineaFactura> lineasDeFactura) {
        this.lineasDeFactura = lineasDeFactura;
    }



    
}
