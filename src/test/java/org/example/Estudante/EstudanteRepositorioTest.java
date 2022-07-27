package org.example.Estudante;

import org.example.model.Estudante;
import org.junit.*;

import static org.junit.Assert.*;

public class EstudanteRepositorioTest {

    private static EstudanteRepositorio estudanteRepositorio;

    @BeforeClass
    public static void beforeClass() throws  Exception{
        estudanteRepositorio = new EstudanteRepositorio("estudante_pu_teste");
    }

    @AfterClass
    public static void afterClass() throws Exception{
        estudanteRepositorio.close();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void find() {
        Estudante estudante = new Estudante();
        estudante.setNome("Noret");
        estudante.setSobrenome("Marco");
        estudanteRepositorio.add(estudante);
        estudante = estudanteRepositorio.find(estudante.getId());

        assertNotNull(estudante);
        assertNotNull(estudante.getId());
        assertEquals("Noret",estudante.getNome());
    }

    @Test
    public void add() {
        Estudante estudante = new Estudante();
        estudante.setNome("João");
        estudante.setSobrenome("Marcio");

        estudanteRepositorio.add(estudante);
        assertNotNull(estudante.getId());
        assertTrue(estudante.getId().equals(1L));
    }

    @Test
    public void update() {
        Estudante estudante;
        estudante = estudanteRepositorio.find(1L);
        estudante.setSobrenome("Louis");
        estudante = estudanteRepositorio.update(estudante.getId(), estudante.getNome(), estudante.getSobrenome());

        assertNotNull(estudante);
        assertEquals("Louis",estudante.getSobrenome());
    }

    @Test
    public void delete() {
        Estudante estudante;
        estudante = estudanteRepositorio.find(3L);
        estudanteRepositorio.delete(estudante);
        estudante = estudanteRepositorio.find(estudante.getId());
        assertNull(estudante);
    }
}