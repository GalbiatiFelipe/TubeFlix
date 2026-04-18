package com.tubetv.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private double rating;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /*
    * Relação de tabelas
    * Para fazer a relação da tabela movie com as outras, que um movie pode ter varias categories ou streamings e vice versa
    * utilizamos a annotation ManyToMany, e precisamos de uma tabela intermediaria
    * */
    @ManyToMany
    /*
    * Tabela Intermediaria
    * utilizamos o 'joinColumns = @JoinColumn(name = "movie_id")' para dizer que a coluna 'id' da tabela movie
    * vai ser a 'movie_id' na tabela intermediaria
    * e 'inverseJoinColumns = @JoinColumn(name = "category_id")' faz com que a coluna 'id' da tabela category sera a
    * 'category_id' na tabela intermediaria
    * */
    @JoinTable(name = "movie_category",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "movie_streaming",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "streaming_id")
    )
    private List<Streaming> streamings;
}
