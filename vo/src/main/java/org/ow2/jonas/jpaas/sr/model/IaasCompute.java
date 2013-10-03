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
     * Internal id of the resource into the underlying IaaS
     */
    private String internalId;

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

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
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
                .append(", internalId=").append(getInternalId().toString())
                .append("]");
        return sb.toString();
    }

    public IaasComputeVO createVO() {
        return new IaasComputeVO(getId(), getName(), getState(), new HashMap<String,String>(getCapabilities()),
                isMultitenant(), isReusable(), new LinkedList<Integer>(getUsedPorts()), ipAddress, hostname,
                conf, new LinkedList<String>(getRoles()),internalId);
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
        this.internalId = iaasComputeVO.getInternalId();
    }

    public List<PaasEntity> getPaasEntityList() {
        return paasEntityList;
    }

    public void setPaasEntityList(List<PaasEntity> paasEntityList) {
        this.paasEntityList = paasEntityList;
    }
}
