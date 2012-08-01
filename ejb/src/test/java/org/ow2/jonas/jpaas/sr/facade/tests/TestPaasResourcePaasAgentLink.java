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
