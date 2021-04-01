package ast.nodes.contents;

/**
 * A Marker Interface To Treat All Kinds Of Element Content (Another Element, Script, Curly Content, ..)
 * No Matter How Structurally Different They Are To Be Of The Same Type.
 *
 */

public interface Content {
    public String toString(int col);
}
