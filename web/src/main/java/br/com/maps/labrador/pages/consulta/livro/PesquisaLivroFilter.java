package br.com.maps.labrador.pages.consulta.livro;

import java.io.Serializable;

import jmine.tec.web.wicket.component.injection.FormInputProvider;
import jmine.tec.web.wicket.component.injection.composite.LabeledFormInput;

/**
 * Filtro para a tela de pesquisa de livros.
 * 
 * @author laercio.duarte
 * @created Aug 29, 2013
 */
@FormInputProvider
public class PesquisaLivroFilter implements Serializable  {

	private String isbn10;

	private String isbn13;
	
	private String titulo;
	
	private String autor;
	
	private String editora;

	/**
	 * @return isbn10
	 */
	public String getIsbn10() {
		return isbn10;
	}

	/**
	 * @param isbn10 the isbn10 to set
	 */
	@LabeledFormInput(label = "ISBN 10")
	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	/**
	 * @return isbn13
	 */
	public String getIsbn13() {
		return isbn13;
	}

	/**
	 * @param isbn13 the isbn13 to set
	 */
	@LabeledFormInput(label = "ISBN 13")
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	/**
	 * @return titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	@LabeledFormInput(label = "Título")
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return autor
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * @param autor the autor to set
	 */
	@LabeledFormInput(label = "Autor")
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * @return editora
	 */
	public String getEditora() {
		return editora;
	}

	/**
	 * @param editora the editora to set
	 */
	@LabeledFormInput(label = "Editora")
	public void setEditora(String editora) {
		this.editora = editora;
	}
}
