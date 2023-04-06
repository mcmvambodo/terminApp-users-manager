package com.doitwell.group.springTesting.RDV;

import com.doitwell.group.springTesting.Boxes.Boxes;
import com.doitwell.group.springTesting.Boxes.BoxesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RDVService {

    @Autowired
    private RDVRepository repo;
    @Autowired
    private BoxesRepository boxesRepository;

    public List<RDV> getAll(){
        return repo.findAll();
    }

    public String createRDV(RDV rdv){
        repo.save(rdv);

        return "rdv create successfully";
    }

    public String cancelRDV(Long id){
        Optional<RDV> fRdv = repo.findById(id);
        if(fRdv.isEmpty()) throw new IllegalStateException(String.format("rdv with id %s not found",id));
        repo.delete(fRdv.get());

        return "rdv deleted successfully";
    }

    @Transactional
    public RDV callRDVtoBoxe(Long rdv_id, Long boxe_id){
        Optional<RDV> fRdv = repo.findById(rdv_id);
        Optional<Boxes> fBoxe = boxesRepository.findById(boxe_id);

        if (fRdv.isEmpty()) throw new IllegalStateException(String.format("rdv with id %d not found",rdv_id));
        if (fBoxe.isEmpty()) throw new IllegalStateException(String.format("boxe with id %d not found", boxe_id));

        RDV rdv = fRdv.get();
        Boxes boxe = fBoxe.get();

        //rdv.setBoxe(boxe);
        rdv.setStatus(1);

        repo.save(rdv);

        return rdv;
    }

    public void rdvComplete(){

    }

    @Transactional
    public String rdvChangeDate(Long rdv_id, LocalDateTime date){
        Optional<RDV> fRdv = repo.findById(rdv_id);
        if (fRdv.isEmpty()){
            throw new IllegalStateException("rdv not found with id"+rdv_id);
        }
        fRdv.get().setRdv_date(date);
        repo.save(fRdv.get());
        return "rdv with id "+rdv_id+" have been updated";
    }

}
