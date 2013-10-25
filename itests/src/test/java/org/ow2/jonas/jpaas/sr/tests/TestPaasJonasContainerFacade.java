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

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;
import org.ops4j.pax.exam.testng.listener.PaxExam;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * PaasJonasContainer Facade test case
 * @author David Richard
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class TestPaasJonasContainerFacade extends SetupTest {

    /**
     * PaasJonasContainer Facade
     */
    @Inject
    private ISrPaasJonasContainerFacade iSrPaasJonasContainerFacade;

    /**
     * Capabilities list
     */
    private Map<String,String> capabilitiesList;

    /**
     * usedPorts list
     */
    private List<Integer> usedPorts;

    /**
     * Jonas 1 value object
     */
    private JonasVO jonas1;

    /**
     * Jonas 2 value object
     */
    private JonasVO jonas2;

    @Test
    public void initPaasJonasContainerFacade() throws NamingException {

        capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        jonas1 = new JonasVO("jonas1PaasJonasContainerFacade", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas2 = new JonasVO("jonas2PaasJonasContainerFacade", "state", capabilitiesList, false, false, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
    }


    @Test(dependsOnMethods="initPaasJonasContainerFacade")
    public void testCreateJonasContainer() {
        //Create and test a jonas
        JonasVO tmpJonas1 = iSrPaasJonasContainerFacade.createJonasContainer(jonas1);
        Assert.assertNotEquals(tmpJonas1.getId(), null);
        jonas1 = tmpJonas1;

        //Create and test a second jonas
        JonasVO tmpJonas2 = iSrPaasJonasContainerFacade.createJonasContainer(jonas2);
        Assert.assertNotEquals(tmpJonas2.getId(), null);
        jonas2 = tmpJonas2;
    }

    @Test(dependsOnMethods = "testCreateJonasContainer")
    public void testGetJonasContainer() {
        //Get the first jonas
        JonasVO tmpJonas1 = iSrPaasJonasContainerFacade.getJonasContainer(jonas1.getId());
        Assert.assertEquals(jonas1.getId(), tmpJonas1.getId());
        Assert.assertEquals(jonas1.getName(), tmpJonas1.getName());
        Assert.assertEquals(jonas1.getState(), tmpJonas1.getState());
        Assert.assertEquals(jonas1.getCapabilities(), tmpJonas1.getCapabilities());
        Assert.assertEquals(jonas1.isMultitenant(), tmpJonas1.isMultitenant());
        Assert.assertEquals(jonas1.isReusable(), tmpJonas1.isReusable());
        Assert.assertEquals(jonas1.getUsedPorts(), tmpJonas1.getUsedPorts());
        Assert.assertEquals(jonas1.getJonasVersion(), tmpJonas1.getJonasVersion());
        Assert.assertEquals(jonas1.getProfile(), tmpJonas1.getProfile());
        Assert.assertEquals(jonas1.getJdkVersion(), tmpJonas1.getJdkVersion());
        Assert.assertEquals(jonas1.getDomain(), tmpJonas1.getDomain());
    }

    @Test(dependsOnMethods = "testGetJonasContainer")
    public void testUpdateJonasContainer() {
        //Update the first jonas
        jonas1.setName("updateJonas1");
        jonas1.setJonasVersion("updateJonasVersion");
        jonas1.setProfile("updateProfile");
        jonas1.setJdkVersion("updateJdkVersion");
        jonas1.setDomain("updateDomain");

        JonasVO tmpJonas1 = iSrPaasJonasContainerFacade.updateJonasContainer(jonas1);
        Assert.assertEquals(jonas1.getId(), tmpJonas1.getId());
        Assert.assertEquals(jonas1.getName(), tmpJonas1.getName());
        Assert.assertEquals(jonas1.getProfile(), tmpJonas1.getProfile());
        Assert.assertEquals(jonas1.getJonasVersion(), tmpJonas1.getJonasVersion());
        Assert.assertEquals(jonas1.getJdkVersion(), tmpJonas1.getJdkVersion());
        Assert.assertEquals(jonas1.getDomain(), tmpJonas1.getDomain());
    }

    @Test(dependsOnMethods = "testUpdateJonasContainer")
    public void testFindJonasContainer() {
        List<JonasVO> jonasList = iSrPaasJonasContainerFacade.findJonasContainers();
        assertTrue(jonasList.size() > 0);
    }


}
