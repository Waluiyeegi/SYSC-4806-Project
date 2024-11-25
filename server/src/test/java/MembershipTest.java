import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.example.model.Perk;
import com.example.model.Membership;
import com.example.model.User;

import java.util.ArrayList;

class MembershipTest {

    @Test
    public void MembershipTest(){
        String name = "Test Name";
        User user = new User();
        Perk perk = new Perk();

        Membership membership = new Membership();

        membership.setName(name);
        membership.setPerk(perk);
        membership.setUser(user);

        assertEquals(membership.getName(), name);
        assertEquals(membership.getPerks().get(0), perk);
        assertEquals(membership.getUser(), user);
    }
}