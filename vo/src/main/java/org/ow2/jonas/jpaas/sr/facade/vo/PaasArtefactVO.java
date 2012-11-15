/**
 * JPaaS
 * Copyright 2012 Bull S.A.S.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * $Id:$
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
