package apap.tutorial.bacabaca.restservice;

import apap.tutorial.bacabaca.model.Penerbit;
import java.util.List;

public interface PenerbitRestService {
    List<Penerbit> retrieveRestAllPenerbit();
    Penerbit getRestPenerbitById(long id);
    void createRestPenerbit(Penerbit penerbit);
    Penerbit updateRestPenerbit(Penerbit penerbit);
    void deleteRestPenerbit(Penerbit penerbit);
}
