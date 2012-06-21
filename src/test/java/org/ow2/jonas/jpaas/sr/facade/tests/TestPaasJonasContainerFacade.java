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

import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.ConnectorVO;
import org.ow2.jonas.jpaas.sr.facade.vo.DatasourceVO;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.LinkedList;
import java.util.List;

/**
 * PaasJonasContainer Facade test case
 * @author David Richard
 */
public class TestPaasJonasContainerFacade {

    /**
     * PaasJonasContainer Facade
     */
    private ISrPaasJonasContainerFacade iSrPaasJonasContainerFacade = null;

    /**
     * Capabilities list
     */
    private List<String> capabilitiesList;

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


    @BeforeClass
    public void init() throws NamingException {
        getBean();

        capabilitiesList = new LinkedList<String>();
        capabilitiesList.add("capability 1");
        capabilitiesList.add("capability 2");

        usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        jonas1 = new JonasVO("jonas1", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas2 = new JonasVO("jonas2", "state", capabilitiesList, false, false, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
    }


    private void getBean() throws NamingException {
        this.iSrPaasJonasContainerFacade = (ISrPaasJonasContainerFacade) new InitialContext().lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade");
    }

    @Test
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
        Assert.assertEquals(jonasList.size(), 2);
    }

    @Test(dependsOnMethods = "testFindJonasContainer")
    public void testAddConnector() {
        iSrPaasJonasContainerFacade.addConnector(jonas1.getId(), "connector1", 10);
        iSrPaasJonasContainerFacade.addConnector(jonas1.getId(), "connector2", 11);
    }

    @Test(dependsOnMethods = "testAddConnector")
    public void testGetConnectors() {
        List<ConnectorVO> connectorList = iSrPaasJonasContainerFacade.getConnectors(jonas1.getId());
        Assert.assertEquals(connectorList.size(), 2);
    }

    @Test(dependsOnMethods = "testGetConnectors")
    public void testRemoveConnector() {
        iSrPaasJonasContainerFacade.removeConnector(jonas1.getId(), "connector1");
        List<ConnectorVO> connectorList = iSrPaasJonasContainerFacade.getConnectors(jonas1.getId());
        Assert.assertEquals(connectorList.size(), 1);
        iSrPaasJonasContainerFacade.removeConnector(jonas1.getId(), "connector2");
        connectorList = iSrPaasJonasContainerFacade.getConnectors(jonas1.getId());
        Assert.assertEquals(connectorList.isEmpty(), true);
    }

    @Test(dependsOnMethods = "testFindJonasContainer")
    public void testAddDatasource() {
        iSrPaasJonasContainerFacade.addDatasource(jonas1.getId(), "datasource1", "url", "className");
        iSrPaasJonasContainerFacade.addDatasource(jonas1.getId(), "datasource2", "url2", "className2");
    }

    @Test(dependsOnMethods = "testAddDatasource")
    public void testGetDatasources() {
        List<DatasourceVO> datasourceList = iSrPaasJonasContainerFacade.getDatasources(jonas1.getId());
        Assert.assertEquals(datasourceList.size(), 2);
    }

    @Test(dependsOnMethods = "testGetDatasources")
    public void testRemoveDatasource() {
        iSrPaasJonasContainerFacade.removeDatasource(jonas1.getId(), "datasource1");
        List<DatasourceVO> datasourceList = iSrPaasJonasContainerFacade.getDatasources(jonas1.getId());
        Assert.assertEquals(datasourceList.size(), 1);
        iSrPaasJonasContainerFacade.removeDatasource(jonas1.getId(), "datasource2");
        datasourceList = iSrPaasJonasContainerFacade.getDatasources(jonas1.getId());
        Assert.assertEquals(datasourceList.isEmpty(), true);
    }

    @Test(dependsOnMethods = "testRemoveDatasource")
    public void testDeletePaasJonasContainer() {
        iSrPaasJonasContainerFacade.deleteJonasContainer(jonas1.getId());
        List<JonasVO> jonasList = iSrPaasJonasContainerFacade.findJonasContainers();
        Assert.assertEquals(jonasList.size(), 1);
        iSrPaasJonasContainerFacade.deleteJonasContainer(jonas2.getId());
        jonasList = iSrPaasJonasContainerFacade.findJonasContainers();
        Assert.assertEquals(jonasList.isEmpty(), true);
    }

}
