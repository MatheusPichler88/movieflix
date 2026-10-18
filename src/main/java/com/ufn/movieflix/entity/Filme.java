package com.ufn.movieflix.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    private double avaliacao;

    @CreationTimestamp //preenche com data e hora quando é criado no banco
    @Column(name = "criado_em")
    private LocalDate lancamento;

    @UpdateTimestamp //atualiza data e hora no banco quando houver alterações
    @Column(name = "atualizado_em")
    private LocalDate atualizadoEm;

    // Um filme pode ter varias categorias
    @ManyToMany
    @JoinTable(name = "filme_categoria",
            joinColumns = @JoinColumn(name = "filme_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;

    // Um filme pode ter varios streaming
    @ManyToMany
    @JoinTable(name = "filme_streaming",
    joinColumns = @JoinColumn(name = "filme_id"),
    inverseJoinColumns = @JoinColumn(name = "streaming_id")
    )
    private List<Streaming> streamings;
}
