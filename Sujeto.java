/**
 * Interface que implementa el patron observer.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public interface Sujeto {

  /**
   * Metodo que agrega a un suscriptor a la lista del sujeto.
   * 
   * @param s suscriptor que sera agregado a la lista.
   * @param tipoDeSuscripcion 
   */
  void registrar(Cliente cliente, String tipoDeSuscripcion);

  /**
   * Metodo que remueve a un suscriptor de la lista del sujeto.
   * 
   * @param s suscriptor que sera removido de la lista.
   */
  void remover(Cliente cliente);

  /**
   * Metodo que notificara a los suscriptores de los cambios en el sujeto.
   */
  void notificar();
  
}
