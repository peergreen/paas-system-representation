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
