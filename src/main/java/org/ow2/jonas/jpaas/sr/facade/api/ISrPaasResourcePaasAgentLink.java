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
import org.ow2.jonas.jpaas.sr.facade.vo.PaasResourceVO;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface for the PaasResourceAgentLink facade.
 * @author David Richard
 */
@Remote
public interface ISrPaasResourcePaasAgentLink {

    /**
     * Add a link between a PaasResource and a PaasAgent
     *
     * @param paasResourceId Id of the PaasResource
     * @param paasAgentId Id of the PaasAgent
     */
    public void addPaasResourceAgentLink(String paasResourceId, String paasAgentId);

    /**
     * Remove a link between a PaasResource and a PaasAgent
     *
     * @param paasResourceId Id of the PaasResource
     * @param paasAgentId Id of the PaasAgent
     */
    public void removePaasResourceAgentLink(String paasResourceId, String paasAgentId);

    /**
     * Get the PaasResources of a PaasAgent
     *
     * @param paasAgentId Id of the PaasAgent
     */
    public List<PaasResourceVO> findPaasResourcesByAgent(String paasAgentId);

    /**
     * Get the PaasAgent of a PaasResource
     *
     * @param paasResourceId Id of the PaasResource
     */
    public PaasAgentVO findAgentByPaasResource(String paasResourceId);
}