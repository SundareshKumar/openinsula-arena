package org.openinsula.arena.echo2.component.model.container;

import java.io.Serializable;
import java.util.Collection;

import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.table.TableModel;

public interface ContainerTableModel<T> extends TableModel {
	
	/**
	 * Remove all the beans from the container.
	 */
	public void clear();
	
	/**
	 * Adds a bean to the table model container.
	 * @param bean
	 */
	public void addItem(T bean);

	/**
	 * Adds one or more beans to the table model container.
	 * @param beans
	 */
	public void addItems(T ... beans);

	/**
	 * Adds a collection of beans to the table model container.
	 * @param beans
	 */
	public void addItems(Collection<T> beans);

	/**
	 * Set the entire table model container with new beans, removing the existing ones.
	 * @param beans
	 */
	public void setItems(Collection<T> beans);

	/**
	 * Get the selected bean by passing an ActionEvent from a button.
	 * @param actionEvent ActionEvent object from a Button.
	 * @return A bean of the specified generic type, return null if bean not found.
	 */
	public T getSelectedItem(ActionEvent actionEvent);
	
	/**
	 * Search the bean
	 * @param itemId The id of the bean, usually specified by the implemented ContainerTableModel.
	 * @return A bean of the specified generic type, return null if bean not found.
	 */
	public T getItemById(Serializable itemId);

	/**
	 * Returns the given id for the bean.
	 * @param bean
	 * @return
	 */
	public Serializable findIdFromBean(T bean);
	
	/**
	 * Search and remove the item found by a comparison with the bean passed by parameter.
	 * @param bean The beans that must be removed.
	 * @return False if not found or couldn't removed
	 */
	public boolean deleteItem(T bean);
	
	/**
	 * Search and remove the item found by a comparison with the bean passed by parameter.
	 * The id will be get from the ActionEvent.getActionCommand().
	 * @param actionEvent This is an actionEvent that came from a button.
	 * @return False if not found or couldn't removed
	 */
	public boolean deleteItem(ActionEvent actionEvent);
	
	/**
	 * Search and remove the item found by a comparison with the bean passed by parameter.
	 * @param itemId
	 * @return False if not found or couldn't removed
	 */
	public boolean deleteItem(Serializable itemId);
	
	/**
	 * @return The number of beans in the container
	 */
	public int size();
}
