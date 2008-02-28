package org.openinsula.arena.echo2.component.model.info.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.table.DefaultTableModel;
import nextapp.echo2.app.table.TableColumnModel;

import org.apache.commons.beanutils.PropertyUtils;
import org.openinsula.arena.echo2.component.div.Div;
import org.openinsula.arena.echo2.component.model.info.InfoTableModel;
import org.openinsula.arena.echo2.component.util.FormFactory;
import org.openinsula.arena.echo2.component.util.Styles;
import org.springframework.beans.factory.annotation.Autowired;

public class InfoTableModelImpl<T> extends DefaultTableModel implements InfoTableModel<T> {
    private static final long serialVersionUID = 1L;

    private Map<String, String> columnMap = new LinkedHashMap<String, String>();

    private List<T> beanList = new ArrayList<T>();

    private boolean editColumn = true;

    private boolean deleteColumn = true;

    private ActionListener editButtonActionListener;

    private ActionListener deleteButtonActionListener;

    @Autowired(required = false)
    private Styles styles;
    
    private ImageReference editImage;

    private ImageReference deleteImage;
    
    {
    	if (styles != null) {
    		editImage = styles.getEditIcon();
    		deleteImage = styles.getDeleteIcon();
    	}
    }

    private TableColumnModel tableColumnModel;

    private List<Extent> widthColumns = new ArrayList<Extent>();

    public void setTableColumnModel(TableColumnModel tableColumnModel) {
        this.tableColumnModel = tableColumnModel;
    }

    public void addColumn(String label, String beanField) {
        addColumn(label, beanField, 0);
    }

    public void addColumn(String label, String beanField, int width) {
        columnMap.put(beanField, label);
        widthColumns.add(new Extent(width, Extent.PX));
        buildColumns();
    }

    public void clear() {
        while (getRowCount() > 0) {
            deleteRow(0);
        }

        beanList.clear();
    }

    public void setRows(Collection<T> beans) {
        clear();
        for (T bean : beans) {
            addRow(bean);
        }
    }

    public void addAll(Collection<T> collection) {
		for (T t : collection) {
			addRow(t);
		}
	}

