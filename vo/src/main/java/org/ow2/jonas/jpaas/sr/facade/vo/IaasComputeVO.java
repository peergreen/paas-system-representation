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

import org.ow2.jonas.jpaas.sr.model.IaasCompute;

import java.util.LinkedList;
import java.util.List;

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

    public IaasComputeVO(String id, String name, String state, List<String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts, String ipAddress, String hostname, String conf,
            List<String> roles) {
        super(id, name, state, capabilities, multitenant, reusable, usedPorts);
        this.ipAddress = ipAddress;
        this.hostname = hostname;
        this.conf = conf;
        this.roles = roles;
    }

    public IaasComputeVO(String name, String state, List<String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts, String ipAddress, String hostname, String conf,
            String role) {
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
        sb.append("IaasCompute[ipAddress=").append(getIpAddress())
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
