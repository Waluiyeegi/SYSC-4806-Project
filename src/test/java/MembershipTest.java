import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MembershipTest {
    @Test
    void testMembership() {
        Membership membership = new Membership("Costco", new ArrayList<Perk>());

        assertEquals("Costco", membership.getName());

        membership.addPerk(new Perk("10% off", new ArrayList<Restriction>(), 12345, new Product("Flights"), new Membership("Costco", new ArrayList<Perk>())));
        assertEquals(1, membership.getPerks().size());
    }
}