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
import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationVersionFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVO;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVersionVO;
import org.ow2.jonas.jpaas.sr.facade.vo.DeployableVO;
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
 * ApplicationVersion Facade test case
 * @author David Richard
 */
public class TestApplicationVersionFacade {

    /**
     * User Facade
     */
    private ISrUserFacade iSrUserFacade = null;

    /**
     * Application Facade
     */
    private ISrApplicationFacade iSrApplicationFacade = null;

    /**
     * ApplicationVersion Facade
     */
    private ISrApplicationVersionFacade iSrApplicationVersionFacade = null;

    /**
     * ID of the User
     */
    private String userID;

    /**
     * ID of the Application
     */
    private String applicationID;

    /**
     * Requirements list
     */
    private List<String> requirementsList;

    /**
     * ApplicationVersion 1 value object
     */
    private ApplicationVersionVO appVersion1;

    /**
     * ApplicationVersion 2 value object
     */
    private ApplicationVersionVO appVersion2;


    @BeforeClass
    public void init() throws NamingException {
        getBean();
        initDatabase();
        appVersion1 = new ApplicationVersionVO("testLabel", requirementsList);
        appVersion2 = new ApplicationVersionVO("testLabel2", requirementsList);
    }

    private void getBean() throws NamingException {
        InitialContext initialContext = new InitialContext();
        this.iSrUserFacade = (ISrUserFacade) initialContext.lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade");
        this.iSrApplicationFacade = (ISrApplicationFacade) initialContext.lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationFacade");
        this.iSrApplicationVersionFacade = (ISrApplicationVersionFacade) initialContext.lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationVersionFacade");
    }

    private void initDatabase() {
        //Create User
        UserVO user1 = new UserVO("user1", "testPassword", "testRole");
        user1 = iSrUserFacade.createUser(user1);
        this.userID = user1.getId();

        //Create Application
        List<String> capabilitesList = new LinkedList<String>();
        capabilitesList.add("capability 1");
        capabilitesList.add("capability 2");
        requirementsList = new LinkedList<String>();
        requirementsList.add("requirement 1");
        requirementsList.add("requirement 2");
        ApplicationVO app1 =  new ApplicationVO(applicationID, "app1", "testDescription", requirementsList,
                capabilitesList);
        app1 = iSrApplicationFacade.createApplication(userID, app1);
        this.applicationID = app1.getId();
    }

    @AfterClass
    public void cleanDatabase() {
        iSrApplicationFacade.deleteApplication(applicationID);
        iSrUserFacade.deleteUser(userID);
    }

    @Test
    public void testCreateApplicationVersion() {
        //Create an ApplicationVersion
        ApplicationVersionVO tmpAppVersion1 = iSrApplicationVersionFacade.createApplicationVersion(applicationID,
                appVersion1);
        Assert.assertNotEquals(tmpAppVersion1.getVersionId(), null);
        appVersion1 = tmpAppVersion1;

        //Create a second ApplicationVersion
        ApplicationVersionVO tmpAppVersion2 = iSrApplicationVersionFacade.createApplicationVersion(applicationID,
                appVersion2);
        Assert.assertNotEquals(tmpAppVersion2.getVersionId(), null);
        appVersion2 = tmpAppVersion2;
    }

    @Test(dependsOnMethods = "testCreateApplicationVersion")
    public void testGetApplicationVersion() {
        //Get the first ApplicationVersion
        ApplicationVersionVO tmpAppVersion1 =
                iSrApplicationVersionFacade.getApplicationVersion(applicationID, appVersion1.getVersionId());
        Assert.assertEquals(appVersion1.getVersionId(), tmpAppVersion1.getVersionId());
        Assert.assertEquals(appVersion1.getLabel(), tmpAppVersion1.getLabel());
        Assert.assertEquals(appVersion1.getRequirements(), tmpAppVersion1.getRequirements());
    }

