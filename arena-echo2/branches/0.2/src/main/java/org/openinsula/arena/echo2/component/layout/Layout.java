package org.openinsula.arena.echo2.component.layout;

import nextapp.echo2.app.Component;

public interface Layout extends LayoutElement {

	/**
	 * Adiciona um component.
	 * @param component
	 */
	void addComponent(Component component);

	/**
	 * Adiciona um component com o caption como string.
	 * @param caption
	 * @param component
	 */
	void addComponent(String caption, Component component);

	/**
	 * Adiciona um component com outro component como caption.
	 * @param caption
	 * @param component
	 */
	void addComponent(Component caption, Component component);

	/**
	 * Adiciona um estilo ao DIV que encapsula os componentes que vierem depois
	 * @param name
	 * @param value
	 */
	public void addStyle(String name, Object value);

	/**
	 * Adiciona um layout dentro de outro
	 * @param layout
	 */
	public void addLayout(Layout layout);

	/**
	 * Adiciona uma propriedade de um Style para ser adicionado a um componente
	 * @param klazz
	 * @param name
	 * @param value
	 */
	public void addComponentStyle(Class<? extends Component> klazz, String name, Object value);

	/**
	 * Renderiza o layout.
	 * @return Component renderizado como layout.
	 */
	Component buildLayout();

}
