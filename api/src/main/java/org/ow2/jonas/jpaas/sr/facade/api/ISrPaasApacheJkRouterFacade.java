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

import org.ow2.jonas.jpaas.sr.facade.vo.LoadBalancerVO;
import org.ow2.jonas.jpaas.sr.facade.vo.ApacheJkVO;
import org.ow2.jonas.jpaas.sr.facade.vo.WorkerVO;

import java.util.List;

/**
 * Interface for the ApacheJk facade.
 * @author David Richard
 */
public interface ISrPaasApacheJkRouterFacade {

    /**
     * Create an ApacheJk.
     *
     * @param apacheJkVO the ApacheJk to create
     * @return the ApacheJk created
     */
    public ApacheJkVO createApacheJkRouter(ApacheJkVO apacheJkVO);

    /**
     * Delete an ApacheJk.
     *
     * @param paasResourceId Id of the ApacheJk to delete
     */
    public void deleteApacheJkRouter(String paasResourceId);

    /**
     * Update an ApacheJk.
     *
     * @param apacheJkVO the new ApacheJk
     * @return the ApacheJk updated
     */
    public ApacheJkVO updateApacheJkRouter(ApacheJkVO apacheJkVO);

    /**
     * Get an ApacheJk.
     *
     * @param paasResourceId Id of the ApacheJk
     * @return the ApacheJk
     */
    public ApacheJkVO getApacheJkRouter(String paasResourceId);

    /**
     * Get the ApacheJks.
     *
     * @return a list of ApacheJks
     */
    public List<ApacheJkVO> findApacheJkRouters();

    /**
     * Add a worker
     *
     * @param paasResourceId Id of the ApacheJk
     * @param name the worker name
     * @param host the worker host
     * @param port the worker port
     */
    public void addWorker(String paasResourceId, String name, String host, int port);

    /**
     * Remove a worker
     *
     * @param paasResourceId Id of the ApacheJk
     * @param name the worker name
     */
    public void removeWorker(String paasResourceId, String name);

    /**
     * Get the workers of an Apache Router
     *
     * @param paasResourceId Id of the ApacheJk
     * @return a list with the workers
     */
    public List<WorkerVO> getWorkers(String paasResourceId);

    /**
     * Add a LoadBalancer
     *
     * @param paasResourceId Id of the ApacheJk
     * @param name the LoadBalancer name
     * @param mountPoints the LoadBalancer mountPoints
     * @param workers the LoadBalancer workerList
     */
    public void addLoadBalancer(String paasResourceId, String name, List<String> mountPoints, List<String> workers);

    /**
     * Remove a LoadBalancer
     * @param paasResourceId Id of the ApacheJk
     * @param name the LoadBalancer name
     */
    public void removeLoadBalancer(String paasResourceId, String name);

    /**
     * Get the LoadBalancers
     *
     * @param paasResourceId Id of the ApacheJk
     * @return a list with the LoadBalancers
     */
    public List<LoadBalancerVO> getLoadBalancers(String paasResourceId);
}
