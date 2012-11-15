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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        this.iSrIaasComputeFacade = (ISrIaasComputeFacade) initialContext.lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrIaasComputeFacade");
        this.iSrPaasResourceIaasComputeLink = (ISrPaasResourceIaasComputeLink) initialContext.lookup("java:global/" +
                moduleName + "/SrFacadeBean!" +
                "org.ow2.jonas.jpaas.sr.facade.api.ISrPaasResourceIaasComputeLink");
    }

    private void initDatabase() {
        Map<String,String> capabilitiesList = new HashMap<String,String>();
        capabilitiesList.put("capability 1", "value");
        capabilitiesList.put("capability 2", "value");

        List<Integer> usedPorts = new LinkedList<Integer>();
        usedPorts.add(1);
        usedPorts.add(2);

        List<String> roles = new LinkedList<String>();
        roles.add("Jonas");

        jonas1 = new JonasVO("jonas1", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas2 = new JonasVO("jonas2", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        jonas3 = new JonasVO("jonas3", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");
        iaasCompute1 = new IaasComputeVO("iaasCompute1", "state", capabilitiesList, true, true, usedPorts, "ipAddress",
                "hostname", "conf", roles);
        iaasCompute2 = new IaasComputeVO("iaasCompute2", "state", capabilitiesList, false, false, usedPorts, "ipAddress",
                "hostname", "conf", roles);

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
