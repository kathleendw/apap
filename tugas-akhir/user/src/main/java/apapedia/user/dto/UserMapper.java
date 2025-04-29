package apapedia.user.dto;

import org.mapstruct.Mapper;

import apapedia.user.dto.response.UserResponseDTO;
import apapedia.user.dto.auth.RegisterRequest;
import apapedia.user.dto.auth.RegisterSellerRequest;
import apapedia.user.dto.request.*;
import apapedia.user.dto.response.CustomerResponse;
import apapedia.user.dto.response.SellerResponse;
import apapedia.user.model.Customer;
import apapedia.user.model.Seller;
import apapedia.user.model.UserApapedia;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserApapedia createUserRequestDTOToUser(RegisterRequest createUserRequestDTO);
    UserResponseDTO userToUserResponseDTO(UserApapedia user);

    Seller createUserRequestDTOToSeller(RegisterSellerRequest createUserRequestDTO);
    SellerResponse sellerToSellerResponseDTO(Seller seller);

    Customer createUserRequestDTOToCustomer(RegisterRequest customerResponseDTO);
    CustomerResponse customerToCustomerResponseDTO(Customer customer);

    UserApapedia updateUserRequestDTOToUser(UpdateUserRequestDTO updateUserRequestDTO);

    Seller updateUserRequestDTOToSeller(UpdateUserRequestDTO updateUserRequestDTO);

    Customer updateUserRequestDTOToCustomer(UpdateUserRequestDTO updateUserRequestDTO);
}
