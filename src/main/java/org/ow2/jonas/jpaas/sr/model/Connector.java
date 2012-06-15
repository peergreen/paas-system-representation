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

import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVO;
import org.ow2.jonas.jpaas.sr.facade.vo.ConnectorVO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.LinkedList;

/**
 * Define a Connector
 * @author David Richard
 */
@javax.persistence.Entity
@Table
public class Connector implements java.io.Serializable {

    /**
     * key of the Connector.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Name of the Connector.
     */
    private String name;

    /**
     * port of the Connector.
     */
    private int port;

    /**
     * Jonas of the Connector.
     */
    @ManyToOne(optional=false)
    private Jonas jonas;


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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connector[key=").append(getKey())
                .append(", name=").append(getName())
                .append(", port=").append(getPort())
                .append(", jonas=").append(getJonas())
                .append("]");
        return sb.toString();
    }


    public Jonas getJonas() {
        return jonas;
    }

    public void setJonas(Jonas jonas) {
        this.jonas = jonas;
    }

    public ConnectorVO createConnectorVO() {
        return new ConnectorVO(name, port);
    }


}
