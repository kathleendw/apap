package apap.tutorial.bacabaca.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import apap.tutorial.bacabaca.model.UserModel;
import apap.tutorial.bacabaca.repository.UserDb;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserDb userDb;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserModel user = userDb.findByUsername(username);
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority(user.getRole().getRole()));
        return new User(user.getUsername(), user.getPassword(), grantedAuthoritySet);
    }
}
