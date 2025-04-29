package apap.tutorial.bacabaca.DTO;

import apap.tutorial.bacabaca.DTO.request.CreateBukuRequestDTO;
import apap.tutorial.bacabaca.DTO.request.UpdateBukuRequestDTO;
import apap.tutorial.bacabaca.DTO.response.ReadBukuResponseDTO;
import apap.tutorial.bacabaca.model.Buku;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BukuMapper {

    Buku createBukuRequestDTOToBuku(CreateBukuRequestDTO createBukuRequestDTO);
    @AfterMapping
    default void judulLowerCreate(CreateBukuRequestDTO createBukuRequestDTO, @MappingTarget Buku buku){
        buku.setJudulLower(createBukuRequestDTO.getJudul().toLowerCase());
    }

    Buku updateBukuRequestDTOToBuku(UpdateBukuRequestDTO updateBukuRequestDTO);
    @AfterMapping
    default void judulLowerUpdate(UpdateBukuRequestDTO updateBukuRequestDTO, @MappingTarget Buku buku){
        buku.setJudulLower(updateBukuRequestDTO.getJudul().toLowerCase());
    }

    UpdateBukuRequestDTO bukuToUpdateBukuRequestDTO(Buku buku);

    @Mapping(target = "namaPenerbit", ignore = true)
    ReadBukuResponseDTO bukuToReadBukuResponseDTO(Buku buku);

    @AfterMapping
    default void setNamaPenerbit(Buku buku, @MappingTarget ReadBukuResponseDTO readBukuResponseDTO){
        if (buku.getPenerbit() != null && buku != null){
            readBukuResponseDTO.setNamaPenerbit(buku.getPenerbit().getNamaPenerbit());
        } else {
            readBukuResponseDTO.setNamaPenerbit("-");
        }
    }

}