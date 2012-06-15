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
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasResourceIaasComputeLink;
import org.ow2.jonas.jpaas.sr.facade.vo.IaasComputeVO;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasResourceVO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.LinkedList;
import java.util.List;

/**
 * PaasResourceIaasComputeLink test case
 * @author David Richard
 */
public class TestPaasResourceIaasComputeLink {
    /**
     * PaasJonasContainer Facade
     */
    private ISrPaasJonasContainerFacade iSrPaasJonasContainerFacade = null;

    /**
     * IaasCompute Facade
     */
    private ISrIaasComputeFacade iSrIaasComputeFacade = null;

    /**
     * IaasCompute Facade
     */
    private ISrPaasResourceIaasComputeLink iSrPaasResourceIaasComputeLink = null;

    /**
     * Jonas 1 value object
     */
    private JonasVO jonas1;

    /**
     * Jonas 1 value object
     */
    private JonasVO jonas2;

    /**
     * Jonas 1 value object
     */
    private JonasVO jonas3;

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
        initDatabase();
    }


    private void getBean() throws NamingException {
        InitialContext initialContext = new InitialContext();
        this.iSrPaasJonasContainerFacade = (ISrPaasJonasContainerFacade) initialContext.lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade");
        this.iSrIaasComputeFacade = (ISrIaasComputeFacade) initialContext.lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrIaasComputeFacade");
        this.iSrPaasResourceIaasComputeLink = (ISrPaasResourceIaasComputeLink) initialContext.lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasResourceIaasComputeLink");
    }

    private void initDatabase() {
        List<String> capabilitesList = new LinkedList<String>();
        capabilitesList.add("capability 1");
        capabilitesList.add("capability 2");

        List<Integer> usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        jonas1 = new JonasVO("jonas1", "state", capabilitesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas2 = new JonasVO("jonas2", "state", capabilitesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas3 = new JonasVO("jonas3", "state", capabilitesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        iaasCompute1 = new IaasComputeVO("iaasCompute1", "state", capabilitesList, true, true, usedPorts, "ipAddress",
                "hostname", "conf", "role");
        iaasCompute2 = new IaasComputeVO("iaasCompute2", "state", capabilitesList, false, false, usedPorts, "ipAddress",
                "hostname", "conf", "role");

        jonas1 = iSrPaasJonasContainerFacade.createJonasContainer(jonas1);
        jonas2 = iSrPaasJonasContainerFacade.createJonasContainer(jonas2);
        jonas3 = iSrPaasJonasContainerFacade.createJonasContainer(jonas3);
        iaasCompute1 = iSrIaasComputeFacade.createIaasCompute(iaasCompute1);
        iaasCompute2 = iSrIaasComputeFacade.createIaasCompute(iaasCompute2);
    }

    @AfterClass
    public void cleanDatabase() {
        iSrPaasJonasContainerFacade.deleteJonasContainer(jonas1.getId());
        iSrPaasJonasContainerFacade.deleteJonasContainer(jonas2.getId());
        iSrPaasJonasContainerFacade.deleteJonasContainer(jonas3.getId());
        iSrIaasComputeFacade.deleteIaasCompute(iaasCompute1.getId());
        iSrIaasComputeFacade.deleteIaasCompute(iaasCompute2.getId());
    }

    @Test
    public void testAddResourceIaasComputeLink() {
        iSrPaasResourceIaasComputeLink.addPaasResourceIaasComputeLink(jonas1.getId(), iaasCompute1.getId());
        iSrPaasResourceIaasComputeLink.addPaasResourceIaasComputeLink(jonas2.getId(), iaasCompute1.getId());
        iSrPaasResourceIaasComputeLink.addPaasResourceIaasComputeLink(jonas3.getId(), iaasCompute2.getId());
    }

    @Test(dependsOnMethods="testAddResourceIaasComputeLink")
    public void testFindResourcesByIaasCompute() {
        List<PaasResourceVO> paasResourceVOList =
                iSrPaasResourceIaasComputeLink.findPaasResourcesByIaasCompute(iaasCompute1.getId());
        Assert.assertEquals(paasResourceVOList.size(), 2);
        paasResourceVOList = iSrPaasResourceIaasComputeLink.findPaasResourcesByIaasCompute(iaasCompute2.getId());
        Assert.assertEquals(paasResourceVOList.size(), 1);
        Assert.assertEquals(paasResourceVOList.get(0).getId(), jonas3.getId());
    }

    @Test(dependsOnMethods="testFindResourcesByIaasCompute")
    public void testFindIaasComputesByResource() {
        IaasComputeVO iaasComputeVO = iSrPaasResourceIaasComputeLink.findIaasComputeByPaasResource(jonas1.getId());
        Assert.assertEquals(iaasComputeVO.getId(), iaasCompute1.getId());
    }

    @Test(dependsOnMethods = "testFindIaasComputesByResource")
    public void testRemoveResourceIaasComputeLink() {
        iSrPaasResourceIaasComputeLink.removePaasResourceIaasComputeLink(jonas1.getId(), iaasCompute1.getId());
        List<PaasResourceVO> paasResourceVOList =
                iSrPaasResourceIaasComputeLink.findPaasResourcesByIaasCompute(iaasCompute1.getId());
        Assert.assertEquals(paasResourceVOList.size(), 1);

        iSrPaasResourceIaasComputeLink.removePaasResourceIaasComputeLink(jonas2.getId(), iaasCompute1.getId());
        paasResourceVOList = iSrPaasResourceIaasComputeLink.findPaasResourcesByIaasCompute(iaasCompute1.getId());
        Assert.assertEquals(paasResourceVOList.isEmpty(), true);
    }
}
