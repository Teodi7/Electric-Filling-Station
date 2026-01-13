package org.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class ClientInvoiceSteps {

    // @Then("no invoice should exist") hier eingefügt und bei ChargeEVSteps entfernt

    @Then("an invoice should exist with:")
    public void an_invoice_should_exist_with(DataTable table) {

        Invoice invoice = ChargeEVSteps.getLastInvoice();
        Assertions.assertNotNull(invoice, "Expected an invoice, but none was created");

        Map<String, String> row = table.asMaps(String.class, String.class).get(0);

        Assertions.assertEquals(row.get("location"), invoice.getLocationName());
        Assertions.assertEquals(
                Double.parseDouble(row.get("energy")),
                invoice.getEnergyKwh(),
                0.01
        );
        Assertions.assertEquals(
                Double.parseDouble(row.get("pricePerKwh")),
                invoice.getPricePerKwh(),
                0.01
        );
        Assertions.assertEquals(
                Double.parseDouble(row.get("total")),
                invoice.getTotalAmount(),
                0.01
        );
    }

    // ✅ EDGE CASE
    @Then("no invoice should exist")
    public void no_invoice_should_exist() {
        Assertions.assertNull(
                ChargeEVSteps.getLastInvoice(),
                "Expected no invoice, but one was created"
        );
    }
}
