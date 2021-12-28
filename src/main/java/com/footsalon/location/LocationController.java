package com.footsalon.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/location")
public class LocationController {

    private final LocationService locationService;

//    @PostMapping(path = "/create")
//    public void createLocation(@RequestBody Location location) {
//        locationService.createLocation(location);
//    }

}
