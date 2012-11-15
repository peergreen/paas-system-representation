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
import org.ow2.jonas.jpaas.sr.facade.vo.PaasDatabaseVO;

import java.util.List;

/**
 * Interface for the PaasDatabasePaasDatabaseLink facade.
 * @author David Richard
 */
public interface ISrPaasContainerPaasDatabaseLink {

    /**
       * Add a link between a PaasContainer and a PaasDatabase
       *
       * @param paasContainerId Id of the PaasContainer
       * @param paasDatabaseId Id of the PaasDatabase
       */
      public void addContainerDatabaseLink(String paasContainerId, String paasDatabaseId);
  
      /**
       * Remove a link between a PaasContainer and a PaasDatabase
       *
       * @param paasContainerId Id of the PaasContainer
       * @param paasDatabaseId Id of the PaasDatabase
       */
      public void removeContainerDatabaseLink(String paasContainerId, String paasDatabaseId);
  
      /**
       * Get the PaasContainers of a PaasDatabase
       *
       * @param paasDatabaseId Id of the PaasDatabase
       */
      public List<PaasContainerVO> findContainersByDatabase(String paasDatabaseId);
  
      /**
       * Get the PaasDatabases of a PaasContainer
       *
       * @param paasContainerId Id of the PaasContainer
       */
      public List<PaasDatabaseVO> findDatabasesByContainer(String paasContainerId);
}
