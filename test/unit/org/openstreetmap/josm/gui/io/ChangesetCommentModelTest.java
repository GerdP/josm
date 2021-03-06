// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.gui.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.Test;
import org.openstreetmap.josm.testutils.JOSMTestRules;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Unit tests of {@link ChangesetCommentModel} class.
 */
class ChangesetCommentModelTest {

    /**
     * Setup tests
     */
    @RegisterExtension
    @SuppressFBWarnings(value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD")
    public JOSMTestRules test = new JOSMTestRules();

    /**
     * Test of {@link ChangesetCommentModel#findHashTags}.
     */
    @Test
    void testFindHashTags() {
        ChangesetCommentModel model = new ChangesetCommentModel();
        assertEquals(Collections.emptyList(), model.findHashTags());
        model.setComment(" ");
        assertEquals(Collections.emptyList(), model.findHashTags());
        model.setComment(" #");
        assertEquals(Collections.emptyList(), model.findHashTags());
        model.setComment(" # ");
        assertEquals(Collections.emptyList(), model.findHashTags());
        model.setComment(" https://example.com/#map ");
        assertEquals(Collections.emptyList(), model.findHashTags());
        model.setComment("#59606086");
        assertEquals(Collections.emptyList(), model.findHashTags());
        model.setComment(" #foo ");
        assertEquals(Arrays.asList("#foo"), model.findHashTags());
        model.setComment(" #foo #bar baz");
        assertEquals(Arrays.asList("#foo", "#bar"), model.findHashTags());
        model.setComment(" #foo, #bar, baz");
        assertEquals(Arrays.asList("#foo", "#bar"), model.findHashTags());
        model.setComment(" #foo; #bar; baz");
        assertEquals(Arrays.asList("#foo", "#bar"), model.findHashTags());
        model.setComment("#hotosm-project-4773 #DRONEBIRD #OsakaQuake2018 #AOYAMAVISION");
        assertEquals(Arrays.asList("#hotosm-project-4773", "#DRONEBIRD", "#OsakaQuake2018", "#AOYAMAVISION"), model.findHashTags());
    }
}
