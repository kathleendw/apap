package apapedia.user.restservice;

import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apapedia.user.config.JwtUtils;
import apapedia.user.dto.auth.LoginJwtRequest;
import apapedia.user.dto.auth.LoginRequest;
import apapedia.user.dto.auth.LoginResponse;
import apapedia.user.dto.request.UpdateBalanceRequest;
import apapedia.user.dto.response.UpdateBalanceResponse;
import apapedia.user.model.Customer;
import apapedia.user.model.ERole;
import apapedia.user.model.Seller;
import apapedia.user.model.UserApapedia;
import apapedia.user.repository.CustomerDb;
import apapedia.user.repository.SellerDb;
import apapedia.user.repository.UserDb;

import jakarta.transaction.Transactional;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus; 
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@Transactional
public class UserRestService {
    @Autowired
    private SellerDb sellerDb;

    @Autowired
    private CustomerDb customerDb;

    @Autowired
    private UserDb userDb;

    @Autowired 
    JwtUtils jwtUtils;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authManager;

    public LoginResponse login(LoginRequest request){
        var user = userDb.findByUsername(request.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + request.getUsername());
        }
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(), 
                request.getPassword())
        );
        if(authentication.isAuthenticated()){
            var jwtToken = jwtUtils.generateJwtToken(user);
            return new LoginResponse(jwtToken);
        } else {
            throw new UsernameNotFoundException("Username atau password Anda salah!");
        }
        
    }

    public LoginResponse loginJwtSeller(LoginJwtRequest request){
        var user = userDb.findByUsername(request.getUsername());

        if(user == null){
            return new LoginResponse();
        }
        var jwtToken = jwtUtils.generateJwtToken(user);
            return new LoginResponse(jwtToken);
        
    }

    public UserApapedia findByUsername(String username){
        return userDb.findByUsername(username);
    }

    public Customer getCustomerByUsername(String username){
        return customerDb.findByUsername(username);
    }

    public void createRestSeller(Seller seller){
        seller.setPassword(encoder.encode(seller.getPassword()));
        seller.setCreatedAt(LocalDateTime.now());
        seller.setUpdatedAt(LocalDateTime.now());
        seller.setRole(ERole.SELLER);
        sellerDb.save(seller);
    }

    public Seller getSeller(UUID idSeller){
        return sellerDb.findSellerByIdUser(idSeller);
    }

    public void createRestCustomer(Customer customer){
        customer.setPassword(encoder.encode(customer.getPassword()));
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        customer.setRole(ERole.CUSTOMER);
        customerDb.save(customer);
    }

    public Customer getCustomer(UUID idSeller){
        return customerDb.findCustomerByIdUser(idSeller);
    }

    public void deleteUser(Seller user){
        userDb.delete(user);
    }

    public UpdateBalanceResponse updateBalance(UpdateBalanceRequest request){
        UserApapedia user = userDb.findByIdUser(request.getIdUser());
        user.setBalance(user.getBalance()+request.getMoney());
        userDb.save(user);
        var response = new UpdateBalanceResponse(request.getIdUser(), request.getMoney(), user.getBalance(), true);
        return response;
    }

    
    public List<Seller> getAllSeller() {
        return sellerDb.findAll(); 
    }

    public Seller updateRestSeller(Seller sellerDTO) {
        Seller seller = getSeller(sellerDTO.getIdUser());
        if (seller != null){
            if (!encoder.matches(sellerDTO.getPassword(), seller.getPassword())) {
                seller.setNameUser(sellerDTO.getNameUser());
                seller.setUsername(sellerDTO.getUsername());
                seller.setPassword(encoder.encode(sellerDTO.getPassword()));
                seller.setEmail(sellerDTO.getEmail());
                seller.setAddress(sellerDTO.getAddress());
                seller.setCreatedAt(sellerDTO.getCreatedAt());
                seller.setUpdatedAt(LocalDateTime.now());
                seller.setBalance(sellerDTO.getBalance());
                seller.setCategory(sellerDTO.getCategory());
                sellerDb.save(seller);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password must be different from the old password");
            }
        }
        return seller;
    }

    public List<Customer> getAllCustomer() {
        return customerDb.findAll(); 
    }

    public Customer updateRestCustomer(Customer customerDTO) {
        Customer customer = getCustomer(customerDTO.getIdUser());
        if (customer != null) {
            if (!encoder.matches(customerDTO.getPassword(), customer.getPassword())) {
                customer.setNameUser(customerDTO.getNameUser());
                customer.setUsername(customerDTO.getUsername());
                customer.setPassword(encoder.encode(customerDTO.getPassword()));
                customer.setEmail(customerDTO.getEmail());
                customer.setAddress(customerDTO.getAddress());
                customer.setCreatedAt(customerDTO.getCreatedAt());
                customer.setUpdatedAt(LocalDateTime.now());
                customer.setBalance(customerDTO.getBalance());
                customer.setCartId(customerDTO.getCartId());
                customerDb.save(customer);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password must be different from the old password");
            }
        }
        return customer;
    }


}