package org.naounit.intellij.plugin.Patterns;

import java.util.regex.Matcher;

public class DDDFixtureSrcPattern implements PathPattern
{
    private String pattern;

    private DDDFixtureSrcPattern(String pattern)
    {
        this.pattern = pattern;
    }

    public static DDDFixtureSrcPattern create()
    {
        return new DDDFixtureSrcPattern("src/?(?<bc>.*)/tests/0-fixtures/src/(?<path>.*/)?(.*)$");
    }

    public static DDDFixtureSrcPattern create(Matcher matcher, String filename) throws RuntimeException
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

        return new DDDFixtureSrcPattern(String.format("src/%s/tests/0-fixtures/src/%s%s$", matcher.group("bc"), path, filename));
    }

    public PathPattern createTargetPatternFromMatcher(Matcher matcher, String filename)
    {
        return DDDFixtureTestsPattern.create(matcher, filename);
    }

    public String toString()
    {
        return this.pattern;
    }
}
