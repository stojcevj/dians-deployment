package com.example.pharmacies.web;

import com.example.pharmacies.model.Pharmacy;
import com.example.pharmacies.service.PharmacyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping({"/home", "/"})
public class MainController {
    private final PharmacyService pharmacyService;

    public MainController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping
    public String getMainPage(Model model){
        model.addAttribute("bodyContent", "homepage");
        model.addAttribute("title", "PharMap");
        return "master";
    }

    @GetMapping("/add")
    public String getAddPharmacy(Model model){
        model.addAttribute("bodyContent", "addpharmacy");
        model.addAttribute("title", "Add");
        return "master";
    }

    @GetMapping("/search")
    public String getSearchPharmacy(Model model){
        model.addAttribute("list", pharmacyService.listAllPharmacies());
        model.addAttribute("bodyContent", "search");
        model.addAttribute("title", "Search");
        return "master";
    }
    @PostMapping("/search")
    public String postSearchPharmacy(Model model, @RequestParam(required = false) String city)
    {
        if(city==null)
            model.addAttribute("list",pharmacyService.listAllPharmacies());
        else
        model.addAttribute("list",pharmacyService.findByContains(city));
        model.addAttribute("bodyContent", "search");
        model.addAttribute("title", "Search");
        return "master";
    }

    @PostMapping("/add")
    public String addPharmacy(Model model,
                              @RequestParam String name,
                              @RequestParam String location,
                              @RequestParam String workingTime,
                              @RequestParam Double lon,
                              @RequestParam Double lat,
                              @RequestParam String phoneNumber,
                              @RequestParam String city,
                              @RequestParam String website){
        pharmacyService.savePharmacy(new Pharmacy(name, location, workingTime, lat, lon, phoneNumber, city, website));
        return "redirect:/search";
    }
    @GetMapping("/getClosestPharmacy")
    public String userLocation(Model model)
    {
        model.addAttribute("list",pharmacyService.listAllPharmacies());
        model.addAttribute("bodyContent", "locateClosest");
        model.addAttribute("title", "LocateClosest");
        return "master";
    }
}
