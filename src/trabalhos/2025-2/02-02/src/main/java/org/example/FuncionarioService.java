package org.example;

import org.example.Funcionario;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class FuncionarioService {


    public void salvar(Funcionario funcionario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(funcionario);

        tx.commit();
        session.close();
    }

    public List<Funcionario> listarTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Funcionario> funcionarios = session.createQuery("FROM Funcionario", Funcionario.class).list();

        session.close();
        return funcionarios;
    }

    public Funcionario buscarPorId(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Funcionario funcionario = session.get(Funcionario.class, id);

        session.close();
        return funcionario;
    }

    public void atualizar(Funcionario funcionario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.merge(funcionario);

        tx.commit();
        session.close();
    }

    public void remover(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Funcionario funcionario = session.get(Funcionario.class, id);
        if (funcionario != null) {
            session.remove(funcionario);
        }

        tx.commit();
        session.close();
    }
}