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

import org.ow2.jonas.jpaas.sr.model.PaasRouter;

import java.util.List;
import java.util.Map;

/**
 * Define a PaasRouter Value Object
 * @author David Richard
 */
public class PaasRouterVO extends PaasResourceVO implements java.io.Serializable {

    public PaasRouterVO() {
        super();
    }

    public PaasRouterVO(String id, String name, String state, Map<String,String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts) {
        super(id, name, state, capabilities, multitenant, reusable, usedPorts);
    }

    public PaasRouterVO(String name, String state, Map<String,String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts) {
        super(name, state, capabilities, multitenant, reusable, usedPorts);
    }

    /**
     * Change a PaasRouter Value Object into an Entity object
     * @return a PaasRouter Entity
     */
    public PaasRouter createBean() {
        PaasRouter paasRouter = new PaasRouter();
        paasRouter.setName(getName());
        paasRouter.setState(getState());
        paasRouter.setCapabilities(getCapabilities());
        paasRouter.setMultitenant(isMultitenant());
        paasRouter.setReusable(isReusable());
        paasRouter.setUsedPorts(getUsedPorts());
        return paasRouter;
    }
}
