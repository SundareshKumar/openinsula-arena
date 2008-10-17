package org.openinsula.arena.gwt.client.ui.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

public abstract class NavigationSupport extends Composite implements
		HistoryListener {

	private final List<Hyperlink> links;

	private final Map<String, Integer> viewStack;

	private final AdvancedDeckPanel viewDeck;

	private String defaultToken;

	private String linkStyleName;

	private String linkSelectedStyleName;

	private String viewDeckStyleName;

	public NavigationSupport() {
		links = new ArrayList<Hyperlink>();
		viewStack = new HashMap<String, Integer>();
		viewDeck = new AdvancedDeckPanel();

		setLinkStyleName("NavigationLink");
		setViewDeckStyleName("NavigationViewDeck");

		History.addHistoryListener(this);
	}

	public void setLinkStyleName(final String linkStyleName) {
		this.linkStyleName = linkStyleName;
		this.linkSelectedStyleName = linkStyleName + "-selected";
	}

	public void setViewDeckStyleName(final String viewDeckStyleName) {
		this.viewDeckStyleName = viewDeckStyleName;
	}

	public List<Hyperlink> getLinks() {
		return links;
	}

	public DeckPanel getViewDeck() {
		return viewDeck;
	}

	public void addView(final String label, final Widget view) {
		addView(label, view, false);
	}

	public void addView(final String label, final Widget view, final boolean defaultView) {
		addView(label, view, defaultView, null);
	}

	public void addView(final String label, final Widget view, final ClickListener listener) {
		addView(label, view, false, listener);
	}

	public void addView(final String label, final Widget view, final boolean defaultView, final ClickListener listener) {
		String token = getHistoryToken(label, view);

		if (defaultView) {
			defaultToken = token;
		}

		viewDeck.add(view);
		viewStack.put(token, viewDeck.getWidgetCount() - 1);

		Hyperlink link = createLink(label, token);

		if (listener != null) {
			link.addClickListener(listener);
		}

		links.add(link);
	}

	protected String getHistoryToken(final String linkLabel, Widget view) {
		StringBuilder sb = new StringBuilder();
		sb.append(view.hashCode());
		
//		sb.append(hashCode());
//		sb.append('-');
//		sb.append(linkLabel.replaceAll("\\s", "").toUpperCase());

		return sb.toString();
	}

	protected Hyperlink createLink(final String text, final String historyToken) {
		Hyperlink link = new Hyperlink(text, historyToken);
		return link;
	}

	public void onHistoryChanged(final String historyToken) {
		if (viewStack.containsKey(historyToken)) {
			viewDeck.showWidget(viewStack.get(historyToken));

			if (viewDeck.wasShowed()) {
				for (Hyperlink link : links) {
					if (link.getTargetHistoryToken().equals(historyToken)) {
						link.setStyleName(linkSelectedStyleName);
					} else {
						link.setStyleName(linkStyleName);
					}
				}
			}
		}
	}

	public void pack() {
		if (defaultToken == null && !links.isEmpty()) {
			defaultToken = links.get(0).getTargetHistoryToken();
		}

		for (Hyperlink link : links) {
			link.setStyleName(linkStyleName);
		}

		viewDeck.setStyleName(viewDeckStyleName);
	}

	public final void showDefaultView() {
		if (defaultToken == null) {
			pack();
		}

		if (links.isEmpty()) {
			GWT.log("No view found!", null);
			return;
		}

		onHistoryChanged(defaultToken);
	}

}
