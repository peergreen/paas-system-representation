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

import org.ow2.jonas.jpaas.sr.model.ApplicationVersion;
import org.ow2.jonas.jpaas.sr.model.Deployable;

import java.util.LinkedList;
import java.util.List;

/**
 * Define an ApplicationVersion Value Object
 * @author David Richard
 */
public class ApplicationVersionVO implements java.io.Serializable {

    /**
     * Id of the ApplicationVersion.
     */
    private String versionId;

    /**
     * label of the ApplicationVersion.
     */
    private String label;

    /**
     * Requirements of the ApplicationVersion.
     */
    private List<String> requirements;

    /**
     * Deployables of the ApplicationVersion.
     */
    private List<DeployableVO> deployableList;

    /**
     * ApplicationVersionInstance of the ApplicationVersion.
     */
    private List<ApplicationVersionInstanceVO> applicationVersionInstanceList;

    /**
     * Id of the Application of the ApplicationVersion.
     */
    private String appId;

    public ApplicationVersionVO() {
        this.requirements = new LinkedList<String>();
        this.deployableList = new LinkedList<DeployableVO>();
        this.applicationVersionInstanceList = new LinkedList<ApplicationVersionInstanceVO>();
    }

    public ApplicationVersionVO(String versionId, String label, List<String> requirements) {
        this.versionId = versionId;
        this.label = label;
        this.requirements = requirements;
        this.deployableList = new LinkedList<DeployableVO>();
        this.applicationVersionInstanceList = new LinkedList<ApplicationVersionInstanceVO>();
    }

    public ApplicationVersionVO(String label, List<String> requirements) {
        this.versionId = null;
        this.label = label;
        this.requirements = requirements;
        this.deployableList = new LinkedList<DeployableVO>();
        this.applicationVersionInstanceList = new LinkedList<ApplicationVersionInstanceVO>();
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String id) {
        this.versionId = id;
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
        sb.append("ApplicationVersion[versionId=").append(getVersionId())
                .append(", label=").append(getLabel())
                .append(", requirements=").append(getRequirements().toString())
                .append("]");
        return sb.toString();
    }

    /**
     * Change an ApplicationVersion Value Object into an Entity object
     * @return an ApplicationVersion Entity
     */
    public ApplicationVersion createBean() {
        ApplicationVersion applicationVersion = new ApplicationVersion();
        applicationVersion.setId(versionId);
        applicationVersion.setLabel(label);
        applicationVersion.setRequirements(requirements);
        if (deployableList != null) {
            List<Deployable> list = new LinkedList<Deployable>();
            for (DeployableVO tmp : deployableList ) {
                Deployable deployable = tmp.createBean();
                deployable.setApplicationVersion(applicationVersion);
                list.add(deployable);
            }
            applicationVersion.setDeployableList(list);
        }
        return applicationVersion;
    }

    public List<DeployableVO> getDeployableList() {
        return deployableList;
    }

    public void setDeployableList(List<DeployableVO> deployableList) {
        this.deployableList = deployableList;
    }

    public List<ApplicationVersionInstanceVO> getApplicationVersionInstanceList() {
        return applicationVersionInstanceList;
    }

    public void setApplicationVersionInstanceList(List<ApplicationVersionInstanceVO> applicationVersionInstanceList) {
        this.applicationVersionInstanceList = applicationVersionInstanceList;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
