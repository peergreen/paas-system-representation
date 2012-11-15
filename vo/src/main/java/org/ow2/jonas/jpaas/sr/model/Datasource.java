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

import org.ow2.jonas.jpaas.sr.facade.vo.DatasourceVO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Define a Datasource
 * @author David Richard
 */
@javax.persistence.Entity
@Table
public class Datasource implements java.io.Serializable {

    /**
     * key of the Datasource.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Name of the Datasource.
     */
    private String name;

    /**
     * url of the Datasource.
     */
    private String url;

    /**
     * className of the Datasource.
     */
    private String className;

    /**
     * Jonas of the Datasource.
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String port) {
        this.url = port;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connector[key=").append(getKey())
                .append(", name=").append(getName())
                .append(", url=").append(getUrl())
                .append(", className=").append(getClassName())
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

    public DatasourceVO createDatasourceVO() {
        return new DatasourceVO(name, url, className);
    }

}
