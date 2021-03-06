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


import org.ow2.jonas.jpaas.sr.facade.vo.PaasDatabaseVO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;


/**
 * Define a PaasDatabase
 * @author David Richard
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PaasDatabase extends PaasResource implements java.io.Serializable {

    /**
     * PaasContainers of the PaasDatabase.
     */
    @ManyToMany(mappedBy = "paasDatabaseList",  cascade = CascadeType.MERGE)
    private List<PaasContainer> paasContainerList;

    public PaasDatabaseVO createVO() {
        return new PaasDatabaseVO(getId(), getName(), getState(), new HashMap<String,String>(getCapabilities()),
                isMultitenant(), isReusable(), new LinkedList<Integer>(getUsedPorts()));
    }

    public void mergePaasDatabaseVO(PaasDatabaseVO paasDatabaseVO) {
        setName(paasDatabaseVO.getName());
        setState(paasDatabaseVO.getState());
        setCapabilities(paasDatabaseVO.getCapabilities());
        setMultitenant(paasDatabaseVO.isMultitenant());
        setReusable(paasDatabaseVO.isReusable());
        setUsedPorts(paasDatabaseVO.getUsedPorts());
    }

    public List<PaasContainer> getPaasContainerList() {
        return paasContainerList;
    }

    public void setPaasContainerList(List<PaasContainer> paasContainerList) {
        this.paasContainerList = paasContainerList;
    }
}
