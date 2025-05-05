package services;

import java.util.List;
import java.util.Set;

import dao.TicketCategoryDao;
import model.TicketCategory;
import model.Ticket;

public class TicketCategoryService {
    private TicketCategoryDao categoryDao = new TicketCategoryDao();

    public TicketCategory getById(long id) {
        return categoryDao.get(id);
    }

    public long create(TicketCategory category) {
        return categoryDao.create(category);
    }

    public void update(TicketCategory category) {
        categoryDao.update(category);
    }

    public void delete(TicketCategory category) {
        categoryDao.delete(category);
    }

    public List<TicketCategory> getAll() {
        return categoryDao.getAll();
    }

    public TicketCategory filterTicketsByCategories(long id) {
        return categoryDao.filterTicketsByCategory(id);
    }
}