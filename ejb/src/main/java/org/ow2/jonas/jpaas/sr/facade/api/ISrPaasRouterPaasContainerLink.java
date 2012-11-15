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

import org.ow2.jonas.jpaas.sr.facade.vo.PaasContainerVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasRouterVO;

import java.util.List;

/**
 * Interface for the PaasRouterPaasContainerLink facade.
 * @author David Richard
 */
public interface ISrPaasRouterPaasContainerLink {

    /**
     * Add a link between a PaasRouter and a PaasContainer
     *
     * @param paasRouterId Id of the PaasRouter
     * @param paasContainerId Id of the PaasContainer
     */
    public void addRouterContainerLink(String paasRouterId, String paasContainerId);

    /**
     * Remove a link between a PaasRouter and a PaasContainer
     *
     * @param paasRouterId Id of the PaasRouter
     * @param paasContainerId Id of the PaasContainer
     */
    public void removeRouterContainerLink(String paasRouterId, String paasContainerId);

    /**
     * Get the PaasRouters of a PaasContainer
     *
     * @param paasContainerId Id of the PaasContainer
     */
    public List<PaasRouterVO> findRoutersByContainer(String paasContainerId);

    /**
     * Get the PaasContainers of a PaasRouter
     *
     * @param paasRouterId Id of the PaasRouter
     */
    public List<PaasContainerVO> findContainersByRouter(String paasRouterId);

}
