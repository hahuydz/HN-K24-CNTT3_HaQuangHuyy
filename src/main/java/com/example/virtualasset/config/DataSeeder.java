package com.example.virtualasset.config;

import com.example.virtualasset.entity.GameItem;
import com.example.virtualasset.entity.Rarity;
import com.example.virtualasset.repository.GameItemRepository;
import com.example.virtualasset.repository.RarityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RarityRepository rarityRepository;
    private final GameItemRepository gameItemRepository;

    public DataSeeder(RarityRepository rarityRepository,
                      GameItemRepository gameItemRepository) {

        this.rarityRepository = rarityRepository;
        this.gameItemRepository = gameItemRepository;
    }

    @Override
    public void run(String... args) {

        if (rarityRepository.count() == 0) {

            Rarity r1 = new Rarity();
            r1.setName("Immortal");
            r1.setDropRate(0.5);

            Rarity r2 = new Rarity();
            r2.setName("Legendary");
            r2.setDropRate(1.5);

            Rarity r3 = new Rarity();
            r3.setName("Covert");
            r3.setDropRate(3.0);

            rarityRepository.save(r1);
            rarityRepository.save(r2);
            rarityRepository.save(r3);

            for (int i = 1; i <= 4; i++) {

                GameItem g = new GameItem();

                g.setItemName("Dragon Skin " + i);
                g.setGameTitle("CSGO");
                g.setMarketPrice(100.0 + i);
                g.setDropDate(LocalDate.now());
                g.setItemImage("default.png");
                g.setIsTradeable(true);
                g.setRarity(r1);

                gameItemRepository.save(g);
            }
        }
    }
}