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

import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasFrontendFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasFrontendVO;
import org.ow2.jonas.jpaas.sr.facade.vo.VirtualHostVO;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.LinkedList;
import java.util.List;

/**
 * PaasFrontend Facade test case
 * @author David Richard
 */
public class TestPaasFrontendFacade {

    /**
     * PaasFrontend Facade
     */
    private ISrPaasFrontendFacade iSrPaasFrontendFacade = null;

    /**
     * VirtualHostVO list
     */
    private List<VirtualHostVO>  virtualHostVOList;


    /**
     * PaasFrontend 1 value object
     */
    private PaasFrontendVO paasFrontend1;

    /**
     * PaasFrontend 2 value object
     */
    private PaasFrontendVO paasFrontend2;

    /**
     * VirtualHost 1 value object
     */
    private VirtualHostVO virtualHost1;

    /**
     * VirtualHost 2 value object
     */
    private VirtualHostVO virtualHost2;

    /**
     * Name of the module for the lookup
     */
    private final String moduleName = System.getProperty("module.name");


    @BeforeClass
    public void init() throws NamingException {
        getBean();

        virtualHost1 = new VirtualHostVO("virtualHost1", "id1");
        virtualHost2 = new VirtualHostVO("virtualHost2", "id2");

        virtualHostVOList = new LinkedList<VirtualHostVO>();
        virtualHostVOList.add(virtualHost1);
        virtualHostVOList.add(virtualHost2);


        paasFrontend1 = new PaasFrontendVO();
        paasFrontend2 = new PaasFrontendVO();
    }


    private void getBean() throws NamingException {
        this.iSrPaasFrontendFacade = (ISrPaasFrontendFacade) new InitialContext().lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasFrontendFacade");
    }

    @Test
    public void testCreateFrontend() {
        PaasFrontendVO tmpPaasFrontend1 = iSrPaasFrontendFacade.createFrontend(paasFrontend1);
        Assert.assertNotEquals(tmpPaasFrontend1.getId(), null);
        paasFrontend1 = tmpPaasFrontend1;


        PaasFrontendVO tmpPaasFrontend2 = iSrPaasFrontendFacade.createFrontend(paasFrontend2);
        Assert.assertNotEquals(tmpPaasFrontend2.getId(), null);
        paasFrontend2 = tmpPaasFrontend2;
    }

    @Test(dependsOnMethods = "testCreateFrontend")
    public void testGetFrontend() {
        PaasFrontendVO tmpPaasFrontend1 = iSrPaasFrontendFacade.getFrontend(paasFrontend1.getId());
        Assert.assertEquals(paasFrontend1.getId(), tmpPaasFrontend1.getId());
        Assert.assertEquals(paasFrontend1.getVirtualHosts().size(),
                tmpPaasFrontend1.getVirtualHosts().size());
    }

    @Test(dependsOnMethods = "testGetFrontend")
    public void testUpdateFrontend() {
        PaasFrontendVO tmpPaasFrontend1 = iSrPaasFrontendFacade.updateFrontend(paasFrontend1);
    }

    @Test(dependsOnMethods = "testUpdateFrontend")
    public void testFindFrontend() {
        List<PaasFrontendVO> jonasList = iSrPaasFrontendFacade.findFrontends();
        Assert.assertEquals(jonasList.size(), 2);
    }
    
    @Test(dependsOnMethods = "testFindFrontend")
    public void testAddVirtualHost() {
        iSrPaasFrontendFacade.addVirtualHost(paasFrontend1.getId(), "virtualHost1");
        iSrPaasFrontendFacade.addVirtualHost(paasFrontend1.getId(), "virtualHost2");
    }

    @Test(dependsOnMethods = "testAddVirtualHost")
    public void testGetVirtualHosts() {
        List<VirtualHostVO> virtualHostList = iSrPaasFrontendFacade.getVirtualHosts(paasFrontend1.getId());
        Assert.assertEquals(virtualHostList.size(), 2);
    }

    @Test(dependsOnMethods = "testGetVirtualHosts")
    public void testRemoveVirtualHost() {
        iSrPaasFrontendFacade.removeVirtualHost(paasFrontend1.getId(), "virtualHost1");
        List<VirtualHostVO> virtualHostList = iSrPaasFrontendFacade.getVirtualHosts(paasFrontend1.getId());
        Assert.assertEquals(virtualHostList.size(), 1);
        iSrPaasFrontendFacade.removeVirtualHost(paasFrontend1.getId(), "virtualHost2");
        virtualHostList = iSrPaasFrontendFacade.getVirtualHosts(paasFrontend1.getId());
        Assert.assertEquals(virtualHostList.isEmpty(), true);
    }

    @Test(dependsOnMethods = "testRemoveVirtualHost")
    public void testDeletePaasFrontend() {
        iSrPaasFrontendFacade.deleteFrontend(paasFrontend1.getId());
        List<PaasFrontendVO> jonasList = iSrPaasFrontendFacade.findFrontends();
        Assert.assertEquals(jonasList.size(), 1);
        iSrPaasFrontendFacade.deleteFrontend(paasFrontend2.getId());
        jonasList = iSrPaasFrontendFacade.findFrontends();
        Assert.assertEquals(jonasList.isEmpty(), true);
    }

}
