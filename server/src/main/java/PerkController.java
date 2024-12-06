<<<<<<< Updated upstream
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
=======
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/perks")
public class PerkController {

    @Autowired
    private PerkService perkService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadPerk(@RequestBody Perk perk) {
        try {
            Perk createdPerk = perkService.addPerk(perk);
            return ResponseEntity.ok(createdPerk);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPerks() {
        return ResponseEntity.ok(perkService.getAllPerks());
>>>>>>> Stashed changes
    }
}