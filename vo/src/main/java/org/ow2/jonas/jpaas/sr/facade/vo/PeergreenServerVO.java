package org.ow2.jonas.jpaas.sr.facade.vo;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.ow2.jonas.jpaas.sr.model.PeergreenServer;

/**
 * Define a Peergreen server
 * @author Florent Benoit
 */
public class PeergreenServerVO extends PaasContainerVO implements java.io.Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -5382597951586449684L;

    /**
     * Version.
     */
    private String version;

    /**
     * Profile of the Peergreen server.
     */
    private String profile;

    public PeergreenServerVO() {
        super();
    }

    public PeergreenServerVO(String id, String name, String state, Map<String,String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts, String version, String profile) {
        super(id, name, state, capabilities, multitenant, reusable, usedPorts);
        this.version = version;
        this.profile = profile;
    }

    public PeergreenServerVO(String name, String state, Map<String,String> capabilities, boolean multitenant,
            boolean reusable, List<Integer> usedPorts, String version, String profile) {
        super(name, state, capabilities, multitenant, reusable, usedPorts);
        this.version = version;
        this.profile = profile;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JonasVO[Id=").append(getId())
                .append(", name=").append(getName())
                .append(", state=").append(getState())
                .append(", version=").append(getVersion())
                .append(", profile=").append(getProfile())
                .append("]");
        return sb.toString();
    }


    /**
     * Change a Jonas Value Object into an Entity object
     * @return a Jonas Entity
     */
    public PeergreenServer createBean() {
        PeergreenServer peergreenServer = new PeergreenServer();
        super.mergeBean(peergreenServer);
        peergreenServer.setVersion(version);
        peergreenServer.setProfile(profile);
        return peergreenServer;
    }
}
