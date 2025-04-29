package apap.tutorial.bacabaca.restservice;

import apap.tutorial.bacabaca.model.Penerbit;
import apap.tutorial.bacabaca.repository.PenerbitDb;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class PenerbitRestServiceImpl implements PenerbitRestService{
    @Autowired
    private PenerbitDb penerbitDb;

    @Override
    public List<Penerbit> retrieveRestAllPenerbit() {
        return penerbitDb.findAll(); 
    }

    @Override
    public Penerbit getRestPenerbitById(long id) {
        return penerbitDb.findById(id).get();
    };

    @Override
    public void createRestPenerbit(Penerbit penerbit) { 
        penerbitDb.save(penerbit);
    }

    @Override
    public Penerbit updateRestPenerbit(Penerbit penerbitDTO) {
        Penerbit penerbit = getRestPenerbitById(penerbitDTO.getIdPenerbit());
        if (penerbit != null) {
            penerbit.setNamaPenerbit(penerbitDTO.getNamaPenerbit());
            penerbit.setAlamat(penerbitDTO.getAlamat());
            penerbit.setEmail(penerbitDTO.getEmail());
            penerbit.setListBuku(penerbitDTO.getListBuku());
            penerbitDb.save(penerbit);
        }
        return penerbit;
    }

    @Override
    public void deleteRestPenerbit(Penerbit penerbit){
        penerbit.setIsDeleted(true);
        penerbitDb.save(penerbit);
    }
}
