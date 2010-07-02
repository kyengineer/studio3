package com.aptana.editor.js.sdoc.model;

import com.aptana.parsing.io.SourcePrinter;

import beaver.Symbol;

public class Type extends Symbol
{
	public static final Type OBJECT_TYPE = new Type("Object");
	
	private String _name;
	
	/**
	 * Type
	 * 
	 * @param name
	 */
	public Type(String name)
	{
		this._name = name;
	}
	
	/**
	 * getName
	 * 
	 * @return
	 */
	public String getName()
	{
		return this._name;
	}
	
	/**
	 * toSource
	 * 
	 * @return
	 */
	public String toSource()
	{
		SourcePrinter writer = new SourcePrinter();
		
		this.toSource(writer);
		
		return writer.toString();
	}
	
	/**
	 * toSource
	 * 
	 * @param writer
	 */
	public void toSource(SourcePrinter writer)
	{
		writer.print(this._name);
	}
}
