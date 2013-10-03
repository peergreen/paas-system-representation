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

import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVO;

import java.util.List;

/**
 * Interface for the Application facade.
 * @author David Richard
 */
public interface ISrApplicationFacade {

    /**
     * Create an application.
     *
     * @param userId Id of the user
     * @param applicationVO the application to create
     * @return the application created
     */
    ApplicationVO createApplication(String userId, ApplicationVO applicationVO);

    /**
     * Update an application.
     *
     * @param applicationVO the new application
     * @return the application updated
     */
    ApplicationVO updateApplication(ApplicationVO applicationVO);

    /**
     * Delete an application.
     *
     * @param appId Id of the application to delete
     */
    void deleteApplication(String appId);

    /**
     * Get an application.
     *
     * @param appId Id of the application
     * @return the application
     */
    ApplicationVO getApplication(String appId);

    /**
     * Get the Applications of a user.
     *
     * @param userId Id of the user
     * @return a list of Applications
     */
    List<ApplicationVO> findApplications(String userId);

    /**
     * Get specific Application(s) of a user.
     *
     * @param userId Id of the user
     * @param appName Name of the application
     * @return a list of Applications
     */
    List<ApplicationVO> findApplications(String userId, String appName);

}
