
import java.util.LinkedList;
import java.util.Random;

/**
 * Clase que simula la plataforma HVO Max.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 * @version Febrero 2024
 * 
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

  @Override
  public void registrar(Cliente cliente, String tipoDeSuscripcion) {
    
    if (!tiposDeSuscripcion.contains(tipoDeSuscripcion)) {

      throw new IllegalArgumentException("Tipo de suscripcion invalido");

    }

    Suscriptor suscriptor = new Suscriptor(cliente, tipoDeSuscripcion);

    if (!suscriptoresActivos.contains(suscriptor) && !suscriptoresInactivos.contains(suscriptor) ) {
      
      suscriptoresActivos.add(suscriptor);

      cliente.anadirRegistro(cliente.getNombre() + " bienvenido a " + NOMBRE_DE_LA_PLATAFORMA);

    } else if (suscriptoresInactivos.contains(suscriptor)) {

      suscriptoresActivos.add(suscriptor);

      suscriptoresInactivos.remove(suscriptor);

      cliente.anadirRegistro("Bienvenido de vuelta " + cliente.getNombre());

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

      suscriptor.desactivarSuscripcionDePrueba();

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
   * el tipo de suscriptor de un cliente y si el cliente tiene o no una suscripcion de 
   * prueba.
   * 
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
     * Metodo que devuelve el cliente que tiene una suscripcion a HVO Max
     * 
     * @return El cliente que tiene la suscripcion.
     */
    private Cliente getCliente(){

      return this.cliente;
      
    }

    /**
     * Metodo que devuelve el tipo de suscripcion del suscriptor.
     *  
     * @return El tipo de suscripcion.
     */
    public String getTipoDeSuscripcion(){

      return this.tipoSuscripcion;
    
    }

    /**
     * Metodo que devuelve la antiguedad del suscriptor.
     * 
     * @return La antiguedad del suscriptor.
     */
    public int getAntiguedad(){

      return this.antiguedad;

    }

    /**
     * Metodo que devuelve el estado de la suscripcion de pueba.
     * 
     * @return true si el suscriptor aun tiene la suscripcion de prueba, false si el suscriptor ya no tiene la suscripcion de prueba.
     */
    public boolean getSuscripcionDePrueba(){

      return suscripcionDePrueba;

    }

    /**
     * Metodo que cambia el tipo de suscripcion del suscriptor.
     * 
     * @param cadena El nuevo tipo de suscripcion.
     */
    public void setTipoDeSuscripcion(String cadena){

      this.tipoSuscripcion = cadena;

    }

    /**
     * Metodo que aumenta la antiguedad del suscriptor en 1,
     * si la antiguedad llega a tres se desactiva la suscripcion
     * de prueba.
     */
    public void aumentarAntiguedad(){

      antiguedad++;

      if (antiguedad == 3) {

        desactivarSuscripcionDePrueba();
        
      }

    }

    /**
     * Metodo que desactiva la suscripcion de prueba cambiando la
     * variable suscripcionDePrueba a false.
     */
    public void desactivarSuscripcionDePrueba(){

      suscripcionDePrueba = false;

    }

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
