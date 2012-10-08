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

package org.ow2.jonas.jpaas.sr.model;

import org.ow2.jonas.jpaas.sr.facade.vo.IaasComputeVO;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Define an IaasCompute
 * @author David Richard
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class IaasCompute extends IaasResource implements java.io.Serializable {

    /**
     * IP Address of the IaasCompute.
     */
    private String ipAddress;

    /**
     * Hostname of the IaasCompute.
     */
    private String hostname;

    /**
     * Conf of the IaasCompute.
     */
    private String conf;

    /**
     * Roles of the IaasCompute.
     */
    @ElementCollection
    private List<String> roles;

    /**
     * PaasEntities of the IaasCompute.
     */
    @OneToMany(mappedBy="iaasCompute", orphanRemoval=true, cascade={CascadeType.ALL})
    private List<PaasEntity> paasEntityList;


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String name) {
        this.hostname = name;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String description) {
        this.conf = description;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IaasCompute[Id=").append(getId())
                .append(", name=").append(getName())
                .append(", state=").append(getState())
                .append(", ipAddress=").append(getIpAddress())
                .append(", hostname=").append(getHostname())
                .append(", conf=").append(getConf())
                .append(", roles=").append(getRoles().toString())
                .append("]");
        return sb.toString();
    }

    public IaasComputeVO createVO() {
        return new IaasComputeVO(getId(), getName(), getState(), new HashMap<String,String>(getCapabilities()),
                isMultitenant(), isReusable(), new LinkedList<Integer>(getUsedPorts()), ipAddress, hostname,
                conf, new LinkedList<String>(getRoles()));
    }

    public void mergeIaasComputeVO(IaasComputeVO iaasComputeVO) {
        setName(iaasComputeVO.getName());
        setState(iaasComputeVO.getState());
        setCapabilities(iaasComputeVO.getCapabilities());
        setMultitenant(iaasComputeVO.isMultitenant());
        setReusable(iaasComputeVO.isReusable());
        setUsedPorts(iaasComputeVO.getUsedPorts());
        this.ipAddress = iaasComputeVO.getIpAddress();
        this.hostname = iaasComputeVO.getHostname();
        this.conf = iaasComputeVO.getConf();
        this.roles = iaasComputeVO.getRoles();
    }

    public List<PaasEntity> getPaasEntityList() {
        return paasEntityList;
    }

    public void setPaasEntityList(List<PaasEntity> paasEntityList) {
        this.paasEntityList = paasEntityList;
    }
}
