package com.UFMSPetSistemas.getpet.model.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "servico") // Nome da tabela no banco de dados
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia para geração de ID (auto incremento no banco)
    private int id;

    @ManyToOne // Relacionamento com a tabela Usuario
    @JoinColumn(name = "id_usuario", nullable = false) // FK para tabela Usuario
    private Usuario usuario;

    @Column(name = "nome", nullable = false) // Nome não pode ser nulo
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT") // Detalhes adicionais sobre o serviço
    private String descricao;

    @Column(name = "img", nullable = true) // Caminho ou URL para a imagem (pode ser nulo)
    private String img;

    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.DATE) // Indicação de que o tipo é Date (somente data)
    private Date data;

    @Column(name = "avaliacao", nullable = true)
    private int avaliacao; // Avaliação do serviço (por exemplo, uma nota de 1 a 5)

    @Column(name = "valor", nullable = true)
    private double valor; // Avaliação do serviço (por exemplo, uma nota de 1 a 5)

    public Servico() {
        // Construtor padrão
    }

    public Servico(int id, Usuario usuario, String nome, String descricao, String img, Date data, int avaliacao, double valor) {
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.descricao = descricao;
        this.img = img;
        this.data = data;
        this.avaliacao = avaliacao;
        this.valor = valor;
    }

    // GETTERS E SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


}