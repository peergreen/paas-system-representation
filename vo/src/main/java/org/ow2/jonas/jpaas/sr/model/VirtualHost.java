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

import org.ow2.jonas.jpaas.sr.facade.vo.VirtualHostVO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Define a VirtualHost
 * @author David Richard
 */
@javax.persistence.Entity
@Table
public class VirtualHost implements java.io.Serializable {

    /**
     * key of the VirtualHost.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Name of the VirtualHost.
     */
    private String name;

    /**
     * PaasFrontend of the VirtualHost.
     */
    @ManyToOne(optional=false)
    private PaasFrontend paasFrontend;


    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VirtualHost[key=").append(getKey())
                .append(", name=").append(getName())
                .append("]");
        return sb.toString();
    }


    public PaasFrontend getPaasFrontend() {
        return paasFrontend;
    }

    public void setPaasFrontend(PaasFrontend paasFrontend) {
        this.paasFrontend = paasFrontend;
    }

    public VirtualHostVO createVirtualHostVO() {
        return new VirtualHostVO(name);
    }

    public void mergeVirtualHostVO(VirtualHostVO virtualHostVO) {
        this.name = virtualHostVO.getName();
    }
}
