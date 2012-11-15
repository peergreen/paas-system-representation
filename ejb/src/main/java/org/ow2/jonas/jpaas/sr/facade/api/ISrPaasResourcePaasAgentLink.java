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

import org.ow2.jonas.jpaas.sr.facade.vo.PaasAgentVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasResourceVO;

import java.util.List;

/**
 * Interface for the PaasResourceAgentLink facade.
 * @author David Richard
 */
public interface ISrPaasResourcePaasAgentLink {

    /**
     * Add a link between a PaasResource and a PaasAgent
     *
     * @param paasResourceId Id of the PaasResource
     * @param paasAgentId Id of the PaasAgent
     */
    public void addPaasResourceAgentLink(String paasResourceId, String paasAgentId);

    /**
     * Remove a link between a PaasResource and a PaasAgent
     *
     * @param paasResourceId Id of the PaasResource
     * @param paasAgentId Id of the PaasAgent
     */
    public void removePaasResourceAgentLink(String paasResourceId, String paasAgentId);

    /**
     * Get the PaasResources of a PaasAgent
     *
     * @param paasAgentId Id of the PaasAgent
     */
    public List<PaasResourceVO> findPaasResourcesByAgent(String paasAgentId);

    /**
     * Get the PaasAgent of a PaasResource
     *
     * @param paasResourceId Id of the PaasResource
     */
    public PaasAgentVO findAgentByPaasResource(String paasResourceId);
}
