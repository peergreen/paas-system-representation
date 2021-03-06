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

package org.ow2.jonas.jpaas.sr.tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;
import org.ops4j.pax.exam.testng.listener.PaxExam;
import org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.UserVO;
import org.ow2.jonas.jpaas.sr.init.SetupTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * UserFacade test case
 * @author David Richard
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class TestUserFacade extends SetupTest {

    /**
     * User Facade
     */
    @Inject
    private ISrUserFacade iSrUserFacade;

    /**
     * User 1 value object
     */
    private UserVO user1;

    /**
     * User 2 value object
     */
    private UserVO user2;

    @Test
    public void initUserFacade() throws NamingException {
        user1 = new UserVO("user1UserFacade", "testPassword", "testRole");
        user2 = new UserVO("user2UserFacade", "testPassword2", "testRole2");
    }


    @Test(dependsOnMethods="initUserFacade")
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
        assertTrue(userList.size() > 0);

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
        int size = userList.size();
        iSrUserFacade.deleteUser(user2.getId());
        userList = iSrUserFacade.findUsers();
        Assert.assertEquals(userList.size(), size -1);
    }

}
