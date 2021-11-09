import java.util.Date;
import java.util.LinkedList;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class test {
    Covid19ManagerImpl Covid19Manager = null;
    private static final Logger logger = Logger.getLogger(test.class);
    @Before
    public void setUp(){
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        Covid19Manager= (Covid19ManagerImpl) Covid19ManagerImpl.getInstance();
        Covid19Manager.listaVacunados=new LinkedList<Vacunado>();
        Covid19Manager.listaVacunas=new LinkedList<Vacuna>();
    }
    @After
    public void tearDown(){Covid19Manager.liberarRecursos();}
    @Test
    public void Comprovacion(){
        Covid19Manager.vacunar("Pol",new Vacuna("Pfizer",30), new Date(17/02/2021));
        logger.info("Se ha vacunado correctamente a un nuevo usuario");
    }
    @Test
    public void Comprovacion2(){
        Covid19Manager.realizarSeguimineto("Pol",new Date (17/02/2021),"bien");
        logger.info("Se ha realizado el seguimiento correctamente");
    }
    

}
