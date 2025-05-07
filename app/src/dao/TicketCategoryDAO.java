package dao;

import model.TicketCategory;

public class TicketCategoryDAO extends BaseDAO<TicketCategory> {
    public TicketCategory getWithTickets(long id) {
        TicketCategory category = null;
        try {
            initTransaction();
            category = (TicketCategory) session.createQuery(
                "select c from TicketCategory c left join fetch c.tickets where c.id = :id"
            )
            .setParameter("id", id)
            .uniqueResult();
        } finally {
            session.close();
        }
        return category;
    }
}
