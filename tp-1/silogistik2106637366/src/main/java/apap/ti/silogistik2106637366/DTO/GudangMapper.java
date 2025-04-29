package apap.ti.silogistik2106637366.DTO;

import apap.ti.silogistik2106637366.DTO.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106637366.DTO.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106637366.DTO.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106637366.model.Gudang;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);
    ReadGudangResponseDTO gudangToReadGudangResponseDTO(Gudang gudang);
    Gudang updateGudangRequestDTOToGudang(UpdateGudangRequestDTO updateGudangRequestDTO);
}