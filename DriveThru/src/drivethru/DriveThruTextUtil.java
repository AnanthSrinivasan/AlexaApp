/**
    Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package drivethru;

import java.util.Arrays;
import java.util.List;

/**
 * Util containing various text related utils.
 */
public final class DriveThruTextUtil {

    private DriveThruTextUtil() {
    }

    /**
     * List of player names blacklisted for this app.
     */
    private static final List<String> NAME_BLACKLIST = Arrays.asList("player", "players");

    /**
     * Text of complete help.
     */
    public static final String COMPLETE_HELP =
            "Here's some things you can say. Add John, give John 5 points, tell me the score, "
                    + "new game, reset, and exit.";

    /**
     * Text of next help.
     */
    public static final String NEXT_HELP = "You can give a player points, add a player, get the "
            + "current score, or say help. What would you like?";

}
