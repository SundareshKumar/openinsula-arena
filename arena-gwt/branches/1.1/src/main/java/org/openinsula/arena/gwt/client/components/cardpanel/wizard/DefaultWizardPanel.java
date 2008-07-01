package org.openinsula.arena.gwt.client.components.cardpanel.wizard;

import org.openinsula.arena.gwt.client.components.cardpanel.AbstractCard;
import org.openinsula.arena.gwt.client.components.cardpanel.Card;
import org.openinsula.arena.gwt.client.components.cardpanel.CardChangeAdapterImpl;
import org.openinsula.arena.gwt.client.components.cardpanel.CardChangeEvent;
import org.openinsula.arena.gwt.client.components.cardpanel.CardPanel;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DefaultWizardPanel extends VerticalPanel {

	private FlowPanel buttonPanel;
	
	private Button nextButton;
	
	private Button previousButton;
	
	private CardPanel cardPanel;
	
	{
		initComponents();
		customizeComponents();
		initActions();
	}
	
	public void addCard(final AbstractCard card) {
		cardPanel.addCard(card);
	}

	public void showCard(Card card) {
		cardPanel.showCard(card);
	}
	
	private void customizeComponents() {
		cardPanel.setWidth("100%");
	}
	
	private void initActions() {
		nextButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				cardPanel.next();
				refreshButtonBar();
			}
		});
		
		previousButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				cardPanel.previous();
				refreshButtonBar();
			}
		});
		
		cardPanel.addCardChangeListener(new CardChangeAdapterImpl() {
			public boolean onShow(CardChangeEvent event) {
				nextButton.setText(event.getNewCard().getNextLabel());
				previousButton.setText(event.getNewCard().getPreviousLabel());
				return true;
			}
		});
	}
	
	private void refreshButtonBar() {
		nextButton.setVisible(cardPanel.hasNext());
		previousButton.setVisible(cardPanel.hasPrevious());
	}
	
	public void pack() {
		cardPanel.pack();
		refreshButtonBar();
	}
	
	private void initComponents() {
		cardPanel = new CardPanel();
		
		buttonPanel = new FlowPanel();
		buttonPanel.setStyleName("insula-wizardButtonBar");
		nextButton = new Button();
		previousButton = new Button();
		
		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);
		
		add(cardPanel);
		add(buttonPanel);
	}
	
}
