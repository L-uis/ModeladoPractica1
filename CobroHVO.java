
/**
 * Interface que permite hacer cobros para el servicio HVO Max.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public interface CobroHVO {

  /**
   * Metodo que realiza el cobro al suscriptor.
   * 
   * @return el estado del cobro.
   */
  String cobro(Suscriptor suscriptor);
}
