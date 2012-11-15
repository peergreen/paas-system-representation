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

package org.ow2.jonas.jpaas.sr.facade.vo;

import org.ow2.jonas.jpaas.sr.model.NodeTemplate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Define a NodeTemplate Value Object
 * @author David Richard
 */
public class NodeTemplateVO implements java.io.Serializable {

    /**
     * Id of the Node.
     */
    private String id;

    /**
     * TemplateId of the Node : id given in the template descriptor file.
     */
    private String templateId;

    /**
     * Name of the Node.
     */
    private String name;

    /**
     * Name of the node's configuration.
     */
    private String configurationName;

    /**
     * Requirements of the Node.
     */
    private List<String> requirements = new LinkedList<String>();

    /**
     * SlaEnforcement of the Node.
     */
    private Map<String,String> slaEnforcement = new HashMap<String, String>();

    /**
     * Minimum size of the Node.
     */
    private int minSize;

    /**
     * Maximum size of the Node.
     */
    private int maxSize;

    /**
     * Maximum size of the Node.
     */
    private int currentSize;

    public NodeTemplateVO() {
        this.requirements = new LinkedList<String>();
        this.slaEnforcement = new HashMap<String, String>();
    }

    public NodeTemplateVO(String id, String templateId, String name, String configurationName,
            List<String> requirements, Map<String,String> slaEnforcement,
            int minSize, int maxSize, int currentSize) {
        this.id = id;
        this.templateId = templateId;
        this.name = name;
        this.configurationName = configurationName;
        this.requirements = new LinkedList<String>(requirements);
        this.slaEnforcement = new HashMap<String, String>(slaEnforcement);
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.currentSize = currentSize;
    }

    public NodeTemplateVO(String templateId, String name, String configurationName,
            List<String> requirements, Map<String,String> slaEnforcement,
            int minSize, int maxSize, int currentSize) {
        this.templateId = templateId;
        this.name = name;
        this.configurationName = configurationName;
        this.requirements = new LinkedList<String>(requirements);
        this.slaEnforcement = new HashMap<String, String>(slaEnforcement);
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.currentSize = currentSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfigurationName() {
        return configurationName;
    }

    public void setConfigurationName(String configurationName) {
        this.configurationName = configurationName;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public Map<String,String> getSlaEnforcement() {
        return slaEnforcement;
    }

    public void setSlaEnforcement(Map<String,String> slaEnforcement) {
        this.slaEnforcement = slaEnforcement;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NodeTemplate[id=").append(getId())
                .append(", templateId=").append(getTemplateId())
                .append(", name=").append(getName())
                .append(", configurationName=").append(getConfigurationName())
                .append(", requirements=").append(getRequirements().toString())
                .append(", slaEnforcement=").append(getSlaEnforcement().toString())
                .append(", minSize=").append(getMinSize())
                .append(", maxSize=").append(getMaxSize())
                .append(", currentSize=").append(getCurrentSize())
                .append("]");
        return sb.toString();
    }

    /**
     * Change a NodeTemplate Value Object into an Entity object
     * @return a NodeTemplate Entity
     */
    public NodeTemplate createBean() {
        NodeTemplate nodeTemplate = new NodeTemplate();
        nodeTemplate.setId(id);
        nodeTemplate.setTemplateId(templateId);
        nodeTemplate.setName(name);
        nodeTemplate.setConfigurationName(configurationName);
        nodeTemplate.setRequirements(requirements);
        nodeTemplate.setSlaEnforcement(slaEnforcement);
        nodeTemplate.setMinSize(minSize);
        nodeTemplate.setMaxSize(maxSize);
        nodeTemplate.setCurrentSize(currentSize);
        return nodeTemplate;
    }
}
