package apap.ti.silogistik2106637366.DTO;

import apap.ti.silogistik2106637366.DTO.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106637366.DTO.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106637366.DTO.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106637366.model.Barang;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);
    ReadBarangResponseDTO barangToReadBarangResponseDTO(Barang barang);
    Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);
    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang);
}