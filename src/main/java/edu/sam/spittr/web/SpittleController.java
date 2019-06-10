package edu.sam.spittr.web;


import edu.sam.spittr.Spittle;
import edu.sam.spittr.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(method=RequestMethod.GET)
    public String spittles(Model model) {
        // log.debug("spittles started with model={} and params={}", model, param);
        List<Spittle> spittles = spittleRepository.findSpittles(Long.MAX_VALUE, 20);
        // log.debug("Found following splittes:{}", spittles);
        model.addAttribute(spittles);

        String resultURL = "spittles";
        // log.debug("spittles finished, result URL={} and updated model={}", resultURL, model);
        return resultURL;
    }
}