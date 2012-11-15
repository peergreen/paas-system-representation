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

import org.ow2.jonas.jpaas.sr.model.VirtualHost;

import java.util.HashMap;
import java.util.Map;


/**
 * Define a VirtualHost Value Object
 * @author David Richard
 */
public class VirtualHostVO implements java.io.Serializable {

    /**
     * Name of the VirtualHost.
     */
    private String name;

    /**
     * The VirtualHost Id.
     */
    private String vhostId;

    /**
     * ProxyPass directives of the VirtualHost.
     */
    private Map<String, String> proxypassDirectives;


    public VirtualHostVO() {
       proxypassDirectives = new HashMap<String, String>();
    }

    public VirtualHostVO(String name, String vhostId) {
        this.name = name;
        this.vhostId = vhostId;
        proxypassDirectives = new HashMap<String, String>();
    }

    public VirtualHostVO(String name, String vhostId, Map<String,String> proxypassDirectives) {
        this.name = name;
        this.vhostId = vhostId;
        this.proxypassDirectives = new HashMap<String, String>(proxypassDirectives);
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

    /**
     * Change a VirtualHost Value Object into an Entity object
     * @return a VirtualHost Entity
     */
    public VirtualHost createBean() {
        VirtualHost virtualHost = new VirtualHost();
        virtualHost.setVhostId(getVhostId());
        virtualHost.setName(getName());
        virtualHost.setProxypassDirectives(getProxypassDirectives());
        return virtualHost;
    }

    public Map<String, String> getProxypassDirectives() {
        return proxypassDirectives;
    }

    public void setProxypassDirectives(Map<String, String> proxypassDirectives) {
        this.proxypassDirectives = proxypassDirectives;
    }
}
