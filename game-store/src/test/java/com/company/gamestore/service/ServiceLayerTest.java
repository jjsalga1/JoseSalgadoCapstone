package com.company.gamestore.service;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.ProcessingFee;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer service;
    ConsoleControllerRepository consoleControllerRepository;
    GameControllerRepository gameControllerRepository;
    InvoiceRepository invoiceRepository;
    ProcessingFeesRepository processingFeesRepository;
    SalesTaxRateRepository salesTaxRateRepository;
    TshirtRepository tshirtRepository;

    @Before
    public void setUp() throws Exception{
        //we will each att our set up mock to this funciton
        setUpInvoiceRepositoryMock();
        service = new ServiceLayer(consoleControllerRepository,gameControllerRepository,invoiceRepository,
                processingFeesRepository,salesTaxRateRepository,tshirtRepository);
    }

    // Helper method
    private void setUpInvoiceRepositoryMock() {
        invoiceRepository = mock(InvoiceRepository.class);
        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setName("Jose Salgado");
        invoice.setStreet("1 Irvine Ln.");
        invoice.setCity("Irvine");
        invoice.setState("CA");
        invoice.setZipcode("92617");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal(10.00));
        invoice.setTax(new BigDecimal(.8));
        invoice.setProcessingFee(new BigDecimal(1.25));
        invoice.setTotal(new BigDecimal(15.00));

        Invoice invoice2 = new Invoice();
        invoice2.setId(2);
        invoice2.setName("Joe Doe");
        invoice2.setStreet("4 Oak St.");
        invoice2.setCity("Bakersfield");
        invoice2.setState("CA");
        invoice2.setZipcode("93314");
        invoice2.setItemType("Game");
        invoice2.setItemId(32);
        invoice2.setQuantity(4);
        invoice2.setSubtotal(new BigDecimal(11.00));
        invoice2.setTax(new BigDecimal(.7));
        invoice2.setProcessingFee(new BigDecimal(1.30));
        invoice2.setTotal(new BigDecimal(10.00));

        List<Invoice> iList = new ArrayList<>();
        iList.add(invoice);
        iList.add(invoice2);

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice2);

        doReturn(invoice).when(invoiceRepository).save(invoice2);
        doReturn(Optional.of(invoice)).when(invoiceRepository).findById(1);
        doReturn(iList).when(invoiceRepository).findAll();
        doReturn(invoiceList).when(invoiceRepository).findByName("Joe Doe");
    }

    private void setUpSalesTaxRateRepositoryMock() {
        salesTaxRateRepository = mock(SalesTaxRateRepository.class);
    }

    @Test
    public void shouldSaveInvoice() {
        // Arrange
        InvoiceViewModel expectedResult = new InvoiceViewModel();
        expectedResult.setId(2);
        expectedResult.setName("Susan Lady");
        expectedResult.setStreet("1 Pine Dr.");
        expectedResult.setCity("Shafter");
        expectedResult.setState("CA");
        expectedResult.setZipcode("93262");
        expectedResult.setItemType("Console");
        expectedResult.setItemId(1);
        expectedResult.setQuantity(1);

        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setName("Susan Lady");
        invoice.setStreet("1 Pine Dr.");
        invoice.setCity("Shafter");
        invoice.setState("CA");
        invoice.setZipcode("93262");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setQuantity(1);

        // ACT
        //invoice = service.saveInvoice(invoice);
        assertEquals(expectedResult, invoice);
    }

    @Test
    public void shouldFindInvoice() {
        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setId(1);
        invoice.setName("Jose Salgado");
        invoice.setStreet("1 Irvine Ln.");
        invoice.setCity("Irvine");
        invoice.setState("CA");
        invoice.setZipcode("92617");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal(10.00));
        invoice.setTax(new BigDecimal(.8));
        invoice.setProcessingFee(new BigDecimal(1.25));
        invoice.setTotal(new BigDecimal(15.00));

        InvoiceViewModel invoiceViewModel = service.findInvoice(1);
        assertEquals(invoiceViewModel, invoice);
    }

    @Test
    public void shouldFindAllInvoices() {
        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setId(1);
        invoice.setName("Jose Salgado");
        invoice.setStreet("1 Irvine Ln.");
        invoice.setCity("Irvine");
        invoice.setState("CA");
        invoice.setZipcode("92617");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal(10.00));
        invoice.setTax(new BigDecimal(.8));
        invoice.setProcessingFee(new BigDecimal(1.25));
        invoice.setTotal(new BigDecimal(15.00));


        InvoiceViewModel invoice2 = new InvoiceViewModel();
        invoice2.setId(2);
        invoice2.setName("Joe Doe");
        invoice2.setStreet("4 Oak St.");
        invoice2.setCity("Bakersfield");
        invoice2.setState("CA");
        invoice2.setZipcode("93314");
        invoice2.setItemType("Game");
        invoice2.setItemId(32);
        invoice2.setQuantity(4);
        invoice2.setSubtotal(new BigDecimal(11.00));
        invoice2.setTax(new BigDecimal(.7));
        invoice2.setProcessingFee(new BigDecimal(1.30));
        invoice2.setTotal(new BigDecimal(10.00));


        List<InvoiceViewModel> iList = new ArrayList<>();
        iList.add(invoice);
        iList.add(invoice2);

        List<InvoiceViewModel> invoiceViewModelList = service.findAllInvoices();
        assertEquals(iList.size(), invoiceViewModelList.size());
    }

    @Test
    public void shouldFindInvoiceByName() {
        InvoiceViewModel invoice2 = new InvoiceViewModel();
        invoice2.setId(2);
        invoice2.setName("Joe Doe");
        invoice2.setStreet("4 Oak St.");
        invoice2.setCity("Bakersfield");
        invoice2.setState("CA");
        invoice2.setZipcode("93314");
        invoice2.setItemType("Game");
        invoice2.setItemId(32);
        invoice2.setQuantity(4);
        invoice2.setSubtotal(new BigDecimal(11.00));
        invoice2.setTax(new BigDecimal(.7));
        invoice2.setProcessingFee(new BigDecimal(1.30));
        invoice2.setTotal(new BigDecimal(10.00));

        List<InvoiceViewModel> ivm = service.findInvoicesByName("Joe Doe");
        assertEquals(1, ivm.size());
    }
}