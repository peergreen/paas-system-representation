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

import org.ow2.jonas.jpaas.sr.model.PaasFrontend;
import org.ow2.jonas.jpaas.sr.model.VirtualHost;

import java.util.LinkedList;
import java.util.List;

/**
 * Define a PaasFrontend Value Object
 * @author David Richard
 */
public class PaasFrontendVO implements java.io.Serializable {

    /**
     * Id of the PaasFrontend.
     */
    private String id;

    /**
     * VirtualHosts of the PaasFrontend.
     */
    private List<VirtualHostVO> virtualHosts;

    public PaasFrontendVO() {
        virtualHosts = new LinkedList<VirtualHostVO>();
    }

    public PaasFrontendVO(String id, List<VirtualHostVO> virtualHosts) {
        this.id = id;
        this.virtualHosts = virtualHosts;
    }

    public PaasFrontendVO(List<VirtualHostVO> virtualHosts) {
        this.id = null;
        this.virtualHosts = virtualHosts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<VirtualHostVO> getVirtualHosts() {
        return virtualHosts;
    }

    public void setVirtualHosts(List<VirtualHostVO> virtualHosts) {
        this.virtualHosts = virtualHosts;
    }

    /**
     * Change a PaasFrontend Value Object into an Entity object
     * @return a PaasFrontend Entity
     */
    public PaasFrontend createBean() {
        PaasFrontend paasFrontend = new PaasFrontend();
        List<VirtualHost> virtualHostList = new LinkedList<VirtualHost>();
        for (VirtualHostVO tmp : virtualHosts) {
            VirtualHost virtualHost = tmp.createBean();
            virtualHost.setPaasFrontend(paasFrontend);
            virtualHostList.add(virtualHost);
        }
        paasFrontend.setVirtualHosts(virtualHostList);
        return paasFrontend;
    }

}
