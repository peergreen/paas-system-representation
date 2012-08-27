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

import org.ow2.jonas.jpaas.sr.facade.api.ISrEnvironmentFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.ConnectorTemplateVO;
import org.ow2.jonas.jpaas.sr.facade.vo.ContainerNodeTemplateVO;
import org.ow2.jonas.jpaas.sr.facade.vo.DatabaseNodeTemplateVO;
import org.ow2.jonas.jpaas.sr.facade.vo.DatasourceTemplateVO;
import org.ow2.jonas.jpaas.sr.facade.vo.EnvironmentVO;
import org.ow2.jonas.jpaas.sr.facade.vo.IaasComputeNodeTemplateVO;
import org.ow2.jonas.jpaas.sr.facade.vo.NodeTemplateVO;
import org.ow2.jonas.jpaas.sr.facade.vo.RouterNodeTemplateVO;
import org.ow2.jonas.jpaas.sr.facade.vo.TopologyTemplateVO;
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

/**
 * Environment Facade test case
 * @author David Richard
 */
public class TestEnvironmentFacade {

    /**
     * User Facade
     */
    private ISrUserFacade iSrUserFacade = null;

    /**
     * Environment Facade
     */
    private ISrEnvironmentFacade iSrEnvironmentFacade = null;

    /**
     * User ID
     */
    private String userID;

    /**
     * Environment 1 value object
     */
    private EnvironmentVO env1;

    /**
     * Environment 2 value object
     */
    private EnvironmentVO env2;

    /**
     * Name of the module for the lookup
     */
    private final String moduleName = System.getProperty("module.name");

    @BeforeClass
    public void init() throws NamingException {
        getBean();
        initDatabase();

        env1 =  new EnvironmentVO("env1", "testDescription", "testState");
        env2 =  new EnvironmentVO("env2", "testDescription2", "testState2");
    }

