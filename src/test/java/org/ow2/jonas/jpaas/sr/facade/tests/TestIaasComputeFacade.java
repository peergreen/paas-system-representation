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

import org.ow2.jonas.jpaas.sr.facade.api.ISrIaasComputeFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.IaasComputeVO;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.LinkedList;
import java.util.List;

/**
 * IaasCompute Facade test case
 * @author David Richard
 */
public class TestIaasComputeFacade {

    /**
     * IaasCompute Facade
     */
    private ISrIaasComputeFacade iSrIaasComputeFacade = null;

    /**
     * Capabilities list
     */
    private List<String> capabilitesList;

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


    @BeforeClass
    public void init() throws NamingException {
        getBean();

        capabilitesList = new LinkedList<String>();
        capabilitesList.add("capability 1");
        capabilitesList.add("capability 2");

        usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        iaasCompute1 = new IaasComputeVO("iaasCompute1", "state", capabilitesList, true, true, usedPorts, "ipAddress",
                "hostname", "conf", "role");
        iaasCompute2 = new IaasComputeVO("iaasCompute2", "state", capabilitesList, false, false, usedPorts, "ipAddress",
                "hostname", "conf", "role");
    }


    private void getBean() throws NamingException {
        this.iSrIaasComputeFacade = (ISrIaasComputeFacade) new InitialContext().lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrIaasComputeFacade");
    }

    @Test
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
        Assert.assertEquals(iaasCompute1.getRole(), tmpIaasCompute1.getRole());
    }

    @Test(dependsOnMethods = "testGetIaasCompute")
    public void testUpdateIaasCompute() {
        iaasCompute1.setName("updateIaasCompute1");
        iaasCompute1.setIpAddress("updateIpAddress");
        iaasCompute1.setConf("updateConf");
        iaasCompute1.setHostname("updateHostname");
        iaasCompute1.setRole("updateRole");

        IaasComputeVO tmpIaasCompute1 = iSrIaasComputeFacade.updateIaasCompute(iaasCompute1);
        Assert.assertEquals(iaasCompute1.getId(), tmpIaasCompute1.getId());
        Assert.assertEquals(iaasCompute1.getName(), tmpIaasCompute1.getName());
        Assert.assertEquals(iaasCompute1.getIpAddress(), tmpIaasCompute1.getIpAddress());
        Assert.assertEquals(iaasCompute1.getConf(), tmpIaasCompute1.getConf());
        Assert.assertEquals(iaasCompute1.getHostname(), tmpIaasCompute1.getHostname());
        Assert.assertEquals(iaasCompute1.getRole(), tmpIaasCompute1.getRole());
    }

    @Test(dependsOnMethods = "testUpdateIaasCompute")
    public void testFindIaasCompute() {
        List<IaasComputeVO> jonasList = iSrIaasComputeFacade.findIaasComputes();
        Assert.assertEquals(jonasList.size(), 2);
    }

    @Test(dependsOnMethods = "testFindIaasCompute")
    public void testDeleteIaasCompute() {
        iSrIaasComputeFacade.deleteIaasCompute(iaasCompute1.getId());
        List<IaasComputeVO> jonasList = iSrIaasComputeFacade.findIaasComputes();
        Assert.assertEquals(jonasList.size(), 1);
        iSrIaasComputeFacade.deleteIaasCompute(iaasCompute2.getId());
        jonasList = iSrIaasComputeFacade.findIaasComputes();
        Assert.assertEquals(jonasList.isEmpty(), true);
    }

}
