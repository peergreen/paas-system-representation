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

package org.ow2.jonas.jpaas.sr.facade.vo;

import org.ow2.jonas.jpaas.sr.facade.model.User;

/**
 * Define a User
 * @author David Richard
 */
public class UserVO implements java.io.Serializable {

    /**
     * Id of the user.
     */
    private String id;

    /**
     * Name of the user.
     */
    private String name;

    /**
     * Password of the user.
     */
    private String password;

    /**
     * Role of the user.
     */
    private String role;


    public UserVO(String id, String name, String password, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public UserVO(String name, String password, String role) {
        this.id = null;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User[id=").append(getId())
                .append(", name=").append(getName())
                .append(", password=").append(getPassword())
                .append(", role=").append(getRole())
                .append("]");
        return sb.toString();
    }

    /**
     * Change a User Value Object into an Entity object
     * @return a User Entity
     */
    public User createBean() {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }
}
