package com.TraineProject.CustomerService.dao.daoImpl;

import com.TraineProject.CustomerService.dao.InvoiceDao;
import com.TraineProject.CustomerService.entity.InvoiceEntity;
import com.TraineProject.CustomerService.repo.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDaoImpl implements InvoiceDao {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceDaoImpl ( InvoiceRepository invoiceRepository)
    {
        this.invoiceRepository = invoiceRepository ;
    }
    @Override
    public InvoiceEntity saveInvoice(InvoiceEntity invoiceEntity) {
        return invoiceRepository.save(invoiceEntity);
    }
}
