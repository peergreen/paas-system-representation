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

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;
import org.ops4j.pax.exam.testng.listener.PaxExam;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasFrontendFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasFrontendVO;
import org.ow2.jonas.jpaas.sr.facade.vo.VirtualHostVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * PaasFrontend Facade test case
 * @author David Richard
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class TestPaasFrontendFacade extends SetupTest {

    /**
     * PaasFrontend Facade
     */
    @Inject
    private ISrPaasFrontendFacade iSrPaasFrontendFacade;

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


    @Test
    public void initPaasFrontendFacade() throws NamingException {

        virtualHost1 = new VirtualHostVO("virtualHost1PaasFrontendFacade", "id1");
        virtualHost2 = new VirtualHostVO("virtualHost2PaasFrontendFacade", "id2");

        virtualHostVOList = new LinkedList<VirtualHostVO>();
        virtualHostVOList.add(virtualHost1);
        virtualHostVOList.add(virtualHost2);


        paasFrontend1 = new PaasFrontendVO();
        paasFrontend2 = new PaasFrontendVO();
    }

    @Test(dependsOnMethods="initPaasFrontendFacade")
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
        iSrPaasFrontendFacade.updateFrontend(paasFrontend1);
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
