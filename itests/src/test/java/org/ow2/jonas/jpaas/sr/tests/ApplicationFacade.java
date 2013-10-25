package org.ow2.jonas.jpaas.sr.tests;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;
import org.ops4j.pax.exam.testng.listener.PaxExam;
import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVO;
import org.ow2.jonas.jpaas.sr.facade.vo.UserVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class ApplicationFacade extends SetupTest {

    @Inject
    private ISrApplicationFacade iSrApplicationFacade;

    @Inject
    private ISrUserFacade iSrUserFacade;

    /**
     * User ID
     */
    private String userID;

    /**
     * Capabilities list
     */
    private Map<String,String> capabilitiesList;

    /**
     * Requirements list
     */
    private List<String> requirementsList;

    /**
     * Application 1 value object
     */
    private ApplicationVO app1;

    /**
     * Application 2 value object
     */
    private ApplicationVO app2;

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

    @Test(dependsOnMethods = "testGetApplication")
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
