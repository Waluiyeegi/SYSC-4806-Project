import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/perks")
public class PerkViewController {

    @Autowired
    private PerkManager perkManager;

    // Endpoint to get perk details by ID
    @GetMapping("/{id}")
    public Perk getPerkById(@PathVariable Integer id) {
        return perkManager.getPerkById(id);
    }
}