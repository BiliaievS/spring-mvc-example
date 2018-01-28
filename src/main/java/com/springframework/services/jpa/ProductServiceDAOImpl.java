package com.springframework.services.jpa;

import com.springframework.commands.ProductForm;
import com.springframework.converters.ProductFormToProduct;
import com.springframework.converters.ProductToProductForm;
import com.springframework.domain.Product;
import com.springframework.services.ProductService;
import com.springframework.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by sbiliaiev on 30/08/17.
 */
@Service
@Profile("jpadao")
public class ProductServiceDAOImpl extends AbstractDAOService implements ProductService {

    private ProductFormToProduct productFormToProduct;

    @Autowired
    public void setProductFormToProduct(ProductFormToProduct productFormToProduct) {
        this.productFormToProduct = productFormToProduct;
    }

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Product.class, id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Product savProduct = em.merge(product);
        em.getTransaction().commit();

        return savProduct;
    }

    public Product saveOrUpdateProductForm(ProductForm productForm) {
        return saveOrUpdate(productFormToProduct.convert(productForm));
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Product.class, id));
        em.getTransaction().commit();
    }
}
