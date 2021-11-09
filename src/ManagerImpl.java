import java.util.Comparator;
import java.util.List;

public class ManagerImpl implements Manager{
    private static Manager instance;
    public static Manager getInstance(){
        if (instance==null){
            instance=new ManagerImpl();
        }
        return instance;
    }
    List<Usuario>listaUsuarios;

    @Override
    public void NuevoUsuario(String nombre) {
        Usuario usuario = new Usuario(nombre);
        listaUsuarios.add(usuario);
    }

    @Override
    public List<Usuario> usuariosOrdenados(List<Usuario> listaUsuarios) {
        listaUsuarios.sort(new Comparator<Usuario>() {
            @Override
            public int compare(Usuario o1, Usuario o2) {
            return o1.getNombreU().compareTo(o2.getNombreU());}
        });
        return listaUsuarios;
    }

    @Override
    public Usuario InfoUsuario(String nombre) {
        Usuario usuarioI = null;
        boolean encontrado=false;
        int i=0;
        while((encontrado=false )&& (i<listaUsuarios.size())){
            if (listaUsuarios.get(i).getNombreU() == nombre) {
                usuarioI=listaUsuarios.get(i);
                encontrado = true;
            } else i=i+1;
        }
        return usuarioI;
    }

    @Override
    public void PuntoInteresNuevo(String nombreU, String nombrePI) {
        int i=0;
        boolean encontrado=false;
        while((encontrado==false)&&(i<listaUsuarios.size())) {
            if (listaUsuarios.get(i).getNombreU() == nombreU) {
                listaUsuarios.get(i).listaPuntosInteres.add(nombrePI);
                encontrado = true;
            } else i=i+1;
        }
    }

    @Override
    public List<Usuario> UsuariosQueHanPasadoPorPI(List<Usuario> listaUsuarios,String nombrePI) {
        List<Usuario>UsuariosQueHanPasado=null;
        int i=0;
        while(i<listaUsuarios.size())
            if(listaUsuarios.get(i).getListaPuntosInteres().contains(nombrePI)) {
                UsuariosQueHanPasado.add(listaUsuarios.get(i));
                i=i+1;
            }
            else
                i=i+1;
        return UsuariosQueHanPasado;
    }

    @Override
    public List<Usuario> usuariosOrdenadosDescendentemente(List<Usuario> listaUsuarios) {

        listaUsuarios.sort(new Comparator<Usuario>() {
            @Override
            public int compare(Usuario o1, Usuario o2) {
                return Double.compare(o1.listaPuntosInteres.size(), o2.listaPuntosInteres.size());}
        });
            return listaUsuarios;
    }

    @Override
    public void liberarRecursos() {
        this.listaUsuarios.clear();
    }
}
