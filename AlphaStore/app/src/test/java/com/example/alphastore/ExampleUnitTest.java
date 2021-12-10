package com.example.alphastore;

import static org.junit.Assert.assertEquals;

import com.example.alphastore.model.Cliente;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void ValidaNomes() {
        assertEquals(true, Cliente.validaNome("Mario Joao"));
    }
    @Test
    public void NegativaValidaNomes() {
        assertEquals(false, Cliente.validaNome("Mario1 Joao"));
    }
    @Test
    public void ValidaEmail() {
        assertEquals(true, Cliente.validaEmail("daniel@gmail.com"));
    }
    @Test
    public void NegativaValidaEmail() {
        assertEquals(false, Cliente.validaEmail("daniel.com"));
    }
}