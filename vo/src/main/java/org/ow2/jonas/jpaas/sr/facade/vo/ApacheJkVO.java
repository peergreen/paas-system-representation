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

import org.ow2.jonas.jpaas.sr.model.ApacheJk;
import org.ow2.jonas.jpaas.sr.model.LoadBalancer;
import org.ow2.jonas.jpaas.sr.model.Worker;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Define an ApacheJkVO Value Object
 * @author David Richard
 */
public class ApacheJkVO extends PaasRouterVO implements java.io.Serializable {


    /**
     * Version of the Apache.
     */
    private String apacheVersion;

    /**
     * Version of the Jk module.
     */
    private String jkVersion;

    /**
     * LoadBalancers of the Apache.
     */
    private List<LoadBalancerVO> loadBalancerList;

    /**
     * Workers of the Apache.
     */
    private List<WorkerVO> workerList;

    public ApacheJkVO() {
        super();
        this.loadBalancerList = new LinkedList<LoadBalancerVO>();
        this.workerList = new LinkedList<WorkerVO>();
    }

    public ApacheJkVO(String id, String name, String state, Map<String,String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts, String apacheVersion, String jkVersion) {
        super(id, name, state, capabilities, multitenant, reusable, usedPorts);
        this.apacheVersion = apacheVersion;
        this.jkVersion = jkVersion;
        this.loadBalancerList = new LinkedList<LoadBalancerVO>();
        this.workerList = new LinkedList<WorkerVO>();
    }

    public ApacheJkVO(String name, String state, Map<String,String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts, String apacheVersion, String jkVersion) {
        super(name, state, capabilities, multitenant, reusable, usedPorts);
        this.apacheVersion = apacheVersion;
        this.jkVersion = jkVersion;
        this.loadBalancerList = new LinkedList<LoadBalancerVO>();
        this.workerList = new LinkedList<WorkerVO>();
    }

    public String getApacheVersion() {
        return apacheVersion;
    }

    public void setApacheVersion(String apacheVersion) {
        this.apacheVersion = apacheVersion;
    }

    public String getJkVersion() {
        return jkVersion;
    }

    public void setJkVersion(String jkVersion) {
        this.jkVersion = jkVersion;
    }

    /**
     * Change a ApacheJk Value Object into an Entity object
     * @return a ApacheJk Entity
     */
    public ApacheJk createBean() {
        ApacheJk apacheJk = new ApacheJk();
        apacheJk.setName(getName());
        apacheJk.setState(getState());
        apacheJk.setCapabilities(getCapabilities());
        apacheJk.setMultitenant(isMultitenant());
        apacheJk.setReusable(isReusable());
        apacheJk.setUsedPorts(getUsedPorts());
        apacheJk.setApacheVersion(apacheVersion);
        apacheJk.setJkVersion(jkVersion);
        if (workerList != null) {
            List<Worker> list = new LinkedList<Worker>();
            for (WorkerVO tmp : workerList ) {
                Worker worker = tmp.createBean();
                worker.setApacheJk(apacheJk);
                list.add(worker);
            }
            apacheJk.setWorkerList(list);
        }
        if (loadBalancerList != null) {
            List<LoadBalancer> list = new LinkedList<LoadBalancer>();
            for (LoadBalancerVO tmp : loadBalancerList ) {
                LoadBalancer loadBalancer = tmp.createBean();
                loadBalancer.setApacheJk(apacheJk);
                list.add(loadBalancer);
            }
            apacheJk.setLoadBalancerList(list);
        }
        return apacheJk;
    }

    public List<LoadBalancerVO> getLoadBalancerList() {
        return loadBalancerList;
    }

    public void setLoadBalancerList(List<LoadBalancerVO> loadBalancerList) {
        this.loadBalancerList = loadBalancerList;
    }

    public List<WorkerVO> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<WorkerVO> workerList) {
        this.workerList = workerList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ApacheJkVO[Id=").append(getId())
                .append(", name=").append(getName())
                .append(", state=").append(getState())
                .append(", apacheVersion=").append(getApacheVersion())
                .append(", jkVersion=").append(getJkVersion())
                .append("]");
        return sb.toString();
    }
}
