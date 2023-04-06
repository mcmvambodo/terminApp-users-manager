package com.doitwell.group.springTesting.Boxes;

import com.doitwell.group.springTesting.Rooms.Rooms;
import com.doitwell.group.springTesting.Rooms.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/boxe")
public class BoxeController {

    @Autowired
    private BoxesRepository repo;

    @Autowired
    private RoomsRepository roomsRepository;

    @GetMapping
    public List<Boxes> getAll(){
        return repo.findAll();
    }

    @GetMapping("{id}")
    private Boxes getOne(@PathVariable("id") Long id){
        Boxes boxe = repo.findById(id).orElseThrow(()->{
            throw new IllegalStateException(String.format("No boxe with id %d was found",id));
        });
        return boxe;
    }

    @PostMapping
    public Boxes create(@RequestBody Boxes boxe){

        if (Objects.isNull(boxe)) throw new NullPointerException();

        Optional<Boxes> fBoxe = repo.findByBoxeNumber(boxe.getBoxeNumber());
        if (fBoxe.isPresent()) throw new IllegalStateException(String.format("A boxe with number %d was found",boxe.getBoxeNumber()));

        boxe.setRoom(addOrCreateRoom(boxe.getRoom().getName()));
        repo.save(boxe);
        return boxe;
    }

    @DeleteMapping("{id}")
    public String deleteOne(@PathVariable("id") Long id){
        Optional<Boxes> boxe = repo.findById(id);
        if (boxe.isEmpty()) throw new IllegalStateException(String.format("No boxe with id %d was found",id));
        return String.format("boxe with id %d was deleted successfully",id);
    }

    private Rooms addOrCreateRoom(String name){
        Optional<Rooms> room = roomsRepository.findByName(name);
        return room.orElseGet(() -> roomsRepository.save(new Rooms(name)));
    }


}
