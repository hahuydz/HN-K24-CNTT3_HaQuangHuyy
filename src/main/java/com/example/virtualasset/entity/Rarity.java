package com.example.virtualasset.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rarity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 50, message = "Tên phải từ 3-50 ký tự")
    private String name;

    private Double dropRate;

    @OneToMany(mappedBy = "rarity", cascade = CascadeType.ALL)
    private List<GameItem> gameItems;
}