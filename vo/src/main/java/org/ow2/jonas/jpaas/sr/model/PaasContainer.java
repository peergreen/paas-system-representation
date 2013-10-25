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

package org.ow2.jonas.jpaas.sr.model;


import org.ow2.jonas.jpaas.sr.facade.vo.ConnectorVO;
import org.ow2.jonas.jpaas.sr.facade.vo.DatasourceVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasContainerVO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Define a PaasContainer
 * @author David Richard
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PaasContainer extends PaasResource implements java.io.Serializable {

    /**
     * PaasDatabases of the PaasContainer.
     */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "PaasContainerPaasDatabaseLink",
            joinColumns = { @JoinColumn(name = "PaasContainer_key")},
            inverseJoinColumns={@JoinColumn(name="PaasDatabase_key")})
    private List<PaasDatabase> paasDatabaseList;

    /**
     * PaasRouters of the PaasContainer.
     */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "PaasRouterPaasContainerLink",
            joinColumns = { @JoinColumn(name = "PaasContainer_key")},
            inverseJoinColumns={@JoinColumn(name="PaasRouter_key")})
    private List<PaasRouter> paasRouterList;

    /**
     * Connectors of the container.
     */
    @OneToMany(mappedBy="container", orphanRemoval=true, cascade={CascadeType.ALL})
    private List<Connector> connectorList;

    /**
     * Datasources of the container.
     */
    @OneToMany(mappedBy="container", orphanRemoval=true, cascade={CascadeType.ALL})
    private List<Datasource> datasourceList;

    public List<PaasDatabase> getPaasDatabaseList() {
        return paasDatabaseList;
    }

    public void setPaasDatabaseList(List<PaasDatabase> paasDatabaseList) {
        this.paasDatabaseList = paasDatabaseList;
    }

    public List<PaasRouter> getPaasRouterList() {
        return paasRouterList;
    }

    public void setPaasRouterList(List<PaasRouter> paasRouterList) {
        this.paasRouterList = paasRouterList;
    }

    public PaasContainerVO createPaasContainerVO() {
        PaasContainerVO paasContainerVO =  new PaasContainerVO(getId(), getName(), getState(), new HashMap<String,String>(getCapabilities()),
                isMultitenant(), isReusable(), new LinkedList<Integer>(getUsedPorts()));

        updatePaasContainerVO(paasContainerVO);
        return paasContainerVO;
    }

    protected PaasContainerVO updatePaasContainerVO(PaasContainerVO paasContainerVO) {
        if (getConnectorList() != null) {
            for (Connector tmp : getConnectorList()) {
                paasContainerVO.getConnectorList().add(tmp.createConnectorVO());
            }
        }
        if (getDatasourceList() != null) {
            for (Datasource tmp : getDatasourceList()) {
                paasContainerVO.getDatasourceList().add(tmp.createDatasourceVO());
            }
        }

        return paasContainerVO;
    }




    public void mergePaasContainerVO(PaasContainerVO paasContainerVO) {
        setName(paasContainerVO.getName());
        setState(paasContainerVO.getState());
        setCapabilities(paasContainerVO.getCapabilities());
        setMultitenant(paasContainerVO.isMultitenant());
        setReusable(paasContainerVO.isReusable());
        setUsedPorts(paasContainerVO.getUsedPorts());
        if (paasContainerVO.getConnectorList() != null) {
            LinkedList<Connector> originalConnectorList = new LinkedList<Connector>(this.getConnectorList());
            this.getConnectorList().clear();
            for (ConnectorVO tmp : paasContainerVO.getConnectorList()) {
                Connector connector = tmp.createBean();
                for (Connector originalConnector : originalConnectorList) {
                    if (originalConnector.getName().equals(connector.getName())) {
                        connector.setKey(originalConnector.getKey());
                        break;
                    }
                }
                this.getConnectorList().add(connector);
            }
        }
        if (paasContainerVO.getDatasourceList() != null) {
            LinkedList<Datasource> originalDatasourceList = new LinkedList<Datasource>(this.datasourceList);
            this.datasourceList.clear();
            for (DatasourceVO tmp : paasContainerVO.getDatasourceList()) {
                Datasource datasource = tmp.createBean();
                for (Datasource originalDatasource : originalDatasourceList) {
                    if (originalDatasource.getName().equals(datasource.getName())) {
                        datasource.setKey(originalDatasource.getKey());
                        break;
                    }
                }
                this.datasourceList.add(datasource);
            }
        }


    }



    public List<Connector> getConnectorList() {
        return connectorList;
    }

    public void setConnectorList(List<Connector> connectorList) {
        this.connectorList = connectorList;
    }

    public List<Datasource> getDatasourceList() {
        return datasourceList;
    }

    public void setDatasourceList(List<Datasource> datasourceList) {
        this.datasourceList = datasourceList;
    }

}
