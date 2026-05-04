package com.example.virtualasset.controller;

import com.example.virtualasset.entity.GameItem;
import com.example.virtualasset.service.GameItemService;
import com.example.virtualasset.service.RarityService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/game-item")
public class GameItemController {

    private final GameItemService gameItemService;
    private final RarityService rarityService;

    public GameItemController(GameItemService gameItemService,
                              RarityService rarityService) {

        this.gameItemService = gameItemService;
        this.rarityService = rarityService;
    }

    @GetMapping
    public String list(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(required = false) Long rarityId,
            @RequestParam(defaultValue = "0") int page,
            Model model
    ) {

        Page<GameItem> gameItems =
                gameItemService.search(keyword, rarityId, page);

        model.addAttribute("gameItems", gameItems);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("rarityId", rarityId);
        model.addAttribute("rarities", rarityService.getAll());

        return "gameitem/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {

        model.addAttribute("gameItem", new GameItem());
        model.addAttribute("rarities", rarityService.getAll());

        return "gameitem/add";
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute GameItem gameItem,
            BindingResult result,
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws IOException {

        if (result.hasErrors()) {

            model.addAttribute("rarities", rarityService.getAll());

            return "gameitem/add";
        }

        if (!file.isEmpty()) {

            String fileName =
                    UUID.randomUUID() + file.getOriginalFilename();

            String uploadDir =
                    "src/main/resources/static/uploads";

            File dir = new File(uploadDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            Path path = Paths.get(uploadDir, fileName);

            Files.write(path, file.getBytes());

            gameItem.setItemImage(fileName);
        }

        gameItemService.save(gameItem);

        return "redirect:/game-item";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute(
                "gameItem",
                gameItemService.findById(id)
        );

        model.addAttribute(
                "rarities",
                rarityService.getAll()
        );

        return "gameitem/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        gameItemService.delete(id);

        return "redirect:/game-item";
    }
}