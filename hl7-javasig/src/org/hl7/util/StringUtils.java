/* Copyright 2002 Health Level Seven, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Health Level Seven, Inc.
 * Use is subject to license terms.
 */
package org.hl7.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class that contains common functions dealing with strings.
 *
 * @author Skirmantas Kligys (skirmis@hotmail.com)
 */
public class StringUtils
{
  //-------------------------------------------------------------------------
  /**
   * Constructor is private, all methods are static; utility class pattern.
   */
  private StringUtils()
  {
      super();
  }

  //-------------------------------------------------------------------------
  /**
   * Splits a String at character <code>separator</code> and trims spaces
   * if present.
   *
   * @param s  incoming string
   * @param separator  separator character
   * @return  an array of tokens found
   */
  public static String[] split(final String s, final char separator)
  {
    if (s == null) return null;

    final List<String> result = new ArrayList<String>();
    int j = -1;
    for (int i = s.indexOf(separator); i != -1;
         j = i, i = s.indexOf(separator, i + 1))
    {
      result.add(s.substring(j + 1, i).trim());
    }
    result.add(s.substring(j + 1).trim());

    return result.toArray(new String[result.size()]);
  }

  //-------------------------------------------------------------------------
  /**
   * Joins a string array into one string with <code>separator</code>
   * between elements.
   *
   * @param as  array of tokens to join
   * @param separator  separator character
   * @return  the joined string
   */
  public static String join(final String[] as, final String separator)
  {
    if (as == null) {
        return null;
    }
    else if (as.length == 1) {
        return as[0];
    }
    else
    {
      final StringBuffer sb = new StringBuffer();
      for (int i = 0; i < as.length; ++i)
      {
        if (i > 0) sb.append(separator);
        sb.append(as[i]);
      }
      return sb.toString();
    }
  }

  //-------------------------------------------------------------------------
  /**
   * Tests if a string is all capitals or not.
   *
   * @param s  the string to be tested
   * @return  flag indicating if the string is all capitals
   */
  public static boolean isAllCaps(final String s)
  {
    if (s == null || s.length() == 0) return false;

    for (int i = 0; i < s.length(); ++i)
    {
      char c = s.charAt(i);
      if (Character.isLowerCase(c)) return false;
    }
    return true;
  }

  //-------------------------------------------------------------------------
  /**
   * Returns <code>true</code> if the string fits the CMET HMD or RMIM ID
   * pattern: <code>COCT_HDdddddd</code> or <code>COCT_RMdddddd</code>,
   * where <code>d</code> is a decimal digit.
   *
   * @param s  string to test
   * @return  flag indicating if the string matches the pattern
   */
  public static boolean matchesCmetHmdPattern(final String s)
  {
    final String sWithoutSETPrefix = removeSet(s);
    return (sWithoutSETPrefix != null) && (sWithoutSETPrefix.length() == 13) &&
      (sWithoutSETPrefix.startsWith("COCT_HD") || sWithoutSETPrefix.startsWith("COCT_RM")) &&
      (sWithoutSETPrefix.charAt(7) >= '0') && (sWithoutSETPrefix.charAt(7) <= '9') &&
      (sWithoutSETPrefix.charAt(8) >= '0') && (sWithoutSETPrefix.charAt(8) <= '9') &&
      (sWithoutSETPrefix.charAt(9) >= '0') && (sWithoutSETPrefix.charAt(9) <= '9') &&
      (sWithoutSETPrefix.charAt(10) >= '0') && (sWithoutSETPrefix.charAt(10) <= '9') &&
      (sWithoutSETPrefix.charAt(11) >= '0') && (sWithoutSETPrefix.charAt(11) <= '9') &&
      (sWithoutSETPrefix.charAt(12) >= '0') && (sWithoutSETPrefix.charAt(12) <= '9');
  }

    /**
     * Since 2005 Normative Edition, the name convention is changed. It has to following
     * HL7 v3 artifact naming convention: {UUDD_AAnnnnnnUVnn}.
     * - UU = Sub-Section code
     * - DD = Domain code
     * - AA = Artifact or Document code.
     * (Message Type will be MT) - nnnnnn = Six digit zero-filled number
     * - UV = Universal
     * - nn = ballot version
     *
     * Since the ballot version is different, starting from 01 to unknown, this function find related file
     * by guessing the version number
		 *
		 * THIS METHOD IS NOT USED BY JAVASIG CODE ANY MORE. IF IT WERE, IT WOULD HAVE TO BE
		 * CONVERTED TO NOT USER Files BUT Resources INSTEAD.
		 * TestParseAndBuild uses it, but that's O.K. Just those tests will eventually fail.
     *
     * @param fileDirectory
     * @param messageType
     * @param fileExtension
     * @return File Name
     */
  public static String searchMessageTypeFileName(final String fileDirectory, final String messageType, final String fileExtension, final boolean padding)
        throws FileNotFoundException
  {
	  String absolutePath = null;
      
	  for (int i = 1; i < 100; i++)
      {
          String pad = padding == true ? i < 10 ? "UV0" + i : "UV" + String.valueOf(i): "";
          absolutePath = fileDirectory + File.separator + messageType + pad + "." + fileExtension;
          final File file = new File(absolutePath);
          if (file.exists())
          {
        	  absolutePath = file.getAbsolutePath();
        	  break;
          }
          else
          {
        	  absolutePath = null;
          }
      }
      
      if(absolutePath == null)
      {
          throw new FileNotFoundException("File Directory:" + fileDirectory + " Message Type:" + messageType
                  + " File Extenstion:" + fileExtension);
      }
      
      return absolutePath;
  }

