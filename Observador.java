

/**
 * Interface que implementa el patron observer.
 * Define el metodo actualizar, que recibe un servicio de streaming 
 * y actualiza el estado de los observadores.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 * @version Febrero 2024
 */
public interface Observador {

  /**
   * Metodo que actualiza el estado de los observadores.
   * Se invoca cuando el servicio de streaming notifica a los observadores 
   * sobre algun cambio.
   * @param servicio El servicio de streaming que notifica a los observadores.
   */
  void actualizar(ServicioStreaming servicio);
  
}