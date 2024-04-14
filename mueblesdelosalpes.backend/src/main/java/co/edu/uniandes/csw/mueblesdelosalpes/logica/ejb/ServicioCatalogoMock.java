/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ ServicioCatalogoMock.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package co.edu.uniandes.csw.mueblesdelosalpes.logica.ejb;

import co.edu.uniandes.csw.mueblesdelosalpes.persistencia.mock.ServicioPersistenciaMock;
import co.edu.uniandes.csw.mueblesdelosalpes.dto.Mueble;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.OperacionInvalidaException;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioCatalogoMockLocal;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioCatalogoMockRemote;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioPersistenciaMockLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 * Implementacion de los servicios del catálogo de muebles que se le prestan al sistema.
 * @author Juan Sebastián Urrego
 */
@Stateless
public class ServicioCatalogoMock implements IServicioCatalogoMockRemote,IServicioCatalogoMockLocal
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    private IServicioPersistenciaMockLocal persistencia;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor sin argumentos de la clase
     */
    public ServicioCatalogoMock()
    {
        persistencia=new ServicioPersistenciaMock();
        //Inicializa el arreglo de los muebles
  
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Agrega un mueble al sistema
     * @param mueble Nuevo mueble
     */
 
    public void agregarMueble(Mueble mueble)
    {
        try
        {
            persistencia.create(mueble);
        }
        catch (OperacionInvalidaException ex)
        {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    /**
     * Se elimina un mueble del sistema dado su identificador único
     * @param id Identificador único del mueble
     */
    @Override
    public void eliminarMueble(long id)
    {
        Mueble m=(Mueble) persistencia.findById(Mueble.class, id);
        try
        {
            persistencia.delete(m);
        }
        catch (OperacionInvalidaException ex)
        {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Remueve un ejemplar del mueble (no el mueble)
     * @param id Identificador único del mueble
     */
    @Override
    public void removerEjemplarMueble(long id)
    {
        ArrayList<Mueble>muebles=(ArrayList<Mueble>) persistencia.findAll(Mueble.class);
        Mueble mueble;
        for (int i = 0; i < muebles.size(); i++)
        {
            mueble = muebles.get(i);
            if(mueble.getReferencia()==id)
            {
                int cantidad=mueble.getCantidad();
                mueble.setCantidad(cantidad-1);
                persistencia.update(mueble);
                break;
            }
        }
    }

    /**
     * Devuelve los muebles del sistema
     * @return muebles Arreglo con todos los muebles del sistema
     */
 
    public List<Mueble> darMuebles()
    {
        return persistencia.findAll(Mueble.class);
    }

    //----Ivan Zambrano----
    /**
     * Busca un mueble en el sistema dado su identificador único
     *
     * @param id Identificador único del mueble
     * @return El mueble encontrado, o null si no se encuentra
     */
    public Mueble buscarMueblePorId(long id) {
        return (Mueble) persistencia.findById(Mueble.class, id);
    }
    
    /**
     * Se elimina un mueble del sistema dado su identificador único
     * @param id Identificador único del mueble
     * @return true si la eliminación fue exitosa, false si no.
     */
    @Override
    public boolean eliminarMuebleBoolean(long id) {
        Mueble m=(Mueble) persistencia.findById(Mueble.class, id);
        if(m != null){
            try {
                persistencia.delete(m);
            } catch (OperacionInvalidaException ex) {
                Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Agregar un ejemplar del mueble (no el mueble)
     * @param id Identificador único del mueble
     */
    @Override
    public void agregarEjemplarMueble(long id)
    {
        ArrayList<Mueble>muebles=(ArrayList<Mueble>) persistencia.findAll(Mueble.class);
        Mueble mueble;
        for (int i = 0; i < muebles.size(); i++)
        {
            mueble = muebles.get(i);
            if(mueble.getReferencia()==id)
            {
                int cantidad=mueble.getCantidad();
                mueble.setCantidad(cantidad+1);
                persistencia.update(mueble);
                break;
            }
        }
    }
    
    /**
     * Aumenta el precio del mueble
     * @param id Identificador único del mueble
     */
    @Override
    public void aumentarPrecioMueble(long id)
    {
        ArrayList<Mueble>muebles=(ArrayList<Mueble>) persistencia.findAll(Mueble.class);
        Mueble mueble;
        for (int i = 0; i < muebles.size(); i++)
        {
            mueble = muebles.get(i);
            if(mueble.getReferencia()==id)
            {
                double precio = mueble.getPrecio();
                double precioNuevo = precio+100;
                mueble.setPrecio(precioNuevo);
                persistencia.update(mueble);
                break;
            }
        }
    }
    
     /**
     * Disminuye el precio del mueble
     * @param id Identificador único del mueble
     */
    @Override
    public void disminuirPrecioMueble(long id)
    {
        ArrayList<Mueble>muebles=(ArrayList<Mueble>) persistencia.findAll(Mueble.class);
        Mueble mueble;
        for (int i = 0; i < muebles.size(); i++)
        {
            mueble = muebles.get(i);
            if(mueble.getReferencia()==id)
            {
                double precio = mueble.getPrecio();
                double precioNuevo = precio-100;
                mueble.setPrecio(precioNuevo);
                persistencia.update(mueble);
                break;
            }
        }
    }
}
