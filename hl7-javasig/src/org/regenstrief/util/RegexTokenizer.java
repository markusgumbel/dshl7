/*
 * Copyright (c) 1998-2003 Regenstrief Institute, Inc.  All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.regenstrief.util;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** A kind of token iterator that allows one to clip a sequence of 
    regular expression matches from a string.

    <p>Example, a tokenizer that would do some shallow XML parsing
    could look somewhat like this:

    <pre>
    String REGEX = "<([/?!]?)([^ >]+)>|&amp;(#?)([A-Za-z0-9]+);";
    int GROUP_ALL = 0;
    int GROUP_TAG_PREFIX = 1;
    int GROUP_TAG_NAME = 2;
    int GROUP_ENT_PREFIX = 3;
    int GROUP_ENT_NAME = 4;
    RegexTokenizer tokens = new RegexTokenizer(PSEUDO_XML_REGEX, xmlString);
    while(tokens.seek()) {
      String textNode = tokens.skipped();
      if(textNode != null) {
        // text node leading up to next tag
      }
      String tagName = tokens.group(GROUP_TAG_NAME);
      String entName = tokens.group(GROUP_ENT_NAME);
      if(tagName != null) {
        String tagPrefix = tokens.group(GROUP_TAG_PREFIX);
	if(tagPrefix == null) {
	  // start element
	} else if(tagPrefix.equals("/")) {
	  // end element
	}
      } else if(entName != null) {
        String entPrefix = tokens.group(GROUP_ENT_PREFIX);
	if(entPrefix == null) {
	  if(entName.equals("amp")) {
	    // the &amp;amp; entity means the character &amp;
	  } else if(entName.equals("lt")) {
	    // the &amp;lt; entity means the character &lt;
	  } else ...
	} else if(entPrefix.equals("#")) {
	  int char = Integer.parseInt(entName);
	  // the character char
	}
      }
    }
    String textNode = tokens.rest();
    if(textNode) {
      // text node after last tag (or lone text node if there were no tags)
    }
    </pre>

    @author Gunther Schadow.
    @version $Id: RegexTokenizer.java 4607 2006-10-18 19:43:42Z crosenthal $
*/
public class RegexTokenizer implements MatchResult {
  private Matcher _matcher;
  private String  _string;
  private int _head;
  private int _middle;
  private int _tail;
  private int _end;

  /** Create a tokenizer with the given pattern string tokenizing the given string. */
  public RegexTokenizer(String pattern, String string) {
    this(Pattern.compile(pattern), string);
  }
  
  /** Create a tokenizer with the given pattern tokenizing the given string. */ 
  public RegexTokenizer(Pattern pattern, String string) {
    _string = string;
    _matcher = pattern.matcher(_string);
    _head=0;
    _middle=0;
    _tail=0;
    _end=_string.length();
  }
  
  /** Finds the next matching token, skipping over any non-matching text. The non-matching
      text can be retrieved by the skipped property. */
  public boolean seek() {
    if(_matcher.find(_tail)) {
			consume();
      return true;
    } else
      return false;
  }
  
  /** Returns true if we are looking directly at a matching token, else returns false. */
  public boolean next() {
    _matcher.region(_tail, _end); // instead of lookingAt(_tail), which doesn't exist
    if(_matcher.lookingAt()) {
			consume();
      return true;
    } else
      return false;
  }

	private final void consume() {
		_head=_tail;
		_middle=_matcher.start();
		_tail=_matcher.end();		
	}
  
  /** Returns the matching token */
  public String token() { return _matcher.group(); }
  /** Returns the matching token */
  public String group() { return _matcher.group(); }
  
  /** Returns the text that was skipped by the most recent seek operation. */
  public String skipped() { return _string.substring(_head, _middle); }
  
  /** Returns the rest of the string that has not yet been matched either because
  we did not try to match it using next or seek, or because a final seek
  had not found any further matching token. */
  public String rest() { return _string.substring(_tail); }
  
  /** Returns a the capture-group, which relates to the parenthesis term in the 
      regex. The group number 0 (zero) is the same as the entire token, 1 (one)
      is the first parenthesis term, etc. */
  public String group(int i) { return _matcher.group(i); }
  
  /** Returns the number of capture groups defined in the regex term. */
  public int groupCount() { return _matcher.groupCount(); }
  
  public int start() { return _matcher.start(); }
  public int end() { return _matcher.end(); }
	public int start(int i) { return _matcher.start(i); }
	public int end(int i) { return _matcher.end(i); }

  public String tail() {
  	String str = rest();
  	_tail=_end;
  	return str;
  }
  
	public Pattern pattern() { return _matcher.pattern(); }
  
  public RegexTokenizer reset() {
  	_head=0;
    _middle=0;
    _tail=0;
  	return this;
  }
      
  public RegexTokenizer reset(String input) {
  	_matcher.reset(input);
  	_string = input;
    _head=0;
    _middle=0;
    _tail=0;
    _end=_string.length();
  	return this;
  }
}
