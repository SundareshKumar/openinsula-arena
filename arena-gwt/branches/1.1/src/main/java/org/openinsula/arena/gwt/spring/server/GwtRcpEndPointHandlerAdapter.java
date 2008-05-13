package org.openinsula.arena.gwt.spring.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GwtRcpEndPointHandlerAdapter extends RemoteServiceServlet implements HandlerAdapter {

	private static ThreadLocal<Object> handlerHolder = new ThreadLocal<Object>();

	private static final long serialVersionUID = 1L;

	@Override
	public long getLastModified(final HttpServletRequest request, final Object handler) {
		return -1L;
	}

	@Override
	public ModelAndView handle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
		try {
			// store the handler for retrieval in processCall()
			handlerHolder.set(handler);
			doPost(request, response);
		} finally {
			// clear out thread local to avoid resource leak
			handlerHolder.set(null);
		}

		return null;
	}

	protected Object getCurrentHandler() {
		return handlerHolder.get();
	}

	@Override
	public boolean supports(final Object handler) {
		return (handler instanceof RemoteService) && handler.getClass().isAnnotationPresent(GwtRpcEndPoint.class);
	}

	@Override
	public String processCall(final String payload) throws SerializationException {
		System.out.println("executando processCall()");
		/*
		 * The code below is borrowed from RemoteServiceServet.processCall, with
		 * the following changes:
		 *
		 * 1) Changed object for decoding and invocation to be the handler
		 * (versus the original 'this')
		 */

		try {
			RPCRequest rpcRequest = RPC.decodeRequest(payload, getCurrentHandler().getClass());
			return RPC.invokeAndEncodeResponse(getCurrentHandler(), rpcRequest.getMethod(), rpcRequest.getParameters());
		} catch (Throwable t) {
			return RPC.encodeResponseForFailure(null, t);
		}
	}
}
