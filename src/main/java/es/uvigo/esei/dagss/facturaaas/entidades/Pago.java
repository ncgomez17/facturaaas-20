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
    @JoinColumn( name = "FACTURA_ID")
    private Factura factura;
        
    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago;
        
    private Double importe;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public Pago() {
    }

    public Pago(Factura factura, EstadoPago estadoPago,Double importe, Date fecha) {
        this.factura = factura;
        this.estadoPago = estadoPago;
        this.importe = importe;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public Factura getFactura() {
        return factura;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public Double getImporte() {
        return importe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
