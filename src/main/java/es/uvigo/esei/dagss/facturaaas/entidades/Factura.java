/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;
        
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
    
    @ManyToOne
    private FormaPago formaPago;
    
    private Double importe;
    private Double sumaTotal;
    private String comentarios;
    private String ejercicio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura", fetch = FetchType.LAZY)
    private List<LineaFactura> lineasDeFactura;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura", fetch = FetchType.LAZY)
    private List<Pago> pago;
    
    public Factura(){
        
    }

    public Factura( EstadoFactura estadoFactura,Usuario usuario, Cliente cliente,FormaPago formaPago, Double importe, Double sumaTotal,String comentarios,String ejercicio,Date fecha, List<LineaFactura> lineasDeFactura,List<Pago> pagos) {
        this.estadoFactura = estadoFactura;
        this.usuario = usuario;
        this.cliente = cliente;
        this.formaPago = formaPago;
        this.importe = importe;
        this.sumaTotal = sumaTotal;
        this.comentarios = comentarios;
        this.ejercicio = ejercicio;
        this.fechaEmision = fecha;
        this.lineasDeFactura = lineasDeFactura;
        this.pago = pagos;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public Long getId() {
        return id;
    }

    public EstadoFactura getEstadoFactura() {
        return estadoFactura;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }


    public Double getImporte() {
        return importe;
    }

    public Double getSumaTotal() {
        return sumaTotal;
    }

    public String getComentarios() {
        return comentarios;
    }

    public String getEjercicio() {
        return ejercicio;
    }
    

    public List<LineaFactura> getLineasDeFactura() {
        return lineasDeFactura;
    }

    public List<Pago> getPago() {
        return pago;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setEstadoFactura(EstadoFactura estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public void setSumaTotal(Double sumaTotal) {
        this.sumaTotal = sumaTotal;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
    

    public void setLineasDeFactura(List<LineaFactura> lineasDeFactura) {
        this.lineasDeFactura = lineasDeFactura;
    }

    public void setPago(List<Pago> pago) {
        this.pago = pago;
    }
    
    @Override
    public int hashCode() {
        if (id != null) {
            return Objects.hashCode(this.id);
        } else {
            return hashCodePorContenido();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (id != null) {
            return equalsPorId(obj);
        } else {
            return equalsPorContenido(obj);
        }
    }

    public boolean equalsPorId(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Factura other = (Factura) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public int hashCodePorContenido() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.importe);
        hash = 97 * hash + Objects.hashCode(this.sumaTotal);
        return hash;
    }

    public boolean equalsPorContenido(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Factura other = (Factura) obj;

        if (!Objects.equals(this.importe, other.importe)) {
            return false;
        }
        if (!Objects.equals(this.sumaTotal, other.sumaTotal)) {
            return false;
        }
        return true;
    }
    

 
}
