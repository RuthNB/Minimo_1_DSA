import java.util.Date;
import java.util.LinkedList;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class test {
    ManagerImpl Manager = null;
    private static final Logger logger = Logger.getLogger(test.class);

    @Before
    public void setUp(){
        PropertyConfigurator.configure("src/resources/log4j.properties");
        Manager= (ManagerImpl) ManagerImpl.getInstance();
        Manager.listaUsuarios=new LinkedList<Usuario>();
        Usuario Maria = new Usuario("Maria");
        Manager.listaUsuarios.add(Maria);
        Usuario Ruth = new Usuario("Ruth");
        Manager.listaUsuarios.add(Ruth);
    }
    @After
    public void tearDown(){Manager.liberarRecursos();}

    @Test
    public void Comprovacion(){
        Manager.NuevoUsuario("Ruth");
        logger.info("Se ha añadido un nuevo usuario");
    }
    @Test
    public void Comprovacion2(){
        Manager.InfoUsuario("Ruth");
        logger.info("Añade PI");
    }


}
