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


    @BeforeClass
    public void init() throws NamingException {
        getBean();
        user1 = new UserVO("user1", "testPassword", "testRole");
        user2 = new UserVO("user2", "testPassword2", "testRole2");
    }


    private void getBean() throws NamingException {
        this.iSrUserFacade = (ISrUserFacade) new InitialContext().lookup("java:global/" +
                "system-representation-1.1.1-SNAPSHOT/SrFacadeBean!org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade");
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
