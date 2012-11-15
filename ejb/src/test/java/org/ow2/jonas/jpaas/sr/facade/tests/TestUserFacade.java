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

package org.ow2.jonas.jpaas.sr.facade.tests;

import org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.UserVO;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * UserFacade test case
 * @author David Richard
 */
public class TestUserFacade {

    /**
     * User Facade
     */
    private ISrUserFacade iSrUserFacade = null;

    /**
     * User 1 value object
     */
    private UserVO user1;

    /**
     * User 2 value object
     */
    private UserVO user2;

    /**
     * Name of the module for the lookup
     */
    private final String moduleName = System.getProperty("module.name");


    @BeforeClass
    public void init() throws NamingException {
        getBean();
        user1 = new UserVO("user1", "testPassword", "testRole");
        user2 = new UserVO("user2", "testPassword2", "testRole2");
    }


    private void getBean() throws NamingException {
        this.iSrUserFacade = (ISrUserFacade) new InitialContext().lookup("java:global/" + moduleName +
                "/SrFacadeBean!org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade");
    }

    @Test
    public void testCreateUser() {
        //Create and test a user
        UserVO tmpUser1 = iSrUserFacade.createUser(user1);
        Assert.assertNotEquals(tmpUser1.getId(), null);
        user1 = tmpUser1;

        //Create and test a second user
        UserVO tmpUser2 = iSrUserFacade.createUser(user2);
        Assert.assertNotEquals(tmpUser2.getId(), null);
        user2 = tmpUser2;
    }

    @Test(dependsOnMethods = "testCreateUser")
    public void testGetUser() {
        //Get the first user
        UserVO tmpUser1 = iSrUserFacade.getUser(user1.getId());
        Assert.assertEquals(user1.getId(), tmpUser1.getId());
        Assert.assertEquals(user1.getName(), tmpUser1.getName());
        Assert.assertEquals(user1.getPassword(), tmpUser1.getPassword());
        Assert.assertEquals(user1.getRole(), tmpUser1.getRole());
    }

    @Test(dependsOnMethods = "testCreateUser")
    public void testUpdateUser() {
        //Update the first user
        user1.setName("updateUser1");
        user1.setPassword("updatePassword");
        user1.setRole("updateRole");
        UserVO tmpUser1 = iSrUserFacade.updateUser(user1);
        Assert.assertEquals(user1.getId(), tmpUser1.getId());
        Assert.assertEquals(user1.getName(), tmpUser1.getName());
        Assert.assertEquals(user1.getPassword(), tmpUser1.getPassword());
        Assert.assertEquals(user1.getRole(), tmpUser1.getRole());
    }

    @Test(dependsOnMethods = "testUpdateUser")
    public void testFindUser() {
        //Test find all users
        List<UserVO> userList = iSrUserFacade.findUsers();
        Assert.assertEquals(userList.size(), 2);

        //Test find user by name
        userList = iSrUserFacade.findUsers(user2.getName());
        Assert.assertEquals(userList.size(), 1);
        Assert.assertEquals(userList.get(0).getName(), user2.getName());
    }

    @Test(dependsOnMethods = "testFindUser")
    public void testDeleteUser() {
        //Delete users
        iSrUserFacade.deleteUser(user1.getId());
        List<UserVO> userList = iSrUserFacade.findUsers();
        Assert.assertEquals(userList.size(), 1);
        iSrUserFacade.deleteUser(user2.getId());
        userList = iSrUserFacade.findUsers();
        Assert.assertEquals(userList.isEmpty(), true);
    }

}
