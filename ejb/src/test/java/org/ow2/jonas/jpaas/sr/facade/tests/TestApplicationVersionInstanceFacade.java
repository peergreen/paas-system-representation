/**
 * JPaaS
 * Copyright 2012 Bull S.A.S.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * $Id:$
 */

package org.ow2.jonas.jpaas.sr.facade.tests;

import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationVersionFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationVersionInstanceFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVO;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVersionInstanceVO;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVersionVO;
import org.ow2.jonas.jpaas.sr.facade.vo.DeployableVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasArtefactVO;
import org.ow2.jonas.jpaas.sr.facade.vo.UserVO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ApplicationVersionInstance Facade test case
 * @author David Richard
 */
public class TestApplicationVersionInstanceFacade {

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
     * ApplicationVersionInstance Facade
     */
    private ISrApplicationVersionInstanceFacade iSrApplicationVersionInstanceFacade = null;

    /**
     * ID of the User
     */
    private String userID;

    /**
     * ID of the Application
     */
    private String applicationID;

    /**
     * ID of the ApplicationVersion
     */
    private String applicationVersionID;

    /**
     * ApplicationVersionInstance 1 value object
     */
    private ApplicationVersionInstanceVO appVersionInstance1;

    /**
     * ApplicationVersionInstance 2 value object
     */
    private ApplicationVersionInstanceVO appVersionInstance2;

    /**
     * Name of the module for the lookup
     */
    private final String moduleName = System.getProperty("module.name");


    @BeforeClass
    public void init() throws NamingException {
        getBean();
        initDatabase();
        appVersionInstance1 = new ApplicationVersionInstanceVO("appVersionInstance1", "testState");
        appVersionInstance2 = new ApplicationVersionInstanceVO("appVersionInstance2", "testState2");
    }

    private void getBean() throws NamingException {
        InitialContext initialContext = new InitialContext();
        this.iSrUserFacade = (ISrUserFacade) initialContext.lookup("java:global/" + moduleName +
                "/SrFacadeBean!org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade");
        this.iSrApplicationFacade = (ISrApplicationFacade) initialContext.lookup("java:global/" + moduleName +
                "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationFacade");
        this.iSrApplicationVersionFacade = (ISrApplicationVersionFacade) initialContext.lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationVersionFacade");
        this.iSrApplicationVersionInstanceFacade = (ISrApplicationVersionInstanceFacade) initialContext.lookup(
                "java:global/" + moduleName + "/SrFacadeBean!" +
                        "org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationVersionInstanceFacade");
    }

    private void initDatabase() {
        //Create User
        UserVO user1 = new UserVO("user1", "testPassword", "testRole");
        user1 = iSrUserFacade.createUser(user1);
        this.userID = user1.getId();

        //Create Application
        Map<String,String> capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");
        List<String> requirementsList = new LinkedList<String>();
        requirementsList.add("requirement 1");
        requirementsList.add("requirement 2");
        ApplicationVO app1 =  new ApplicationVO(applicationID, "app1", "testDescription", requirementsList,
                capabilitiesList);
        app1 = iSrApplicationFacade.createApplication(userID, app1);
        this.applicationID = app1.getId();

        //Create ApplicationVersion
        ApplicationVersionVO appVersion1 = new ApplicationVersionVO("testLabel", requirementsList);
        appVersion1 = iSrApplicationVersionFacade.createApplicationVersion(applicationID, appVersion1);
        this.applicationVersionID = appVersion1.getVersionId();
    }

    @AfterClass
    public void cleanDatabase() {
        iSrApplicationVersionFacade.deleteApplicationVersion(applicationID, applicationVersionID);
        iSrApplicationFacade.deleteApplication(applicationID);
        iSrUserFacade.deleteUser(userID);
    }

