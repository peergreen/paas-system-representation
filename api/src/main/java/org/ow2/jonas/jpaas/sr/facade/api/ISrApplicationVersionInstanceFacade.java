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

import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVersionInstanceVO;

import java.util.List;

/**
 * Interface for the ApplicationVersionInstance facade.
 * @author David Richard
 */
public interface ISrApplicationVersionInstanceFacade {

    /**
     * Create an ApplicationVersionInstance.
     *
     * @param appId Id of the Application
     * @param appVersionId Id of the ApplicationVersion
     * @param applicationVersionInstanceVO the ApplicationVersionInstance to create
     * @return the ApplicationVersionInstance created
     */
    public ApplicationVersionInstanceVO createApplicationVersionInstance(String appId, String appVersionId,
            ApplicationVersionInstanceVO applicationVersionInstanceVO);

    /**
     * Update an ApplicationVersionInstance.
     *
     *
     * @param appId Id of the Application
     * @param appVersionId Id of the ApplicationVersion
     * @param applicationVersionInstanceVO the ApplicationVersionInstance to update
     * @return the ApplicationVersionInstance updated
     */
    public ApplicationVersionInstanceVO updateApplicationVersionInstance(String appId, String appVersionId,
            ApplicationVersionInstanceVO applicationVersionInstanceVO);

    /**
     * Delete an ApplicationVersionInstance
     * @param appId Id of the Application
     * @param appVersionId Id of the ApplicationVersion
     * @param appVersionInstanceId Id of the ApplicationVersionInstance to delete
     */
    public void deleteApplicationVersionInstance(String appId, String appVersionId, String appVersionInstanceId);

    /**
     * Get an ApplicationVersionInstance
     * @param appId Id of the Application
     * @param appVersionId Id of the ApplicationVersion
     * @param appVersionInstanceId Id of the ApplicationVersionInstance to retrieve
     * @return the ApplicationVersionInstance
     */
    public ApplicationVersionInstanceVO getApplicationVersionInstance(String appId, String appVersionId,
            String appVersionInstanceId);

    /**
     * Get the ApplicationVersionInstance(s) of an ApplicationVersion.
     *
     * @param appId Id of the Application
     * @param appVersionId Id of the ApplicationVersion
     * @return a list of ApplicationVersionInstances
     */
    public List<ApplicationVersionInstanceVO> findApplicationVersionInstances(String appId, String appVersionId);

    /**
     * Get specific ApplicationVersionInstance(s) of an Application.
     *
     * @param appId Id of the Application
     * @param appVersionId Id of the ApplicationVersion
     * @param instanceName Instance name of the ApplicationVersionInstance
     * @return a list of ApplicationVersionInstances
     */
    public List<ApplicationVersionInstanceVO> findApplicationVersionInstances(String appId, String appVersionId,
            String instanceName);
}
