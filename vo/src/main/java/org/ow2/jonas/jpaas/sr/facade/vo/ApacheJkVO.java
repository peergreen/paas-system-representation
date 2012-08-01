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
