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