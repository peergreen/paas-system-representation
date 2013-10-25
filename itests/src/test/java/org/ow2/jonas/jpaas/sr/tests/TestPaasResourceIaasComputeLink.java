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
import org.ow2.jonas.jpaas.sr.facade.api.ISrIaasComputeFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasResourceIaasComputeLink;
import org.ow2.jonas.jpaas.sr.facade.vo.IaasComputeVO;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasResourceVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * PaasResourceIaasComputeLink test case
 * @author David Richard
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class TestPaasResourceIaasComputeLink extends SetupTest {
    /**
     * PaasJonasContainer Facade
     */
    @Inject
    private ISrPaasJonasContainerFacade iSrPaasJonasContainerFacade;

    /**
     * IaasCompute Facade
     */
    @Inject
    private ISrIaasComputeFacade iSrIaasComputeFacade;

    /**
     * IaasCompute Facade
     */
    @Inject
    private ISrPaasResourceIaasComputeLink iSrPaasResourceIaasComputeLink;

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
     * IaasCompute 1 value object
     */
    private IaasComputeVO iaasCompute1;

    /**
     * IaasCompute 2 value object
     */
    private IaasComputeVO iaasCompute2;

    @Test
    public void initPaasResourceIaasComputeLink() {
        Map<String,String> capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        List<Integer> usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        List<String> roles = new LinkedList<String>();
        roles.add("Jonas");

        jonas1 = new JonasVO("jonas1PaasResourceIaasComputeLink", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas2 = new JonasVO("jonas2PaasResourceIaasComputeLink", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas3 = new JonasVO("jonas3PaasResourceIaasComputeLink", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        iaasCompute1 = new IaasComputeVO("iaasCompute1PaasResourceIaasComputeLink", "state", capabilitiesList, true, true, usedPorts, "ipAddress",
                "hostname", "conf", roles, "internalId");
        iaasCompute2 = new IaasComputeVO("iaasCompute2PaasResourceIaasComputeLink", "state", capabilitiesList, false, false, usedPorts, "ipAddress",
                "hostname", "conf", roles, "internalId");

        jonas1 = iSrPaasJonasContainerFacade.createJonasContainer(jonas1);
        jonas2 = iSrPaasJonasContainerFacade.createJonasContainer(jonas2);
        jonas3 = iSrPaasJonasContainerFacade.createJonasContainer(jonas3);
        iaasCompute1 = iSrIaasComputeFacade.createIaasCompute(iaasCompute1);
        iaasCompute2 = iSrIaasComputeFacade.createIaasCompute(iaasCompute2);
    }


    @Test(dependsOnMethods="initPaasResourceIaasComputeLink")
    public void testAddResourceIaasComputeLink() {
        iSrPaasResourceIaasComputeLink.addPaasResourceIaasComputeLink(jonas1.getId(), iaasCompute1.getId());
        iSrPaasResourceIaasComputeLink.addPaasResourceIaasComputeLink(jonas2.getId(), iaasCompute1.getId());
        iSrPaasResourceIaasComputeLink.addPaasResourceIaasComputeLink(jonas3.getId(), iaasCompute2.getId());
    }

    @Test(dependsOnMethods="testAddResourceIaasComputeLink")
    public void testFindResourcesByIaasCompute() {
        List<PaasResourceVO> paasResourceVOList =
                iSrPaasResourceIaasComputeLink.findPaasResourcesByIaasCompute(iaasCompute1.getId());
        Assert.assertEquals(paasResourceVOList.size(), 2);
        paasResourceVOList = iSrPaasResourceIaasComputeLink.findPaasResourcesByIaasCompute(iaasCompute2.getId());
        Assert.assertEquals(paasResourceVOList.size(), 1);
        Assert.assertEquals(paasResourceVOList.get(0).getId(), jonas3.getId());
    }

    @Test(dependsOnMethods="testFindResourcesByIaasCompute")
    public void testFindIaasComputesByResource() {
        IaasComputeVO iaasComputeVO = iSrPaasResourceIaasComputeLink.findIaasComputeByPaasResource(jonas1.getId());
        Assert.assertEquals(iaasComputeVO.getId(), iaasCompute1.getId());
    }

    @Test(dependsOnMethods = "testFindIaasComputesByResource")
    public void testRemoveResourceIaasComputeLink() {
        iSrPaasResourceIaasComputeLink.removePaasResourceIaasComputeLink(jonas1.getId(), iaasCompute1.getId());
        List<PaasResourceVO> paasResourceVOList =
                iSrPaasResourceIaasComputeLink.findPaasResourcesByIaasCompute(iaasCompute1.getId());
        Assert.assertEquals(paasResourceVOList.size(), 1);

        iSrPaasResourceIaasComputeLink.removePaasResourceIaasComputeLink(jonas2.getId(), iaasCompute1.getId());
        paasResourceVOList = iSrPaasResourceIaasComputeLink.findPaasResourcesByIaasCompute(iaasCompute1.getId());
        Assert.assertEquals(paasResourceVOList.isEmpty(), true);
    }
}
