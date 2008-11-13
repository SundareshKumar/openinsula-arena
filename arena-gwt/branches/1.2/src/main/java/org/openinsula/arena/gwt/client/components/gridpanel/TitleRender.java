package org.openinsula.arena.gwt.client.components.gridpanel;

import com.google.gwt.user.client.ui.Widget;

public interface TitleRender<W extends Widget, T> {

	W render(T value);

}
