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
