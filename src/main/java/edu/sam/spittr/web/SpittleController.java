package edu.sam.spittr.web;

import edu.sam.spittr.Spittle;
import edu.sam.spittr.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        return "allSpittles";
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String create() {
        return "createSpittleForm";
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String persist(Spittle spittleToPersist) {
        spittleRepository.save(spittleToPersist);
        return "allSpittles";
    }

}