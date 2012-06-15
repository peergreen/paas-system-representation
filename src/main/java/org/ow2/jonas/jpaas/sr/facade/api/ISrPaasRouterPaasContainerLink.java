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

import org.ow2.jonas.jpaas.sr.facade.vo.PaasContainerVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasRouterVO;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface for the PaasRouterPaasContainerLink facade.
 * @author David Richard
 */
@Remote
public interface ISrPaasRouterPaasContainerLink {

    /**
     * Add a link between a PaasRouter and a PaasContainer
     *
     * @param paasRouterId Id of the PaasRouter
     * @param paasContainerId Id of the PaasContainer
     */
    public void addRouterContainerLink(String paasRouterId, String paasContainerId);

    /**
     * Remove a link between a PaasRouter and a PaasContainer
     *
     * @param paasRouterId Id of the PaasRouter
     * @param paasContainerId Id of the PaasContainer
     */
    public void removeRouterContainerLink(String paasRouterId, String paasContainerId);

    /**
     * Get the PaasRouters of a PaasContainer
     *
     * @param paasContainerId Id of the PaasContainer
     */
    public List<PaasRouterVO> findRoutersByContainer(String paasContainerId);

    /**
     * Get the PaasContainers of a PaasRouter
     *
     * @param paasRouterId Id of the PaasRouter
     */
    public List<PaasContainerVO> findContainersByRouter(String paasRouterId);

}