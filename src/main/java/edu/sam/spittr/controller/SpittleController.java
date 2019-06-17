package edu.sam.spittr.controller;

import edu.sam.spittr.dto.SpittleDTO;
import edu.sam.spittr.repository.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/spittles")
public class SpittleController{
    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model) {
        model.addAttribute(Constants.Model.SPITTLE_LIST_KEY, spittleRepository.readSpittles(Long.MAX_VALUE, 20));
        return "allSpittles";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        // Add a SpittleDTO object with "spittle" key in the model
        // It's referenced at createSpittleForm JSP
        model.addAttribute(Constants.Model.SPITTLE_ENTITY_KEY, new SpittleDTO.Builder().build());
        return "createSpittleForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String persist(SpittleDTO spittleToPersist) {
        spittleRepository.create(spittleToPersist);
        return "redirect:/spittles";
    }

    @RequestMapping(value = "/edit/{spittleId}", method = RequestMethod.GET)
    public String edit(@PathVariable long spittleId, Model model) {
        // Add a SpittleDTO object with specific ID and "spittle" key in the model
        // It's referenced in the editSpittleForm JSP view
        model.addAttribute(Constants.Model.SPITTLE_ENTITY_KEY, spittleRepository.readById(spittleId));
        return "editSpittleForm";
    }

    @RequestMapping(value = "/edit/{spittleId}", method = RequestMethod.POST)
    public String update(@PathVariable long spittleId, SpittleDTO editSpittle) {
        spittleRepository.update(editSpittle);
        return "redirect:/spittles";
    }

    @RequestMapping(value = "/remove/{spittleId}", method = RequestMethod.GET)
    public String edit(@PathVariable long spittleId) {
        spittleRepository.delete(spittleId);
        return "redirect:/spittles";
    }

    // @RequestMapping that has a variable portion of the path represented the SpittleDTO ID
    // Placeholder is a name surrounded by curly braces: {name}

    // Path parameter and method parameter binding:
    // @RequestMapping(value="/{name}", method=RequestMethod.METHOD)
    // public String handlerMethod(@PathVariable("pathParam") type methodParam);

    // Since the method parameter name happens to be the same as the placeholder
    // name, value parameter on @PathVariable can optionally be omitted

    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    public String spittle(Model model, @PathVariable long spittleId) {
        // The model key will be spittle, inferred by the type passed in to addAttribute()
        model.addAttribute(spittleRepository.readById(spittleId));
        return Constants.Model.SPITTLE_ENTITY_KEY;
    }

}