    @Test
    public void testCreateApplicationVersionInstance() {
        //Create an ApplicationVersionInstance
        ApplicationVersionInstanceVO tmpAppVersionInstance1 =
                iSrApplicationVersionInstanceFacade.createApplicationVersionInstance(applicationID,
                        applicationVersionID, appVersionInstance1);
        Assert.assertNotEquals(tmpAppVersionInstance1.getId(), null);
        appVersionInstance1 = tmpAppVersionInstance1;

        //Create a second ApplicationVersionInstance
        ApplicationVersionInstanceVO tmpAppVersionInstance2 =
                iSrApplicationVersionInstanceFacade.createApplicationVersionInstance(applicationID,
                        applicationVersionID, appVersionInstance2);
        Assert.assertNotEquals(tmpAppVersionInstance2.getId(), null);
        appVersionInstance2 = tmpAppVersionInstance2;
    }

    @Test(dependsOnMethods = "testCreateApplicationVersionInstance")
    public void testGetApplicationVersionInstance() {
        //Get the first ApplicationVersionInstance
        ApplicationVersionInstanceVO tmpAppVersionInstance1 =
                iSrApplicationVersionInstanceFacade.getApplicationVersionInstance(applicationID, applicationVersionID,
                        appVersionInstance1.getId());
        Assert.assertEquals(appVersionInstance1.getId(), tmpAppVersionInstance1.getId());
        Assert.assertEquals(appVersionInstance1.getName(), tmpAppVersionInstance1.getName());
        Assert.assertEquals(appVersionInstance1.getState(), tmpAppVersionInstance1.getState());
    }

    @Test(dependsOnMethods = "testCreateApplicationVersionInstance")
    public void testUpdateApplicationVersionInstance() {
        //Update an ApplicationVersionInstance
        appVersionInstance2.setName("updateAppVersionInstance2");
        appVersionInstance2.setState("updateState");
        ApplicationVersionInstanceVO tmpAppVersionInstance2 =
                iSrApplicationVersionInstanceFacade.updateApplicationVersionInstance(applicationID,
                        applicationVersionID, appVersionInstance2);
        Assert.assertEquals(appVersionInstance2.getId(), tmpAppVersionInstance2.getId());
        Assert.assertEquals(appVersionInstance2.getName(), tmpAppVersionInstance2.getName());
        Assert.assertEquals(appVersionInstance2.getState(), tmpAppVersionInstance2.getState());
    }

    @Test(dependsOnMethods = "testUpdateApplicationVersionInstance")
    public void testFindApplicationVersionInstance() {
        //Find ApplicationVersionInstances of an ApplicationVersion
        List<ApplicationVersionInstanceVO> applicationVersionInstanceVOList =
                iSrApplicationVersionInstanceFacade.findApplicationVersionInstances(applicationID,
                        applicationVersionID);
        Assert.assertEquals(applicationVersionInstanceVOList.size(), 2);

        //Find ApplicationVersionInstances (with a specific name) of an ApplicationVersion
        applicationVersionInstanceVOList =  iSrApplicationVersionInstanceFacade.findApplicationVersionInstances(
                applicationID, applicationVersionID, appVersionInstance1.getName());
        Assert.assertEquals(applicationVersionInstanceVOList.size(), 1);
        Assert.assertEquals(applicationVersionInstanceVOList.get(0).getName(), appVersionInstance1.getName());
    }

    @Test(dependsOnMethods = "testFindApplicationVersionInstance")
    public void testDeleteApplicationVersionInstance() {
        //Delete ApplicationVersionInstances
        iSrApplicationVersionInstanceFacade.deleteApplicationVersionInstance(applicationID, applicationVersionID,
                appVersionInstance1.getId());
        List<ApplicationVersionInstanceVO> applicationVersionInstanceVOList =
                iSrApplicationVersionInstanceFacade.findApplicationVersionInstances(applicationID,
                        applicationVersionID);
        Assert.assertEquals(applicationVersionInstanceVOList.size(), 1);
        iSrApplicationVersionInstanceFacade.deleteApplicationVersionInstance(applicationID, applicationVersionID,
                appVersionInstance2.getId());
        applicationVersionInstanceVOList = iSrApplicationVersionInstanceFacade.findApplicationVersionInstances(
                applicationID, applicationVersionID);
        Assert.assertEquals(applicationVersionInstanceVOList.isEmpty(), true);
    }

