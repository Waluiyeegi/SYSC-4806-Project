import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.example.model.Perk;
import java.util.ArrayList;

class PerkTest {

    @Test
    public void PerkTest(){
        Perk perk = new Perk();

        String description = "Test Description";
        String name = "Test Name";
        String membership = "Test Membership";

        perk.addDescription(description);
        perk.setName(name);
        perk.setMembership(membership);

        assertEquals(perk.getDescription(), description);
        assertEquals(perk.getName(), name);
        assertEquals(perk.getMembership(), membership);
    }
}