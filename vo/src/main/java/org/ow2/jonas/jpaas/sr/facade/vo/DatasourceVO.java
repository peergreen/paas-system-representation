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

import org.ow2.jonas.jpaas.sr.model.Datasource;

/**
 * Define a Datasource Value Object
 * @author David Richard
 */
public class DatasourceVO implements java.io.Serializable {

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

    public DatasourceVO() {
    }

    public DatasourceVO(String name, String url, String className) {
        this.name = name;
        this.url = url;
        this.className = className;
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

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Change a Datasource Value Object into an Entity object
     * @return a Datasource Entity
     */
    public Datasource createBean() {
        Datasource datasource = new Datasource();
        datasource.setName(name);
        datasource.setUrl(url);
        datasource.setClassName(className);
        return datasource;
    }
}
