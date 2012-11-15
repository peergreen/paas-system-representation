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

import org.ow2.jonas.jpaas.sr.facade.vo.VirtualHostVO;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Map;

/**
 * Define a VirtualHost
 * @author David Richard
 */
@javax.persistence.Entity
@Table
public class VirtualHost implements java.io.Serializable {

    /**
     * key of the VirtualHost.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Name of the VirtualHost.
     */
    private String name;

    /**
     * The VirtualHost Id.
     */
    private String vhostId;

    /**
     * PaasFrontend of the VirtualHost.
     */
    @ManyToOne(optional=false)
    private PaasFrontend paasFrontend;

    /**
     * ProxyPass directives of the VirtualHost.
     */
    @ElementCollection
    private Map<String, String> proxypassDirectives;


    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVhostId() {
        return vhostId;
    }

    public void setVhostId(String vhostId) {
        this.vhostId = vhostId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VirtualHost[key=").append(getKey())
                .append(", name=").append(getName())
                .append(", vhostId=").append(getVhostId())
                .append("]");
        return sb.toString();
    }


    public PaasFrontend getPaasFrontend() {
        return paasFrontend;
    }

    public void setPaasFrontend(PaasFrontend paasFrontend) {
        this.paasFrontend = paasFrontend;
    }

    public VirtualHostVO createVirtualHostVO() {
        return new VirtualHostVO(name, vhostId, proxypassDirectives);
    }

    public void mergeVirtualHostVO(VirtualHostVO virtualHostVO) {
        this.name = virtualHostVO.getName();
        this.vhostId = virtualHostVO.getVhostId();
        this.proxypassDirectives = virtualHostVO.getProxypassDirectives();
    }

    public Map<String, String> getProxypassDirectives() {
        return proxypassDirectives;
    }

    public void setProxypassDirectives(Map<String, String> proxypassDirectives) {
        this.proxypassDirectives = proxypassDirectives;
    }
}
