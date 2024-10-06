package database;

import org.hibernate.*;

import java.util.List;

public class ClientesDAO implements DAO<ClientesEntity> {

    @Override
    public void add(ClientesEntity cliente) {
        Transaction transaction = null;
        try (Session session = DatabaseConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(ClientesEntity cliente) {
        Transaction transaction = null;
        try (Session session = DatabaseConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ClientesEntity cliente) {
        Transaction transaction = null;
        try (Session session = DatabaseConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public ClientesEntity findById(int id) {
        try (Session session = DatabaseConnection.getSessionFactory().openSession()) {
            return session.get(ClientesEntity.class, id);
        }
    }

    public ClientesEntity findByCPF(String cpf) {
        try (Session session = DatabaseConnection.getSessionFactory().openSession()) {
            Query<ClientesEntity> query = session.createQuery("FROM ClientesEntity WHERE cpf = :cpf", ClientesEntity.class);
            query.setParameter("cpf", cpf);
            return query.uniqueResult();
        }
    }

    @Override
    public List<ClientesEntity> findAll() {
        try (Session session = DatabaseConnection.getSessionFactory().openSession()) {
            return session.createQuery("FROM ClientesEntity", ClientesEntity.class).list();
        }
    }

    public List<ClientesEntity> OrderByName() {
        try (Session session = DatabaseConnection.getSessionFactory().openSession()) {
            return session.createQuery("FROM ClientesEntity ORDER BY nome ASC", ClientesEntity.class).list();
        }
    }
}
