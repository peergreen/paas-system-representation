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
