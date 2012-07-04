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


import org.ow2.jonas.jpaas.sr.facade.vo.PaasAgentVO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
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
        sb.append("NodeTemplate[apiUrl=").append(getApiUrl())
                .append(", paasResourceList=").append(getPaasResourceList().toString())
                .append("]");
        return sb.toString();
    }

    public PaasAgentVO createPaasAgentVO() {
        return new PaasAgentVO(getId(), getName(), getState(), new LinkedList<String>(getCapabilities()),
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