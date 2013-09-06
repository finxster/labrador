package br.com.maps.labrador.helper;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.maps.labrador.domain.livro.Livro;


public class IsbnDBHelperTest {

    @Test
    public void testGetLivroByAuthor() {
        Livro livro = new Livro() {
            
        };
        
        IsbnDBHelper.getLivroByAutor("eduardo spohr", livro);
        
        assertEquals("EDUARDO SPOHR", livro.getAutor());
    }
    
}
