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

package org.ow2.jonas.jpaas.sr.facade.vo;

import org.ow2.jonas.jpaas.sr.model.PaasContainer;

import java.util.List;

/**
 * Define a PaasContainer Value Object
 * @author David Richard
 */
public class PaasContainerVO extends PaasResourceVO implements java.io.Serializable {

    public PaasContainerVO() {
        super();
    }

    public PaasContainerVO(String id, String name, String state, List<String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts) {
        super(id, name, state, capabilities, multitenant, reusable, usedPorts);
    }

    public PaasContainerVO(String name, String state, List<String> capabilities, boolean multitenant,
                boolean reusable, List<Integer> usedPorts) {
            super(name, state, capabilities, multitenant, reusable, usedPorts);
        }

    /**
     * Change a PaasContainer Value Object into an Entity object
     * @return a PaasContainer Entity
     */
    public PaasContainer createBean() {
        PaasContainer paasContainer = new PaasContainer();
        paasContainer.setName(getName());
        paasContainer.setState(getState());
        paasContainer.setCapabilities(getCapabilities());
        paasContainer.setMultitenant(isMultitenant());
        paasContainer.setReusable(isReusable());
        paasContainer.setUsedPorts(getUsedPorts());
        return paasContainer;
    }

}
