package com.example.virtualasset.repository;

import com.example.virtualasset.entity.GameItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameItemRepository extends JpaRepository<GameItem, Long> {

    Page<GameItem> findByItemNameContainingIgnoreCase(String keyword, Pageable pageable);

    Page<GameItem> findByRarityId(Long rarityId, Pageable pageable);

    Page<GameItem> findByItemNameContainingIgnoreCaseAndRarityId(
            String keyword,
            Long rarityId,
            Pageable pageable
    );
}