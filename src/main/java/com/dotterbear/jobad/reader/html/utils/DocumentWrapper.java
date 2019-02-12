package com.dotterbear.jobad.reader.html.utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DocumentWrapper {

	private Document document;

	public String concatClassNamesSelector(String... classNames) {
		if (classNames == null || classNames.length == 0)
			return "";
		return String.join(" ", Arrays.stream(classNames)
				.map(className -> "." + className)
				.collect(Collectors.toList()));
	}

	public Element getElementBySelector(String selector) {
		return document.selectFirst(selector);
	}

	public Element getElementByClassNames(String... classNames) {
		return getElementBySelector(concatClassNamesSelector(classNames));
	}

	public List<Element> getElementsByClassNames(String... classNames) {
		List<Element> elements = document.select(concatClassNamesSelector(classNames));
		return Optional.ofNullable(elements).orElse(new ArrayList<Element>());
	}

	public String getElementTextByClassNames(String... classNames) {
		return getElementTextBySelector(concatClassNamesSelector(classNames));
	}

	public String getElementTextBySelector(String selector) {
		Element element = getElementBySelector(selector);
		return element == null ? null : element.text();
	}

	public String getElementHtmlBySelector(String... classNames) {
		return geElementHtmlBySelector(concatClassNamesSelector(classNames));
	}

	public String geElementHtmlBySelector(String selector) {
		Element element = getElementBySelector(selector);
		return element == null ? null : element.html();
	}

	public DocumentWrapper setDocument(Document document) {
		this.document = document;
		return this;
	}

	public Document getDocument() {
		return document;
	}
}
