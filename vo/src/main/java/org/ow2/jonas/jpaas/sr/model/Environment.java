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


import org.ow2.jonas.jpaas.sr.facade.vo.EnvironmentVO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Define an Environment
 * @author David Richard
 */
@Entity
@Table
public class Environment implements java.io.Serializable {

    /**
     * key of the environment.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Id of the environment.
     */
    @Column(unique=true)
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
     * User of the environment.
     */
    @ManyToOne
    private User user;

    /**
     * TopologyTemplate of the environment.
     */
    @OneToOne(mappedBy="environment", orphanRemoval=true, cascade={CascadeType.ALL})
    private TopologyTemplate topologyTemplate;

    /**
     * ApplicationVersionInstance of the environment.
     */
    @OneToMany(mappedBy="environment")
    private List<ApplicationVersionInstance> applicationVersionInstanceList;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Environment[key=").append(getKey())
                .append(", id=").append(getId())
                .append(", name=").append(getName())
                .append(", description=").append(getDescription())
                .append(", state=").append(getState())
                .append("]");
        return sb.toString();
    }

    public EnvironmentVO createEnvironmentVO() {
        EnvironmentVO environmentVO = new EnvironmentVO(id,name,description,state);
        if (topologyTemplate != null) {
            environmentVO.setTopologyTemplate(topologyTemplate.createTopologyTemplateVO());
        }
        return environmentVO;
    }

    public void mergeEnvironmentVO(EnvironmentVO environmentVO){
        this.name = environmentVO.getName();
        this.description = environmentVO.getDescription();
        this.state = environmentVO.getState();
        if (topologyTemplate != null) {
            this.topologyTemplate.mergeTopologyTemplateVO(environmentVO.getTopologyTemplate());
        }

    }

    public TopologyTemplate getTopologyTemplate() {
        return topologyTemplate;
    }

    public void setTopologyTemplate(TopologyTemplate topologyTemplate) {
        this.topologyTemplate = topologyTemplate;
    }

    public List<ApplicationVersionInstance> getApplicationVersionInstanceList() {
        return applicationVersionInstanceList;
    }

    public void setApplicationVersionInstanceList(List<ApplicationVersionInstance> applicationVersionInstanceList) {
        this.applicationVersionInstanceList = applicationVersionInstanceList;
    }
}
