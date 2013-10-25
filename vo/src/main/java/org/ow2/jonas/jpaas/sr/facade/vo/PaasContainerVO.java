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

package org.ow2.jonas.jpaas.sr.facade.vo;

import org.ow2.jonas.jpaas.sr.model.Connector;
import org.ow2.jonas.jpaas.sr.model.Datasource;
import org.ow2.jonas.jpaas.sr.model.PaasContainer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Define a PaasContainer Value Object
 * @author David Richard
 */
public class PaasContainerVO extends PaasResourceVO implements java.io.Serializable {

    /**
     * Connectors of the Jonas.
     */
    private List<ConnectorVO> connectorList;

    /**
     * Datasources of the Jonas.
     */
    private List<DatasourceVO> datasourceList;

    public PaasContainerVO() {
        super();
        this.connectorList = new LinkedList<>();
        this.datasourceList = new LinkedList<>();
    }

    public PaasContainerVO(String id, String name, String state, Map<String,String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts) {
        super(id, name, state, capabilities, multitenant, reusable, usedPorts);
        this.connectorList = new LinkedList<>();
        this.datasourceList = new LinkedList<>();

    }

    public PaasContainerVO(String name, String state, Map<String,String> capabilities, boolean multitenant,
                boolean reusable, List<Integer> usedPorts) {
            super(name, state, capabilities, multitenant, reusable, usedPorts);
            this.connectorList = new LinkedList<>();
            this.datasourceList = new LinkedList<>();

        }

    /**
     * Change a PaasContainer Value Object into an Entity object
     * @return a PaasContainer Entity
     */
    public PaasContainer createBean() {
        PaasContainer paasContainer = new PaasContainer();
        mergeBean(paasContainer);
        return paasContainer;
    }


    protected PaasContainer mergeBean(PaasContainer paasContainer) {
        paasContainer.setName(getName());
        paasContainer.setState(getState());
        paasContainer.setCapabilities(getCapabilities());
        paasContainer.setMultitenant(isMultitenant());
        paasContainer.setReusable(isReusable());
        paasContainer.setUsedPorts(getUsedPorts());
        if (connectorList != null) {
            List<Connector> list = new LinkedList<Connector>();
            for (ConnectorVO tmp : connectorList ) {
                Connector connector = tmp.createBean();
                connector.setContainer(paasContainer);
                list.add(connector);
            }
            paasContainer.setConnectorList(list);
        }
        if (datasourceList != null) {
            List<Datasource> list = new LinkedList<Datasource>();
            for (DatasourceVO tmp : datasourceList ) {
                Datasource datasource = tmp.createBean();
                datasource.setContainer(paasContainer);
                list.add(datasource);
            }
            paasContainer.setDatasourceList(list);
        }

        return paasContainer;
    }


    public List<ConnectorVO> getConnectorList() {
        return connectorList;
    }

    public void setConnectorList(List<ConnectorVO> connectorList) {
        this.connectorList = connectorList;
    }

    public List<DatasourceVO> getDatasourceList() {
        return datasourceList;
    }

    public void setDatasourceList(List<DatasourceVO> datasourceList) {
        this.datasourceList = datasourceList;
    }
}
