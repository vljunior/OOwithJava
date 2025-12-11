package org.example;

import org.example.Cliente;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ClienteService {


     //1. SALVAR (Create)

    public void salvar(Cliente cliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(cliente);

        tx.commit();
        session.close();
    }

    //2. LISTAR TODOS (Read)

    public List<Cliente> listarTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Cliente> clientes = session.createQuery("FROM Cliente", Cliente.class).list();

        session.close();
        return clientes;
    }


    //3. BUSCAR POR ID (Read)

    public Cliente buscarPorId(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Cliente cliente = session.get(Cliente.class, id);

        session.close();
        return cliente;
    }

    //4. ATUALIZAR (Update)

    public void atualizar(Cliente cliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.merge(cliente);

        tx.commit();
        session.close();
    }

    //5. REMOVER (Delete)

    public void remover(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Cliente cliente = session.get(Cliente.class, id);
        if (cliente != null) {
            session.remove(cliente);
        }

        tx.commit();
        session.close();
    }
}