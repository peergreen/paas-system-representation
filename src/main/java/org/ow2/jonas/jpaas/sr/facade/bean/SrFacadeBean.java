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

package org.ow2.jonas.jpaas.sr.facade.bean;

import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationEnvLink;
import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationVersionFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrApplicationVersionInstanceFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrEnvironmentFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrEnvironmentPaasResourceLink;
import org.ow2.jonas.jpaas.sr.facade.api.ISrIaasComputeFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasAgentFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasApacheJkRouterFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasContainerPaasDatabaseLink;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasDatabaseFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasFrontendFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasJonasContainerFacade;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasResourcePaasAgentLink;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasResourceIaasComputeLink;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasRouterFrontendLink;
import org.ow2.jonas.jpaas.sr.facade.api.ISrPaasRouterPaasContainerLink;
import org.ow2.jonas.jpaas.sr.facade.api.ISrUserFacade;
import org.ow2.jonas.jpaas.sr.facade.vo.ApacheJkVO;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVO;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVersionInstanceVO;
import org.ow2.jonas.jpaas.sr.facade.vo.ApplicationVersionVO;
import org.ow2.jonas.jpaas.sr.facade.vo.ConnectorVO;
import org.ow2.jonas.jpaas.sr.facade.vo.DatasourceVO;
import org.ow2.jonas.jpaas.sr.facade.vo.EnvironmentVO;
import org.ow2.jonas.jpaas.sr.facade.vo.IaasComputeVO;
import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;
import org.ow2.jonas.jpaas.sr.facade.vo.LoadBalancerVO;
import org.ow2.jonas.jpaas.sr.facade.vo.NodeTemplateVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasAgentVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasContainerVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasDatabaseVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasFrontendVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasResourceVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasRouterVO;
import org.ow2.jonas.jpaas.sr.facade.vo.UserVO;
import org.ow2.jonas.jpaas.sr.facade.vo.VirtualHostVO;
import org.ow2.jonas.jpaas.sr.facade.vo.WorkerVO;
import org.ow2.jonas.jpaas.sr.model.ApacheJk;
import org.ow2.jonas.jpaas.sr.model.Application;
import org.ow2.jonas.jpaas.sr.model.ApplicationVersion;
import org.ow2.jonas.jpaas.sr.model.ApplicationVersionInstance;
import org.ow2.jonas.jpaas.sr.model.Connector;
import org.ow2.jonas.jpaas.sr.model.Datasource;
import org.ow2.jonas.jpaas.sr.model.Deployable;
import org.ow2.jonas.jpaas.sr.model.Entity;
import org.ow2.jonas.jpaas.sr.model.Environment;
import org.ow2.jonas.jpaas.sr.model.IaasCompute;
import org.ow2.jonas.jpaas.sr.model.Jonas;
import org.ow2.jonas.jpaas.sr.model.LoadBalancer;
import org.ow2.jonas.jpaas.sr.model.NodeTemplate;
import org.ow2.jonas.jpaas.sr.model.PaasAgent;
import org.ow2.jonas.jpaas.sr.model.PaasArtefact;
import org.ow2.jonas.jpaas.sr.model.PaasContainer;
import org.ow2.jonas.jpaas.sr.model.PaasDatabase;
import org.ow2.jonas.jpaas.sr.model.PaasEntity;
import org.ow2.jonas.jpaas.sr.model.PaasFrontend;
import org.ow2.jonas.jpaas.sr.model.PaasResource;
import org.ow2.jonas.jpaas.sr.model.PaasRouter;
import org.ow2.jonas.jpaas.sr.model.RelationshipTemplate;
import org.ow2.jonas.jpaas.sr.model.TopologyTemplate;
import org.ow2.jonas.jpaas.sr.model.User;
import org.ow2.jonas.jpaas.sr.model.VirtualHost;
import org.ow2.jonas.jpaas.sr.model.Worker;
import org.ow2.jonas.jpaas.sr.sequence.Sequence;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * SR session facade bean.
 * @author David Richard
 */
