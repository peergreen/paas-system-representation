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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    private PaasFrontendVO paasFrontend1;

    /**
     * Name of the module for the lookup
     */
    private final String moduleName = System.getProperty("module.name");


    @BeforeClass
    public void init() throws NamingException {
        getBean();
        initDatabase();
    }


    private void getBean() throws NamingException {
        InitialContext initialContext = new InitialContext();
        this.iSrPaasApacheJkRouterFacade = (ISrPaasApacheJkRouterFacade) initialContext.lookup("java:global/" +
                moduleName + "/SrFacadeBean!org.ow2.jonas.jpaas.sr.facade.api.ISrPaasApacheJkRouterFacade");
        this.iSrPaasFrontendFacade = (ISrPaasFrontendFacade) initialContext.lookup("java:global/" + moduleName +
                "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasFrontendFacade");
        this.iSrPaasRouterFrontendLink = (ISrPaasRouterFrontendLink) initialContext.lookup("java:global/" + moduleName +
                "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasRouterFrontendLink");
    }

    private void initDatabase() {

        Map<String,String> capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        List<Integer> usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        apacheJk1 = new ApacheJkVO("apacheJk1", "state", capabilitiesList, true, true, usedPorts, "apacheVersion",
                "jkVersion");
        apacheJk2 = new ApacheJkVO("apacheJk2", "state", capabilitiesList, false, false, usedPorts, "apacheVersion2",
                "jkVersion2");
        apacheJk1 = iSrPaasApacheJkRouterFacade.createApacheJkRouter(apacheJk1);
        apacheJk2 = iSrPaasApacheJkRouterFacade.createApacheJkRouter(apacheJk2);

        paasFrontend1 = new PaasFrontendVO();
        paasFrontend1 = iSrPaasFrontendFacade.createFrontend(paasFrontend1);
    }

    @AfterClass
    public void cleanDatabase() {
        iSrPaasFrontendFacade.deleteFrontend(paasFrontend1.getId());
        iSrPaasApacheJkRouterFacade.deleteApacheJkRouter(apacheJk1.getId());
        iSrPaasApacheJkRouterFacade.deleteApacheJkRouter(apacheJk2.getId());
    }

    @Test
    public void testAddPaasRouterFrontendLink() {
        iSrPaasRouterFrontendLink.addPaasRouterFrontendLink(apacheJk1.getId(), paasFrontend1.getId());
        iSrPaasRouterFrontendLink.addPaasRouterFrontendLink(apacheJk2.getId(), paasFrontend1.getId());
    }

    @Test(dependsOnMethods="testAddPaasRouterFrontendLink")
    public void testFindPaasRoutersByFrontend() {
        List<PaasRouterVO> paasRouterVOList =
                iSrPaasRouterFrontendLink.findPaasRoutersByFrontend(paasFrontend1.getId());
        Assert.assertEquals(paasRouterVOList.size(), 2);
        Assert.assertEquals(paasRouterVOList.get(0).getId(), apacheJk1.getId());
    }

    @Test(dependsOnMethods="testFindPaasRoutersByFrontend")
    public void testFindFrontendByPaasRouter() {
        PaasFrontendVO paasFrontendVO =
                iSrPaasRouterFrontendLink.findFrontendByPaasRouter(apacheJk1.getId());
        Assert.assertEquals(paasFrontendVO.getId(), paasFrontend1.getId());
        paasFrontendVO = iSrPaasRouterFrontendLink.findFrontendByPaasRouter(apacheJk2.getId());
        Assert.assertEquals(paasFrontendVO.getId(), paasFrontend1.getId());
    }

    @Test(dependsOnMethods = "testFindFrontendByPaasRouter")
    public void testRemovePaasRouterFrontendLink() {
        iSrPaasRouterFrontendLink.removePaasRouterFrontendLink(apacheJk1.getId(), paasFrontend1.getId());
        List<PaasRouterVO> paasRouterVOList =
                iSrPaasRouterFrontendLink.findPaasRoutersByFrontend(paasFrontend1.getId());
        Assert.assertEquals(paasRouterVOList.size(), 1);

        iSrPaasRouterFrontendLink.removePaasRouterFrontendLink(apacheJk2.getId(), paasFrontend1.getId());
        paasRouterVOList = iSrPaasRouterFrontendLink.findPaasRoutersByFrontend(paasFrontend1.getId());
        Assert.assertEquals(paasRouterVOList.isEmpty(), true);
    }
}
