package org.openinsula.arena.echo2.component.pager;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.event.TableModelEvent;
import nextapp.echo2.app.event.TableModelListener;
import nextapp.echo2.app.table.AbstractTableModel;

import org.openinsula.arena.echo2.component.div.Div;
import org.openinsula.arena.echo2.component.model.PageableTableModel;

public class PagerRow extends Row {
	private static final long serialVersionUID = 1L;

	private PageableTableModel model;

	private int showPages;

	private PagerStyles pagerStyles;

	public PagerRow(PageableTableModel pageableTableModel) throws IllegalArgumentException {
		super();
		this.model = pageableTableModel;
		this.model.addTableModelListener(new TableModelListener() {
			private static final long serialVersionUID = 1L;

			public void tableChanged(TableModelEvent arg0) {

				if (model.getPageCount() < model.getCurrentPage()) {
					model.setCurrentPage(0);
				}

				PagerRow.this.renderPager();
			}
		});
	}

	public PagerRow(PageableTableModel model, PagerStyles pagerStyles) {
		this(model);
		this.pagerStyles = pagerStyles;
	}

	public void renderPager() throws RuntimeException {
		removeAll();

		int startPage = 0;
		int endPage = getTotalNumberPages();
		if (showPages > 0) {
			startPage = (getCurrentPage() / showPages) * showPages;
			endPage = Math.min(startPage + showPages, getTotalNumberPages());
		}

		BotoesControleActionListener botoesControleActionListener = new BotoesControleActionListener();

		// botao 'anterior'

		Button botaoAnterior = new Button();
		if (pagerStyles != null) {
			botaoAnterior.setStyle(pagerStyles.getBackPagerButtonStyle());
		}
		botaoAnterior.setEnabled(getCurrentPage() > 0);

		if (botaoAnterior.getStyle() == null
				|| botaoAnterior.getStyle().getProperty(Button.PROPERTY_BACKGROUND_IMAGE) == null) {
			botaoAnterior.setText("[Anterior]");
		}

		botaoAnterior.setActionCommand("anterior");
		botaoAnterior.addActionListener(botoesControleActionListener);
		this.add(botaoAnterior);

		// botao para retornar a paginacao das paginas
		if (startPage > 0) {
			Button botaoPaginacaoAnterior = new Button();
			if (pagerStyles != null) {
				botaoPaginacaoAnterior.setStyle(pagerStyles.getSkipPagerButtonStyle());
			}

			if (botaoPaginacaoAnterior.getStyle().getProperty(Button.PROPERTY_BACKGROUND_IMAGE) == null) {
				botaoPaginacaoAnterior.setText("[...]");
			}

			botaoPaginacaoAnterior.setActionCommand(Integer.toString(((startPage - 1))));
			botaoPaginacaoAnterior.addActionListener(new BotaoNumeradoActionListener());
			this.add(botaoPaginacaoAnterior);
		}

		// botoes com a numeracao
		for (int i = startPage; i < endPage; i++) {
			if (endPage - startPage > 0) {
				Button b = new Button();
				if (pagerStyles != null) {
					if (getCurrentPage() == i) {
						b.setStyle(pagerStyles.getSelectedPagerButtonStyle());
					}
					else {
						b.setStyle(pagerStyles.getPagerButtonStyle());
					}
				}
				if (b.getStyle() == null || b.getStyle().getProperty(Button.PROPERTY_BACKGROUND_IMAGE) == null) {
					b.setText("[" + (i + 1) + "]");
				}
				else {
					b.setText(Integer.toString(i + 1));
				}

				b.setActionCommand(new Integer(i).toString());
				b.addActionListener(new BotaoNumeradoActionListener());
				Div div = new Div();
				div.setMargin(new Extent(2, Extent.PX));
				div.add(b);
				this.add(div);
			}
		}

		// botao para avancar a paginacao das paginas
		if (endPage < getTotalNumberPages()) {
			Button botaoPaginacaoProxima = new Button();
			if (pagerStyles != null) {
				botaoPaginacaoProxima.setStyle(pagerStyles.getSkipPagerButtonStyle());
			}

			if (botaoPaginacaoProxima.getStyle() == null
					|| botaoPaginacaoProxima.getStyle().getProperty(Button.PROPERTY_BACKGROUND_IMAGE) == null) {
				botaoPaginacaoProxima.setText("[...]");
			}

			botaoPaginacaoProxima.setActionCommand(new Integer(endPage).toString());
			botaoPaginacaoProxima.addActionListener(new BotaoNumeradoActionListener());
			this.add(botaoPaginacaoProxima);
		}

		// botao 'proxima' pagina
		Button botaoProxima = new Button();
		if (pagerStyles != null) {
			botaoProxima.setStyle(pagerStyles.getNextPagerButtonStyle());
		}

		botaoProxima.setEnabled(getCurrentPage() < (getTotalNumberPages() - 1));

		if (botaoProxima.getStyle() == null
				|| botaoProxima.getStyle().getProperty(Button.PROPERTY_BACKGROUND_IMAGE) == null) {
			botaoProxima.setText("[Próxima]");
		}

		botaoProxima.setActionCommand("proxima");
		botaoProxima.addActionListener(botoesControleActionListener);
		this.add(botaoProxima);
	}

	private class BotaoNumeradoActionListener implements ActionListener {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent actionEvent) {
			int command = Integer.parseInt(actionEvent.getActionCommand());
			model.setCurrentPage(command);

			if (model instanceof AbstractTableModel) {
				((AbstractTableModel) model).fireTableDataChanged();
			}

			PagerRow.this.renderPager();
		}
	}

	private class BotoesControleActionListener implements ActionListener {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent actionEvent) {
			String command = actionEvent.getActionCommand();
			if ("anterior".equals(command)) {
				model.setCurrentPage(model.getCurrentPage() - 1);
			}
			else if ("proxima".equals(command)) {
				model.setCurrentPage(model.getCurrentPage() + 1);
			}

			if (model instanceof AbstractTableModel) {
				((AbstractTableModel) model).fireTableDataChanged();
			}

			PagerRow.this.renderPager();
		}
	}

	protected int getTotalNumberPages() {
		return model.getPageCount();
	}

	protected int getCurrentPage() {
		return model.getCurrentPage();
	}

	protected int getPageSize() {
		return model.getRowCount();
	}

	public int getShowPages() {
		return showPages;
	}

	public void setShowPages(int showPages) {
		this.showPages = showPages;
	}

	public PagerStyles getPagerStyles() {
		return pagerStyles;
	}

	public void setPagerStyles(PagerStyles pagerStyles) {
		this.pagerStyles = pagerStyles;
	}
}
