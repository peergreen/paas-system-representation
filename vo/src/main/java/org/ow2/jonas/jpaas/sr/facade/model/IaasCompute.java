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

import org.ow2.jonas.jpaas.sr.facade.vo.IaasComputeVO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
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
     * Role of the IaasCompute.
     */
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IaasCompute[ipAddress=").append(getIpAddress())
                .append(", hostname=").append(getHostname())
                .append(", conf=").append(getConf())
                .append(", role=").append(getRole())
                .append("]");
        return sb.toString();
    }

    public IaasComputeVO createIaasComputeVO() {
        return new IaasComputeVO(getId(), getName(), getState(), new LinkedList<String>(getCapabilities()),
                isMultitenant(), isReusable(), new LinkedList<Integer>(getUsedPorts()), ipAddress, hostname,
                conf, role);
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
        this.role = iaasComputeVO.getRole();
    }

    public List<PaasEntity> getPaasEntityList() {
        return paasEntityList;
    }

    public void setPaasEntityList(List<PaasEntity> paasEntityList) {
        this.paasEntityList = paasEntityList;
    }
}
