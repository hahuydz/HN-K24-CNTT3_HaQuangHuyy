package com.example.virtualasset.controller;

import com.example.virtualasset.entity.Rarity;
import com.example.virtualasset.service.RarityService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rarity")
public class RarityController {

    private final RarityService rarityService;

    public RarityController(RarityService rarityService) {
        this.rarityService = rarityService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", rarityService.getAll());
        return "rarity/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("rarity", new Rarity());
        return "rarity/add";
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute Rarity rarity,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            return "rarity/add";
        }

        rarityService.save(rarity);
        return "redirect:/rarity";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("rarity", rarityService.findById(id));
        return "rarity/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        rarityService.delete(id);
        return "redirect:/rarity";
    }
}