    @Test(dependsOnMethods = "testDeleteApplicationVersionInstance")
    public void testDeployable() {
        DeployableVO deployable1 = new DeployableVO("deployable1", "url", true, new LinkedList<String>(),
                new HashMap<String, String>());
        ApplicationVersionInstanceVO appVersionInstance1 = new ApplicationVersionInstanceVO("appVersionInstance1",
                "testState");
        DeployableVO deployable2 = new DeployableVO("deployable2", "url2", true, new LinkedList<String>(),
                new HashMap<String, String>());
        appVersionInstance1.getDeployableList().add(deployable1);
        appVersionInstance1.getDeployableList().add(deployable2);
        ApplicationVersionInstanceVO tmpInstanceVO;
        tmpInstanceVO = iSrApplicationVersionInstanceFacade.createApplicationVersionInstance(applicationID,
                applicationVersionID, appVersionInstance1);
        Assert.assertEquals(appVersionInstance1.getDeployableList().get(0).getUrl(),
                tmpInstanceVO.getDeployableList().get(0).getUrl());
        Assert.assertEquals(appVersionInstance1.getDeployableList().size(),
                tmpInstanceVO.getDeployableList().size(), "Test Deployable creation");

        //Test update
        tmpInstanceVO.getDeployableList().get(1).setUrl("updateUrl2");
        DeployableVO deployable3 = new DeployableVO("deployable3", "url3", true, new LinkedList<String>(),
                new HashMap<String, String>());
        tmpInstanceVO.getDeployableList().add(deployable3);
        tmpInstanceVO.getDeployableList().remove(0);
        ApplicationVersionInstanceVO updateInstanceVO =
                iSrApplicationVersionInstanceFacade.updateApplicationVersionInstance(applicationID,
                        applicationVersionID, tmpInstanceVO);

        Assert.assertEquals(updateInstanceVO.getDeployableList().size(),
                tmpInstanceVO.getDeployableList().size(), "Test Deployable update");
        Assert.assertEquals(updateInstanceVO.getDeployableList().get(0).getUrl(),
                tmpInstanceVO.getDeployableList().get(0).getUrl(), "Test Deployable update");

        iSrApplicationVersionInstanceFacade.deleteApplicationVersionInstance(applicationID, applicationVersionID,
                tmpInstanceVO.getId());
    }

    @Test(dependsOnMethods = "testDeleteApplicationVersionInstance")
    public void testPaasArtefact() {
        PaasArtefactVO paasArtefactVO = new PaasArtefactVO();
        ApplicationVersionInstanceVO appVersionInstance1 = new ApplicationVersionInstanceVO("appVersionInstance1",
                "testState");
        appVersionInstance1.getPaasArtefactList().add(paasArtefactVO);

        DeployableVO deployable1 = new DeployableVO("deployable1", "url", true, new LinkedList<String>(),
                new HashMap<String, String>());
        DeployableVO deployable2 = new DeployableVO("deployable2", "url2", true, new LinkedList<String>(),
                new HashMap<String, String>());
        appVersionInstance1.getDeployableList().add(deployable1);
        appVersionInstance1.getDeployableList().add(deployable2);

        ApplicationVersionInstanceVO tmpInstanceVO =
                iSrApplicationVersionInstanceFacade.createApplicationVersionInstance(applicationID,
                        applicationVersionID, appVersionInstance1);
        Assert.assertEquals(tmpInstanceVO.getPaasArtefactList().get(0).getInstanceId(),
                tmpInstanceVO.getId());

        iSrApplicationVersionInstanceFacade.deleteApplicationVersionInstance(applicationID, applicationVersionID,
                tmpInstanceVO.getId());
    }

}
