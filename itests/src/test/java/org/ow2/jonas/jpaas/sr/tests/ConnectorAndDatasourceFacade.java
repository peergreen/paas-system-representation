package org.ow2.jonas.jpaas.sr.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;
import org.ops4j.pax.exam.testng.listener.PaxExam;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasPeergreenServerContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.ConnectorVO;
import org.ow2.jonas.jpaas.sr.facade.vo.DatasourceVO;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PeergreenServerVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class ConnectorAndDatasourceFacade extends SetupTest {

    /**
     * PaasJonasContainer Facade
     */
    @Inject
    private ISrPaasContainerFacade paasContainerFacade;

    @Inject
    private ISrPaasJonasContainerFacade jonasContainerFacade;


    @Inject
    private ISrPaasPeergreenServerContainerFacade peergreenServerContainerFacade;


    /**
     * Capabilities list
     */
    private Map<String,String> capabilitiesList;

    /**
     * usedPorts list
     */
    private List<Integer> usedPorts;

    /**
     * Jonas
     */
    private JonasVO jonas;

    /**
     * Peergreen server.
     */
    private PeergreenServerVO peergreenServer;



    @Test
    public void initConnectors() throws NamingException {

        capabilitiesList = new HashMap<String,String>();

        usedPorts = new LinkedList<Integer>();

        jonas = new JonasVO("jonas1", "state", capabilitiesList, true, true, usedPorts, "jonasVersion", "profile",
                "jdkVersion", "domain");

        jonas = jonasContainerFacade.createJonasContainer(jonas);

        peergreenServer = new PeergreenServerVO("peergreen1", "state", capabilitiesList, false, false, usedPorts, "jonasVersion", "profile");
        peergreenServer = peergreenServerContainerFacade.createPeergreenServerContainer(peergreenServer);
    }



    @Test(dependsOnMethods="initConnectors")
    public void testAddConnector() {
        paasContainerFacade.addConnector(jonas.getId(), "connector1", 10);
        paasContainerFacade.addConnector(jonas.getId(), "connector2", 11);

        paasContainerFacade.addConnector(peergreenServer.getId(), "connector1", 100);
        paasContainerFacade.addConnector(peergreenServer.getId(), "connector2", 101);

    }

    @Test(dependsOnMethods = "testAddConnector")
    public void testGetConnectors() {
        assertEquals(paasContainerFacade.getConnectors(jonas.getId()).size(), 2);
        assertEquals(paasContainerFacade.getConnectors(peergreenServer.getId()).size(), 2);

    }

    @Test(dependsOnMethods = "testGetConnectors")
    public void testRemoveConnector() {
        paasContainerFacade.removeConnector(jonas.getId(), "connector1");
        List<ConnectorVO> connectorList = paasContainerFacade.getConnectors(jonas.getId());
        assertEquals(connectorList.size(), 1);
        paasContainerFacade.removeConnector(jonas.getId(), "connector2");
        connectorList = paasContainerFacade.getConnectors(jonas.getId());
        assertEquals(connectorList.isEmpty(), true);

        paasContainerFacade.removeConnector(peergreenServer.getId(), "connector1");
        connectorList = paasContainerFacade.getConnectors(peergreenServer.getId());
        assertEquals(connectorList.size(), 1);


    }

    @Test(dependsOnMethods = "testRemoveConnector")
    public void testAddDatasource() {
        paasContainerFacade.addDatasource(jonas.getId(), "datasource1", "url", "className");
        paasContainerFacade.addDatasource(jonas.getId(), "datasource2", "url2", "className2");

        paasContainerFacade.addDatasource(peergreenServer.getId(), "datasource1", "url", "className");
        paasContainerFacade.addDatasource(peergreenServer.getId(), "datasource2", "url2", "className2");

    }

    @Test(dependsOnMethods = "testAddDatasource")
    public void testGetDatasources() {
        assertEquals(paasContainerFacade.getDatasources(jonas.getId()).size(), 2);
        assertEquals(paasContainerFacade.getDatasources(peergreenServer.getId()).size(), 2);
    }

    @Test(dependsOnMethods = "testGetDatasources")
    public void testRemoveDatasource() {
        paasContainerFacade.removeDatasource(jonas.getId(), "datasource1");
        List<DatasourceVO> datasourceList = paasContainerFacade.getDatasources(jonas.getId());
        Assert.assertEquals(datasourceList.size(), 1);
        paasContainerFacade.removeDatasource(jonas.getId(), "datasource2");
        datasourceList = paasContainerFacade.getDatasources(jonas.getId());
        Assert.assertEquals(datasourceList.isEmpty(), true);


        paasContainerFacade.removeDatasource(peergreenServer.getId(), "datasource1");
        datasourceList = paasContainerFacade.getDatasources(peergreenServer.getId());
        assertEquals(datasourceList.size(), 1);
        paasContainerFacade.removeDatasource(peergreenServer.getId(), "datasource2");
        datasourceList = paasContainerFacade.getDatasources(peergreenServer.getId());
        assertTrue(datasourceList.isEmpty());


    }
}
