package org.openinsula.arena.gwt.client.ui.view;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Widget;

public class AdvancedDeckPanel extends DeckPanel implements DeckPanelAware {

	private boolean cascadeShowHideNotifications = true;

	private boolean wasShowed = true;

	public void setCascadeShowHideNotifications(
			final boolean cascadeShowHideNotifications) {
		this.cascadeShowHideNotifications = cascadeShowHideNotifications;
	}

	public boolean wasShowed() {
		return wasShowed;
	}

	@Override
	public void showWidget(final int index) {
		int actualIdx = getVisibleWidget();

		if (actualIdx != -1) {
			wasShowed = notifyOnHide(getWidget(actualIdx));
		}

		if (index != -1) {
			notifyOnShow(getWidget(index));
		}

		if (wasShowed) {
			super.showWidget(index);
		}
	}

	private boolean notifyOnHide(final Widget widget) {
		if (widget instanceof DeckPanelAware) {
			return ((DeckPanelAware) widget).onHide();
		}
		return true;
	}

	private void notifyOnShow(final Widget widget) {
		if (widget instanceof DeckPanelAware) {
			((DeckPanelAware) widget).onShow();
		}
	}

	public boolean onHide() {
		boolean hide = true;
		if (cascadeShowHideNotifications) {
			for (int i = 0, j = getWidgetCount(); i < j; i++) {
				hide = hide && notifyOnHide(getWidget(i));
			}
		}
		return hide;
	}

	public void onShow() {
	}

}
