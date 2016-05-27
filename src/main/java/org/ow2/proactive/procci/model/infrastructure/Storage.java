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


package org.ow2.proactive.procci.model.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ow2.proactive.procci.model.infrastructure.action.StorageAction;
import org.ow2.proactive.procci.model.infrastructure.constants.Attributes;
import org.ow2.proactive.procci.model.infrastructure.constants.InfrastructureKinds;
import org.ow2.proactive.procci.model.infrastructure.state.StorageState;
import org.ow2.proactive.procci.model.metamodel.Attribute;
import org.ow2.proactive.procci.model.metamodel.Kind;
import org.ow2.proactive.procci.model.metamodel.Link;
import org.ow2.proactive.procci.model.metamodel.Mixin;
import org.ow2.proactive.procci.model.metamodel.Resource;
import com.google.common.collect.ImmutableList;
import lombok.Getter;

/**
 * The Storage represent resources that record information to a data storage device
 */
public class Storage extends Resource {

    @Getter
    private final Float size;
    @Getter
    private StorageState state;

    @Getter
    private ImmutableList<StorageAction> actions;


    /**
     * Constructor
     *
     * @param url     is the user url
     * @param kind    is the kind which identify the class type
     * @param title   is the display name of the instance
     * @param mixins  are the mixins instance associate to the instance
     * @param summary is the summary of the resource instance
     * @param links   is a set of the Link compositions
     * @param size    is the storage size in GigaBytes
     * @param state   is the state aimed by the user or the current state
     * @param actions are the actions which will be applied to the Storage instance
     */
    private Storage(String url, Kind kind,
            String title, List<Mixin> mixins,
            String summary, List<Link> links, Float size, StorageState state, List<StorageAction> actions) {

        super(url, kind,
                title, mixins, summary, links);
        setAttributes();
        this.size = size;
        this.state = state;
        this.actions = new ImmutableList.Builder<StorageAction>().addAll(actions).build();
    }

    public static class Builder {
        private final String url;
        private final Float size;
        private String title;
        private String summary;
        private StorageState state;
        private List<Mixin> mixins;
        private List<Link> links;
        private List<StorageAction> actions;

        public Builder(String url, Float size) {
            this.url = url;
            this.size = size;
            this.title = "";
            this.summary = "";
            this.state = null;
            this.mixins = new ArrayList<>();
            this.links = new ArrayList<>();
            this.actions = new ArrayList<>();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder state(StorageState state) {
            this.state = state;
            return this;
        }

        public Builder addMixin(Mixin mixin) {
            this.mixins.add(mixin);
            return this;
        }

        public Builder addLink(Link link) {
            this.links.add(link);
            return this;
        }

        public Builder addAction(StorageAction action) {
            this.actions.add(action);
            return this;
        }

        public Storage build() {
            return new Storage(url, InfrastructureKinds.STORAGE, title, mixins, summary, links, size, state,
                    actions);
        }
    }

    public String getMessage() {
        return state.getMessage();
    }

    private static Set<Attribute> setAttributes() {
        Set<Attribute> attributes = Resource.getAttributes();
        attributes.add(Attributes.SIZE);
        attributes.add(Attributes.STORAGE_STATE);
        attributes.add(Attributes.STORAGE_MESSAGE);
        return attributes;
    }

}