import java.io.IOException;

/**
 * Metodo que implementa el patron observer.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public interface Observador {

  /**
   * Metodo que actualiza el estado de los observadores.
   * 
   * @throws IOException 
   */
  void actualizar(ServicioStreaming s) throws IOException;
}