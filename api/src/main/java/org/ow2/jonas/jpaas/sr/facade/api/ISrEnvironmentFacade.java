/**
 * JPaaS
 * Copyright 2012 Bull S.A.S.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * $Id:$
 */

package org.ow2.jonas.jpaas.sr.facade.api;

import org.ow2.jonas.jpaas.sr.facade.vo.EnvironmentVO;

import java.util.List;

/**
 * Interface for the Environment facade.
 * @author David Richard
 */

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
