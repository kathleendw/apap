package apapedia.frontend_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "catalog")
public class Catalog {

    @Id
    private String UUID;

    @NotNull
    @Lob
    @Column(name = "fotoCatalog", nullable = false)
    private byte[] fotoCatalog;

    @NotNull
    @Column(name = "namaCatalog", nullable = false, unique = true)
    private String namaCatalog;

    @NotNull
    @Column(name = "kategoriCatalog", nullable = false)
    private Integer kategoriCatalog;

    @NotNull
    @Column(name = "deskripsiCatalog", nullable = false, unique = true)
    private String deskripsiCatalog;

    @NotBlank
    @Column(name = "stokCatalog", nullable = false)
    @Positive(message = "Stok tidak boleh negatif")
    private Integer stokCatalog;

    @NotNull
    @Column(name = "hargaCatalog", nullable = false)
    @Positive(message = "Harga tidak boleh negatif")
    private Long hargaCatalog;
}
