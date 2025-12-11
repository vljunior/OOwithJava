package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class ClienteTest {

    @Test
    public void deveSalvarCliente() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Cliente cliente = new Cliente();
        cliente.setNome("Thiago");
        cliente.setCpf("123.456.789-00");

        session.save(cliente);
        tx.commit();
        session.close();

        System.out.println("Cliente salvo com sucesso! ID: " + cliente.getId());
    }
}
