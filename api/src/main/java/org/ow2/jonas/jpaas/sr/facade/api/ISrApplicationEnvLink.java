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
import org.ow2.jonas.jpaas.sr.facade.vo.EnvironmentVO;

import java.util.List;

/**
 * Interface for the ApplicationEnvLink facade.
 * @author David Richard
 */
public interface ISrApplicationEnvLink {

    /**
     * Add a link between an Environment and an Application
     *
     * @param appId                Id of the Application
     * @param appVersionId                 Id of the ApplicationVersion
     * @param appVersionInstanceId Id of the ApplicationVersionInstance
     * @param envId Id of the Environment
     */
    public void addApplicationEnvLink(String appId, String appVersionId, String appVersionInstanceId,
            String envId);

    /**
     * Remove a link between an Environment and an Application
     *
     * @param appId                Id of the Application
     * @param appVersionId                 Id of the ApplicationVersion
     * @param appVersionInstanceId Id of the ApplicationVersionInstance
     */
    public void removeApplicationEnvLink(String appId, String appVersionId, String appVersionInstanceId);

    /**
     * Get the ApplicationVersionInstances of an Environment
     *
     * @param envId ID of the Environment
     * @return a list of ApplicationVersionInstances
     */
    public List<ApplicationVersionInstanceVO> findApplicationVersionInstancesByEnv(String envId);

    /**
     * Get the Environment of an ApplicationVersionInstance
     *
     * @param appId                Id of the Application
     * @param appVersionId                 Id of the ApplicationVersion
     * @param appVersionInstanceId Id of the ApplicationVersionInstance
     * @return the Environment
     */
    public EnvironmentVO getEnvByApplicationVersionInstance(String appId, String appVersionId,
            String appVersionInstanceId);
}
