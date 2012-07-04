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

package org.ow2.jonas.jpaas.sr.facade.model;


import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;


/**
 * Define a Jonas
 * @author David Richard
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Jonas extends PaasContainer implements java.io.Serializable {

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
    @OneToMany(mappedBy="jonas", orphanRemoval=true, cascade={CascadeType.ALL})
    private List<Connector> connectorList;

    /**
     * Datasources of the Jonas.
     */
    @OneToMany(mappedBy="jonas", orphanRemoval=true, cascade={CascadeType.ALL})
    private List<Datasource> datasourceList;


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

    public JonasVO createJonasVO() {
        JonasVO jonasVO = new JonasVO(getId(), getName(), getState(), new LinkedList<String>(getCapabilities()),
                isMultitenant(), isReusable(), new LinkedList<Integer>(getUsedPorts()), jonasVersion, profile,
                jdkVersion, domain);
        if (connectorList != null) {
            for (Connector tmp : connectorList) {
                jonasVO.getConnectorList().add(tmp.createConnectorVO());
            }
        }
        if (datasourceList != null) {
            for (Datasource tmp : datasourceList) {
                jonasVO.getDatasourceList().add(tmp.createDatasourceVO());
            }
        }
        return jonasVO;
    }

    public void mergeJonasVO(JonasVO jonasVO) {
        setName(jonasVO.getName());
        setState(jonasVO.getState());
        setCapabilities(jonasVO.getCapabilities());
        setMultitenant(jonasVO.isMultitenant());
        setReusable(jonasVO.isReusable());
        setUsedPorts(jonasVO.getUsedPorts());
        setJonasVersion(jonasVO.getJonasVersion());
        setProfile(jonasVO.getProfile());
        setJdkVersion(jonasVO.getJdkVersion());
        setDomain(jonasVO.getDomain());
    }

}