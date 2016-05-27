/*
 * ProActive Parallel Suite(TM): The Java(TM) library for
 *    Parallel, Distributed, Multi-Core Computing for
 *    Enterprise Grids & Clouds
 *
 * Copyright (C) 2013-2015 ActiveEon
 * 
 * Contact: proactive@ow2.org or contact@activeeon.com
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; version 3 of
 * the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
 * USA
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 *
 *  Initial developer(s):               The ProActive Team
 *                        http://proactive.inria.fr/team_members.htm
 *  Contributor(s):
 *
 * $$ACTIVEEON_INITIAL_DEV$$
 */

package org.ow2.proactive.procci.model.metamodel;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.ow2.proactive.procci.model.metamodel.constants.Attributes;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity is the abstract type that will gather the information contained in Resource and Link
 */
public abstract class Entity {
    @Getter
    private final URI id;
    @Getter
    @Setter
    private String title;
    @Getter
    private final Kind kind;
    @Getter
    @Setter
    private List<Mixin> mixins;

    /**
     * Minimal constructor
     *
     * @param url  is the user url
     * @param kind is the kind instance which uniquely identify the instance
     */
    public Entity(String url, Kind kind) {
        try {
            this.id = new URI("urn:" + url + ":" + UUID.randomUUID().toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Wrong URL given for the entity :" + url + "/");
        }
        this.kind = kind;
        this.title = "";
        this.mixins = new ArrayList<>();
    }

    /**
     * Constructor which set all parameters
     *
     * @param url    is the user url
     * @param kind   is the kind instance which uniquely identify the instance
     * @param title  is the display name of the instance
     * @param mixins are the mixins instance associate to the instance
     */
    public Entity(String url, Kind kind, String title, List<Mixin> mixins) {
        try {
            this.id = new URI("urn:" + url + ":" + UUID.randomUUID().toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Wrong URL given for the entity :" + url + "/");
        }
        this.kind = kind;
        this.title = title;
        this.mixins = mixins;
    }

    public static Set<Attribute> getAttributes() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(Attributes.ID);
        attributes.add(Attributes.ENTITY_TITLE);
        attributes.add(Attributes.KIND);
        attributes.add(Attributes.MIXINS);
        return attributes;
    }

}