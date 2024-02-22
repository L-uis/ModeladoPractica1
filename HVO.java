
import java.util.LinkedList;
import java.util.Random;

/**
 * Clase que simula la plataforma HVO Max.
 * Implementa la interface ServicioStreaming para definir los métodos registrar, 
 * remover y notificar.
 * Usa la interface CobroHVO para realizar los cobros a los clientes según el 
 * tipo de suscripción.
 * Usa la clase Suscriptor para almacenar la información de clientes y su suscripcion.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 */
public class HVO implements ServicioStreaming{

  private final String NOMBRE_DE_LA_PLATAFORMA = "HVO Max";
  
  private LinkedList<Suscriptor> suscriptoresActivos;

  private LinkedList<Suscriptor> suscriptoresInactivos;

  private LinkedList<String> tiposDeSuscripcion;

  private LinkedList<String> recomendaciones;

  private String recomendacionDelMes;

  private CobroHVO cobro;

  /**
   * Constructor de la clase HVO, inicializa las listas, anade
   * la Suscripcion de prueba de HVO Max y Suscripcion normal de HVO Max
   * a la lista de tiposDeSuscripvion.
   */
  public HVO(){

    suscriptoresActivos = new LinkedList<Suscriptor>();
    
    suscriptoresInactivos = new LinkedList<Suscriptor>();
    
    recomendaciones = new LinkedList<String>();
    
    tiposDeSuscripcion = new LinkedList<>();

    tiposDeSuscripcion.add("Suscripcion de prueba de HVO Max");

    tiposDeSuscripcion.add("Suscripcion normal de HVO Max");

  }

  /**
   * Metodo que registra a un cliente con el tipo de suscripción indicado.
   * Si el cliente no estaba suscrito previamente, se le añade a la lista de 
   * suscriptores activos y se le da la bienvenida.
   * Si el cliente estaba suscrito pero inactivo, se le reactiva la suscripción 
   * y se le da la bienvenida de vuelta.
   * 
   * @param cliente El cliente que se quiere registrar.
   * @param tipoDeSuscripcion El tipo de suscripción que el cliente elige.
   * @throws IllegalArgumentException Si el tipo de suscripción es inválido.
   */
  @Override
  public void registrar(Cliente cliente, String tipoDeSuscripcion) {
    
    if (!tiposDeSuscripcion.contains(tipoDeSuscripcion)) {

      throw new IllegalArgumentException("Tipo de suscripcion invalido");

    }

    Suscriptor suscriptor = new Suscriptor(cliente, tipoDeSuscripcion);

    if (!suscriptoresActivos.contains(suscriptor) && !suscriptoresInactivos.contains(suscriptor) ) {
      
      suscriptoresActivos.add(suscriptor);

      Cliente clienteActual = suscriptor.getCliente();

      clienteActual.anadirRegistro(clienteActual.getNombre() + " bienvenido a " + NOMBRE_DE_LA_PLATAFORMA);

    } else if (suscriptoresInactivos.contains(suscriptor)) {

      suscriptoresActivos.add(suscriptor);

      suscriptoresInactivos.remove(suscriptor);

      Cliente clienteActual = suscriptor.getCliente();

      clienteActual.anadirRegistro("Bienvenido de vuelta " + clienteActual.getNombre());
    }

  }
  
  /** 
   * Metodo que remueve a un cliente de la plataforma.
   * Si el cliente estaba suscrito y activo, se le quita de la lista de suscriptores 
   * activos y se le añade a la lista de suscriptores inactivos.
   * Se le desactiva la suscripción de prueba si la tenía y se le despide con un mensaje.
   * Si el cliente no estaba suscrito, se le informa con otro mensaje.
   * 
   * @param cliente El cliente que se quiere remover.
   */
  @Override
  public void remover(Cliente cliente){

    Suscriptor buscaSuscriptor = new Suscriptor(cliente);

    if (suscriptoresActivos.contains(buscaSuscriptor)) {
    
      int indiceDelSuscriptor = suscriptoresActivos.indexOf(buscaSuscriptor);

      Suscriptor suscriptor = suscriptoresActivos.get(indiceDelSuscriptor); 

      suscriptoresActivos.remove(indiceDelSuscriptor);

      suscriptoresInactivos.add(suscriptor);

      suscriptor.desactivarSuscripcionDePrueba();

      String mensajeDespedida = cliente.getNombre() + " lamentamos que dejes " + NOMBRE_DE_LA_PLATAFORMA;

      cliente.anadirRegistro(mensajeDespedida);
    
    } else {

      System.out.println("El cliente " + cliente.getNombre() + " no esta suscrito a " + NOMBRE_DE_LA_PLATAFORMA);

    }
  }

