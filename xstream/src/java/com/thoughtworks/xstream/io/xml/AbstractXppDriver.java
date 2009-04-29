/*
 * Copyright (C) 2009 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 * 
 * Created on 29. April 2009 by Joerg Schaible
 */
package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.core.util.XmlHeaderAwareReader;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.StreamException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * An abstract base class for a driver using an XPP implementation. 
 * 
 * @author Joe Walnes
 * @author J&ouml;rg Schaible
 * @since upcoming
 */
public abstract class AbstractXppDriver extends AbstractXmlDriver {

    /**
     * Construct an AbstractXppDriver.
     * 
     * @param replacer the replacer for XML friendly tag and attribute names
     * @since upcoming
     */
    public AbstractXppDriver(XmlFriendlyReplacer replacer) {
        super(replacer);
    }

    /**
     * {@inheritDoc}
     */
    public HierarchicalStreamReader createReader(Reader xml) {
        try {
            return new XppReader(xml, createParser(), xmlFriendlyReplacer());
        } catch (XmlPullParserException e) {
            throw new StreamException("Cannot create XmlPullParser");
        }
    }

    /**
     * {@inheritDoc}
     */
    public HierarchicalStreamReader createReader(InputStream in) {
        try {
            return createReader(new XmlHeaderAwareReader(in));
        } catch (UnsupportedEncodingException e) {
            throw new StreamException(e);
        } catch (IOException e) {
            throw new StreamException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public HierarchicalStreamWriter createWriter(Writer out) {
        return new PrettyPrintWriter(out, xmlFriendlyReplacer());
    }

    /**
     * {@inheritDoc}
     */
    public HierarchicalStreamWriter createWriter(OutputStream out) {
        return createWriter(new OutputStreamWriter(out));
    }

    /**
     * Create the parser of the XPP implementation.
     * @throws XmlPullParserException if the parser cannot be created
     * 
     * @since upcoming
     */
    protected abstract XmlPullParser createParser() throws XmlPullParserException;
}