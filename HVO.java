
import java.util.LinkedList;

public class HVO implements ServicioStreaming{

  private final String NOMBRE_DE_LA_PLATAFORMA = "HVO Max";
  
  private LinkedList<Suscriptor> suscriptoresActivos;

  private LinkedList<Suscriptor> suscriptoresInactivos;

  private LinkedList<String> tiposDeSuscripcion;

  private LinkedList<String> recomendaciones;

  private int contadorDeRecomendaciones;

  private String recomendacionDelMes;

  private CobroHVO cobro;

  public HVO(){

    suscriptoresActivos = new LinkedList<Suscriptor>();
    
    suscriptoresInactivos = new LinkedList<Suscriptor>();
    
    recomendaciones = new LinkedList<String>();
    
    tiposDeSuscripcion = new LinkedList<>();

    tiposDeSuscripcion.add("Suscripcion de prueba de HVO Max");

    tiposDeSuscripcion.add("Suscripcion normal de HVO Max");

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

  public String cobro(Cliente cliente){

    Suscriptor buscaSuscriptor = new Suscriptor(cliente);

    int indiceDelSuscriptor = suscriptoresActivos.indexOf(buscaSuscriptor);

    Suscriptor suscriptor = suscriptoresActivos.get(indiceDelSuscriptor); 
    
    String tipoSuscripcion = suscriptor.getTipoDeSuscripcion();

    if (tipoSuscripcion.equals("Suscripcion de prueba de HVO Max") && !suscriptor.getSuscripcionDePrueba()) {
      
      suscriptor.setTipoDeSuscripcion("Suscripcion normal de HVO Max");

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

      contadorDeRecomendaciones++;
      
      if (contadorDeRecomendaciones == recomendaciones.size()) {

        contadorDeRecomendaciones = 0;
        
      }

      recomendacionDelMes = recomendaciones.get(contadorDeRecomendaciones);

      return recomendacionDelMes;
    
    }

  }

  public class Suscriptor {
    
    private int antiguedad;
    
    private String tipoSuscripcion;

    private Cliente cliente;

    private boolean suscripcionDePrueba;

    public Suscriptor(Cliente cliente){

      this.cliente = cliente;

    }

    public Suscriptor(Cliente cliente, String tipoDeSuscripcion){

      this.cliente = cliente;
      
      this.tipoSuscripcion = tipoDeSuscripcion;
      
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

    public boolean getSuscripcionDePrueba(){

      return suscripcionDePrueba;

    }

    public void setTipoDeSuscripcion(String cadena){

      this.tipoSuscripcion = cadena;

    }

    public void aumentarAntiguedad(){

      antiguedad++;

    }

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
