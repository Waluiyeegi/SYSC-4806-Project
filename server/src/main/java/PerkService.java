import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class PerkService {

    @Autowired
    private PerkRepository perkRepository;

    public Perk addPerk(Perk perk) {
        return perkRepository.save(perk);
    }

    public List<Perk> getAllPerks() {
        return (List<Perk>) perkRepository.findAll();
    }
}
