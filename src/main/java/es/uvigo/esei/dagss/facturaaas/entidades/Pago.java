/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name ="PAGO")
public class Pago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn( name = "CLIENTE_ID")
    private Cliente cliente;

    @OneToOne
    @JoinColumn( name = "FACTURA_ID")
    private Factura factura;
        
    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago;
        
    private String nombre;
    private String nif;
    private Long importe;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public Pago() {
    }

    public Pago(Long id, Cliente cliente, Factura factura, EstadoPago estadoPago, String nombre, String nif, Long importe, Date fecha) {
        this.id = id;
        this.cliente = cliente;
        this.factura = factura;
        this.estadoPago = estadoPago;
        this.nombre = nombre;
        this.nif = nif;
        this.importe = importe;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Factura getFactura() {
        return factura;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNif() {
        return nif;
    }

    public Long getImporte() {
        return importe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setImporte(Long importe) {
        this.importe = importe;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
