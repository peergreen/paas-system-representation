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
