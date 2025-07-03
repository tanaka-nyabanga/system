package com.user.users.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;
    @Column(name = "task", nullable = false)
    String task;
    @Column(name = "description")
    String description;
}
