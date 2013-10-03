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
import org.ow2.jonas.jpaas.sr.facade.vo.PaasResourceVO;

import java.util.List;

/**
 * Interface for the PaasResourceIaasComputeLink facade.
 * @author David Richard
 */
public interface ISrPaasResourceIaasComputeLink {

    /**
         * Add a link between a PaasResource and a IaasCompute
         *
         * @param paasResourceId Id of the PaasResource
         * @param iaasComputeId Id of the IaasCompute
         */
        public void addPaasResourceIaasComputeLink(String paasResourceId, String iaasComputeId);
    
        /**
         * Remove a link between a PaasResource and a IaasCompute
         *
         * @param paasResourceId Id of the PaasResource
         * @param iaasComputeId Id of the IaasCompute
         */
        public void removePaasResourceIaasComputeLink(String paasResourceId, String iaasComputeId);
    
        /**
         * Get the PaasResources of a IaasCompute
         *
         * @param iaasComputeId Id of the IaasCompute
         */
        public List<PaasResourceVO> findPaasResourcesByIaasCompute(String iaasComputeId);
    
        /**
         * Get the IaasCompute of a PaasResource
         *
         * @param paasResourceId Id of the PaasResource
         */
        public IaasComputeVO findIaasComputeByPaasResource(String paasResourceId);
}
