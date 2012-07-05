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


import org.ow2.jonas.jpaas.sr.model.Environment;

/**
 * Define an Environment Value Object
 * @author David Richard
 */
public class EnvironmentVO implements java.io.Serializable {

    /**
     * Id of the environment.
     */
    private String id;

    /**
     * Name of the environment.
     */
    private String name;

    /**
     * Description of the environment.
     */
    private String description;

    /**
     * State of the environment.
     */
    private String state;

    /**
     * TopologyTemplate of the environment.
     */
    private TopologyTemplateVO topologyTemplate;

    public EnvironmentVO(String id, String name, String description, String state) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.topologyTemplate = null;
    }

    public EnvironmentVO(String name, String description, String state) {
        this.id = null;
        this.name = name;
        this.description = description;
        this.state = state;
        this.topologyTemplate = null;
    }

    public EnvironmentVO(String id, String name, String description, String state,
            TopologyTemplateVO topologyTemplateVO) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.topologyTemplate = topologyTemplateVO;
    }

    public EnvironmentVO(String name, String description, String state, TopologyTemplateVO topologyTemplateVO) {
        this.id = null;
        this.name = name;
        this.description = description;
        this.state = state;
        this.topologyTemplate = topologyTemplateVO;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Environment[id=").append(getId())
                .append(", name=").append(getName())
                .append(", description=").append(getDescription())
                .append(", state=").append(getState())
                .append("]");
        return sb.toString();
    }

    /**
     * Change an Environment Value Object into an Entity object
     * @return an Environment Entity
     */
    public Environment createBean() {
        Environment environment = new Environment();
        environment.setId(id);
        environment.setName(name);
        environment.setDescription(description);
        environment.setState(state);
        if (topologyTemplate != null) {
            environment.setTopologyTemplate(topologyTemplate.createBean());
        }
        return environment;
    }

    public TopologyTemplateVO getTopologyTemplate() {
        return topologyTemplate;
    }

    public void setTopologyTemplate(TopologyTemplateVO topologyTemplate) {
        this.topologyTemplate = topologyTemplate;
    }
}
