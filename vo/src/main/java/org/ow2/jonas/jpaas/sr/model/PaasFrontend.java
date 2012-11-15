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

import org.ow2.jonas.jpaas.sr.facade.vo.PaasFrontendVO;
import org.ow2.jonas.jpaas.sr.facade.vo.VirtualHostVO;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

/**
 * Define a PaasFrontend
 * @author David Richard
 */
@javax.persistence.Entity
@Table
public class PaasFrontend implements java.io.Serializable {

    /**
     * key of the PaasFrontend.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Id of the PaasFrontend.
     */
    private String id;

    /**
     * Name of the PaasFrontend
     */
    private String name;

    /**
     * Url of the agent API
     */
    private String apiUrl;

    /**
     * VirtualHosts of the PaasFrontend.
     */
    @OneToMany(mappedBy="paasFrontend", orphanRemoval=true, cascade={CascadeType.ALL})
    private List<VirtualHost> virtualHosts;

    /**
     * PaasRouters of the VirtualHost.
     */
    @OneToMany(mappedBy= "paasFrontend", cascade = CascadeType.MERGE)
    private List<PaasRouter> paasRouterList;


    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PaasFrontend[key=").append(getKey())
                .append(", id=").append(getId())
                .append(", name=").append(getName())
                .append(", apiUrl=").append(getApiUrl())
                .append("]");
        return sb.toString();
    }

    public List<VirtualHost> getVirtualHosts() {
        return virtualHosts;
    }

    public void setVirtualHosts(List<VirtualHost> virtualHostList) {
        this.virtualHosts = virtualHostList;
    }

    public PaasFrontendVO createPaasFrontendVO() {
        List<VirtualHostVO> virtualHostVOList = new LinkedList<VirtualHostVO>();
        for (VirtualHost tmp : virtualHosts) {
            virtualHostVOList.add(tmp.createVirtualHostVO());
        }
        return new PaasFrontendVO(id, name, apiUrl, virtualHostVOList);
    }

    public void mergePaasFrontendVO(PaasFrontendVO paasFrontendVO) {
        this.name = paasFrontendVO.getName();
        this.apiUrl = paasFrontendVO.getApiUrl();
        if (paasFrontendVO.getVirtualHosts() != null) {
            LinkedList<VirtualHost> originalVirtualHostList = new LinkedList<VirtualHost>(this.virtualHosts);
            this.virtualHosts.clear();
            for (VirtualHostVO tmp : paasFrontendVO.getVirtualHosts()) {
                VirtualHost virtualHost = tmp.createBean();
                for (VirtualHost originalVirtualHost : originalVirtualHostList) {
                    if (originalVirtualHost.getName().equals(virtualHost.getName())) {
                        virtualHost.setKey(originalVirtualHost.getKey());
                        break;
                    }
                }
                this.virtualHosts.add(virtualHost);
            }
        }
    }

    public List<PaasRouter> getPaasRouterList() {
        return paasRouterList;
    }

    public void setPaasRouterList(List<PaasRouter> paasRouterList) {
        this.paasRouterList = paasRouterList;
    }
}
