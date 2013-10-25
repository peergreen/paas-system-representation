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
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasApacheJkRouterFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.ApacheJkVO;
import org.ow2.jonas.jpaas.sr.facade.vo.LoadBalancerVO;
import org.ow2.jonas.jpaas.sr.facade.vo.WorkerVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * PaasApacheJkRouter Facade test case
 * @author David Richard
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class TestPaasApacheJkRouterFacade extends SetupTest {

    /**
     * PaasApacheJkRouter Facade
     */
    @Inject
    private ISrPaasApacheJkRouterFacade iSrPaasApacheJkRouterFacade;

    /**
     * Capabilities list
     */
    private Map<String,String> capabilitiesList;

    /**
     * usedPorts list
     */
    private List<Integer> usedPorts;

    /**
     * ApacheJk 1 value object
     */
    private ApacheJkVO apacheJk1;

    /**
     * ApacheJk 2 value object
     */
    private ApacheJkVO apacheJk2;

    @Test
    public void initPaasApacheJkRouterFacade() throws NamingException {

        capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        apacheJk1 = new ApacheJkVO("apacheJk1PaasApacheJkRouterFacade", "state", capabilitiesList, true, true, usedPorts, "apacheVersion",
                "jkVersion");
        apacheJk2 = new ApacheJkVO("apacheJk2PaasApacheJkRouterFacade", "state", capabilitiesList, false, false, usedPorts, "apacheVersion2",
                "jkVersion2");
    }

    @Test(dependsOnMethods="initPaasApacheJkRouterFacade")
    public void testCreateApacheJkRouter() {
        //Create and test a apacheJk
        ApacheJkVO tmpApacheJk1 = iSrPaasApacheJkRouterFacade.createApacheJkRouter(apacheJk1);
        Assert.assertNotEquals(tmpApacheJk1.getId(), null);
        apacheJk1 = tmpApacheJk1;

        //Create and test a second apacheJk
        ApacheJkVO tmpApacheJk2 = iSrPaasApacheJkRouterFacade.createApacheJkRouter(apacheJk2);
        Assert.assertNotEquals(tmpApacheJk2.getId(), null);
        apacheJk2 = tmpApacheJk2;
    }

    @Test(dependsOnMethods = "testCreateApacheJkRouter")
    public void testGetApacheJkRouter() {
        //Get the first apacheJk
        ApacheJkVO tmpApacheJk1 = iSrPaasApacheJkRouterFacade.getApacheJkRouter(apacheJk1.getId());
        Assert.assertEquals(apacheJk1.getId(), tmpApacheJk1.getId());
        Assert.assertEquals(apacheJk1.getName(), tmpApacheJk1.getName());
        Assert.assertEquals(apacheJk1.getState(), tmpApacheJk1.getState());
        Assert.assertEquals(apacheJk1.getCapabilities(), tmpApacheJk1.getCapabilities());
        Assert.assertEquals(apacheJk1.isMultitenant(), tmpApacheJk1.isMultitenant());
        Assert.assertEquals(apacheJk1.isReusable(), tmpApacheJk1.isReusable());
        Assert.assertEquals(apacheJk1.getUsedPorts(), tmpApacheJk1.getUsedPorts());
        Assert.assertEquals(apacheJk1.getApacheVersion(), tmpApacheJk1.getApacheVersion());
        Assert.assertEquals(apacheJk1.getJkVersion(), tmpApacheJk1.getJkVersion());
    }

    @Test(dependsOnMethods = "testGetApacheJkRouter")
    public void testUpdateApacheJkRouter() {
        //Update the first apacheJk
        apacheJk1.setName("updateApacheJk1");
        apacheJk1.setApacheVersion("updateApacheVersion");
        apacheJk1.setJkVersion("updateJkVersion");


        ApacheJkVO tmpApacheJk1 = iSrPaasApacheJkRouterFacade.updateApacheJkRouter(apacheJk1);
        Assert.assertEquals(apacheJk1.getId(), tmpApacheJk1.getId());
        Assert.assertEquals(apacheJk1.getName(), tmpApacheJk1.getName());
        Assert.assertEquals(apacheJk1.getApacheVersion(), tmpApacheJk1.getApacheVersion());
        Assert.assertEquals(apacheJk1.getJkVersion(), tmpApacheJk1.getJkVersion());
    }

    @Test(dependsOnMethods = "testUpdateApacheJkRouter")
    public void testFindApacheJkRouter() {
        List<ApacheJkVO> apacheJkList = iSrPaasApacheJkRouterFacade.findApacheJkRouters();
        Assert.assertEquals(apacheJkList.size(), 2);
    }

    @Test(dependsOnMethods = "testFindApacheJkRouter")
    public void testAddWorker() {
        iSrPaasApacheJkRouterFacade.addWorker(apacheJk1.getId(), "worker1", "host", 10);
        iSrPaasApacheJkRouterFacade.addWorker(apacheJk1.getId(), "worker2", "host2", 11);
    }

    @Test(dependsOnMethods = "testAddWorker")
    public void testGetWorkers() {
        List<WorkerVO> workerList = iSrPaasApacheJkRouterFacade.getWorkers(apacheJk1.getId());
        Assert.assertEquals(workerList.size(), 2);
    }

    @Test(dependsOnMethods = "testGetWorkers")
    public void testRemoveWorker() {
        iSrPaasApacheJkRouterFacade.removeWorker(apacheJk1.getId(), "worker1");
        List<WorkerVO> workerList = iSrPaasApacheJkRouterFacade.getWorkers(apacheJk1.getId());
        Assert.assertEquals(workerList.size(), 1);
        iSrPaasApacheJkRouterFacade.removeWorker(apacheJk1.getId(), "worker2");
        workerList = iSrPaasApacheJkRouterFacade.getWorkers(apacheJk1.getId());
        Assert.assertEquals(workerList.isEmpty(), true);
    }

    @Test(dependsOnMethods = "testRemoveWorker")
    public void testAddLoadBalancer() {
        List<String> mountPoints = new LinkedList<String>();
        mountPoints.add("mountPoint 1");
        mountPoints.add("mountPoint 2");

        List<String> workers = new LinkedList<String>();
        workers.add("worker1");
        workers.add("worker2");

        iSrPaasApacheJkRouterFacade.addLoadBalancer(apacheJk1.getId(), "loadBalancer1", mountPoints, workers);
        iSrPaasApacheJkRouterFacade.addLoadBalancer(apacheJk1.getId(), "loadBalancer2", mountPoints, workers);
    }

    @Test(dependsOnMethods = "testAddLoadBalancer")
    public void testGetLoadBalancers() {
        List<LoadBalancerVO> loadBalancerList = iSrPaasApacheJkRouterFacade.getLoadBalancers(apacheJk1.getId());
        Assert.assertEquals(loadBalancerList.size(), 2);
    }

    @Test(dependsOnMethods = "testGetLoadBalancers")
    public void testRemoveLoadBalancer() {
        iSrPaasApacheJkRouterFacade.removeLoadBalancer(apacheJk1.getId(), "loadBalancer1");
        List<LoadBalancerVO> loadBalancerList = iSrPaasApacheJkRouterFacade.getLoadBalancers(apacheJk1.getId());
        Assert.assertEquals(loadBalancerList.size(), 1);
        iSrPaasApacheJkRouterFacade.removeLoadBalancer(apacheJk1.getId(), "loadBalancer2");
        loadBalancerList = iSrPaasApacheJkRouterFacade.getLoadBalancers(apacheJk1.getId());
        Assert.assertEquals(loadBalancerList.isEmpty(), true);
    }

    @Test(dependsOnMethods = "testRemoveLoadBalancer")
    public void testDeletePaasApacheJkRouter() {
        iSrPaasApacheJkRouterFacade.deleteApacheJkRouter(apacheJk1.getId());
        List<ApacheJkVO> apacheJkList = iSrPaasApacheJkRouterFacade.findApacheJkRouters();
        Assert.assertEquals(apacheJkList.size(), 1);
        iSrPaasApacheJkRouterFacade.deleteApacheJkRouter(apacheJk2.getId());
        apacheJkList = iSrPaasApacheJkRouterFacade.findApacheJkRouters();
        Assert.assertEquals(apacheJkList.isEmpty(), true);
    }

}
