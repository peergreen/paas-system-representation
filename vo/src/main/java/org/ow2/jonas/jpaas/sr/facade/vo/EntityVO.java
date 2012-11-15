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

import org.ow2.jonas.jpaas.sr.model.Entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Define an Entity Value Object
 * @author David Richard
 */
public class EntityVO implements java.io.Serializable {
    /**
     * Id of the Entity.
     */
    private String id;

    /**
     * Name of the Entity.
     */
    private String name;

    /**
     * State of the Entity.
     */
    private String state;

    /**
     * Capabilities of the Entity.
     */
    private Map<String,String> capabilities;

    /**
     * True if the Entity is multitenant.
     */
    private boolean isMultitenant;

    /**
     * True if the Entity is reusable.
     */
    private boolean isReusable;

    /**
     * List of ports used by the Entity.
     */
    private List<Integer> usedPorts;

    public EntityVO() {
        capabilities = new HashMap<String,String>();
        usedPorts = new LinkedList<Integer>();
    }

    public EntityVO(String id, String name, String state, Map<String,String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.capabilities = capabilities;
        isMultitenant = multitenant;
        isReusable = reusable;
        this.usedPorts = usedPorts;
    }

    public EntityVO(String name, String state, Map<String,String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts) {
        this.id = null;
        this.name = name;
        this.state = state;
        this.capabilities = capabilities;
        isMultitenant = multitenant;
        isReusable = reusable;
        this.usedPorts = usedPorts;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<String,String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Map<String,String> capabilities) {
        this.capabilities = capabilities;
    }

    public boolean isMultitenant() {
        return isMultitenant;
    }

    public void setMultitenant(boolean multitenant) {
        isMultitenant = multitenant;
    }

    public boolean isReusable() {
        return isReusable;
    }

    public void setReusable(boolean reusable) {
        isReusable = reusable;
    }

    public List<Integer> getUsedPorts() {
        return usedPorts;
    }

    public void setUsedPorts(List<Integer> usedPorts) {
        this.usedPorts = usedPorts;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EntityVO[id=").append(getId())
                .append(", name=").append(getName())
                .append(", state=").append(getState())
                .append(", capabilities=").append(getCapabilities().toString())
                .append(", isMultitenant=").append(isMultitenant())
                .append(", isReusable=").append(isReusable())
                .append(", usedPorts=").append(getUsedPorts().toString())
                .append("]");
        return sb.toString();
    }

    /**
     * Change an Entity Value Object into an EntityBean object
     * @return an Entity EntityBean
     */
    public Entity createBean() {
        Entity entity = new Entity();
        entity.setName(name);
        entity.setState(state);
        entity.setCapabilities(capabilities);
        entity.setMultitenant(isMultitenant);
        entity.setUsedPorts(usedPorts);
        return entity;
    }
}
