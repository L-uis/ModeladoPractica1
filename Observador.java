
import java.io.IOException;

/**
 * Interface que implementa el patron observer.
 * Define el método actualizar, que recibe un servicio de streaming 
 * y actualiza el estado de los observadores.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 */
public interface Observador {

  /**
   * Metodo que actualiza el estado de los observadores.
   * Se invoca cuando el servicio de streaming notifica a los observadores 
   * sobre algún cambio.
   * @param s El servicio de streaming que notifica a los observadores.
   * @throws IOException Si ocurre algún error de entrada o salida.
   */
  void actualizar(ServicioStreaming s) throws IOException;
  
}