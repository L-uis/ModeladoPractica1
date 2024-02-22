
import java.util.LinkedList;

/**
 * Clase que simula la plataforma Memeflix.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 * @version Febrero 2024
 * 
 */
public class Memeflix implements ServicioStreaming{

  private final String NOMBRE_DE_LA_PLATAFORMA = "Memeflix";

  private LinkedList<Suscriptor> suscriptoresActivos;

  private LinkedList<Suscriptor> suscriptoresInactivos;

  private LinkedList<String> tiposDeSuscripcion;

  private LinkedList<String> recomendaciones;

  private int contadorDeRecomendaciones;

  private String recomendacionDelMes;

  private CobroMemeflix cobro;

  /**
   * Constructor de la clase Memeflix, inicializa las listas, anade
   * la Sucripcion de Memeflix para un dispositivo, Sucripcion de Memeflix para dos dispositivos 
   * y Sucripcion de Memeflix para cuatro dispositivos a la lista de tiposDeSuscripvion.
   */
  public Memeflix(){

    suscriptoresActivos = new LinkedList<Suscriptor>();
    
    suscriptoresInactivos = new LinkedList<Suscriptor>();
    
    recomendaciones = new LinkedList<String>();
    
    tiposDeSuscripcion = new LinkedList<>();

    tiposDeSuscripcion.add("Sucripcion de Memeflix para un dispositivo");

    tiposDeSuscripcion.add("Sucripcion de Memeflix para dos dispositivos");

    tiposDeSuscripcion.add("Sucripcion de Memeflix para cuatro dispositivos");

    contadorDeRecomendaciones = 0;

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

      cliente.anadirRegistro(NOMBRE_DE_LA_PLATAFORMA);

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
    
    if (tipoSuscripcion.equals("Sucripcion de Memeflix para un dispositivo")){
      
      cobro = new MemeflixUnDispositivo();

    }if (tipoSuscripcion.equals("Sucripcion de Memeflix para dos dispositivos")) {
      
      cobro = new MemeflixDosDispositivos();

    }if (tipoSuscripcion.equals("Sucripcion de Memeflix para cuatro dispositivos")) {
      
      cobro = new MemeflixCuatroDispositivos();
    }

    return cobro.cobro(cliente);

  }

  @Override
  public String getRecomendacion() {

    if (recomendaciones.size() == 0) {

      return "Actualmente " + NOMBRE_DE_LA_PLATAFORMA + " no tiene recomendaciones";

    }else{

      contadorDeRecomendaciones++;
      
      if (contadorDeRecomendaciones == recomendaciones.size()) {

        contadorDeRecomendaciones = 0;
        
      }

      recomendacionDelMes = recomendaciones.get(contadorDeRecomendaciones);

      return recomendacionDelMes;
    
    }

  }

  @Override
  public void anadirRecomendacion(String recomendacion){

    recomendaciones.add(recomendacion);

  }

  /**
   * Clase auxiliar Suscriptor, esta clase es usada para guardar los datos de antiguedad y
   * el tipo de suscriptor de un cliente
   * 
   */
  public class Suscriptor {
    
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

    private Cliente getCliente(){

      return this.cliente;
      
    }

    public String getTipoDeSuscripcion(){

      return this.tipoSuscripcion;
    
    }

    public int getAntiguedad(){

      return this.antiguedad;

    }

    public void setTipoDeSuscripcion(String cadena){

      this.tipoSuscripcion = cadena;

    }

    public void aumentarAntiguedad(){

      antiguedad++;

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
