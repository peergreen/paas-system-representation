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

import org.ow2.jonas.jpaas.sr.model.ApplicationVersionInstance;
import org.ow2.jonas.jpaas.sr.model.Deployable;
import org.ow2.jonas.jpaas.sr.model.PaasArtefact;

import java.util.LinkedList;
import java.util.List;

/**
 * Define an ApplicationVersionInstance Value Object
 * @author David Richard
 */
public class ApplicationVersionInstanceVO implements java.io.Serializable {

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
     * Deployables of the ApplicationVersionInstance.
     */
    private List<DeployableVO> deployableList;

    /**
     * PaasArtefact of the ApplicationVersionInstance.
     */
    private List<PaasArtefactVO> paasArtefactList;

    /**
     * Id of the Application.
     */
    private String appId;

    /**
     * Id of the ApplicationVersion.
     */
    private String versionId;

    public ApplicationVersionInstanceVO() {
        this.deployableList = new LinkedList<DeployableVO>();
        this.paasArtefactList = new LinkedList<PaasArtefactVO>();
    }

    public ApplicationVersionInstanceVO(String id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.deployableList = new LinkedList<DeployableVO>();
        this.paasArtefactList = new LinkedList<PaasArtefactVO>();
    }

    public ApplicationVersionInstanceVO(String name, String state) {
        this.id = null;
        this.name = name;
        this.state = state;
        this.deployableList = new LinkedList<DeployableVO>();
        this.paasArtefactList = new LinkedList<PaasArtefactVO>();
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

    /**
     * Change an ApplicationVersionInstance Value Object into an Entity object
     * @return an ApplicationVersionInstance Entity
     */
    public ApplicationVersionInstance createBean() {
        ApplicationVersionInstance applicationVersionInstance = new ApplicationVersionInstance();
        applicationVersionInstance.setId(id);
        applicationVersionInstance.setName(name);
        applicationVersionInstance.setState(state);
        if (deployableList != null) {
            List<Deployable> list = new LinkedList<Deployable>();
            for (DeployableVO tmp : deployableList ) {
                Deployable deployable = tmp.createBean();
                deployable.setApplicationVersionInstance(applicationVersionInstance);
                list.add(deployable);
            }
            applicationVersionInstance.setDeployableList(list);
        }
        if (paasArtefactList != null) {
            List<PaasArtefact> list = new LinkedList<PaasArtefact>();
            for (PaasArtefactVO tmp : paasArtefactList ) {
                PaasArtefact paasArtefact = tmp.createBean();
                paasArtefact.setApplicationVersionInstance(applicationVersionInstance);
                list.add(paasArtefact);
            }
            applicationVersionInstance.setPaasArtefactList(list);
        }
        return applicationVersionInstance;
    }

    public List<DeployableVO> getDeployableList() {
        return deployableList;
    }

    public void setDeployableList(List<DeployableVO> deployableList) {
        this.deployableList = deployableList;
    }

    public List<PaasArtefactVO> getPaasArtefactList() {
        return paasArtefactList;
    }

    public void setPaasArtefactList(List<PaasArtefactVO> paasArtefactList) {
        this.paasArtefactList = paasArtefactList;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
}
