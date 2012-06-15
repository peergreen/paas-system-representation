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

import org.ow2.jonas.jpaas.sr.model.Entity;

import java.util.LinkedList;
import java.util.List;

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
    private List<String> capabilities;

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
        capabilities = new LinkedList<String>();
        usedPorts = new LinkedList<Integer>();
    }

    public EntityVO(String id, String name, String state, List<String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.capabilities = capabilities;
        isMultitenant = multitenant;
        isReusable = reusable;
        this.usedPorts = usedPorts;
    }

    public EntityVO(String name, String state, List<String> capabilities, boolean multitenant,
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

    public List<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<String> capabilities) {
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
