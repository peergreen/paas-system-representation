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
import org.ow2.jonas.jpaas.sr.model.RelationshipTemplate;
import org.ow2.jonas.jpaas.sr.model.TopologyTemplate;

import java.util.LinkedList;
import java.util.List;

/**
 * Define a TopologyTemplate Value Object
 * @author David Richard
 */
public class TopologyTemplateVO implements java.io.Serializable {

    /**
     * NodeTemplate of the TopologyTemplate.
     */
    private List<NodeTemplateVO> nodeTemplateList;

    /**
     * NodeTemplate of the TopologyTemplate.
     */
    private List<RelationshipTemplateVO> relationshipTemplateList;


    public TopologyTemplateVO() {
        this.nodeTemplateList = new LinkedList<NodeTemplateVO>();
        this.relationshipTemplateList = new LinkedList<RelationshipTemplateVO>();
    }

    public TopologyTemplateVO(List<NodeTemplateVO> nodeTemplateList,
            List<RelationshipTemplateVO> relationshipTemplateList) {
        this.nodeTemplateList = nodeTemplateList;
        this.relationshipTemplateList = relationshipTemplateList;
    }

    public List<NodeTemplateVO> getNodeTemplateList() {
        return nodeTemplateList;
    }

    public void setNodeTemplateList(List<NodeTemplateVO> nodeTemplateList) {
        this.nodeTemplateList = nodeTemplateList;
    }

    public List<RelationshipTemplateVO> getRelationshipTemplateList() {
        return relationshipTemplateList;
    }

    public void setRelationshipTemplateList(List<RelationshipTemplateVO> relationshipTemplateList) {
        this.relationshipTemplateList = relationshipTemplateList;
    }

    /**
     * Change a TopologyTemplate Value Object into an EntityBean object
     * @return a TopologyTemplate EntityBean
     */
    public TopologyTemplate createBean() {
        TopologyTemplate topologyTemplate = new TopologyTemplate();
        if (nodeTemplateList != null) {
            List<NodeTemplate> list = new LinkedList<NodeTemplate>();
            for (NodeTemplateVO tmp : nodeTemplateList ) {
                NodeTemplate nodeTemplate = tmp.createBean();
                nodeTemplate.setTopologyTemplate(topologyTemplate);
                list.add(nodeTemplate);
            }
            topologyTemplate.setNodeTemplateList(list);
        }
        if (relationshipTemplateList != null) {
            List<RelationshipTemplate> list = new LinkedList<RelationshipTemplate>();
            for (RelationshipTemplateVO tmp : relationshipTemplateList ) {
                RelationshipTemplate relationshipTemplate = tmp.createBean();
                relationshipTemplate.setTopologyTemplate(topologyTemplate);
                list.add(relationshipTemplate);
            }
            topologyTemplate.setRelationshipTemplateList(list);
        }
        return topologyTemplate;
    }


}
