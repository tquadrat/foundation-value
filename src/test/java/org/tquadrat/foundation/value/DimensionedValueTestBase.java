/*
 * ============================================================================
 *  Copyright © 2002-2021 by Thomas Thrien.
 *  All Rights Reserved.
 * ============================================================================
 *  Licensed to the public under the agreements of the GNU Lesser General Public
 *  License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations
 *  under the License.
 */

package org.tquadrat.foundation.value;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.tquadrat.foundation.lang.Objects.isNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.StringConverter;
import org.tquadrat.foundation.testutil.TestBaseClass;
import org.tquadrat.foundation.value.api.Dimension;
import org.tquadrat.foundation.value.api.DimensionWithLinearConversion;
import org.tquadrat.foundation.value.api.DimensionedValue;

/**
 *  The abstract base class for tests of classes that are derived from
 *  {@link DimensionedValue}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: DimensionedValueTestBase.java 1076 2023-10-03 18:36:07Z tquadrat $
 *
 *  @param  <D> The type for the dimension.
 *  @param  <T> The type for the dimensioned value.
 */
@ClassVersion( sourceVersion = "$Id: DimensionedValueTestBase.java 1076 2023-10-03 18:36:07Z tquadrat $" )
public abstract class DimensionedValueTestBase<D extends Dimension, T extends DimensionedValue<D>> extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Determines the class for the dimension.
     *
     *  @return The enum class.
     */
    @SuppressWarnings( "unchecked" )
    protected Class<D> determineEnumClass()
    {
        Class<D> retValue = null;
        final var dimensions = new ArrayList<>( getDimensions() );
        if( dimensions.get( 0 ) instanceof Enum<?> enumConstant )
        {
            retValue = (Class<D>) enumConstant.getDeclaringClass();
        }
        else
        {
            fail( "Dimension is not an enum" );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  determineEnumClass()

    /**
     *  Returns the dimensions for the class under test.
     *
     *  @return The dimensions.
     */
    protected abstract Collection<D> getDimensions();

    /**
     *  Returns the instance of
     *  {@link org.tquadrat.foundation.lang.StringConverter}
     *  for the class under test.
     *
     *  @return The string converter.
     */
    protected abstract StringConverter<T> getStringConverter();

    /**
     *  Tests the methods
     *  {@link DimensionedValue#clone()}.
     *
     *  @param  value   The value for the tests.
     *  @throws Exception   Something unexpected went wrong.
     */
    @SuppressWarnings( {"JUnit5MalformedParameterized", "unused"} )
    @ParameterizedTest
    @MethodSource( "valueProvider" )
    final void testCopyAndClone( final T value ) throws Exception
    {
        skipThreadTest();

        assumeFalse( isNull( value ) );

        @SuppressWarnings( "unchecked" )
        final
        var clone = (T) value.clone();
        assertNotNull( clone );
        assertEquals( clone, value );
        assertNotSame( clone, value );

        @SuppressWarnings( "unchecked" )
        final var copy = (T) value.copy();
        assertNotNull( copy );
        assertEquals( copy, value );
        assertNotSame( copy, value );

        for( final var dimension : getDimensions() )
        {
            final var otherCopy = value.copy( dimension );
            assertNotNull( otherCopy );
            assertEquals( otherCopy, value );
            assertNotSame( otherCopy, value );
        }
    }   //  testCopyAndClone()

    /**
     *  Tests the method
     *  {@link DimensionedValue#newInstance(Dimension, BigDecimal)}.
     *
     *  @param  value   The value for the tests.
     *  @throws Exception   Something unexpected went wrong.
     */
    @SuppressWarnings( {"JUnit5MalformedParameterized", "unused"} )
    @ParameterizedTest
    @MethodSource( "valueProvider" )
    final void testNewInstance( final T value ) throws Exception
    {
        skipThreadTest();

        assumeFalse( isNull( value ) );

        for( final var dimension : getDimensions() )
        {
            //---* Same dimension *--------------------------------------------
            if( dimension == value.getUnit() ) continue;

            if( dimension instanceof DimensionWithLinearConversion dimensionWithFactor )
            {
                //---* Alternative unit *--------------------------------------
                if( dimensionWithFactor.factor().equals( ((DimensionWithLinearConversion) value.getUnit()).factor() ) ) continue;
            }

            //---* Base value is zero *----------------------------------------
            if( value.baseValue().equals( BigDecimal.ZERO ) ) continue;
            final var number = value.value();

            //---* Effective value is zero *-----------------------------------
            if( number.equals( BigDecimal.ZERO ) ) continue;

            final var other = value.newInstance( dimension, value.value() );
            assertNotEquals( other, value );
        }
    }   //  testNewInstance()

    /**
     *  Tests for the instance of
     *  {@link org.tquadrat.foundation.lang.StringConverter}
     *  for the class under test.
     *
     *  @param  value   The value for the tests.
     *  @throws Exception   Something unexpected went wrong.
     */
    @SuppressWarnings( {"JUnit5MalformedParameterized", "unused"} )
    @ParameterizedTest
    @MethodSource( "valueProvider" )
    final void testStringConverter( final T value ) throws Exception
    {
        skipThreadTest();

        final var candidate = getStringConverter();
        assertNotNull( candidate );

        assertEquals( value, candidate.fromString( candidate.toString( value ) ) );
    }   //  testStringConverter()

    /**
     *  Tests for the instance of
     *  {@link org.tquadrat.foundation.lang.StringConverter}
     *  for the class under test.
     *
     *  @throws Exception   Something unexpected went wrong.
     */
    @SuppressWarnings( "unused" )
    @Test
    final void testStringConverterWithNullArgument() throws Exception
    {
        skipThreadTest();

        final var candidate = getStringConverter();
        assertNotNull( candidate );

        assertNull( candidate.fromString( null ) );
        assertNull( candidate.toString( null ) );
    }   //  testStringConverterWithNullArgument()

    /**
     *  Tests whether the unit symbols for the dimensions are unique.
     */
    @Test
    final void testUniqueness()
    {
        skipThreadTest();

        final var dimensions = getDimensions();

        final var unitCount = dimensions.stream()
            .map( Dimension::unitSymbol )
            .distinct()
            .count();

        assertEquals( dimensions.size(), unitCount, format( "Duplicate unit symbol for %s", determineEnumClass().getName() ) );
    }   //  testUniqueness()

    /**
     *  Tests for the instance of
     *  {@link org.tquadrat.foundation.lang.StringConverter}
     *  for the class under test.
     *
     *  @param  value   The value for the tests.
     *  @throws Exception   Something unexpected went wrong.
     */
    /*
     * The 'value provider' method will be provided by the implementing class.
     */
    @SuppressWarnings( "JUnit5MalformedParameterized" )
    @ParameterizedTest
    @MethodSource( "valueProvider" )
    final void testValueConversion( final T value ) throws Exception
    {
        skipThreadTest();

        final var candidate = getStringConverter();
        assertNotNull( candidate );

        assertEquals( value, candidate.fromString( candidate.toString( value ) ) );
    }   //  testValueConversion()

    /**
     *  Tests the constructors for the value type.
     *
     *  @throws Exception   Something unexpected went wrong.
     */
    @SuppressWarnings( "unused" )
    @Test
    abstract void testValueTypeConstructor() throws Exception;
}
//  class DimensionedValueTestBase

/*
 *  End of File
 */