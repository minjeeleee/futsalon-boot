package com.footsalon.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/location")
public class LocationController {

    private final LocationService locationService;

    @PostMapping(path = "/create")
    @ResponseBody
    public Location createLocation(@RequestBody Location request) {
        return locationService.createLocation(request);
    }

}
