/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Mueble;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioCarritoMockRemote;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioCatalogoMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
@Path("/Catalogo")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CatalogoService {
 
    /**
     * Referencia al Ejb del catalogo encargada de realizar las operaciones del mismo.
     */
    @EJB
    private IServicioCatalogoMockLocal catalogoEjb;
   
 
    /**
     * Servicio que ofrece una lista JSON con el catálogo de Muebles de los alpes (Los muebles disponibles para la venta).
     * @return la lista JSON con los muebles de MDLA.
  
     */
    @GET
    @Path("muebles/")
    public List<Mueble> getTodosLosMuebles() {
        return catalogoEjb.darMuebles();
 
    }
    
    //----Ivan Zambrano----
    /**
     * Obtiene un mueble del catálogo por su ID
     * @param id Identificador único del mueble
     * @return Response con el mueble encontrado o un código de estado NOT_FOUND
     * si no se encuentra
     */
    @GET
    @Path("/muebles/{id}")
    public Response obtenerMueblePorId(@PathParam("id") long id) {
        Mueble mueble = catalogoEjb.buscarMueblePorId(id);
        if (mueble != null) {
            return Response.ok(mueble).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Error!! El mueble con el ID " + id + " no se encontró en el catálogo.")
                    .build();
        }
    }
    
    /**
     * Elimina un mueble del catálogo por su ID
     * @param id Identificador único del mueble a eliminar
     * @return Response con el resultado de la operación o un código de estado
     * NOT_FOUND si no se encuentra
     */
    @DELETE
    @Path("/eliminarmueble/{id}")
    public Response eliminarMueblePorId(@PathParam("id") long id) {
        boolean eliminacionExitosa = catalogoEjb.eliminarMuebleBoolean(id);
        try {
            if (eliminacionExitosa) {
                return Response.ok("Eliminación exitosa!!").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Eliminación fallida!! El mueble con el ID " + id + " no se encontró en el catálogo.")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al eliminar el mueble!!").build();
        }
    }
    
    /**
     * Método POST para agregar un nuevo mueble al catálogo de muebles.
     * @param nuevoMueble El nuevo mueble que se va a agregar al catálogo.
     * @return Un objeto Response con el estado 201 (CREATED) si el mueble se agregó correctamente.
     */
    @POST
    @Path("/agregarmueble/")
    public Response agregarMueble(Mueble nuevoMueble) {
        catalogoEjb.agregarMueble(nuevoMueble);
        return Response.status(Response.Status.CREATED)
                .entity("Mueble agregado.")
                .build();
    }
    
    /**
     * Agrega un ejemplar del mueble al catálogo.
     * @param id Identificador único del mueble.
     * @return Response indicando si se agregó el ejemplar correctamente o si no
     * se encontró el mueble con el ID especificado.
     */
    @PUT
    @Path("/muebles/{id}/agregarEjemplar")
    public Response agregarEjemplarMueble(@PathParam("id") long id) {
        Mueble mueble = catalogoEjb.buscarMueblePorId(id);
        if (mueble != null) {
            try {
                catalogoEjb.agregarEjemplarMueble(id);
                return Response.ok("Ejemplar agregado correctamente!!").build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al actualizar el mueble!!").build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Ejemplar no agregado!! El mueble con el ID " + id + " no se encontró en el catálogo.").build();
        }
    }
    
     /**
     * Remover un ejemplar del mueble al catálogo.
     * @param id Identificador único del mueble.
     * @return Response indicando si se removio el ejemplar correctamente o si no
     * se encontró el mueble con el ID especificado.
     */
    @PUT
    @Path("/muebles/{id}/removerEjemplar")
    public Response removerEjemplarMueble(@PathParam("id") long id) {
        Mueble mueble = catalogoEjb.buscarMueblePorId(id);
        if (mueble != null) {
            try {
                catalogoEjb.removerEjemplarMueble(id);
                return Response.ok("Ejemplar removido correctamente!!").build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al actualizar el mueble!!").build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Ejemplar no agregado!! El mueble con el ID " + id + " no se encontró en el catálogo.").build();
        }
    }
    
    /**
     * Aumentar el precio del mueble en el catálogo.
     * @param id Identificador único del mueble.
     * @return Response indicando si se aumento el precio correctamente o si no
     * se encontró el mueble con el ID especificado.
     */
    @PUT
    @Path("/muebles/{id}/aumentarPrecio")
    public Response aumentarPrecioMueble(@PathParam("id") long id) {
        Mueble mueble = catalogoEjb.buscarMueblePorId(id);
        if (mueble != null) {
            try {
                catalogoEjb.aumentarPrecioMueble(id);
                return Response.ok("Precio aumentado en 100 correctamente!!").build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al aumentar el precio del mueble!!").build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Precio no aumentado!! El mueble con el ID " + id + " no se encontró en el catálogo.").build();
        }
    }
    
    /**
     * Disminuir el precio del mueble en el catálogo.
     * @param id Identificador único del mueble.
     * @return Response indicando si se disminuyo el precio correctamente o si no
     * se encontró el mueble con el ID especificado.
     */
    @PUT
    @Path("/muebles/{id}/disminuirPrecio")
    public Response disminuirPrecioMueble(@PathParam("id") long id) {
        Mueble mueble = catalogoEjb.buscarMueblePorId(id);
        if (mueble != null) {
            try {
                catalogoEjb.disminuirPrecioMueble(id);
                return Response.ok("Precio disminuido en 100.0 correctamente!!").build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al disminuir el precio del mueble!!").build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Precio no disminuido!! El mueble con el ID " + id + " no se encontró en el catálogo.").build();
        }
    }
    
}
       