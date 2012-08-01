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

import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasContainerPaasDatabaseLink;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasDatabaseFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasContainerVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasDatabaseVO;
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
 * PaasContainerPaasDatabaseLink test case
 * @author David Richard
 */
public class TestPaasContainerPaasDatabaseLink {
    /**
     * PaasJonasContainer Facade
     */
    private ISrPaasJonasContainerFacade iSrPaasJonasContainerFacade = null;

    /**
     * PaasDatabase Facade
     */
    private ISrPaasDatabaseFacade iSrPaasDatabaseFacade = null;

    /**
     * PaasDatabase Facade
     */
    private ISrPaasContainerPaasDatabaseLink iSrPaasContainerPaasDatabaseLink = null;

    /**
     * Jonas 1 value object
     */
    private JonasVO jonas1;

    /**
     * Jonas 1 value object
     */
    private JonasVO jonas2;

    /**
     * PaasDatabase 1 value object
     */
    private PaasDatabaseVO paasDatabase1;

    /**
     * PaasDatabase 2 value object
     */
    private PaasDatabaseVO paasDatabase2;

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
        this.iSrPaasJonasContainerFacade = (ISrPaasJonasContainerFacade) initialContext.lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade");
        this.iSrPaasDatabaseFacade = (ISrPaasDatabaseFacade) initialContext.lookup("java:global/" + moduleName +
                "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasDatabaseFacade");
        this.iSrPaasContainerPaasDatabaseLink = (ISrPaasContainerPaasDatabaseLink) initialContext.lookup("java:global/"
                + moduleName + "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasContainerPaasDatabaseLink");
    }

    private void initDatabase() {
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
        paasDatabase1 = new PaasDatabaseVO("paasDatabase1", "state", capabilitiesList, true, true, usedPorts);
        paasDatabase2 = new PaasDatabaseVO("paasDatabase2", "state", capabilitiesList, false, false, usedPorts);

        jonas1 = iSrPaasJonasContainerFacade.createJonasContainer(jonas1);
        jonas2 = iSrPaasJonasContainerFacade.createJonasContainer(jonas2);
        paasDatabase1 = iSrPaasDatabaseFacade.createDatabase(paasDatabase1);
        paasDatabase2 = iSrPaasDatabaseFacade.createDatabase(paasDatabase2);
    }

    @AfterClass
    public void cleanDatabase() {
        iSrPaasJonasContainerFacade.deleteJonasContainer(jonas1.getId());
        iSrPaasJonasContainerFacade.deleteJonasContainer(jonas2.getId());
        iSrPaasDatabaseFacade.deleteDatabase(paasDatabase1.getId());
        iSrPaasDatabaseFacade.deleteDatabase(paasDatabase2.getId());
    }

    @Test
    public void testAddContainerDatabaseLink() {
        iSrPaasContainerPaasDatabaseLink.addContainerDatabaseLink(jonas1.getId(), paasDatabase1.getId());
        iSrPaasContainerPaasDatabaseLink.addContainerDatabaseLink(jonas2.getId(), paasDatabase1.getId());
        iSrPaasContainerPaasDatabaseLink.addContainerDatabaseLink(jonas1.getId(), paasDatabase2.getId());
    }

    @Test(dependsOnMethods="testAddContainerDatabaseLink")
    public void testFindContainersByDatabase() {
        List<PaasContainerVO> paasContainerVOList =
                iSrPaasContainerPaasDatabaseLink.findContainersByDatabase(paasDatabase1.getId());
        Assert.assertEquals(paasContainerVOList.size(), 2);
        paasContainerVOList = iSrPaasContainerPaasDatabaseLink.findContainersByDatabase(paasDatabase2.getId());
        Assert.assertEquals(paasContainerVOList.size(), 1);
        Assert.assertEquals(paasContainerVOList.get(0).getId(), jonas1.getId());
    }

    @Test(dependsOnMethods="testFindContainersByDatabase")
    public void testFindDatabasesByContainer() {
        List<PaasDatabaseVO> paasDatabaseVOList = iSrPaasContainerPaasDatabaseLink.findDatabasesByContainer(jonas1.getId());
        Assert.assertEquals(paasDatabaseVOList.size(), 2);
        paasDatabaseVOList = iSrPaasContainerPaasDatabaseLink.findDatabasesByContainer(jonas2.getId());
        Assert.assertEquals(paasDatabaseVOList.size(), 1);
        Assert.assertEquals(paasDatabaseVOList.get(0).getId(), paasDatabase1.getId());
    }

    @Test(dependsOnMethods = "testFindDatabasesByContainer")
    public void testRemoveContainerDatabaseLink() {
        iSrPaasContainerPaasDatabaseLink.removeContainerDatabaseLink(jonas1.getId(), paasDatabase2.getId());
        List<PaasDatabaseVO> paasDatabaseVOList =
                iSrPaasContainerPaasDatabaseLink.findDatabasesByContainer(jonas1.getId());
        Assert.assertEquals(paasDatabaseVOList.size(), 1);
        Assert.assertEquals(paasDatabaseVOList.get(0).getId(), paasDatabase1.getId());
        List<PaasContainerVO> paasContainerVOList =
                iSrPaasContainerPaasDatabaseLink.findContainersByDatabase(paasDatabase2.getId());
        Assert.assertEquals(paasContainerVOList.isEmpty(), true);

        iSrPaasContainerPaasDatabaseLink.removeContainerDatabaseLink(jonas2.getId(), paasDatabase1.getId());
        paasDatabaseVOList = iSrPaasContainerPaasDatabaseLink.findDatabasesByContainer(jonas2.getId());
        Assert.assertEquals(paasDatabaseVOList.isEmpty(), true);
        paasContainerVOList = iSrPaasContainerPaasDatabaseLink.findContainersByDatabase(paasDatabase1.getId());
        Assert.assertEquals(paasContainerVOList.size(), 1);
        Assert.assertEquals(paasContainerVOList.get(0).getId(), jonas1.getId());
    }
}
