package edu.penzgtu.ip.bookshop.changelogs;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;

import edu.penzgtu.ip.bookshop.model.Book;

@Changelog
public class BookChangelog {
	@Changeset(order = 1, id = "change1", author = "Roman")
	public void changeset(MongoTemplate template) {
		template.findAll(Book.class, "book").forEach(document -> {
			document.setCategory("test");
			template.save(document, "book");
		});
	}
}