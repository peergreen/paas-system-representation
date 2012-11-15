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
