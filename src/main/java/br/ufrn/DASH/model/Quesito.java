package br.ufrn.DASH.model;


import java.util.List;

import br.ufrn.DASH.model.enums.TipoResposta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quesito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String enunciado;
    private Boolean obrigatorio;
    private Integer ordem;
    private Integer nivel;
    private TipoResposta tipoResposta;

    @OneToOne(mappedBy = "quesito")
    private Resposta resposta;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quesito")
    private List<Opcao> opcoes;

    @ManyToMany
    private List<Opcao> opcoesHabilitadoras; 

    @ManyToOne
    private Secao secao;

    @ManyToOne
    private Quesito superQuesito;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Quesito> subQuesitos;
}
