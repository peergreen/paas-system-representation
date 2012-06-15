/**
 * JPaaS
 * Copyright (C) 2012 Bull S.A.S.
 * Contact: jasmine@ow2.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
 * USA
 *
 * --------------------------------------------------------------------------
 * $Id$
 * --------------------------------------------------------------------------
 */

package org.ow2.jonas.jpaas.sr.facade.tests;

import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVO;
import org.ow2.jonas.jpaas.sr.facade.vo.UserVO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.LinkedList;
import java.util.List;

/**
 * Application Facade test case
 * @author David Richard
 */
public class TestApplicationFacade {

    /**
     * User Facade
     */
    private ISrUserFacade iSrUserFacade = null;

    /**
     * Application Facade
     */
    private ISrApplicationFacade iSrApplicationFacade = null;

    /**
     * User ID
     */
    private String userID;

    /**
     * Capabilities list
     */
    private List<String> capabilitesList;

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

    @BeforeClass
    public void init() throws NamingException {
        getBean();
        initDatabase();

        capabilitesList = new LinkedList<String>();
        capabilitesList.add("capability 1");
        capabilitesList.add("capability 2");

        requirementsList = new LinkedList<String>();
        requirementsList.add("requirement 1");
        requirementsList.add("requirement 2");

        app1 =  new ApplicationVO("app1", "testDescription", requirementsList, capabilitesList);
        app2 =  new ApplicationVO("app2", "testDescription2", requirementsList, capabilitesList);
    }

    private void getBean() throws NamingException {
        InitialContext initialContext = new InitialContext();
        this.iSrUserFacade = (ISrUserFacade) initialContext.lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade");
        this.iSrApplicationFacade = (ISrApplicationFacade) initialContext.lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationFacade");
    }

    private void initDatabase() {
        UserVO user1 = new UserVO("user1", "testPassword", "testRole");
        user1 = iSrUserFacade.createUser(user1);
        this.userID = user1.getId();
    }

    @AfterClass
    public void cleanDatabase() {
        iSrUserFacade.deleteUser(userID);
    }


    @Test
    public void testCreateApplication() {
        //Create an Application
        ApplicationVO tmpApp1 =  iSrApplicationFacade.createApplication(userID, app1);
        Assert.assertNotEquals(tmpApp1.getId(), null);
        app1 = tmpApp1;

        //Create a Second Application
        ApplicationVO tmpApp2 =  iSrApplicationFacade.createApplication(userID, app2);
        Assert.assertNotEquals(tmpApp2.getId(), null);
        app2 = tmpApp2;
    }

    @Test(dependsOnMethods = "testCreateApplication")
    public void testGetApplication() {
        //Get the First Application
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
        List<String> updateCapabilitesList = new LinkedList<String>(capabilitesList);
        updateCapabilitesList.remove(1);
        app1.setCapabilities(updateCapabilitesList);
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
