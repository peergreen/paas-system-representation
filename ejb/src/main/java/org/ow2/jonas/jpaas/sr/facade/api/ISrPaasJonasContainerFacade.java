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

package org.ow2.jonas.jpaas.sr.facade.api;

import org.ow2.jonas.jpaas.sr.facade.vo.ConnectorVO;
import org.ow2.jonas.jpaas.sr.facade.vo.DatasourceVO;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;

import java.util.List;

/**
 * Interface for the PaasJonasContainer facade.
 * @author David Richard
 */
public interface ISrPaasJonasContainerFacade {

    /**
     * Create a PaasJonasContainer.
     *
     * @param jonasVO the PaasJonasContainer to create
     * @return the PaasJonasContainer created
     */
    public JonasVO createJonasContainer(JonasVO jonasVO);

    /**
     * Delete a PaasJonasContainer.
     *
     * @param paasResourceId Id of the PaasJonasContainer to delete
     */
    public void deleteJonasContainer(String paasResourceId);

    /**
     * Update a PaasJonasContainer.
     *
     * @param jonasVO the new PaasJonasContainer
     * @return the PaasJonasContainer updated
     */
    public JonasVO updateJonasContainer(JonasVO jonasVO);

    /**
     * Get a PaasJonasContainer.
     *
     * @param paasResourceId Id of the PaasJonasContainer
     * @return the PaasJonasContainer
     */
    public JonasVO getJonasContainer(String paasResourceId);

    /**
     * Get the PaasJonasContainers.
     *
     * @return a list of PaasJonasContainers
     */
    public List<JonasVO> findJonasContainers();

    /**
     * Add a Connector
     *
     * @param paasResourceId Id of the PaasJonasContainer
     * @param name the name of the Connector
     * @param port the port of the Connector
     */
    public void addConnector(String paasResourceId, String name, int port);

    /**
     * Remove a Connector
     *
     * @param name the name of the Connector
     */
    public void removeConnector(String paasResourceId, String name);

    /**
     * Get the Connectors
     *
     * @param paasResourceId Id of the PaasJonasContainer
     * @return a list with the Connectors
     */
    public List<ConnectorVO> getConnectors(String paasResourceId);

    /**
     * Add a Datasource
     *
     * @param paasResourceId Id of the PaasJonasContainer
     * @param name the name of the datasource
     * @param url the url of the datasource
     * @param className the className of the datasource
     */
    public void addDatasource(String paasResourceId, String name, String url, String className);

    /**
     * Remove a DataSource
     *
     * @param paasResourceId Id of the PaasJonasContainer
     * @param name the name of the datasource
     */
    public void removeDatasource(String paasResourceId, String name);

    /**
     * Get the Datasources
     *
     * @param paasResourceId Id of the PaasJonasContainer
     * @return a list with the Datasources
     */
    public List<DatasourceVO> getDatasources(String paasResourceId);
}
