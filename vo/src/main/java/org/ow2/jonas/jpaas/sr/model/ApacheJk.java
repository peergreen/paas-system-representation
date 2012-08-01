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


import org.ow2.jonas.jpaas.sr.facade.vo.ApacheJkVO;
import org.ow2.jonas.jpaas.sr.facade.vo.LoadBalancerVO;
import org.ow2.jonas.jpaas.sr.facade.vo.WorkerVO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;


/**
 * Define a ApacheJk
 * @author David Richard
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class ApacheJk extends PaasRouter implements java.io.Serializable {

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
    @OneToMany(mappedBy="apacheJk", orphanRemoval=true, cascade={CascadeType.ALL})
    private List<LoadBalancer> loadBalancerList;

    /**
     * Workers of the Apache.
     */
    @OneToMany(mappedBy="apacheJk", orphanRemoval=true, cascade={CascadeType.ALL})
    private List<Worker> workerList;

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

    public List<LoadBalancer> getLoadBalancerList() {
        return loadBalancerList;
    }

    public void setLoadBalancerList(List<LoadBalancer> loadBalancerList) {
        this.loadBalancerList = loadBalancerList;
    }

    public List<Worker> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<Worker> workerList) {
        this.workerList = workerList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ApacheJk[Id=").append(getId())
                .append(", name=").append(getName())
                .append(", state=").append(getState())
                .append(", apacheVersion=").append(getApacheVersion())
                .append(", jkVersion=").append(getJkVersion())
                .append("]");
        return sb.toString();
    }

    public ApacheJkVO createApacheJkVO() {
        ApacheJkVO apacheJkVO = new ApacheJkVO(getId(), getName(), getState(),
                new LinkedList<String>(getCapabilities()), isMultitenant(), isReusable(),
                new LinkedList<Integer>(getUsedPorts()), apacheVersion, jkVersion);
        if (workerList != null) {
            for (Worker tmp : workerList) {
                apacheJkVO.getWorkerList().add(tmp.createWorkerVO());
            }
        }
        if (loadBalancerList != null) {
            for (LoadBalancer tmp : loadBalancerList) {
                apacheJkVO.getLoadBalancerList().add(tmp.createLoadBalancerVO());
            }
        }
        return apacheJkVO;
    }

    public void mergeApacheJkVO(ApacheJkVO apacheJkVO) {
        setName(apacheJkVO.getName());
        setState(apacheJkVO.getState());
        setCapabilities(apacheJkVO.getCapabilities());
        setMultitenant(apacheJkVO.isMultitenant());
        setReusable(apacheJkVO.isReusable());
        setUsedPorts(apacheJkVO.getUsedPorts());
        setApacheVersion(apacheJkVO.getApacheVersion());
        setJkVersion(apacheJkVO.getJkVersion());
        if (apacheJkVO.getWorkerList() != null) {
            LinkedList<Worker> originalWorkerList = new LinkedList<Worker>(this.workerList);
            this.workerList.clear();
            for (WorkerVO tmp : apacheJkVO.getWorkerList()) {
                Worker worker = tmp.createBean();
                for (Worker originalWorker : originalWorkerList) {
                    if (originalWorker.getName().equals(worker.getName())) {
                        worker.setKey(originalWorker.getKey());
                        break;
                    }
                }
                this.workerList.add(worker);
            }
        }
        if (apacheJkVO.getLoadBalancerList() != null) {
            LinkedList<LoadBalancer> originalLoadBalancerList = new LinkedList<LoadBalancer>(this.loadBalancerList);
            this.loadBalancerList.clear();
            for (LoadBalancerVO tmp : apacheJkVO.getLoadBalancerList()) {
                LoadBalancer loadBalancer = tmp.createBean();
                for (LoadBalancer originalLoadBalancer : originalLoadBalancerList) {
                    if (originalLoadBalancer.getName().equals(loadBalancer.getName())) {
                        loadBalancer.setKey(originalLoadBalancer.getKey());
                        break;
                    }
                }
                this.loadBalancerList.add(loadBalancer);
            }
        }
    }
}