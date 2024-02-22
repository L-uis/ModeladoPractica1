import java.io.IOException;

/**
 * Clase que simula el comportamiento de diferentes clientes que se suscriben a servicios 
 * de streaming durante un año.
 * Usa las clases Cliente, ServicioStreaming, y Registro para modelar la simulación.
 * Usa el patrón Observer para notificar a los clientes sobre los cobros y las recomendaciones 
 * de los servicios.
 * Usa el patrón Strategy para definir diferentes tipos de suscripción para cada servicio.
 * Genera un archivo txt con el registro de cada cliente al final de la simulación.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 */
public class Simulacion {

  /**
   * Metodo principal que ejecuta la simulación.
   * 
   * @throws IOException Si ocurre un error al generar los archivos txt.
   */
  public static void main(String[] args) throws IOException {

    // Se crea un arreglo con los nombres de los meses
    String[] meses = {"///////////////////// Primer mes /////////////////////", "///////////////////// Segundo mes /////////////////////"
                      , "///////////////////// Tercer mes /////////////////////", "///////////////////// Cuarto mes /////////////////////"
                      , "///////////////////// Quinto mes /////////////////////", "///////////////////// Sexto mes /////////////////////"
                      , "///////////////////// Septimo mes /////////////////////", "///////////////////// Octavo mes /////////////////////"
                      , "///////////////////// Noveno mes /////////////////////", "///////////////////// Decimo mes /////////////////////"
                      , "///////////////////// Decimoprimer mes /////////////////////", "///////////////////// Decimosegundo mes /////////////////////"};

    int contador = 0;

    // Se inicializa un contador para recorrer el arreglo de meses
    Memeflix memeflix = new Memeflix();
    Momazon momazon = new Momazon();
    Spootify spootify = new Spootify();
    Thisney thisney = new Thisney();
    HVO hvo = new HVO();

    // Se inicializan los servicios de streaming con sus respectivas recomendaciones
    memeflix.anadirRecomendacion("Luther: Cae la noche");
    memeflix.anadirRecomendacion("La sociedad de la nieve");
    memeflix.anadirRecomendacion("One Piece");
    memeflix.anadirRecomendacion("Dr. House");
    memeflix.anadirRecomendacion("The Walking Dead");

    momazon.anadirRecomendacion("Smile");
    momazon.anadirRecomendacion("Top Gun: Maverick");
    momazon.anadirRecomendacion("Un amigo extraordinario");
    momazon.anadirRecomendacion("Gen V");
    momazon.anadirRecomendacion("Duna");

    spootify.anadirRecomendacion("Blinding Lights de The Weeknd");
    spootify.anadirRecomendacion("Shape of You de Ed Sheeran");
    spootify.anadirRecomendacion("Someone You Loved de Lewis Capaldi");
    spootify.anadirRecomendacion("Sunflower de Post Malone y Swae Lee");
    spootify.anadirRecomendacion("Starboy de The Weeknd y Daft Punk");
    
    thisney.anadirRecomendacion("Loki");
    thisney.anadirRecomendacion("Ahsoka");
    thisney.anadirRecomendacion("The Adventures of Young Indiana Jones");
    thisney.anadirRecomendacion("The Mandalorian");
    thisney.anadirRecomendacion("Percy Jackson y los dioses del Olimpo");

    hvo.anadirRecomendacion("Elvis");
    hvo.anadirRecomendacion("Cinco lobitos");
    hvo.anadirRecomendacion("Black Adam");
    hvo.anadirRecomendacion("Judas y el mesías negro");
    hvo.anadirRecomendacion("Dunkerque");

    // Se colocan los tipos de suscripciones como constantes
    final String MEMEFLIX_UN_DISPOSITIVO = "Sucripcion de Memeflix para un dispositivo";
    final String MEMEFLIX_DOS_DISPOSITIVOS = "Sucripcion de Memeflix para dos dispositivos";
    final String MEMEFLIX_CUATRO_DISPOSITIVOS = "Sucripcion de Memeflix para cuatro dispositivos";

    final String MOMAZON_NORMAL = "Sucripcion normal de Momazon Prime Video";
    final String MOMAZON_PREMIUM = "Sucripcion premium de Momazon Prime Video";

    final String SPOOTIFY_GRATIS = "Sucripcion gratis de Spootify";
    final String SPOOTIFY_PREMIUM = "Sucripcion premium de Spootify";

    final String THISNEY_CON_DESCUENTO = "Sucripcion con descuento de Thisney+";
    final String THISNEY_NORMAL = "Sucripcion normal de Thisney+";

    final String HVO_PRUEBA = "Suscripcion de prueba de HVO Max";
    final String HVO_NORMAL = "Suscripcion normal de HVO Max";


    /**
     * Se inicializan los clientes con los datos dados y sus respectivas suscripciones
     * 
     * Alicia inicia la simulacion con 15000.
     * Bob inicia la simulacion con 2400.
     * Cesar inicia la simulacion con 5000.
     * Diego inicia la simulacion con 9000.
     * Erika inicia la simulacion con 10000.
     * Fausto inicia la simulacion con 5000.
     */
    Cliente alicia = new Cliente("Alicia", 15000);
    Cliente bob = new Cliente("Bob", 2400);
    Cliente cesar = new Cliente("Cesar", 5000);
    Cliente diego = new Cliente("Diego", 9000);
    Cliente erika = new Cliente("Erika", 10000);
    Cliente fausto = new Cliente("Fausto", 5000);

    /**
     * Primer mes.
     * 
     * Alicia se suscribe a todos los servicios.
     * Bob contrata todos los servicios con la version mas cara.
     * Cesar Contrata Thisney+ y HVO Max.
     * Diego contrara HVO Max, Momazon Prime viedo premium y Spootify normal.
     * Erika contrata Memeflix para cuatro dispositivos, Spootify normal y HVO Max.
     * Fausto contrata Thisney+ y HVO Max.
     */
    alicia.anadirRegistro("\n" + meses[contador] + "\n");
    bob.anadirRegistro("\n" + meses[contador] + "\n");
    cesar.anadirRegistro("\n" + meses[contador] + "\n");
    diego.anadirRegistro("\n" + meses[contador] + "\n");
    erika.anadirRegistro("\n" + meses[contador] + "\n");
    fausto.anadirRegistro("\n" + meses[contador] + "\n");
    contador++;

    alicia.agregarSuscripcion(memeflix, MEMEFLIX_CUATRO_DISPOSITIVOS);
    alicia.agregarSuscripcion(momazon,MOMAZON_PREMIUM);
    alicia.agregarSuscripcion(spootify,SPOOTIFY_PREMIUM);
    alicia.agregarSuscripcion(thisney,THISNEY_CON_DESCUENTO);
    alicia.agregarSuscripcion(hvo,HVO_PRUEBA);

    bob.agregarSuscripcion(memeflix, MEMEFLIX_CUATRO_DISPOSITIVOS);
    bob.agregarSuscripcion(momazon,MOMAZON_PREMIUM);
    bob.agregarSuscripcion(spootify,SPOOTIFY_PREMIUM);
    bob.agregarSuscripcion(thisney,THISNEY_CON_DESCUENTO);
    bob.agregarSuscripcion(hvo,HVO_PRUEBA);

    cesar.agregarSuscripcion(thisney,THISNEY_CON_DESCUENTO);
    cesar.agregarSuscripcion(hvo,HVO_PRUEBA);

    diego.agregarSuscripcion(momazon,MOMAZON_PREMIUM);
    diego.agregarSuscripcion(spootify,SPOOTIFY_GRATIS);
    diego.agregarSuscripcion(hvo,HVO_PRUEBA);

    erika.agregarSuscripcion(memeflix, MEMEFLIX_CUATRO_DISPOSITIVOS);
    erika.agregarSuscripcion(spootify,SPOOTIFY_GRATIS);
    erika.agregarSuscripcion(hvo,HVO_PRUEBA);

    fausto.agregarSuscripcion(thisney,THISNEY_CON_DESCUENTO);
    fausto.agregarSuscripcion(hvo,HVO_PRUEBA);

    memeflix.notificar();
    momazon.notificar();
    spootify.notificar();
    thisney.notificar();
    hvo.notificar();

    /**
     * Segundo mes.
     */
    alicia.anadirRegistro("\n" + meses[contador] + "\n");
    bob.anadirRegistro("\n" + meses[contador] + "\n");
    cesar.anadirRegistro("\n" + meses[contador] + "\n");
    diego.anadirRegistro("\n" + meses[contador] + "\n");
    erika.anadirRegistro("\n" + meses[contador] + "\n");
    fausto.anadirRegistro("\n" + meses[contador] + "\n");
    contador++;

    memeflix.notificar();
    momazon.notificar();
    spootify.notificar();
    thisney.notificar();
    hvo.notificar();

    /**
     * Tercer mes.
     * 
     * Bob se da de baja en Thisney+ y HVO Max.
     * Erika contrata Thisney+ y se desuscribe de HVO Max.
     * Fausto cancela todas sus suscripciones y contrata Memeflix para un dispositivo.
     */
    alicia.anadirRegistro("\n" + meses[contador] + "\n");
    bob.anadirRegistro("\n" + meses[contador] + "\n");
    cesar.anadirRegistro("\n" + meses[contador] + "\n");
    diego.anadirRegistro("\n" + meses[contador] + "\n");
    erika.anadirRegistro("\n" + meses[contador] + "\n");
    fausto.anadirRegistro("\n" + meses[contador] + "\n");
    contador++;

    bob.eliminarSuscripcion(thisney);
    bob.eliminarSuscripcion(hvo);

    erika.eliminarSuscripcion(hvo);
    erika.agregarSuscripcion(thisney, THISNEY_CON_DESCUENTO);

    fausto.eliminarTodasLasSuscripciones();
    fausto.agregarSuscripcion(memeflix, MEMEFLIX_UN_DISPOSITIVO);

    memeflix.notificar();
    momazon.notificar();
    spootify.notificar();
    thisney.notificar();
    hvo.notificar();

    /**
     * Cuarto mes
     * 
     * Bob se da de baja en Memeflix y Momazon.
     */
    alicia.anadirRegistro("\n" + meses[contador] + "\n");
    bob.anadirRegistro("\n" + meses[contador] + "\n");
    cesar.anadirRegistro("\n" + meses[contador] + "\n");
    diego.anadirRegistro("\n" + meses[contador] + "\n");
    erika.anadirRegistro("\n" + meses[contador] + "\n");
    fausto.anadirRegistro("\n" + meses[contador] + "\n");
    contador++;

    bob.eliminarSuscripcion(memeflix);
    bob.eliminarSuscripcion(momazon);

    memeflix.notificar();
    momazon.notificar();
    spootify.notificar();
    thisney.notificar();
    hvo.notificar();

    /**
     * Quinto mes
     * 
     * Fausto contrata Thisney+ y HVO Max.
     */
    alicia.anadirRegistro("\n" + meses[contador] + "\n");
    bob.anadirRegistro("\n" + meses[contador] + "\n");
    cesar.anadirRegistro("\n" + meses[contador] + "\n");
    diego.anadirRegistro("\n" + meses[contador] + "\n");
    erika.anadirRegistro("\n" + meses[contador] + "\n");
    fausto.anadirRegistro("\n" + meses[contador] + "\n");
    contador++;

    fausto.agregarSuscripcion(thisney, THISNEY_NORMAL);
    fausto.agregarSuscripcion(hvo, HVO_NORMAL);

    memeflix.notificar();
    momazon.notificar();
    spootify.notificar();
    thisney.notificar();
    hvo.notificar();

    /**
     * Sexto mes
     * 
     * Diego contrata Thisney+
     * Erika Cancela todos sus servicios.
     * Fausto cancela todas sus suscripciones.
     */
    alicia.anadirRegistro("\n" + meses[contador] + "\n");
    bob.anadirRegistro("\n" + meses[contador] + "\n");
    cesar.anadirRegistro("\n" + meses[contador] + "\n");
    diego.anadirRegistro("\n" + meses[contador] + "\n");
    erika.anadirRegistro("\n" + meses[contador] + "\n");
    fausto.anadirRegistro("\n" + meses[contador] + "\n");
    contador++;

    diego.agregarSuscripcion(thisney, THISNEY_CON_DESCUENTO);

    erika.eliminarTodasLasSuscripciones();

    fausto.eliminarTodasLasSuscripciones();

    memeflix.notificar();
    momazon.notificar();
    spootify.notificar();
    thisney.notificar();
    hvo.notificar();

    /**
     * Septimo mes
     * 
     * Cesar contrata Spootify premium.
     * Diego contrata Memeflix para un dispositivo y cambia a la version premium de Spootify y cancela Momazon
     */
    alicia.anadirRegistro("\n" + meses[contador] + "\n");
    bob.anadirRegistro("\n" + meses[contador] + "\n");
    cesar.anadirRegistro("\n" + meses[contador] + "\n");
    diego.anadirRegistro("\n" + meses[contador] + "\n");
    erika.anadirRegistro("\n" + meses[contador] + "\n");
    fausto.anadirRegistro("\n" + meses[contador] + "\n");
    contador++;

    cesar.agregarSuscripcion(spootify, SPOOTIFY_PREMIUM);

    diego.agregarSuscripcion(memeflix, MEMEFLIX_UN_DISPOSITIVO);
    diego.cambiarSuscripcion(spootify, SPOOTIFY_PREMIUM);
    diego.eliminarSuscripcion(momazon);

    memeflix.notificar();
    momazon.notificar();
    spootify.notificar();
    thisney.notificar();
    hvo.notificar();

    /**
     * Octavo mes
     */
    alicia.anadirRegistro("\n" + meses[contador] + "\n");
    bob.anadirRegistro("\n" + meses[contador] + "\n");
    cesar.anadirRegistro("\n" + meses[contador] + "\n");
    diego.anadirRegistro("\n" + meses[contador] + "\n");
    erika.anadirRegistro("\n" + meses[contador] + "\n");
    fausto.anadirRegistro("\n" + meses[contador] + "\n");
    contador++;

    memeflix.notificar();
    momazon.notificar();
    spootify.notificar();
    thisney.notificar();
    hvo.notificar();

    /**
     * Noveno mes
     */
    alicia.anadirRegistro("\n" + meses[contador] + "\n");
    bob.anadirRegistro("\n" + meses[contador] + "\n");
    cesar.anadirRegistro("\n" + meses[contador] + "\n");
    diego.anadirRegistro("\n" + meses[contador] + "\n");
    erika.anadirRegistro("\n" + meses[contador] + "\n");
    fausto.anadirRegistro("\n" + meses[contador] + "\n");
    contador++;

    memeflix.notificar();
    momazon.notificar();
    spootify.notificar();
    thisney.notificar();
    hvo.notificar();

    /**
     * Decimo mes
     * 
     * Erica contrata Momazon Premium, HVO Max y Thisney+
     */
    alicia.anadirRegistro("\n" + meses[contador] + "\n");
    bob.anadirRegistro("\n" + meses[contador] + "\n");
    cesar.anadirRegistro("\n" + meses[contador] + "\n");
    diego.anadirRegistro("\n" + meses[contador] + "\n");
    erika.anadirRegistro("\n" + meses[contador] + "\n");
    fausto.anadirRegistro("\n" + meses[contador] + "\n");
    contador++;
    
    erika.agregarSuscripcion(momazon, MOMAZON_PREMIUM);
    erika.agregarSuscripcion(thisney, THISNEY_NORMAL);
    erika.agregarSuscripcion(hvo, HVO_NORMAL);

    memeflix.notificar();
    momazon.notificar();
    spootify.notificar();
    thisney.notificar();
    hvo.notificar();

    /**
     * Decimoprimer mes
     */
    alicia.anadirRegistro("\n" + meses[contador] + "\n");
    bob.anadirRegistro("\n" + meses[contador] + "\n");
    cesar.anadirRegistro("\n" + meses[contador] + "\n");
    diego.anadirRegistro("\n" + meses[contador] + "\n");
    erika.anadirRegistro("\n" + meses[contador] + "\n");
    fausto.anadirRegistro("\n" + meses[contador] + "\n");
    contador++;

    memeflix.notificar();
    momazon.notificar();
    spootify.notificar();
    thisney.notificar();
    hvo.notificar();

    /**
     * Decimosegundo mes
     */
    alicia.anadirRegistro("\n" + meses[contador] + "\n");
    bob.anadirRegistro("\n" + meses[contador] + "\n");
    cesar.anadirRegistro("\n" + meses[contador] + "\n");
    diego.anadirRegistro("\n" + meses[contador] + "\n");
    erika.anadirRegistro("\n" + meses[contador] + "\n");
    fausto.anadirRegistro("\n" + meses[contador] + "\n");

    memeflix.notificar();
    momazon.notificar();
    spootify.notificar();
    thisney.notificar();
    hvo.notificar();

    /**
     * Se generan los registros de los clientes.
     */
    alicia.generarRegistro();
    bob.generarRegistro();
    cesar.generarRegistro();
    erika.generarRegistro();
    fausto.generarRegistro();

  }
  
}