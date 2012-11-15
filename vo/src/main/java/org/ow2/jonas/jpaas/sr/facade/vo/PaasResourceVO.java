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

import org.ow2.jonas.jpaas.sr.model.PaasResource;

import java.util.List;
import java.util.Map;

/**
 * Define a PaasResource Value Object
 * @author David Richard
 */
public class PaasResourceVO extends PaasEntityVO implements java.io.Serializable {

    public PaasResourceVO() {
        super();
    }

    public PaasResourceVO(String id, String name, String state, Map<String,String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts) {
        super(id, name, state, capabilities, multitenant, reusable, usedPorts);
    }

    public PaasResourceVO(String name, String state, Map<String,String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts) {
        super(name, state, capabilities, multitenant, reusable, usedPorts);
    }

    /**
     * Change a PaasResource Value Object into an Entity object
     * @return a PaasResource Entity
     */
    public PaasResource createBean() {
        PaasResource paasResource = new PaasResource();
        paasResource.setName(getName());
        paasResource.setState(getState());
        paasResource.setCapabilities(getCapabilities());
        paasResource.setMultitenant(isMultitenant());
        paasResource.setReusable(isReusable());
        paasResource.setUsedPorts(getUsedPorts());
        return paasResource;
    }
}
