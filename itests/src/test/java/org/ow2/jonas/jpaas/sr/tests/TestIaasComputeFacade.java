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
import org.ow2.jonas.jpaas.sr.facade.api.ISrIaasComputeFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.IaasComputeVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * IaasCompute Facade test case
 * @author David Richard
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class TestIaasComputeFacade extends SetupTest {

    /**
     * IaasCompute Facade
     */
    @Inject
    private ISrIaasComputeFacade iSrIaasComputeFacade;

    /**
     * Capabilities list
     */
    private Map<String,String> capabilitiesList;

    /**
     * usedPorts list
     */
    private List<Integer> usedPorts;

    /**
     * IaasCompute 1 value object
     */
    private IaasComputeVO iaasCompute1;

    /**
     * IaasCompute 2 value object
     */
    private IaasComputeVO iaasCompute2;



    @Test
    public void initIaasComputeFacade() throws NamingException {

        capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        List<String> roles = new LinkedList<String>();
        roles.add("Jonas");

        iaasCompute1 = new IaasComputeVO("iaasCompute1", "state", capabilitiesList, true, true, usedPorts, "ipAddress",
                "hostname", "conf", roles, "id");
        iaasCompute2 = new IaasComputeVO("iaasCompute2", "state", capabilitiesList, false, false, usedPorts, "ipAddress",
                "hostname", "conf", roles, "id");
    }



    @Test(dependsOnMethods="initIaasComputeFacade")
    public void testCreateIaasCompute() {
        IaasComputeVO tmpIaasCompute1 = iSrIaasComputeFacade.createIaasCompute(iaasCompute1);
        Assert.assertNotEquals(tmpIaasCompute1.getId(), null);
        iaasCompute1 = tmpIaasCompute1;

        IaasComputeVO tmpIaasCompute2 = iSrIaasComputeFacade.createIaasCompute(iaasCompute2);
        Assert.assertNotEquals(tmpIaasCompute2.getId(), null);
        iaasCompute2 = tmpIaasCompute2;
    }

    @Test(dependsOnMethods = "testCreateIaasCompute")
    public void testGetIaasCompute() {
        IaasComputeVO tmpIaasCompute1 = iSrIaasComputeFacade.getIaasCompute(iaasCompute1.getId());
        Assert.assertEquals(iaasCompute1.getId(), tmpIaasCompute1.getId());
        Assert.assertEquals(iaasCompute1.getName(), tmpIaasCompute1.getName());
        Assert.assertEquals(iaasCompute1.getState(), tmpIaasCompute1.getState());
        Assert.assertEquals(iaasCompute1.getCapabilities(), tmpIaasCompute1.getCapabilities());
        Assert.assertEquals(iaasCompute1.isMultitenant(), tmpIaasCompute1.isMultitenant());
        Assert.assertEquals(iaasCompute1.isReusable(), tmpIaasCompute1.isReusable());
        Assert.assertEquals(iaasCompute1.getUsedPorts(), tmpIaasCompute1.getUsedPorts());
        Assert.assertEquals(iaasCompute1.getIpAddress(), tmpIaasCompute1.getIpAddress());
        Assert.assertEquals(iaasCompute1.getConf(), tmpIaasCompute1.getConf());
        Assert.assertEquals(iaasCompute1.getHostname(), tmpIaasCompute1.getHostname());
        Assert.assertEquals(iaasCompute1.getRoles().toString(), tmpIaasCompute1.getRoles().toString());
    }

    @Test(dependsOnMethods = "testGetIaasCompute")
    public void testUpdateIaasCompute() {
        iaasCompute1.setName("updateIaasCompute1");
        iaasCompute1.setIpAddress("updateIpAddress");
        iaasCompute1.setConf("updateConf");
        iaasCompute1.setHostname("updateHostname");

        IaasComputeVO tmpIaasCompute1 = iSrIaasComputeFacade.updateIaasCompute(iaasCompute1);
        Assert.assertEquals(iaasCompute1.getId(), tmpIaasCompute1.getId());
        Assert.assertEquals(iaasCompute1.getName(), tmpIaasCompute1.getName());
        Assert.assertEquals(iaasCompute1.getIpAddress(), tmpIaasCompute1.getIpAddress());
        Assert.assertEquals(iaasCompute1.getConf(), tmpIaasCompute1.getConf());
        Assert.assertEquals(iaasCompute1.getHostname(), tmpIaasCompute1.getHostname());
    }

    @Test(dependsOnMethods = "testUpdateIaasCompute")
    public void testFindIaasCompute() {
        List<IaasComputeVO> jonasList = iSrIaasComputeFacade.findIaasComputes();
        assertTrue(jonasList.size() > 0);
    }

    @Test(dependsOnMethods = "testFindIaasCompute")
    public void testDeleteIaasCompute() {
        iSrIaasComputeFacade.deleteIaasCompute(iaasCompute1.getId());
        List<IaasComputeVO> jonasList = iSrIaasComputeFacade.findIaasComputes();
        int size = jonasList.size();
        assertTrue(jonasList.size() > 0);
        iSrIaasComputeFacade.deleteIaasCompute(iaasCompute2.getId());
        jonasList = iSrIaasComputeFacade.findIaasComputes();
        Assert.assertEquals(jonasList.size(), size - 1);
    }

}
