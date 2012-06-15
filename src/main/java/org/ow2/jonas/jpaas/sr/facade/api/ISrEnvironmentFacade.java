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

import org.ow2.jonas.jpaas.sr.facade.vo.EnvironmentVO;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface for the Environment facade.
 * @author David Richard
 */
@Remote
public interface ISrEnvironmentFacade {

    /**
     * Create an environment.
     *
     * @param userId Id of the user
     * @param environmentVO the environment to create
     * @return the environment created
     */
    public EnvironmentVO createEnvironment(String userId, EnvironmentVO environmentVO);

    /**
     * Update an environment.
     *
     * @param environmentVO the new environment
     * @return the environment updated
     */
    public EnvironmentVO updateEnvironment(EnvironmentVO environmentVO);

    /**
     * Delete an environment.
     *
     * @param envId Id of the environment to delete
     */
    public void deleteEnvironment(String envId);

    /**
     * Get an environment.
     *
     * @param envId Id of the environment
     * @return the environment
     */
    public EnvironmentVO getEnvironment(String envId);

    /**
     * Get the environment of a user.
     *
     * @param userId Id of the user
     * @return a list of environments
     */
    public List<EnvironmentVO> findEnvironments(String userId);

    /**
     * Get specific Environment(s) of a user.
     *
     * @param userId Id of the user
     * @param envName Name of the environment
     * @return a list of Environments
     */
    public List<EnvironmentVO> findEnvironments(String userId, String envName);
}