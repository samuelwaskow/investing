package com.aistocks.investing;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

public class StockTest extends InvestingApplicationTests {

    @Test
    public void shouldNotGetStock() throws Exception {
        mockMvc.perform(get("/stock/10000")
                .accept(APPLICATION_JSON))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCRUDStock() throws Exception {

        /* get empty list */
        shouldGet("/stock", "[]");

        /* Add 3 stocks */
        shouldCreate("Petrobras Ordinaria", "PETR3");
        shouldCreate("Taurus Armas", "TASA3");
        shouldCreate("Energias do Brasil", "ENBR3");

        /* Retrieve the 3 stocks that were created at the previous step */
        shouldGet("/stock", "[{\"id\":1,\"name\":\"Petrobras Ordinaria\",\"ticker\":\"PETR3\"}," +
                "{\"id\":2,\"name\":\"Taurus Armas\",\"ticker\":\"TASA3\"}," +
                "{\"id\":3,\"name\":\"Energias do Brasil\",\"ticker\":\"ENBR3\"}]");

        /* Retrieve only one stock */
        shouldGet("/stock/2", "{\"id\":2,\"name\":\"Taurus Armas\",\"ticker\":\"TASA3\"}");

        /* Updates the first stock */
        shouldUpdate(1, "Industrias Romi", "ROMI3");

        /* Check for the updated list */
        shouldGet("/stock",
                "[{\"id\":1,\"name\":\"Industrias Romi\",\"ticker\":\"ROMI3\"}," +
                        "{\"id\":2,\"name\":\"Taurus Armas\",\"ticker\":\"TASA3\"}," +
                        "{\"id\":3,\"name\":\"Energias do Brasil\",\"ticker\":\"ENBR3\"}]");

        /* Delete the last stock */
        shouldDelete(3, "Energias do Brasil", "ENBR3");

        /* Check for the updated list */
        shouldGet("/stock", "[{\"id\":1,\"name\":\"Industrias Romi\",\"ticker\":\"ROMI3\"}," +
                "{\"id\":2,\"name\":\"Taurus Armas\",\"ticker\":\"TASA3\"}]");
    }

    /**
     * Get stocks
     * 
     * @param url      Either get or list stocks
     * @param expected Expected json
     * @throws Exception
     */
    private void shouldGet(final String url, final String expected) throws Exception {

        mockMvc.perform(get(url)
                .accept(APPLICATION_JSON))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }

    /**
     * Create a stock
     * 
     * @param name
     * @param ticker
     * @throws Exception
     */
    private void shouldCreate(final String name, final String ticker) throws Exception {

        mockMvc.perform(post("/stock")
                .contentType(APPLICATION_JSON)
                .content("{ \"name\": \"" + name + "\", \"ticker\": \"" + ticker + "\"}")
                .accept(APPLICATION_JSON))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.ticker").value(ticker));

    }

    /**
     * Updates a stock
     * 
     * @param id
     * @param name
     * @param ticker
     * @throws Exception
     */
    private void shouldUpdate(final long id, final String name, final String ticker) throws Exception {

        mockMvc.perform(put("/stock/" + id)
                .contentType(APPLICATION_JSON)
                .content("{ \"name\": \"" + name + "\", \"ticker\": \"" + ticker + "\"}")
                .accept(APPLICATION_JSON))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.ticker").value(ticker));

    }

    /**
     * Deletes a stock
     * 
     * @param id     To be deleted
     * @param name   The stock name to be checked
     * @param ticker The stock ticker to be checked
     * @throws Exception
     */
    private void shouldDelete(final long id, final String name, final String ticker) throws Exception {

        mockMvc.perform(delete("/stock/" + id)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.ticker").value(ticker));

    }

}
