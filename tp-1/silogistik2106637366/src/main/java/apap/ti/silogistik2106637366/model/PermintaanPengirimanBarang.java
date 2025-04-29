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
import apap.ti.silogistik2106637366.model.PermintaanPengiriman;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman_barang")

public class PermintaanPengirimanBarang {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPermintaanPengirimanBarang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sku_barang")
    private Barang barang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_permintaan_pengiriman")
    private PermintaanPengiriman permintaanPengiriman;

    @NotNull
    @Column(name = "kuantitas", nullable = false)
    private Integer kuantitas;
}