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
    public Manager manager; //Cambiar nombre manager
    //CREACIÓN ESTRUCTURAS DE LA IMPLEMENTACIÓN
    List<Usuario> listaUsuarios;


    public Service(){
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        this.manager = ManagerImpl.getInstance(); // Cambiar nombre manager

        // INICIALIZAR ESTRUCTURAS DE LA IMPLEMENTACIÓN
        listaUsuarios=new LinkedList<Usuario>();

        // CREACIÓN DE OBJETOS NECESARIOS

        Usuario Maria = new Usuario("Maria");
        Usuario Ruth = new Usuario("Ruth");


        // COLOCACIÓN DE OBJETOS PREDETERMINADOS EN LAS LISTAS
        listaUsuarios.add(Maria);
        listaUsuarios.add(Ruth);
        PuntoInteresNuevo("Maria","puente");
    }

    // AÑADIR USUARIO
    @PUT
    @ApiOperation(value = "NuevoUsuario", notes = "Añade un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Error"),
    })
    @Path("/NuevoUsuario/{nombreU}}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response NuevoUsuario(@PathParam("nombreU") String usuario) {
        try {
            manager.NuevoUsuario("usuario");
            Usuario usuario1 = new Usuario(usuario);
            listaUsuarios.add(usuario1);
            return Response.status(200).build();
        }
        catch(Error E){
            return Response.status(400).build();
        }
    }

    // USUARIOS ORDENADOS
    @GET
    @ApiOperation(value = "usuariosOrdenados", notes = "Lista de usuarios ordenadados alfabeticamente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful",response = Usuario.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Error"),
    })
    @Path("/usuariosOrdenados")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usuariosOrdenados() {
        try {
            List<Usuario> listaOrdenada = new LinkedList<Usuario>();
            listaOrdenada = manager.usuariosOrdenados(listaUsuarios);
            GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(listaOrdenada){};
            return Response.status(200).entity(entity).build();
        }catch(Error E){
            return Response.status(400).build();}}


    // INFO USUARIO
    @GET
    @ApiOperation(value = "InfoUsuario", notes = "Informacion de un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful",response = Usuario.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Error"),
    })
    @Path("/InfoUsuario/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response InfoUsuario(@PathParam("nombre") String nombre) {
        try {
            Usuario usuario = manager.InfoUsuario(nombre);
            GenericEntity entity = new GenericEntity(usuario){};
            return Response.status(200).entity(entity).build();
        }
        catch(Error E){
            return Response.status(400).build();
        }
    }

    // AÑADIR PUNTO DE INTERES A UN USUARIO
    @PUT
    @ApiOperation(value = "PuntoInteresNuevo", notes = "Añade punto de interes a un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Error"),
    })
    @Path("/PuntoInteresNuevo/{nombreU}/{nombrePI}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response PuntoInteresNuevo(@PathParam("nombreU") String usuario,@PathParam("nombrePI") String pi) {
        try {
            manager.PuntoInteresNuevo("nombreU","nombrePI");
            return Response.status(200).build();
        }
        catch(Error E){
            return Response.status(400).build();
        }
    }

    // LISTA USUARIOS QUE HAN PASADO POR PI
    @GET
    @ApiOperation(value = "UsuariosQueHanPasadoPorPI", notes = "Lista de usuarios que han pasado por PI")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful",response = Usuario.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Error"),
    })
    @Path("/UsuariosQueHanPasadoPorPI/{nombrePI}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response UsuariosQueHanPasadoPorPI(@PathParam("nombrePI") String nombrePI) {
        try {
            List<Usuario> listaUsuariosQueHanPasado = new LinkedList<Usuario>();
            listaUsuariosQueHanPasado = manager.UsuariosQueHanPasadoPorPI(listaUsuarios,"nombrePI");
            GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(listaUsuariosQueHanPasado){};
            return Response.status(200).entity(entity).build();
        }
        catch(Error E){
            return Response.status(400).build();
        }
    }

    // LISTA USUARIOS ORDENADA SEGUN NUMERO DE PI
    @GET
    @ApiOperation(value = "usuariosOrdenadosDescendentemente", notes = "Lista de usuarios ordenados segun numero de PI")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful",response = Usuario.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Error"),
    })
    @Path("/usuariosOrdenadosDescendentemente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response UsuariosQueHanPasadoPorPI() {
        try {
            List<Usuario> listaUsuariosOrdenada = new LinkedList<Usuario>();
            listaUsuariosOrdenada = manager.usuariosOrdenadosDescendentemente(listaUsuarios);
            GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(listaUsuariosOrdenada){};
            return Response.status(200).entity(entity).build();
        }
        catch(Error E){
            return Response.status(400).build();
        }
    }
}
