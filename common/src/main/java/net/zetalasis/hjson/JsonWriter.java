/*******************************************************************************
 * Copyright (c) 2013, 2015 EclipseSource.
 * Copyright (c) 2015-2016 Christian Zangl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package net.zetalasis.hjson;

import java.io.IOException;
import java.io.Writer;
import java.util.regex.Matcher;


class JsonWriter {

  boolean format;

  public JsonWriter(boolean format) {
    this.format=format;
  }

  void nl(Writer tw, int level) throws IOException {
    if (format) {
      tw.write(JsonValue.eol);
      for (int i=0; i<level; i++) tw.write("  ");
    }
  }

  public void save(JsonValue value, Writer tw, int level) throws IOException {
    boolean following=false;
    switch (value.getType()) {
      case OBJECT:
        JsonObject obj=value.asObject();
        tw.write('{');
        for (JsonObject.Member pair : obj) {
          if (following) tw.write(",");
          nl(tw, level+1);
          tw.write('\"');
          tw.write(escapeString(pair.getName()));
          tw.write("\":");
          //save(, tw, level+1, " ", false);
          JsonValue v=pair.getValue();
          if (format) tw.write(" ");
          if (v==null) tw.write("null");
          else save(v, tw, level+1);
          following=true;
        }
        if (following) nl(tw, level);
        tw.write('}');
        break;
      case ARRAY:
        JsonArray arr=value.asArray();
        int n=arr.size();
        tw.write('[');
        for (int i=0; i<n; i++) {
          if (i > 0) tw.write(",");
          JsonValue v=arr.get(i);
          nl(tw, level+1);
          save(arr.get(i), tw, level+1);
        }
        if (n > 0) nl(tw, level);
        tw.write(']');
        break;
      case BOOLEAN:
        tw.write(value.isTrue()?"true":"false");
        break;
      case STRING:
        tw.write('"');
        tw.write(escapeString(value.asString()));
        tw.write('"');
        break;
      default:
        tw.write(value.toString());
        break;
    }
  }

  static String escapeString(String src) {
    if (src==null) return null;

    int i = 0;
    StringBuilder sb=new StringBuilder();
    Matcher m = HjsonWriter.needsEscape.matcher(src);

    while (m.find()) {
      // Assume all matches are single chars.
      sb.append(src, i, m.start()).append(getEscapedChar(m.group().charAt(0)));
      i = m.end();
    }

    if (i < 1) {
      return src;
    }

    sb.append(src, i, src.length());

    return sb.toString();
  }

  private static String getEscapedChar(char c) {
    switch (c) {
      case '\"': return "\\\"";
      case '\t': return "\\t";
      case '\n': return "\\n";
      case '\r': return "\\r";
      case '\f': return "\\f";
      case '\b': return "\\b";
      case '\\': return "\\\\";
      default: return "\\u" + String.format("%04x", (int) c);
    }
  }
}
