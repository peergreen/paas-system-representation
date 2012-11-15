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
