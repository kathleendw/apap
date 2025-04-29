package apapedia.user.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apapedia.user.dto.UserMapper;
import apapedia.user.dto.auth.LoginJwtRequest;
import apapedia.user.dto.auth.LoginRequest;
import apapedia.user.dto.auth.LoginResponse;
import apapedia.user.dto.auth.RegisterRequest;
import apapedia.user.dto.auth.RegisterSellerRequest;
import apapedia.user.dto.response.CustomerResponse;
import apapedia.user.dto.response.SellerResponse;
import apapedia.user.restservice.UserRestService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/authentication")
public class AuthController {
    // @Autowired
    // JwtService jwtService;

    // @Autowired
    // AuthenticationManager authenticationManager;

    @Autowired
    UserRestService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        var loginResponse = userService.login(request);

        var response = ResponseEntity.ok()
                .header("Authorization", "Bearer " + loginResponse.getJwtToken())
                .body(loginResponse);

        System.out.println("Sudah logged in ->" + loginResponse.getJwtToken());
        return response;
    }

    @PostMapping("/login-jwt-seller")
    public ResponseEntity<LoginResponse> loginJwtSeller(@Valid @RequestBody LoginJwtRequest request, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        var loginResponse = userService.loginJwtSeller(request);

        var response = ResponseEntity.ok()
                .body(loginResponse);

        System.out.println("Sudah logged in -->"+ response);
        return response;
    }

    @PostMapping("/create/seller")
    public ResponseEntity<SellerResponse> createSeller(@Valid @RequestBody RegisterSellerRequest sellerDTO, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }

        var seller = userMapper.createUserRequestDTOToSeller(sellerDTO);
        userService.createRestSeller(seller);
        var sellerResponse = userMapper.sellerToSellerResponseDTO(seller);
        return ResponseEntity.ok(sellerResponse);
    }

    @PostMapping("/create/customer")
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody RegisterRequest customerDTO, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        try {
            var customer = userMapper.createUserRequestDTOToCustomer(customerDTO);
            userService.createRestCustomer(customer);
            var customerResponse = userMapper.customerToCustomerResponseDTO(customer);
            return ResponseEntity.ok(customerResponse);
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nama atau username yang Anda daftarkan sudah terdaftar.");
        }
    }
}
