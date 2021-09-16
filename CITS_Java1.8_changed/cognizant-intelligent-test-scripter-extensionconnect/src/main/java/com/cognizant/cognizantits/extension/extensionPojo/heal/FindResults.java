/*
 * Copyright 2014 - 2017 Cognizant Technology Solutions
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
 */
package com.cognizant.cognizantits.extension.extensionPojo.heal;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "pageName",
    "objectname",
    "exist",
    "action"
})
public class FindResults {

    @JsonProperty("pageName")
    private String pageName;
    @JsonProperty("objectname")
    private String objectname;
    @JsonProperty("exist")
    private String exist;
    @JsonProperty("action")
    private String action;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return The pageName
     */
    @JsonProperty("pageName")
    public String getPageName() {
        return pageName;
    }

    /**
     *
     * @param pageName The pageName
     */
    @JsonProperty("pageName")
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    /**
     *
     * @return The objectname
     */
    @JsonProperty("objectname")
    public String getObjectname() {
        return objectname;
    }

    /**
     *
     * @param objectname The objectname
     */
    @JsonProperty("objectname")
    public void setObjectname(String objectname) {
        this.objectname = objectname;
    }

    /**
     *
     * @return The exist
     */
    @JsonProperty("exist")
    public String getExist() {
        return exist;
    }

    /**
     *
     * @param exist The exist
     */
    @JsonProperty("exist")
    public void setExist(String exist) {
        this.exist = exist;
    }

    /**
     *
     * @return The action
     */
    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    /**
     *
     * @param action The action
     */
    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
