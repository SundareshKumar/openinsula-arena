package org.openinsula.arena.gwt.client.ui;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FocusListenerAdapter;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.SourcesChangeEvents;
import com.google.gwt.user.client.ui.SourcesClickEvents;
import com.google.gwt.user.client.ui.SourcesKeyboardEvents;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

public abstract class FocusUtils {

	public static void deferredFocus(final HasFocus element) {
		DeferredCommand.addCommand(new Command() {
			public void execute() {
				element.setFocus(true);
			}
		});
	}

	public static void selectAllOnFocus(final TextBoxBase target) {
		target.addFocusListener(new FocusListenerAdapter() {
			@Override
			public void onFocus(final Widget sender) {
				target.selectAll();
			}
		});
	}

	public static void nextOnKeyUp(final int keyCode, final SourcesKeyboardEvents source, final HasFocus target) {
		source.addKeyboardListener(new KeyboardListenerAdapter() {
			@Override
			public void onKeyUp(final Widget sender, final char key, final int modifiers) {
				if (key == keyCode) {
					deferredFocus(target);
				}
			}
		});
	}

	public static void nextOnKeyDown(final int keyCode, final int modifiers, final SourcesKeyboardEvents source, final HasFocus target) {
		source.addKeyboardListener(new KeyboardListenerAdapter() {
			@Override
			public void onKeyDown(final Widget sender, final char k, final int m) {
				if (k == keyCode && m == modifiers) {
					deferredFocus(target);
				}
			}
		});
	}

	public static void nextOnEnter(final SourcesKeyboardEvents source, final HasFocus target) {
		nextOnKeyUp(KeyboardListener.KEY_ENTER, source, target);
	}

	public static void nextOnTab(final SourcesKeyboardEvents source, final HasFocus target) {
		nextOnKeyUp(KeyboardListener.KEY_TAB, source, target);
	}

	public static void nextOnChange(final SourcesChangeEvents source, final HasFocus target) {
		source.addChangeListener(new ChangeListener() {
			public void onChange(final Widget sender) {
				deferredFocus(target);
			}
		});
	}

	public static void nextOnClick(final SourcesClickEvents source, final HasFocus target) {
		source.addClickListener(new ClickListener() {
			public void onClick(final Widget sender) {
				deferredFocus(target);
			}
		});
	}

}
