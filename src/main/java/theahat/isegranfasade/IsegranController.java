package theahat.isegranfasade;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IsegranController {
    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping(path = "/produkter")
    public String produkter() {
        return "produkter";
    }
}
