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

import java.util.List;

import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;

/**
 * Interface for the PaasJonasContainer facade.
 * @author David Richard
 */
public interface ISrPaasJonasContainerFacade {

    /**
     * Create a PaasJonasContainer.
     *
     * @param jonasVO the PaasJonasContainer to create
     * @return the PaasJonasContainer created
     */
    public JonasVO createJonasContainer(JonasVO jonasVO);

    /**
     * Delete a PaasJonasContainer.
     *
     * @param paasResourceId Id of the PaasJonasContainer to delete
     */
    public void deleteJonasContainer(String paasResourceId);

    /**
     * Update a PaasJonasContainer.
     *
     * @param jonasVO the new PaasJonasContainer
     * @return the PaasJonasContainer updated
     */
    public JonasVO updateJonasContainer(JonasVO jonasVO);

    /**
     * Get a PaasJonasContainer.
     *
     * @param paasResourceId Id of the PaasJonasContainer
     * @return the PaasJonasContainer
     */
    public JonasVO getJonasContainer(String paasResourceId);

    /**
     * Get the PaasJonasContainers.
     *
     * @return a list of PaasJonasContainers
     */
    public List<JonasVO> findJonasContainers();

    /**
     * Get the PaasJonasContainer
     * @return a PaasJonasContainer
     */
    public JonasVO findJonasContainer(String containerName);


}
