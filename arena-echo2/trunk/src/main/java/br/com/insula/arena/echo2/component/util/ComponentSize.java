package br.com.insula.arena.echo2.component.util;

/**
 * Enum responsável por definir o tamanho dos componentes (px).
 *
 * @author utiumi
 */
public enum ComponentSize {

	/** Tamanho 1 (30px). */
	SIZE_1(30),

	/** Tamanho 2 (80px). */
	SIZE_2(80),

	/** Tamanho 3 (120px). */
	SIZE_3(120),

	/** Tamanho 4 (220px). */
	SIZE_4(220),

	/** Tamanho 5 (320px). */
	SIZE_5(320);

	/** Atributo que guarda o tamanho do componente. */
	private Integer size;

	/**
	 * Constrói um novo <code>ComponentSize</code>.
	 *
	 * @param paramSize tamanho do componente.
	 */
	private ComponentSize(final Integer paramSize) {
		size = paramSize;
	}

	/**
	 * Getter para o atributo <code>size</code>.
	 *
	 * @return tamanho do componente.
	 */
	public Integer getSize() {
		return size;
	}
}
