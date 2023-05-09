package com.serbatic.facturas.service;

import com.serbatic.facturas.accessingData.Article;
import com.serbatic.facturas.accessingData.Demand;
import com.serbatic.facturas.accessingData.Invoice;
import com.serbatic.facturas.accessingData.InvoiceRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService implements InvoiceServiceInterface{

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Override
    public Invoice addNewInvoice(String date, Demand demand) {
        Invoice inv=new Invoice();
        inv.setDate(date);
        inv.setDemand(demand);
        return invoiceRepository.save(inv);
    }

    @Override
    public Invoice updateInvoicePartially(Long invId, Invoice invDetails) throws ResourceNotFoundException {
        Invoice inv=invoiceRepository.findById(invId).orElseThrow(() -> new ResourceNotFoundException("Invoice not found on :: "+invId));

        if(invDetails.getDate()!=null){
            inv.setDate(invDetails.getDate());
        }
        if(invDetails.getDemand()!=null){
            inv.setDemand(invDetails.getDemand());
        }
        return invoiceRepository.save(inv);
    }

    @Override
    public Invoice findInvoice(Long invId) throws ResourceNotFoundException {
        return invoiceRepository.findById(invId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found on : " + invId));
    }

    @Override
    public void deleteInvoice(Long id) {
         invoiceRepository.deleteById(id);
    }

    @Override
    public Iterable<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
}
