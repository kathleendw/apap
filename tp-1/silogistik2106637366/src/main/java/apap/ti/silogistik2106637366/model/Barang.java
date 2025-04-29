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
import apap.ti.silogistik2106637366.model.PermintaanPengirimanBarang;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "barang")

public class Barang {
    @Id
    @NotNull
    @Column(name = "sku_barang", nullable = false)
    private String skuBarang;

    @NotNull
    @Column(name = "tipe_barang", nullable = false)
    private Integer tipeBarang;

    @NotNull
    @Column(name = "merk_barang", nullable = false)
    private String merkBarang;

    @NotNull
    @Column(name = "harga_barang", nullable = false)
    private long hargaBarang;

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<GudangBarang> listGudangBarang;

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PermintaanPengirimanBarang> permintaanPengirimanBarang;
}