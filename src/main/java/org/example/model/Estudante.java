package org.example.model;

import javax.persistence.*;

@Entity
@Table(name="estudantes")
@NamedQuery(name="find student by id",query = "SELECT e.id, e.nome, e.sobrenome FROM Estudante  e WHERE e.id = :id")
public class Estudante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "firstName",length=150,nullable = false)
    private String nome;
    @Column(name = "lastName", length=150,nullable = true)
    private String sobrenome;

    public Estudante() {
    }

    public Estudante(Long id, String nome, String sobrenome) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString(){
        return "Student: {"+
                "id="+id+
                "firstName="+nome+'\''+
                "lastName="+sobrenome+'\''+
                '}';
    }


}
