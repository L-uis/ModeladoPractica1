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
   * Metodo que registra a un cliente con el tipo de suscripcion indicado.
   * Si el cliente no estaba suscrito previamente, se le añade a la lista de 
   * suscriptores activos y se le da la bienvenida.
   * Si el cliente estaba suscrito pero inactivo, se le reactiva la suscripcion 
   * y se le da la bienvenida de vuelta.
   * 
   * @param cliente El cliente que se quiere registrar.
   * @param tipoDeSuscripcion El tipo de suscripcion que el cliente elige.
   * @throws IllegalArgumentException Si el tipo de suscripcion es invalido.
   */
  void registrar(Cliente cliente, String tipoDeSuscripcion);

  /** 
   * Metodo que remueve a un cliente de la plataforma.
   * Si el cliente estaba suscrito y activo, se le quita de la lista de suscriptores 
   * activos y se le añade a la lista de suscriptores inactivos.
   * Se le desactiva la suscripcion de prueba si la tenia y se le despide con un mensaje.
   * Si el cliente no estaba suscrito, se le informa con otro mensaje.
   * 
   * @param cliente El cliente que se quiere remover.
   */
  void remover(Cliente cliente);

  /**
   * Metodo que notifica a los clientes sobre los cobros y las recomendaciones.
   * Se recorre la lista de suscriptores activos y se les cobra segun el tipo de 
   * suscripcion que tengan.
   * Si el cobro es rechazado, se cancela la suscripcion del cliente y se le informa 
   * con un mensaje.
   * Si el cobro es exitoso, se le muestra el estado del cobro y se le incrementa la 
   * antigüedad de la suscripcion.
   * Se le muestra al cliente la recomendacion del mes de la plataforma.
   */
  void notificar();
  
}
