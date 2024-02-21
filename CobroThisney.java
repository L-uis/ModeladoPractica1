
/**
 * Interface que permite hacer cobros para el servicio Thisney+.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public interface CobroThisney {

  /**
   * Metodo que realiza el cobro al suscriptor.
   * 
   * @return el estado del cobro.
   */
  String cobro(Cliente cliente);
}
