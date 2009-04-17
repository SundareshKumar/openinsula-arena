package org.openinsula.arena.gwt.client.components.cardpanel.wizard;

import java.util.HashMap;
import java.util.Map;

import org.openinsula.arena.gwt.client.components.cardpanel.AbstractCard;
import org.openinsula.arena.gwt.client.components.cardpanel.Card;
import org.openinsula.arena.gwt.client.components.cardpanel.CardChangeAdapterImpl;
import org.openinsula.arena.gwt.client.components.cardpanel.CardChangeEvent;
import org.openinsula.arena.gwt.client.components.cardpanel.CardPanel;
import org.openinsula.arena.gwt.client.components.cardpanel.Sequence;

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

	private Map<Card, WizardNavigatorListener> navigatiorListenerMap = new HashMap<Card, WizardNavigatorListener>();

	{
		initComponents();
		customizeComponents();
		initActions();
	}

	public void setCardSequence(Sequence<Card> cardSequence) {
		cardPanel.setCardSequence(cardSequence);
	}

	public void addCard(final AbstractCard card) {
		cardPanel.addCard(card);
	}

	public void addCard(final AbstractCard card, WizardNavigatorListener listener) {
		navigatiorListenerMap.put(card, listener);
		addCard(card);
	}

	public void showCard(Card card) {
		cardPanel.showCard(card);
	}

	private void customizeComponents() {
		cardPanel.setWidth("100%");
	}

	public Card getVisibleCard() {
		return cardPanel.getCard(cardPanel.getVisibleWidget());
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
