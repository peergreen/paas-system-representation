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

import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasAgentFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasAgentVO;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * PaasAgent Facade test case
 * @author David Richard
 */
public class TestPaasAgentFacade {

    /**
     * PaasAgent Facade
     */
    private ISrPaasAgentFacade iSrPaasAgentFacade = null;

    /**
     * Capabilities list
     */
    private Map<String,String> capabilitiesList;

    /**
     * usedPorts list
     */
    private List<Integer> usedPorts;

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

        capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        paasAgent1 = new PaasAgentVO("paasAgent1", "state", capabilitiesList, true, true, usedPorts, "apiUrl");
        paasAgent2 = new PaasAgentVO("paasAgent2", "state", capabilitiesList, false, false, usedPorts, "apiUrl2");
    }


    private void getBean() throws NamingException {
        this.iSrPaasAgentFacade = (ISrPaasAgentFacade) new InitialContext().lookup("java:global/" + moduleName +
                "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasAgentFacade");
    }

    @Test
    public void testCreateAgent() {
        PaasAgentVO tmpPaasAgent1 = iSrPaasAgentFacade.createAgent(paasAgent1);
        Assert.assertNotEquals(tmpPaasAgent1.getId(), null);
        paasAgent1 = tmpPaasAgent1;

        PaasAgentVO tmpPaasAgent2 = iSrPaasAgentFacade.createAgent(paasAgent2);
        Assert.assertNotEquals(tmpPaasAgent2.getId(), null);
        paasAgent2 = tmpPaasAgent2;
    }

    @Test(dependsOnMethods = "testCreateAgent")
    public void testGetAgent() {
        PaasAgentVO tmpPaasAgent1 = iSrPaasAgentFacade.getAgent(paasAgent1.getId());
        Assert.assertEquals(paasAgent1.getId(), tmpPaasAgent1.getId());
        Assert.assertEquals(paasAgent1.getName(), tmpPaasAgent1.getName());
        Assert.assertEquals(paasAgent1.getState(), tmpPaasAgent1.getState());
        Assert.assertEquals(paasAgent1.getCapabilities(), tmpPaasAgent1.getCapabilities());
        Assert.assertEquals(paasAgent1.isMultitenant(), tmpPaasAgent1.isMultitenant());
        Assert.assertEquals(paasAgent1.isReusable(), tmpPaasAgent1.isReusable());
        Assert.assertEquals(paasAgent1.getUsedPorts(), tmpPaasAgent1.getUsedPorts());
        Assert.assertEquals(paasAgent1.getApiUrl(), tmpPaasAgent1.getApiUrl());
    }

    @Test(dependsOnMethods = "testGetAgent")
    public void testUpdateAgent() {
        paasAgent1.setName("updatePaasAgent1");
        paasAgent1.setApiUrl("updateApiUrl");

        PaasAgentVO tmpPaasAgent1 = iSrPaasAgentFacade.updateAgent(paasAgent1);
        Assert.assertEquals(paasAgent1.getId(), tmpPaasAgent1.getId());
        Assert.assertEquals(paasAgent1.getName(), tmpPaasAgent1.getName());
        Assert.assertEquals(paasAgent1.getApiUrl(), tmpPaasAgent1.getApiUrl());
    }

    @Test(dependsOnMethods = "testUpdateAgent")
    public void testFindAgent() {
        List<PaasAgentVO> jonasList = iSrPaasAgentFacade.findAgents();
        Assert.assertEquals(jonasList.size(), 2);
    }

    @Test(dependsOnMethods = "testFindAgent")
    public void testDeletePaasAgent() {
        iSrPaasAgentFacade.deleteAgent(paasAgent1.getId());
        List<PaasAgentVO> jonasList = iSrPaasAgentFacade.findAgents();
        Assert.assertEquals(jonasList.size(), 1);
        iSrPaasAgentFacade.deleteAgent(paasAgent2.getId());
        jonasList = iSrPaasAgentFacade.findAgents();
        Assert.assertEquals(jonasList.isEmpty(), true);
    }

}
