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

import org.ow2.jonas.jpaas.sr.facade.vo.PaasDatabaseVO;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface for the PaasDatabase facade.
 * @author David Richard
 */
@Remote
public interface ISrPaasDatabaseFacade {

    /**
     * Create a PaasDatabase.
     *
     * @param paasDatabaseVO the PaasDatabase to create
     * @return the PaasDatabase created
     */
    public PaasDatabaseVO createDatabase(PaasDatabaseVO paasDatabaseVO);

    /**
     * Delete a PaasDatabase.
     *
     * @param paasResourceId Id of the PaasDatabase to delete
     */
    public void deleteDatabase(String paasResourceId);

    /**
     * Update a PaasDatabase.
     *
     * @param paasDatabaseVO the new PaasDatabase
     * @return the PaasDatabase updated
     */
    public PaasDatabaseVO updateDatabase(PaasDatabaseVO paasDatabaseVO);

    /**
     * Get a PaasDatabase.
     *
     * @param paasResourceId Id of the PaasDatabase
     * @return the PaasDatabase
     */
    public PaasDatabaseVO getDatabase(String paasResourceId);

    /**
     * Get the PaasDatabases.
     *
     * @return a list of PaasDatabases
     */
    public List<PaasDatabaseVO> findDatabases();
}