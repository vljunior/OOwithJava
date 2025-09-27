package exemploaluno;


import jakarta.persistence.*;

import java.util.List;

public class AlunoRepositoryHibernate implements AlunoRepository {

    private final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("escolaPU");

    @Override
    public void salvar(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(aluno);
        tx.commit();
        em.close();
    }

    @Override
    public Aluno buscarPorCpf(CPF cpf) {
        EntityManager em = emf.createEntityManager();
        Aluno aluno = em.find(Aluno.class, cpf);
        em.close();
        return aluno;
    }

    @Override
    public List<Aluno> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Aluno> lista = em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
        em.close();
        return lista;
    }

    @Override
    public void deletar(CPF cpf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Aluno aluno = em.find(Aluno.class, cpf);
        if (aluno != null) em.remove(aluno);
        tx.commit();
        em.close();
    }
}
