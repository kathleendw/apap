package apap.ti.silogistik2106637366.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import apap.ti.silogistik2106637366.model.GudangBarang;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gudang")

public class Gudang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idGudang;

    @NotNull
    @Column(name = "nama_gudang", nullable = false, length = 255)
    private String namaGudang;

    @NotNull
    @Column(name = "alamat_gudang", nullable = false, length = 255)
    private String alamatGudang;

    @OneToMany(mappedBy = "gudang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<GudangBarang> listGudangBarang;

}