package theahat.isegranfasade;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class IsegranController {
    private List<Produkt> produkter = new ArrayList<>();

    public IsegranController() {
        Produkt markise = new Produkt("Markise", "Lorem ipsum bla bla bla", 199.90, "bilde1.jpeg", "3 uker");
        Produkt parasoll = new Produkt("Parasoll", "En veldig fin parasoll", 235.9, "bilde2.webp", "1 uke");
        produkter.add(markise);
        produkter.add(parasoll);
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping(path = "/produkter")
    public String produkter(Model model) {
        model.addAttribute("produkter", produkter);
        return "produkter";
    }
    @GetMapping(path = "/produkter/{title}")
    public String produkt(Model model, @PathVariable String title){
        Optional<Produkt> first = produkter.stream().filter(produkt -> produkt.title().equals(title)).findFirst();
        model.addAttribute("produkt", first.get());
        return "produkt-side";
    }
}
