package com.example.virtualasset.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 150, message = "Tên vật phẩm từ 5-150 ký tự")
    private String itemName;

    @NotBlank(message = "Không được để trống game title")
    private String gameTitle;

    private Double marketPrice;

    @PastOrPresent(message = "Không được là ngày tương lai")
    private LocalDate dropDate;

    private String itemImage;

    private Boolean isTradeable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rarity_id")
    private Rarity rarity;
}