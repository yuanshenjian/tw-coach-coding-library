package com.thoughtworks.star.entity;

import lombok.*;

import javax.persistence.*;

// TODO students implement
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_item")
public class Item {
    @Id
    private String id;

    private String name;

    private double price;
}
