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


import org.ow2.jonas.jpaas.sr.model.ConnectorTemplate;

/**
 * Define a ConnectorTemplate Value Object
 * @author David Richard
 */
public class ConnectorTemplateVO extends RelationshipTemplateVO implements java.io.Serializable {

    /**
     * Router Id of the Connector.
     */
    private String routerId;

    /**
     * Container Id of the Connector.
     */
    private String containerId;

    public ConnectorTemplateVO() {
        super();
    }

    public ConnectorTemplateVO(String id, String templateId, String name, String routerId, String containerId) {
        super(id, templateId, name);
        this.routerId = routerId;
        this.containerId = containerId;
    }

    public ConnectorTemplateVO(String templateId, String name, String routerId, String containerId) {
        super(templateId, name);
        this.routerId = routerId;
        this.containerId = containerId;
    }

    public String getRouterId() {
        return routerId;
    }

    public void setRouterId(String routerId) {
        this.routerId = routerId;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }


    /**
     * Change a ConnectorTemplate Value Object into an EntityBean object
     * @return a ConnectorTemplate EntityBean
     */
    public ConnectorTemplate createBean() {
        ConnectorTemplate connectorTemplate = new ConnectorTemplate();
        connectorTemplate.setTemplateId(getTemplateId());
        connectorTemplate.setName(getName());
        connectorTemplate.setContainerId(getContainerId());
        connectorTemplate.setRouterId(getRouterId());
        return connectorTemplate;
    }
}
