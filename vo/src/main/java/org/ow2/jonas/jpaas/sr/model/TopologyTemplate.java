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

import org.ow2.jonas.jpaas.sr.facade.vo.NodeTemplateVO;
import org.ow2.jonas.jpaas.sr.facade.vo.RelationshipTemplateVO;
import org.ow2.jonas.jpaas.sr.facade.vo.TopologyTemplateVO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

/**
 * Define a TopologyTemplate
 * @author David Richard
 */
@Entity
@Table
public class TopologyTemplate implements java.io.Serializable {

    /**
     * key of the TopologyTemplate.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * NodeTemplate of the TopologyTemplate.
     */
    @OneToMany(mappedBy="topologyTemplate", orphanRemoval=true, cascade={CascadeType.ALL})
    private List<NodeTemplate> nodeTemplateList;

    /**
     * NodeTemplate of the TopologyTemplate.
     */
    @OneToMany(mappedBy="topologyTemplate", orphanRemoval=true, cascade={CascadeType.ALL})
    private List<RelationshipTemplate> relationshipTemplateList;

    /**
     * Environment of the TopologyTemplate.
     */
    @OneToOne(optional=false)
    private Environment environment;


    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TopologyTemplate[key=").append(getKey())
                .append("]");
        return sb.toString();
    }

    public List<NodeTemplate> getNodeTemplateList() {
        return nodeTemplateList;
    }

    public void setNodeTemplateList(List<NodeTemplate> nodeTemplateList) {
        this.nodeTemplateList = nodeTemplateList;
    }

    public List<RelationshipTemplate> getRelationshipTemplateList() {
        return relationshipTemplateList;
    }

    public void setRelationshipTemplateList(List<RelationshipTemplate> relationshipTemplateList) {
        this.relationshipTemplateList = relationshipTemplateList;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public TopologyTemplateVO createTopologyTemplateVO() {
        TopologyTemplateVO topologyTemplateVO = new TopologyTemplateVO();
        if (nodeTemplateList != null) {
            for (NodeTemplate tmp : nodeTemplateList) {
                topologyTemplateVO.getNodeTemplateList().add(tmp.createNodeTemplateVO());
            }
        }
        if (relationshipTemplateList != null) {
            for (RelationshipTemplate tmp : relationshipTemplateList) {
                topologyTemplateVO.getRelationshipTemplateList().add(tmp.createRelationshipTemplateVO());
            }
        }
        return topologyTemplateVO;
    }

/*    private NodeTemplateVO createNodeTemplateInheritance(NodeTemplate nodeTemplate) {
        NodeTemplateVO result = null;
        if (nodeTemplate instanceof ContainerNodeTemplate) {
            ContainerNodeTemplate containerNodeTemplate = (ContainerNodeTemplate) nodeTemplate;
            result = containerNodeTemplate.createNodeTemplateVO();
        } else if (nodeTemplate instanceof RouterNodeTemplate) {
            RouterNodeTemplate routerNodeTemplate = (RouterNodeTemplate) nodeTemplate;
            result = routerNodeTemplate.createNodeTemplateVO();
        } else if (nodeTemplate instanceof DatabaseNodeTemplate) {
            DatabaseNodeTemplate databaseNodeTemplate = (DatabaseNodeTemplate) nodeTemplate;
            result = databaseNodeTemplate.createNodeTemplateVO();
        }
        else if (nodeTemplate instanceof IaasComputeNodeTemplate) {
            IaasComputeNodeTemplate iaasComputeNodeTemplate = (IaasComputeNodeTemplate) nodeTemplate;
            result = iaasComputeNodeTemplate.createNodeTemplateVO();
        } else {
            result = nodeTemplate.createNodeTemplateVO();
        }
        return result;
    }

    private RelationshipTemplateVO createRelationshipTemplateInheritance(RelationshipTemplate relationshipTemplate) {
        RelationshipTemplateVO result = null;
            if (relationshipTemplate instanceof ConnectorTemplate) {
                ConnectorTemplate connectorTemplate = (ConnectorTemplate) relationshipTemplate;
                result = connectorTemplate.createRelationshipTemplateVO();
            } else if (relationshipTemplate instanceof DatasourceTemplate) {
                DatasourceTemplate datasourceTemplate = (DatasourceTemplate) relationshipTemplate;
                result = datasourceTemplate.createRelationshipTemplateVO();
            } else {
                result = relationshipTemplate.createRelationshipTemplateVO();
            }
            return result;
        }*/

    public void mergeTopologyTemplateVO(TopologyTemplateVO topologyTemplateVO) {
        if (topologyTemplateVO.getNodeTemplateList() != null) {
            LinkedList<NodeTemplate> originalNodeTemplateList = new LinkedList<NodeTemplate>(nodeTemplateList);
            this.nodeTemplateList.clear();
            for (NodeTemplateVO tmp : topologyTemplateVO.getNodeTemplateList()) {
                NodeTemplate nodeTemplate = tmp.createBean();
                if (nodeTemplate.getId() != null) {
                    for (NodeTemplate originalNodeTemplate : originalNodeTemplateList) {
                        if (originalNodeTemplate.getId().equals(nodeTemplate.getId())) {
                            nodeTemplate.setKey(originalNodeTemplate.getKey());
                            break;
                        }
                    }
                }
                nodeTemplateList.add(nodeTemplate);
            }
        }
        if (topologyTemplateVO.getRelationshipTemplateList() != null) {
            LinkedList<RelationshipTemplate> originalRelationshipTemplateList = new LinkedList<RelationshipTemplate>(relationshipTemplateList);
            this.relationshipTemplateList.clear();
            for (RelationshipTemplateVO tmp : topologyTemplateVO.getRelationshipTemplateList()) {
                RelationshipTemplate relationshipTemplate = tmp.createBean();
                if (relationshipTemplate.getId() != null) {
                    for (RelationshipTemplate originalRelationshipTemplate : originalRelationshipTemplateList) {
                        if (originalRelationshipTemplate.getId().equals(relationshipTemplate.getId())) {
                            relationshipTemplate.setKey(originalRelationshipTemplate.getKey());
                            break;
                        }
                    }
                }
                relationshipTemplateList.add(relationshipTemplate);
            }
        }
    }
}
