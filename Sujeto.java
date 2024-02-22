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
   * Metodo que agrega a un cliente a la lista del sujeto.
   * 
   * @param cliente cliente que sera agregado a la lista.
   * @param tipoDeSuscripcion Tipo de suscripcion con la que se registrara al cliente.
   */
  void registrar(Cliente cliente, String tipoDeSuscripcion);

  /**
   * Metodo que remueve a un cliente de la lista del sujeto.
   * 
   * @param cliente cliente que sera removido de la lista.
   */
  void remover(Cliente cliente);

  /**
   * Metodo que notificara a los clientes de los cambios en el sujeto.
   */
  void notificar();

}
