package org.ow2.jonas.jpaas.sr.tests;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.junit.Before;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.ops4j.pax.exam.testng.listener.PaxExam;
import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVO;
import org.ow2.jonas.jpaas.sr.facade.vo.UserVO;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.net.URI;
import java.util.*;

import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;
import com.peergreen.deployment.*;


@Listeners(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class ApplicationFacadeTest {

    public static final String PROJECT_VERSION = "1.0.0-M1-SNAPSHOT";

    @Inject
    private static ISrApplicationFacade iSrApplicationFacade;

    @Inject
    private static ISrUserFacade iSrUserFacade;

    /**
     * User ID
     */
    private static String userID;

    /**
     * Capabilities list
     */
    private static Map<String,String> capabilitiesList;

    /**
     * Requirements list
     */
    private static List<String> requirementsList;

    /**
     * Application 1 value object
     */
    private static ApplicationVO app1;

    /**
     * Application 2 value object
     */
    private static ApplicationVO app2;


    /**
     * Name of the module for the lookup
     */
    private final String moduleName = System.getProperty("module.name");

    @Configuration
    public Option[] config() {

        // Reduce log level.
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);

        return options(systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level").value("WARN"),
                mavenBundle("org.testng", "testng", "6.3.1"),
                mavenBundle("com.peergreen.paas", "paas-system-representation-vo").version(PROJECT_VERSION),
                mavenBundle("com.peergreen.paas", "paas-system-representation-api").version(PROJECT_VERSION),
                mavenBundle("com.peergreen.paas", "paas-system-representation-ejb").version(PROJECT_VERSION));
    }


    private void init() {
        capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        requirementsList = new LinkedList<String>();
        requirementsList.add("requirement 1");
        requirementsList.add("requirement 2");

        app1 =  new ApplicationVO("app1", "testDescription", requirementsList, capabilitiesList);
        app2 =  new ApplicationVO("app2", "testDescription2", requirementsList, capabilitiesList);
    }


    private void initDatabase() {
        UserVO user1 = new UserVO("user1", "testPassword", "testRole");
        user1 = iSrUserFacade.createUser(user1);
        this.userID = user1.getId();
    }


    @Test
    public void testCreateApplication() {
        init();
        initDatabase();
        System.out.println("app1=" + app1);
        System.out.println("userID=" + userID);

        System.out.println("userID=" + userID);

        //Create an Application
        ApplicationVO tmpApp1 =  iSrApplicationFacade.createApplication(userID, app1);
        Assert.assertNotEquals(tmpApp1.getId(), null);
        app1=tmpApp1;
        System.out.println("app1=" + app1);

        //Create a Second Application
        ApplicationVO tmpApp2 =  iSrApplicationFacade.createApplication(userID, app2);
        Assert.assertNotEquals(tmpApp2.getId(), null);
        app2= tmpApp2;
        System.out.println("app2=" + app2);

    }

    @Test(dependsOnMethods = "testCreateApplication")
    public void testGetApplication() {
        //Get the First Application
        System.out.println("app1=" + app1);

        ApplicationVO tmpApp1 = iSrApplicationFacade.getApplication(app1.getId());
        Assert.assertEquals(app1.getId(), tmpApp1.getId());
        Assert.assertEquals(app1.getName(), tmpApp1.getName());
        Assert.assertEquals(app1.getDescription(), tmpApp1.getDescription());
        Assert.assertEquals(app1.getRequirements(), tmpApp1.getRequirements());
        Assert.assertEquals(app1.getCapabilities(), tmpApp1.getCapabilities());
    }

    @Test(dependsOnMethods = "testCreateApplication")
    public void testUpdateApplication() {
        //Update First Application
        app1.setName("updateApp1");
        app1.setDescription("updateDescription");
        List<String> updateRequirementsList = new LinkedList<String>(requirementsList);
        updateRequirementsList.add("capability 3");
        app1.setRequirements(updateRequirementsList);
        Map<String,String> updateCapabilitiesList = new HashMap<String,String>(capabilitiesList);
        updateCapabilitiesList.remove(1);
        app1.setCapabilities(updateCapabilitiesList);
        ApplicationVO tmpApp1 = iSrApplicationFacade.updateApplication(app1);
        Assert.assertEquals(app1.getId(), tmpApp1.getId());
        Assert.assertEquals(app1.getName(), tmpApp1.getName());
        Assert.assertEquals(app1.getDescription(), tmpApp1.getDescription());
        Assert.assertEquals(app1.getRequirements(), tmpApp1.getRequirements());
        Assert.assertEquals(app1.getCapabilities(), tmpApp1.getCapabilities());
    }

    @Test(dependsOnMethods = "testUpdateApplication")
    public void testFindApplication() {
        //Find Applications
        List<ApplicationVO> applicationList = iSrApplicationFacade.findApplications(userID);
        Assert.assertEquals(applicationList.size(), 2);

        //Find Applications with name
        applicationList = iSrApplicationFacade.findApplications(userID, app2.getName());
        Assert.assertEquals(applicationList.size(), 1);
        Assert.assertEquals(applicationList.get(0).getName(), app2.getName());
    }

    @Test(dependsOnMethods = "testFindApplication")
    public void testDeleteApplication() {
        //Delete applications
        iSrApplicationFacade.deleteApplication(app1.getId());
        List<ApplicationVO> applicationList = iSrApplicationFacade.findApplications(userID);
        Assert.assertEquals(applicationList.size(), 1);
        iSrApplicationFacade.deleteApplication(app2.getId());
        applicationList = iSrApplicationFacade.findApplications(userID);
        Assert.assertEquals(applicationList.isEmpty(), true);
    }

}
