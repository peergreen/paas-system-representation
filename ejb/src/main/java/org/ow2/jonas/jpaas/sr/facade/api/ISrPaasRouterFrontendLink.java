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
import org.ow2.jonas.jpaas.sr.facade.vo.PaasRouterVO;

import java.util.List;

/**
 * Interface for the PaasFrontendLink facade.
 * @author David Richard
 */
public interface ISrPaasRouterFrontendLink {

    /**
     * Add a link between a PaasRouter and a PaasFrontend
     *
     * @param paasRouterId Id of the PaasRouter
     * @param paasFrontendId Id of the PaasFrontend
     */
    public void addPaasRouterFrontendLink(String paasRouterId, String paasFrontendId);

    /**
     * Remove a link between a PaasRouter and a PaasFrontend
     *
     * @param paasRouterId Id of the PaasRouter
     * @param paasFrontendId Id of the PaasFrontend
     */
    public void removePaasRouterFrontendLink(String paasRouterId, String paasFrontendId);

    /**
     * Get the PaasRouters of a PaasFrontend
     *
     * @param paasFrontendId Id of the PaasFrontend
     * @return a list of PaasRouter
     */
    public List<PaasRouterVO> findPaasRoutersByFrontend(String paasFrontendId);

    /**
     * Get the PaasFrontend of a PaasRouter
     *
     * @param paasRouterId Id of the PaasRouter
     * @return the PaasFrontend
     */
    public PaasFrontendVO findFrontendByPaasRouter(String paasRouterId);
}