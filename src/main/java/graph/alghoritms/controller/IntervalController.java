package graph.alghoritms.controller;

import com.google.gson.Gson;
import graph.alghoritms.dto.response.GetIntervalDecisionsDtoResponse;
import graph.alghoritms.dto.response.IntervalGraphDtoResponse;
import graph.alghoritms.error.ErrorCode;
import graph.alghoritms.error.ServerException;
import graph.alghoritms.form.QuantityForm;
import graph.alghoritms.service.IntervalGraphService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
public class IntervalController {

    private IntervalGraphService service;
    private Gson gson = new Gson();

    public IntervalController() {
        service = new IntervalGraphService();
    }

    @GetMapping(value = { "/interval"})
    public String interval(Model model) throws ServerException {
        QuantityForm form = new QuantityForm("");
        IntervalGraphDtoResponse response = service.getIntervalMainGraph();
        model.addAttribute("intervalGraph", "'"+gson.toJson(response)+"'");
        response = service.getIntervalGraphToShow();
        model.addAttribute("intervalGraphToShow", "'"+gson.toJson(response)+"'");
        GetIntervalDecisionsDtoResponse decisionsResponse = service.getIntervalPrimGraphs();
        model.addAttribute("intervalPrimGraphs", "'"+gson.toJson(decisionsResponse)+"'");
        decisionsResponse = service.getIntervalKruskalGraphs();
        model.addAttribute("intervalKruskalGraphs", "'"+gson.toJson(decisionsResponse)+"'");
        model.addAttribute("intervalQuantityForm", form);
        model.addAttribute("intervalQuantity", service.getQuantity());
        return "interval";
    }

    @GetMapping(value = { "/interval/prim"})
    public String runPrimInterval(Model model) throws ServerException {
        service.runPrim();
        return interval(model);
    }

    @GetMapping(value = { "/interval/kruskal"})
    public String runKruskalInterval(Model model) throws ServerException {
        service.runKruskal();
        return interval(model);
    }

    @GetMapping(value = { "/interval/mainGraph"})
    public String getMainIntervalGraph(Model model)
            throws ServerException {
        service.getIntervalMainGraphAndShow();
        return interval(model);
    }

    @GetMapping(value = {"/interval/kruskal/{id}"})
    public String getKruskalIntervalGraphById(
            Model model,
            @PathVariable int id)
            throws ServerException {
        service.getKruskalGraphById(id);
        return interval(model);
    }

    @GetMapping(value = { "/interval/prim/{id}"})
    public String getPrimIntervalGraphById(
            Model model,
            @PathVariable int id)
            throws ServerException {
        service.getPrimGraphById(id);
        return interval(model);
    }

    @PostMapping(value = { "/interval"})
    public String generateIntervalGraph(
            Model model,
            @Valid @ModelAttribute("intervalQuantityForm") QuantityForm form)
            throws ServerException {

        String quantity = form.getQuantity();
        if (quantity == null)
            throw new ServerException(ErrorCode.NULL_VALUE);
        if (quantity.length() == 0)
            throw new ServerException(ErrorCode.VOID_STRING);
        int quantityInt;
        try {
            quantityInt = Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new ServerException(ErrorCode.INVALID_NUMBER);
        }
        IntervalGraphDtoResponse response = service.generateGraph(quantityInt);
        model.addAttribute("intervalGraph", gson.toJson(response));
        model.addAttribute("intervalGraphToShow", gson.toJson(response));
        model.addAttribute("intervalQuantityForm", form);
        model.addAttribute("intervalQuantity", quantityInt);
        return "redirect:/interval";
    }

}
