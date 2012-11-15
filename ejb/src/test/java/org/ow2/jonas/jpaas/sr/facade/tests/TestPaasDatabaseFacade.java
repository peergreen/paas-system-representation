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

import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasDatabaseFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasDatabaseVO;
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
 * PaasDatabase Facade test case
 * @author David Richard
 */
public class TestPaasDatabaseFacade {

    /**
     * PaasDatabase Facade
     */
    private ISrPaasDatabaseFacade iSrPaasDatabaseFacade = null;

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

        paasDatabase1 = new PaasDatabaseVO("paasDatabase1", "state", capabilitiesList, true, true, usedPorts);
        paasDatabase2 = new PaasDatabaseVO("paasDatabase2", "state", capabilitiesList, false, false, usedPorts);
    }


    private void getBean() throws NamingException {
        this.iSrPaasDatabaseFacade = (ISrPaasDatabaseFacade) new InitialContext().lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasDatabaseFacade");
    }

    @Test
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
        Assert.assertEquals(jonasList.size(), 2);
    }

    @Test(dependsOnMethods = "testFindDatabase")
    public void testDeletePaasDatabase() {
        iSrPaasDatabaseFacade.deleteDatabase(paasDatabase1.getId());
        List<PaasDatabaseVO> jonasList = iSrPaasDatabaseFacade.findDatabases();
        Assert.assertEquals(jonasList.size(), 1);
        iSrPaasDatabaseFacade.deleteDatabase(paasDatabase2.getId());
        jonasList = iSrPaasDatabaseFacade.findDatabases();
        Assert.assertEquals(jonasList.isEmpty(), true);
    }

}
