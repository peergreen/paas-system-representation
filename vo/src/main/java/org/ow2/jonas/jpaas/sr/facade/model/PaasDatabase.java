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

package org.ow2.jonas.jpaas.sr.facade.model;


import org.ow2.jonas.jpaas.sr.facade.vo.PaasDatabaseVO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import java.util.LinkedList;
import java.util.List;


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

    public PaasDatabaseVO createPaasDatabaseVO() {
        return new PaasDatabaseVO(getId(), getName(), getState(), new LinkedList<String>(getCapabilities()),
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