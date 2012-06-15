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

import org.ow2.jonas.jpaas.sr.model.RelationshipTemplate;
import org.ow2.jonas.jpaas.sr.model.TopologyTemplate;

import java.util.List;

/**
 * Define a RelationshipTemplate Value Object
 * @author David Richard
 */
public class RelationshipTemplateVO implements java.io.Serializable {

    /**
     * Id of the RelationshipTemplate.
     */
    private String id;

    /**
     * Name of the RelationshipTemplate.
     */
    private String name;

    public RelationshipTemplateVO() {

    }

    public RelationshipTemplateVO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public RelationshipTemplateVO(String name) {
            this.id = null;
            this.name = name;
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Change a TopologyTemplate Value Object into an EntityBean object
     * @return a TopologyTemplate EntityBean
     */
    public RelationshipTemplate createBean() {
        RelationshipTemplate relationshipTemplate = new RelationshipTemplate();
        relationshipTemplate.setName(name);
        return relationshipTemplate;
    }


}
