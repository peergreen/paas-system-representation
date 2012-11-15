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


import org.ow2.jonas.jpaas.sr.model.DatasourceTemplate;

/**
 * Define a DatasourceTemplate Value Object
 * @author David Richard
 */
public class DatasourceTemplateVO extends RelationshipTemplateVO implements java.io.Serializable {

    /**
     * Database Id of the Connector.
     */
    private String databaseId;

    /**
     * Container Id of the Connector.
     */
    private String containerId;

    public DatasourceTemplateVO() {
        super();
    }

    public DatasourceTemplateVO(String id, String templateId, String name, String databaseId, String containerId) {
        super(id, templateId, name);
        this.databaseId = databaseId;
        this.containerId = containerId;
    }

    public DatasourceTemplateVO(String templateId, String name, String databaseId, String containerId) {
        super(templateId, name);
        this.databaseId = databaseId;
        this.containerId = containerId;
    }


    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    /**
     * Change a DatasourceTemplate Value Object into an EntityBean object
     * @return a DatasourceTemplate EntityBean
     */
    public DatasourceTemplate createBean() {
        DatasourceTemplate datasourceTemplate = new DatasourceTemplate();
        datasourceTemplate.setTemplateId(getTemplateId());
        datasourceTemplate.setName(getName());
        datasourceTemplate.setContainerId(getContainerId());
        datasourceTemplate.setDatabaseId(getDatabaseId());
        return datasourceTemplate;
    }

}
