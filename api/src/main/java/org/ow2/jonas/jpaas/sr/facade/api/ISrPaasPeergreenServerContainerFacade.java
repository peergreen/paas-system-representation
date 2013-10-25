
package org.ow2.jonas.jpaas.sr.facade.api;

import java.util.List;

import org.ow2.jonas.jpaas.sr.facade.vo.JonasVO;
import org.ow2.jonas.jpaas.sr.facade.vo.PeergreenServerVO;

/**
 * Interface for the PaasPeergreenServerContainer facade.
 * @author Florent Benoit
 */
public interface ISrPaasPeergreenServerContainerFacade {

    /**
     * Create a PaasPeergreenServerContainer.
     *
     * @param peergreenServerVO the PaasPeergreenServerContainer to create
     * @return the PaasPeergreenServerContainer created
     */
    public PeergreenServerVO createPeergreenServerContainer(PeergreenServerVO peergreenServerVO);

    /**
     * Delete a PaasPeergreenServerContainer.
     *
     * @param paasResourceId Id of the PaasPeergreenServerContainer to delete
     */
    public void deletePeergreenServerContainer(String paasResourceId);

    /**
     * Update a PaasPeergreenServerContainer.
     *
     * @param jonasVO the new PaasPeergreenServerContainer
     * @return the PaasPeergreenServerContainer updated
     */
    public PeergreenServerVO updatePeergreenServerContainer(PeergreenServerVO peergreenServerVO);

    /**
     * Get a PaasPeergreenServerContainer.
     *
     * @param paasResourceId Id of the PaasPeergreenServerContainer
     * @return the PaasPeergreenServerContainer
     */
    public PeergreenServerVO getPeergreenServerContainer(String paasResourceId);

    /**
     * Get the PaasPeergreenServerContainers.
     *
     * @return a list of PaasPeergreenServerContainers
     */
    public List<PeergreenServerVO> findPeergreenServerContainers();

    /**
     * Get the PeergreenServer container
     * @return a PeergreenServerVO
     */
    public PeergreenServerVO findPeergreenServerContainer(String containerName);

}
