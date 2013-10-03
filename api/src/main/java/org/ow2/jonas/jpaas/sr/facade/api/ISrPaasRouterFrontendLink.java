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

import org.ow2.jonas.jpaas.sr.facade.vo.PaasFrontendVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PaasRouterVO;

import java.util.List;

/**
 * Interface for the PaasFrontendLink facade.
 * @author David Richard
 */
public interface ISrPaasRouterFrontendLink {

    /**
     * Add a link between a PaasRouter and a PaasFrontend
     *
     * @param paasRouterId Id of the PaasRouter
     * @param paasFrontendId Id of the PaasFrontend
     */
    public void addPaasRouterFrontendLink(String paasRouterId, String paasFrontendId);

    /**
     * Remove a link between a PaasRouter and a PaasFrontend
     *
     * @param paasRouterId Id of the PaasRouter
     * @param paasFrontendId Id of the PaasFrontend
     */
    public void removePaasRouterFrontendLink(String paasRouterId, String paasFrontendId);

    /**
     * Get the PaasRouters of a PaasFrontend
     *
     * @param paasFrontendId Id of the PaasFrontend
     * @return a list of PaasRouter
     */
    public List<PaasRouterVO> findPaasRoutersByFrontend(String paasFrontendId);

    /**
     * Get the PaasFrontend of a PaasRouter
     *
     * @param paasRouterId Id of the PaasRouter
     * @return the PaasFrontend
     */
    public PaasFrontendVO findFrontendByPaasRouter(String paasRouterId);
}
