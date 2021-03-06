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

package org.ow2.jonas.jpaas.sr.tests;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;
import org.ops4j.pax.exam.testng.listener.PaxExam;
import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationVersionFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVO;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVersionVO;
import org.ow2.jonas.jpaas.sr.facade.vo.DeployableVO;
import org.ow2.jonas.jpaas.sr.facade.vo.UserVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * ApplicationVersion Facade test case
 * @author David Richard
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class TestApplicationVersionFacade extends SetupTest {

    /**
     * User Facade
     */
    @Inject
    private ISrUserFacade iSrUserFacade;

    /**
     * Application Facade
     */
    @Inject
    private ISrApplicationFacade iSrApplicationFacade;

    /**
     * ApplicationVersion Facade
     */
    @Inject
    private ISrApplicationVersionFacade iSrApplicationVersionFacade;

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


    @Test
    public void initApplicationVersionFacade() throws NamingException {
        initDatabase();
        appVersion1 = new ApplicationVersionVO("testLabelApplicationVersionFacade", requirementsList);
        appVersion2 = new ApplicationVersionVO("testLabel2ApplicationVersionFacade", requirementsList);
    }


    private void initDatabase() {
        //Create User
        UserVO user1 = new UserVO("user1ApplicationVersionFacade", "testPassword", "testRole");
        user1 = iSrUserFacade.createUser(user1);
        this.userID = user1.getId();

        //Create Application
        Map<String,String> capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");
        requirementsList = new LinkedList<String>();
        requirementsList.add("requirement 1");
        requirementsList.add("requirement 2");
        ApplicationVO app1 =  new ApplicationVO(applicationID, "app1ApplicationVersionFacade", "testDescription", requirementsList,
                capabilitiesList);
        app1 = iSrApplicationFacade.createApplication(userID, app1);
        this.applicationID = app1.getId();
    }


    @Test(dependsOnMethods="initApplicationVersionFacade")
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
        DeployableVO deployable1 = new DeployableVO("deployable1", "url", true, requirementsList,
                new HashMap<String, String>());
        DeployableVO deployable2 = new DeployableVO("deployable2", "url2", true, requirementsList,
                new HashMap<String, String>());
        appVersion1.getDeployableList().add(deployable1);
        appVersion1.getDeployableList().add(deployable2);
        ApplicationVersionVO tmpAppVersion1;
        tmpAppVersion1 = iSrApplicationVersionFacade.createApplicationVersion(applicationID, appVersion1);
        Assert.assertEquals(appVersion1.getDeployableList().get(0).getUrl(),
                tmpAppVersion1.getDeployableList().get(0).getUrl(), "Test Deployable creation");
        //Test update
        tmpAppVersion1.getDeployableList().get(1).setUrl("updateUrl2");
        DeployableVO deployable3 = new DeployableVO("deployable3", "url3", true, requirementsList,
                new HashMap<String, String>());
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

    @Test(dependsOnMethods="testDeployable")
    public void cleanDatabase() {
        iSrApplicationFacade.deleteApplication(applicationID);
        iSrUserFacade.deleteUser(userID);
    }


}
