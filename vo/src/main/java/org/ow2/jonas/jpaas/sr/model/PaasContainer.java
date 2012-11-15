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


import org.ow2.jonas.jpaas.sr.facade.vo.PaasContainerVO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Define a PaasContainer
 * @author David Richard
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PaasContainer extends PaasResource implements java.io.Serializable {

    /**
     * PaasDatabases of the PaasContainer.
     */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "PaasContainerPaasDatabaseLink",
            joinColumns = { @JoinColumn(name = "PaasContainer_key")},
            inverseJoinColumns={@JoinColumn(name="PaasDatabase_key")})
    private List<PaasDatabase> paasDatabaseList;

    /**
     * PaasRouters of the PaasContainer.
     */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "PaasRouterPaasContainerLink",
            joinColumns = { @JoinColumn(name = "PaasContainer_key")},
            inverseJoinColumns={@JoinColumn(name="PaasRouter_key")})
    private List<PaasRouter> paasRouterList;


    public List<PaasDatabase> getPaasDatabaseList() {
        return paasDatabaseList;
    }

    public void setPaasDatabaseList(List<PaasDatabase> paasDatabaseList) {
        this.paasDatabaseList = paasDatabaseList;
    }

    public List<PaasRouter> getPaasRouterList() {
        return paasRouterList;
    }

    public void setPaasRouterList(List<PaasRouter> paasRouterList) {
        this.paasRouterList = paasRouterList;
    }

    public PaasContainerVO createPaasContainerVO() {
        return new PaasContainerVO(getId(), getName(), getState(), new HashMap<String,String>(getCapabilities()),
                isMultitenant(), isReusable(), new LinkedList<Integer>(getUsedPorts()));
    }

    public void mergePaasContainerVO(PaasContainerVO paasContainerVO) {
        setName(paasContainerVO.getName());
        setState(paasContainerVO.getState());
        setCapabilities(paasContainerVO.getCapabilities());
        setMultitenant(paasContainerVO.isMultitenant());
        setReusable(paasContainerVO.isReusable());
        setUsedPorts(paasContainerVO.getUsedPorts());
    }

}
