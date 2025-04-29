package apapedia.user.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import apapedia.user.dto.UserMapper;
import apapedia.user.dto.auth.RegisterRequest;
import apapedia.user.dto.request.UpdateBalanceRequest;
import apapedia.user.dto.request.UpdateUserRequestDTO;
import apapedia.user.dto.response.CustomerResponse;
import apapedia.user.dto.response.UpdateBalanceResponse;
import apapedia.user.restservice.UserRestService;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
    @Autowired
    UserRestService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createSeller(@Valid @RequestBody RegisterRequest customerDTO, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }

        var customer = userMapper.createUserRequestDTOToCustomer(customerDTO);
        userService.createRestCustomer(customer);
        var customerResponse = userMapper.customerToCustomerResponseDTO(customer);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/retrieve/{idUser}")
    // @PreAuthorize("hasAuthority('ROLE_CUSTOMER')") 
    public ResponseEntity<CustomerResponse> retrieveCustomer(@PathVariable("idUser") UUID idCustomer){
        var customer = userService.getCustomer(idCustomer);
        var customerResponse = userMapper.customerToCustomerResponseDTO(customer);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/retrieve-by-username/{username}")
    // @PreAuthorize("hasAuthority('ROLE_CUSTOMER')") 
    public ResponseEntity<CustomerResponse> retrieveCustomerByUsername(@PathVariable("username") String username){
        var customer = userService.getCustomerByUsername(username);
        var customerResponse = userMapper.customerToCustomerResponseDTO(customer);
        return ResponseEntity.ok(customerResponse);
    }

    @PutMapping("/update-balance")
    public ResponseEntity<UpdateBalanceResponse> topup(
                            @Valid @RequestBody UpdateBalanceRequest topUpRequest,
                            BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        UpdateBalanceResponse response = userService.updateBalance(topUpRequest);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value="/edit/{idUser}", method = RequestMethod.PUT)
    public ResponseEntity<CustomerResponse> restUpdateCustomer(@PathVariable("idUser") UUID idCustomer, @Valid @RequestBody UpdateUserRequestDTO customerDTO, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            customerDTO.setIdUser(idCustomer);
            var customer = userMapper.updateUserRequestDTOToCustomer(customerDTO);
            var customerFromDto = userService.getCustomer(idCustomer);
            customer.setUpdatedAt(LocalDateTime.now());
            customer.setCreatedAt(customerFromDto.getCreatedAt());
            customer.setBalance(customerFromDto.getBalance());
            customer.setCartId(customerFromDto.getCartId());
            userService.updateRestCustomer(customer);
            customer.setPassword(encoder.encode(customer.getPassword()));
            var customerResponse = userMapper.customerToCustomerResponseDTO(customer);
            return ResponseEntity.ok(customerResponse);
        }
    }
}
