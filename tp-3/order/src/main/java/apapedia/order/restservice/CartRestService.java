package apapedia.order.restservice;

import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import apapedia.order.model.*;
import apapedia.order.dto.response.CustomerResponseDTO;
import apapedia.order.repository.*;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartRestService {
    @Autowired
    private CartDb cartDb;

    @Autowired
    private CartItemDb cartItemDb;

    private RestTemplate restTemplate = new RestTemplate();

    public void createRestCart(Cart cart){
        cartDb.save(cart);
    }

    public Cart getCartById(UUID cartId){
        return cartDb.findCartByCartId(cartId);
    }

    public Cart getCartByUser(UUID userId){
        return cartDb.findCartByUserId(userId);
    }

    public CartItem getCartItem(UUID itemId){
        return cartItemDb.findCartItemByItemId(itemId);
    }

    public List<Cart> getAllCart() {
        return cartDb.findAll();
    }

    public Cart updateRestCart(Cart cartDTO) {
        Cart cart = getCartById(cartDTO.getCartId());
        if (cart != null){
            cart.setListCartItem(cartDTO.getListCartItem());
            cartDb.save(cart);
        }
        return cart;
    }

    public CartItem updateRestCartItem(CartItem cartItemDTO) {
        CartItem cartItem = getCartItem(cartItemDTO.getItemId());
        if (cartItem != null){
            cartItem.setQuantity(cartItemDTO.getQuantity());
            cartItemDb.save(cartItem);
        }
        return cartItem;
    }

    public void deleteRestCartItem(CartItem cartItem){
        cartItem.setIsDeleted(true);
        cartItemDb.save(cartItem);
    }

    public String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Skip "Bearer "
        }
        return null;
    }
    

}