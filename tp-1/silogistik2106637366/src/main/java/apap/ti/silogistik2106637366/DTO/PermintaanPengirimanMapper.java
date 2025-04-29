package apap.ti.silogistik2106637366.DTO;

import apap.ti.silogistik2106637366.DTO.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106637366.DTO.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106637366.model.PermintaanPengiriman;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);
    ReadPermintaanPengirimanResponseDTO permintaanPengirimanToReadPermintaanPengirimanResponseDTO(PermintaanPengiriman permintaanPengiriman);
}