    @Test(dependsOnMethods = "testCreateApplicationVersion")
    public void testUpdateApplicationVersion() {
        //Update an ApplicationVersion
        appVersion2.setLabel("updateLabel");
        List<String> updateRequirementsList = new LinkedList<String>(requirementsList);
        updateRequirementsList.add("capability 3");
        appVersion2.setRequirements(updateRequirementsList);
        ApplicationVersionVO tmpAppVersion2 = iSrApplicationVersionFacade.updateApplicationVersion(applicationID,
                appVersion2);
        Assert.assertEquals(appVersion2.getVersionId(), tmpAppVersion2.getVersionId());
        Assert.assertEquals(appVersion2.getLabel(), tmpAppVersion2.getLabel());
        Assert.assertEquals(appVersion2.getRequirements(), tmpAppVersion2.getRequirements());
    }

    @Test(dependsOnMethods = "testUpdateApplicationVersion")
    public void testFindApplicationVersion() {
        //Find ApplicationVersions of an Application
        List<ApplicationVersionVO> applicationVersionVOList =
                iSrApplicationVersionFacade.findApplicationVersions(applicationID);
        Assert.assertEquals(applicationVersionVOList.size(), 2);

        //Find ApplicationVersions (with a specific label) of an Application
        applicationVersionVOList =  iSrApplicationVersionFacade.findApplicationVersions(applicationID,
                appVersion1.getLabel());
        Assert.assertEquals(applicationVersionVOList.size(), 1);
        Assert.assertEquals(applicationVersionVOList.get(0).getLabel(), appVersion1.getLabel());
    }


    @Test(dependsOnMethods = "testFindApplicationVersion")
    public void testDeleteApplicationVersion() {
        //Delete ApplicationVersions
        iSrApplicationVersionFacade.deleteApplicationVersion(applicationID, appVersion1.getVersionId());
        List<ApplicationVersionVO> applicationVersionVOList =
                iSrApplicationVersionFacade.findApplicationVersions(applicationID);
        Assert.assertEquals(applicationVersionVOList.size(), 1);
        iSrApplicationVersionFacade.deleteApplicationVersion(applicationID, appVersion2.getVersionId());
        applicationVersionVOList = iSrApplicationVersionFacade.findApplicationVersions(applicationID);
        Assert.assertEquals(applicationVersionVOList.isEmpty(), true);
    }

    @Test(dependsOnMethods = "testDeleteApplicationVersion")
    public void testDeployable() {
        DeployableVO deployable1 = new DeployableVO("url", true, requirementsList, new LinkedList<String>());
        DeployableVO deployable2 = new DeployableVO("url2", true, requirementsList, new LinkedList<String>());
        appVersion1.getDeployableList().add(deployable1);
        appVersion1.getDeployableList().add(deployable2);
        ApplicationVersionVO tmpAppVersion1;
        tmpAppVersion1 = iSrApplicationVersionFacade.createApplicationVersion(applicationID, appVersion1);
        Assert.assertEquals(appVersion1.getDeployableList().get(0).getUrl(),
                tmpAppVersion1.getDeployableList().get(0).getUrl(), "Test Deployable creation");
        //Test update
        tmpAppVersion1.getDeployableList().get(1).setUrl("updateUrl2");
        DeployableVO deployable3 = new DeployableVO("url3", true, requirementsList, new LinkedList<String>());
        tmpAppVersion1.getDeployableList().add(deployable3);
        tmpAppVersion1.getDeployableList().remove(0);
        ApplicationVersionVO updateAppVersion1 = iSrApplicationVersionFacade.updateApplicationVersion(applicationID,
                tmpAppVersion1);
        Assert.assertEquals(updateAppVersion1.getDeployableList().size(),
                                tmpAppVersion1.getDeployableList().size(), "Test Deployable update");
        Assert.assertEquals(updateAppVersion1.getDeployableList().get(0).getUrl(),
                        tmpAppVersion1.getDeployableList().get(0).getUrl(), "Test Deployable update");
        iSrApplicationVersionFacade.deleteApplicationVersion(applicationID, tmpAppVersion1.getVersionId());
    }

}
