package org.example.Estudante;

import com.mysql.cj.Session;
import org.example.model.Estudante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
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

    public List<String> findFirstNames(String name){
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT e.nome FROM Estudante e");

        return query.getResultList();
    }

    public Estudante findById(Long id){
        Query query = entityManager.createNamedQuery("find student by id");
        query.setParameter("id",id);
        entityManager.clear(); // JPQL não afeta diretamente o banco de dados. Para persistir as mudanças é preciso limpar o entityManager
        return (Estudante) query.getSingleResult();
    }

    public Estudante updateFirstNameById(String firstName, Long id){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Estudante SET nome = :firstName WHERE id = :id");
        /*
        query.setParameter(1,firstName);
        query.setParameter(2,id);
         */
        query.setParameter("firstName",firstName);
        query.setParameter("id",id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.clear();
        return findById(id);
    }

    public void deleteById(Long id){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM Estudante WHERE id = :id");
        query.setParameter("id",id);
        entityManager.getTransaction().commit();
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
