package apap.tutorial.celciusConverter.controller;

import apap.tutorial.celciusConverter.model.CelciusConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.regex.Pattern;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CelciusConverterController {
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    private String getCelciusConverterPage(String celcius, Model model){
        if (celcius.isBlank() || celcius.isEmpty() || !pattern.matcher(celcius).matches()){
            model.addAttribute("message", "Isi nilai celcius yang ingin di-convert dengan benar!");
        } else {
            final CelciusConverter celciusConverter = new CelciusConverter(Double.parseDouble(celcius));
            model.addAttribute("celciusConverter", celciusConverter);
        }
        return "celciusConverterPage.html";
    }

    @GetMapping(value = "/celcius-converter")
    public String celciusConverterRequestParam(@RequestParam(value = "celcius") String celcius, Model model){
        return getCelciusConverterPage(celcius, model);
    }

    @GetMapping(value = "/celcius-converter/{celcius}")
    public String celciusConverterWithPathVariable(@PathVariable(value = "celcius") String celcius, Model model){
        return getCelciusConverterPage(celcius, model);
    }
}
