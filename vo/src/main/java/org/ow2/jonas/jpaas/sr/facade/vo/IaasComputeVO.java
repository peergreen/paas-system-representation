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

import org.ow2.jonas.jpaas.sr.model.IaasCompute;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Define an IaasCompute Value Object
 * @author David Richard
 */
public class IaasComputeVO extends IaasResourceVO implements java.io.Serializable {
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
    private List<String> roles;

    public IaasComputeVO() {
        super();
        roles = new LinkedList<String>();
    }

    public IaasComputeVO(String id, String name, String state, Map<String,String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts, String ipAddress, String hostname, String conf,
            List<String> roles) {
        super(id, name, state, capabilities, multitenant, reusable, usedPorts);
        this.ipAddress = ipAddress;
        this.hostname = hostname;
        this.conf = conf;
        this.roles = roles;
    }

    public IaasComputeVO(String name, String state, Map<String,String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts, String ipAddress, String hostname, String conf,
            List<String> roles) {
        super(name, state, capabilities, multitenant, reusable, usedPorts);
        this.ipAddress = ipAddress;
        this.hostname = hostname;
        this.conf = conf;
        this.roles = roles;
    }

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

    public void setConf(String conf) {
        this.conf = conf;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IaasComputeVO[Id=").append(getId())
                .append(", name=").append(getName())
                .append(", state=").append(getState())
                .append(", ipAddress=").append(getIpAddress())
                .append(", hostname=").append(getHostname())
                .append(", conf=").append(getConf())
                .append(", roles=").append(getRoles().toString())
                .append("]");
        return sb.toString();
    }

    /**
     * Change a IaasCompute Value Object into an Entity object
     * @return a IaasCompute Entity
     */
    public IaasCompute createBean() {
        IaasCompute iaasCompute = new IaasCompute();
        iaasCompute.setIpAddress(ipAddress);
        iaasCompute.setHostname(hostname);
        iaasCompute.setConf(conf);
        iaasCompute.setRoles(roles);
        iaasCompute.setMultitenant(isMultitenant());
        iaasCompute.setName(getName());
        iaasCompute.setState(getState());
        iaasCompute.setCapabilities(getCapabilities());
        iaasCompute.setReusable(isReusable());
        iaasCompute.setUsedPorts(getUsedPorts());
        return iaasCompute;
    }
}
