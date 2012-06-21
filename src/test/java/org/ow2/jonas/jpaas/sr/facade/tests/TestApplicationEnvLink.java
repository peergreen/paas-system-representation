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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.LinkedList;
import java.util.List;

/**
 * ApplicationEnvLink test case
 * @author David Richard
 */
public class TestApplicationEnvLink {

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
     * ApplicationEnvLink Facade
     */
    private ISrApplicationEnvLink iSrApplicationEnvLink = null;

    /**
     * Environment Facade
     */
    private ISrEnvironmentFacade iSrEnvironmentFacade = null;

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


    @BeforeClass
    public void init() throws NamingException {
        getBean();
        initDatabase();
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
        this.iSrApplicationVersionInstanceFacade = (ISrApplicationVersionInstanceFacade) initialContext.lookup(
                "java:global/system-representation-1.1.1-SNAPSHOT/SrFacadeBean!" +
                        "org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationVersionInstanceFacade");
        this.iSrEnvironmentFacade = (ISrEnvironmentFacade) initialContext.lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrEnvironmentFacade");
        this.iSrApplicationEnvLink = (ISrApplicationEnvLink) initialContext.lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationEnvLink");
    }

    private void initDatabase() {
        //Create User
        UserVO user1 = new UserVO("user1", "testPassword", "testRole");
        user1 = iSrUserFacade.createUser(user1);
        this.userID = user1.getId();

        //Create Application
        List<String> capabilitiesList = new LinkedList<String>();
        capabilitiesList.add("capability 1");
        capabilitiesList.add("capability 2");
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

        //Create an ApplicationVersionInstance
        ApplicationVersionInstanceVO appVersionInstance1 = new ApplicationVersionInstanceVO("appVersionInstance1", "testState");
        appVersionInstance1 = iSrApplicationVersionInstanceFacade.createApplicationVersionInstance(applicationID,
                applicationVersionID, appVersionInstance1);
        appVersionInstance1ID = appVersionInstance1.getId();

        //Create a second ApplicationVersionInstance
        ApplicationVersionInstanceVO appVersionInstance2 = new ApplicationVersionInstanceVO("appVersionInstance2", "testState2");
        appVersionInstance2 = iSrApplicationVersionInstanceFacade.createApplicationVersionInstance(applicationID,
                applicationVersionID, appVersionInstance2);
        appVersionInstance2ID = appVersionInstance2.getId();

        //Create an Environment
        EnvironmentVO env =  new EnvironmentVO("env1", "testDescription", "testState");
        env =  iSrEnvironmentFacade.createEnvironment(userID, env);
        envID = env.getId();

    }

    @AfterClass
    public void cleanDatabase() {
        iSrApplicationVersionInstanceFacade.deleteApplicationVersionInstance(applicationID, applicationVersionID,
                appVersionInstance1ID);
        iSrApplicationVersionInstanceFacade.deleteApplicationVersionInstance(applicationID, applicationVersionID,
                appVersionInstance2ID);
        iSrApplicationVersionFacade.deleteApplicationVersion(applicationID, applicationVersionID);
        iSrApplicationFacade.deleteApplication(applicationID);
        iSrUserFacade.deleteUser(userID);
    }

    @Test
    public void testApplicationDeploymentLink() {
        iSrApplicationEnvLink.addApplicationDeploymentLink(applicationID, applicationVersionID, appVersionInstance1ID,
                envID);

        iSrApplicationEnvLink.addApplicationDeploymentLink(applicationID, applicationVersionID, appVersionInstance2ID,
                envID);
    }

    @Test(dependsOnMethods = "testApplicationDeploymentLink")
    public void testFindApplicationVersionInstancesByEnv() {
        List<ApplicationVersionInstanceVO> applicationVersionInstanceVOList =
                iSrApplicationEnvLink.findApplicationVersionInstancesByEnv(envID);
        Assert.assertEquals(applicationVersionInstanceVOList.size(), 2);
        Assert.assertEquals(applicationVersionInstanceVOList.get(0).getId(), appVersionInstance1ID);
        Assert.assertEquals(applicationVersionInstanceVOList.get(1).getId(), appVersionInstance2ID);
    }

    @Test(dependsOnMethods = "testApplicationDeploymentLink")
    public void testGetEnvByApplicationVersionInstance() {
        EnvironmentVO environmentVO = iSrApplicationEnvLink.getEnvByApplicationVersionInstance(applicationID,
                applicationVersionID, appVersionInstance1ID);
        Assert.assertEquals(environmentVO.getId(), envID);
        EnvironmentVO environment2VO = iSrApplicationEnvLink.getEnvByApplicationVersionInstance(applicationID,
                applicationVersionID, appVersionInstance2ID);
        Assert.assertEquals(environmentVO.toString(), environment2VO.toString());
    }

    @Test(dependsOnMethods = { "testApplicationDeploymentLink", "testFindApplicationVersionInstancesByEnv",
            "testGetEnvByApplicationVersionInstance" })
    public void testRemoveApplicationDeploymentLink() {
        iSrApplicationEnvLink.removeApplicationDeploymentLink(applicationID,
                applicationVersionID, appVersionInstance1ID);
        List<ApplicationVersionInstanceVO> applicationVersionInstanceVOList =
                iSrApplicationEnvLink.findApplicationVersionInstancesByEnv(envID);
        Assert.assertEquals(applicationVersionInstanceVOList.size(), 1);

        iSrApplicationEnvLink.removeApplicationDeploymentLink(applicationID,
                applicationVersionID, appVersionInstance2ID);
        applicationVersionInstanceVOList =
                iSrApplicationEnvLink.findApplicationVersionInstancesByEnv(envID);
        Assert.assertEquals(applicationVersionInstanceVOList.isEmpty(), true);
    }

}
