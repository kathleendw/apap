package apap.ti.silogistik2106637366;

import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import java.util.Locale;

import apap.ti.silogistik2106637366.DTO.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106637366.DTO.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106637366.DTO.GudangMapper;
import apap.ti.silogistik2106637366.DTO.KaryawanMapper;
import apap.ti.silogistik2106637366.service.GudangService;
import apap.ti.silogistik2106637366.service.KaryawanService;

@SpringBootApplication
public class Silogistik2106637366Application {
	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106637366Application.class, args); 
	}

	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, KaryawanService karyawanService, GudangMapper gudangMapper, KaryawanMapper karyawanMapper){
		return args -> {
			var faker = new Faker(new Locale("in-ID"));
			var gudangDTO = new CreateGudangRequestDTO();
			gudangDTO.setNamaGudang(faker.company().name());
			gudangDTO.setAlamatGudang(faker.address().fullAddress());

			var karyawanDTO = new CreateKaryawanRequestDTO();
			karyawanDTO.setNamaKaryawan(faker.name().fullName());
			String jenis = faker.demographic().sex();
			int jenisKelamin = jenis.equals("Male") ? 1 : 2;
			karyawanDTO.setJenisKelamin(jenisKelamin);
			karyawanDTO.setTanggalLahir(faker.date().birthday());
				
			var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);
			karyawan = karyawanService.createKaryawan(karyawan);
			var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
			gudang = gudangService.createGudang(gudang);
		};
	}
}