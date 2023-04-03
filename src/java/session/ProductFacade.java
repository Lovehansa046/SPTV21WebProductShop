

package session;

//import entity.Book;
import entity.Product;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "SPTV21WebProductShopPU")
    private EntityManager em;

    public ProductFacade(Class<Product> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

}
