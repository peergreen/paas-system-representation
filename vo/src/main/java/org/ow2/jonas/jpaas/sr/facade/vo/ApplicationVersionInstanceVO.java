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

import org.ow2.jonas.jpaas.sr.facade.model.ApplicationVersionInstance;
import org.ow2.jonas.jpaas.sr.facade.model.Deployable;
import org.ow2.jonas.jpaas.sr.facade.model.PaasArtefact;

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
