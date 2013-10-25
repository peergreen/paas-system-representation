package org.ow2.jonas.jpaas.sr.model;


import java.util.HashMap;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.ow2.jonas.jpaas.sr.facade.vo.PeergreenServerVO;


/**
 * Define a Peergreen Server
 * @author Florent Benoit
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PeergreenServer extends PaasContainer implements java.io.Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -5217817593098592798L;

    /**
     * Version.
     */
    private String version;

    /**
     * Profile of the Jonas.
     */
    private String profile;


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


    public PeergreenServerVO createVO() {
        PeergreenServerVO peergreenServerVO = new PeergreenServerVO(getId(), getName(), getState(), new HashMap<String,String>(getCapabilities()),
                isMultitenant(), isReusable(), new LinkedList<Integer>(getUsedPorts()), getVersion(), profile);
        updatePaasContainerVO(peergreenServerVO);
        return peergreenServerVO;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PeergreenServer[Id=").append(getId())
                .append(", name=").append(getName())
                .append(", state=").append(getState())
                .append(", version=").append(getVersion())
                .append(", profile=").append(getProfile())
                .append("]");
        return sb.toString();
    }

    public void mergePeergreenServerVO(PeergreenServerVO peergreenServerVO) {
        super.mergePaasContainerVO(peergreenServerVO);
        setVersion(peergreenServerVO.getVersion());
        setProfile(peergreenServerVO.getProfile());
    }

}
