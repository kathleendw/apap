// package apapedia.order.restservice;

// import java.util.HashSet;
// import java.util.Set;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;
// import org.springframework.http.ResponseEntity;
// import org.springframework.http.HttpMethod;
// import org.springframework.core.ParameterizedTypeReference;
// import org.springframework.web.client.RestTemplate;
// import apapedia.order.dto.response.CustomerResponseDTO;

// @Service
// public class UserDetailsServiceImpl implements UserDetailsService {

//     @Autowired
//     private PasswordEncoder encoder;

//     private RestTemplate restTemplate = new RestTemplate();

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         String url = "http://localhost:8082/api/customer/retrieve-by-username/" + username;
//         ResponseEntity<CustomerResponseDTO> response = restTemplate.exchange(
//             url,
//             HttpMethod.GET,
//             null,
//             new ParameterizedTypeReference<CustomerResponseDTO>() {}
//         );
//         var user = response.getBody();
//         Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
//         grantedAuthoritySet.add(new SimpleGrantedAuthority(user.getRole().toString()));
//         return new User(user.getUsername(), user.getPassword(), grantedAuthoritySet);
//     }
    
// }
