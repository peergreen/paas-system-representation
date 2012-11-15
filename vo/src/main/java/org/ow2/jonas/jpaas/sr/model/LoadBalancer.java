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

import org.ow2.jonas.jpaas.sr.facade.vo.LoadBalancerVO;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

/**
 * Define a LoadBalancer
 * @author David Richard
 */
@javax.persistence.Entity
@Table
public class LoadBalancer implements java.io.Serializable {

    /**
     * key of the LoadBalancer.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Name of the LoadBalancer.
     */
    private String name;

    /**
     * Mount points of the LoadBalancer.
     */
    @ElementCollection
    private List<String> mountPoints;

    /**
     * Workers of the LoadBalancer.
     */
    @ElementCollection
    private List<String> workers;


    /**
     * ApacheJk of the LoadBalancer.
     */
    @ManyToOne(optional=false)
    private ApacheJk apacheJk;


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

    public List<String> getMountPoints() {
        return mountPoints;
    }

    public void setMountPoints(List<String> mountPoints) {
        this.mountPoints = mountPoints;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connector[key=").append(getKey())
                .append(", name=").append(getName())
                .append(", mountPoints=").append(getMountPoints().toString())
                .append(", apacheJk=").append(getApacheJk())
                .append(", worker=").append(getWorkers().toString())
                .append("]");
        return sb.toString();
    }

    public ApacheJk getApacheJk() {
        return apacheJk;
    }

    public void setApacheJk(ApacheJk apacheJk) {
        this.apacheJk = apacheJk;
    }

    public List<String> getWorkers() {
        return workers;
    }

    public void setWorkers(List<String> workers) {
        this.workers = workers;
    }

    public LoadBalancerVO createLoadBalancerVO() {
        return new LoadBalancerVO(name, new LinkedList<String>(mountPoints), new LinkedList<String>(workers));
    }
}
