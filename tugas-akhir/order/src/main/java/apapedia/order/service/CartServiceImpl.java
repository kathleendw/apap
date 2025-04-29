// package apapedia.order.service;

// import java.util.List;
// import java.util.UUID;

// import org.springframework.beans.factory.annotation.Autowired;

// import apapedia.order.model.Cart;
// import apapedia.order.repository.CartDb;
// import apapedia.order.repository.CartItemDb;

// public class CartServiceImpl implements CartService {

//     @Autowired
//     private CartDb cartDb;

//     @Autowired
//     private CartItemDb cartItemDb;

//     @Override
//     public List<Cart> getAllCart() {
//         return cartDb.findAll();
//     }

//     // @Override
//     // public Cart updateCart(Cart cartFromDTO) {
//     //     Cart cart = getCartById(cartFromDTO.getId());

//     //     if (cart != null) {
//     //         cart.setId(cartFromDTO.getId());
//     //         cart.setUserId(cartFromDTO.getUserId());
//     //         cart.setTotalPrice(cartFromDTO.getTotalPrice());
//     //         cartDb.save(cart);
//     //     }
//     //     return cart;
//     // }

//     @Override
//     public void saveCart(Cart cart) {
//         cartDb.save(cart);
//     }

//     @Override
//     public List<Cart> getAllCart() {
//         return cartDb.findAll();
//     }

//     @Override
//     public Cart getCartById(UUID id) {
//         for (Cart cart : getAllCart()) {
//             if (cart.getCartId() == id) {
//                 return cart;
//             }
//         }
//         return null;
//     }

//     @Override
//     public Cart updateCart(Cart cartFromDTO) {
//         Cart cart = getCartById(cartFromDTO.getCartId());
//         if (cart != null){
//             cart.setListCartItem(cartFromDTO.getListCartItem());
//             cartDb.save(cart);
//         }
//         return cart;
//     }
// }
