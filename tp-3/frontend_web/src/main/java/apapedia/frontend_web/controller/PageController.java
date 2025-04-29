package apapedia.frontend_web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import apapedia.frontend_web.restservice.UserRestService;
import apapedia.frontend_web.security.xml.Attributes;
import apapedia.frontend_web.security.xml.ServiceResponse;
import apapedia.frontend_web.setting.Setting;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {
    @Autowired
    private UserRestService userRestService;

    private WebClient webClient = WebClient.builder()
                    .codecs(configurer -> configurer.defaultCodecs()
                    .jaxb2Decoder(new Jaxb2XmlDecoder()))
                    .build();

    @GetMapping("/validate-ticket")
    public ModelAndView adminLoginSSO(
        @RequestParam(value = "ticket", required = false) String ticket, HttpServletRequest request)
    {
        var uri1 = Setting.SERVER_VALIDATE_TICKET + "ticket=" + ticket + "&service=" + Setting.CLIENT_LOGIN;
        // System.out.println(String.format("%sticket=%s&%service=%s",
        //         Setting.SERVER_VALIDATE_TICKET,
        //         ticket,
        //         Setting.CLIENT_LOGIN
        //     ));
        ServiceResponse serviceResponse = this.webClient.get().uri(uri1).retrieve().bodyToMono(ServiceResponse.class).block();

        
        Attributes attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
        String username = serviceResponse.getAuthenticationSuccess().getUser();
        
        Authentication authentication = new UsernamePasswordAuthenticationToken(username,"webadmin", null);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        
        String name = attributes.getNama();
        var token = userRestService.getToken(username, name);

        if(token == null){
            return new ModelAndView("redirect:/seller/register");
        }
        
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
        httpSession.setAttribute("token", token);
        httpSession.setAttribute("jwtToken", token);
        
        return new ModelAndView("redirect:/catalog");
    }

    @GetMapping("/login-sso")
    public ModelAndView loginSSO(){
        return new ModelAndView("redirect:" + Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
    }

    @GetMapping("/logout-sso")
    public ModelAndView logoutSSO(Principal principal){
        return new ModelAndView("redirect:" + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
    }

    @GetMapping("/profile")
    public String viewProfile(){
        return "profile-page";
    }

}

