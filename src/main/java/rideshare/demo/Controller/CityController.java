package rideshare.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rideshare.demo.Entity.City;
import rideshare.demo.Service.CityService;

import java.util.List;

@RestController
@CrossOrigin(value = {"*"})
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value = "/city/search", method = RequestMethod.GET)
    public ResponseEntity<?> search(
            @RequestParam String search,
            @RequestParam int limit
    ) {
        return new ResponseEntity<>(cityService.search(search, limit), HttpStatus.OK);
    }

    @RequestMapping(value = "/city/list", method = RequestMethod.GET)
    public ResponseEntity<List<City>> list() {
        return new ResponseEntity<>(cityService.list(), HttpStatus.OK);
    }

    @RequestMapping(value = "/city/generate", method = RequestMethod.POST)
    public ResponseEntity<?> generate(@RequestParam(value = "limit", required = false, defaultValue = "18") int limit) {
        cityService.generate();
        return new ResponseEntity<>(cityService.list(), HttpStatus.OK);
    }
}
