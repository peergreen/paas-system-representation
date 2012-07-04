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

package org.ow2.jonas.jpaas.sr.facade.model;

import org.ow2.jonas.jpaas.sr.facade.vo.LoadBalancerVO;
import org.ow2.jonas.jpaas.sr.facade.vo.WorkerVO;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @OneToMany(cascade={CascadeType.ALL})
    private List<Worker> workers;


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

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public LoadBalancerVO createLoadBalancerVO() {
        List<WorkerVO> workerVOList = new LinkedList<WorkerVO>();
        for (Worker tmp : workers) {
            workerVOList.add(tmp.createWorkerVO());
        }
        return new LoadBalancerVO(name, new LinkedList<String>(mountPoints), new LinkedList<WorkerVO>(workerVOList));
    }
}
