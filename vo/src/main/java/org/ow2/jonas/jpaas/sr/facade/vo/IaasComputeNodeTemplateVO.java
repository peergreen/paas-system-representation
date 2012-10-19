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


import org.ow2.jonas.jpaas.sr.model.IaasComputeNodeTemplate;

import java.util.List;
import java.util.Map;

/**
 * Define a DatabaseNodeTemplate Value Object
 * @author David Richard
 */
public class IaasComputeNodeTemplateVO extends NodeTemplateVO implements java.io.Serializable {

    public IaasComputeNodeTemplateVO(String id, String templateId, String name, String configurationName,
            List<String> requirements, Map<String,String> slaEnforcement, int minSize, int maxSize, int currentSize) {
        super(id, templateId, name, configurationName, requirements, slaEnforcement, minSize, maxSize, currentSize);
    }

    public IaasComputeNodeTemplateVO(String templateId, String name, String configurationName,
            List<String> requirements, Map<String,String> slaEnforcement, int minSize, int maxSize, int currentSize) {
        super(templateId, name, configurationName, requirements, slaEnforcement, minSize, maxSize, currentSize);
    }

    /**
     * Change a IaasComputeNodeTemplate Value Object into an Entity object
     * @return a IaasComputeNodeTemplate Entity
     */
    public IaasComputeNodeTemplate createBean() {
        IaasComputeNodeTemplate iaasComputeNodeTemplate = new IaasComputeNodeTemplate();
        iaasComputeNodeTemplate.setId(getId());
        iaasComputeNodeTemplate.setTemplateId(getTemplateId());
        iaasComputeNodeTemplate.setName(getName());
        iaasComputeNodeTemplate.setConfigurationName(getConfigurationName());
        iaasComputeNodeTemplate.setRequirements(getRequirements());
        iaasComputeNodeTemplate.setSlaEnforcement(getSlaEnforcement());
        iaasComputeNodeTemplate.setMinSize(getMinSize());
        iaasComputeNodeTemplate.setMaxSize(getMaxSize());
        iaasComputeNodeTemplate.setCurrentSize(getCurrentSize());
        return iaasComputeNodeTemplate;
    }

}
