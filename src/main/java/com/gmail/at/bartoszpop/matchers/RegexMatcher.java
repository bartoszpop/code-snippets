package com.gmail.at.bartoszpop.matchers;

import java.text.Format;
import java.text.MessageFormat;
import java.util.regex.Pattern;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * A {@link Matcher} which matches if an examined string matches the given regular expression.
 * 
 * @author Bartosz Popiela
 */
public final class RegexMatcher extends TypeSafeMatcher<String> {

	private static final Format DESCRIPTION_FORMAT = new MessageFormat("string matches \"{0}\"");

	private static final Format MISMATCH_FORMAT = new MessageFormat("string \"{0}\" does not match \"{1}\"");

	private final Pattern pattern;

	private RegexMatcher(String regex) {
		this.pattern = Pattern.compile(regex);
	}

	@Override
	protected boolean matchesSafely(String item) {
		return pattern.matcher(item).matches();
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(DESCRIPTION_FORMAT.format(new String[] { pattern.pattern() }));
	}

	@Override
	protected void describeMismatchSafely(String item, Description mismatchDescription) {
		mismatchDescription.appendText(MISMATCH_FORMAT.format(new String[] { item, pattern.toString() }));
	}

	public static Matcher<? super String> matches(String regex) {
		return new RegexMatcher(regex);
	}
}