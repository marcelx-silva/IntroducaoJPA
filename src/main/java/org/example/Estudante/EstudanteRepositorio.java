package org.example.Estudante;

import org.example.model.Estudante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

public class EstudanteRepositorio {
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public EstudanteRepositorio(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("estudante_pu");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public EstudanteRepositorio(String persistenceUnitName){
        this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public Estudante find(Long id){
        Optional<Estudante> estudanteOptional = Optional.ofNullable(entityManager.find(Estudante.class,id));
        Estudante estudante = estudanteOptional.get();
        if (estudanteOptional.isPresent())
                return estudante;
        else
            return null;
    }

    public Estudante add(Estudante estudante){
        entityManager.getTransaction().begin();
        entityManager.persist(estudante);
        entityManager.getTransaction().commit();
        return estudante;
    }

    public Estudante update(Long id, String nome, String sobrenome){
        Optional<Estudante> estudanteOptional = Optional.ofNullable(find(id));
        Estudante estudante = estudanteOptional.get();

        if (estudanteOptional.isPresent()){

            entityManager.getTransaction().begin();
            estudante.setNome(nome);
            estudante.setSobrenome(sobrenome);
            entityManager.getTransaction().commit();
        }
        return estudante;

    }

    public void delete(Estudante estudante){
        entityManager.getTransaction().begin();
        entityManager.remove(estudante);
        entityManager.getTransaction().commit();
    }

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }

}
