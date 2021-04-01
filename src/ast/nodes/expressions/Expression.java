package ast.nodes.expressions;

import ast.nodes.attributes.AttributeValue;

/**
 * A Marker Interface To Treat All Kinds Of Expressions
 * No Matter How Structurally Different They Are To Be Of The Same Type.
 * All These Expressions Could Be Used In Certain Places,
 * For Instance A Function Parameter Could Be Any Type That Implements This Interface.
 */

public interface Expression extends AttributeValue {
}
