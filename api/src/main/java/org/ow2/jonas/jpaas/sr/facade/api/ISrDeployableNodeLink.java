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
package org.ow2.jonas.jpaas.sr.facade.api;

import org.ow2.jonas.jpaas.sr.facade.vo.DeployableVO;
import org.ow2.jonas.jpaas.sr.facade.vo.NodeTemplateVO;

import java.util.List;

/**
 * Interface for the DeployableNodeLink facade.
 * @author David Richard
 */
public interface ISrDeployableNodeLink {

    /**
     * Add a link between a Deployable and a Node
     *
     * @param deployableId Id of the Deployable
     * @param nodeId Id of the Node
     */
    public void addDeployableNodeLink(String deployableId, String nodeId);

    /**
     * Remove a link between a Deployable and a Node
     *
     * @param deployableId Id of the Deployable
     * @param nodeId Id of the Node
     */
    public void removeDeployableNodeLink(String deployableId, String nodeId);

    /**
     * Get the Deployables of a Node
     *
     * @param nodeId ID of the Node
     * @return a list of Deployable
     */
    public List<DeployableVO> findDeployablesByNode(String nodeId);

    /**
     * Get the Nodes of a Deployable
     *
     * @param deployableId Id of the Deployable
     * @return  a list of NodeTemplate
     */
    public List<NodeTemplateVO> findNodesByDeployable(String deployableId);
}
