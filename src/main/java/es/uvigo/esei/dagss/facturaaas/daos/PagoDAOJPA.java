/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.Cliente;
import es.uvigo.esei.dagss.facturaaas.entidades.Factura;
import es.uvigo.esei.dagss.facturaaas.entidades.Pago;
import es.uvigo.esei.dagss.facturaaas.entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Usuario
 */
@Stateless
public class PagoDAOJPA extends GenericoDAOJPA<Pago, Long> implements PagoDAO{

    @Override
    public List<Pago> buscarConPropietario(Usuario propietario) {
        System.out.println("Buscar pago por id de usuario");
        System.out.println("Usuario: " + propietario.getId());
        TypedQuery<Pago> query = em.createQuery("SELECT pago FROM Pago AS pago WHERE pago.factura.usuario.id = :idPropietario", Pago.class);
        query.setParameter("idPropietario", propietario.getId());
        List<Pago> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Pago> buscarPorClienteConPropietario(Usuario propietario, Cliente cliente) {
        System.out.println("Buscar pago por cliente");
        System.out.println("Usuario: " + propietario.getId());
        System.out.println("Cliente: " + cliente.getId());
        TypedQuery<Pago> query = em.createQuery("SELECT pago FROM Pago AS pago WHERE pago.factura.usuario.id = :idPropietario AND pago.factura.cliente.id = :idCliente", Pago.class);
        query.setParameter("idPropietario", propietario.getId());
        query.setParameter("idCliente", cliente.getId());
        List<Pago> resultado = query.getResultList();
        return resultado;
    }
    @Override
    public List<Pago> buscarPorFacturaPago(Usuario propietario, Factura factura) {
        System.out.println("Buscar factura por cliente");
        System.out.println("Usuario: " + propietario.getId());
        TypedQuery<Pago> query = em.createQuery("SELECT pago FROM Pago AS pago WHERE pago.factura.usuario.id = :idPropietario AND pago.factura.id = :idFactura", Pago.class);
        query.setParameter("idPropietario", propietario.getId());
        query.setParameter("idFactura", factura.getId());
        List<Pago> resultado = query.getResultList();
        return resultado;
    }

}
