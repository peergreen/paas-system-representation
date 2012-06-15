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
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasFrontendFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasRouterFrontendLink;
import org.ow2.jonas.jpaas.sr.facade.vo.ApacheJkVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasFrontendVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasRouterVO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.LinkedList;
import java.util.List;

/**
 * PaasRouterFrontendLink test case
 * @author David Richard
 */
public class TestPaasRouterFrontendLink {

    /**
     * PaasApacheJkRouter Facade
     */
    private ISrPaasApacheJkRouterFacade iSrPaasApacheJkRouterFacade = null;

    /**
     * ISrPaasFrontend Facade
     */
    private ISrPaasFrontendFacade iSrPaasFrontendFacade = null;

    /**
     * PaasRouterFrontendLink Facade
     */
    private ISrPaasRouterFrontendLink iSrPaasRouterFrontendLink = null;

    /**
     * ApacheJk 1 value object
     */
    private ApacheJkVO apacheJk1;

    /**
     * ApacheJk 2 value object
     */
    private ApacheJkVO apacheJk2;

    /**
     * PaasFrontend 1 value object
     */
    private PaasFrontendVO paasFrontend1 ;

    /**
     * PaasFrontend 2 value object
     */
    private PaasFrontendVO paasFrontend2;


    @BeforeClass
    public void init() throws NamingException {
        getBean();
        initDatabase();
    }


    private void getBean() throws NamingException {
        InitialContext initialContext = new InitialContext();
        this.iSrPaasApacheJkRouterFacade = (ISrPaasApacheJkRouterFacade) initialContext.lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!org.ow2.jonas.jpaas.sr.facade.api.ISrPaasApacheJkRouterFacade");
        this.iSrPaasFrontendFacade = (ISrPaasFrontendFacade) initialContext.lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasFrontendFacade");
        this.iSrPaasRouterFrontendLink = (ISrPaasRouterFrontendLink) initialContext.lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasRouterFrontendLink");
    }

    private void initDatabase() {

        List<String> capabilitesList = new LinkedList<String>();
        capabilitesList.add("capability 1");
        capabilitesList.add("capability 2");

        List<Integer> usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        apacheJk1 = new ApacheJkVO("apacheJk1", "state", capabilitesList, true, true, usedPorts, "apacheVersion",
                "jkVersion");
        apacheJk2 = new ApacheJkVO("apacheJk2", "state", capabilitesList, false, false, usedPorts, "apacheVersion2",
                "jkVersion2");
        apacheJk1 = iSrPaasApacheJkRouterFacade.createApacheJkRouter(apacheJk1);
        apacheJk2 = iSrPaasApacheJkRouterFacade.createApacheJkRouter(apacheJk2);

        paasFrontend1 = new PaasFrontendVO();
        paasFrontend2 = new PaasFrontendVO();
        paasFrontend1 = iSrPaasFrontendFacade.createFrontend(paasFrontend1);
        paasFrontend2 = iSrPaasFrontendFacade.createFrontend(paasFrontend2);
    }

    @AfterClass
    public void cleanDatabase() {
        iSrPaasFrontendFacade.deleteFrontend(paasFrontend1.getId());
        iSrPaasFrontendFacade.deleteFrontend(paasFrontend2.getId());
        iSrPaasApacheJkRouterFacade.deleteApacheJkRouter(apacheJk1.getId());
        iSrPaasApacheJkRouterFacade.deleteApacheJkRouter(apacheJk2.getId());
    }

    @Test
    public void testAddPaasRouterFrontendLink() {
        iSrPaasRouterFrontendLink.addPaasRouterFrontendLink(apacheJk1.getId(), paasFrontend1.getId());
        iSrPaasRouterFrontendLink.addPaasRouterFrontendLink(apacheJk1.getId(), paasFrontend2.getId());
        iSrPaasRouterFrontendLink.addPaasRouterFrontendLink(apacheJk2.getId(), paasFrontend2.getId());
    }

    @Test(dependsOnMethods="testAddPaasRouterFrontendLink")
    public void testFindPaasRoutersByFrontend() {
        List<PaasRouterVO> paasRouterVOList = iSrPaasRouterFrontendLink.findPaasRoutersByFrontend(
                paasFrontend1.getId());
        Assert.assertEquals(paasRouterVOList.size(), 1);
        paasRouterVOList = iSrPaasRouterFrontendLink.findPaasRoutersByFrontend(paasFrontend2.getId());
        Assert.assertEquals(paasRouterVOList.size(), 2);
        Assert.assertEquals(paasRouterVOList.get(0).getId(), apacheJk1.getId());
    }

    @Test(dependsOnMethods="testFindPaasRoutersByFrontend")
    public void testFindFrontendsByPaasRouter() {
        List<PaasFrontendVO> paasFrontendVOList =
                iSrPaasRouterFrontendLink.findFrontendsByPaasRouter(apacheJk1.getId());
        Assert.assertEquals(paasFrontendVOList.size(), 2);
        Assert.assertEquals(paasFrontendVOList.get(0).getId(), paasFrontend1.getId());
    }

    @Test(dependsOnMethods = "testFindFrontendsByPaasRouter")
    public void testRemovePaasRouterFrontendLink() {
        iSrPaasRouterFrontendLink.removePaasRouterFrontendLink(apacheJk1.getId(), paasFrontend1.getId());
        List<PaasRouterVO> paasRouterVOList = iSrPaasRouterFrontendLink.findPaasRoutersByFrontend(paasFrontend1.getId());
        Assert.assertEquals(paasRouterVOList.isEmpty(), true);
        List<PaasFrontendVO> paasFrontendVOList = iSrPaasRouterFrontendLink.findFrontendsByPaasRouter(apacheJk1.getId());
        Assert.assertEquals(paasFrontendVOList.size(), 1);

        iSrPaasRouterFrontendLink.removePaasRouterFrontendLink(apacheJk2.getId(), paasFrontend2.getId());
        paasRouterVOList =
                iSrPaasRouterFrontendLink.findPaasRoutersByFrontend(paasFrontend2.getId());
        Assert.assertEquals(paasRouterVOList.size(), 1);
        paasFrontendVOList =
                iSrPaasRouterFrontendLink.findFrontendsByPaasRouter(apacheJk2.getId());
        Assert.assertEquals(paasFrontendVOList.isEmpty(), true);
    }
}
