package com.footsalon.index.controller;

import com.footsalon.location.LocationService;
import com.footsalon.match.MatchMasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {

    private final MatchMasterService matchMasterService;
    private final LocationService locationService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("matchBoardList", matchMasterService.findMatchBoardList());
        model.addAttribute("locations", locationService.findAllLocations());
        return "index";
    }
}
