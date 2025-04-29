package apap.ti.silogistik2106637366.DTO;

import apap.ti.silogistik2106637366.DTO.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106637366.model.Karyawan;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO createKaryawanRequestDTO);
}