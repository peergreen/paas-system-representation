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

import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVersionVO;

import javax.ejb.Remote;
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
