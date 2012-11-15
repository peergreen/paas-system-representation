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

import org.ow2.jonas.jpaas.sr.facade.vo.PaasFrontendVO;
import org.ow2.jonas.jpaas.sr.facade.vo.VirtualHostVO;

import java.util.List;

/**
 * Interface for the PaasFrontend facade.
 * @author David Richard
 */
public interface ISrPaasFrontendFacade {

    /**
     * Create a PaasFrontend.
     *
     * @param paasFrontendVO the PaasFrontend to create
     * @return the PaasFrontend created
     */
    public PaasFrontendVO createFrontend(PaasFrontendVO paasFrontendVO);

    /**
     * Delete a PaasFrontend.
     *
     * @param paasFrontendId Id of the PaasFrontend to delete
     */
    public void deleteFrontend(String paasFrontendId);

    /**
     * Update a PaasFrontend.
     *
     * @param paasFrontendVO the new PaasFrontend
     * @return the PaasFrontend updated
     */
    public PaasFrontendVO updateFrontend(PaasFrontendVO paasFrontendVO);

    /**
     * Get a PaasFrontend.
     *
     * @param paasFrontendId Id of the PaasFrontend
     * @return the PaasFrontend
     */
    public PaasFrontendVO getFrontend(String paasFrontendId);

    /**
     * Get the PaasFrontends.
     *
     * @return a list of PaasFrontends
     */
    public List<PaasFrontendVO> findFrontends();

    /**
     * Add a VirtualHost
     *
     * @param paasFrontendId Id of the PaasFrontend
     * @param name the name of the VirtualHost
     */
    public void addVirtualHost(String paasFrontendId, String name);

    /**
     * Remove a VirtualHost
     *
     * @param name the name of the VirtualHost
     */
    public void removeVirtualHost(String paasFrontendId, String name);

    /**
     * Get the VirtualHosts
     *
     * @param paasFrontendId Id of the PaasFrontend
     * @return a list with the VirtualHosts
     */
    public List<VirtualHostVO> getVirtualHosts(String paasFrontendId);
}
