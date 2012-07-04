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


import java.util.List;

/**
 * Define a RouterNodeTemplate Value Object
 * @author David Richard
 */
public class RouterNodeTemplateVO extends NodeTemplateVO implements java.io.Serializable {

    public RouterNodeTemplateVO(String id, String name, List<String> requirements, List<String> slaEnforcement,
            int minSize, int maxSize, int currentSize) {
        super(id, name, requirements, slaEnforcement, minSize, maxSize, currentSize);
    }

    public RouterNodeTemplateVO(String name, List<String> requirements, List<String> slaEnforcement, int minSize,
            int maxSize, int currentSize) {
        super(name, requirements, slaEnforcement, minSize, maxSize, currentSize);
    }

}
