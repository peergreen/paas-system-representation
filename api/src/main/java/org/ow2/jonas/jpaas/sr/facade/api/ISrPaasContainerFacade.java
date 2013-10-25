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

import java.util.List;

import org.ow2.jonas.jpaas.sr.facade.vo.ConnectorVO;
import org.ow2.jonas.jpaas.sr.facade.vo.DatasourceVO;

/**
 * Interface for the PaasContainer facade.
 * @author Florent Benoit
 */
public interface ISrPaasContainerFacade {

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
