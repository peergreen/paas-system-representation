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

import org.ow2.jonas.jpaas.sr.facade.model.Deployable;

import java.util.LinkedList;
import java.util.List;

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
    private List<String> slaEnforcement;

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
        slaEnforcement = new LinkedList<String>();
    }

    public DeployableVO(String id, String url, boolean uploaded, List<String> requirements,
            List<String> slaEnforcement) {
        this.id = id;
        this.url = url;
        isUploaded = uploaded;
        this.requirements = requirements;
        this.slaEnforcement = slaEnforcement;
    }

    public DeployableVO(String url, boolean uploaded, List<String> requirements, List<String> slaEnforcement) {
        this.id = null;
        this.url = url;
        isUploaded = uploaded;
        this.requirements = requirements;
        this.slaEnforcement = slaEnforcement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getSlaEnforcement() {
        return slaEnforcement;
    }

    public void setSlaEnforcement(List<String> slaEnforcement) {
        this.slaEnforcement = slaEnforcement;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deployable[id=").append(getId())
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
        deployable.setUrl(url);
        deployable.setUploaded(isUploaded);
        deployable.setRequirements(requirements);
        deployable.setSlaEnforcement(slaEnforcement);
        return deployable;
    }

}
