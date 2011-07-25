/*
 * Copyright (C) 2011 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 * 
 * Created on 19. April 2011 by Joerg Schaible
 */
package com.thoughtworks.xstream.hibernate.converter;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.TreeSetConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;

import org.hibernate.collection.PersistentSortedSet;


/**
 * A converter for Hibernate's {@link PersistentSortedSet}. The converter will drop any
 * reference to the Hibernate collection and emit at serialization time an equivalent JDK
 * collection instead.
 * 
 * @author J&ouml;rg Schaible
 * @since upcoming
 */
public class HibernatePersistentSortedSetConverter extends TreeSetConverter {

    /**
     * Construct a HibernatePersistentSortedSetConverter.
     * 
     * @param mapper
     * @since upcoming
     */
    public HibernatePersistentSortedSetConverter(final Mapper mapper) {
        super(mapper);
    }

    public boolean canConvert(final Class type) {
        return type == PersistentSortedSet.class;
    }

    public Object unmarshal(final HierarchicalStreamReader reader,
        final UnmarshallingContext context) {
        throw new ConversionException("Cannot deserialize Hibernate collection");
    }
}