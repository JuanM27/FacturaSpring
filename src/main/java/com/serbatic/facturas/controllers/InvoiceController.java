package com.serbatic.facturas.controllers;

import com.serbatic.facturas.accessingData.Article;
import com.serbatic.facturas.accessingData.Demand;
import com.serbatic.facturas.accessingData.Invoice;
import com.serbatic.facturas.service.InvoiceService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/invoice")
public class InvoiceController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping(path = "/add") // Map ONLY POST Requests

    public @ResponseBody String addNewInvoice(@RequestParam String date,
                                              @RequestParam Demand demand) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        Invoice savedInvoice = invoiceService.addNewInvoice(date, demand);
        return "Invoice saved with id " + savedInvoice.getIdInvoice();
    }

        @PatchMapping(path = "/{id}") // Map ONLY PATCH Requests
        public ResponseEntity<Invoice> updateInvoicePartially(
                @PathVariable(value = "idInv") Long invoiceId, @RequestBody Invoice invDetails)
      throws ResourceNotFoundException {
            Invoice updatedInvoice = invoiceService.updateInvoicePartially(invoiceId,invDetails);
            return ResponseEntity.ok(updatedInvoice);
        }

    // This returns a json with the article information
    @GetMapping(path = "/{id}")
    public ResponseEntity<Invoice> findInvoice(@PathVariable(value = "idInv") Long invoiceId)
            throws ResourceNotFoundException {
        Invoice invoice=invoiceService.findInvoice(invoiceId);
        return ResponseEntity.ok().body(invoice);
    }

    // Delete

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String deleteInvoice(@PathVariable("idInv") Long id) {
        invoiceService.deleteInvoice(id);
        return String.format("Invoice %d deleted", id);

        // You can add the option of returning the deleted article just in case if you want to create
        // some
        // sort of backup
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Invoice> getAllArticles() {
        // This returns a JSON or XML with the articles
        return invoiceService.getAllInvoices();
    }
}
