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


import org.ow2.jonas.jpaas.sr.facade.vo.RelationshipTemplateVO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Define a RelationshipTemplate
 * @author David Richard
 */
@Entity
@Table
public class RelationshipTemplate implements java.io.Serializable  {

    /**
     * key of the RelationshipTemplate.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Id of the RelationshipTemplate.
     */
    private String id;

    /**
     * TemplateId of the RelationshipTemplate : id given in the template descriptor file.
     */
    private String templateId;

    /**
     * Name of the RelationshipTemplate.
     */
    private String name;

    /**
     * TopologyTemplate of the Node.
     */
    @ManyToOne(optional=false)
    private TopologyTemplate topologyTemplate;

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RelationshipTemplate[key=").append(getKey())
                .append(", id=").append(getId())
                .append(", templateId=").append(getTemplateId())
                .append(", name=").append(getName())
                .append("]");
        return sb.toString();
    }

    public TopologyTemplate getTopologyTemplate() {
        return topologyTemplate;
    }

    public void setTopologyTemplate(TopologyTemplate topologyTemplate) {
        this.topologyTemplate = topologyTemplate;
    }

    public RelationshipTemplateVO createRelationshipTemplateVO() {
        return new RelationshipTemplateVO(id, templateId, name);
    }

    public void mergeRelationshipTemplateVO(RelationshipTemplateVO relationshipTemplateVO){
        this.name = relationshipTemplateVO.getName();
    }
}
