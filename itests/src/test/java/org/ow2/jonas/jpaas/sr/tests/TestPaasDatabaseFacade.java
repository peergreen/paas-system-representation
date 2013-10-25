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
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasDatabaseFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasDatabaseVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * PaasDatabase Facade test case
 * @author David Richard
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class TestPaasDatabaseFacade extends SetupTest {

    /**
     * PaasDatabase Facade
     */
    @Inject
    private ISrPaasDatabaseFacade iSrPaasDatabaseFacade;

    /**
     * Capabilities list
     */
    private Map<String,String> capabilitiesList;

    /**
     * usedPorts list
     */
    private List<Integer> usedPorts;

    /**
     * PaasDatabase 1 value object
     */
    private PaasDatabaseVO paasDatabase1;

    /**
     * PaasDatabase 2 value object
     */
    private PaasDatabaseVO paasDatabase2;


    @Test
    public void initPaasDatabaseFacade() throws NamingException {

        capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        paasDatabase1 = new PaasDatabaseVO("paasDatabase1PaasDatabaseFacade", "state", capabilitiesList, true, true, usedPorts);
        paasDatabase2 = new PaasDatabaseVO("paasDatabase2PaasDatabaseFacade", "state", capabilitiesList, false, false, usedPorts);
    }


    @Test(dependsOnMethods="initPaasDatabaseFacade")
    public void testCreateDatabase() {
        PaasDatabaseVO tmpPaasDatabase1 = iSrPaasDatabaseFacade.createDatabase(paasDatabase1);
        Assert.assertNotEquals(tmpPaasDatabase1.getId(), null);
        paasDatabase1 = tmpPaasDatabase1;

        PaasDatabaseVO tmpPaasDatabase2 = iSrPaasDatabaseFacade.createDatabase(paasDatabase2);
        Assert.assertNotEquals(tmpPaasDatabase2.getId(), null);
        paasDatabase2 = tmpPaasDatabase2;
    }

    @Test(dependsOnMethods = "testCreateDatabase")
    public void testGetDatabase() {
        PaasDatabaseVO tmpPaasDatabase1 = iSrPaasDatabaseFacade.getDatabase(paasDatabase1.getId());
        Assert.assertEquals(paasDatabase1.getId(), tmpPaasDatabase1.getId());
        Assert.assertEquals(paasDatabase1.getName(), tmpPaasDatabase1.getName());
        Assert.assertEquals(paasDatabase1.getState(), tmpPaasDatabase1.getState());
        Assert.assertEquals(paasDatabase1.getCapabilities(), tmpPaasDatabase1.getCapabilities());
        Assert.assertEquals(paasDatabase1.isMultitenant(), tmpPaasDatabase1.isMultitenant());
        Assert.assertEquals(paasDatabase1.isReusable(), tmpPaasDatabase1.isReusable());
        Assert.assertEquals(paasDatabase1.getUsedPorts(), tmpPaasDatabase1.getUsedPorts());
    }

    @Test(dependsOnMethods = "testGetDatabase")
    public void testUpdateDatabase() {
        paasDatabase1.setName("updatePaasDatabase1");

        PaasDatabaseVO tmpPaasDatabase1 = iSrPaasDatabaseFacade.updateDatabase(paasDatabase1);
        Assert.assertEquals(paasDatabase1.getId(), tmpPaasDatabase1.getId());
        Assert.assertEquals(paasDatabase1.getName(), tmpPaasDatabase1.getName());
    }

    @Test(dependsOnMethods = "testUpdateDatabase")
    public void testFindDatabase() {
        List<PaasDatabaseVO> jonasList = iSrPaasDatabaseFacade.findDatabases();
        assertTrue(jonasList.size() > 0);
    }


}