    private void getBean() throws NamingException {
        InitialContext initialContext = new InitialContext();
        this.iSrUserFacade = (ISrUserFacade) initialContext.lookup("java:global/" + moduleName +
                "/SrFacadeBean!org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade");
        this.iSrEnvironmentFacade = (ISrEnvironmentFacade) initialContext.lookup("java:global/" + moduleName +
                "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrEnvironmentFacade");
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
    public void testCreateEnvironment() {
        //Create an Environment
        EnvironmentVO tmpEnv1 =  iSrEnvironmentFacade.createEnvironment(userID, env1);
        Assert.assertNotEquals(tmpEnv1.getId(), null);
        env1 = tmpEnv1;

        //Create a Second Environment
        EnvironmentVO tmpEnv2 =  iSrEnvironmentFacade.createEnvironment(userID, env2);
        Assert.assertNotEquals(tmpEnv2.getId(), null);
        env2 = tmpEnv2;
    }

    @Test(dependsOnMethods = "testCreateEnvironment")
    public void testGetEnvironment() {
        //Get the First Environment
        EnvironmentVO tmpEnv1 = iSrEnvironmentFacade.getEnvironment(env1.getId());
        Assert.assertEquals(env1.getId(), tmpEnv1.getId());
        Assert.assertEquals(env1.getName(), tmpEnv1.getName());
        Assert.assertEquals(env1.getDescription(), tmpEnv1.getDescription());
        Assert.assertEquals(env1.getState(), tmpEnv1.getState());
    }

    @Test(dependsOnMethods = "testCreateEnvironment")
    public void testUpdateEnvironment() {
        //Update First Environment
        env1.setName("updateEnv1");
        env1.setDescription("updateDescription");
        env1.setState("updateState");
        EnvironmentVO tmpEnv1 = iSrEnvironmentFacade.updateEnvironment(env1);
        Assert.assertEquals(env1.getId(), tmpEnv1.getId());
        Assert.assertEquals(env1.getName(), tmpEnv1.getName());
        Assert.assertEquals(env1.getDescription(), tmpEnv1.getDescription());
        Assert.assertEquals(env1.getState(), tmpEnv1.getState());
    }

    @Test(dependsOnMethods = "testUpdateEnvironment")
    public void testFindEnvironment() {
        //Find Environments
        List<EnvironmentVO> environmentList = iSrEnvironmentFacade.findEnvironments(userID);
        Assert.assertEquals(environmentList.size(), 2);

        //Find Environments with name
        environmentList = iSrEnvironmentFacade.findEnvironments(userID, env2.getName());
        Assert.assertEquals(environmentList.size(), 1);
        Assert.assertEquals(environmentList.get(0).getName(), env2.getName());
    }

    @Test(dependsOnMethods = "testFindEnvironment")
    public void testDeleteEnvironment() {
        //Delete environments
        iSrEnvironmentFacade.deleteEnvironment(env1.getId());
        List<EnvironmentVO> environmentList = iSrEnvironmentFacade.findEnvironments(userID);
        Assert.assertEquals(environmentList.size(), 1);
        iSrEnvironmentFacade.deleteEnvironment(env2.getId());
        environmentList = iSrEnvironmentFacade.findEnvironments(userID);
        Assert.assertEquals(environmentList.isEmpty(), true);
    }

    @Test(dependsOnMethods = "testDeleteEnvironment")
    public void testComplexEnvironment() {
        ConnectorTemplateVO connectorTemplate1 = new ConnectorTemplateVO("connectorTemplate1");
        DatasourceTemplateVO datasourceTemplate1 = new DatasourceTemplateVO("datasourceTemplate1");
        NodeTemplateVO nodeTemplate1 = new NodeTemplateVO("nodeTemplate1", new LinkedList<String>(),
                new HashMap<String, String>(), 1, 5, 3);
        RouterNodeTemplateVO routerNodeTemplate1 = new RouterNodeTemplateVO("routerNodeTemplate1",
                new LinkedList<String>(), new HashMap<String, String>(), 1, 5, 3);
        ContainerNodeTemplateVO containerNodeTemplate1 = new ContainerNodeTemplateVO("containerNodeTemplate1",
                new LinkedList<String>(), new HashMap<String, String>(), 1, 5, 3);
        DatabaseNodeTemplateVO databaseNodeTemplate1 = new DatabaseNodeTemplateVO("databaseNodeTemplate1",
                new LinkedList<String>(), new HashMap<String, String>(), 1, 5, 3);
        IaasComputeNodeTemplateVO iaasComputeNodeTemplate1 = new IaasComputeNodeTemplateVO("iaasComputeNodeTemplate1",
                new LinkedList<String>(), new HashMap<String, String>(), 1, 5, 3);
        TopologyTemplateVO topologyTemplateVO = new TopologyTemplateVO();
        topologyTemplateVO.getRelationshipTemplateList().add(connectorTemplate1);
        topologyTemplateVO.getRelationshipTemplateList().add(datasourceTemplate1);
        topologyTemplateVO.getNodeTemplateList().add(nodeTemplate1);
        topologyTemplateVO.getNodeTemplateList().add(routerNodeTemplate1);
        topologyTemplateVO.getNodeTemplateList().add(containerNodeTemplate1);
        topologyTemplateVO.getNodeTemplateList().add(databaseNodeTemplate1);
        topologyTemplateVO.getNodeTemplateList().add(iaasComputeNodeTemplate1);
        EnvironmentVO env1 = new EnvironmentVO("env1", "testDescription", "testState");
        env1.setTopologyTemplate(topologyTemplateVO);
        EnvironmentVO tmpEnv1 =  iSrEnvironmentFacade.createEnvironment(userID, env1);
        Assert.assertEquals(tmpEnv1.getTopologyTemplate().getRelationshipTemplateList().get(0).getName(),
                connectorTemplate1.getName(), "Test ConnectorTemplate");
        Assert.assertEquals(tmpEnv1.getTopologyTemplate().getRelationshipTemplateList().get(1).getName(),
                datasourceTemplate1.getName(), "Test DatasourceTemplate");
        Assert.assertEquals(tmpEnv1.getTopologyTemplate().getNodeTemplateList().get(0).getName(),
                nodeTemplate1.getName(), "Test NodeTemplate");
        Assert.assertEquals(tmpEnv1.getTopologyTemplate().getNodeTemplateList().get(1).getName(),
                routerNodeTemplate1.getName(), "Test RouterNodeTemplate");
        Assert.assertEquals(tmpEnv1.getTopologyTemplate().getNodeTemplateList().get(2).getName(),
                containerNodeTemplate1.getName(), "Test ContainerNodeTemplate");
        Assert.assertEquals(tmpEnv1.getTopologyTemplate().getNodeTemplateList().get(3).getName(),
                databaseNodeTemplate1.getName(), "Test DatabaseNodeTemplate");
        Assert.assertEquals(tmpEnv1.getTopologyTemplate().getNodeTemplateList().get(4).getName(),
                iaasComputeNodeTemplate1.getName(), "Test IaasComputeNodeTemplate");

        //Test update
        tmpEnv1.getTopologyTemplate().getRelationshipTemplateList().get(0).setName("updateName");
        DatasourceTemplateVO datasourceTemplate2 = new DatasourceTemplateVO("datasourceTemplate2");
        tmpEnv1.getTopologyTemplate().getRelationshipTemplateList().add(datasourceTemplate2);
        tmpEnv1.getTopologyTemplate().getRelationshipTemplateList().remove(1);
        tmpEnv1.getTopologyTemplate().getNodeTemplateList().get(0).setName("updateName");
        NodeTemplateVO nodeTemplate2 = new NodeTemplateVO("nodeTemplate2", new LinkedList<String>(),
                new HashMap<String, String>(), 1, 5, 3);
        tmpEnv1.getTopologyTemplate().getNodeTemplateList().add(nodeTemplate2);
        tmpEnv1.getTopologyTemplate().getNodeTemplateList().remove(1);
        EnvironmentVO updateEnv = iSrEnvironmentFacade.updateEnvironment(tmpEnv1);

        Assert.assertEquals(updateEnv.getTopologyTemplate().getRelationshipTemplateList().get(0).getName(),
                tmpEnv1.getTopologyTemplate().getRelationshipTemplateList().get(0).getName(),
                "Test Update Relationship");
        Assert.assertEquals(updateEnv.getTopologyTemplate().getRelationshipTemplateList().size(),
                tmpEnv1.getTopologyTemplate().getRelationshipTemplateList().size(),
                "Test Update Relationship");
        Assert.assertEquals(updateEnv.getTopologyTemplate().getNodeTemplateList().get(0).getName(),
                tmpEnv1.getTopologyTemplate().getNodeTemplateList().get(0).getName(),
                "Test Update Node");
        Assert.assertEquals(updateEnv.getTopologyTemplate().getNodeTemplateList().size(),
                tmpEnv1.getTopologyTemplate().getNodeTemplateList().size(),
                "Test Update Node");

        iSrEnvironmentFacade.deleteEnvironment(tmpEnv1.getId());
    }

}
