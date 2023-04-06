package com.doitwell.group.springTesting.Rooms;

import com.doitwell.group.springTesting.Common.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Common.base_url+"/room")
public class RoomController {

    @Autowired
    private RoomsRepository repo;

    @GetMapping
    public List<Rooms> getAll(){
        return repo.findAll();
    }

    @GetMapping("{id}")
    public Rooms getOne(@PathVariable("id") Long id){
        return repo.findById(id).orElseThrow(()->{
            throw new IllegalStateException(String.format("No room with id %d was found",id));
        });
    }

    @PostMapping
    public Rooms create(@RequestBody Rooms room){
        Optional<Rooms> fRoom = repo.findByName(room.getName());
        if (fRoom.isPresent()) throw new IllegalStateException(String.format("A room with name %s already exist",room.getName()));
        repo.save(room);
        return room;
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id){
        Rooms room = repo.findById(id).orElseThrow(()->{
            throw new IllegalStateException(String.format("A room with id %d was not found",id));
        });
        repo.delete(room);
        return String.format("The room with id %d was deleted successfully",id);
    }

}
