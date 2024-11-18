import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PerkManager {

    private List<Perk> perks = new ArrayList<>();

    public PerkManager()
    {

    }

    public void addPerk(Perk perk)
    {
        perks.add(perk);
    }

    public void removePerk(String name)
    {
        Predicate<Perk> nameMatch = n -> n.getName().equals(name);
        perks.removeIf(nameMatch);
    }

    public String perksToString()
    {
        String out = "";
        for (Perk p : perks) {
            out += p.toString() + "" + "\n";
        }
        return out;
    }
}
