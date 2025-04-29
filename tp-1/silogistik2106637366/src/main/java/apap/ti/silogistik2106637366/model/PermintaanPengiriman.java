package apap.ti.silogistik2106637366.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;
import java.util.List;
import apap.ti.silogistik2106637366.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106637366.model.Karyawan;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman")

public class PermintaanPengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPermintaanPengiriman;

    @NotNull
    @Column(name = "nomor_pengiriman", nullable = false, length = 16)
    private String nomorPengiriman;

    @NotNull
    @Column(name = "is_cancelled", nullable = false)
    private Boolean isCancelled  = Boolean.FALSE;

    @NotNull
    @Column(name = "nama_penerima", nullable = false)
    private String namaPenerima;

    @NotNull
    @Column(name = "alamat_penerima", nullable = false)
    private String alamatPenerima;

    @NotNull
    @Column(name = "tanggal_pengiriman", nullable = false)
    private Date tanggalPengiriman;

    @NotNull
    @Column(name = "biaya_pengiriman", nullable = false)
    private Integer biayaPengiriman;

    @NotNull
    @Column(name = "jenis_layanan", nullable = false)
    private Integer jenisLayanan;

    @NotNull
    @Column(name = "waktu_permintaan", nullable = false)
    private Date waktuPermintaan;

    @OneToMany(mappedBy = "permintaanPengiriman", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_karyawan")
    private Karyawan karyawan;

    public Boolean getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}