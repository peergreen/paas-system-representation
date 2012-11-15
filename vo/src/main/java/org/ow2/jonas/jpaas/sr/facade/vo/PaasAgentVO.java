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
