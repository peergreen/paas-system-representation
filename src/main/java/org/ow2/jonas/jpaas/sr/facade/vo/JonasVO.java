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

package org.ow2.jonas.jpaas.sr.facade.vo;

import org.ow2.jonas.jpaas.sr.model.Connector;
import org.ow2.jonas.jpaas.sr.model.Datasource;
import org.ow2.jonas.jpaas.sr.model.Jonas;

import java.util.LinkedList;
import java.util.List;

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
    }

    public JonasVO(String id, String name, String state, List<String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts, String jonasVersion, String profile,
            String jdkVersion, String domain) {
        super(id, name, state, capabilities, multitenant, reusable, usedPorts);
        this.jonasVersion = jonasVersion;
        this.profile = profile;
        this.jdkVersion = jdkVersion;
        this.domain = domain;
    }

    public JonasVO(String name, String state, List<String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts, String jonasVersion, String profile,
            String jdkVersion, String domain) {
        super(name, state, capabilities, multitenant, reusable, usedPorts);
        this.jonasVersion = jonasVersion;
        this.profile = profile;
        this.jdkVersion = jdkVersion;
        this.domain = domain;
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
