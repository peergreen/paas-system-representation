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

import org.ow2.jonas.jpaas.sr.facade.api.ISrEnvironmentPaasResourceLink;
import org.ow2.jonas.jpaas.sr.facade.api.ISrEnvironmentFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.EnvironmentVO;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;
import org.ow2.jonas.jpaas.sr.facade.vo.NodeTemplateVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasResourceVO;
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
import java.util.Map;
import java.util.Properties;

/**
 * EnvironmentPaasResourceLink test case
 * @author David Richard
 */
public class TestEnvironmentPaasResourceLink {
    /**
     * User ID
     */
    private String userID;

    /**
     * User Facade
     */
    private ISrUserFacade iSrUserFacade = null;

    /**
     * PaasJonasContainer Facade
     */
    private ISrPaasJonasContainerFacade iSrPaasJonasContainerFacade = null;

    /**
     * Environment Facade
     */
    private ISrEnvironmentFacade iSrEnvironmentFacade = null;

    /**
     * EnvironmentPaasResourceLink Facade
     */
    private ISrEnvironmentPaasResourceLink iSrEnvironmentPaasResourceLink = null;

    /**
     * Jonas 1 value object
     */
    private JonasVO jonas1;

    /**
     * Jonas 1 value object
     */
    private JonasVO jonas2;

    /**
     * Jonas 1 value object
     */
    private JonasVO jonas3;

    /**
     * Environment 1 value object
     */
    private EnvironmentVO env1;

    /**
     * NodeTemplate 1 value object
     */
    private NodeTemplateVO nodeTemplate1 ;

    /**
     * NodeTemplate 2 value object
     */
    private NodeTemplateVO nodeTemplate2;

    /**
     * Name of the module for the lookup
     */
    private final String moduleName = System.getProperty("module.name");


    @BeforeClass
    public void init() throws NamingException {
        getBean();
        initDatabase();
    }


    private void getBean() throws NamingException {
        InitialContext initialContext = new InitialContext();
        this.iSrUserFacade = (ISrUserFacade) initialContext.lookup("java:global/" + moduleName +
                "/SrFacadeBean!org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade");
        this.iSrPaasJonasContainerFacade = (ISrPaasJonasContainerFacade) initialContext.lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade");
        this.iSrEnvironmentFacade = (ISrEnvironmentFacade) initialContext.lookup("java:global/" + moduleName +
                "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrEnvironmentFacade");
        this.iSrEnvironmentPaasResourceLink = (ISrEnvironmentPaasResourceLink) initialContext.lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrEnvironmentPaasResourceLink");
    }

