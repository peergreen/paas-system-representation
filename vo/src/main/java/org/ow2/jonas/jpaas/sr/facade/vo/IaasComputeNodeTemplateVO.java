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
