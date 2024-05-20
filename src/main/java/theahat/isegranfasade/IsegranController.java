package theahat.isegranfasade;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class IsegranController {
    private final ProduktRepository repository;
    public IsegranController(ProduktRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping(path = "/produkter")
    public String produkter(Model model) {
        List<Produkt> alleProdukter = repository.findAll();
        model.addAttribute("produkter", alleProdukter);
        return "produkter";
    }
    @GetMapping(path = "/produkter/{id}")
    public String produkt(Model model, @PathVariable Long id){
        Optional<Produkt> first = repository.findById(id);
        model.addAttribute("produkt", first.get());
        return "produkt-side";
    }
}
