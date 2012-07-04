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

import org.ow2.jonas.jpaas.sr.facade.model.VirtualHost;


/**
 * Define a VirtualHost Value Object
 * @author David Richard
 */
public class VirtualHostVO implements java.io.Serializable {

    /**
     * Name of the VirtualHost.
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public VirtualHostVO(String name) {
        this.name = name;
    }

    /**
     * Change a VirtualHost Value Object into an Entity object
     * @return a VirtualHost Entity
     */
    public VirtualHost createBean() {
        VirtualHost virtualHost = new VirtualHost();
        virtualHost.setName(getName());
        return virtualHost;
    }
}
