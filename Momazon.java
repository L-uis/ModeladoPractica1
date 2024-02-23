
import java.util.LinkedList;
import java.util.Random;

/**
 * Clase que simula la plataforma Momazon Prime Video.
 * Implementa la interface ServicioStreaming para definir los métodos registrar, 
 * remover y notificar.
 * Usa la interface CobroMomazon para realizar los cobros a los clientes según el 
 * tipo de suscripción.
 * Usa la clase Suscriptor para almacenar la información de clientes y su suscripcion.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 */
public class Momazon implements ServicioStreaming{

  private final String NOMBRE_DE_LA_PLATAFORMA = "Momazon Prime Video";

  private LinkedList<Suscriptor> suscriptoresActivos;

  private LinkedList<Suscriptor> suscriptoresInactivos;

  private LinkedList<String> tiposDeSuscripcion;

  private LinkedList<String> recomendaciones;

  private String recomendacionDelMes;

  private CobroMomazon cobro;

  /**
   * Constructor de la clase Momazon, inicializa las listas, anade
   * la Suscripcion normal de Momazon Prime Video y Suscripcion premium de Momazon Prime Vide
   * a la lista de tiposDeSuscripcion.
   */
  public Momazon(){
    
    suscriptoresActivos = new LinkedList<Suscriptor>();
    
    suscriptoresInactivos = new LinkedList<Suscriptor>();
    
    recomendaciones = new LinkedList<String>();
    
    tiposDeSuscripcion = new LinkedList<>();

    tiposDeSuscripcion.add("Suscripcion normal de Momazon Prime Video");

    tiposDeSuscripcion.add("Suscripcion premium de Momazon Prime Video");

  }

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

  @Override
  public void remover(Cliente cliente){

    Suscriptor buscaSuscriptor = new Suscriptor(cliente);

    if (suscriptoresActivos.contains(buscaSuscriptor)) {
    
      int indiceDelSuscriptor = suscriptoresActivos.indexOf(buscaSuscriptor);

      Suscriptor suscriptor = suscriptoresActivos.get(indiceDelSuscriptor); 

      suscriptoresActivos.remove(indiceDelSuscriptor);

      suscriptoresInactivos.add(suscriptor);

      String mensajeDespedida = cliente.getNombre() + " lamentamos que dejes " + NOMBRE_DE_LA_PLATAFORMA;

      cliente.anadirRegistro(mensajeDespedida);
    
    } else {

      System.out.println("El cliente " + cliente.getNombre() + " no esta suscrito a " + NOMBRE_DE_LA_PLATAFORMA);

    }

  }

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

  @Override
  public String cobro(Cliente cliente) {

    Suscriptor buscaSuscriptor = new Suscriptor(cliente);

    int indiceDelSuscriptor = suscriptoresActivos.indexOf(buscaSuscriptor);

    Suscriptor suscriptor = suscriptoresActivos.get(indiceDelSuscriptor); 
    
    String tipoSuscripcion = suscriptor.getTipoDeSuscripcion();
    
    if (tipoSuscripcion.equals("Sucripcion premium de Momazon Prime Video")){
      
      cobro = new MomazonNormal();

    }if (tipoSuscripcion.equals("Sucripcion normal de Momazon Prime Video")) {
      
      cobro = new MomazonPremium();

    }

    return cobro.cobro(cliente);

  }

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

  @Override
  public void anadirRecomendacion(String recomendacion){

    recomendaciones.add(recomendacion);

  }

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
   * el tipo de suscriptor de un cliente.
   */
  public class Suscriptor{

    private int antiguedad;
    
    private String tipoSuscripcion;

    private Cliente cliente;

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
     * Metodo que cambia el tipo de suscripcion del suscriptor por otro diferente.
     * 
     * @param cadena El nuevo tipo de suscripcion del suscriptor.
     */
    public void setTipoDeSuscripcion(String cadena){

      this.tipoSuscripcion = cadena;

    }

    /**
     * Metodo que aumenta la antiguedad del suscriptor en un mes.
     */
    public void aumentarAntiguedad(){
      
      antiguedad++;

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