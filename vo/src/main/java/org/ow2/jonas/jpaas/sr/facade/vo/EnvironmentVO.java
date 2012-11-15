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
