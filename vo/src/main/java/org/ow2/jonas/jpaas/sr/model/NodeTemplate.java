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

import org.ow2.jonas.jpaas.sr.facade.vo.NodeTemplateVO;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;

/**
 * Define a Node
 * @author David Richard
 */
@javax.persistence.Entity
@Table
public class NodeTemplate implements java.io.Serializable {

    /**
     * key of the Node.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Id of the Node.
     */
    private String id;

    /**
     * TemplateId of the Node : id given in the template descriptor file.
     */
    private String templateId;

    /**
     * Name of the Node.
     */
    private String name;

    /**
     * Requirements of the Node.
     */
    @ElementCollection
    private List<String> requirements;

    /**
     * SlaEnforcement of the Node.
     */
    @ElementCollection
    private Map<String,String> slaEnforcement;

    /**
     * Minimum size of the Node.
     */
    private int minSize;

    /**
     * Maximum size of the Node.
     */
    private int maxSize;

    /**
     * Maximum size of the Node.
     */
    private int currentSize;

    /**
     * TopologyTemplate of the Node.
     */
    @ManyToOne(optional=false)
    private TopologyTemplate topologyTemplate;

    /**
     * Entities of the Node.
     */
    @ManyToMany(mappedBy="nodeTemplateList",  cascade = CascadeType.MERGE)
    private List<Entity> entityList;

    /**
     * Deployable of the Node.
     */
    @OneToMany(mappedBy="nodeTemplate", cascade = CascadeType.ALL)
    private List<Deployable> deployableList;

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

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NodeTemplate[key=").append(getKey())
                .append(", id=").append(getId())
                .append(", templateId=").append(getTemplateId())
                .append(", name=").append(getName())
                .append(", requirements=").append(getRequirements().toString())
                .append(", slaEnforcement=").append(getSlaEnforcement().toString())
                .append(", minSize=").append(getMinSize())
                .append(", maxSize=").append(getMaxSize())
                .append(", currentSize=").append(getCurrentSize())
                .append("]");
        return sb.toString();
    }

    public NodeTemplateVO createNodeTemplateVO() {
        return new NodeTemplateVO(id,templateId,name,requirements,slaEnforcement, minSize, maxSize, currentSize);
    }

    public void mergeNodeTemplateVO(NodeTemplateVO nodeTemplateVO){
        this.templateId = nodeTemplateVO.getTemplateId();
        this.name = nodeTemplateVO.getName();
        this.requirements = nodeTemplateVO.getRequirements();
        this.slaEnforcement = nodeTemplateVO.getSlaEnforcement();
        this.minSize = nodeTemplateVO.getMinSize();
        this.maxSize = nodeTemplateVO.getMaxSize();
        this.currentSize = nodeTemplateVO.getCurrentSize();
    }

    public TopologyTemplate getTopologyTemplate() {
        return topologyTemplate;
    }

    public void setTopologyTemplate(TopologyTemplate topologyTemplate) {
        this.topologyTemplate = topologyTemplate;
    }

    public List<Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<Entity> entityList) {
        this.entityList = entityList;
    }

    public List<Deployable> getDeployableList() {
        return deployableList;
    }

    public void setDeployableList(List<Deployable> deployableList) {
        this.deployableList = deployableList;
    }
}
