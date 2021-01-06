/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.Cliente;
import es.uvigo.esei.dagss.facturaaas.entidades.Factura;
import es.uvigo.esei.dagss.facturaaas.entidades.LineaFactura;
import es.uvigo.esei.dagss.facturaaas.entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author usuario
 */
@Stateless
public class FacturaDAOJPA extends GenericoDAOJPA<Factura, Long> implements FacturaDAO {

    @Override
    public List<Factura> buscarConPropietario(Usuario propietario) {
        System.out.println("Buscar factura por id de usuario");
        System.out.println("Usuario: " + propietario.getId());
        TypedQuery<Factura> query = em.createQuery("SELECT fac FROM Factura AS fac WHERE fac.usuario.id = :idPropietario", Factura.class);
        query.setParameter("idPropietario", propietario.getId());
        List<Factura> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Factura> buscarPorClienteConPropietario(Usuario propietario, Cliente cliente) {
        System.out.println("Buscar factura por cliente");
        System.out.println("Usuario: " + propietario.getId());
        System.out.println("Cliente: " + cliente.getId());
        TypedQuery<Factura> query = em.createQuery("SELECT fac FROM Factura AS fac WHERE fac.usuario.id = :idPropietario AND fac.cliente.id = :idCliente", Factura.class);
        query.setParameter("idPropietario", propietario.getId());
        query.setParameter("idCliente", cliente.getId());
        List<Factura> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<LineaFactura> buscarPorIdConLineas(Long id) {
        Factura toret = this.buscarPorClave(id);
        System.out.println("Numero de lineas"+toret.getLineasDeFactura().size());
        return toret.getLineasDeFactura();
    }
    @Override
    public LineaFactura crearLineas(LineaFactura l) {
         this.em.persist(l);
         this.em.flush();
         return l;
    }
    @Override
    public LineaFactura actualizarLineas(LineaFactura l) {
         LineaFactura toret = em.merge(l);
         em.flush();
         return toret;
    }
    @Override
    public void borrarLineas(LineaFactura l) {
        em.remove(em.merge(l));
        em.flush();
    }
    


    
    
}