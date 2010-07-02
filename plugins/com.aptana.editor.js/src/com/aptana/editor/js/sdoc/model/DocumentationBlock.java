package com.aptana.editor.js.sdoc.model;

import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import beaver.Symbol;

import com.aptana.parsing.io.SourcePrinter;

public class DocumentationBlock extends Symbol
{
	private String _text;
	private List<Tag> _tags;

	/**
	 * Block
	 * 
	 * @param tags
	 */
	public DocumentationBlock(List<Tag> tags)
	{
		this("", tags);
	}

	/**
	 * Block
	 * 
	 * @param text
	 */
	public DocumentationBlock(String text)
	{
		this(text, new LinkedList<Tag>());
	}

	/**
	 * Block
	 * 
	 * @param text
	 * @param tags
	 */
	public DocumentationBlock(String text, List<Tag> tags)
	{
		this._text = text;
		this._tags = tags;
	}

	/**
	 * getTags
	 * 
	 * @return
	 */
	public List<Tag> getTags()
	{
		return this._tags;
	}

	/**
	 * getTags
	 * 
	 * @param tagSelector
	 * @return
	 */
	public List<Tag> getTags(EnumSet<TagType> tagSelector)
	{
		List<Tag> result;

		if (this._tags != null && this._tags.isEmpty() == false)
		{
			result = new LinkedList<Tag>();

			for (Tag tag : this._tags)
			{
				if (tagSelector.contains(tag.getType()))
				{
					result.add(tag);
				}
			}
		}
		else
		{
			result = Collections.emptyList();
		}

		return result;
	}

	/**
	 * getTags
	 * 
	 * @param type
	 * @return
	 */
	public List<Tag> getTags(TagType type)
	{
		return this.getTags(EnumSet.of(type));
	}

	/**
	 * getText
	 * 
	 * @return
	 */
	public String getText()
	{
		return this._text;
	}

	/**
	 * hasTags
	 * 
	 * @return
	 */
	public boolean hasTags()
	{
		return (this._tags != null && this._tags.isEmpty() == false);
	}

	/**
	 * setRange
	 * 
	 * @param start
	 * @param end
	 */
	public void setRange(int start, int end)
	{
		this.start = start;
		this.end = end;
	}

	/**
	 * toSource
	 * 
	 * @return
	 */
	public String toSource()
	{
		SourcePrinter writer = new SourcePrinter(" * ");

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
		writer.println("/**").increaseIndent();

		if (this._text != null && this._text.isEmpty() == false)
		{
			writer.printlnWithIndent(this._text);

			if (this.hasTags())
			{
				writer.printIndent().println();
			}
		}

		if (this.hasTags())
		{
			for (Tag tag : this._tags)
			{
				writer.printIndent();
				tag.toSource(writer);
				writer.println();
			}
		}

		writer.decreaseIndent().println(" */");
	}
}
