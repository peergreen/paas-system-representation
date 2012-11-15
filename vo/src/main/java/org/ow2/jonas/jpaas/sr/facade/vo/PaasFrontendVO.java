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

import org.ow2.jonas.jpaas.sr.model.PaasFrontend;
import org.ow2.jonas.jpaas.sr.model.VirtualHost;

import java.util.LinkedList;
import java.util.List;

/**
 * Define a PaasFrontend Value Object
 * @author David Richard
 */
public class PaasFrontendVO implements java.io.Serializable {

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
    private List<VirtualHostVO> virtualHosts;

    public PaasFrontendVO() {
        virtualHosts = new LinkedList<VirtualHostVO>();
    }

    public PaasFrontendVO(String id, String name, String apiUrl, List<VirtualHostVO> virtualHosts) {
        this.id = id;
        this.name = name;
        this.apiUrl = apiUrl;
        this.virtualHosts = virtualHosts;
    }

    public PaasFrontendVO(String name, String apiUrl, List<VirtualHostVO> virtualHosts) {
        this.id = null;
        this.name = name;
        this.apiUrl = apiUrl;
        this.virtualHosts = virtualHosts;
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

    public List<VirtualHostVO> getVirtualHosts() {
        return virtualHosts;
    }

    public void setVirtualHosts(List<VirtualHostVO> virtualHosts) {
        this.virtualHosts = virtualHosts;
    }

    /**
     * Change a PaasFrontend Value Object into an Entity object
     * @return a PaasFrontend Entity
     */
    public PaasFrontend createBean() {
        PaasFrontend paasFrontend = new PaasFrontend();
        paasFrontend.setName(getName());
        paasFrontend.setApiUrl(getApiUrl());
        List<VirtualHost> virtualHostList = new LinkedList<VirtualHost>();
        for (VirtualHostVO tmp : virtualHosts) {
            VirtualHost virtualHost = tmp.createBean();
            virtualHost.setPaasFrontend(paasFrontend);
            virtualHostList.add(virtualHost);
        }
        paasFrontend.setVirtualHosts(virtualHostList);
        return paasFrontend;
    }

}
