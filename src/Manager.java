import java.util.Date;
import java.util.List;

public interface Manager {
    //- Añadir un usuario
    //- Listado de usuarios ordenado alfabéticamente
    //- Consultar información de un usuario


    //- Informar que un usuario pasa por un punto de interés (puerta, casilla X,
    //puente, casilla Y, etc..) añadir punto interes


    //- Consultar los puntos de interés por los que un usuario pasa (orden en que se
    //notifica)

    //- Listado de usuarios que han pasado por un punto de interés

    //- Listado de alumnos ordenado descendentemente por puntos de interés por
    //los que han pasado.

    public void NuevoUsuario(String nombre);
    public List<Usuario> usuariosOrdenados(List<Usuario> listaUsuarios);
    public Usuario InfoUsuario(String nombre);
    public void PuntoInteresNuevo(String nombreU,String nombrePI);
    public List<Usuario>UsuariosQueHanPasadoPorPI(List<Usuario> listaUsuarios,String nombrePI);
    public List<Usuario> usuariosOrdenadosDescendentemente(List<Usuario> listaUsuarios);
    public void liberarRecursos();

}
