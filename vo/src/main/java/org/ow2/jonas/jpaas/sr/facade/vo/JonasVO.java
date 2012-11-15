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
import org.ow2.jonas.jpaas.sr.model.Jonas;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Define a Jonas Value Object
 * @author David Richard
 */
public class JonasVO extends PaasContainerVO implements java.io.Serializable {

    /**
     * Version of the Jonas.
     */
    private String jonasVersion;

    /**
     * Profile of the Jonas.
     */
    private String profile;

    /**
     * Version of the JDK.
     */
    private String jdkVersion;

    /**
     * Domain of the Jonas.
     */
    private String domain;

    /**
     * Connectors of the Jonas.
     */
    private List<ConnectorVO> connectorList;

    /**
     * Datasources of the Jonas.
     */
    private List<DatasourceVO> datasourceList;


    public JonasVO() {
        super();
        this.connectorList = new LinkedList<ConnectorVO>();
        this.datasourceList = new LinkedList<DatasourceVO>();
    }

    public JonasVO(String id, String name, String state, Map<String,String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts, String jonasVersion, String profile,
            String jdkVersion, String domain) {
        super(id, name, state, capabilities, multitenant, reusable, usedPorts);
        this.jonasVersion = jonasVersion;
        this.profile = profile;
        this.jdkVersion = jdkVersion;
        this.domain = domain;
        this.connectorList = new LinkedList<ConnectorVO>();
        this.datasourceList = new LinkedList<DatasourceVO>();
    }

    public JonasVO(String name, String state, Map<String,String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts, String jonasVersion, String profile,
            String jdkVersion, String domain) {
        super(name, state, capabilities, multitenant, reusable, usedPorts);
        this.jonasVersion = jonasVersion;
        this.profile = profile;
        this.jdkVersion = jdkVersion;
        this.domain = domain;
        this.connectorList = new LinkedList<ConnectorVO>();
        this.datasourceList = new LinkedList<DatasourceVO>();
    }

    public String getJonasVersion() {
        return jonasVersion;
    }

    public void setJonasVersion(String jonasVersion) {
        this.jonasVersion = jonasVersion;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getJdkVersion() {
        return jdkVersion;
    }

    public void setJdkVersion(String jdkVersion) {
        this.jdkVersion = jdkVersion;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JonasVO[Id=").append(getId())
                .append(", name=").append(getName())
                .append(", state=").append(getState())
                .append(", jonasVersion=").append(getJonasVersion())
                .append(", profile=").append(getProfile())
                .append(", jdkVersion=").append(getJdkVersion())
                .append(", domain=").append(getDomain())
                .append("]");
        return sb.toString();
    }

    /**
     * Change a Jonas Value Object into an Entity object
     * @return a Jonas Entity
     */
    public Jonas createBean() {
        Jonas jonas = new Jonas();
        jonas.setName(getName());
        jonas.setState(getState());
        jonas.setCapabilities(getCapabilities());
        jonas.setMultitenant(isMultitenant());
        jonas.setReusable(isReusable());
        jonas.setUsedPorts(getUsedPorts());
        jonas.setJonasVersion(jonasVersion);
        jonas.setJdkVersion(jdkVersion);
        jonas.setDomain(domain);
        jonas.setProfile(profile);
        if (connectorList != null) {
            List<Connector> list = new LinkedList<Connector>();
            for (ConnectorVO tmp : connectorList ) {
                Connector connector = tmp.createBean();
                connector.setJonas(jonas);
                list.add(connector);
            }
            jonas.setConnectorList(list);
        }
        if (datasourceList != null) {
            List<Datasource> list = new LinkedList<Datasource>();
            for (DatasourceVO tmp : datasourceList ) {
                Datasource datasource = tmp.createBean();
                datasource.setJonas(jonas);
                list.add(datasource);
            }
            jonas.setDatasourceList(list);
        }
        return jonas;
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
