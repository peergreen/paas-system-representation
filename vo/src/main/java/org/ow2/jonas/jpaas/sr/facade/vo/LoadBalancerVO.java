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

import org.ow2.jonas.jpaas.sr.model.LoadBalancer;
import org.ow2.jonas.jpaas.sr.model.Worker;

import java.util.LinkedList;
import java.util.List;

/**
 * Define a LoadBalancer Value Object
 * @author David Richard
 */
public class LoadBalancerVO implements java.io.Serializable {

    /**
     * Name of the LoadBalancer.
     */
    private String name;

    /**
     * Mount points of the LoadBalancer.
     */
    private List<String> mountPoints;

    /**
     * Workers of the LoadBalancer
     */
    private List<String> workers;


    public LoadBalancerVO() {
        mountPoints = new LinkedList<String>();
        workers = new LinkedList<String>();
    }

    public LoadBalancerVO(String name, List<String> mountPoints, List<String> workers) {
        this.name = name;
        this.mountPoints = new LinkedList<String>(mountPoints);
        this.workers = new LinkedList<String>(workers);
    }

    public String getLoadBalancerName() {
        return name;
    }

    public void setLoadBalancerName(String name) {
        this.name = name;
    }

    public List<String> getMountPoints() {
        return mountPoints;
    }

    public void setMountPoints(List<String> mountPoints) {
        this.mountPoints = mountPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getWorkers() {
        return workers;
    }

    public void setWorkers(List<String> workers) {
        this.workers = workers;
    }

    /**
     * Change a LoadBalancer Value Object into an Entity object
     * @return a LoadBalancer Entity
     */
    public LoadBalancer createBean() {
        LoadBalancer loadBalancer = new LoadBalancer();
        loadBalancer.setName(name);
        loadBalancer.setMountPoints(mountPoints);
        loadBalancer.setWorkers(workers);
        return loadBalancer;
    }

}
