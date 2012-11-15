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


import org.ow2.jonas.jpaas.sr.facade.vo.IaasComputeNodeTemplateVO;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


/**
 * Define an IaasComputeNodeTemplate
 * @author David Richard
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class IaasComputeNodeTemplate extends NodeTemplate implements java.io.Serializable {

    public IaasComputeNodeTemplateVO createNodeTemplateVO() {
        return new IaasComputeNodeTemplateVO(getId(), getTemplateId(), getName(), getConfigurationName(),
                getRequirements(), getSlaEnforcement(), getMinSize(), getMaxSize(), getCurrentSize());
    }

}
