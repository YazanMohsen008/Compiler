package ast.nodes.elements;

import ast.nodes.contents.Content;

/**
 * A Marker Interface To Treat All Kinds Of HTML Document Elements
 * Whether It's HTML Or Not (XML, DTD, Script, Style ..)
 * No Matter How Structurally Different They Are To Be Of The Same Type.
 *
 * Any Element Could Be Nested Inside Another Element.
 */

public interface Element extends Content {
    public String toString(int col);
}
