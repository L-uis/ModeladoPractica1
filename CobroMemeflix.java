
/**
 * Interface que permite hacer cobros para el servicio Memeflix.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public interface CobroMemeflix {

  /**
   * Metodo que realiza el cobro al suscriptor.
   * 
   * @return el estado del cobro.
   */
  String cobro(Cliente cliente);
  
}
