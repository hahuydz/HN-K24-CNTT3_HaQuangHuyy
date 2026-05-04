package com.example.virtualasset.service;

import com.example.virtualasset.entity.GameItem;
import com.example.virtualasset.repository.GameItemRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class GameItemService {

    private final GameItemRepository gameItemRepository;

    public GameItemService(GameItemRepository gameItemRepository) {
        this.gameItemRepository = gameItemRepository;
    }

    public Page<GameItem> getAll(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return gameItemRepository.findAll(pageable);
    }

    public Page<GameItem> search(String keyword, Long rarityId, int page) {

        Pageable pageable = PageRequest.of(page, 5);

        if (!keyword.isEmpty() && rarityId != null) {
            return gameItemRepository
                    .findByItemNameContainingIgnoreCaseAndRarityId(
                            keyword,
                            rarityId,
                            pageable
                    );
        }

        if (!keyword.isEmpty()) {
            return gameItemRepository
                    .findByItemNameContainingIgnoreCase(keyword, pageable);
        }

        if (rarityId != null) {
            return gameItemRepository.findByRarityId(rarityId, pageable);
        }

        return gameItemRepository.findAll(pageable);
    }

    public void save(GameItem gameItem) {
        gameItemRepository.save(gameItem);
    }

    public GameItem findById(Long id) {
        return gameItemRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        gameItemRepository.deleteById(id);
    }
}