	public void addRow(T bean) {
        List<Object> row = new ArrayList<Object>();

        int columnIndex = 0;
        for (String field : columnMap.keySet()) {
            try {
                String valorCampo = formatProperty(field, PropertyUtils.getProperty(bean, field)).toString();

                Div columnDiv = new Div();
                columnDiv.setOverflow(Div.OVERFLOW_HIDDEN);
                columnDiv.setToolTipText(valorCampo);

                if (tableColumnModel != null && widthColumns.get(columnIndex) != null) {
                    tableColumnModel.getColumn(columnIndex).setWidth(widthColumns.get(columnIndex));
                }

                Label valorDescricaoLabel = new Label();
                valorDescricaoLabel.setText(valorCampo);
                valorDescricaoLabel.setLineWrap(false);
                valorDescricaoLabel.setFont(new Font(Font.VERDANA, Font.PLAIN, new Extent(10, Extent.PX)));

                columnDiv.add(valorDescricaoLabel);

                row.add(columnDiv);
                columnIndex++;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        long beanId = -1;
        try {
            beanId = getBeanId(bean);
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (NoSuchMethodException e) {
        }
        
        if (isEditColumn()) {
        	row.add(buildEditButton(bean, beanId));
        }
        
        if (isDeleteColumn()) {
            row.add(buildDeleteButton(bean, beanId));
        }

        if (!beanList.contains(bean)) {
            super.addRow(row.toArray());
            beanList.add(bean);
        }

    }
    
    /**
     * Constrói o botão de deletar
     * @param bean
     * @param beanId
     * @return
     */
    protected Button buildDeleteButton(T bean, long beanId) {
    	Button button = FormFactory.iconButton("Remover", getDeleteImage());
    	if (deleteButtonActionListener != null && beanId > -1) {
    		button.setActionCommand(Long.toString(beanId));
    		button.addActionListener(deleteButtonActionListener);
    	}

    	return button;
    }

    /**
     * Constrói o botão de editar
     * @param bean
     * @return
     */
    protected Button buildEditButton(T bean, long beanId) {
    	Button button = FormFactory.iconButton("Selecionar", getEditImage());
    	if (editButtonActionListener != null && beanId > -1) {
    		button.setActionCommand(Long.toString(beanId));
    		button.addActionListener(editButtonActionListener);
    	}
    	return button;
    }
    
	/*
     * Método que recebe o nome e o valor da propriedade para ser formatado em
     * sub-classes
     */
    protected Object formatProperty(String property, Object value) {
        if (value instanceof Date) {
        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return sdf.format((Date)value);
        }

        return value;
    }

    public long getSelectedBeanId(ActionEvent ae) {
        return Long.parseLong(ae.getActionCommand());
    }

    public long getBeanId(T bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    	return (Long) PropertyUtils.getProperty(bean, "id");
    }
    
    public void deleteRow(long beanId) {
        int index = -1;
        int counter = 0;

        T bean = null; 
        
        for (T item : beanList) {
            try {
				if (getBeanId(item) == beanId) {
				    index = counter;
				    bean = item;
				} else {
				    counter++;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
        }
        if (index >= 0) {
            super.deleteRow(index);
            beanList.remove(bean);
        }
    }
    
    public void deleteRow(T bean) {
        int index = -1;
        int counter = 0;

        for (Object item : beanList) {
            if (item.equals(bean)) {
                index = counter;
            } else {
                counter++;
            }
        }
        if (index >= 0) {
            super.deleteRow(index);
            beanList.remove(bean);
        }
    }

    private void buildColumns() {
        int columnCount = columnMap.size();
        if (isEditColumn())
            columnCount++;
        if (isDeleteColumn())
            columnCount++;
        setColumnCount(columnCount);

        int columnIndex = 0;
        for (String column : columnMap.values()) {
            setColumnName(columnIndex, column);
            columnIndex++;
        }

        if (isEditColumn()) {
            setColumnName(columnIndex, "");
            columnIndex++;
        }
        if (isDeleteColumn()) {
            setColumnName(columnIndex, "");
            columnIndex++;
        }
    }

    public List<T> getBeanList() {
        return beanList;
    }

    public boolean isDeleteColumn() {
        return deleteColumn;
    }

    public void setDeleteColumn(boolean deleteColumn) {
        this.deleteColumn = deleteColumn;
        buildColumns();
    }

    public boolean isEditColumn() {
        return editColumn;
    }

    public void setEditColumn(boolean editColumn) {
        this.editColumn = editColumn;
        buildColumns();
    }

    public ActionListener getDeleteButtonActionListener() {
        return deleteButtonActionListener;
    }

    public void setDeleteButtonActionListener(ActionListener deleteButtonActionListener) {
        this.deleteButtonActionListener = deleteButtonActionListener;
    }

    public ActionListener getEditButtonActionListener() {
        return editButtonActionListener;
    }

    public void setEditButtonActionListener(ActionListener editButtonActionListener) {
        this.editButtonActionListener = editButtonActionListener;
    }

    public ImageReference getEditImage() {
        return editImage;
    }

    public void setEditImage(ImageReference editImage) {
        this.editImage = editImage;
    }

    public ImageReference getDeleteImage() {
        return deleteImage;
    }

    public void setDeleteImage(ImageReference deleteImage) {
        this.deleteImage = deleteImage;
    }

    private int currentPage = 0;
    
    private int pageSize = 10;
    
	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageCount() {
		int pageCount = (beanList.size() / pageSize);
		
		if ( (beanList.size() % pageSize) != 0 ) {
			pageCount++;
		}
		
		return pageCount;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public Styles getStyles() {
		return styles;
	}

	public void setStyles(Styles styles) {
		this.styles = styles;
	}
}