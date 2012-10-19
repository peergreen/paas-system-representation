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


import org.ow2.jonas.jpaas.sr.model.RouterNodeTemplate;

import java.util.List;
import java.util.Map;

/**
 * Define a RouterNodeTemplate Value Object
 * @author David Richard
 */
public class RouterNodeTemplateVO extends NodeTemplateVO implements java.io.Serializable {

    public RouterNodeTemplateVO(String id, String templateId, String name, String configurationName,
            List<String> requirements, Map<String,String> slaEnforcement, int minSize, int maxSize, int currentSize) {
        super(id, templateId, name, configurationName, requirements, slaEnforcement, minSize, maxSize, currentSize);
    }

    public RouterNodeTemplateVO(String templateId, String name, String configurationName, List<String> requirements,
            Map<String,String> slaEnforcement, int minSize, int maxSize, int currentSize) {
        super(templateId, name, configurationName, requirements, slaEnforcement, minSize, maxSize, currentSize);
    }

    /**
     * Change a RouterNodeTemplate Value Object into an Entity object
     * @return a RouterNodeTemplate Entity
     */
    public RouterNodeTemplate createBean() {
        RouterNodeTemplate routerNodeTemplate = new RouterNodeTemplate();
        routerNodeTemplate.setId(getId());
        routerNodeTemplate.setTemplateId(getTemplateId());
        routerNodeTemplate.setName(getName());
        routerNodeTemplate.setConfigurationName(getConfigurationName());
        routerNodeTemplate.setRequirements(getRequirements());
        routerNodeTemplate.setSlaEnforcement(getSlaEnforcement());
        routerNodeTemplate.setMinSize(getMinSize());
        routerNodeTemplate.setMaxSize(getMaxSize());
        routerNodeTemplate.setCurrentSize(getCurrentSize());
        return routerNodeTemplate;
    }

}
