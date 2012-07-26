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

import org.ow2.jonas.jpaas.sr.facade.vo.WorkerVO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Define a Worker
 * @author David Richard
 */
@javax.persistence.Entity
@Table
public class Worker implements java.io.Serializable {

    /**
     * key of the Worker.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Name of the Worker.
     */
    private String name;

    /**
     * Host of the Worker.
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

    /**
     * ApacheJk of the Worker.
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connector[key=").append(getKey())
                .append(", name=").append(getName())
                .append(", host=").append(getHost())
                .append(", port=").append(getPort())
                .append(", status=").append(getStatus())
                .append(", apacheJk=").append(getApacheJk())
                .append("]");
        return sb.toString();
    }

    public ApacheJk getApacheJk() {
        return apacheJk;
    }

    public void setApacheJk(ApacheJk apacheJk) {
        this.apacheJk = apacheJk;
    }

    public WorkerVO createWorkerVO() {
        return new WorkerVO(name, host, port, status);
    }
}
