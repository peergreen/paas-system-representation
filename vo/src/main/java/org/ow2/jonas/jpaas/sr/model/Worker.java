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
