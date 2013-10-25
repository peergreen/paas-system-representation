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
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasContainerPaasDatabaseLink;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasDatabaseFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasContainerVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasDatabaseVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * PaasContainerPaasDatabaseLink test case
 * @author David Richard
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class TestPaasContainerPaasDatabaseLink extends SetupTest {

    /**
     * PaasJonasContainer Facade
     */
    @Inject
    private ISrPaasJonasContainerFacade iSrPaasJonasContainerFacade;

    /**
     * PaasDatabase Facade
     */
    @Inject
    private ISrPaasDatabaseFacade iSrPaasDatabaseFacade;

    /**
     * PaasDatabase Facade
     */
    @Inject
    private ISrPaasContainerPaasDatabaseLink iSrPaasContainerPaasDatabaseLink;

    /**
     * Jonas 1 value object
     */
    private JonasVO jonas1;

    /**
     * Jonas 1 value object
     */
    private JonasVO jonas2;

    /**
     * PaasDatabase 1 value object
     */
    private PaasDatabaseVO paasDatabase1;

    /**
     * PaasDatabase 2 value object
     */
    private PaasDatabaseVO paasDatabase2;

    @Test
    public void initPaasContainerPaasDatabaseLink() {
        Map<String,String> capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        List<Integer> usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        jonas1 = new JonasVO("jonas1PaasContainerPaasDatabaseLink", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas2 = new JonasVO("jonas2PaasContainerPaasDatabaseLink", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        paasDatabase1 = new PaasDatabaseVO("paasDatabase1PaasContainerPaasDatabaseLink", "state", capabilitiesList, true, true, usedPorts);
        paasDatabase2 = new PaasDatabaseVO("paasDatabase2PaasContainerPaasDatabaseLink", "state", capabilitiesList, false, false, usedPorts);

        jonas1 = iSrPaasJonasContainerFacade.createJonasContainer(jonas1);
        jonas2 = iSrPaasJonasContainerFacade.createJonasContainer(jonas2);
        paasDatabase1 = iSrPaasDatabaseFacade.createDatabase(paasDatabase1);
        paasDatabase2 = iSrPaasDatabaseFacade.createDatabase(paasDatabase2);
    }


    @Test(dependsOnMethods="initPaasContainerPaasDatabaseLink")
    public void testAddContainerDatabaseLink() {
        iSrPaasContainerPaasDatabaseLink.addContainerDatabaseLink(jonas1.getId(), paasDatabase1.getId());
        iSrPaasContainerPaasDatabaseLink.addContainerDatabaseLink(jonas2.getId(), paasDatabase1.getId());
        iSrPaasContainerPaasDatabaseLink.addContainerDatabaseLink(jonas1.getId(), paasDatabase2.getId());
    }

    @Test(dependsOnMethods="testAddContainerDatabaseLink")
    public void testFindContainersByDatabase() {
        List<PaasContainerVO> paasContainerVOList =
                iSrPaasContainerPaasDatabaseLink.findContainersByDatabase(paasDatabase1.getId());
        Assert.assertEquals(paasContainerVOList.size(), 2);
        paasContainerVOList = iSrPaasContainerPaasDatabaseLink.findContainersByDatabase(paasDatabase2.getId());
        Assert.assertEquals(paasContainerVOList.size(), 1);
        Assert.assertEquals(paasContainerVOList.get(0).getId(), jonas1.getId());
    }

    @Test(dependsOnMethods="testFindContainersByDatabase")
    public void testFindDatabasesByContainer() {
        List<PaasDatabaseVO> paasDatabaseVOList = iSrPaasContainerPaasDatabaseLink.findDatabasesByContainer(jonas1.getId());
        Assert.assertEquals(paasDatabaseVOList.size(), 2);
        paasDatabaseVOList = iSrPaasContainerPaasDatabaseLink.findDatabasesByContainer(jonas2.getId());
        Assert.assertEquals(paasDatabaseVOList.size(), 1);
        Assert.assertEquals(paasDatabaseVOList.get(0).getId(), paasDatabase1.getId());
    }

    @Test(dependsOnMethods = "testFindDatabasesByContainer")
    public void testRemoveContainerDatabaseLink() {
        iSrPaasContainerPaasDatabaseLink.removeContainerDatabaseLink(jonas1.getId(), paasDatabase2.getId());
        List<PaasDatabaseVO> paasDatabaseVOList =
                iSrPaasContainerPaasDatabaseLink.findDatabasesByContainer(jonas1.getId());
        Assert.assertEquals(paasDatabaseVOList.size(), 1);
        Assert.assertEquals(paasDatabaseVOList.get(0).getId(), paasDatabase1.getId());
        List<PaasContainerVO> paasContainerVOList =
                iSrPaasContainerPaasDatabaseLink.findContainersByDatabase(paasDatabase2.getId());
        Assert.assertEquals(paasContainerVOList.isEmpty(), true);

        iSrPaasContainerPaasDatabaseLink.removeContainerDatabaseLink(jonas2.getId(), paasDatabase1.getId());
        paasDatabaseVOList = iSrPaasContainerPaasDatabaseLink.findDatabasesByContainer(jonas2.getId());
        Assert.assertEquals(paasDatabaseVOList.isEmpty(), true);
        paasContainerVOList = iSrPaasContainerPaasDatabaseLink.findContainersByDatabase(paasDatabase1.getId());
        Assert.assertEquals(paasContainerVOList.size(), 1);
        Assert.assertEquals(paasContainerVOList.get(0).getId(), jonas1.getId());
    }
}
