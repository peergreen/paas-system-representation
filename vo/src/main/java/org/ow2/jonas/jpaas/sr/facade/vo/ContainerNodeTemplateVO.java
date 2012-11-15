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


import org.ow2.jonas.jpaas.sr.model.ContainerNodeTemplate;

import java.util.List;
import java.util.Map;

/**
 * Define a ContainerNodeTemplate Value Object
 * @author David Richard
 */
public class ContainerNodeTemplateVO extends NodeTemplateVO implements java.io.Serializable {

    public ContainerNodeTemplateVO(String id, String templateId, String name, String configurationName,
            List<String> requirements, Map<String,String> slaEnforcement, int minSize, int maxSize, int currentSize) {
        super(id, templateId, name, configurationName, requirements, slaEnforcement, minSize, maxSize, currentSize);
    }

    public ContainerNodeTemplateVO(String templateId, String name, String configurationName, List<String> requirements,
            Map<String,String> slaEnforcement, int minSize, int maxSize, int currentSize) {
        super(templateId, name, configurationName, requirements, slaEnforcement, minSize, maxSize, currentSize);
    }

    /**
     * Change a ContainerNodeTemplate Value Object into an Entity object
     * @return a ContainerNodeTemplate Entity
     */
    public ContainerNodeTemplate createBean() {
        ContainerNodeTemplate containerNodeTemplate = new ContainerNodeTemplate();
        containerNodeTemplate.setId(getId());
        containerNodeTemplate.setTemplateId(getTemplateId());
        containerNodeTemplate.setName(getName());
        containerNodeTemplate.setConfigurationName(getConfigurationName());
        containerNodeTemplate.setRequirements(getRequirements());
        containerNodeTemplate.setSlaEnforcement(getSlaEnforcement());
        containerNodeTemplate.setMinSize(getMinSize());
        containerNodeTemplate.setMaxSize(getMaxSize());
        containerNodeTemplate.setCurrentSize(getCurrentSize());
        return containerNodeTemplate;
    }

}
