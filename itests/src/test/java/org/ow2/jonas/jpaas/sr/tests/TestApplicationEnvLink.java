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
import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationEnvLink;
import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationVersionFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationVersionInstanceFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrEnvironmentFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVO;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVersionInstanceVO;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVersionVO;
import org.ow2.jonas.jpaas.sr.facade.vo.EnvironmentVO;
import org.ow2.jonas.jpaas.sr.facade.vo.UserVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * ApplicationEnvLink test case
 * @author David Richard
 */

@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class TestApplicationEnvLink extends SetupTest {

    /**
     * User Facade
     */
    @Inject
    private ISrUserFacade iSrUserFacade;

    /**
     * Application Facade
     */
    @Inject
    private ISrApplicationFacade iSrApplicationFacade ;

    /**
     * ApplicationVersion Facade
     */
    @Inject
    private ISrApplicationVersionFacade iSrApplicationVersionFacade;

    /**
     * ApplicationVersionInstance Facade
     */
    @Inject
    private ISrApplicationVersionInstanceFacade iSrApplicationVersionInstanceFacade;

    /**
     * ApplicationEnvLink Facade
     */
    @Inject
    private ISrApplicationEnvLink iSrApplicationEnvLink;

    /**
     * Environment Facade
     */
    @Inject
    private ISrEnvironmentFacade iSrEnvironmentFacade;

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
     * ID of the ApplicationVersionInstance 1
     */
    private String appVersionInstance1ID;

    /**
     * ID of the ApplicationVersionInstance 2
     */
    private String appVersionInstance2ID;

    /**
     * ID of the Environment
     */
    private String envID;


    @Test
    public void initApplicationEnvLink() throws NamingException {
        initDatabase();
    }


    private void initDatabase() {
        //Create User
        UserVO user1 = new UserVO("user1ApplicationEnvLink", "testPassword", "testRole");
        user1 = iSrUserFacade.createUser(user1);
        this.userID = user1.getId();

        //Create Application
        Map<String,String> capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");
        List<String> requirementsList = new LinkedList<String>();
        requirementsList.add("requirement 1");
        requirementsList.add("requirement 2");
        ApplicationVO app1 =  new ApplicationVO(applicationID, "app1ApplicationEnvLink", "testDescription", requirementsList,
                capabilitiesList);
        app1 = iSrApplicationFacade.createApplication(userID, app1);
        this.applicationID = app1.getId();

        //Create ApplicationVersion
        ApplicationVersionVO appVersion1 = new ApplicationVersionVO("testLabel", requirementsList);
        appVersion1 = iSrApplicationVersionFacade.createApplicationVersion(applicationID, appVersion1);
        this.applicationVersionID = appVersion1.getVersionId();

        //Create an ApplicationVersionInstance
        ApplicationVersionInstanceVO appVersionInstance1 = new ApplicationVersionInstanceVO("appVersionInstance1ApplicationEnvLink", "testState");
        appVersionInstance1 = iSrApplicationVersionInstanceFacade.createApplicationVersionInstance(applicationID,
                applicationVersionID, appVersionInstance1);
        appVersionInstance1ID = appVersionInstance1.getId();

        //Create a second ApplicationVersionInstance
        ApplicationVersionInstanceVO appVersionInstance2 = new ApplicationVersionInstanceVO("appVersionInstance2ApplicationEnvLink", "testState2");
        appVersionInstance2 = iSrApplicationVersionInstanceFacade.createApplicationVersionInstance(applicationID,
                applicationVersionID, appVersionInstance2);
        appVersionInstance2ID = appVersionInstance2.getId();

        //Create an Environment
        EnvironmentVO env =  new EnvironmentVO("env1ApplicationEnvLink", "testDescription", "testState");
        env =  iSrEnvironmentFacade.createEnvironment(userID, env);
        envID = env.getId();

    }


    @Test(dependsOnMethods="initApplicationEnvLink")
    public void testApplicationEnvLink() {
        iSrApplicationEnvLink.addApplicationEnvLink(applicationID, applicationVersionID, appVersionInstance1ID,
                envID);

        iSrApplicationEnvLink.addApplicationEnvLink(applicationID, applicationVersionID, appVersionInstance2ID,
                envID);
    }

    @Test(dependsOnMethods = "testApplicationEnvLink")
    public void testFindApplicationVersionInstancesByEnv() {
        List<ApplicationVersionInstanceVO> applicationVersionInstanceVOList =
                iSrApplicationEnvLink.findApplicationVersionInstancesByEnv(envID);
        Assert.assertEquals(applicationVersionInstanceVOList.size(), 2);
        Assert.assertEquals(applicationVersionInstanceVOList.get(0).getId(), appVersionInstance1ID);
        Assert.assertEquals(applicationVersionInstanceVOList.get(1).getId(), appVersionInstance2ID);
    }

    @Test(dependsOnMethods = "testFindApplicationVersionInstancesByEnv")
    public void testGetEnvByApplicationVersionInstance() {
        EnvironmentVO environmentVO = iSrApplicationEnvLink.getEnvByApplicationVersionInstance(applicationID,
                applicationVersionID, appVersionInstance1ID);
        Assert.assertEquals(environmentVO.getId(), envID);
        EnvironmentVO environment2VO = iSrApplicationEnvLink.getEnvByApplicationVersionInstance(applicationID,
                applicationVersionID, appVersionInstance2ID);
        Assert.assertEquals(environmentVO.toString(), environment2VO.toString());
    }

    @Test(dependsOnMethods = "testGetEnvByApplicationVersionInstance")
    public void testRemoveApplicationEnvLink() {
        iSrApplicationEnvLink.removeApplicationEnvLink(applicationID,
                applicationVersionID, appVersionInstance1ID);
        List<ApplicationVersionInstanceVO> applicationVersionInstanceVOList =
                iSrApplicationEnvLink.findApplicationVersionInstancesByEnv(envID);
        Assert.assertEquals(applicationVersionInstanceVOList.size(), 1);

        iSrApplicationEnvLink.removeApplicationEnvLink(applicationID,
                applicationVersionID, appVersionInstance2ID);
        applicationVersionInstanceVOList =
                iSrApplicationEnvLink.findApplicationVersionInstancesByEnv(envID);
        Assert.assertEquals(applicationVersionInstanceVOList.isEmpty(), true);
    }

}
