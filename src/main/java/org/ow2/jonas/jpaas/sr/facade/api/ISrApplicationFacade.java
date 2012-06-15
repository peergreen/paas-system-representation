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

import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVO;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface for the Application facade.
 * @author David Richard
 */
@Remote
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
