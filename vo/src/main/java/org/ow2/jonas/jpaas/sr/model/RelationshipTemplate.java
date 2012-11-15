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
        this.templateId = relationshipTemplateVO.getTemplateId();
    }
}
