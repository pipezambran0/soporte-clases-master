/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ IServicioCatalogoMockLocal.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces;


import co.edu.uniandes.csw.mueblesdelosalpes.dto.Mueble;
import java.util.List;
import javax.ejb.Local;

/**
 * Contrato funcional de los servicios que se le prestan al catálogo
 * @author Juan Sebastián Urrego
 */
@Local
public interface IServicioCatalogoMockLocal
{

    /**
     * Agrega un mueble al sistema
     * @param mueble Nuevo mueble
     */
    public void agregarMueble(Mueble mueble);

    /**
     * Elimina un mueble del sistema
     * @param id Identificador único del mueble a eliminar
     */
    public void eliminarMueble(long id);

    /**
     * Devuelve todos los muebles del sistema
     * @return muebles Lista de muebles
     */
    public List<Mueble> darMuebles();

    /**
     * Remueve un ejemplar del mueble (no el mueble)
     * @param id Identificador único del mueble
     */
    public void removerEjemplarMueble(long id);

    //----Ivan Zambrano----
    /**
     * Devuelve un mueble del catálogo según su ID
     * @param id Identificador único del mueble
     * @return El mueble correspondiente al ID especificado
     */
    public Mueble buscarMueblePorId(long id);

    /**
     * Intenta eliminar un mueble del catálogo según su ID.
     * @param id Identificador único del mueble a eliminar.
     * @return true si la eliminación fue exitosa, false si no se encontró
     * ningún mueble con el ID especificado.
     */
    public boolean eliminarMuebleBoolean(long id);
    
    /**
     * Agregar un ejemplar del mueble (no el mueble)
     * @param id Identificador único del mueble
     */
    public void agregarEjemplarMueble(long id);
    
    /**
     * Aumenta el precio de un mueble en el catálogo según su ID.
     * @param id Identificador único del mueble cuyo precio se va a aumentar.
     */
    public void aumentarPrecioMueble(long id);
    
    /**
     * Disminuye el precio de un mueble en el catálogo según su ID.
     * @param id Identificador único del mueble cuyo precio se va disminuir.
     */
    public void disminuirPrecioMueble(long id);
}
