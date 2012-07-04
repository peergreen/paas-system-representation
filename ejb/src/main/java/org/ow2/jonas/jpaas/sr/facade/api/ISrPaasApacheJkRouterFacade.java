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

import org.ow2.jonas.jpaas.sr.facade.vo.LoadBalancerVO;
import org.ow2.jonas.jpaas.sr.facade.vo.ApacheJkVO;
import org.ow2.jonas.jpaas.sr.facade.vo.WorkerVO;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface for the ApacheJk facade.
 * @author David Richard
 */
@Remote
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
    public void addLoadBalancer(String paasResourceId, String name, List<String> mountPoints, List<WorkerVO> workers);

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