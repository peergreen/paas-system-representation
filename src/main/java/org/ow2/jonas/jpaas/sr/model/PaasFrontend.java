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

import org.ow2.jonas.jpaas.sr.facade.vo.PaasFrontendVO;
import org.ow2.jonas.jpaas.sr.facade.vo.VirtualHostVO;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

/**
 * Define a PaasFrontend
 * @author David Richard
 */
@javax.persistence.Entity
@Table
public class PaasFrontend implements java.io.Serializable {

    /**
     * key of the PaasFrontend.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Id of the PaasFrontend.
     */
    private String id;

    /**
     * VirtualHosts of the PaasFrontend.
     */
    @OneToMany(mappedBy="paasFrontend", orphanRemoval=true, cascade={CascadeType.ALL})
    private List<VirtualHost> virtualHosts;

    /**
     * PaasRouters of the VirtualHost.
     */
    @ManyToMany(mappedBy="paasFrontendList", cascade = CascadeType.MERGE)
    private List<PaasRouter> paasRouterList;


    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PaasFrontend[key=").append(getKey())
                .append(", id=").append(getId())
                .append("]");
        return sb.toString();
    }

    public List<VirtualHost> getVirtualHosts() {
        return virtualHosts;
    }

    public void setVirtualHosts(List<VirtualHost> virtualHostList) {
        this.virtualHosts = virtualHostList;
    }

    public PaasFrontendVO createPaasFrontendVO() {
        List<VirtualHostVO> virtualHostVOList = new LinkedList<VirtualHostVO>();
        for (VirtualHost tmp : virtualHosts) {
            virtualHostVOList.add(tmp.createVirtualHostVO());
        }
        return new PaasFrontendVO(id, virtualHostVOList);
    }

    public void mergePaasFrontendVO(PaasFrontendVO paasFrontendVO) {
/*        for (VirtualHostVO tmp : paasFrontendVO.getVirtualHosts()) {
            VirtualHost virtualHost = tmp.createBean();
            virtualHost.setPaasFrontend(this);
            virtualHostList.add(virtualHost);
        }*/
    }

    public List<PaasRouter> getPaasRouterList() {
        return paasRouterList;
    }

    public void setPaasRouterList(List<PaasRouter> paasRouterList) {
        this.paasRouterList = paasRouterList;
    }
}
