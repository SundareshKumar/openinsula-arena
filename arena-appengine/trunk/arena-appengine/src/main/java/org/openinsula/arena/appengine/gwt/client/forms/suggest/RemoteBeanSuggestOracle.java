package org.openinsula.arena.appengine.gwt.client.forms.suggest;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SuggestOracle;

public abstract class RemoteBeanSuggestOracle<T> extends SuggestOracle {
	private static HTML convertMe = new HTML();

	public static String escapeText(final String escapeMe) {
		convertMe.setText(escapeMe);
		String escaped = convertMe.getHTML();
		return escaped;
	}

	/**
	 * Simpler than the Google MultiWordSuggest highlighter in that it will only
	 * highlight the first occurrence
	 *
	 * @param candidate
	 * @param query
	 * @return
	 */
	public static String highlight(final String candidate, String query) {

		int index = 0;
		int cursor = 0;

		// Create strong search string.
		StringBuilder accum = new StringBuilder();

		query = query.toLowerCase();

		index = candidate.toLowerCase().indexOf(query, index);

		if (index != -1) {

			int endIndex = index + query.length();
			String part1 = escapeText(candidate.substring(cursor, index));
			String part2 = escapeText(candidate.substring(index, endIndex));
			cursor = endIndex;
			accum.append(part1).append("<strong>").append(part2).append("</strong>");
		}

		// Finish creating the formatted string.
		String end = candidate.substring(cursor);
		accum.append(escapeText(end));

		return accum.toString();
	}

	public void requestSuggestions(final Request request, final Callback callback) {
		getRemoteCandidates(request, new AsyncCallback<List<T>>() {

			public void onFailure(final Throwable e) {
				e.printStackTrace();
			}

			public void onSuccess(final List<T> result) {
				List<BeanSuggestion<T>> suggestions = new ArrayList<BeanSuggestion<T>>();
				for (T candidate : result) {
					suggestions.add(getBeanSuggestionFor(candidate, request));
				}
				callback.onSuggestionsReady(request, new Response(suggestions));
			}

		});

	}

	protected BeanSuggestion<T> getBeanSuggestionFor(final T bean, final Request request) {
		return new BeanSuggestion<T>(bean, request.getQuery());
	}

	protected abstract void getRemoteCandidates(Request request, AsyncCallback<List<T>> callback);

}
