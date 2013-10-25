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
import org.ow2.jonas.jpaas.sr.facade.api.ISrIaasComputeFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasAgentFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasAgentIaasComputeLink;
import org.ow2.jonas.jpaas.sr.facade.vo.IaasComputeVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasAgentVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * PaasAgentIaasComputeLink test case
 * @author David Richard
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class TestPaasAgentIaasComputeLink extends SetupTest {

    /**
     * PaasJonasContainer Facade
     */
    @Inject
    private ISrPaasAgentFacade iSrPaasAgentFacade;

    /**
     * IaasCompute Facade
     */
    @Inject
    private ISrIaasComputeFacade iSrIaasComputeFacade;

    /**
     * IaasCompute Facade
     */
    @Inject
    private ISrPaasAgentIaasComputeLink iSrPaasAgentIaasComputeLink;

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

    @Test
    public void initPaasAgentIaasComputeLink() throws NamingException {
        Map<String,String> capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        List<Integer> usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        List<String> roles = new LinkedList<String>();
        roles.add("Jonas");

        agent1 = new PaasAgentVO("agent1PaasAgentFacade", "state", capabilitiesList, true, true, usedPorts, "url1");
        agent2 = new PaasAgentVO("agent2PaasAgentFacade", "state", capabilitiesList, true, true, usedPorts, "url2");
        agent3 = new PaasAgentVO("agent3PaasAgentFacade", "state", capabilitiesList, true, true, usedPorts, "url3");
        iaasCompute1 = new IaasComputeVO("iaasCompute1PaasAgentFacade", "state", capabilitiesList, true, true, usedPorts, "ipAddress",
                "hostname", "conf", roles, "internalId");
        iaasCompute2 = new IaasComputeVO("iaasCompute2PaasAgentFacade", "state", capabilitiesList, false, false, usedPorts, "ipAddress",
                "hostname", "conf", roles, "internalId");

        agent1 = iSrPaasAgentFacade.createAgent(agent1);
        agent2 = iSrPaasAgentFacade.createAgent(agent2);
        agent3 = iSrPaasAgentFacade.createAgent(agent3);
        iaasCompute1 = iSrIaasComputeFacade.createIaasCompute(iaasCompute1);
        iaasCompute2 = iSrIaasComputeFacade.createIaasCompute(iaasCompute2);
    }


    @Test(dependsOnMethods="initPaasAgentIaasComputeLink")
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
