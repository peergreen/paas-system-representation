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
