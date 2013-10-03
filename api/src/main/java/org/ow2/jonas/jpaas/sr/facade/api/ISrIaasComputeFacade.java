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

import java.util.List;

/**
 * Interface for the IaasCompute facade.
 * @author David Richard
 */
public interface ISrIaasComputeFacade {

    /**
     * Create an IaasCompute.
     *
     * @param iaasComputeVO the user to create
     * @return the user created
     */
    public IaasComputeVO createIaasCompute(IaasComputeVO iaasComputeVO);

    /**
     * Delete an IaasCompute.
     *
     * @param iaasComputeId Id of the IaasCompute to delete
     */

    public void deleteIaasCompute(String iaasComputeId);

    /**
     * Update a IaasCompute.
     *
     * @param iaasComputeVO the new IaasCompute
     * @return the IaasCompute updated
     */
    public IaasComputeVO updateIaasCompute(IaasComputeVO iaasComputeVO);

    /**
     * Get a IaasCompute.
     *
     * @param iaasComputeId Id of the IaasCompute
     * @return the IaasCompute
     */
    public IaasComputeVO getIaasCompute(String iaasComputeId);

    /**
     * Get the IaasComputes.
     *
     * @return a list of IaasComputes
     */
    public List<IaasComputeVO> findIaasComputes();
}
