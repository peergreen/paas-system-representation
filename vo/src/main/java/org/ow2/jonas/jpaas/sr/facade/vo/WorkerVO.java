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

import org.ow2.jonas.jpaas.sr.facade.model.Worker;



/**
 * Define a Worker Value Object
 * @author David Richard
 */
public class WorkerVO implements java.io.Serializable {

    /**
     * Name of the Worker.
     */
    private String name;

    /**
     * host of the Worker.
     */
    private String host;

    /**
     * port of the Worker.
     */
    private int port;


    public WorkerVO() {
    }

    public WorkerVO(String name, String host, int port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    public String getLoadBalancerName() {
        return name;
    }

    public void setLoadBalancerName(String name) {
        this.name = name;
    }

    public String getMountPoints() {
        return host;
    }

    public void setMountPoints(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Change a Worker Value Object into an Entity object
     * @return a Worker Entity
     */
    public Worker createBean() {
        Worker worker = new Worker();
        worker.setName(name);
        worker.setHost(host);
        worker.setPort(port);
        return worker;
    }
}
