package org.ow2.proactive.procci.fixtures;

import org.ow2.proactive.procci.model.User;

/**
 * Created by Iaroslav on 4/29/2016.
 */
public class UserFixture {

    public static User simpleUser() {
        return new User("Marco", 18, 70000);
    }
}