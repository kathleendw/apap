package apapedia.order.dto;

import apapedia.order.dto.request.CreateCartRequestDTO;
import apapedia.order.dto.request.CreateCartItemRequestDTO;
import apapedia.order.dto.request.UpdateCartRequestDTO;
import apapedia.order.dto.request.UpdateCartItemRequestDTO;
import apapedia.order.dto.response.CartResponseDTO;
import apapedia.order.model.Cart;
import apapedia.order.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart createCartRequestDTOToCart(CreateCartRequestDTO createCartRequestDTO);
    CartResponseDTO cartToCartResponseDTO(Cart cart);
    CartItem createCartItemRequestDTOToCartItem(CreateCartItemRequestDTO createCartItemRequestDTO);
    CartItem updateCartItemRequestDTOToCartItem(UpdateCartItemRequestDTO updateCartItemRequestDTO);
    Cart updateCartRequestDTOToCart(UpdateCartRequestDTO updateCartRequestDTO);
}