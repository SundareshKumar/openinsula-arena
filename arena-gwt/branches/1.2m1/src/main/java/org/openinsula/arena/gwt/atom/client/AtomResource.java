package org.openinsula.arena.gwt.atom.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Lucas K Mogari
 */
public abstract class AtomResource {

	private Text title;

	private String id;

	private Date updated;

	private Text rights;

	private List<Person> authors;

	private List<Person> contributors;

	private List<Category> categories;

	private List<Link> links;

	public AtomResource() {
	}

	public AtomResource(String id, String title) {
		this.title = new Text(title);
		this.id = id;
	}

	public Text getTitle() {
		return title;
	}

	public void setTitle(Text title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void addAuthor(Person author) {
		authors.add(author);
	}

	public void removeAuthor(Person author) {
		authors.remove(author);
	}

	public List<Person> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Person> authors) {
		this.authors = authors;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public void addLink(Link link) {
		links.add(link);
	}

	public List<Link> getLinks() {
		return links;
	}

	public Link getLink(String rel, String type) {
		for (final Link link : links) {
			if (link.matches(rel, type)) {
				return link;
			}
		}

		return null;
	}

	public List<Link> getLinks(String rel, String type) {
		final List<Link> result = new ArrayList<Link>();

		for (final Link link : links) {
			if (link.matches(rel, type)) {
				result.add(link);
			}
		}
		return result;
	}

	public Link getSelfLink() {
		return getLink(Link.Rel.SELF, Link.Type.ATOM);
	}

	public Link getEditLink() {
		return getLink(Link.Rel.ENTRY_EDIT, Link.Type.ATOM);
	}

	public String getSelfUrl() {
		final Link selfLink = getSelfLink();
		return selfLink != null ? selfLink.getHref() : null;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public void addCategory(Category category) {
		categories.add(category);
	}

	public void removeCategory(Category category) {
		categories.remove(category);
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void addContributor(Person contributor) {
		contributors.add(contributor);
	}

	public void removeContributor(Person contributor) {
		contributors.remove(contributor);
	}

	public List<Person> getContributors() {
		return contributors;
	}

	public void setContributors(List<Person> contributors) {
		this.contributors = contributors;
	}

	public Text getRights() {
		return rights;
	}

	public void setRights(Text rights) {
		this.rights = rights;
	}

}
