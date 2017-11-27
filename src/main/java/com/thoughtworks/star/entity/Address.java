package com.thoughtworks.star.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO students implement
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_address")
public class Address {
    @Id
    private String id;

    private String description;
}
