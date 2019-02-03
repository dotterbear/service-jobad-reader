package com.dotterbear.jobad.reader.html.utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DocumentWrapper {

	private Document document;

	private String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";

	private int timeout = 5000;

	Predicate<Document> isResponseOk;

	public DocumentWrapper(Predicate<Document> isResponseOk) {
		super();
		this.isResponseOk = isResponseOk;
	}

	public String concatClassNamesSelector(String... classNames) {
		if (classNames == null || classNames.length == 0)
			return "";
		return String.join(" ", Arrays.stream(classNames)
				.map(className -> "." + className)
				.collect(Collectors.toList()));
	}

	public DocumentWrapper fetchDocument(String url) throws IOException {
		document = Jsoup.connect(url)
				.userAgent(userAgent)
				.timeout(timeout)
				.get();
		if(!isResponseOk.test(document))
			throw new IOException("is not validate");
		return this;
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

	public Document getDocument() {
		return document;
	}
}
