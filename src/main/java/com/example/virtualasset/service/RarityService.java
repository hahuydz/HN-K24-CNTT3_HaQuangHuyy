package com.example.virtualasset.service;

import com.example.virtualasset.entity.GameItem;
import com.example.virtualasset.entity.Rarity;
import com.example.virtualasset.repository.GameItemRepository;
import com.example.virtualasset.repository.RarityRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RarityService {

    private final RarityRepository rarityRepository;
    private final GameItemRepository gameItemRepository;

    public RarityService(RarityRepository rarityRepository,
                         GameItemRepository gameItemRepository) {
        this.rarityRepository = rarityRepository;
        this.gameItemRepository = gameItemRepository;
    }

    public List<Rarity> getAll() {
        return rarityRepository.findAll();
    }

    public void save(Rarity rarity) {
        rarityRepository.save(rarity);
    }

    public Rarity findById(Long id) {
        return rarityRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Long id) {

        Rarity rarity = rarityRepository.findById(id).orElse(null);

        if (rarity != null) {

            List<GameItem> items = rarity.getGameItems();

            for (GameItem item : items) {
                item.setRarity(null);
                gameItemRepository.save(item);
            }

            rarityRepository.delete(rarity);
        }
    }
}