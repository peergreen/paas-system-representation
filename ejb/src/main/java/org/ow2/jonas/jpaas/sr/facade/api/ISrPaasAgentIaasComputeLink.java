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
import org.ow2.jonas.jpaas.sr.facade.vo.PaasAgentVO;

import java.util.List;

/**
 * Interface for the PaasAgentIaasComputeLink facade.
 * @author David Richard
 */
public interface ISrPaasAgentIaasComputeLink {

    /**
         * Add a link between a PaasAgent and a IaasCompute
         *
         * @param paasAgentId Id of the PaasAgent
         * @param iaasComputeId Id of the IaasCompute
         */
        public void addPaasAgentIaasComputeLink(String paasAgentId, String iaasComputeId);
    
        /**
         * Remove a link between a PaasAgent and a IaasCompute
         *
         * @param paasAgentId Id of the PaasAgent
         * @param iaasComputeId Id of the IaasCompute
         */
        public void removePaasAgentIaasComputeLink(String paasAgentId, String iaasComputeId);
    
        /**
         * Get the PaasAgents of a IaasCompute
         *
         * @param iaasComputeId Id of the IaasCompute
         */
        public List<PaasAgentVO> findPaasAgentsByIaasCompute(String iaasComputeId);
    
        /**
         * Get the IaasCompute of a PaasAgent
         *
         * @param paasAgentId Id of the PaasAgent
         */
        public IaasComputeVO findIaasComputeByPaasAgent(String paasAgentId);
}