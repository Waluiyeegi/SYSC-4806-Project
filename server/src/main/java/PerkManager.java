import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class PerkManager {

    @Autowired
    private PerkRepository perkRepository;

    public PerkManager()
    {

    }

    public List<Perk> getPerks(){
        return (List<Perk>) perkRepository.findAll();
    }

    public Perk savePerk(Perk perk){
        return perkRepository.save(perk);
    }

    public Perk getPerkByID(int id){
        Optional<Perk> perkOptional = perkRepository.findById(id);
        if (perkOptional.isPresent()) {
            return perkOptional.get();
        } else {
            throw new RuntimeException("Perk[" + id + "] not found");
        }

    }
}
