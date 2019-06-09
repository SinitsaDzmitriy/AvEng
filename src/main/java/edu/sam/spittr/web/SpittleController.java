package edu.sam.spittr.web;


import edu.sam.spittr.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "spittles";
    }

    // @RequestMapping that has a variable portion of the path represented the Spittle ID
    // Placeholder is a name surrounded by curly braces: {name}

    // Path parameter and method parameter binding:
    // @RequestMapping(value="/{name}", method=RequestMethod.METHOD)
    // public String handlerMethod(@PathVariable("pathParam") type methodParam);

    // Since the method parameter name happens to be the same as the placeholder
    // name, value parameter on @PathVariable can optionally be omitted

    @RequestMapping(value="/{spittleId}", method=RequestMethod.GET)
    public String spittle(Model model, @PathVariable long spittleId) {
        // The model key will be spittle, inferred by the type passed in to addAttribute()
        model.addAttribute(spittleRepository.findOne(spittleId));
        return "spittle";
    }

}
