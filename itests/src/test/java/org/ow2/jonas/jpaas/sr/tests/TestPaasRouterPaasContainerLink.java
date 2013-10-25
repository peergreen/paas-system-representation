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
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasApacheJkRouterFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasRouterPaasContainerLink;
import org.ow2.jonas.jpaas.sr.facade.vo.ApacheJkVO;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasContainerVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasRouterVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * PaasRouterPaasContainerLink test case
 * @author David Richard
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class TestPaasRouterPaasContainerLink extends SetupTest {

    /**
     * PaasJonasContainer Facade
     */
    @Inject
    private ISrPaasJonasContainerFacade iSrPaasJonasContainerFacade;

    /**
     * PaasApacheJkRouter Facade
     */
    @Inject
    private ISrPaasApacheJkRouterFacade iSrPaasApacheJkRouterFacade;

    /**
     * PaasRouter Facade
     */
    @Inject
    private ISrPaasRouterPaasContainerLink iSrPaasContainerPaasRouterLink;

    /**
     * Jonas 1 value object
     */
    private JonasVO jonas1;

    /**
     * Jonas 1 value object
     */
    private JonasVO jonas2;

    /**
     * ApacheJk 1 value object
     */
    private ApacheJkVO apacheJk1;

    /**
     * ApacheJk 2 value object
     */
    private ApacheJkVO apacheJk2;


    @Test
    public void initPaasRouterPaasContainerLink() {
        Map<String,String> capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        List<Integer> usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        jonas1 = new JonasVO("jonas1PaasRouterPaasContainerLink", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas2 = new JonasVO("jonas2PaasRouterPaasContainerLink", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        apacheJk1 = new ApacheJkVO("apacheJk1PaasRouterPaasContainerLink", "state", capabilitiesList, true, true, usedPorts, "apacheVersion",
                "jkVersion");
        apacheJk2 = new ApacheJkVO("apacheJk2PaasRouterPaasContainerLink", "state", capabilitiesList, false, false, usedPorts, "apacheVersion2",
                "jkVersion2");

        jonas1 = iSrPaasJonasContainerFacade.createJonasContainer(jonas1);
        jonas2 = iSrPaasJonasContainerFacade.createJonasContainer(jonas2);
        apacheJk1 = iSrPaasApacheJkRouterFacade.createApacheJkRouter(apacheJk1);
        apacheJk2 = iSrPaasApacheJkRouterFacade.createApacheJkRouter(apacheJk2);
    }

    @Test(dependsOnMethods="initPaasRouterPaasContainerLink")
    public void testAddRouterContainerLink() {
        iSrPaasContainerPaasRouterLink.addRouterContainerLink(apacheJk1.getId(), jonas1.getId());
        iSrPaasContainerPaasRouterLink.addRouterContainerLink(apacheJk1.getId(), jonas2.getId());
        iSrPaasContainerPaasRouterLink.addRouterContainerLink(apacheJk2.getId(), jonas1.getId());
    }

    @Test(dependsOnMethods="testAddRouterContainerLink")
    public void testFindContainersByRouter() {
        List<PaasContainerVO> paasContainerVOList =
                iSrPaasContainerPaasRouterLink.findContainersByRouter(apacheJk1.getId());
        Assert.assertEquals(paasContainerVOList.size(), 2);
        paasContainerVOList = iSrPaasContainerPaasRouterLink.findContainersByRouter(apacheJk2.getId());
        Assert.assertEquals(paasContainerVOList.size(), 1);
        Assert.assertEquals(paasContainerVOList.get(0).getId(), jonas1.getId());
    }

    @Test(dependsOnMethods="testFindContainersByRouter")
    public void testFindRoutersByContainer() {
        List<PaasRouterVO> paasRouterVOList = iSrPaasContainerPaasRouterLink.findRoutersByContainer(jonas1.getId());
        Assert.assertEquals(paasRouterVOList.size(), 2);
        paasRouterVOList = iSrPaasContainerPaasRouterLink.findRoutersByContainer(jonas2.getId());
        Assert.assertEquals(paasRouterVOList.size(), 1);
        Assert.assertEquals(paasRouterVOList.get(0).getId(), apacheJk1.getId());
    }

    @Test(dependsOnMethods = "testFindRoutersByContainer")
    public void testRemoveRouterContainerLink() {
        iSrPaasContainerPaasRouterLink.removeRouterContainerLink(apacheJk2.getId(), jonas1.getId());
        List<PaasRouterVO> paasRouterVOList = iSrPaasContainerPaasRouterLink.findRoutersByContainer(jonas1.getId());
        Assert.assertEquals(paasRouterVOList.size(), 1);
        Assert.assertEquals(paasRouterVOList.get(0).getId(), apacheJk1.getId());
        List<PaasContainerVO> paasContainerVOList =
                iSrPaasContainerPaasRouterLink.findContainersByRouter(apacheJk2.getId());
        Assert.assertEquals(paasContainerVOList.isEmpty(), true);

        iSrPaasContainerPaasRouterLink.removeRouterContainerLink(apacheJk1.getId(), jonas2.getId());
        paasRouterVOList = iSrPaasContainerPaasRouterLink.findRoutersByContainer(jonas2.getId());
        Assert.assertEquals(paasRouterVOList.isEmpty(), true);
        paasContainerVOList = iSrPaasContainerPaasRouterLink.findContainersByRouter(apacheJk1.getId());
        Assert.assertEquals(paasContainerVOList.size(), 1);
        Assert.assertEquals(paasContainerVOList.get(0).getId(), jonas1.getId());
    }
}
