package org.openinsula.arena.echo2.sessionmanagement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class ActiveSession<T> {

	private static ServletContext servletContext;
	
	/**
	 * Este método deve ser invocado antes do uso da classe, e em um local aonde
	 * seja em comum para as sessões. Fazendo com que apenas uma instancia seja criada.
	 * @param <V>
	 * @param servletContext
	 * @param type
	 */
	public static <V> void setCurrent(ServletContext servletContext, Class<V> type) {
		if (getCurrent(type) == null) {
			new ActiveSession<V>(servletContext);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <V> ActiveSession<V> getCurrent(Class<V> type) {
		if (servletContext != null) {
			return (ActiveSession<V>) servletContext.getAttribute(ActiveSession.ACTIVE_SESSION_ATT);
		}

		return null;
	}
	
	public static final String ACTIVE_SESSION_ATT = "activeSession";
	
	private Map<HttpSession, T> sessions = new HashMap<HttpSession, T>();
	
	/**
	 * Constrói a instancia e adiciona nos attributos do servletcontext
	 * @param servletContext
	 */
	public ActiveSession(ServletContext servletContext) {
		super();
		ActiveSession.servletContext = servletContext;
		ActiveSession.servletContext.setAttribute(ActiveSession.ACTIVE_SESSION_ATT, this);
	}
	
	/**
	 * Adiciona uma nova HttpSession sem valor
	 * @param httpSession
	 */
	public void addSession(HttpSession httpSession) {
		sessions.put(httpSession, null);
	}
	
	/**
	 * Adiciona ou modifica uma HttpSession 
	 * @param httpSession
	 * @param t
	 */
	public void setSessionValue(HttpSession httpSession, T t) {
		sessions.put(httpSession, t);
	}
	
	/**
	 * @param t
	 * @return Retorna a sessão de acordo com o objeto passado por parâmetro
	 */
	public HttpSession getSession(T t) {
		if (t != null && sessions.containsValue(t)) {
			for (Entry<HttpSession, T> value : sessions.entrySet()) {
				if (t.equals(value.getValue())) {
					return value.getKey();
				}
			}
		}
		return null;
	}
	
	/**
	 * @param httpSession
	 * @return Retorna o objeto guardado para a sessão passada por parâmetro
	 */
	public T getSessionValue(HttpSession httpSession) {
		return sessions.get(httpSession);
	}
	
	/**
	 * Remove a sessão e retorna o objeto para a sessão passada por parâmetro
	 * @param httpSession
	 * @return
	 */
	public T removeSession(HttpSession httpSession) {
		return sessions.remove(httpSession);
	}
	
	/**
	 * @return Todas as HttpSessions inseridas.
	 */
	public Set<HttpSession> getAllHttpSession() {
		return sessions.keySet();
	}
	
	/**
	 * @return Todos os valores inseridos com T.
	 */
	public Collection<T> getAllValues() {
		return sessions.values();
	}
}
