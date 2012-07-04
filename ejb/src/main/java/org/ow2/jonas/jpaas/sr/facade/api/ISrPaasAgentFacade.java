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

import org.ow2.jonas.jpaas.sr.facade.vo.PaasAgentVO;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface for the PaasAgent facade.
 * @author David Richard
 */
@Remote
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