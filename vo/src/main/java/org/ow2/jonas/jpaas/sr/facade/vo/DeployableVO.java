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

import org.ow2.jonas.jpaas.sr.model.Deployable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Define a Deployable Value Object
 * @author David Richard
 */
public class DeployableVO implements java.io.Serializable {

    /**
     * Id of the deployable.
     */
    private String id;

    /**
     * Name of the deployable.
     */
    private String name;

    /**
     * Url of the deployable.
     */
    private String url;

    /**
     * True if the deployable is uploaded
     */
    private boolean isUploaded;

    /**
     * Requirements of the deployable.
     */
    private List<String> requirements;

    /**
     * SlaEnforcement of the deployable.
     */
    private Map<String,String> slaEnforcement;

    /**
     * ID of the ApplicationVersion of the deployable.
     */
    private String versionId;

    /**
     * ID of the ApplicationVersionInstance of the deployable.
     */
    private String instanceId;


    public DeployableVO() {
        requirements = new LinkedList<String>();
        slaEnforcement = new HashMap<String, String>();
    }

    public DeployableVO(String id, String name, String url, boolean uploaded, List<String> requirements,
            Map<String,String> slaEnforcement) {
        this.id = id;
        this.name = name;
        this.url = url;
        isUploaded = uploaded;
        this.requirements = new LinkedList<String>(requirements);
        this.slaEnforcement = new HashMap<String, String>(slaEnforcement);
    }

    public DeployableVO(String name, String url, boolean uploaded, List<String> requirements, Map<String,String> slaEnforcement) {
        this.id = null;
        this.name = name;
        this.url = url;
        isUploaded = uploaded;
        this.requirements = new LinkedList<String>(requirements);
        this.slaEnforcement = new HashMap<String, String>(slaEnforcement);
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public Map<String,String> getSlaEnforcement() {
        return slaEnforcement;
    }

    public void setSlaEnforcement(Map<String,String> slaEnforcement) {
        this.slaEnforcement = slaEnforcement;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deployable[id=").append(getId())
                .append(", name=").append(getName())
                .append(", url=").append(getUrl())
                .append(", isUploaded=").append(isUploaded())
                .append(", requirements=").append(getRequirements().toString())
                .append(", slaEnforcement=").append(getSlaEnforcement().toString())
                .append("]");
        return sb.toString();
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * Change a Deployable Value Object into an EntityBean object
     * @return a Deployable EntityBean
     */
    public Deployable createBean() {
        Deployable deployable = new Deployable();
        deployable.setId(id);
        deployable.setName(name);
        deployable.setUrl(url);
        deployable.setUploaded(isUploaded);
        deployable.setRequirements(requirements);
        deployable.setSlaEnforcement(slaEnforcement);
        return deployable;
    }

}
