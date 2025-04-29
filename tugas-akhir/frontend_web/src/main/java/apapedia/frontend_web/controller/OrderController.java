package apapedia.frontend_web.controller;

import java.util.List;
import java.util.UUID;

import apapedia.frontend_web.service.JwtService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import apapedia.frontend_web.service.OrderService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/order/seller")
    public String listSellerOrder(Model model, HttpSession httpSession){
        String jwtToken = (String) httpSession.getAttribute("token");

        String url;

        if (jwtService.getRoleFromJwtToken(jwtToken).equals("SELLER")) {
            String sellerId = jwtService.getIdFromJwtToken(jwtToken);
            System.out.println(sellerId);
            if (sellerId != null) {
                url = "http://localhost:8083/api/order/seller/" + sellerId;
            } else {
                return "redirect:/catalog";
            }
        } else {
            return "redirect:/catalog";
        }

        System.out.println(url);

        List listOrder = restTemplate.getForObject(url, List.class);

        model.addAttribute("listOrder", listOrder);
        model.addAttribute("userId", jwtService.getIdFromJwtToken(jwtToken));

        return "order-history-page";
    }

    @GetMapping("/order/{orderId}")
    public String updateOrder(@PathVariable("orderId") UUID orderId, Model model){
        var response = orderService.updateOrder(orderId);

        return "redirect:/order/seller/" + response.getSeller();
    }
}
