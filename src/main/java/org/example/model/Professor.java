package org.example.model;

import javax.persistence.*;

@Entity
@Table(name="professores")

public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="firstName", length=150, nullable = false)
    private String nome;
    @Column(name="lastName", length=150, nullable = true)
    private String sobrenome;

    public Professor() {
    }

    public Professor(Long id, String nome, String sobrenome) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String toString(){
        return "Student: {"+
                "id="+id+
                "firstName="+nome+'\''+
                "lastName="+sobrenome+'\''+
                '}';
    }

}
