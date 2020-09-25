package org.naounit.intellij.plugin.Patterns;

import java.util.regex.Matcher;

public class DDDFixtureTestsPattern implements PathPattern
{
    private String pattern;

    private DDDFixtureTestsPattern(String pattern)
    {
        this.pattern = pattern;
    }

    public static DDDFixtureTestsPattern create()
    {
        return new DDDFixtureTestsPattern("src/?(?<bc>.*)/tests/0-fixtures/tests/(?<path>.*/)?(.*)$");
    }

    public static DDDFixtureTestsPattern create(Matcher matcher, String filename) throws RuntimeException
    {
        if(! matcher.find())
        {
            throw new RuntimeException();
        }

        String path = matcher.group("path");
        if(path == null)
        {
            path = "";
        }

        return new DDDFixtureTestsPattern(String.format("src/%s/tests/0-fixtures/tests/%s%s$", matcher.group("bc"), path, filename));
    }

    public PathPattern createTargetPatternFromMatcher(Matcher matcher, String filename)
    {
        return DDDFixtureSrcPattern.create(matcher, filename);
    }

    public String toString()
    {
        return this.pattern;
    }
}
