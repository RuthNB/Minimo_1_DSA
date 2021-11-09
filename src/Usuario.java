import java.util.List;

public class Usuario {

        String nombreU;
        List<String> listaPuntosInteres;

        public Usuario(String nombre) {
            this.nombreU = nombre;
            this.listaPuntosInteres = null;
        }

        public void setNombreU(String nombre) {
            this.nombreU = nombre;
        }

        public String getNombreU() {
            return nombreU;
        }

        public List<String> getListaPuntosInteres() {
            return listaPuntosInteres;
        }

        public void PuntoInteresNuevo(String PI) {
            listaPuntosInteres.add(PI);
        }
    }

