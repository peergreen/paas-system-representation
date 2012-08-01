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
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasRouterPaasContainerLink;
import org.ow2.jonas.jpaas.sr.facade.vo.ApacheJkVO;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasContainerVO;
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
 * PaasRouterPaasContainerLink test case
 * @author David Richard
 */
public class TestPaasRouterPaasContainerLink {
    /**
     * PaasJonasContainer Facade
     */
    private ISrPaasJonasContainerFacade iSrPaasJonasContainerFacade = null;

    /**
     * PaasApacheJkRouter Facade
     */
    private ISrPaasApacheJkRouterFacade iSrPaasApacheJkRouterFacade = null;

    /**
     * PaasRouter Facade
     */
    private ISrPaasRouterPaasContainerLink iSrPaasContainerPaasRouterLink = null;

    /**
     * Jonas 1 value object
     */
    private JonasVO jonas1;

    /**
     * Jonas 1 value object
     */
    private JonasVO jonas2;

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
        initRouter();
    }


    private void getBean() throws NamingException {
        InitialContext initialContext = new InitialContext();
        this.iSrPaasJonasContainerFacade = (ISrPaasJonasContainerFacade) initialContext.lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade");
        this.iSrPaasApacheJkRouterFacade = (ISrPaasApacheJkRouterFacade) initialContext.lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasApacheJkRouterFacade");
        this.iSrPaasContainerPaasRouterLink = (ISrPaasRouterPaasContainerLink) initialContext.lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasRouterPaasContainerLink");
    }

    private void initRouter() {
        Map<String,String> capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        List<Integer> usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        jonas1 = new JonasVO("jonas1", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas2 = new JonasVO("jonas2", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        apacheJk1 = new ApacheJkVO("apacheJk1", "state", capabilitiesList, true, true, usedPorts, "apacheVersion",
                "jkVersion");
        apacheJk2 = new ApacheJkVO("apacheJk2", "state", capabilitiesList, false, false, usedPorts, "apacheVersion2",
                "jkVersion2");

        jonas1 = iSrPaasJonasContainerFacade.createJonasContainer(jonas1);
        jonas2 = iSrPaasJonasContainerFacade.createJonasContainer(jonas2);
        apacheJk1 = iSrPaasApacheJkRouterFacade.createApacheJkRouter(apacheJk1);
        apacheJk2 = iSrPaasApacheJkRouterFacade.createApacheJkRouter(apacheJk2);
    }

    @AfterClass
    public void cleanRouter() {
        iSrPaasJonasContainerFacade.deleteJonasContainer(jonas1.getId());
        iSrPaasJonasContainerFacade.deleteJonasContainer(jonas2.getId());
        iSrPaasApacheJkRouterFacade.deleteApacheJkRouter(apacheJk1.getId());
        iSrPaasApacheJkRouterFacade.deleteApacheJkRouter(apacheJk2.getId());
    }

    @Test
    public void testAddRouterContainerLink() {
        iSrPaasContainerPaasRouterLink.addRouterContainerLink(apacheJk1.getId(), jonas1.getId());
        iSrPaasContainerPaasRouterLink.addRouterContainerLink(apacheJk1.getId(), jonas2.getId());
        iSrPaasContainerPaasRouterLink.addRouterContainerLink(apacheJk2.getId(), jonas1.getId());
    }

    @Test(dependsOnMethods="testAddRouterContainerLink")
    public void testFindContainersByRouter() {
        List<PaasContainerVO> paasContainerVOList =
                iSrPaasContainerPaasRouterLink.findContainersByRouter(apacheJk1.getId());
        Assert.assertEquals(paasContainerVOList.size(), 2);
        paasContainerVOList = iSrPaasContainerPaasRouterLink.findContainersByRouter(apacheJk2.getId());
        Assert.assertEquals(paasContainerVOList.size(), 1);
        Assert.assertEquals(paasContainerVOList.get(0).getId(), jonas1.getId());
    }

    @Test(dependsOnMethods="testFindContainersByRouter")
    public void testFindRoutersByContainer() {
        List<PaasRouterVO> paasRouterVOList = iSrPaasContainerPaasRouterLink.findRoutersByContainer(jonas1.getId());
        Assert.assertEquals(paasRouterVOList.size(), 2);
        paasRouterVOList = iSrPaasContainerPaasRouterLink.findRoutersByContainer(jonas2.getId());
        Assert.assertEquals(paasRouterVOList.size(), 1);
        Assert.assertEquals(paasRouterVOList.get(0).getId(), apacheJk1.getId());
    }

    @Test(dependsOnMethods = "testFindRoutersByContainer")
    public void testRemoveRouterContainerLink() {
        iSrPaasContainerPaasRouterLink.removeRouterContainerLink(apacheJk2.getId(), jonas1.getId());
        List<PaasRouterVO> paasRouterVOList = iSrPaasContainerPaasRouterLink.findRoutersByContainer(jonas1.getId());
        Assert.assertEquals(paasRouterVOList.size(), 1);
        Assert.assertEquals(paasRouterVOList.get(0).getId(), apacheJk1.getId());
        List<PaasContainerVO> paasContainerVOList =
                iSrPaasContainerPaasRouterLink.findContainersByRouter(apacheJk2.getId());
        Assert.assertEquals(paasContainerVOList.isEmpty(), true);

        iSrPaasContainerPaasRouterLink.removeRouterContainerLink(apacheJk1.getId(), jonas2.getId());
        paasRouterVOList = iSrPaasContainerPaasRouterLink.findRoutersByContainer(jonas2.getId());
        Assert.assertEquals(paasRouterVOList.isEmpty(), true);
        paasContainerVOList = iSrPaasContainerPaasRouterLink.findContainersByRouter(apacheJk1.getId());
        Assert.assertEquals(paasContainerVOList.size(), 1);
        Assert.assertEquals(paasContainerVOList.get(0).getId(), jonas1.getId());
    }
}
