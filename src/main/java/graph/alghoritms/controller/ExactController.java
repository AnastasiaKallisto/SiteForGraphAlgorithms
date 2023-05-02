package graph.alghoritms.controller;


import com.google.gson.Gson;
import graph.alghoritms.dto.response.ExactGraphDtoResponse;
import graph.alghoritms.error.ErrorCode;
import graph.alghoritms.error.ServerException;
import graph.alghoritms.form.QuantityForm;
import graph.alghoritms.service.ExactGraphService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class ExactController {
    private ExactGraphService service;
    private Gson gson = new Gson();

    public ExactController() {
        service = new ExactGraphService();
    }

    @GetMapping(value = {"/", "/exact"})
    public String exact(Model model) throws ServerException {
        QuantityForm form = new QuantityForm("");
        ExactGraphDtoResponse response = service.getExactMainGraph();
        model.addAttribute("exactGraph", "'"+gson.toJson(response)+"'");
        response = service.getExactPrim();
        model.addAttribute("exactPrimGraph", "'"+gson.toJson(response)+"'");
        response = service.getExactKruskal();
        model.addAttribute("exactKruskalGraph", "'"+gson.toJson(response)+"'");
        model.addAttribute("exactQuantityForm", form);
        model.addAttribute("exactQuantity", service.getQuantity());
        return "exact";
    }

    @GetMapping(value = { "/exact/prim"})
    public String runPrim(Model model) throws ServerException {
        ExactGraphDtoResponse response = service.runPrim();
        model.addAttribute("exactPrimGraph", gson.toJson(response));
        return exact(model);
    }

    @GetMapping(value = { "/exact/kruskal"})
    public String runKruskal(Model model) throws ServerException {
        ExactGraphDtoResponse response = service.runKruskal();
        model.addAttribute("exactKruskalGraph", gson.toJson(response));
        return exact(model);
    }

    @PostMapping(value = {"/", "/exact"})
    public String generateExactGraph(Model model, //
                                     @Valid @ModelAttribute("exactQuantityForm") QuantityForm form) throws ServerException {

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
        ExactGraphDtoResponse response = service.generateGraph(quantityInt);
        model.addAttribute("exactGraph", gson.toJson(response));
        model.addAttribute("exactQuantityForm", form);
        model.addAttribute("exactQuantity", quantityInt);
        return "redirect:/exact";
    }

}

