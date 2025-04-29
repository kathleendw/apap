package apapedia.user.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import apapedia.user.dto.UserMapper;
import apapedia.user.dto.auth.RegisterSellerRequest;
import apapedia.user.dto.request.UpdateBalanceRequest;
import apapedia.user.dto.request.UpdateUserRequestDTO;
import apapedia.user.dto.response.SellerResponse;
import apapedia.user.dto.response.UpdateBalanceResponse;
import apapedia.user.restservice.UserRestService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/seller")
public class SellerRestController {
    
    @Autowired
    UserRestService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/create")
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

    @GetMapping("/retrieve/{idUser}")
    // @PreAuthorize("hasAuthority('ROLE_SELLER')") 
    public ResponseEntity<SellerResponse> retrieveSeller(@PathVariable("idUser") UUID idSeller){
        var seller = userService.getSeller(idSeller);
        var sellerResponse = userMapper.sellerToSellerResponseDTO(seller);
        System.out.println("Ini retrieve user" + sellerResponse.getBalance());
        return ResponseEntity.ok(sellerResponse);
    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<String> deleteUser(@PathVariable("idUser") UUID idSeller){
        var seller = userService.getSeller(idSeller);
        userService.deleteUser(seller);
        var body = "User berhasil dihapus";
        return ResponseEntity.ok(body);
    }

    @PutMapping("/update-balance")
    public ResponseEntity<UpdateBalanceResponse> withdraw( 
                            @Valid @RequestBody UpdateBalanceRequest withdrawRequest,
                            BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        
        UpdateBalanceResponse response = userService.updateBalance(withdrawRequest);
        return ResponseEntity.ok(response);
    }
    
    @RequestMapping(value="/edit/{idUser}", method = RequestMethod.PUT)
    public ResponseEntity<SellerResponse> restUpdateSeller(@PathVariable("idUser") UUID idSeller, @Valid @RequestBody UpdateUserRequestDTO sellerDTO, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            sellerDTO.setIdUser(idSeller);
            var seller = userMapper.updateUserRequestDTOToSeller(sellerDTO);
            var sellerFromDto = userService.getSeller(idSeller);
            seller.setUpdatedAt(LocalDateTime.now());
            seller.setCreatedAt(sellerFromDto.getCreatedAt());
            seller.setBalance(sellerFromDto.getBalance());
            seller.setCategory(sellerFromDto.getCategory());
            userService.updateRestSeller(seller);
            seller.setPassword(encoder.encode(seller.getPassword()));
            var sellerResponse = userMapper.sellerToSellerResponseDTO(seller);
            return ResponseEntity.ok(sellerResponse);
        }
    }
}
