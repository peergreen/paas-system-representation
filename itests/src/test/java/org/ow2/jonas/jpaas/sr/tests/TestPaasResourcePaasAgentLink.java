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

import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;
import org.ops4j.pax.exam.testng.listener.PaxExam;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasAgentFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasResourcePaasAgentLink;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasAgentVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasResourceVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * PaasResourcePaasAgentLink test case
 * @author David Richard
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class TestPaasResourcePaasAgentLink extends SetupTest {

    /**
     * PaasJonasContainer Facade
     */
    @Inject
    private ISrPaasJonasContainerFacade iSrPaasJonasContainerFacade;

    /**
     * PaasAgent Facade
     */
    @Inject
    private ISrPaasAgentFacade iSrPaasAgentFacade;

    /**
     * PaasAgent Facade
     */
    @Inject
    private ISrPaasResourcePaasAgentLink iSrPaasResourcePaasAgentLink;

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

    @Test
    public void initPaasResourcePaasAgentLink() {
        Map<String,String> capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        List<Integer> usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        jonas1 = new JonasVO("jonas1PaasResourcePaasAgentLink", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas2 = new JonasVO("jonas2PaasResourcePaasAgentLink", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas3 = new JonasVO("jonas3PaasResourcePaasAgentLink", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        paasAgent1 = new PaasAgentVO("paasAgent1PaasResourcePaasAgentLink", "state", capabilitiesList, true, true, usedPorts, "apiUrl");
        paasAgent2 = new PaasAgentVO("paasAgent2PaasResourcePaasAgentLink", "state", capabilitiesList, false, false, usedPorts, "apiUrl2");

        jonas1 = iSrPaasJonasContainerFacade.createJonasContainer(jonas1);
        jonas2 = iSrPaasJonasContainerFacade.createJonasContainer(jonas2);
        jonas3 = iSrPaasJonasContainerFacade.createJonasContainer(jonas3);
        paasAgent1 = iSrPaasAgentFacade.createAgent(paasAgent1);
        paasAgent2 = iSrPaasAgentFacade.createAgent(paasAgent2);
    }

    @Test(dependsOnMethods="initPaasResourcePaasAgentLink")
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
