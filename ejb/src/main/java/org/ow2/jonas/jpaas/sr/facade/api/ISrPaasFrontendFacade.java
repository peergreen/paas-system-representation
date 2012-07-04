/**
 * JPaaS
 * Copyright (C) 2012 Bull S.A.S.
 * Contact: jasmine@ow2.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
 * USA
 *
 * --------------------------------------------------------------------------
 * $Id$
 * --------------------------------------------------------------------------
 */

package org.ow2.jonas.jpaas.sr.facade.api;

import org.ow2.jonas.jpaas.sr.facade.vo.PaasFrontendVO;
import org.ow2.jonas.jpaas.sr.facade.vo.VirtualHostVO;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface for the PaasFrontend facade.
 * @author David Richard
 */
@Remote
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