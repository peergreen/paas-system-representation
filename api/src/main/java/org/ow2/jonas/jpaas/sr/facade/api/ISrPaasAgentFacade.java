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

import java.util.List;

/**
 * Interface for the PaasAgent facade.
 * @author David Richard
 */
public interface ISrPaasAgentFacade {

    /**
     * Create a PaasAgent.
     *
     * @param paasAgentVO the PaasAgent to create
     * @return the PaasAgent created
     */
    public PaasAgentVO createAgent(PaasAgentVO paasAgentVO);

    /**
     * Delete a PaasAgent.
     *
     * @param paasResourceId Id of the PaasAgent to delete
     */
    public void deleteAgent(String paasResourceId);

    /**
     * Update a PaasAgent.
     *
     * @param paasAgentVO the new PaasAgent
     * @return the PaasAgent updated
     */
    public PaasAgentVO updateAgent(PaasAgentVO paasAgentVO);

    /**
     * Get a PaasAgent.
     *
     * @param paasResourceId Id of the PaasAgent
     * @return the PaasAgent
     */
    public PaasAgentVO getAgent(String paasResourceId);

    /**
     * Get the PaasAgents.
     *
     * @return a list of PaasAgents
     */
    public List<PaasAgentVO> findAgents();
}
