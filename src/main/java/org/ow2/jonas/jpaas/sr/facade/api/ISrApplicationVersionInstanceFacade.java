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

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface for the ApplicationVersionInstance facade.
 * @author David Richard
 */
@Remote
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