    private void initDatabase() {
        UserVO user1 = new UserVO("user1", "testPassword", "testRole");
        user1 = iSrUserFacade.createUser(user1);
        this.userID = user1.getId();

        Map<String,String> capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        List<Integer> usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        jonas1 = new JonasVO("jonas1", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas2 = new JonasVO("jonas2", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas3 = new JonasVO("jonas3", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        env1 =  new EnvironmentVO("env1", "testDescription", "testState");
        nodeTemplate1 = new NodeTemplateVO("nodeTemplate1", new LinkedList<String>(),
                new LinkedList<Properties>(), 1, 5, 3);
        nodeTemplate2 = new NodeTemplateVO("nodeTemplate2", new LinkedList<String>(),
                new LinkedList<Properties>(), 1, 5, 3);
        TopologyTemplateVO topologyTemplateVO = new TopologyTemplateVO();
        topologyTemplateVO.getNodeTemplateList().add(nodeTemplate1);
        topologyTemplateVO.getNodeTemplateList().add(nodeTemplate2);
        env1.setTopologyTemplate(topologyTemplateVO);

        jonas1 = iSrPaasJonasContainerFacade.createJonasContainer(jonas1);
        jonas2 = iSrPaasJonasContainerFacade.createJonasContainer(jonas2);
        jonas3 = iSrPaasJonasContainerFacade.createJonasContainer(jonas3);
        env1 = iSrEnvironmentFacade.createEnvironment(userID, env1);
        topologyTemplateVO = env1.getTopologyTemplate();
        nodeTemplate1 = topologyTemplateVO.getNodeTemplateList().get(0);
        nodeTemplate2 = topologyTemplateVO.getNodeTemplateList().get(1);

    }

    @AfterClass
    public void cleanDatabase() {
        iSrPaasJonasContainerFacade.deleteJonasContainer(jonas1.getId());
        iSrPaasJonasContainerFacade.deleteJonasContainer(jonas2.getId());
        iSrPaasJonasContainerFacade.deleteJonasContainer(jonas3.getId());
        iSrEnvironmentFacade.deleteEnvironment(env1.getId());
        iSrUserFacade.deleteUser(userID);
    }

    @Test
    public void testAddPaasResourceNodeLink() {
        iSrEnvironmentPaasResourceLink.addPaasResourceNodeLink(nodeTemplate1.getId(), jonas1.getId());
        iSrEnvironmentPaasResourceLink.addPaasResourceNodeLink(nodeTemplate1.getId(), jonas2.getId());
        iSrEnvironmentPaasResourceLink.addPaasResourceNodeLink(nodeTemplate2.getId(), jonas3.getId());
        iSrEnvironmentPaasResourceLink.addPaasResourceNodeLink(nodeTemplate1.getId(), jonas3.getId());
    }

    @Test(dependsOnMethods="testAddPaasResourceNodeLink")
    public void testFindPaasResourcesByNode() {
        List<PaasResourceVO> paasResourceVOList = iSrEnvironmentPaasResourceLink.findPaasResourcesByNode
                (nodeTemplate1.getId());
        Assert.assertEquals(paasResourceVOList.size(), 3);
        paasResourceVOList = iSrEnvironmentPaasResourceLink.findPaasResourcesByNode(nodeTemplate2.getId());
        Assert.assertEquals(paasResourceVOList.size(), 1);
        Assert.assertEquals(paasResourceVOList.get(0).getId(), jonas3.getId());
    }

    @Test(dependsOnMethods="testFindPaasResourcesByNode")
    public void testFindNodesByPaasResource() {
        List<NodeTemplateVO> nodeTemplateVOList =
                iSrEnvironmentPaasResourceLink.findNodesByPaasResource(jonas3.getId());
        Assert.assertEquals(nodeTemplateVOList.size(), 2);
        Assert.assertEquals(nodeTemplateVOList.get(0).getName(), nodeTemplate2.getName());
    }

    @Test(dependsOnMethods = "testFindNodesByPaasResource")
    public void testRemovePaasResourceNodeLink() {
        iSrEnvironmentPaasResourceLink.removePaasResourceNodeLink(nodeTemplate1.getId(), jonas1.getId());
        List<PaasResourceVO> paasResourceVOList =
                iSrEnvironmentPaasResourceLink.findPaasResourcesByNode(nodeTemplate1.getId());
        Assert.assertEquals(paasResourceVOList.size(), 2);
        List<NodeTemplateVO> nodeTemplateVOList =
                iSrEnvironmentPaasResourceLink.findNodesByPaasResource(jonas1.getId());
               Assert.assertEquals(nodeTemplateVOList.isEmpty(), true);

        iSrEnvironmentPaasResourceLink.removePaasResourceNodeLink(nodeTemplate2.getId(), jonas3.getId());
        paasResourceVOList = iSrEnvironmentPaasResourceLink.findPaasResourcesByNode(nodeTemplate2.getId());
        Assert.assertEquals(paasResourceVOList.isEmpty(), true);
        nodeTemplateVOList = iSrEnvironmentPaasResourceLink.findNodesByPaasResource(jonas3.getId());
               Assert.assertEquals(nodeTemplateVOList.size(), 1);
    }
}