  /**
   * Metodo que notifica a los clientes sobre los cobros y las recomendaciones.
   * Se recorre la lista de suscriptores activos y se les cobra según el tipo de 
   * suscripción que tengan.
   * Si el cobro es rechazado, se cancela la suscripción del cliente y se le informa 
   * con un mensaje.
   * Si el cobro es exitoso, se le muestra el estado del cobro y se le incrementa la 
   * antigüedad de la suscripción.
   * Se le muestra al cliente la recomendación del mes de la plataforma.
   */
  @Override
  public void notificar(){

    for (Suscriptor suscriptor : suscriptoresActivos) {

      Cliente cliente = suscriptor.getCliente();    

      cliente.anadirRegistro("\n" + NOMBRE_DE_LA_PLATAFORMA);

      String estadoDelCobro = this.cobro(cliente);

      String rechazado = "El pago a sido rechazado, se cancelara la suscripcion del servicio";

      if (estadoDelCobro.equals(rechazado)) {

        cliente.anadirRegistro(rechazado);
      
        suscriptor.setTipoDeSuscripcion("Inactivo");

        this.remover(cliente);

        break;

      } else {

        cliente.anadirRegistro(estadoDelCobro);

      }

      suscriptor.aumentarAntiguedad();

      String mensajeDeAntiguedad = cliente.getNombre() + " llevas suscrito a " + NOMBRE_DE_LA_PLATAFORMA + " " + suscriptor.getAntiguedad() + " meses";

      cliente.anadirRegistro(mensajeDeAntiguedad);

      String recomendacion = this.getRecomendacion();

      cliente.anadirRegistro(recomendacion);

    }

  }

  /**
   * Metodo que devuelve el valor del cobro al cliente según el tipo de suscripción.
   * Usa la interface CobroHVO para delegar la responsabilidad del cobro a una clase 
   * que la implemente.
   * 
   * @param cliente El cliente al que se le cobra el servicio.
   * @return El estado del cobro, si fue exitoso o rechazado.
   */
  @Override
  public String cobro(Cliente cliente){

    Suscriptor buscaSuscriptor = new Suscriptor(cliente);

    int indiceDelSuscriptor = suscriptoresActivos.indexOf(buscaSuscriptor);

    Suscriptor suscriptor = suscriptoresActivos.get(indiceDelSuscriptor); 
    
    String tipoSuscripcion = suscriptor.getTipoDeSuscripcion();

    if (tipoSuscripcion.equals("Suscripcion de prueba de HVO Max") && !suscriptor.getSuscripcionDePrueba()) {
      
      suscriptor.setTipoDeSuscripcion("Suscripcion normal de HVO Max");

      tipoSuscripcion = suscriptor.getTipoDeSuscripcion();

      String actualizacion = "Tu periodo de prueva a terminado, tu nueva suscripcion es: Suscripcion normal de HVO Max";

      cliente.anadirRegistro(actualizacion);

    }
    
    if (tipoSuscripcion.equals("Suscripcion normal de HVO Max")){
      
      cobro = new HVONormal();

    }if (tipoSuscripcion.equals("Suscripcion de prueba de HVO Max")) {
      
      cobro = new HVOPrueba();

    }

    return cobro.cobro(cliente);
  
  }

  /**
   * Metodo que devuelve la recomendación del mes de la plataforma.
   * Usa la lista de recomendaciones para elegir una al azar y la asigna a la variable 
   * recomendacionDelMes.
   * 
   * @return La recomendación del mes en forma de cadena.
   */
  @Override
  public String getRecomendacion() {
    
    if (recomendaciones.size() == 0) {

      return "Actualmente " + NOMBRE_DE_LA_PLATAFORMA + " no tiene recomendaciones";

    }else{

      Random random = new Random();

      int numeroAleatorio = random.nextInt(recomendaciones.size()); 

      recomendacionDelMes = NOMBRE_DE_LA_PLATAFORMA + " te recomienda: " +recomendaciones.get(numeroAleatorio);

      return recomendacionDelMes;

    }

  }

  /**
   * Metodo que anade una recomendacion a la lista de recomendaciones de la plataforma.
   * 
   * @param recomendacion La recomendacion que se quiere anadir.
   */
  @Override
  public void anadirRecomendacion(String recomendacion){

    recomendaciones.add(recomendacion);

  }