@Stateless
public class SrFacadeBean implements ISrUserFacade, ISrApplicationFacade, ISrApplicationVersionFacade,
        ISrApplicationVersionInstanceFacade, ISrEnvironmentFacade, ISrApplicationEnvLink, ISrPaasJonasContainerFacade,
        ISrEnvironmentPaasResourceLink, ISrPaasApacheJkRouterFacade, ISrPaasDatabaseFacade, ISrPaasAgentFacade,
        ISrIaasComputeFacade, ISrPaasFrontendFacade, ISrPaasRouterPaasContainerLink, ISrPaasContainerPaasDatabaseLink,
        ISrPaasResourcePaasAgentLink, ISrPaasResourceIaasComputeLink, ISrPaasRouterFrontendLink {

    @PersistenceContext
    EntityManager entityManager;

    private final String userSequence = "user";

    private final String applicationSequence = "application";

    private final String applicationVersionSequence = "applicationVersion";

    private final String applicationVersionInstanceSequence = "applicationVersionInstance";

    private final String environmentSequence = "environment";

    private final String deployableSequence = "deployable";

    private final String artefactSequence = "artefact";

    private final String nodeSequence = "node";

    private final String relationshipSequence = "relationship";

    private final String entitySequence = "entity";

    private final String frontendSequence = "frontend";

    private String getNextSequence(String type) {
        Sequence sequence = entityManager.find(Sequence.class, type);
        if (sequence == null) {
            sequence = new Sequence();
            sequence.setType(type);
            sequence.setNextSequence(1L);
            entityManager.persist(sequence);
        }
        long nextUserSequence = sequence.getNextSequence();
        sequence.setNextSequence(nextUserSequence + 1);
        entityManager.merge(sequence);
        return String.valueOf(nextUserSequence);
    }

    /**
     * Create a user.
     *
     * @param userVO the user to create
     * @return the user created
     */
    @Override
    public UserVO createUser(UserVO userVO) {
        User user = userVO.createBean();
        user.setId(getNextSequence(userSequence));
        entityManager.persist(user);
        return user.createUserVO();
    }

    /**
     * Update a user.
     *
     * @param userVO the new user
     * @return the user updated
     */
    @Override
    public UserVO updateUser(UserVO userVO) {
        User user = getUserBean(userVO.getId());
        user.mergeUserVO(userVO);
        user = entityManager.merge(user);
        return user.createUserVO();
    }

    /**
     * Delete a user.
     *
     * @param userId Id of the user to delete
     */
    @Override
    public void deleteUser(String userId) {
        entityManager.remove(getUserBean(userId));
    }


    /**
     * Get a user.
     *
     * @param userId Id of the user
     * @return the user
     */
    private User getUserBean(String userId) {
        Query q = entityManager.createQuery("SELECT user FROM User user WHERE user.id=:userId");
        q.setParameter("userId",userId);
        return (User) q.getSingleResult();
    }

    /**
     * Get a user.
     *
     * @param userId Id of the user
     * @return the user
     */
    @Override
    public UserVO getUser(String userId) {
        User user = getUserBean(userId);
        return user.createUserVO();
    }

    /**
     * Get Users with specific name
     *
     * @param name Name of the user
     * @return list of Users with the specified name
     */
    @Override
    public List<UserVO> findUsers(String name) {
        Query q = entityManager.createQuery("SELECT user FROM User user WHERE user.name=:name");
        q.setParameter("name",name);
        List<User> userList = (List<User>) q.getResultList();
        List<UserVO> resultList = new LinkedList<UserVO>();
        for (User tmp : userList) {
            resultList.add(tmp.createUserVO());
        }
        return resultList;
    }

    /**
     * Get all Users
     *
     * @return list of Users
     */
    @Override
    public List<UserVO> findUsers() {
        Query q = entityManager.createQuery("SELECT user FROM User user");
        List<User> userList = (List<User>) q.getResultList();
        List<UserVO> resultList = new LinkedList<UserVO>();
        for (User tmp : userList) {
            resultList.add(tmp.createUserVO());
        }
        return resultList;
    }

    /**
     * Create an application.
     *
     * @param userId        Id of the user
     * @param applicationVO the application to create
     * @return the application created
     */
    @Override
    public ApplicationVO createApplication(String userId, ApplicationVO applicationVO) {
        Application application = applicationVO.createBean();
        User user = getUserBean(userId);
        application.setUser(user);
        application.setId(getNextSequence(applicationSequence));
        entityManager.persist(application);
        return application.createApplicationVO();
    }

    /**
     * Update an application.
     *
     * @param applicationVO the new application
     * @return the application updated
     */
    @Override
    public ApplicationVO updateApplication(ApplicationVO applicationVO) {
        Application application = getApplicationBean(applicationVO.getId());
        application.mergeApplicationVO(applicationVO);
        application = entityManager.merge(application);
        return application.createApplicationVO();
    }

    /**
     * Delete an application.
     *
     * @param appId Id of the application to delete
     */
    @Override
    public void deleteApplication(String appId) {
        entityManager.remove(getApplicationBean(appId));
    }


    /**
     * Get an application.
     *
     * @param appId Id of the application
     * @return the application entity
     */
    private Application getApplicationBean(String appId) {
        Query q = entityManager.createQuery("SELECT Application FROM Application application WHERE" +
                " application.id=:appId");
        q.setParameter("appId",appId);
        return (Application) q.getSingleResult();
    }


    /**
     * Get an application.
     *
     * @param appId Id of the application
     * @return the application
     */
    @Override
    public ApplicationVO getApplication(String appId) {
        Application application = getApplicationBean(appId);
        return application.createApplicationVO();
    }

    /**
     * Get the Applications of a user.
     *
     * @param userId Id of the user
     * @return a list of Applications
     */
    @Override
    public List<ApplicationVO> findApplications(String userId) {
        User user = getUserBean(userId);
        List<Application> applicationList = user.getApplicationList();
        List<ApplicationVO> resultList = new LinkedList<ApplicationVO>();
        for (Application tmp : applicationList) {
            resultList.add(tmp.createApplicationVO());
        }
        return resultList;
    }

    /**
     * Get specific Application(s) of a user.
     *
     * @param userId  Id of the user
     * @param appName Name of the application
     * @return a list of Applications
     */
    @Override
    public List<ApplicationVO> findApplications(String userId, String appName) {
        List<ApplicationVO> list = findApplications(userId);
        for (ListIterator<ApplicationVO> iterator = list.listIterator(); iterator.hasNext();) {
            ApplicationVO tmp = iterator.next();
            if (!tmp.getName().equals(appName)) {
                iterator.remove();
            }
        }
        return list;
    }

    /**
     * Get an deployable.
     *
     * @param deployableId Id of the deployable
     * @return the deployable entity
     */
    private Deployable getDeployableBean(String deployableId) {
        Query q = entityManager.createQuery("SELECT Deployable FROM Deployable deployable WHERE" +
                " deployable.id=:deployableId");
        q.setParameter("deployableId",deployableId);
        return (Deployable) q.getSingleResult();
    }

    /**
     * Create an ApplicationVersion.
     *
     * @param appId                Id of the Application
     * @param applicationVersionVO the ApplicationVersion to create
     * @return the ApplicationVersion created
     */
    @Override
    public ApplicationVersionVO createApplicationVersion(String appId, ApplicationVersionVO applicationVersionVO) {
        ApplicationVersion applicationVersion = applicationVersionVO.createBean();
        Application application = getApplicationBean(appId);
        applicationVersion.setApplication(application);
        applicationVersion.setId(getNextSequence(applicationVersionSequence));
        if (applicationVersion.getDeployableList() != null) {
            for (Deployable tmp : applicationVersion.getDeployableList()) {
                tmp.setId(getNextSequence(deployableSequence));
                tmp.setApplicationVersion(applicationVersion);
            }
        }
        entityManager.persist(applicationVersion);
        return applicationVersion.createApplicationVersionVO();
    }


    /**
     * Update an ApplicationVersion.
     *
     *
     * @param appId                Id of the Application
     * @param applicationVersionVO the ApplicationVersion to update
     * @return the ApplicationVersion updated
     */
    @Override
    public ApplicationVersionVO updateApplicationVersion(String appId, ApplicationVersionVO applicationVersionVO) {
        ApplicationVersion applicationVersion = getApplicationVersionBean(appId, applicationVersionVO.getVersionId());
        applicationVersion.mergeApplicationVersionVO(applicationVersionVO);
        if (applicationVersion.getDeployableList() != null) {
            for (Deployable tmp : applicationVersion.getDeployableList()) {
                if (tmp.getId() == null) {
                    tmp.setId(getNextSequence(deployableSequence));
                }
                tmp.setApplicationVersion(applicationVersion);
            }
        }
        applicationVersion = entityManager.merge(applicationVersion);
        return applicationVersion.createApplicationVersionVO();
    }

    /**
     * Delete an ApplicationVersion
     *
     * @param appId                Id of the Application
     * @param appVersionId Id of the ApplicationVersion to delete
     */
    @Override
    public void deleteApplicationVersion(String appId, String appVersionId) {
        entityManager.remove(getApplicationVersionBean(appId, appVersionId));
    }


    /**
     * Get an ApplicationVersion.
     *
     * @param appId                Id of the Application
     * @param appVersionId Id of the ApplicationVersion
     * @return the ApplicationVersion entity
     */
    private ApplicationVersion getApplicationVersionBean(String appId, String appVersionId) {
        Query q = entityManager.createQuery("SELECT ApplicationVersion FROM ApplicationVersion applicationVersion " +
                "WHERE applicationVersion.id=:appVersionId and applicationVersion.application.id=:appId");
        q.setParameter("appVersionId",appVersionId);
        q.setParameter("appId",appId);
        return (ApplicationVersion) q.getSingleResult();
    }

    /**
     * Get an ApplicationVersion
     *
     * @param appId                Id of the Application
     * @param appVersionId Id of the ApplicationVersion to retrieve
     * @return the ApplicationVersion
     */
    @Override
    public ApplicationVersionVO getApplicationVersion(String appId, String appVersionId) {
        ApplicationVersion applicationVersion = getApplicationVersionBean(appId, appVersionId);
        return applicationVersion.createApplicationVersionVO();
    }

    /**
     * Get the ApplicationVersion(s) of an Application.
     *
     * @param appId Id of the application
     * @return a list of ApplicationVersions
     */
    @Override
    public List<ApplicationVersionVO> findApplicationVersions(String appId) {
        Application application = getApplicationBean(appId);
        List<ApplicationVersion> applicationVersionList = application.getApplicationVersionList();
        List<ApplicationVersionVO> resultList = new LinkedList<ApplicationVersionVO>();
        for (ApplicationVersion tmp : applicationVersionList) {
            resultList.add(tmp.createApplicationVersionVO());
        }
        return resultList;
    }

    /**
     * Get specific ApplicationVersion(s) of an Application.
     *
     * @param appId        Id of the application
     * @param versionLabel Label of the ApplicationVersion
     * @return a list of Applications
     */
    @Override
    public List<ApplicationVersionVO> findApplicationVersions(String appId, String versionLabel) {
        List<ApplicationVersionVO> list = findApplicationVersions(appId);
        for (ListIterator<ApplicationVersionVO> iterator = list.listIterator(); iterator.hasNext();) {
            ApplicationVersionVO tmp = iterator.next();
            if (!tmp.getLabel().equals(versionLabel)) {
                iterator.remove();
            }
        }
        return list;
    }

    /**
     * Create an ApplicationVersionInstance.
     *
     * @param appId                Id of the Application
     * @param appVersionId                 Id of the ApplicationVersion
     * @param applicationVersionInstanceVO the ApplicationVersionInstance to create
     * @return the ApplicationVersionInstance created
     */
    @Override
    public ApplicationVersionInstanceVO createApplicationVersionInstance(String appId, String appVersionId,
            ApplicationVersionInstanceVO applicationVersionInstanceVO) {
        ApplicationVersionInstance applicationVersionInstance = applicationVersionInstanceVO.createBean();
        ApplicationVersion applicationVersion = getApplicationVersionBean(appId, appVersionId);
        applicationVersionInstance.setApplicationVersion(applicationVersion);
        applicationVersionInstance.setId(getNextSequence(applicationVersionInstanceSequence));
        if (applicationVersionInstance.getDeployableList() != null) {
            for (Deployable tmp : applicationVersionInstance.getDeployableList()) {
                tmp.setId(getNextSequence(deployableSequence));
                tmp.setApplicationVersion(applicationVersion);
                tmp.setApplicationVersionInstance(applicationVersionInstance);
            }
        }
        if (applicationVersionInstance.getPaasArtefactList() != null) {
            for (PaasArtefact tmp : applicationVersionInstance.getPaasArtefactList()) {
                tmp.setId(getNextSequence(artefactSequence));
                tmp.setApplicationVersionInstance(applicationVersionInstance);
            }
        }
        entityManager.persist(applicationVersionInstance);
        return applicationVersionInstance.createApplicationVersionInstanceVO();
    }

    /**
     * Update an ApplicationVersionInstance.
     *
     *
     * @param appId                Id of the Application
     * @param appVersionId                 Id of the ApplicationVersion
     * @param applicationVersionInstanceVO the ApplicationVersionInstance to update
     * @return the ApplicationVersionInstance updated
     */
    @Override
    public ApplicationVersionInstanceVO updateApplicationVersionInstance(String appId, String appVersionId,
            ApplicationVersionInstanceVO applicationVersionInstanceVO) {
        ApplicationVersionInstance applicationVersionInstance = getApplicationVersionInstanceBean(appId, appVersionId,
                applicationVersionInstanceVO.getId());
        applicationVersionInstance.mergeApplicationVersionInstanceVO(applicationVersionInstanceVO);
        if (applicationVersionInstance.getDeployableList() != null) {
            for (Deployable tmp : applicationVersionInstance.getDeployableList()) {
                if (tmp.getId() == null) {
                    tmp.setId(getNextSequence(deployableSequence));
                }
                tmp.setApplicationVersion(applicationVersionInstance.getApplicationVersion());
                tmp.setApplicationVersionInstance(applicationVersionInstance);
            }
        }

        if (applicationVersionInstance.getPaasArtefactList() != null) {
            for (PaasArtefact tmp : applicationVersionInstance.getPaasArtefactList()) {
                if (tmp.getId() == null) {
                    tmp.setId(getNextSequence(artefactSequence));
                }
                tmp.setApplicationVersionInstance(applicationVersionInstance);
            }
        }
        applicationVersionInstance = entityManager.merge(applicationVersionInstance);
        return applicationVersionInstance.createApplicationVersionInstanceVO();
    }

    /**
     * Delete an ApplicationVersionInstance
     *
     * @param appId                Id of the Application
     * @param appVersionId                 Id of the ApplicationVersion
     * @param appVersionInstanceId Id of the ApplicationVersionInstance to delete
     */
    @Override
    public void deleteApplicationVersionInstance(String appId, String appVersionId, String appVersionInstanceId) {
        entityManager.remove(getApplicationVersionInstanceBean(appId, appVersionId, appVersionInstanceId));
    }

    /**
     * Get an ApplicationVersionInstance.
     *
     * @param appId                Id of the Application
     * @param appVersionId                 Id of the ApplicationVersion
     * @param appVersionInstanceId Id of the ApplicationVersionInstance
     * @return the ApplicationVersionInstance
     */
    private ApplicationVersionInstance getApplicationVersionInstanceBean(String appId, String appVersionId,
            String appVersionInstanceId) {
        Query q = entityManager.createQuery("SELECT ApplicationVersionInstance FROM ApplicationVersionInstance" +
                " applicationVersionInstance WHERE applicationVersionInstance.id=:appVersionInstanceId and" +
                " applicationVersionInstance.applicationVersion.id=:appVersionId and" +
                " applicationVersionInstance.applicationVersion.application.id=:appId ");
        q.setParameter("appVersionInstanceId",appVersionInstanceId);
        q.setParameter("appVersionId",appVersionId);
        q.setParameter("appId",appId);
        return (ApplicationVersionInstance) q.getSingleResult();
    }


    /**
     * Get an ApplicationVersionInstance
     *
     * @param appId                Id of the Application
     * @param appVersionId                 Id of the ApplicationVersion
     * @param appVersionInstanceId Id of the ApplicationVersionInstance to retrieve
     * @return the ApplicationVersionInstance
     */
    @Override
    public ApplicationVersionInstanceVO getApplicationVersionInstance(String appId, String appVersionId,
            String appVersionInstanceId) {
        ApplicationVersionInstance applicationVersionInstance = getApplicationVersionInstanceBean(appId, appVersionId,
                appVersionInstanceId);
        return applicationVersionInstance.createApplicationVersionInstanceVO();
    }

    /**
     * Get the ApplicationVersionInstance(s) of an ApplicationVersion.
     *
     * @param appId                Id of the Application
     * @param appVersionId Id of the ApplicationVersion
     * @return a list of ApplicationVersionInstances
     */
    @Override
    public List<ApplicationVersionInstanceVO> findApplicationVersionInstances(String appId, String appVersionId) {
        ApplicationVersion applicationVersion = getApplicationVersionBean(appId, appVersionId);
        List<ApplicationVersionInstance> applicationVersionInstanceList =
                applicationVersion.getApplicationVersionInstanceList();
        List<ApplicationVersionInstanceVO> resultList = new LinkedList<ApplicationVersionInstanceVO>();
        for (ApplicationVersionInstance tmp : applicationVersionInstanceList) {
            resultList.add(tmp.createApplicationVersionInstanceVO());
        }
        return resultList;
    }

    /**
     * Get specific ApplicationVersionInstance(s) of an Application.
     *
     * @param appId                Id of the Application
     * @param appVersionId Id of the ApplicationVersion
     * @param instanceName Instance name of the ApplicationVersionInstance
     * @return a list of ApplicationVersionInstances
     */
    @Override
    public List<ApplicationVersionInstanceVO> findApplicationVersionInstances(String appId, String appVersionId,
            String instanceName) {
        List<ApplicationVersionInstanceVO> list = findApplicationVersionInstances(appId, appVersionId);
        for (ListIterator<ApplicationVersionInstanceVO> iterator = list.listIterator(); iterator.hasNext();) {
            ApplicationVersionInstanceVO tmp = iterator.next();
            if (!tmp.getName().equals(instanceName)) {
                iterator.remove();
            }
        }
        return list;
    }

    /**
     * Create an environment.
     *
     * @param userId Id of the user
     * @param environmentVO the environment to create
     * @return the environment created
     */
    @Override
    public EnvironmentVO createEnvironment(String userId, EnvironmentVO environmentVO) {
        Environment environment = environmentVO.createBean();
        User user = getUserBean(userId);
        environment.setUser(user);
        environment.setId(getNextSequence(environmentSequence));
        if (environment.getTopologyTemplate() != null) {
            TopologyTemplate topologyTemplate = environment.getTopologyTemplate();
            topologyTemplate.setEnvironment(environment);
            if (topologyTemplate.getRelationshipTemplateList() != null) {
                for (RelationshipTemplate tmp : topologyTemplate.getRelationshipTemplateList()) {
                    tmp.setId(getNextSequence(relationshipSequence));
                    tmp.setTopologyTemplate(topologyTemplate);
                }
            }
            if (topologyTemplate.getNodeTemplateList() != null) {
                for (NodeTemplate tmp : topologyTemplate.getNodeTemplateList()) {
                    tmp.setId(getNextSequence(nodeSequence));
                    tmp.setTopologyTemplate(topologyTemplate);
                }
            }
        }
        entityManager.persist(environment);
        return environment.createEnvironmentVO();
    }

    /**
     * Update an environment.
     *
     * @param environmentVO the new environment
     * @return the environment updated
     */
    @Override
    public EnvironmentVO updateEnvironment(EnvironmentVO environmentVO) {
        Environment environment = getEnvironmentBean(environmentVO.getId());
        environment.mergeEnvironmentVO(environmentVO);
        TopologyTemplate topologyTemplate = environment.getTopologyTemplate();
        if (topologyTemplate != null) {
            if (topologyTemplate.getNodeTemplateList() != null) {
                for (NodeTemplate tmp : topologyTemplate.getNodeTemplateList()) {
                    if (tmp.getId() == null) {
                        tmp.setId(getNextSequence(nodeSequence));
                    }
                    tmp.setTopologyTemplate(topologyTemplate);
                }
            }
            if (topologyTemplate.getRelationshipTemplateList() != null) {
                for (RelationshipTemplate tmp : topologyTemplate.getRelationshipTemplateList()) {
                    if (tmp.getId() == null) {
                        tmp.setId(getNextSequence(relationshipSequence));
                    }
                    tmp.setTopologyTemplate(topologyTemplate);
                }
            }
        }
        environment = entityManager.merge(environment);
        return environment.createEnvironmentVO();
    }

    /**
     * Delete an environment.
     *
     * @param envId Id of the environment to delete
     */
    @Override
    public void deleteEnvironment(String envId) {
        entityManager.remove(getEnvironmentBean(envId));
    }

    /**
     * Get an environment.
     *
     * @param envId Id of the environment
     * @return the environment entity
     */
    private Environment getEnvironmentBean(String envId) {
        Query q = entityManager.createQuery("SELECT Environment FROM Environment environment WHERE environment.id=:envId");
        q.setParameter("envId",envId);
        return (Environment) q.getSingleResult();
    }

    /**
     * Get an environment.
     *
     * @param envId Id of the environment
     * @return the environment
     */
    @Override
    public EnvironmentVO getEnvironment(String envId) {
        Environment environment = getEnvironmentBean(envId);
        return environment.createEnvironmentVO();
    }

    /**
     * Get the environment of a user.
     *
     * @param userId Id of the user
     * @return a list of environments
     */
    @Override
    public List<EnvironmentVO> findEnvironments(String userId) {
        User user = getUserBean(userId);
        List<Environment> environmentList = user.getEnvironmentList();
        List<EnvironmentVO> resultList = new LinkedList<EnvironmentVO>();
        for (Environment tmp : environmentList) {
            resultList.add(tmp.createEnvironmentVO());
        }
        return resultList;
    }

    /**
     * Get specific Environment(s) of a user.
     *
     * @param userId  Id of the user
     * @param envName Name of the environment
     * @return a list of Environments
     */
    @Override
    public List<EnvironmentVO> findEnvironments(String userId, String envName) {
        List<EnvironmentVO> list = findEnvironments(userId);
        for (ListIterator<EnvironmentVO> iterator = list.listIterator(); iterator.hasNext();) {
            EnvironmentVO tmp = iterator.next();
            if (!tmp.getName().equals(envName)) {
                iterator.remove();
            }
        }
        return list;
    }

    /**
     * Add a link between an Environment and an Application
     *
     * @param appId                Id of the Application
     * @param appVersionId                 Id of the ApplicationVersion
     * @param appVersionInstanceId Id of the ApplicationVersionInstance
     * @param envId Id of the Environment
     */
    @Override
    public void addApplicationDeploymentLink(String appId, String appVersionId, String appVersionInstanceId,
            String envId) {
        ApplicationVersionInstance applicationVersionInstance = getApplicationVersionInstanceBean(appId, appVersionId,
                appVersionInstanceId);
        Environment environment = getEnvironmentBean(envId);
        applicationVersionInstance.setEnvironment(environment);
        entityManager.merge(applicationVersionInstance);
    }

    /**
     * Remove a link between an Environment and an Application
     *
     * @param appId                Id of the Application
     * @param appVersionId                 Id of the ApplicationVersion
     * @param appVersionInstanceId Id of the ApplicationVersionInstance
     */
    @Override
    public void removeApplicationDeploymentLink(String appId, String appVersionId, String appVersionInstanceId) {
        ApplicationVersionInstance applicationVersionInstance = getApplicationVersionInstanceBean(appId, appVersionId,
                appVersionInstanceId);
        applicationVersionInstance.setEnvironment(null);
        entityManager.merge(applicationVersionInstance);
    }

    /**
     * Get the ApplicationVersionInstances of an Environment
     *
     * @param envId ID of the Environment
     * @return a list of ApplicationVersionInstances
     */
    @Override
    public List<ApplicationVersionInstanceVO> findApplicationVersionInstancesByEnv(String envId) {
        Environment environment = getEnvironmentBean(envId);
        List<ApplicationVersionInstance> applicationVersionInstanceList =
                environment.getApplicationVersionInstanceList();
        List<ApplicationVersionInstanceVO> resultList = new LinkedList<ApplicationVersionInstanceVO>();
        for (ApplicationVersionInstance tmp : applicationVersionInstanceList) {
            resultList.add(tmp.createApplicationVersionInstanceVO());
        }
        return resultList;
    }

    /**
     * Get the Environment of an ApplicationVersionInstance
     *
     * @param appId                Id of the Application
     * @param appVersionId                 Id of the ApplicationVersion
     * @param appVersionInstanceId Id of the ApplicationVersionInstance
     * @return the Environment
     */
    @Override
    public EnvironmentVO getEnvByApplicationVersionInstance(String appId, String appVersionId,
            String appVersionInstanceId) {
        ApplicationVersionInstance applicationVersionInstance = getApplicationVersionInstanceBean(appId, appVersionId,
                appVersionInstanceId);
        return applicationVersionInstance.getEnvironment().createEnvironmentVO();
    }

    /**
     * Create a PaasJonasContainer.
     *
     * @param jonasVO the PaasJonasContainer to create
     * @return the PaasJonasContainer created
     */
    @Override
    public JonasVO createJonasContainer(JonasVO jonasVO) {
        Jonas jonas = jonasVO.createBean();
        jonas.setId(getNextSequence(entitySequence));
        if (jonas.getConnectorList() != null) {
            for (Connector tmp : jonas.getConnectorList()) {
                tmp.setJonas(jonas);
            }
        }
        if (jonas.getDatasourceList() != null) {
            for (Datasource tmp : jonas.getDatasourceList()) {
                tmp.setJonas(jonas);
            }
        }
        entityManager.persist(jonas);
        return jonas.createJonasVO();
    }

    /**
     * Delete a Jonas.
     *
     * @param paasResourceId Id of the Jonas to delete
     */
    @Override
    public void deleteJonasContainer(String paasResourceId) {
        entityManager.remove(getJonasContainerBean(paasResourceId));
    }

    /**
     * Update a Jonas.
     *
     * @param jonasVO the new PaasJonasContainer
     * @return the Jonas updated
     */
    @Override
    public JonasVO updateJonasContainer(JonasVO jonasVO) {
        Jonas jonas = getJonasContainerBean(jonasVO.getId());
        jonas.mergeJonasVO(jonasVO);
        jonas = entityManager.merge(jonas);
        return jonas.createJonasVO();
    }

    /**
     * Get a Jonas.
     *
     * @param paasResourceId Id of the Jonas
     * @return the Jonas entity
     */
    private Jonas getJonasContainerBean(String paasResourceId) {
        Query q = entityManager.createQuery("SELECT jonas FROM Jonas jonas WHERE jonas.id=:paasResourceId");
        q.setParameter("paasResourceId",paasResourceId);
        return (Jonas) q.getSingleResult();
    }

    /**
     * Get a Jonas.
     *
     * @param paasResourceId Id of the Jonas
     * @return the Jonas
     */
    @Override
    public JonasVO getJonasContainer(String paasResourceId) {
        Jonas paasJonasContainer = getJonasContainerBean(paasResourceId);
        return paasJonasContainer.createJonasVO();
    }

    /**
     * Get the Jonas.
     *
     * @return a list of Jonas
     */
    @Override
    public List<JonasVO> findJonasContainers() {
        Query q = entityManager.createQuery("SELECT jonas FROM Jonas jonas");
        List<Jonas> containerList = (List<Jonas>) q.getResultList();
        List<JonasVO> resultList = new LinkedList<JonasVO>();
        for (Jonas tmp : containerList) {
            resultList.add(tmp.createJonasVO());
        }
        return resultList;
    }

    /**
     * Add a Connector
     *
     * @param paasResourceId Id of the PaasJonasContainer
     * @param name           the name of the Connector
     * @param port           the port of the Connector
     */
    @Override
    public void addConnector(String paasResourceId, String name, int port) {
        Connector connector = new Connector();
        connector.setName(name);
        connector.setPort(port);
        Jonas jonas = getJonasContainerBean(paasResourceId);
        connector.setJonas(jonas);
        entityManager.persist(connector);
    }

    /**
     * Remove a Connector
     *
     * @param name the name of the Connector
     */
    @Override
    public void removeConnector(String paasResourceId, String name) {
        Jonas jonas = getJonasContainerBean(paasResourceId);
        List<Connector> list = jonas.getConnectorList();
        for (ListIterator<Connector> iterator = list.listIterator(); iterator.hasNext();) {
            Connector tmp = iterator.next();
            if (!tmp.getName().equals(name)) {
                iterator.remove();
            }
        }
        entityManager.merge(jonas);
    }

    /**
     * Get the Connectors
     *
     * @param paasResourceId Id of the PaasJonasContainer
     * @return a list with the Connectors
     */
    @Override
    public List<ConnectorVO> getConnectors(String paasResourceId) {
        Jonas jonas = getJonasContainerBean(paasResourceId);
        List<ConnectorVO> resultList = new LinkedList<ConnectorVO>();
        for (Connector tmp : jonas.getConnectorList()) {
            resultList.add(tmp.createConnectorVO());
        }
        return resultList;
    }

    /**
     * Add a Datasource
     *
     * @param paasResourceId Id of the PaasJonasContainer
     * @param name           the name of the datasource
     * @param url            the url of the datasource
     * @param className      the className of the datasource
     */
    @Override
    public void addDatasource(String paasResourceId, String name, String url, String className) {
        Datasource datasource = new Datasource();
        datasource.setName(name);
        datasource.setUrl(url);
        datasource.setClassName(className);
        Jonas jonas = getJonasContainerBean(paasResourceId);
        datasource.setJonas(jonas);
        entityManager.persist(datasource);
    }

    /**
     * Remove a DataSource
     *
     * @param paasResourceId Id of the PaasJonasContainer
     * @param name the name of the datasource
     */
    @Override
    public void removeDatasource(String paasResourceId, String name) {
        Jonas jonas = getJonasContainerBean(paasResourceId);
        List<Datasource> list = jonas.getDatasourceList();
        for (ListIterator<Datasource> iterator = list.listIterator(); iterator.hasNext();) {
            Datasource tmp = iterator.next();
            if (!tmp.getName().equals(name)) {
                iterator.remove();
            }
        }
        entityManager.merge(jonas);
    }

    /**
     * Get the Datasources
     *
     * @param paasResourceId Id of the PaasJonasContainer
     * @return a list with the Datasources
     */
    @Override
    public List<DatasourceVO> getDatasources(String paasResourceId) {
        Jonas jonas = getJonasContainerBean(paasResourceId);
        List<DatasourceVO> resultList = new LinkedList<DatasourceVO>();
        for (Datasource tmp : jonas.getDatasourceList()) {
            resultList.add(tmp.createDatasourceVO());
        }
        return resultList;
    }


    /**
     * Get a PaasResource.
     *
     * @param paasResourceId Id of the PaasResource
     * @return the PaasResource entity
     */
    private PaasResource getPaasResourceBean(String paasResourceId) {
        Query q = entityManager.createQuery("SELECT paasResource FROM PaasResource paasResource WHERE " +
                "paasResource.id=:paasResourceId");
        q.setParameter("paasResourceId",paasResourceId);
        return (PaasResource) q.getSingleResult();
    }

    /**
     * Get a NodeTemplate.
     *
     * @param nodeTemplateId Id of the NodeTemplate
     * @return the NodeTemplate entity
     */
    private NodeTemplate getNodeTemplateBean(String nodeTemplateId) {
        Query q = entityManager.createQuery("SELECT nodeTemplate FROM NodeTemplate nodeTemplate WHERE " +
                "nodeTemplate.id=:nodeTemplateId");
        q.setParameter("nodeTemplateId",nodeTemplateId);
        return (NodeTemplate) q.getSingleResult();
    }


    /**
     * Add a link between a PaasResource and a NodeTemplate
     *
     * @param nodeId         Id of the NodeTemplate
     * @param paasResourceId Id of the PaasResource
     */
    @Override
    public void addPaasResourceNodeLink(String nodeId, String paasResourceId) {
        NodeTemplate nodeTemplate = getNodeTemplateBean(nodeId);
        PaasResource paasResource = getPaasResourceBean(paasResourceId);
        paasResource.getNodeTemplateList().add(nodeTemplate);
        nodeTemplate.getEntityList().add(paasResource);
        entityManager.merge(paasResource);
    }

    /**
     * Remove a link between a PaasResource and a NodeTemplate
     *
     * @param nodeId         Id of the NodeTemplate
     * @param paasResourceId Id of the PaasResource
     */
    @Override
    public void removePaasResourceNodeLink(String nodeId, String paasResourceId) {
        NodeTemplate nodeTemplate = getNodeTemplateBean(nodeId);
        PaasResource paasResource = getPaasResourceBean(paasResourceId);
        paasResource.getNodeTemplateList().remove(nodeTemplate);
        nodeTemplate.getEntityList().remove(paasResource);
        entityManager.merge(paasResource);
    }

    /**
     * Get the PaasResources of a NodeTemplate
     *
     * @param nodeId Id of the NodeTemplate
     * @return A list of PaasResources
     */
    @Override
    public List<PaasResourceVO> findPaasResourcesByNode(String nodeId) {
        NodeTemplate nodeTemplate = getNodeTemplateBean(nodeId);
        List<PaasResourceVO> resultList = new LinkedList<PaasResourceVO>();
        PaasResource paasResource;
        for (Entity tmp : nodeTemplate.getEntityList()) {
            if (tmp instanceof PaasResource) {
                paasResource = (PaasResource) tmp;
                resultList.add(paasResource.createPaasResourceVO());
            }
        }
        return  resultList;
    }

    /**
     * Get the NodeTemplates of a PaasResource
     *
     * @param paasResourceId Id of the PaasResource
     * @return A list of NoteTemplateVO
     */
    @Override
    public List<NodeTemplateVO> findNodesByPaasResource(String paasResourceId) {
        PaasResource paasResource = getPaasResourceBean(paasResourceId);
        List<NodeTemplateVO> resultList = new LinkedList<NodeTemplateVO>();
        for (NodeTemplate tmp : paasResource.getNodeTemplateList()) {
            resultList.add(tmp.createNodeTemplateVO());
        }
        return  resultList;
    }


    /**
     * Create an ApacheJk.
     *
     * @param apacheJkVO the ApacheJk to create
     * @return the ApacheJk created
     */
    @Override
    public ApacheJkVO createApacheJkRouter(ApacheJkVO apacheJkVO) {
        ApacheJk apacheJk = apacheJkVO.createBean();
        apacheJk.setId(getNextSequence(entitySequence));
        if (apacheJk.getWorkerList() != null) {
            for (Worker tmp : apacheJk.getWorkerList()) {
                tmp.setApacheJk(apacheJk);
            }
        }
        if (apacheJk.getLoadBalancerList() != null) {
            for (LoadBalancer tmp : apacheJk.getLoadBalancerList()) {
                tmp.setApacheJk(apacheJk);
            }
        }
        entityManager.persist(apacheJk);
        return apacheJk.createApacheJkVO();
    }

    /**
     * Delete an ApacheJk.
     *
     * @param paasResourceId Id of the ApacheJk to delete
     */
    @Override
    public void deleteApacheJkRouter(String paasResourceId) {
        ApacheJk apacheJk = getApacheJkRouterBean(paasResourceId);
        if (apacheJk.getPaasContainerList() != null) {
            for (PaasContainer tmp : apacheJk.getPaasContainerList()) {
                tmp.getPaasDatabaseList().remove(apacheJk);
            }
        }
        entityManager.remove(apacheJk);
    }

    /**
     * Update an ApacheJk.
     *
     * @param apacheJkVO the new ApacheJk
     * @return the ApacheJk updated
     */
    @Override
    public ApacheJkVO updateApacheJkRouter(ApacheJkVO apacheJkVO) {
        ApacheJk apacheJk = getApacheJkRouterBean(apacheJkVO.getId());
        apacheJk.mergeApacheJkVO(apacheJkVO);
        apacheJk = entityManager.merge(apacheJk);
        return apacheJk.createApacheJkVO();
    }

    /**
     * Get a PaasRouter.
     *
     * @param paasResourceId Id of the PaasRouter
     * @return the PaasRouter entity
     */
    private ApacheJk getApacheJkRouterBean(String paasResourceId) {
        Query q = entityManager.createQuery("SELECT apache FROM ApacheJk apache WHERE " +
                "apache.id=:paasResourceId");
        q.setParameter("paasResourceId",paasResourceId);
        return (ApacheJk) q.getSingleResult();
    }

    /**
     * Get an ApacheJk.
     *
     * @param paasResourceId Id of the ApacheJk
     * @return the ApacheJk
     */
    @Override
    public ApacheJkVO getApacheJkRouter(String paasResourceId) {
        ApacheJk apacheJk = getApacheJkRouterBean(paasResourceId);
        return apacheJk.createApacheJkVO();
    }

    /**
     * Get the ApacheJks.
     *
     * @return a list of ApacheJks
     */
    @Override
    public List<ApacheJkVO> findApacheJkRouters() {
        Query q = entityManager.createQuery("SELECT apacheJk FROM ApacheJk apacheJk");
        List<ApacheJk> apacheList = (List<ApacheJk>) q.getResultList();
        List<ApacheJkVO> resultList = new LinkedList<ApacheJkVO>();
        for (ApacheJk tmp : apacheList) {
            resultList.add(tmp.createApacheJkVO());
        }
        return resultList;
    }

    /**
     * Add a worker
     *
     * @param paasResourceId Id of the PaasRouter
     * @param name           the worker name
     * @param host           the worker host
     * @param port           the worker port
     */
    @Override
    public void addWorker(String paasResourceId, String name, String host, int port) {
        Worker worker = new Worker();
        worker.setName(name);
        worker.setHost(host);
        worker.setPort(port);
        ApacheJk apacheJk = getApacheJkRouterBean(paasResourceId);
        worker.setApacheJk(apacheJk);
        entityManager.persist(worker);
    }

    /**
     * Remove a worker
     *
     * @param paasResourceId Id of the PaasRouter
     * @param name           the worker name
     */
    @Override
    public void removeWorker(String paasResourceId, String name) {
        ApacheJk apacheJk = getApacheJkRouterBean(paasResourceId);
        List<Worker> list = apacheJk.getWorkerList();
        for (ListIterator<Worker> iterator = list.listIterator(); iterator.hasNext();) {
            Worker tmp = iterator.next();
            if (!tmp.getName().equals(name)) {
                iterator.remove();
            }
        }
        entityManager.merge(apacheJk);
    }

    /**
     * Get the workers of an Apache Router
     *
     * @param paasResourceId Id of the PaasRouter
     * @return a list with the workers
     */
    @Override
    public List<WorkerVO> getWorkers(String paasResourceId) {
        ApacheJk apacheJk = getApacheJkRouterBean(paasResourceId);
        List<WorkerVO> resultList = new LinkedList<WorkerVO>();
        for (Worker tmp : apacheJk.getWorkerList()) {
            resultList.add(tmp.createWorkerVO());
        }
        return resultList;
    }

    /**
     * Add a LoadBalancer
     *
     * @param paasResourceId Id of the PaasRouter
     * @param name           the LoadBalancer name
     * @param mountPoints    the LoadBalancer mountPoints
     * @param workers        the LoadBalancer workerList
     */
    @Override
    public void addLoadBalancer(String paasResourceId, String name, List<String> mountPoints, List<WorkerVO> workers) {
        ApacheJk apacheJk = getApacheJkRouterBean(paasResourceId);
        LoadBalancer loadBalancer = new LoadBalancer();
        loadBalancer.setName(name);
        loadBalancer.setMountPoints(mountPoints);
        List<Worker> workerList = new LinkedList<Worker>();
        for (WorkerVO tmp : workers) {

            Worker worker = tmp.createBean();
            worker.setApacheJk(apacheJk);
            workerList.add(worker);
        }
        loadBalancer.setWorkers(workerList);
        loadBalancer.setApacheJk(apacheJk);
        entityManager.persist(loadBalancer);
    }

    /**
     * Remove a LoadBalancer
     *
     * @param paasResourceId Id of the PaasRouter
     * @param name           the LoadBalancer name
     */
    @Override
    public void removeLoadBalancer(String paasResourceId, String name) {
        ApacheJk apacheJk = getApacheJkRouterBean(paasResourceId);
        List<LoadBalancer> list = apacheJk.getLoadBalancerList();
        for (ListIterator<LoadBalancer> iterator = list.listIterator(); iterator.hasNext();) {
            LoadBalancer tmp = iterator.next();
            if (!tmp.getName().equals(name)) {
                iterator.remove();
            }
        }
        entityManager.merge(apacheJk);
    }

    /**
     * Get the LoadBalancers
     *
     * @param paasResourceId Id of the PaasRouter
     * @return a list with the LoadBalancers
     */
    @Override
    public List<LoadBalancerVO> getLoadBalancers(String paasResourceId) {
        ApacheJk apacheJk = getApacheJkRouterBean(paasResourceId);
        List<LoadBalancerVO> resultList = new LinkedList<LoadBalancerVO>();
        for (LoadBalancer tmp : apacheJk.getLoadBalancerList()) {
            resultList.add(tmp.createLoadBalancerVO());
        }
        return resultList;
    }

    /**
     * Create a PaasDatabase.
     *
     * @param paasDatabaseVO the PaasDatabase to create
     * @return the PaasDatabase created
     */
    @Override
    public PaasDatabaseVO createDatabase(PaasDatabaseVO paasDatabaseVO) {
        PaasDatabase paasDatabase = paasDatabaseVO.createBean();
        paasDatabase.setId(getNextSequence(entitySequence));
        entityManager.persist(paasDatabase);
        return paasDatabase.createPaasDatabaseVO();
    }

    /**
     * Delete a PaasDatabase.
     *
     * @param paasResourceId Id of the PaasDatabase to delete
     */
    @Override
    public void deleteDatabase(String paasResourceId) {
        PaasDatabase paasDatabase = getPaasDatabaseBean(paasResourceId);
        if (paasDatabase.getPaasContainerList() != null) {
            for (PaasContainer tmp : paasDatabase.getPaasContainerList()) {
                tmp.getPaasDatabaseList().remove(paasDatabase);
            }
        }
        entityManager.remove(paasDatabase);
    }

    /**
     * Update a PaasDatabase.
     *
     * @param paasDatabaseVO the new PaasDatabase
     * @return the PaasDatabase updated
     */
    @Override
    public PaasDatabaseVO updateDatabase(PaasDatabaseVO paasDatabaseVO) {
        PaasDatabase paasDatabase = getPaasDatabaseBean(paasDatabaseVO.getId());
        paasDatabase.mergePaasDatabaseVO(paasDatabaseVO);
        paasDatabase = entityManager.merge(paasDatabase);
        return paasDatabase.createPaasDatabaseVO();
    }

    /**
     * Get a PaasDatabase.
     *
     * @param paasDatabaseId Id of the PaasDatabase
     * @return the PaasDatabase entity
     */
    private PaasDatabase getPaasDatabaseBean(String paasDatabaseId) {
        Query q = entityManager.createQuery("SELECT paasDatabase FROM PaasDatabase paasDatabase WHERE " +
                "paasDatabase.id=:paasDatabaseId");
        q.setParameter("paasDatabaseId",paasDatabaseId);
        return (PaasDatabase) q.getSingleResult();
    }

    /**
     * Get a PaasDatabase.
     *
     * @param paasResourceId Id of the PaasDatabase
     * @return the PaasDatabase
     */
    @Override
    public PaasDatabaseVO getDatabase(String paasResourceId) {
        PaasDatabase paasDatabase = getPaasDatabaseBean(paasResourceId);
        return paasDatabase.createPaasDatabaseVO();
    }

    /**
     * Get the PaasDatabases.
     *
     * @return a list of PaasDatabases
     */
    @Override
    public List<PaasDatabaseVO> findDatabases() {
        Query q = entityManager.createQuery("SELECT paasDatabase FROM PaasDatabase paasDatabase");
        List<PaasDatabase> list = (List<PaasDatabase>) q.getResultList();
        List<PaasDatabaseVO> resultList = new LinkedList<PaasDatabaseVO>();
        for (PaasDatabase tmp : list) {
            resultList.add(tmp.createPaasDatabaseVO());
        }
        return resultList;
    }

    /**
     * Create an IaasCompute.
     *
     * @param iaasComputeVO the user to create
     * @return the user created
     */
    @Override
    public IaasComputeVO createIaasCompute(IaasComputeVO iaasComputeVO) {
        IaasCompute iaasCompute = iaasComputeVO.createBean();
        iaasCompute.setId(getNextSequence(entitySequence));
        entityManager.persist(iaasCompute);
        return iaasCompute.createIaasComputeVO();
    }

    /**
     * Delete an IaasCompute.
     *
     * @param iaasComputeId Id of the IaasCompute to delete
     */
    @Override
    public void deleteIaasCompute(String iaasComputeId) {
        entityManager.remove(getIaasComputeBean(iaasComputeId));
    }

    /**
     * Update a IaasCompute.
     *
     * @param iaasComputeVO the new IaasCompute
     * @return the IaasCompute updated
     */
    @Override
    public IaasComputeVO updateIaasCompute(IaasComputeVO iaasComputeVO) {
        IaasCompute iaasCompute = getIaasComputeBean(iaasComputeVO.getId());
        iaasCompute.mergeIaasComputeVO(iaasComputeVO);
        iaasCompute = entityManager.merge(iaasCompute);
        return iaasCompute.createIaasComputeVO();
    }

    /**
     * Get a IaasCompute.
     *
     * @param iaasComputeId Id of the IaasCompute
     * @return the IaasCompute entity
     */
    private IaasCompute getIaasComputeBean(String iaasComputeId) {
        Query q = entityManager.createQuery("SELECT iaasCompute FROM IaasCompute iaasCompute WHERE " +
                "iaasCompute.id=:iaasComputeId");
        q.setParameter("iaasComputeId",iaasComputeId);
        return (IaasCompute) q.getSingleResult();
    }

    /**
     * Get a IaasCompute.
     *
     * @param iaasComputeId Id of the IaasCompute
     * @return the IaasCompute
     */
    @Override
    public IaasComputeVO getIaasCompute(String iaasComputeId) {
        IaasCompute iaasCompute = getIaasComputeBean(iaasComputeId);
        return iaasCompute.createIaasComputeVO();
    }

    /**
     * Get the IaasComputes.
     *
     * @return a list of IaasComputes
     */
    @Override
    public List<IaasComputeVO> findIaasComputes() {
        Query q = entityManager.createQuery("SELECT iaasCompute FROM IaasCompute iaasCompute");
        List<IaasCompute> list = (List<IaasCompute>) q.getResultList();
        List<IaasComputeVO> resultList = new LinkedList<IaasComputeVO>();
        for (IaasCompute tmp : list) {
            resultList.add(tmp.createIaasComputeVO());
        }
        return resultList;
    }

    /**
     * Create a PaasAgent.
     *
     * @param paasAgentVO the PaasAgent to create
     * @return the PaasAgent created
     */
    @Override
    public PaasAgentVO createAgent(PaasAgentVO paasAgentVO) {
        PaasAgent paasAgent = paasAgentVO.createBean();
        paasAgent.setId(getNextSequence(entitySequence));
        entityManager.persist(paasAgent);
        return paasAgent.createPaasAgentVO();
    }

    /**
     * Delete a PaasAgent.
     *
     * @param paasResourceId Id of the PaasAgent to delete
     */
    @Override
    public void deleteAgent(String paasResourceId) {
        entityManager.remove(getPaasAgentBean(paasResourceId));
    }

    /**
     * Update a PaasAgent.
     *
     * @param paasAgentVO the new PaasAgent
     * @return the PaasAgent updated
     */
    @Override
    public PaasAgentVO updateAgent(PaasAgentVO paasAgentVO) {
        PaasAgent paasAgent = getPaasAgentBean(paasAgentVO.getId());
        paasAgent.mergePaasAgentVO(paasAgentVO);
        paasAgent = entityManager.merge(paasAgent);
        return paasAgent.createPaasAgentVO();
    }

    /**
     * Get a PaasAgent.
     *
     * @param paasAgentId Id of the PaasAgent
     * @return the PaasAgent entity
     */
    private PaasAgent getPaasAgentBean(String paasAgentId) {
        Query q = entityManager.createQuery("SELECT paasAgent FROM PaasAgent paasAgent WHERE " +
                "paasAgent.id=:paasAgentId");
        q.setParameter("paasAgentId",paasAgentId);
        return (PaasAgent) q.getSingleResult();
    }

    /**
     * Get a PaasAgent.
     *
     * @param paasResourceId Id of the PaasAgent
     * @return the PaasAgent
     */
    @Override
    public PaasAgentVO getAgent(String paasResourceId) {
        PaasAgent paasAgent = getPaasAgentBean(paasResourceId);
        return paasAgent.createPaasAgentVO();
    }

    /**
     * Get the PaasAgents.
     *
     * @return a list of PaasAgents
     */
    @Override
    public List<PaasAgentVO> findAgents() {
        Query q = entityManager.createQuery("SELECT paasAgent FROM PaasAgent paasAgent");
        List<PaasAgent> list = (List<PaasAgent>) q.getResultList();
        List<PaasAgentVO> resultList = new LinkedList<PaasAgentVO>();
        for (PaasAgent tmp : list) {
            resultList.add(tmp.createPaasAgentVO());
        }
        return resultList;
    }

    /**
     * Create a PaasFrontend.
     *
     * @param paasFrontendVO the PaasFrontend to create
     * @return the PaasFrontend created
     */
    @Override
    public PaasFrontendVO createFrontend(PaasFrontendVO paasFrontendVO) {
        PaasFrontend paasFrontend = paasFrontendVO.createBean();
        paasFrontend.setId(getNextSequence(frontendSequence));
        if (paasFrontend.getVirtualHosts() != null) {
            for (VirtualHost tmp : paasFrontend.getVirtualHosts()) {
                tmp.setPaasFrontend(paasFrontend);
            }
        }
        entityManager.persist(paasFrontend);
        return paasFrontend.createPaasFrontendVO();
    }

    /**
     * Delete a PaasFrontend.
     *
     * @param paasResourceId Id of the PaasFrontend to delete
     */
    @Override
    public void deleteFrontend(String paasResourceId) {
        PaasFrontend paasFrontend = getPaasFrontendBean(paasResourceId);
        if (paasFrontend.getPaasRouterList() != null) {
            for (PaasRouter tmp : paasFrontend.getPaasRouterList()) {
                tmp.setPaasFrontend(null);
            }
        }
        entityManager.remove(paasFrontend);
    }

    /**
     * Update a PaasFrontend.
     *
     * @param paasFrontendVO the new PaasFrontend
     * @return the PaasFrontend updated
     */
    @Override
    public PaasFrontendVO updateFrontend(PaasFrontendVO paasFrontendVO) {
        PaasFrontend paasFrontend = getPaasFrontendBean(paasFrontendVO.getId());
        paasFrontend.mergePaasFrontendVO(paasFrontendVO);
        paasFrontend = entityManager.merge(paasFrontend);
        return paasFrontend.createPaasFrontendVO();
    }

    /**
     * Get a PaasFrontend.
     *
     * @param paasFrontendId Id of the PaasFrontend
     * @return the PaasFrontend entity
     */
    private PaasFrontend getPaasFrontendBean(String paasFrontendId) {
        Query q = entityManager.createQuery("SELECT paasFrontend FROM PaasFrontend paasFrontend WHERE " +
                "paasFrontend.id=:paasFrontendId");
        q.setParameter("paasFrontendId",paasFrontendId);
        return (PaasFrontend) q.getSingleResult();
    }

    /**
     * Get a PaasFrontend.
     *
     * @param paasResourceId Id of the PaasFrontend
     * @return the PaasFrontend
     */
    @Override
    public PaasFrontendVO getFrontend(String paasResourceId) {
        PaasFrontend paasFrontend = getPaasFrontendBean(paasResourceId);
        return paasFrontend.createPaasFrontendVO();
    }

    /**
     * Get the PaasFrontends.
     *
     * @return a list of PaasFrontends
     */
    @Override
    public List<PaasFrontendVO> findFrontends() {
        Query q = entityManager.createQuery("SELECT paasFrontend FROM PaasFrontend paasFrontend");
        List<PaasFrontend> list = (List<PaasFrontend>) q.getResultList();
        List<PaasFrontendVO> resultList = new LinkedList<PaasFrontendVO>();
        for (PaasFrontend tmp : list) {
            resultList.add(tmp.createPaasFrontendVO());
        }
        return resultList;
    }

    /**
     * Add a VirtualHost
     *
     * @param paasFrontendId Id of the PaasFrontend
     * @param name           the name of the VirtualHost
     */
    @Override
    public void addVirtualHost(String paasFrontendId, String name) {
        VirtualHost virtualHost = new VirtualHost();
        virtualHost.setName(name);
        PaasFrontend paasFrontend = getPaasFrontendBean(paasFrontendId);
        virtualHost.setPaasFrontend(paasFrontend);
        entityManager.persist(virtualHost);
    }

    /**
     * Remove a VirtualHost
     *
     * @param name the name of the VirtualHost
     */
    @Override
    public void removeVirtualHost(String paasFrontendId, String name) {
        PaasFrontend paasFrontend = getPaasFrontendBean(paasFrontendId);
        List<VirtualHost> list = paasFrontend.getVirtualHosts();
        for (ListIterator<VirtualHost> iterator = list.listIterator(); iterator.hasNext();) {
            VirtualHost tmp = iterator.next();
            if (!tmp.getName().equals(name)) {
                iterator.remove();
            }
        }
        entityManager.merge(paasFrontend);
    }

    /**
     * Get the VirtualHosts
     *
     * @param paasFrontendId Id of the PaasFrontend
     * @return a list with the VirtualHosts
     */
    @Override
    public List<VirtualHostVO> getVirtualHosts(String paasFrontendId) {
        PaasFrontend paasFrontend = getPaasFrontendBean(paasFrontendId);
        List<VirtualHostVO> resultList = new LinkedList<VirtualHostVO>();
        for (VirtualHost tmp : paasFrontend.getVirtualHosts()) {
            resultList.add(tmp.createVirtualHostVO());
        }
        return resultList;
    }

    /**
     * Get a PaasContainer.
     *
     * @param paasContainerId Id of the PaasContainer
     * @return the PaasContainer entity
     */
    private PaasContainer getPaasContainerBean(String paasContainerId) {
        Query q = entityManager.createQuery("SELECT paasContainer FROM PaasContainer paasContainer WHERE " +
                "paasContainer.id=:paasContainerId");
        q.setParameter("paasContainerId",paasContainerId);
        return (PaasContainer) q.getSingleResult();
    }

    /**
     * Add a link between a PaasContainer and a PaasDatabase
     *
     * @param paasContainerId Id of the PaasContainer
     * @param paasDatabaseId  Id of the PaasDatabase
     */
    @Override
    public void addContainerDatabaseLink(String paasContainerId, String paasDatabaseId) {
        PaasContainer paasContainer = getPaasContainerBean(paasContainerId);
        PaasDatabase paasDatabase = getPaasDatabaseBean(paasDatabaseId);
        paasDatabase.getPaasContainerList().add(paasContainer);
        paasContainer.getPaasDatabaseList().add(paasDatabase);
        entityManager.merge(paasDatabase);
    }

    /**
     * Remove a link between a PaasContainer and a PaasDatabase
     *
     * @param paasContainerId Id of the PaasContainer
     * @param paasDatabaseId  Id of the PaasDatabase
     */
    @Override
    public void removeContainerDatabaseLink(String paasContainerId, String paasDatabaseId) {
        PaasContainer paasContainer = getPaasContainerBean(paasContainerId);
        PaasDatabase paasDatabase = getPaasDatabaseBean(paasDatabaseId);
        paasDatabase.getPaasContainerList().remove(paasContainer);
        paasContainer.getPaasDatabaseList().remove(paasDatabase);
        entityManager.merge(paasContainer);
    }

    /**
     * Get the PaasContainers of a PaasDatabase
     *
     * @param paasDatabaseId Id of the PaasDatabase
     */
    @Override
    public List<PaasContainerVO> findContainersByDatabase(String paasDatabaseId) {
        PaasDatabase paasDatabase = getPaasDatabaseBean(paasDatabaseId);
        List<PaasContainerVO> resultList = new LinkedList<PaasContainerVO>();
        for (PaasContainer tmp : paasDatabase.getPaasContainerList()) {
            resultList.add(tmp.createPaasContainerVO());
        }
        return resultList;
    }

    /**
     * Get the PaasDatabases of a PaasContainer
     *
     * @param paasContainerId Id of the PaasContainer
     */
    @Override
    public List<PaasDatabaseVO> findDatabasesByContainer(String paasContainerId) {
        PaasContainer paasContainer = getPaasContainerBean(paasContainerId);
        List<PaasDatabaseVO> resultList = new LinkedList<PaasDatabaseVO>();
        for (PaasDatabase tmp : paasContainer.getPaasDatabaseList()) {
            resultList.add(tmp.createPaasDatabaseVO());
        }
        return resultList;
    }

    /**
     * Add a link between a PaasRouter and a PaasContainer
     *
     * @param paasRouterId    Id of the PaasRouter
     * @param paasContainerId Id of the PaasContainer
     */
    @Override
    public void addRouterContainerLink(String paasRouterId, String paasContainerId) {
        PaasContainer paasContainer = getPaasContainerBean(paasContainerId);
        PaasRouter paasRouter = getPaasRouterBean(paasRouterId);
        paasRouter.getPaasContainerList().add(paasContainer);
        paasContainer.getPaasRouterList().add(paasRouter);
        entityManager.merge(paasRouter);
    }

    /**
     * Remove a link between a PaasRouter and a PaasContainer
     *
     * @param paasRouterId    Id of the PaasRouter
     * @param paasContainerId Id of the PaasContainer
     */
    @Override
    public void removeRouterContainerLink(String paasRouterId, String paasContainerId) {
        PaasContainer paasContainer = getPaasContainerBean(paasContainerId);
        PaasRouter paasRouter = getPaasRouterBean(paasRouterId);
        paasRouter.getPaasContainerList().remove(paasContainer);
        paasContainer.getPaasRouterList().remove(paasRouter);
        entityManager.merge(paasRouter);
    }

    /**
     * Get the PaasRouters of a PaasContainer
     *
     * @param paasContainerId Id of the PaasContainer
     */
    @Override
    public List<PaasRouterVO> findRoutersByContainer(String paasContainerId) {
        PaasContainer paasContainer = getPaasContainerBean(paasContainerId);
        List<PaasRouterVO> resultList = new LinkedList<PaasRouterVO>();
        for (PaasRouter tmp : paasContainer.getPaasRouterList()) {
            resultList.add(tmp.createPaasRouterVO());
        }
        return resultList;
    }


    /**
     * Get a PaasRouter.
     *
     * @param paasRouterId Id of the PaasRouter
     * @return the PaasRouter entity
     */
    private PaasRouter getPaasRouterBean(String paasRouterId) {
        Query q = entityManager.createQuery("SELECT paasRouter FROM PaasRouter paasRouter WHERE " +
                "paasRouter.id=:paasRouterId");
        q.setParameter("paasRouterId",paasRouterId);
        return (PaasRouter) q.getSingleResult();
    }

    /**
     * Get the PaasContainers of a PaasRouter
     *
     * @param paasRouterId Id of the PaasRouter
     */
    @Override
    public List<PaasContainerVO> findContainersByRouter(String paasRouterId) {
        PaasRouter paasRouter = getPaasRouterBean(paasRouterId);
        List<PaasContainerVO> resultList = new LinkedList<PaasContainerVO>();
        for (PaasContainer tmp : paasRouter.getPaasContainerList()) {
            resultList.add(tmp.createPaasContainerVO());
        }
        return resultList;
    }

    /**
     * Add a link between a PaasResource and a PaasAgent
     *
     * @param paasResourceId Id of the PaasResource
     * @param paasAgentId    Id of the PaasAgent
     */
    @Override
    public void addPaasResourceAgentLink(String paasResourceId, String paasAgentId) {
        PaasResource paasResource = getPaasResourceBean(paasResourceId);
        PaasAgent paasAgent = getPaasAgentBean(paasAgentId);
        paasResource.setPaasAgent(paasAgent);
        entityManager.merge(paasResource);
    }

    /**
     * Remove a link between a PaasResource and a PaasAgent
     *
     * @param paasResourceId Id of the PaasResource
     * @param paasAgentId    Id of the PaasAgent
     */
    @Override
    public void removePaasResourceAgentLink(String paasResourceId, String paasAgentId) {
        PaasResource paasResource = getPaasResourceBean(paasResourceId);
        paasResource.setPaasAgent(null);
        entityManager.merge(paasResource);
    }

    /**
     * Get the PaasResources of a PaasAgent
     *
     * @param paasAgentId Id of the PaasAgent
     */
    @Override
    public List<PaasResourceVO> findPaasResourcesByAgent(String paasAgentId) {
        PaasAgent paasAgent = getPaasAgentBean(paasAgentId);
        List<PaasResourceVO> resultList = new LinkedList<PaasResourceVO>();
        for (PaasResource tmp : paasAgent.getPaasResourceList()) {
            resultList.add(tmp.createPaasResourceVO());
        }
        return resultList;
    }

    /**
     * Get the PaasAgent of a PaasResource
     *
     * @param paasResourceId Id of the PaasResource
     */
    @Override
    public PaasAgentVO findAgentByPaasResource(String paasResourceId) {
        PaasResource paasResource = getPaasResourceBean(paasResourceId);
        return paasResource.getPaasAgent().createPaasAgentVO();
    }


    /**
     * Add a link between a PaasResource and a IaasCompute
     *
     * @param paasResourceId Id of the PaasResource
     * @param iaasComputeId  Id of the IaasCompute
     */
    @Override
    public void addPaasResourceIaasComputeLink(String paasResourceId, String iaasComputeId) {
        PaasResource paasResource = getPaasResourceBean(paasResourceId);
        IaasCompute iaasCompute = getIaasComputeBean(iaasComputeId);
        paasResource.setIaasCompute(iaasCompute);
        entityManager.merge(paasResource);
    }

    /**
     * Remove a link between a PaasResource and a IaasCompute
     *
     * @param paasResourceId Id of the PaasResource
     * @param iaasComputeId  Id of the IaasCompute
     */
    @Override
    public void removePaasResourceIaasComputeLink(String paasResourceId, String iaasComputeId) {
        PaasResource paasResource = getPaasResourceBean(paasResourceId);
        paasResource.setIaasCompute(null);
        entityManager.merge(paasResource);
    }

    /**
     * Get the PaasResources of a IaasCompute
     *
     * @param iaasComputeId Id of the IaasCompute
     */
    @Override
    public List<PaasResourceVO> findPaasResourcesByIaasCompute(String iaasComputeId) {
        IaasCompute iaasCompute = getIaasComputeBean(iaasComputeId);
        List<PaasResourceVO> resultList = new LinkedList<PaasResourceVO>();
        PaasResource paasResource;
        for (PaasEntity tmp : iaasCompute.getPaasEntityList()) {
            if (tmp instanceof PaasResource) {
                paasResource = (PaasResource) tmp;
                resultList.add(paasResource.createPaasResourceVO());
            }
        }
        return resultList;
    }

    /**
     * Get the IaasCompute of a PaasResource
     *
     * @param paasResourceId Id of the PaasResource
     */
    @Override
    public IaasComputeVO findIaasComputeByPaasResource(String paasResourceId) {
        PaasResource paasResource = getPaasResourceBean(paasResourceId);
        return paasResource.getIaasCompute().createIaasComputeVO();
    }

    /**
     * Add a link between a PaasRouter and a PaasFrontend
     *
     * @param paasRouterId   Id of the PaasRouter
     * @param paasFrontendId Id of the PaasFrontend
     */
    @Override
    public void addPaasRouterFrontendLink(String paasRouterId, String paasFrontendId) {
        PaasFrontend paasFrontend = getPaasFrontendBean(paasFrontendId);
        PaasRouter paasRouter = getPaasRouterBean(paasRouterId);
        paasRouter.setPaasFrontend(paasFrontend);
        paasFrontend.getPaasRouterList().add(paasRouter);
        entityManager.merge(paasFrontend);
    }

    /**
     * Remove a link between a PaasRouter and a PaasFrontend
     *
     * @param paasRouterId   Id of the PaasRouter
     * @param paasFrontendId Id of the PaasFrontend
     */
    @Override
    public void removePaasRouterFrontendLink(String paasRouterId, String paasFrontendId) {
        PaasFrontend paasFrontend = getPaasFrontendBean(paasFrontendId);
        PaasRouter paasRouter = getPaasRouterBean(paasRouterId);
        paasRouter.setPaasFrontend(null);
        paasFrontend.getPaasRouterList().remove(paasRouter);
        entityManager.merge(paasFrontend);
    }

    /**
     * Get the PaasRouters of a PaasFrontend
     *
     * @param paasFrontendId Id of the PaasFrontend
     * @return a list of PaasRouter
     */
    @Override
    public List<PaasRouterVO> findPaasRoutersByFrontend(String paasFrontendId) {
        PaasFrontend paasFrontend = getPaasFrontendBean(paasFrontendId);
        List<PaasRouterVO> resultList = new LinkedList<PaasRouterVO>();
        for (PaasRouter tmp : paasFrontend.getPaasRouterList()) {
            resultList.add(tmp.createPaasRouterVO());
        }
        return  resultList;
    }

    /**
     * Get the PaasFrontends of a PaasRouter
     *
     * @param paasRouterId Id of the PaasRouter
     * @return the PaasFrontend
     */
    @Override
    public PaasFrontendVO findFrontendByPaasRouter(String paasRouterId) {
        PaasRouter paasRouter = getPaasRouterBean(paasRouterId);
        return paasRouter.getPaasFrontend().createPaasFrontendVO();
    }
}
