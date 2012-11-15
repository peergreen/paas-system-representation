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

package org.ow2.jonas.jpaas.sr.facade.vo;

import org.ow2.jonas.jpaas.sr.model.User;

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