  /**
   * Metodo que cambia el tipo de suscripcion de un cliente a otro diferente.
   * Si el tipo de suscripcion es valido, se actualiza el tipo de suscripcion del cliente 
   * y se le informa con un mensaje.
   * Si el tipo de suscripcion es invalido, se lanza una excepcion.
   * 
   * @param cliente El cliente que quiere cambiar su tipo de suscripcion.
   * @param tipoDeSuscripcion El nuevo tipo de suscripcion que el cliente elige.
   * @throws IllegalArgumentException Si el tipo de suscripcion es invalido.
   */
  @Override
  public void cambiarSuscripcion(Cliente cliente, String tipoDeSuscripcion) {

    if (!tiposDeSuscripcion.contains(tipoDeSuscripcion)) {

      throw new IllegalArgumentException("Tipo de suscripcion invalido");

    }

    Suscriptor buscaSuscriptor = new Suscriptor(cliente);

    int indiceDelSuscriptor = suscriptoresActivos.indexOf(buscaSuscriptor);

    Suscriptor suscriptor = suscriptoresActivos.get(indiceDelSuscriptor);

    String antiguoTipoDeSusCripcion = suscriptor.getTipoDeSuscripcion();

    suscriptor.setTipoDeSuscripcion(tipoDeSuscripcion);

    String registro = "Se a cambiado tu tipo de suscripcion de " + antiguoTipoDeSusCripcion + " a " + tipoDeSuscripcion;

    cliente.anadirRegistro(registro);
 
  }

  /**
   * Clase auxiliar Suscriptor, esta clase es usada para guardar los datos de antiguedad,
   * el tipo de suscriptor de un cliente y si el cliente tiene o no una suscripcion de 
   * prueba.
   */
  public class Suscriptor {
    
    private int antiguedad;
    
    private String tipoSuscripcion;

    private Cliente cliente;

    private boolean suscripcionDePrueba;

    /**
     * Contructor de la clase Suscriptor que se usa para buscar un suscriptor que tenga
     * al mismo cliente.
     * 
     * @param cliente El cliente que sera buscado.
     */
    public Suscriptor(Cliente cliente){

      this.cliente = cliente;

    }

    /**
     * Constructor de la clase suscriptor que se usa para guardar el tipo de suscripcion
     * de un cliente.
     * 
     * @param cliente Cliente que contrata el servicio de Streaming.
     * @param tipoSuscripcion Tipo de suscripcion del cliente.
     */
    public Suscriptor(Cliente cliente, String tipoSuscripcion){
      
      this.cliente = cliente;

      this.tipoSuscripcion = tipoSuscripcion;

      this.antiguedad = 0;

      this.suscripcionDePrueba = true;
    }

    /**
     * Metodo que devuelve el cliente asociado al suscriptor.
     * 
     * @return El cliente del suscriptor.
     */
    private Cliente getCliente(){

      return this.cliente;
      
    }

    /**
     * Metodo que devuelve el tipo de suscripcion del suscriptor.
     * 
     * @return El tipo de suscripcion del suscriptor.
     */
    public String getTipoDeSuscripcion(){

      return this.tipoSuscripcion;
    
    }

    /**
     * Metodo que devuelve la antiguedad del suscriptor en meses.
     * 
     * @return La antiguedad del suscriptor.
     */
    public int getAntiguedad(){

      return this.antiguedad;

    }

    /**
     * Metodo que devuelve si el suscriptor tiene o no una suscripcion de prueba.
     * 
     * @return true si el suscriptor tiene una suscripcion de prueba, false en caso contrario.
     */
    public boolean getSuscripcionDePrueba(){

      return suscripcionDePrueba;

    }

    /**
     * Metodo que cambia el tipo de suscripcion del suscriptor por otro diferente.
     * 
     * @param cadena El nuevo tipo de suscripcion del suscriptor.
     */
    public void setTipoDeSuscripcion(String cadena){

      this.tipoSuscripcion = cadena;

    }

    /**
     * Metodo que aumenta la antiguedad del suscriptor en un mes.
     * Si la antiguedad llega a 3 meses, se desactiva la suscripcion de prueba.
     */
    public void aumentarAntiguedad(){

      antiguedad++;

      if (antiguedad == 3) {
        desactivarSuscripcionDePrueba();
      }

    }

    /**
     * Metodo que desactiva la suscripcion de prueba del suscriptor.
     * Asigna el valor false al atributo suscripcionDePrueba.
     */
    public void desactivarSuscripcionDePrueba(){

      suscripcionDePrueba = false;

    }

    /**
     * Metodo que compara si dos objetos son iguales.
     * Dos objetos son iguales si son instancias de la clase Suscriptor y tienen 
     * el mismo cliente.
     * 
     * @param obj El objeto con el que se quiere comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj){
      if (obj instanceof Suscriptor) {
        
        Suscriptor suscriptor = (Suscriptor) obj;

        if (this.cliente.equals(suscriptor.getCliente())) {

          return true;
  
        }else{
  
          return false;
  
        }
      } else {

        return false;
        
      }

    }

  }
  
}
