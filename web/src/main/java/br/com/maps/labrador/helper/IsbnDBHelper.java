package br.com.maps.labrador.helper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import br.com.maps.labrador.LabradorWebException;
import br.com.maps.labrador.LabradorWebMessages;
import br.com.maps.labrador.domain.livro.Livro;

public class IsbnDBHelper {

    // XXX (diego.ferreira) este parâmetro deverá ser configurado em um ".properties" e injetado via spring
    private static final String MAPS_ISBNDB_COD = "SQGBZAKH";

    public static void getLivroByISBN10(String isbn10, Livro livro) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            URL url = new URL("http://wwwb.isbndb.com/api/v2/json/" + MAPS_ISBNDB_COD + "/book/" + isbn10 + "").toURI().toURL();
            try {
                Map<String, Object> mp = mapper.readValue(url, new TypeReference<Map<String, Object>>() {
                });

                if (mp.get("error") != null) {
                    throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(isbn10));
                }
                hidrateEntity(mp, livro);
            } catch (JsonParseException e) {
                e.printStackTrace();
                throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(isbn10));
            } catch (JsonMappingException e) {
                throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(isbn10));
            } catch (IOException e) {
                if (e instanceof UnknownHostException || e instanceof SocketException) {
                    throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN_REDE_INDISPONIVEL.create(isbn10));
                }
                throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(isbn10));
            }
        } catch (MalformedURLException e) {
            throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(isbn10));
        } catch (URISyntaxException e) {
            throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(isbn10));
        }
    }

    public static void getLivroByAutor(String autor, Livro livro) {
        autor = autor.replace(" ", "+");
        try {
            ObjectMapper mapper = new ObjectMapper();
            URL url = new URL("http://wwwb.isbndb.com/api/v2/json/" + MAPS_ISBNDB_COD + "/authors?q=" + autor + "").toURI().toURL();
            try {
                Map<String, Object> mp = mapper.readValue(url, new TypeReference<Map<String, Object>>() {
                });

                if (mp.get("error") != null) {
                    throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(autor));
                }
                hidrateEntityByAuthor(mp, livro);
            } catch (JsonParseException e) {
                e.printStackTrace();
                throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(autor));
            } catch (JsonMappingException e) {
                throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(autor));
            } catch (IOException e) {
                if (e instanceof UnknownHostException || e instanceof SocketException) {
                    throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN_REDE_INDISPONIVEL.create(autor));
                }
                throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(autor));
            }
        } catch (MalformedURLException e) {
            throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(autor));
        } catch (URISyntaxException e) {
            throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(autor));
        }
    }
    
    private static void hidrateEntityByAuthor(Map<String, Object> mp, Livro livro) {
        Map<String, Object> map = (Map<String, Object>) ((List) mp.get("data")).get(0);
        livro.setAutor(safeCheck(map.get("name")));
        livro.setIsbn13(safeCheck(map.get("isbn13")));
        livro.setEditora(safeCheck(map.get("publisher_name")));
        livro.setTitulo(safeCheck(map.get("title")));
    }
    
    /**
     * Efetua o parte do {@link Map} com as informações obtidas no isbndb e hidrata a instância de livro informada.
     * 
     * @param mp {@link Map}
     * @param livro {@link Livro}
     */
    private static void hidrateEntity(Map<String, Object> mp, Livro livro) {
        Map<String, Object> map = (Map<String, Object>) ((List) mp.get("data")).get(0);
        livro.setIsbn10(safeCheck(map.get("isbn10")));
        livro.setIsbn13(safeCheck(map.get("isbn13")));
        livro.setEditora(safeCheck(map.get("publisher_text")));
        livro.setTitulo(safeCheck(map.get("title")));

        List autores = (List) map.get("author_data");
        if (autores != null && !autores.isEmpty()) {
            Map<String, Object> autoresMap = (Map<String, Object>) autores.get(0);
            livro.setAutor(safeCheck(autoresMap.get("name")));
        }
    }

    /**
     * Efetua um null check na string informada.
     * 
     * @param str string que será analisada
     * @return a própria string caso a mesma não seja nula.
     */
    private static String safeCheck(Object str) {
        if (!StringUtils.isEmpty((String) str)) {
            return str.toString();
        }
        return null;
    }

}
