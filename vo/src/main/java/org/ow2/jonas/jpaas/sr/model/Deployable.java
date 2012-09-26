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

import org.ow2.jonas.jpaas.sr.facade.vo.DeployableVO;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;

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
    @ElementCollection
    private List<String> requirements;

    /**
     * SlaEnforcement of the deployable.
     */
    @ElementCollection
    private Map<String,String> slaEnforcement;

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

    /**
     * NodeTemplate of the deployable.
     */
    @ManyToOne
    private NodeTemplate nodeTemplate;


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

    public NodeTemplate getNodeTemplate() {
        return nodeTemplate;
    }

    public void setNodeTemplate(NodeTemplate nodeTemplate) {
        this.nodeTemplate = nodeTemplate;
    }

    public DeployableVO createDeployableVO() {
        DeployableVO deployableVO = new DeployableVO(id, name, url, isUploaded, requirements, slaEnforcement);
        deployableVO.setInstanceId(applicationVersion.getId());
        deployableVO.setVersionId(applicationVersion.getId());
          return deployableVO;
      }

      public void mergeDeployableVO(DeployableVO deployableVO) {
          this.url = deployableVO.getUrl();
          this.name = deployableVO.getName();
          this.isUploaded = deployableVO.isUploaded();
          this.requirements = deployableVO.getRequirements();
          this.slaEnforcement = deployableVO.getSlaEnforcement();
      }
}