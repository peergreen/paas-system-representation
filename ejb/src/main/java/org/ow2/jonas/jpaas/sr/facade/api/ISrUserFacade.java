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

import org.ow2.jonas.jpaas.sr.facade.vo.UserVO;

import java.util.List;

/**
 * Interface for the User facade.
 * @author David Richard
 */
public interface ISrUserFacade {

    /**
     * Create a user.
     *
     * @param userVO the user to create
     * @return the user created
     */
    UserVO createUser(UserVO userVO);

    /**
     * Update a user.
     *
     * @param userVO the new user
     * @return the user updated
     */
    UserVO updateUser(UserVO userVO);

    /**
     * Delete a user.
     *
     * @param userId Id of the user to delete
     */
    void deleteUser(String userId);

    /**
     * Get a user.
     *
     * @param userId Id of the user
     * @return the user
     */
    UserVO getUser(String userId);

    /**
     * Get Users with specific name
     *
     * @param name Name of the user
     * @return list of Users with the specified name
     */
    List<UserVO> findUsers(String name);

    /**
     * Get all Users
     *
     * @return list of Users
     */
    List<UserVO> findUsers();

}
