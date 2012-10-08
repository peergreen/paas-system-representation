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

package org.ow2.jonas.jpaas.sr.model;


import org.ow2.jonas.jpaas.sr.facade.vo.PaasResourceVO;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import java.util.LinkedList;
import java.util.HashMap;


/**
 * Define a PaasResource
 * @author David Richard
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PaasResource extends PaasEntity implements java.io.Serializable {

    /**
     * PaasAgent of the PaasEntity.
     */
    @ManyToOne
    private PaasAgent paasAgent;

    public PaasAgent getPaasAgent() {
        return paasAgent;
    }

    public void setPaasAgent(PaasAgent paasAgent) {
        this.paasAgent = paasAgent;
    }

    public PaasResourceVO createVO() {
        return new PaasResourceVO(getId(), getName(), getState(), new HashMap<String,String>(getCapabilities()),
                isMultitenant(), isReusable(), new LinkedList<Integer>(getUsedPorts()));
    }

    public void mergePaasResourceVO(PaasResourceVO paasResourceVO) {
        setName(paasResourceVO.getName());
        setState(paasResourceVO.getState());
        setCapabilities(paasResourceVO.getCapabilities());
        setMultitenant(paasResourceVO.isMultitenant());
        setReusable(paasResourceVO.isReusable());
        setUsedPorts(paasResourceVO.getUsedPorts());
    }
}