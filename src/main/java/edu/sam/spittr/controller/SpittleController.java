package edu.sam.spittr.controller;

import edu.sam.spittr.dto.SpittleDTO;
import edu.sam.spittr.repository.SpittleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger LOGGER = LoggerFactory.getLogger(SpittleController.class);

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model) {
        LOGGER.info("Spittle list displaying.");
        LOGGER.debug("Initial state of params: model={}", model);
        model.addAttribute(Constants.Model.SPITTLE_LIST_KEY, spittleRepository.readSpittles(Long.MAX_VALUE, 20));
        LOGGER.debug("Final state of params: model={}", model);
        LOGGER.debug("View name to render: viewName=\"{}\"", "allSpittles");
        return "allSpittles";
    }

    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    public String spittle(Model model, @PathVariable long spittleId) {
        LOGGER.info("Displaying spittle with id={}.", spittleId);
        // The model key will be spittle, inferred by the type passed in to addAttribute()
        model.addAttribute(spittleRepository.readById(spittleId));
        return Constants.Model.SPITTLE_ENTITY_KEY;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String displaySpittleCreationForm(Model model) {
        LOGGER.info("Spittle creation form displaying.");
        LOGGER.debug("Initial state of params: model={}", model);
        // Add a SpittleDTO object with "spittle" key in the model
        // It's referenced at createSpittleForm JSP
        model.addAttribute(Constants.Model.SPITTLE_ENTITY_KEY, new SpittleDTO.Builder().build());
        LOGGER.debug("Final state of params: model={}", model);
        LOGGER.debug("View name to render: viewName=\"{}\"", "spittleCreationForm");
        return "spittleCreationForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(SpittleDTO spittleToPersist) {
        LOGGER.info("New spittle creation.");
        LOGGER.debug("Spittle to persist: spittleToPersist={}", spittleToPersist);
        spittleRepository.create(spittleToPersist);
        LOGGER.debug("Redirection to \"{}\"", "/spittles");
        return "redirect:/spittles";
    }

    @RequestMapping(value = "/update/{idOfSpittleToUpdate}", method = RequestMethod.GET)
    public String displaySpittleUpdateForm(@PathVariable long idOfSpittleToUpdate, Model model) {
        LOGGER.info("Displaying update form for Spittle with id={}.", idOfSpittleToUpdate);
        LOGGER.debug("Initial state of params: model={}", model);
        // Add a SpittleDTO object with specific ID and "spittle" key in the model
        // It's referenced in the editSpittleForm JSP view
        model.addAttribute(Constants.Model.SPITTLE_ENTITY_KEY, spittleRepository.readById(idOfSpittleToUpdate));
        LOGGER.debug("Final state of params: model={}", model);
        LOGGER.debug("View name to render: viewName=\"{}\"", "spittleUpdateForm");
        return "spittleUpdateForm";
    }

    @RequestMapping(value = "/update/{idOfSpittleToUpdate}", method = RequestMethod.POST)
    public String update(@PathVariable long idOfSpittleToUpdate, SpittleDTO updatedSpittle) {
        LOGGER.info("Updating spittle with id={}.", idOfSpittleToUpdate);
        LOGGER.debug("Editable spittle: updatedSpittle={}", updatedSpittle);
        spittleRepository.update(idOfSpittleToUpdate, updatedSpittle);
        LOGGER.debug("Redirection to \"{}\"", "/spittles");
        return "redirect:/spittles";
    }

    @RequestMapping(value = "/delete/{idOfSpittleToDelete}", method = RequestMethod.GET)
    public String delete(@PathVariable long idOfSpittleToDelete) {
        LOGGER.info("Deletion spittle with id={}.", idOfSpittleToDelete);
        spittleRepository.delete(idOfSpittleToDelete);
        LOGGER.debug("Redirection to \"{}\"", "/spittles");
        return "redirect:/spittles";
    }

    // @RequestMapping that has a variable portion of the path represented the SpittleDTO ID
    // Placeholder is a name surrounded by curly braces: {name}

    // Path parameter and method parameter binding:
    // @RequestMapping(value="/{name}", method=RequestMethod.METHOD)
    // public String handlerMethod(@PathVariable("pathParam") type methodParam);

    // Since the method parameter name happens to be the same as the placeholder
    // name, value parameter on @PathVariable can optionally be omitted

}