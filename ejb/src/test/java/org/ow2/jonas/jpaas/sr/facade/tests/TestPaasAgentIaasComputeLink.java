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

import org.ow2.jonas.jpaas.sr.facade.api.ISrIaasComputeFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasAgentFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasAgentIaasComputeLink;
import org.ow2.jonas.jpaas.sr.facade.vo.IaasComputeVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasAgentVO;
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
 * PaasAgentIaasComputeLink test case
 * @author David Richard
 */
public class TestPaasAgentIaasComputeLink {
    /**
     * PaasJonasContainer Facade
     */
    private ISrPaasAgentFacade iSrPaasAgentFacade = null;

    /**
     * IaasCompute Facade
     */
    private ISrIaasComputeFacade iSrIaasComputeFacade = null;

    /**
     * IaasCompute Facade
     */
    private ISrPaasAgentIaasComputeLink iSrPaasAgentIaasComputeLink = null;

    /**
     * Jonas 1 value object
     */
    private PaasAgentVO agent1;

    /**
     * Jonas 1 value object
     */
    private PaasAgentVO agent2;

    /**
     * Jonas 1 value object
     */
    private PaasAgentVO agent3;

    /**
     * IaasCompute 1 value object
     */
    private IaasComputeVO iaasCompute1;

    /**
     * IaasCompute 2 value object
     */
    private IaasComputeVO iaasCompute2;

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
        this.iSrPaasAgentFacade = (ISrPaasAgentFacade) initialContext.lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.agent.jpaas.sr.facade.api.ISrPaasAgentFacade");
        this.iSrIaasComputeFacade = (ISrIaasComputeFacade) initialContext.lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.agent.jpaas.sr.facade.api.ISrIaasComputeFacade");
        this.iSrPaasAgentIaasComputeLink = (ISrPaasAgentIaasComputeLink) initialContext.lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.agent.jpaas.sr.facade.api.ISrPaasAgentIaasComputeLink");
    }

    private void initDatabase() {
        Map<String,String> capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        List<Integer> usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        List<String> roles = new LinkedList<String>();
        roles.add("Jonas");

        agent1 = new PaasAgentVO("agent1", "state", capabilitiesList, true, true, usedPorts, "url1");
        agent2 = new PaasAgentVO("agent2", "state", capabilitiesList, true, true, usedPorts, "url2");
        agent3 = new PaasAgentVO("agent3", "state", capabilitiesList, true, true, usedPorts, "url3");
        iaasCompute1 = new IaasComputeVO("iaasCompute1", "state", capabilitiesList, true, true, usedPorts, "ipAddress",
                "hostname", "conf", roles);
        iaasCompute2 = new IaasComputeVO("iaasCompute2", "state", capabilitiesList, false, false, usedPorts, "ipAddress",
                "hostname", "conf", roles);

        agent1 = iSrPaasAgentFacade.createAgent(agent1);
        agent2 = iSrPaasAgentFacade.createAgent(agent2);
        agent3 = iSrPaasAgentFacade.createAgent(agent3);
        iaasCompute1 = iSrIaasComputeFacade.createIaasCompute(iaasCompute1);
        iaasCompute2 = iSrIaasComputeFacade.createIaasCompute(iaasCompute2);
    }

    @AfterClass
    public void cleanDatabase() {
        iSrPaasAgentFacade.deleteAgent(agent1.getId());
        iSrPaasAgentFacade.deleteAgent(agent2.getId());
        iSrPaasAgentFacade.deleteAgent(agent3.getId());
        iSrIaasComputeFacade.deleteIaasCompute(iaasCompute1.getId());
        iSrIaasComputeFacade.deleteIaasCompute(iaasCompute2.getId());
    }

    @Test
    public void testAddAgentIaasComputeLink() {
        iSrPaasAgentIaasComputeLink.addPaasAgentIaasComputeLink(agent1.getId(), iaasCompute1.getId());
        iSrPaasAgentIaasComputeLink.addPaasAgentIaasComputeLink(agent2.getId(), iaasCompute1.getId());
        iSrPaasAgentIaasComputeLink.addPaasAgentIaasComputeLink(agent3.getId(), iaasCompute2.getId());
    }

    @Test(dependsOnMethods="testAddAgentIaasComputeLink")
    public void testFindAgentsByIaasCompute() {
        List<PaasAgentVO> paasAgentVOList =
                iSrPaasAgentIaasComputeLink.findPaasAgentsByIaasCompute(iaasCompute1.getId());
        Assert.assertEquals(paasAgentVOList.size(), 2);
        paasAgentVOList = iSrPaasAgentIaasComputeLink.findPaasAgentsByIaasCompute(iaasCompute2.getId());
        Assert.assertEquals(paasAgentVOList.size(), 1);
        Assert.assertEquals(paasAgentVOList.get(0).getId(), agent3.getId());
    }

    @Test(dependsOnMethods="testFindAgentsByIaasCompute")
    public void testFindIaasComputesByAgent() {
        IaasComputeVO iaasComputeVO = iSrPaasAgentIaasComputeLink.findIaasComputeByPaasAgent(agent1.getId());
        Assert.assertEquals(iaasComputeVO.getId(), iaasCompute1.getId());
    }

    @Test(dependsOnMethods = "testFindIaasComputesByAgent")
    public void testRemoveAgentIaasComputeLink() {
        iSrPaasAgentIaasComputeLink.removePaasAgentIaasComputeLink(agent1.getId(), iaasCompute1.getId());
        List<PaasAgentVO> paasAgentVOList =
                iSrPaasAgentIaasComputeLink.findPaasAgentsByIaasCompute(iaasCompute1.getId());
        Assert.assertEquals(paasAgentVOList.size(), 1);

        iSrPaasAgentIaasComputeLink.removePaasAgentIaasComputeLink(agent2.getId(), iaasCompute1.getId());
        paasAgentVOList = iSrPaasAgentIaasComputeLink.findPaasAgentsByIaasCompute(iaasCompute1.getId());
        Assert.assertEquals(paasAgentVOList.isEmpty(), true);
    }
}
