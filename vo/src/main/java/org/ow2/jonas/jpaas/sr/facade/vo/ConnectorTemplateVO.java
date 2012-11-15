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
