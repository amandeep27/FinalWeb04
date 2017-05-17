package com.web.domain;

import java.io.Serializable;

public class BookInfo implements Serializable {

	private static final long serialVersionUID = 1468261988873266262L;
	private int bookId;
	private String booktitle;
	private String description;
	private int publishYear;
	private BookAuthor author;
	private BookType type;

	public BookInfo(int bookId, String booktitle, String description, int publishYear, BookAuthor author,
			BookType type) {
		super();
		this.bookId = bookId;
		this.booktitle = booktitle;
		this.description = description;
		this.publishYear = publishYear;
		this.author = author;
		this.type = type;
	}

	public BookInfo(int bookId, String booktitle, String description, int publishYear) {
		super();
		this.bookId = bookId;
		this.booktitle = booktitle;
		this.description = description;
		this.publishYear = publishYear;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

	public BookAuthor getAuthor() {
		return author;
	}

	public void setAuthor(BookAuthor author) {
		this.author = author;
	}

	public BookType getType() {
		return type;
	}

	public void setType(BookType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + bookId;
		result = prime * result + ((booktitle == null) ? 0 : booktitle.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + publishYear;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BookInfo other = (BookInfo) obj;
		if (author == null) {
			if (other.author != null) {
				return false;
			}
		} else if (!author.equals(other.author)) {
			return false;
		}
		if (bookId != other.bookId) {
			return false;
		}
		if (booktitle == null) {
			if (other.booktitle != null) {
				return false;
			}
		} else if (!booktitle.equals(other.booktitle)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (publishYear != other.publishYear) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName());
		sb.append(":");
		sb.append(" Book Id = < " + this.bookId);
		sb.append(" >, Book Title = < " + this.booktitle);
		sb.append(" >, Description = < " + this.description);
		sb.append(" >, PublishYear = < " + this.publishYear);
		sb.append(" >, Author = < " + this.author);
		sb.append(" >, Type = < " + this.type + " >");
		return sb.toString();
	}

}
