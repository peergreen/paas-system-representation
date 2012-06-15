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

import org.ow2.jonas.jpaas.sr.facade.vo.PaasArtefactVO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Define a PaasArtefact
 * @author David Richard
 */
@Entity
@Table
public class PaasArtefact implements java.io.Serializable {

    /**
     * key of the PaasArtefact.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Id of the PaasArtefact.
     */
    private String id;

    /**
     * Deployables of the PaasArtefact.
     */
    @OneToMany(mappedBy="paasArtefact", cascade={CascadeType.ALL})
    private List<Deployable> deployable;

    /**
     * ApplicationVersionInstance of the PaasArtefact.
     */
    @ManyToOne(optional=false)
    private ApplicationVersionInstance applicationVersionInstance;


    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PaasArtefact[key=").append(getKey())
                .append("]");
        return sb.toString();
    }

    public List<Deployable> getDeployable() {
        return deployable;
    }

    public void setDeployable(List<Deployable> deployable) {
        this.deployable = deployable;
    }

    public ApplicationVersionInstance getApplicationVersionInstance() {
        return applicationVersionInstance;
    }

    public void setApplicationVersionInstance(ApplicationVersionInstance applicationVersionInstance) {
        this.applicationVersionInstance = applicationVersionInstance;
    }

    public PaasArtefactVO createPaasArtefactVO() {
        PaasArtefactVO paasArtefactVO = new PaasArtefactVO(id);
        paasArtefactVO.setInstanceId(applicationVersionInstance.getId());
        return paasArtefactVO;
    }

    public void mergePaasArtefactVO(PaasArtefactVO paasArtefactVO){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
