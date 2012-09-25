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


import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVersionVO;
import org.ow2.jonas.jpaas.sr.facade.vo.DeployableVO;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Define an ApplicationVersion
 * @author David Richard
 */
@Entity
@Table
public class ApplicationVersion implements java.io.Serializable {

    /**
     * key of the ApplicationVersion.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Id of the ApplicationVersion.
     */
    private String id;

    /**
     * label of the ApplicationVersion.
     */
    private String label;

    /**
     * Requirements of the ApplicationVersion.
     */
    @ElementCollection
    private List<String> requirements;

    /**
     * Application of the ApplicationVersion.
     */
    @ManyToOne(optional=false)
    private Application application;

    /**
     * Deployables of the ApplicationVersion.
     */
    @OneToMany(mappedBy="applicationVersion", orphanRemoval=true, cascade={CascadeType.ALL})
    private List<Deployable> deployableList;

    /**
     * ApplicationVersionInstance of the ApplicationVersion.
     */
    @OneToMany(mappedBy="applicationVersion", orphanRemoval=true, cascade={CascadeType.ALL})
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String name) {
        this.label = name;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Application[versionId=").append(getId())
                .append(", label=").append(getLabel())
                .append(", requirements=").append(getRequirements().toString())
                .append("]");
        return sb.toString();
    }

    public ApplicationVersionVO createApplicationVersionVO() {
        ApplicationVersionVO applicationVersionVO =
                new ApplicationVersionVO(id, label,new LinkedList<String>(requirements));
        if (deployableList != null) {
            for (Deployable tmp : deployableList) {
                applicationVersionVO.getDeployableList().add(tmp.createDeployableVO());
            }
        }
        if (applicationVersionInstanceList != null) {
            for (ApplicationVersionInstance tmp : applicationVersionInstanceList) {
                applicationVersionVO.getApplicationVersionInstanceList().add(tmp.createApplicationVersionInstanceVO());
            }
        }
        applicationVersionVO.setAppId(application.getId());
        return applicationVersionVO;
    }

    public void mergeApplicationVersionVO(ApplicationVersionVO applicationVersionVO){
        this.id = applicationVersionVO.getVersionId();
        this.label = applicationVersionVO.getLabel();
        this.requirements = applicationVersionVO.getRequirements();
        if (applicationVersionVO.getDeployableList() != null) {
            LinkedList<Deployable> originalDeployableList = new LinkedList<Deployable>(deployableList);
            this.deployableList.clear();
            for (DeployableVO tmp : applicationVersionVO.getDeployableList()) {
                Deployable deployable = tmp.createBean();
                if (deployable.getId() != null) {
                    for (Deployable originalDeployable : originalDeployableList) {
                        if (originalDeployable.getId().equals(deployable.getId())) {
                            deployable.setKey(originalDeployable.getKey());
                            break;
                        }
                    }
                }
                deployableList.add(deployable);
            }
        }
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<Deployable> getDeployableList() {
        return deployableList;
    }

    public void setDeployableList(List<Deployable> deployableList) {
        this.deployableList = deployableList;
    }

    public List<ApplicationVersionInstance> getApplicationVersionInstanceList() {
        return applicationVersionInstanceList;
    }

    public void setApplicationVersionInstanceList(List<ApplicationVersionInstance> applicationVersionInstanceList) {
        this.applicationVersionInstanceList = applicationVersionInstanceList;
    }
}
