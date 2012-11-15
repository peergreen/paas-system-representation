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

import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasResourcePaasAgentLink;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasAgentFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasAgentVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasResourceVO;
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
 * PaasResourcePaasAgentLink test case
 * @author David Richard
 */
public class TestPaasResourcePaasAgentLink {
    /**
     * PaasJonasContainer Facade
     */
    private ISrPaasJonasContainerFacade iSrPaasJonasContainerFacade = null;

    /**
     * PaasAgent Facade
     */
    private ISrPaasAgentFacade iSrPaasAgentFacade = null;

    /**
     * PaasAgent Facade
     */
    private ISrPaasResourcePaasAgentLink iSrPaasResourcePaasAgentLink = null;

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
     * PaasAgent 1 value object
     */
    private PaasAgentVO paasAgent1;

    /**
     * PaasAgent 2 value object
     */
    private PaasAgentVO paasAgent2;

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
        this.iSrPaasJonasContainerFacade = (ISrPaasJonasContainerFacade) initialContext.lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade");
        this.iSrPaasAgentFacade = (ISrPaasAgentFacade) initialContext.lookup("java:global/" + moduleName +
                "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasAgentFacade");
        this.iSrPaasResourcePaasAgentLink = (ISrPaasResourcePaasAgentLink) initialContext.lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasResourcePaasAgentLink");
    }

    private void initDatabase() {
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
        paasAgent1 = new PaasAgentVO("paasAgent1", "state", capabilitiesList, true, true, usedPorts, "apiUrl");
        paasAgent2 = new PaasAgentVO("paasAgent2", "state", capabilitiesList, false, false, usedPorts, "apiUrl2");

        jonas1 = iSrPaasJonasContainerFacade.createJonasContainer(jonas1);
        jonas2 = iSrPaasJonasContainerFacade.createJonasContainer(jonas2);
        jonas3 = iSrPaasJonasContainerFacade.createJonasContainer(jonas3);
        paasAgent1 = iSrPaasAgentFacade.createAgent(paasAgent1);
        paasAgent2 = iSrPaasAgentFacade.createAgent(paasAgent2);
    }

    @AfterClass
    public void cleanDatabase() {
        iSrPaasJonasContainerFacade.deleteJonasContainer(jonas1.getId());
        iSrPaasJonasContainerFacade.deleteJonasContainer(jonas2.getId());
        iSrPaasJonasContainerFacade.deleteJonasContainer(jonas3.getId());
        iSrPaasAgentFacade.deleteAgent(paasAgent1.getId());
        iSrPaasAgentFacade.deleteAgent(paasAgent2.getId());
    }

    @Test
    public void testAddPaasResourceAgentLink() {
        iSrPaasResourcePaasAgentLink.addPaasResourceAgentLink(jonas1.getId(), paasAgent1.getId());
        iSrPaasResourcePaasAgentLink.addPaasResourceAgentLink(jonas2.getId(), paasAgent1.getId());
        iSrPaasResourcePaasAgentLink.addPaasResourceAgentLink(jonas3.getId(), paasAgent2.getId());
    }

    @Test(dependsOnMethods="testAddPaasResourceAgentLink")
    public void testFindPaasResourcesByAgent() {
        List<PaasResourceVO> paasResourceVOList = iSrPaasResourcePaasAgentLink.findPaasResourcesByAgent
                (paasAgent1.getId());
        Assert.assertEquals(paasResourceVOList.size(), 2);
        paasResourceVOList = iSrPaasResourcePaasAgentLink.findPaasResourcesByAgent(paasAgent2.getId());
        Assert.assertEquals(paasResourceVOList.size(), 1);
        Assert.assertEquals(paasResourceVOList.get(0).getId(), jonas3.getId());
    }

    @Test(dependsOnMethods="testFindPaasResourcesByAgent")
    public void testFindAgentByPaasResource() {
        PaasAgentVO paasAgentVO = iSrPaasResourcePaasAgentLink.findAgentByPaasResource(jonas1.getId());
        Assert.assertEquals(paasAgentVO.getId(), paasAgent1.getId());
    }

    @Test(dependsOnMethods = "testFindAgentByPaasResource")
    public void testRemovePaasResourceAgentLink() {
        iSrPaasResourcePaasAgentLink.removePaasResourceAgentLink(jonas1.getId(), paasAgent1.getId());
        List<PaasResourceVO> paasResourceVOList =
                iSrPaasResourcePaasAgentLink.findPaasResourcesByAgent(paasAgent1.getId());
        Assert.assertEquals(paasResourceVOList.size(), 1);

        iSrPaasResourcePaasAgentLink.removePaasResourceAgentLink(jonas2.getId(), paasAgent1.getId());
        paasResourceVOList = iSrPaasResourcePaasAgentLink.findPaasResourcesByAgent(paasAgent1.getId());
        Assert.assertEquals(paasResourceVOList.isEmpty(), true);
    }
}
