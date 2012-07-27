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

import org.ow2.jonas.jpaas.sr.model.Worker;

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

    /**
     * Status of the Worker.
     */
    private String status;


    public WorkerVO() {
    }

    public WorkerVO(String name, String host, int port, String status) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        worker.setStatus(status);
        return worker;
    }

}