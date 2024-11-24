import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.model.Perk;
import com.example.repository.PerkRepository;

@RestController
@RequestMapping("/api/addNewPerk")
@CrossOrigin(origins = "http://localhost:5173")
public class PerkController {
    @Autowired
    private PerkRepository perkRepository;

    @PostMapping
    public void addNewPerk(@RequestBody Perk perk) {
//        if (perk.getProduct() == null || perk.getMembership() == null) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(perkRepository.save(perk));
    }
}