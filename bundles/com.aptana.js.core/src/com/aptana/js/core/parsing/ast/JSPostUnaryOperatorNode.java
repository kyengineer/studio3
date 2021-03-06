/**
 * Aptana Studio
 * Copyright (c) 2005-2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the GNU Public License (GPL) v3 (with exceptions).
 * Please see the license.html included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
package com.aptana.js.core.parsing.ast;

import com.aptana.js.core.parsing.JSTokenType;
import com.aptana.parsing.ast.IParseNode;

import beaver.Symbol;

public class JSPostUnaryOperatorNode extends JSNode
{
	private Symbol _operator;

	/**
	 * Used by ANTLR AST
	 * 
	 * @param start
	 * @param end
	 * @param operator
	 */
	public JSPostUnaryOperatorNode(int start, int end, Symbol operator)
	{
		super();
		this._operator = operator;
		this.setLocation(start, end);

		short type = DEFAULT_TYPE;
		JSTokenType token = JSTokenType.get((String) operator.value);

		switch (token)
		{
			case MINUS_MINUS:
				type = IJSNodeTypes.POST_DECREMENT;
				break;

			case PLUS_PLUS:
				type = IJSNodeTypes.POST_INCREMENT;
				break;

			default:
				throw new IllegalArgumentException(Messages.JSPostUnaryOperatorNode_0 + token);
		}

		this.setNodeType(type);
	}

	/*
	 * (non-Javadoc)
	 * @see com.aptana.editor.js.parsing.ast.JSNode#accept(com.aptana.editor.js.parsing.ast.JSTreeWalker)
	 */
	@Override
	public void accept(JSTreeWalker walker)
	{
		walker.visit(this);
	}

	/**
	 * getExpression
	 * 
	 * @return
	 */
	public IParseNode getExpression()
	{
		return this.getChild(0);
	}

	/**
	 * getOperator
	 * 
	 * @return
	 */
	public Symbol getOperator()
	{
		return this._operator;
	}
}
