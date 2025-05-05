package dao;

import java.util.List;
import org.hibernate.HibernateException;
import model.Ticket;
import model.TicketCategory;

public class TicketCategoryDao extends BaseDAO<TicketCategory> {

    @SuppressWarnings("unchecked")
    public TicketCategory filterTicketsByCategory(long id) {
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
