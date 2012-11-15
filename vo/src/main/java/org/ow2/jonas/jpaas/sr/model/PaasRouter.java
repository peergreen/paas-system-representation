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


import org.ow2.jonas.jpaas.sr.facade.vo.PaasRouterVO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;


/**
 * Define a PaasRouter
 * @author David Richard
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PaasRouter extends PaasResource implements java.io.Serializable {

    /**
     * PaasContainers of the PaasRouter.
     */
    @ManyToMany(mappedBy = "paasRouterList",  cascade = CascadeType.MERGE)
    private List<PaasContainer> paasContainerList;

    /**
     * PaasFrontend of the PaasRouter.
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    private PaasFrontend paasFrontend;


    public PaasFrontend getPaasFrontend() {
        return paasFrontend;
    }

    public void setPaasFrontend(PaasFrontend paasFrontend) {
        this.paasFrontend = paasFrontend;
    }

    public PaasRouterVO createVO() {
        return new PaasRouterVO(getId(), getName(), getState(), new HashMap<String,String>(getCapabilities()),
                isMultitenant(), isReusable(), new LinkedList<Integer>(getUsedPorts()));
    }

    public void mergePaasRouterVO(PaasRouterVO paasRouterVO) {
        setName(paasRouterVO.getName());
        setState(paasRouterVO.getState());
        setCapabilities(paasRouterVO.getCapabilities());
        setMultitenant(paasRouterVO.isMultitenant());
        setReusable(paasRouterVO.isReusable());
        setUsedPorts(paasRouterVO.getUsedPorts());
    }

    public List<PaasContainer> getPaasContainerList() {
        return paasContainerList;
    }

    public void setPaasContainerList(List<PaasContainer> paasContainerList) {
        this.paasContainerList = paasContainerList;
    }

}
