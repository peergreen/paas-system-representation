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
