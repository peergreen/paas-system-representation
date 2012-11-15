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

import org.ow2.jonas.jpaas.sr.facade.vo.PaasDatabaseVO;

import java.util.List;

/**
 * Interface for the PaasDatabase facade.
 * @author David Richard
 */
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
