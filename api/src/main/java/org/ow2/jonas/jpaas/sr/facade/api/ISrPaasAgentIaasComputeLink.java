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

import org.ow2.jonas.jpaas.sr.facade.vo.IaasComputeVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasAgentVO;

import java.util.List;

/**
 * Interface for the PaasAgentIaasComputeLink facade.
 * @author David Richard
 */
public interface ISrPaasAgentIaasComputeLink {

    /**
         * Add a link between a PaasAgent and a IaasCompute
         *
         * @param paasAgentId Id of the PaasAgent
         * @param iaasComputeId Id of the IaasCompute
         */
        public void addPaasAgentIaasComputeLink(String paasAgentId, String iaasComputeId);
    
        /**
         * Remove a link between a PaasAgent and a IaasCompute
         *
         * @param paasAgentId Id of the PaasAgent
         * @param iaasComputeId Id of the IaasCompute
         */
        public void removePaasAgentIaasComputeLink(String paasAgentId, String iaasComputeId);
    
        /**
         * Get the PaasAgents of a IaasCompute
         *
         * @param iaasComputeId Id of the IaasCompute
         */
        public List<PaasAgentVO> findPaasAgentsByIaasCompute(String iaasComputeId);
    
        /**
         * Get the IaasCompute of a PaasAgent
         *
         * @param paasAgentId Id of the PaasAgent
         */
        public IaasComputeVO findIaasComputeByPaasAgent(String paasAgentId);
}
