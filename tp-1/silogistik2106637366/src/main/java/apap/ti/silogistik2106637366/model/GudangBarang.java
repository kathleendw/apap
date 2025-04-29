package apap.ti.silogistik2106637366.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

import apap.ti.silogistik2106637366.model.Barang;
import apap.ti.silogistik2106637366.model.Gudang;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gudang_barang")

public class GudangBarang {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idGudangBarang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sku_barang")
    private Barang barang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gudang")
    private Gudang gudang;

    @NotNull
    @Column(name = "stok", nullable = false)
    private Integer stok;
}