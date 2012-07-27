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

import org.ow2.jonas.jpaas.sr.model.PaasDatabase;

import java.util.List;

/**
 * Define a PaasDatabase Value Object
 * @author David Richard
 */
public class PaasDatabaseVO extends PaasResourceVO implements java.io.Serializable {

    public PaasDatabaseVO() {
        super();
    }

    public PaasDatabaseVO(String id, String name, String state, List<String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts) {
        super(id, name, state, capabilities, multitenant, reusable, usedPorts);
    }

    public PaasDatabaseVO(String name, String state, List<String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts) {
        super(name, state, capabilities, multitenant, reusable, usedPorts);
    }

    /**
     * Change a PaasDatabase Value Object into an Entity object
     * @return a PaasDatabase Entity
     */
    public PaasDatabase createBean() {
        PaasDatabase paasDatabase = new PaasDatabase();
        paasDatabase.setName(getName());
        paasDatabase.setState(getState());
        paasDatabase.setCapabilities(getCapabilities());
        paasDatabase.setMultitenant(isMultitenant());
        paasDatabase.setReusable(isReusable());
        paasDatabase.setUsedPorts(getUsedPorts());
        return paasDatabase;
    }

}