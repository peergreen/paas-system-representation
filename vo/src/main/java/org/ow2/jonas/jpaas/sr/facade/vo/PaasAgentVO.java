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

import org.ow2.jonas.jpaas.sr.model.PaasAgent;

import java.util.List;
import java.util.Map;

/**
 * Define a PaasAgent Value Object
 * @author David Richard
 */
public class PaasAgentVO extends PaasEntityVO implements java.io.Serializable {

    /**
     * Api Url of the PaasAgent.
     */
    private String apiUrl;

    public PaasAgentVO() {
        super();
    }

    public PaasAgentVO(String id, String name, String state, Map<String,String> capabilities, boolean multitenant,
                       boolean reusable, List<Integer> usedPorts, String apiUrl) {
        super(id, name, state, capabilities, multitenant, reusable, usedPorts);
        this.apiUrl = apiUrl;
    }

    public PaasAgentVO(String name, String state, Map<String,String> capabilities, boolean multitenant,
                           boolean reusable, List<Integer> usedPorts, String apiUrl) {
            super(name, state, capabilities, multitenant, reusable, usedPorts);
            this.apiUrl = apiUrl;
        }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PaasAgentVO[Id=").append(getId())
                .append(", name=").append(getName())
                .append(", state=").append(getState())
                .append(", apiUrl=").append(getApiUrl())
                .append("]");
        return sb.toString();
    }

    /**
     * Change a PaasAgent Value Object into an Entity object
     * @return a PaasAgent Entity
     */
    public PaasAgent createBean() {
        PaasAgent paasAgent = new PaasAgent();
        paasAgent.setName(getName());
        paasAgent.setState(getState());
        paasAgent.setCapabilities(getCapabilities());
        paasAgent.setMultitenant(isMultitenant());
        paasAgent.setReusable(isReusable());
        paasAgent.setUsedPorts(getUsedPorts());
        paasAgent.setApiUrl(apiUrl);
        return paasAgent;
    }
}
