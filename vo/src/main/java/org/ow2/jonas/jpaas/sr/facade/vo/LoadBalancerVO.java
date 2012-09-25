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
