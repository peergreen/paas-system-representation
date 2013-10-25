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


import org.ow2.jonas.jpaas.sr.facade.vo.ConnectorVO;
import org.ow2.jonas.jpaas.sr.facade.vo.DatasourceVO;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Define a Jonas
 * @author David Richard
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Jonas extends PaasContainer implements java.io.Serializable {

    /**
     * Version of the Jonas.
     */
    private String jonasVersion;

    /**
     * Profile of the Jonas.
     */
    private String profile;

    /**
     * Version of the JDK.
     */
    private String jdkVersion;

    /**
     * Domain of the Jonas.
     */
    private String domain;


    public String getJonasVersion() {
        return jonasVersion;
    }

    public void setJonasVersion(String jonasVersion) {
        this.jonasVersion = jonasVersion;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getJdkVersion() {
        return jdkVersion;
    }

    public void setJdkVersion(String jdkVersion) {
        this.jdkVersion = jdkVersion;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }


    public JonasVO createVO() {
        JonasVO jonasVO = new JonasVO(getId(), getName(), getState(), new HashMap<String,String>(getCapabilities()),
                isMultitenant(), isReusable(), new LinkedList<Integer>(getUsedPorts()), jonasVersion, profile,
                jdkVersion, domain);
        
        return jonasVO;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jonas[Id=").append(getId())
                .append(", name=").append(getName())
                .append(", state=").append(getState())
                .append(", jonasVersion=").append(getJonasVersion())
                .append(", profile=").append(getProfile())
                .append(", jdkVersion=").append(getJdkVersion())
                .append(", domain=").append(getDomain())
                .append("]");
        return sb.toString();
    }

    public void mergeJonasVO(JonasVO jonasVO) {
        super.mergePaasContainerVO(jonasVO);
        setJonasVersion(jonasVO.getJonasVersion());
        setProfile(jonasVO.getProfile());
        setJdkVersion(jonasVO.getJdkVersion());
        setDomain(jonasVO.getDomain());
    }

}
