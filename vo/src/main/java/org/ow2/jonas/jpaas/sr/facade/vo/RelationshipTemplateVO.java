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

import org.ow2.jonas.jpaas.sr.model.RelationshipTemplate;

/**
 * Define a RelationshipTemplate Value Object
 * @author David Richard
 */
public class RelationshipTemplateVO implements java.io.Serializable {

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

    public RelationshipTemplateVO() {

    }

    public RelationshipTemplateVO(String id, String templateId, String name) {
        this.id = id;
        this.templateId = templateId;
        this.name = name;
    }

    public RelationshipTemplateVO(String templateId, String name) {
        this.id = null;
        this.templateId = templateId;
        this.name = name;
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


    /**
     * Change a TopologyTemplate Value Object into an EntityBean object
     * @return a TopologyTemplate EntityBean
     */
    public RelationshipTemplate createBean() {
        RelationshipTemplate relationshipTemplate = new RelationshipTemplate();
        relationshipTemplate.setTemplateId(templateId);
        relationshipTemplate.setName(name);
        return relationshipTemplate;
    }


}
