package rideshare.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = {"*"})
public class Cont {
    @Autowired
    private Repos repository;
    /*
    @GetMapping("/car/{id}")
    public Car getbyid(@PathVariable("id") String id) {

        return repository.findByCarId(Long.parseLong(id));
    }*/

    @GetMapping("/car/all")
    public List<Car> get() {
        return repository.findAll();
    }

    @PostMapping("/car/postcar")
    public List<Car> post(@RequestBody Car car) {
        repository.save(car);
        return repository.findAll();
    }
}