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

package org.ow2.jonas.jpaas.sr.facade.model;

import org.ow2.jonas.jpaas.sr.facade.vo.DeployableVO;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Define a Deployable
 * @author David Richard
 */
@Entity
@Table
public class Deployable implements java.io.Serializable {

    /**
     * key of the deployable.
     */
    @Id
    @GeneratedValue
    private long key;

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
    @ElementCollection
    private List<String> requirements;

    /**
     * SlaEnforcement of the deployable.
     */
    @ElementCollection
    private List<String> slaEnforcement;

    /**
     * ApplicationVersion of the deployable.
     */
    @ManyToOne(optional=false)
    private ApplicationVersion applicationVersion;

    /**
     * ApplicationVersionInstance of the deployable.
     */
    @ManyToOne(optional=true)
    private ApplicationVersionInstance applicationVersionInstance;

    /**
     * PaasArtefact of the deployable.
     */
    @ManyToOne(optional=true)
    private PaasArtefact paasArtefact;


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

    public ApplicationVersion getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(ApplicationVersion applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public PaasArtefact getPaasArtefact() {
        return paasArtefact;
    }

    public void setPaasArtefact(PaasArtefact paasArtefact) {
        this.paasArtefact = paasArtefact;
    }

    public ApplicationVersionInstance getApplicationVersionInstance() {
        return applicationVersionInstance;
    }

    public void setApplicationVersionInstance(ApplicationVersionInstance applicationVersionInstance) {
        this.applicationVersionInstance = applicationVersionInstance;
    }

    public DeployableVO createDeployableVO() {
        DeployableVO deployableVO = new DeployableVO(id, url, isUploaded, requirements, slaEnforcement);
        deployableVO.setInstanceId(applicationVersion.getId());
        deployableVO.setVersionId(applicationVersion.getId());
          return deployableVO;
      }

      public void mergeDeployableVO(DeployableVO deployableVO) {
          this.url = deployableVO.getUrl();
          this.isUploaded = deployableVO.isUploaded();
          this.requirements = deployableVO.getRequirements();
          this.slaEnforcement = deployableVO.getSlaEnforcement();
      }
}