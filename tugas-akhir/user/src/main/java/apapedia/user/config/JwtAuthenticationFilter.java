// package apapedia.user.config;

// import java.io.IOException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import apapedia.user.restservice.JwtService;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     @Autowired
//     JwtService jwtService;

//     @Autowired
//     UserDetailsService userDetailsService;
    
//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         String authHeader = request.getHeader("Authorization"); 
//         String jwtToken = null; 
//         String username = null; 

//         if (authHeader != null && authHeader.startsWith("Bearer")) { 
//             jwtToken = authHeader.substring(7); 
//             username = jwtService.extractUsername(jwtToken); 
//         } else {
//             filterChain.doFilter(request, response);
//             return;
//         }
  
//         if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { 
//             UserDetails userDetails = userDetailsService.loadUserByUsername(username); 

//             if (jwtService.validateToken(jwtToken, userDetails)) { 
//                 UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); 
//                 token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
//                 SecurityContextHolder.getContext().setAuthentication(token); 
//             } 
//         } 
//         filterChain.doFilter(request, response); 
//     }


    
// }
