package jksoft.com.util;

import org.apache.commons.collections.map.ListOrderedMap;

public class XMap extends ListOrderedMap
{
	
	private static final long serialVersionUID = 1L;

    public XMap() {
    }

    public Object put(Object key, Object value)
    {
        return super.put(convert2CamelCase((String)key), value);
    }

    public Object put2(Object key, Object value)
    {
        return super.put(convert3CamelCase((String)key), value);
    }

    public Object put3(Object key, Object value)
    {
        return super.put(key, value);
    }

    public static String convert2CamelCase(String underScore)
    {
        if(underScore.indexOf('_') < 0 && Character.isLowerCase(underScore.charAt(0)))
            return underScore;
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;
        int len = underScore.length();
        for(int i = 0; i < len; i++)
        {
            char currentChar = underScore.charAt(i);
            if(currentChar == '_')
            {
                nextUpper = true;
                continue;
            }
            if(nextUpper)
            {
                result.append(Character.toUpperCase(currentChar));
                nextUpper = false;
            } else
            {
                result.append(Character.toLowerCase(currentChar));
            }
        }

        return result.toString();
    }

    public static String convert3CamelCase(String underScore)
    {
        if(underScore.indexOf('_') < 0)
            return underScore;
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;
        int len = underScore.length();
        for(int i = 0; i < len; i++)
        {
            char currentChar = underScore.charAt(i);
            if(currentChar == '_')
            {
                nextUpper = true;
                continue;
            }
            if(nextUpper)
            {
                result.append(Character.toUpperCase(currentChar));
                nextUpper = false;
            } else
            {
                result.append(currentChar);
            }
        }

        return result.toString();
    }
}
