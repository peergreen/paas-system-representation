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

import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVO;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Define an Application
 * @author David Richard
 */
@Entity
@Table
public class Application implements java.io.Serializable {

    /**
     * key of the application.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Id of the application.
     */
    @Column(unique=true)
    private String id;

    /**
     * Name of the application.
     */
    private String name;

    /**
     * Description of the application.
     */
    private String description;

    /**
     * Requirements of the application.
     */
    @ElementCollection
    private List<String> requirements;

    /**
     * Capabilities of the application.
     */
    @ElementCollection
    private Map<String,String> capabilities;

    /**
     * User of the application.
     */
    @ManyToOne
    private User user;

    /**
     * ApplicationVersion of the Application.
     */
    @OneToMany(mappedBy="application", orphanRemoval=true, cascade={CascadeType.ALL})
    private List<ApplicationVersion> applicationVersionList;

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

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public Map<String,String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Map<String,String> capabilities) {
        this.capabilities = capabilities;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Application[key=").append(getKey())
                .append(", id=").append(getId())
                .append(", name=").append(getName())
                .append(", description=").append(getDescription())
                .append(", requirements=").append(getRequirements().toString())
                .append(", capabilities=").append(getCapabilities().toString())
                .append("]");
        return sb.toString();
    }

    public ApplicationVO createApplicationVO() {
        ApplicationVO applicationVO = new ApplicationVO(id,name,description,new LinkedList<String>(requirements),
                new HashMap<String,String>(capabilities));
        if (applicationVersionList != null) {
            for (ApplicationVersion tmp : applicationVersionList) {
                applicationVO.getApplicationVersionList().add(tmp.createApplicationVersionVO());
            }
        }
        return applicationVO;
    }

    public void mergeApplicationVO(ApplicationVO applicationVO){
        this.name = applicationVO.getName();
        this.description = applicationVO.getDescription();
        this.capabilities = applicationVO.getCapabilities();
        this.requirements = applicationVO.getRequirements();
    }

    public List<ApplicationVersion> getApplicationVersionList() {
        return applicationVersionList;
    }

    public void setApplicationVersionList(List<ApplicationVersion> applicationVersionList) {
        this.applicationVersionList = applicationVersionList;
    }
}
