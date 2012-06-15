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

import org.ow2.jonas.jpaas.sr.facade.vo.IaasComputeVO;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface for the IaasCompute facade.
 * @author David Richard
 */
@Remote
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