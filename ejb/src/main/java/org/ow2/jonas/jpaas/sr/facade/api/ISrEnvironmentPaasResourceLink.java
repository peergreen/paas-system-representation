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

import org.ow2.jonas.jpaas.sr.facade.vo.NodeTemplateVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasResourceVO;

import java.util.List;

/**
 * Interface for the EnvironmentPaasResourceLink facade.
 * @author David Richard
 */
public interface ISrEnvironmentPaasResourceLink {

    /**
     * Add a link between a PaasResource and a NodeTemplate
     *
     * @param nodeId Id of the NodeTemplate
     * @param paasResourceId Id of the PaasResource
     */
    public void addPaasResourceNodeLink(String nodeId, String paasResourceId);

    /**
     * Remove a link between a PaasResource and a NodeTemplate
     *
     * @param nodeId Id of the NodeTemplate
     * @param paasResourceId Id of the PaasResource
     */
    public void removePaasResourceNodeLink(String nodeId, String paasResourceId);

    /**
     * Get the PaasResources of a NodeTemplate
     *
     * @param nodeId Id of the NodeTemplate
     * @return A list of PaasResources
     */
    public List<PaasResourceVO> findPaasResourcesByNode(String nodeId);

    /**
     * Get the NodeTemplates of a PaasResource
     *
     * @param paasResourceId Id of the PaasResource
     * @return A list of NoteTemplateVO
     */
    public List<NodeTemplateVO> findNodesByPaasResource(String paasResourceId);
}
