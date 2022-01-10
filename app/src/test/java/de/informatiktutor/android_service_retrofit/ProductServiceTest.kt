package de.informatiktutor.android_service_retrofit

import de.informatiktutor.android_service_retrofit.model.Product
import org.junit.Test

import org.junit.Assert.*

/**
 * Tests the ProductService by performing some live requests.
 * Usually you would mock these requests, but in this case it just serves the purpose
 * of demonstrating how to write a service and use the Retrofit library to make API requests.
 */
class ProductServiceTest {

    private val barcode = "893746592187365498273"
    private val expectedUrl = "https://world.openfoodfacts.org/api/v0/product/$barcode.json"

    private val expectedProduct = Product(
        barcode = barcode,
        genericName = "Bio Tomatenmark, zweifach konzentriert",
        genericNameGerman = "Bio Tomatenmark, zweifach konzentriert"
    )

    private val invalidBarcode = "123876345723574237462342"

    @Test
    fun product_createsCorrectRequest() {
        val service = ProductService.create()
        val call = service.product(barcode)

        assertEquals("GET", call.request().method())
        assertEquals(expectedUrl, call.request().url().toString())
    }

    @Test
    fun validProduct_executeLiveRequest_returnsExpectedProduct() {
        val service = ProductService.create()
        val call = service.product(barcode)

        // Synchronously get API result (blocks until request is done)
        val result = call.execute().body()

        assertEquals(barcode, result.code)
        assertEquals(1, result.status) // product found
        assertEquals("product found", result.statusText)

        // Note that we can compare these objects here
        // because we are using Kotlin data classes:
        // https://kotlinlang.org/docs/data-classes.html
        assertEquals(expectedProduct, result.product)
    }

    @Test
    fun invalidProduct_executeLiveRequest_returnsProductNotFound() {
        val service = ProductService.create()
        val call = service.product(invalidBarcode)
        val result = call.execute().body()

        assertEquals(0, result.status) // product not found
        assertEquals("product not found", result.statusText)
    }
}
