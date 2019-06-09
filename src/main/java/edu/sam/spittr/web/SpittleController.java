package edu.sam.spittr.web;


import edu.sam.spittr.data.SpittleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        Logger logger = LoggerFactory.getLogger(SpittleController.class);
        logger.debug("message=\"{}\" model=\"{}\"",
                "Controller has started request processing", model);
        model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        String viewName = "spittles";
        logger.debug("message=\"{}\", model=\"{}\", viewName=\"{}\"",
                "Controller has finished request processing", model, viewName);

        try {
            throw new Exception("No exception is occurred.");
        } catch(Exception e) {
            logger.error("test message", e);
        }

        return viewName;
    }
}