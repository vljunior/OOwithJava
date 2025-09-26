package exemplos.PersistenciaOO.OutroAluno;

import jakarta.persistence.*;
import java.util.List;

public class AlunoRepositorio {

    private static final EntityManagerFactory EMF =
        Persistence.createEntityManagerFactory("escolaPU");

    // CREATE
    public void inserir(Aluno aluno) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(aluno);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // READ (por id)
    public Aluno buscarPorMatricula(String matricula) {
        EntityManager em = EMF.createEntityManager();
        try {
            return em.find(Aluno.class, matricula);
        } finally {
            em.close();
        }
    }

    // UPDATE
    public void atualizar(Aluno aluno) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(aluno);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // DELETE
    public void deletar(String matricula) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Aluno a = em.find(Aluno.class, matricula);
            if (a != null) em.remove(a);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // LISTAR
    public List<Aluno> listarTodos() {
        EntityManager em = EMF.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Aluno a ORDER BY a.matricula", Aluno.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    // Encerrar EMF ao final da aplicação (boa prática)
    public static void fechar() {
        if (EMF.isOpen()) EMF.close();
    }
}
