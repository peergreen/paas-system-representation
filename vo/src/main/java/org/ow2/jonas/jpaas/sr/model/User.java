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

package org.ow2.jonas.jpaas.sr.model;

import org.ow2.jonas.jpaas.sr.facade.vo.UserVO;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.util.List;

/**
 * Define a User
 * @author David Richard
 */
@Entity
@Table(name="paasuser")
public class User implements java.io.Serializable {

    /**
     * key of the user.
     */
    @Id
    @GeneratedValue
    private long key;

    /**
     * Id of the user.
     */
    @Column(unique=true)
    private String id;

    /**
     * Name of the user.
     */
    @Column(unique=true)
    private String name;

    /**
     * Password of the user.
     */
    private String password;

    /**
     * Role of the user.
     */
    private String role;

    /**
     * Applications of the user
     */
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Application> applicationList;

    /**
     * Environment of the user
     */
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Environment> environmentList;

    /**
     * ApplicationVersionInstances of the user
     */
    @ManyToMany(mappedBy="userList")
    private List<ApplicationVersionInstance> applicationVersionInstanceList;


    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
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

    public List<Application> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<Application> applicationVOList) {
        this.applicationList = applicationVOList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User[key=").append(getKey())
                .append(", id=").append(getId())
                .append(", name=").append(getName())
                .append(", password=").append(getPassword())
                .append(", role=").append(getRole())
                .append("]");
        return sb.toString();
    }

    public UserVO createUserVO() {
        return new UserVO(getId(),name,password,role);
    }

    public void mergeUserVO(UserVO userVO) {
        this.name = userVO.getName();
        this.password = userVO.getPassword();
        this.role = userVO.getRole();
    }

    public List<ApplicationVersionInstance> getApplicationVersionInstanceList() {
        return applicationVersionInstanceList;
    }

    public void setApplicationVersionInstanceList(List<ApplicationVersionInstance> applicationVersionInstanceList) {
        this.applicationVersionInstanceList = applicationVersionInstanceList;
    }

    public List<Environment> getEnvironmentList() {
        return environmentList;
    }

    public void setEnvironmentList(List<Environment> environmentList) {
        this.environmentList = environmentList;
    }

}
