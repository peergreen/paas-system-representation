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


import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVersionInstanceVO;
import org.ow2.jonas.jpaas.sr.facade.vo.DeployableVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasArtefactVO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

/**
 * Define an ApplicationVersionInstance
 * @author David Richard
 */
@Entity
@Table
public class ApplicationVersionInstance implements java.io.Serializable {

    /**
     * key of the ApplicationVersionInstance.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Id of the ApplicationVersionInstance.
     */
    private String id;

    /**
     * Name of the ApplicationVersionInstance.
     */
    private String name;

    /**
     * State of the ApplicationVersionInstance.
     */
    private String state;

    /**
     * applicationVersion of the ApplicationVersionInstance.
     */
    @ManyToOne(optional=false)
    private ApplicationVersion applicationVersion;

    /**
     * Environment of the ApplicationVersionInstance.
     */
    @ManyToOne
    private Environment environment;

    /**
     * Deployables of the ApplicationVersionInstance.
     */
    @OneToMany(mappedBy="applicationVersionInstance", cascade={CascadeType.ALL})
    private List<Deployable> deployableList;


    /**
     * PaasArtefact of the ApplicationVersionInstance.
     */
    @OneToMany(mappedBy="applicationVersionInstance", orphanRemoval=true, cascade={CascadeType.ALL})
    private List<PaasArtefact> paasArtefactList;

    /**
     * Users of the ApplicationVersionInstance.
     */
    @ManyToMany
    private List<User> userList;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Application[Id=").append(getId())
                .append(", name=").append(getName())
                .append(", state=").append(getState())
                .append("]");
        return sb.toString();
    }

    public ApplicationVersionInstanceVO createApplicationVersionInstanceVO() {
        ApplicationVersionInstanceVO applicationVersionInstanceVO = new ApplicationVersionInstanceVO(id, name, state);
        if (deployableList != null) {
            for (Deployable tmp : deployableList) {
                applicationVersionInstanceVO.getDeployableList().add(tmp.createDeployableVO());
            }
        }
        if (paasArtefactList != null) {
            for (PaasArtefact tmp : paasArtefactList) {
                applicationVersionInstanceVO.getPaasArtefactList().add(tmp.createPaasArtefactVO());
            }
        }
        applicationVersionInstanceVO.setVersionId(applicationVersion.getId());
        applicationVersionInstanceVO.setAppId(applicationVersion.getApplication().getId());
        return applicationVersionInstanceVO;
    }

    public void mergeApplicationVersionInstanceVO(ApplicationVersionInstanceVO applicationVersionInstanceVO){
        this.id = applicationVersionInstanceVO.getId();
        this.name = applicationVersionInstanceVO.getName();
        this.state = applicationVersionInstanceVO.getState();
        if (applicationVersionInstanceVO.getDeployableList() != null) {
            LinkedList<Deployable> originalDeployableList = new LinkedList<Deployable>(deployableList);
            this.deployableList.clear();
            for (DeployableVO tmp : applicationVersionInstanceVO.getDeployableList()) {
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
        if (applicationVersionInstanceVO.getPaasArtefactList() != null) {
            LinkedList<PaasArtefact> originalPaasArtefactList = new LinkedList<PaasArtefact>(paasArtefactList);
            this.paasArtefactList.clear();
            for (PaasArtefactVO tmp : applicationVersionInstanceVO.getPaasArtefactList()) {
                PaasArtefact paasArtefact = tmp.createBean();
                if (paasArtefact.getId() != null) {
                    for (PaasArtefact originalPaasArtefact : originalPaasArtefactList) {
                        if (originalPaasArtefact.getId().equals(paasArtefact.getId())) {
                            paasArtefact.setKey(originalPaasArtefact.getKey());
                            break;
                        }
                    }
                }
                paasArtefactList.add(paasArtefact);
            }
        }
    }

    public List<Deployable> getDeployableList() {
        return deployableList;
    }

    public void setDeployableList(List<Deployable> deployableList) {
        this.deployableList = deployableList;
    }

    public ApplicationVersion getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(ApplicationVersion applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public List<PaasArtefact> getPaasArtefactList() {
        return paasArtefactList;
    }

    public void setPaasArtefactList(List<PaasArtefact> paasArtefactList) {
        this.paasArtefactList = paasArtefactList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
