/**
 * JPaaS
 * Copyright (C) 2012 Bull S.A.S.
 * Contact: jasmine@ow2.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
 * USA
 *
 * --------------------------------------------------------------------------
 * $Id$
 * --------------------------------------------------------------------------
 */

package org.ow2.jonas.jpaas.sr.facade.tests;

import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasApacheJkRouterFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.WorkerVO;
import org.ow2.jonas.jpaas.sr.facade.vo.LoadBalancerVO;
import org.ow2.jonas.jpaas.sr.facade.vo.ApacheJkVO;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.LinkedList;
import java.util.List;

/**
 * PaasApacheJkRouter Facade test case
 * @author David Richard
 */
public class TestPaasApacheJkRouterFacade {

    /**
     * PaasApacheJkRouter Facade
     */
    private ISrPaasApacheJkRouterFacade iSrPaasApacheJkRouterFacade = null;

    /**
     * Capabilities list
     */
    private List<String> capabilitiesList;

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

    /**
     * Name of the module for the lookup
     */
    private final String moduleName = System.getProperty("module.name");


    @BeforeClass
    public void init() throws NamingException {
        getBean();

        capabilitiesList = new LinkedList<String>();
        capabilitiesList.add("capability 1");
        capabilitiesList.add("capability 2");

        usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        apacheJk1 = new ApacheJkVO("apacheJk1", "state", capabilitiesList, true, true, usedPorts, "apacheVersion",
                "jkVersion");
        apacheJk2 = new ApacheJkVO("apacheJk2", "state", capabilitiesList, false, false, usedPorts, "apacheVersion2",
                "jkVersion2");
    }


    private void getBean() throws NamingException {
        this.iSrPaasApacheJkRouterFacade = (ISrPaasApacheJkRouterFacade) new InitialContext().lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasApacheJkRouterFacade");
    }

    @Test
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
