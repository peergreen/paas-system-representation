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

import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVersionInstanceVO;
import org.ow2.jonas.jpaas.sr.facade.vo.EnvironmentVO;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface for the ApplicationEnvLink facade.
 * @author David Richard
 */
@Remote
public interface ISrApplicationEnvLink {

    /**
     * Add a link between an Environment and an Application
     *
     * @param appId                Id of the Application
     * @param appVersionId                 Id of the ApplicationVersion
     * @param appVersionInstanceId Id of the ApplicationVersionInstance
     * @param envId Id of the Environment
     */
    public void addApplicationDeploymentLink(String appId, String appVersionId, String appVersionInstanceId,
            String envId);

    /**
     * Remove a link between an Environment and an Application
     *
     * @param appId                Id of the Application
     * @param appVersionId                 Id of the ApplicationVersion
     * @param appVersionInstanceId Id of the ApplicationVersionInstance
     */
    public void removeApplicationDeploymentLink(String appId, String appVersionId, String appVersionInstanceId);

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