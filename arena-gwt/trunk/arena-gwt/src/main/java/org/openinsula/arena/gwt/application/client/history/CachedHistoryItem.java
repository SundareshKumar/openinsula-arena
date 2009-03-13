package org.openinsula.arena.gwt.application.client.history;

/**
 * @author Lucas K Mogari
 */
public abstract class CachedHistoryItem extends LazyHistoryItem {

	private Object target;

	public CachedHistoryItem(String token) {
		super(token);
	}

	protected abstract Object initTarget();

	public final Object getTarget() {
		if (target == null) {
			target = initTarget();
		}
		return target;
	}

}
