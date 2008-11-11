package org.openinsula.arena.gwt.samples.client;

import org.openinsula.arena.gwt.serialization.client.xml.XMLSerializable;
import org.openinsula.arena.gwt.serialization.client.xml.XMLSerializerImpl;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;

public class XMLSerializable_TypeSerializer extends XMLSerializerImpl {
	public class RoleXMLSerializerImpl extends XMLSerializerImpl {
		public RoleXMLSerializerImpl() {

		}

		public String toXML(final Document document, final Element parent, final XMLSerializable obj) {
			Element root = document.createElement("role");
			Node rootParent = parent == null ? document : parent;
			rootParent.appendChild(root);
			
			org.openinsula.arena.gwt.samples.client.vo.Role objImpl = (org.openinsula.arena.gwt.samples.client.vo.Role) obj;
			writeSimpleAttribute("id", objImpl.getId(), root, document);
			writeSimpleAttribute("admin", objImpl.isAdmin(), root, document);
			{
				org.openinsula.arena.gwt.samples.client.vo.User[] array = objImpl.getUserArray();
				if (array != null && array.length > 0) {
					Element list = document.createElement("userArray");
					root.appendChild(list);
					for (org.openinsula.arena.gwt.samples.client.vo.User var : array) {
						XMLSerializable_TypeSerializer.this.toXML(document, list, var);

					}

				}

			}
			return null;
		}

		public XMLSerializable fromXML(final Element element) {
			return null;
		}

	}

	public class UserXMLSerializerImpl extends XMLSerializerImpl {
		public UserXMLSerializerImpl() {

		}

		public String toXML(final Document document, final Element parent, final XMLSerializable obj) {
			Element root = document.createElement("user");
			if (parent != null) {
				parent.appendChild(root);
			}
			org.openinsula.arena.gwt.samples.client.vo.User objImpl = (org.openinsula.arena.gwt.samples.client.vo.User) obj;
			writeSimpleAttribute("id", objImpl.getId(), root, document);
			writeSimpleAttribute("username", objImpl.getUsername(), root, document);
			XMLSerializable_TypeSerializer.this.toXML(document, root, objImpl.getRole());
			{
				org.openinsula.arena.gwt.samples.client.vo.Role[] array = objImpl.getRoleArray();
				if (array != null && array.length > 0) {
					Element list = document.createElement("roleArray");
					root.appendChild(list);
					for (org.openinsula.arena.gwt.samples.client.vo.Role var : array) {
						XMLSerializable_TypeSerializer.this.toXML(document, list, var);

					}

				}

			}
			{
				long[] array = objImpl.getLongArray();
				if (array != null && array.length > 0) {
					Element list = document.createElement("longArray");
					root.appendChild(list);
					for (long var : array) {
						writeSimpleAttribute("item", var, list, document);

					}

				}

			}
			{
				java.lang.Integer[] array = objImpl.getIntegerArray();
				if (array != null && array.length > 0) {
					Element list = document.createElement("integerArray");
					root.appendChild(list);
					for (java.lang.Integer var : array) {
						writeSimpleAttribute("item", var, list, document);

					}

				}

			}
			return null;
		}

		public XMLSerializable fromXML(final Element element) {
			return null;
		}

	}

	public XMLSerializable_TypeSerializer() {
		addType("role", new RoleXMLSerializerImpl());
		addType("user", new UserXMLSerializerImpl());

	}
}
