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

import org.ow2.jonas.jpaas.sr.model.Application;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Define an Application Value Object
 * @author David Richard
 */
public class ApplicationVO implements java.io.Serializable {

    /**
     * Id of the application.
     */
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
    private List<String> requirements;

    /**
     * Capabilities of the application.
     */
    private Map<String,String> capabilities;

    /**
     * ApplicationVersion of the Application.
     */
    private List<ApplicationVersionVO> applicationVersionList;


    public ApplicationVO(String id, String name, String description, List<String> requirements,
            Map<String,String> capabilities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.requirements = requirements;
        this.capabilities = capabilities;
        this.applicationVersionList = new LinkedList<ApplicationVersionVO>();
    }

    public ApplicationVO(String name, String description, List<String> requirements,
            Map<String,String> capabilities) {
        this.id = null;
        this.name = name;
        this.description = description;
        this.requirements = requirements;
        this.capabilities = capabilities;
        this.applicationVersionList = new LinkedList<ApplicationVersionVO>();
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Application[id=").append(getId()).append(", name=").append(getName())
                .append(", description=").append(getDescription()).append(", requirements=").append(getRequirements().toString())
                .append(", capabilities=").append(getCapabilities().toString()).append("]");
        return sb.toString();
    }

    /**
     * Change an Application Value Object into an Entity object
     * @return an Application Entity
     */
    public Application createBean() {
        Application application = new Application();
        application.setId(id);
        application.setName(name);
        application.setDescription(description);
        application.setRequirements(requirements);
        application.setCapabilities(capabilities);
        return application;
    }

    public List<ApplicationVersionVO> getApplicationVersionList() {
        return applicationVersionList;
    }

    public void setApplicationVersionList(List<ApplicationVersionVO> applicationVersionList) {
        this.applicationVersionList = applicationVersionList;
    }
}
