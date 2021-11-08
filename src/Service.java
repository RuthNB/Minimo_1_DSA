import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Api(value = "/service")
@Path("/Service")
public class Service { // Nombre del archivo service
    static final Logger logger = Logger.getLogger(Service.class); // Nombre del archivo service
    public Covid19Manager manager; //Cambiar nombre manager

    //CREACIÓN ESTRUCTURAS DE LA IMPLEMENTACIÓN
    List<Vacunado> listaVacunados;
    List<Vacuna> listaVacunas;

    public Service(){
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        this.manager = Covid19ManagerImpl.getInstance(); // Cambiar nombre manager

        // INICIALIZAR ESTRUCTURAS DE LA IMPLEMENTACIÓN
        listaVacunados=new LinkedList<Vacunado>();
        listaVacunas=new LinkedList<Vacuna>();

        // CREACIÓN DE OBJETOS NECESARIOS

        Vacuna Pfizer=new Vacuna("Pfizer",30);
        Vacuna Moderna=new Vacuna("Moderna",10);
        Vacuna Astrazeneca=new Vacuna("Astrazeneca",22);

        // COLOCACIÓN DE OBJETOS PREDETERMINADOS EN LAS LISTAS
        listaVacunas.add(Pfizer);
        listaVacunas.add(Moderna);
        listaVacunas.add(Astrazeneca);
    }

    // VACUNAR
    @PUT
    @ApiOperation(value = "Vacunar", notes = "Vacuna a un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Error"),
    })
    @Path("/Vacunar/{usuario}/{vacuna}/{fecha}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response Vacunar(@PathParam("usuario") String usuario,@PathParam("vacuna") Vacuna vacuna,@PathParam("fecha") Date fecha) {
        try {
            manager.vacunar("usuario",vacuna,fecha);
            Vacunado vacunado = new Vacunado(usuario,vacuna,fecha);
            listaVacunados.add(vacunado);
            return Response.status(200).build();
        }
        catch(Error E){
            return Response.status(400).build();
        }
    }

    // Lista de vacunaciones ordenadas por marca
    @GET
    @ApiOperation(value = "VacunacionesSegunMarca", notes = "Lista de vacunaciones ordenadas por marca y por fecha de vacunacion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful",response = Vacunado.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Error"),
    })
    @Path("/VacunacionesSegunMarca")
    @Produces(MediaType.APPLICATION_JSON)
    public Response VacunacionesSegunMarca() {
        try {
            List<Vacunado> listaOrdenada = new LinkedList<Vacunado>();
            listaOrdenada = manager.vacunacionesOrdenadasPorMarca(listaVacunados);
            GenericEntity<List<Vacunado>> entity = new GenericEntity<List<Vacunado>>(listaOrdenada){};
            return Response.status(200).entity(entity).build();
        }catch(Error E){
            return Response.status(400).build();}}

    // Lista de vacunas ordenadas segun el numero de vacunados
    @GET
    @ApiOperation(value = "VacunasSegunVacunados", notes = "Lista de vacunass ordenadas segun numero de vacunados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful",response = Vacuna.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Error"),
    })
    @Path("/VacunasSegunVacunados")
    @Produces(MediaType.APPLICATION_JSON)
    public Response VacunasSegunVacunados() {
        try {
            List<Vacuna> listaOrdenada = new LinkedList<Vacuna>();
            listaOrdenada = manager.vacunasOrdenadasSegunCantidadVacunados(listaVacunas);
            GenericEntity<List<Vacuna>> entity = new GenericEntity<List<Vacuna>>(listaOrdenada){};
            return Response.status(200).entity(entity).build();
        }
        catch(Error E){
            return Response.status(400).build();
        }
    }

    // Lista de seguimientos de un vacunado
    @GET
    @ApiOperation(value = "SeguimientoVacunado", notes = "Lista de seguimientos de un vacunado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful",response = Seguimiento.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Error"),
    })
    @Path("/SeguimientoVacunado/{vacunado}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response SeguimientoVacunado(@PathParam("vacunado") String vacunado) {
        try {
            List<Seguimiento> listaOrdenada = new LinkedList<Seguimiento>();
            listaOrdenada = manager.seguimientosDeUnVacunado(vacunado);
            GenericEntity<List<Seguimiento>> entity = new GenericEntity<List<Seguimiento>>(listaOrdenada){};
            return Response.status(200).entity(entity).build();
        }
        catch(Error E){
            return Response.status(400).build();
        }
    }

    // AÑADIR SEGUIMIENTO
    @PUT
    @ApiOperation(value = "AñadirSeguimiento", notes = "Añade un seguimiento a un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Error"),
    })
    @Path("/AñadirSeguimiento/{vacunado}/{fecha}/{estado}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response Vacunar(@PathParam("vacunado") String usuario,@PathParam("fecha") Date fecha,@PathParam("estado") String estado) {
        try {
            manager.realizarSeguimineto("vacunado",fecha,"estado");
            return Response.status(200).build();
        }
        catch(Error E){
            return Response.status(400).build();
        }
    }






}
