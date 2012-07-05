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

import org.ow2.jonas.jpaas.sr.model.PaasArtefact;

/**
 * Define a PaasArtefact Value Object
 * @author David Richard
 */
public class PaasArtefactVO implements java.io.Serializable {

    /**
     * Id of the PaasArtefact.
     */
    private String id;

    /**
     * ApplicationVersionInstance of the PaasArtefact.
     */
    private String instanceId;


    public PaasArtefactVO(String id) {
        this.id = id;
    }

    public PaasArtefactVO() {

    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * Change a PaasArtefact Value Object into an EntityBean object
     * @return a PaasArtefact EntityBean
     */
    public PaasArtefact createBean() {
        PaasArtefact paasArtefact = new PaasArtefact();
        paasArtefact.setId(id);
        return paasArtefact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
