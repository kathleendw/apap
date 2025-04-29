package apap.tutorial.bacabaca.restcontroller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import apap.tutorial.bacabaca.DTO.PenerbitMapper;
import apap.tutorial.bacabaca.DTO.request.CreatePenerbitRequestDTO;
import apap.tutorial.bacabaca.DTO.request.UpdatePenerbitRequestDTO;
import apap.tutorial.bacabaca.restservice.PenerbitRestService;
import reactor.core.publisher.Mono;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import apap.tutorial.bacabaca.model.Penerbit;

@RestController
@RequestMapping("/api")

public class PenerbitRestController {
    @Autowired
    private PenerbitRestService penerbitRestService;

    @Autowired
    private PenerbitMapper penerbitMapper;

    @GetMapping(value= "/penerbit/view-all")
    private List<Penerbit> retrieveAllPenerbit(){
        return penerbitRestService.retrieveRestAllPenerbit();
    }

    @GetMapping(value= "/penerbit/{id}")
    private Penerbit retrievePenerbit(@PathVariable("id") String id){
        try {
            return penerbitRestService.getRestPenerbitById(Long.parseLong(id));
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Penerbit " + id + " not found");
        }
    }

    @PostMapping(value = "/penerbit")
    public Penerbit restAddPenerbit(@Valid @RequestBody CreatePenerbitRequestDTO penerbitDTO, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else  {
            var penerbit = penerbitMapper.createPenerbitRequestDTOToPenerbit(penerbitDTO);
            penerbitRestService.createRestPenerbit(penerbit);
            return penerbit;
        }
    }

    @RequestMapping(value= "/penerbit/{id}", method = RequestMethod.PUT)
    public Penerbit restUpdatePenerbit(@PathVariable("id") long id, @Valid @RequestBody UpdatePenerbitRequestDTO penerbitDTO, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else  {
            penerbitDTO.setIdPenerbit(id);
            var penerbit = penerbitMapper.updatePenerbitRequestDTOToPenerbit(penerbitDTO);
            penerbitRestService.updateRestPenerbit(penerbit);
            return penerbit;
        }
    }

    @DeleteMapping(value= "/penerbit/{id}")
    private String restDeletePenerbit(@PathVariable("id") String id){
        try {
            var penerbit = penerbitRestService.getRestPenerbitById(Long.parseLong(id));
            penerbitRestService.deleteRestPenerbit(penerbit);
            return "Penerbit has been deleted";
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Penerbit " + id + " not found");
        }
    }
}
