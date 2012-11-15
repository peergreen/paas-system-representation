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