  //-------------------------------------------------------------------------
  /**
   * Returns <code>true</code> if the string fits the CMET message type
   * pattern: <code>COCT_HDdddddd</code> or <code>COCT_RMdddddd</code>,
   * where d is a decimal digit.
   *
   * @param s  string to test
   * @return  flag indicating if the string matches the pattern
   */
  public static boolean matchesCmetMessagetypePattern(final String s)
  {
    final String sWithoutSETPrefix = removeSet(s);
    return (sWithoutSETPrefix != null) && (sWithoutSETPrefix.length() == 13) &&
      (sWithoutSETPrefix.startsWith("COCT_MT") || sWithoutSETPrefix.startsWith("PORR_MT") || sWithoutSETPrefix.startsWith("MCAI_MT")) &&
      (sWithoutSETPrefix.charAt(7) >= '0') && (sWithoutSETPrefix.charAt(7) <= '9') &&
      (sWithoutSETPrefix.charAt(8) >= '0') && (sWithoutSETPrefix.charAt(8) <= '9') &&
      (sWithoutSETPrefix.charAt(9) >= '0') && (sWithoutSETPrefix.charAt(9) <= '9') &&
      (sWithoutSETPrefix.charAt(10) >= '0') && (sWithoutSETPrefix.charAt(10) <= '9') &&
      (sWithoutSETPrefix.charAt(11) >= '0') && (sWithoutSETPrefix.charAt(11) <= '9') &&
      (sWithoutSETPrefix.charAt(12) >= '0') && (sWithoutSETPrefix.charAt(12) <= '9');
  }

  //-------------------------------------------------------------------------
  /**
   * Maps a CMET HMD ID of the form <code>COCT_HDdddd00</code> or an RMIM ID
   * of the form <code>COCT_RMdd0000</code> to a message type ID of form
   * <code>COCT_MTdddd01</code>, where <code>d</code> is a decimal digit.
   *
   * @param s  the HMD or RMIM ID to convert
   * @return  conversion result: message type ID
   */
  public static String mapCmetHmdToMessageType(final String s)
  {
    if (s == null) {
        return null;
    }
    final String sWithoutSETPrefix = removeSet(s);
    if (matchesCmetMessagetypePattern(sWithoutSETPrefix)) {
        return sWithoutSETPrefix;
    }
    else if (matchesCmetHmdPattern(sWithoutSETPrefix))
    {
      // A workaround for when RMIM IDs are here, and they are not supposed to.
      final String workaround = (sWithoutSETPrefix.charAt(9) == '0' && sWithoutSETPrefix.charAt(10) == '0') ?
        "01" : sWithoutSETPrefix.substring(9, 11);
      return sWithoutSETPrefix.substring(0, 5) + "MT" + sWithoutSETPrefix.substring(7, 9) + workaround +
        "01";
    }
    else return null;
  }

  //-------------------------------------------------------------------------
  /**
   * Maps a CMET message type ID of the form <code>COCT_MTdddddd</code> to
   * an HMD ID of form <code>COCT_HDdddddd</code>,
   * where <code>d</code> is a decimal digit.
   *
   * @param s  the message type ID to convert
   * @return  conversion result: HMD ID
   */
  public static String mapCmetMessagetypeToHmd(final String s)
  {
    final String sWithoutSETPrefix = removeSet(s);
    if (!matchesCmetMessagetypePattern(sWithoutSETPrefix)) return null;

    return sWithoutSETPrefix.substring(0, 5) + "HD" + sWithoutSETPrefix.substring(7, 13);
  }

  //-------------------------------------------------------------------------
  /**
   * If the string is of the form <code>SET&lt;...&gt;</code>, removes
   * the set; otherwise returns unchanged string.
   *
   * @param s  the string to process
   * @return  the string with set notation removed
   */
  public static String removeSet(final String s)
  {
    if (s == null) return null;
    else if (s.startsWith("SET<") && s.endsWith(">"))
    {
      return s.substring(4, s.length() - 1);
    }
    else return s;
  }

  //-------------------------------------------------------------------------
  /**
   * Trims the suffix form the string if it is present; otherwise does
   * nothing.
   *
   * @param s  string to process
   * @param suffix  suffix to trim
   * @return  source string with suffix trimmed
   */
  public static String trimSuffix(final String s, final String suffix)
  {
    if (s == null || s.length() == 0 ||
        suffix == null || suffix.length() == 0) return s;

    if (s.endsWith(suffix))
    {
      return s.substring(0, s.length() - suffix.length());
    }
    else {
        return s;
    }
  }

    /**
     * Enforces that the first character is capital, and the remaining are
     * lowercase.
     *
     * @param s  incoming string
     * @return  transformed string
     */
    public static String forceInitialCap(final String s)
    {
      if (s == null || s.length() == 0 || checkInitialCap(s)) {
          return s;
      }

      return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public static String forceInitialLowerCase(final String s)
    {
        if (s == null || s.length() == 0 ) {
            return s;
        }

        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    /**
     * Checks that the first character is capital, and the remaining are
     * lowercase.
     *
     * @param s  incoming string
     * @return  <code>true</code> if the check was successful
     */
    protected static boolean checkInitialCap(String s)
    {
      if (s == null || s.length() == 0) return false;
      if (!Character.isUpperCase(s.charAt(0))) return false;

      for (int i = 1; i < s.length(); ++i)
      {
        if (!Character.isUpperCase(s.charAt(i))) return false;
      }
      return true;
    }
}
