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


import org.ow2.jonas.jpaas.sr.facade.vo.PaasAgentVO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Define a PaasAgent
 * @author David Richard
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PaasAgent extends PaasEntity implements java.io.Serializable {

    /**
     * Api Url of the PaasAgent.
     */
    private String apiUrl;

    /**
     * PaasResources of the PaasAgent.
     */
    @OneToMany(mappedBy="paasAgent", orphanRemoval=true, cascade={CascadeType.ALL})
    private List<PaasResource> paasResourceList;

    public List<PaasResource> getPaasResourceList() {
        return paasResourceList;
    }

    public void setPaasResourceList(List<PaasResource> paasResourceList) {
        this.paasResourceList = paasResourceList;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PaasAgent[Id=").append(getId())
                .append(", name=").append(getName())
                .append(", state=").append(getState())
                .append(", apiUrl=").append(getApiUrl())
                .append("]");
        return sb.toString();
    }
    public PaasAgentVO createPaasAgentVO() {
        return new PaasAgentVO(getId(), getName(), getState(), new HashMap<String,String>(getCapabilities()),
                isMultitenant(), isReusable(), new LinkedList<Integer>(getUsedPorts()), apiUrl);
    }

    public void mergePaasAgentVO(PaasAgentVO paasAgentVO) {
        setName(paasAgentVO.getName());
        setState(paasAgentVO.getState());
        setCapabilities(paasAgentVO.getCapabilities());
        setMultitenant(paasAgentVO.isMultitenant());
        setReusable(paasAgentVO.isReusable());
        setUsedPorts(paasAgentVO.getUsedPorts());
        setApiUrl(paasAgentVO.getApiUrl());
    }
}
