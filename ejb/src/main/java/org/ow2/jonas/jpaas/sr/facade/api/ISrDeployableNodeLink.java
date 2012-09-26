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

import org.ow2.jonas.jpaas.sr.facade.vo.DeployableVO;
import org.ow2.jonas.jpaas.sr.facade.vo.NodeTemplateVO;

import java.util.List;

/**
 * Interface for the DeployableNodeLink facade.
 * @author David Richard
 */
public interface ISrDeployableNodeLink {

    /**
     * Add a link between a Deployable and a Node
     *
     * @param deployableId Id of the Deployable
     * @param nodeId Id of the Node
     */
    public void addDeployableNodeLink(String deployableId, String nodeId);

    /**
     * Remove a link between a Deployable and a Node
     *
     * @param deployableId Id of the Deployable
     * @param nodeId Id of the Node
     */
    public void removeDeployableNodeLink(String deployableId, String nodeId);

    /**
     * Get the Deployables of a Node
     *
     * @param nodeId ID of the Node
     * @return a list of Deployable
     */
    public List<DeployableVO> findDeployablesByNode(String nodeId);

    /**
     * Get the Node of a Deployable
     *
     * @param deployableId Id of the Deployable
     * @return the NodeTemplate
     */
    public NodeTemplateVO getNodeByDeployable(String deployableId);
}