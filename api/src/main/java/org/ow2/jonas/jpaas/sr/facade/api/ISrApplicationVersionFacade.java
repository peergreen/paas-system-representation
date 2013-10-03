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

import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVersionVO;

import java.util.List;

/**
 * Interface for the ApplicationVersion facade.
 * @author David Richard
 */
public interface ISrApplicationVersionFacade {

    /**
     * Create an ApplicationVersion.
     *
     * @param appId Id of the Application
     * @param applicationVersionVO the ApplicationVersion to create
     * @return the ApplicationVersion created
     */
    ApplicationVersionVO createApplicationVersion(String appId, ApplicationVersionVO applicationVersionVO);

    /**
     * Update an ApplicationVersion.
     *
     *
     * @param appId Id of the Application
     * @param applicationVersionVO the ApplicationVersion to update
     * @return the ApplicationVersion updated
     */
    ApplicationVersionVO updateApplicationVersion(String appId, ApplicationVersionVO applicationVersionVO);

    /**
     * Delete an ApplicationVersion
     *
     * @param appId Id of the Application
     * @param appVersionId Id of the ApplicationVersion to delete
     */
    public void deleteApplicationVersion(String appId, String appVersionId);

    /**
     * Get an ApplicationVersion
     *
     * @param appId Id of the Application
     * @param appVersionId Id of the ApplicationVersion to retrieve
     * @return the ApplicationVersion
     */
    public ApplicationVersionVO getApplicationVersion(String appId, String appVersionId);

    /**
     * Get the ApplicationVersion(s) of an Application.
     *
     * @param appId Id of the application
     * @return a list of ApplicationVersions
     */
    public List<ApplicationVersionVO> findApplicationVersions(String appId);

    /**
     * Get specific ApplicationVersion(s) of an Application.
     *
     * @param appId Id of the application
     * @param versionLabel Label of the ApplicationVersion
     * @return a list of Applications
     */
    public List<ApplicationVersionVO> findApplicationVersions(String appId, String versionLabel);
}
