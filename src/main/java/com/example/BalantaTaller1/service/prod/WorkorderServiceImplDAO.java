package com.example.BalantaTaller1.service.prod;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.BalantaTaller1.dao.ProductDAOImpl;
import com.example.BalantaTaller1.dao.ProductmodelDAOImpl;
import com.example.BalantaTaller1.dao.ProductsubcategoryDAOImpl;
import com.example.BalantaTaller1.dao.ScrapreasonDAOImpl;
import com.example.BalantaTaller1.dao.UnitmeasureDAOImpl;
import com.example.BalantaTaller1.dao.WorkorderDAOImpl;
import com.example.BalantaTaller1.model.prod.Product;
import com.example.BalantaTaller1.model.prod.Scrapreason;
import com.example.BalantaTaller1.model.prod.Workorder;

public class WorkorderServiceImplDAO implements WorkorderSerivice {

    private WorkorderDAOImpl workorderDAO;
    private UnitmeasureDAOImpl unitmeasureDAO;
    private ProductDAOImpl productDAO;
    private ProductmodelDAOImpl productmodelDAO;
    private ProductsubcategoryDAOImpl productsubcategoryDAO;
    private ScrapreasonDAOImpl scrapreasonDAO;

    @Autowired
    public WorkorderServiceImplDAO(WorkorderDAOImpl workorderDAO, ScrapreasonDAOImpl scrapreasonDAO,
            ProductDAOImpl productDAO) {
        this.productDAO = productDAO;
        this.scrapreasonDAO = scrapreasonDAO;
        this.workorderDAO = workorderDAO;
    }

    @Override
    public Workorder save(Workorder wo) {

        Product op = this.productDAO.findById(wo.getProduct().getProductid());
        Scrapreason op2 = this.scrapreasonDAO.findById(wo.getScrapreason().getScrapreasonid());
        if (!op.equals(null) && !op2.equals(null)) {
            constraints(wo);
            wo.setProduct(op);
            wo.setScrapreason(op2);
            workorderDAO.save(wo);
        }

        return wo;
    }

    @Override
    public Workorder edit(Workorder wo) {
        Workorder optional = this.workorderDAO.findById(wo.getWorkorderid());
        if (!optional.equals(null)) {
            constraints(wo);
            workorderDAO.update(wo);
        }

        return wo;
    }

    @Override
    public Scrapreason saveScrapreason(Scrapreason sr) {
        scrapreasonDAO.save(sr);
        return sr;
    }

    @Override
    public Optional<Workorder> findById(Integer id) {
        return Optional.of(workorderDAO.findById(id));
    }

    @Override
    public Iterable<Workorder> findByProduct(Integer id) {
        return workorderDAO.findByProductId(id);
    }

    @Override
    public Iterable<Scrapreason> findAllScrapreason() {
        return scrapreasonDAO.findAll();
    }

    @Override
    public Iterable<Workorder> findAll() {
        return workorderDAO.findAll();
    }

    private void constraints(Workorder wo) {
        if (wo.getOrderqty() < 0) {
            throw new RuntimeException("Cantidad de orden menor a 0");
        }
        if (wo.getScrappedqty() < 0) {
            throw new RuntimeException("Cantidad de rechazo menor a 0");
        }
        if (!wo.getEnddate().isAfter(wo.getStartdate())) {
            throw new RuntimeException("Error en fecha inicial y fecha final");
        }
    }

}
