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
import org.ow2.jonas.jpaas.sr.facade.api.ISrEnvironmentFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrEnvironmentPaasResourceLink;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.EnvironmentVO;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;
import org.ow2.jonas.jpaas.sr.facade.vo.NodeTemplateVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasResourceVO;
import org.ow2.jonas.jpaas.sr.facade.vo.TopologyTemplateVO;
import org.ow2.jonas.jpaas.sr.facade.vo.UserVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * EnvironmentPaasResourceLink test case
 * @author David Richard
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class TestEnvironmentPaasResourceLink extends SetupTest {

    /**
     * User ID
     */
    private String userID;

    /**
     * User Facade
     */
    @Inject
    private ISrUserFacade iSrUserFacade;

    /**
     * PaasJonasContainer Facade
     */
    @Inject
    private ISrPaasJonasContainerFacade iSrPaasJonasContainerFacade;

    /**
     * Environment Facade
     */
    @Inject
    private ISrEnvironmentFacade iSrEnvironmentFacade;

    /**
     * EnvironmentPaasResourceLink Facade
     */
    @Inject
    private ISrEnvironmentPaasResourceLink iSrEnvironmentPaasResourceLink;

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

    @Test
    public void initEnvironmentPaasResourceLink() throws NamingException {

        UserVO user1 = new UserVO("user1EnvironmentPaasResourceLink", "testPassword", "testRole");
        user1 = iSrUserFacade.createUser(user1);
        this.userID = user1.getId();

        Map<String,String> capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        List<Integer> usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        jonas1 = new JonasVO("jonas1EnvironmentPaasResourceLink", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas2 = new JonasVO("jonas2EnvironmentPaasResourceLink", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas3 = new JonasVO("jonas3EnvironmentPaasResourceLink", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        env1 =  new EnvironmentVO("env1EnvironmentPaasResourceLink", "testDescription", "testState");
        nodeTemplate1 = new NodeTemplateVO("n1EnvironmentPaasResourceLink", "nodeTemplate1", "configuration", new LinkedList<String>(),
                new HashMap<String, String>(), 1, 5, 3);
        nodeTemplate2 = new NodeTemplateVO("n2EnvironmentPaasResourceLink", "nodeTemplate2", "configuration", new LinkedList<String>(),
                new HashMap<String, String>(), 1, 5, 3);
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


    @Test(dependsOnMethods="initEnvironmentPaasResourceLink")
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
