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


import org.ow2.jonas.jpaas.sr.facade.vo.ConnectorTemplateVO;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


/**
 * Define a ConnectorTemplate
 * @author David Richard
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class ConnectorTemplate extends RelationshipTemplate implements java.io.Serializable {

    /**
     * Router Id of the Connector.
     */
    private String routerId;

    /**
     * Container Id of the Connector.
     */
    private String containerId;

    public ConnectorTemplateVO createRelationshipTemplateVO() {
        return new ConnectorTemplateVO(getId(), getTemplateId(), getName(), routerId, containerId);
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
}
