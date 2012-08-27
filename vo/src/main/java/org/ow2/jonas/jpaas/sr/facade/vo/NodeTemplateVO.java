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

import org.ow2.jonas.jpaas.sr.model.NodeTemplate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Define a NodeTemplate Value Object
 * @author David Richard
 */
public class NodeTemplateVO implements java.io.Serializable {

    /**
     * Id of the Node.
     */
    private String id;

    /**
     * Name of the Node.
     */
    private String name;

    /**
     * Requirements of the Node.
     */
    private List<String> requirements = new LinkedList<String>();

    /**
     * SlaEnforcement of the Node.
     */
    private Map<String,String> slaEnforcement = new HashMap<String, String>();

    /**
     * Minimum size of the Node.
     */
    private int minSize;

    /**
     * Maximum size of the Node.
     */
    private int maxSize;

    /**
     * Maximum size of the Node.
     */
    private int currentSize;

    public NodeTemplateVO() {
        this.requirements = new LinkedList<String>();
        this.slaEnforcement = new HashMap<String, String>();
    }

    public NodeTemplateVO(String id, String name, List<String> requirements, Map<String,String> slaEnforcement,
            int minSize, int maxSize, int currentSize) {
        this.id = id;
        this.name = name;
        this.requirements = requirements;
        this.slaEnforcement = slaEnforcement;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.currentSize = currentSize;
    }

    public NodeTemplateVO(String name, List<String> requirements, Map<String,String> slaEnforcement,
            int minSize, int maxSize, int currentSize) {
            this.name = name;
            this.requirements = requirements;
            this.slaEnforcement = slaEnforcement;
            this.minSize = minSize;
            this.maxSize = maxSize;
            this.currentSize = currentSize;
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


    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public Map<String,String> getSlaEnforcement() {
        return slaEnforcement;
    }

    public void setSlaEnforcement(Map<String,String> slaEnforcement) {
        this.slaEnforcement = slaEnforcement;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NodeTemplate[id=").append(getId())
                .append(", name=").append(getName())
                .append(", requirements=").append(getRequirements().toString())
                .append(", slaEnforcement=").append(getSlaEnforcement().toString())
                .append(", minSize=").append(getMinSize())
                .append(", maxSize=").append(getMaxSize())
                .append(", currentSize=").append(getCurrentSize())
                .append("]");
        return sb.toString();
    }

    /**
     * Change a NodeTemplate Value Object into an Entity object
     * @return a NodeTemplate Entity
     */
    public NodeTemplate createBean() {
        NodeTemplate nodeTemplate = new NodeTemplate();
        nodeTemplate.setId(id);
        nodeTemplate.setName(name);
        nodeTemplate.setRequirements(requirements);
        nodeTemplate.setSlaEnforcement(slaEnforcement);
        nodeTemplate.setMinSize(minSize);
        nodeTemplate.setMaxSize(maxSize);
        nodeTemplate.setCurrentSize(currentSize);
        return nodeTemplate;
    }

}
