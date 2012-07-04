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
import org.ow2.jonas.jpaas.sr.facade.vo.PaasResourceVO;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface for the PaasResourceIaasComputeLink facade.
 * @author David Richard
 */
@Remote
public interface ISrPaasResourceIaasComputeLink {

    /**
         * Add a link between a PaasResource and a IaasCompute
         *
         * @param paasResourceId Id of the PaasResource
         * @param iaasComputeId Id of the IaasCompute
         */
        public void addPaasResourceIaasComputeLink(String paasResourceId, String iaasComputeId);
    
        /**
         * Remove a link between a PaasResource and a IaasCompute
         *
         * @param paasResourceId Id of the PaasResource
         * @param iaasComputeId Id of the IaasCompute
         */
        public void removePaasResourceIaasComputeLink(String paasResourceId, String iaasComputeId);
    
        /**
         * Get the PaasResources of a IaasCompute
         *
         * @param iaasComputeId Id of the IaasCompute
         */
        public List<PaasResourceVO> findPaasResourcesByIaasCompute(String iaasComputeId);
    
        /**
         * Get the IaasCompute of a PaasResource
         *
         * @param paasResourceId Id of the PaasResource
         */
        public IaasComputeVO findIaasComputeByPaasResource(String paasResourceId);
}