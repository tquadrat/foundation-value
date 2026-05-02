/*
 * ============================================================================
 * Copyright © 2002-2026 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 *
 * Licensed to the public under the agreements of the GNU Lesser General Public
 * License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.tquadrat.foundation.value;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.EmptyArgumentException;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.util.stringconverter.DimensionedValueStringConverter;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Locale.GERMANY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.value.Time.MILLISECOND;
import static org.tquadrat.foundation.value.Time.MINUTE;
import static org.tquadrat.foundation.value.Time.YEAR;

/**
 *  Tests for the classes
 *  {@link Time}
 *  and
 *  {@link TimeValue}.
 *
 *  @author Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestTime.java 1195 2026-04-15 21:33:40Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestTime.java 1195 2026-04-15 21:33:40Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.value.TestTime" )
public class TestTime extends DimensionedValueTestBase<Time,TimeValue>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    protected final Collection<Time> getDimensions()
    {
        return asList( Time.values() );
    }   //  getDimensions()

    /**
     *  {@inheritDoc}
     */
    @Override
    protected final DimensionedValueStringConverter<Time,TimeValue> getStringConverter()
    {
        return TimeValueStringConverter.INSTANCE;
    }   //  getStringConverter()

    /**
     *  Some tests for the formatting.
     *
     *  @throws Exception   Something went awfully wrong.
     */
    @Test
    final void testFormatting() throws Exception
    {
        skipThreadTest();

        var candidate = new TimeValue( YEAR, "22.4456789" );

        assertEquals( "22yr 23w 1d 18h 44min 15,738s", format( GERMANY, "%#s", candidate ) );

        candidate = new TimeValue( MILLISECOND, "10" );
        assertEquals( "0,010s", format( GERMANY, "%#s", candidate ) );

        assertEquals( "10,00 ms", format( GERMANY, "%6.2s", candidate ) );
    }   //  testFormatting()

    /**
     *  Test the values for Time.
     *
     *  @throws Exception   Something went awfully wrong.
     */
    @Test
    final void testTimeValues() throws Exception
    {
        skipThreadTest();

        var candidate = new TimeValue( MILLISECOND, 10 );
        var value = candidate.baseValue();
        assertEquals( 0, new BigDecimal( "0.01" ).compareTo( value ) );

        candidate = new TimeValue( MINUTE, 10 );
        value = candidate.baseValue();
        assertEquals( 0, new BigDecimal( "600" ).compareTo( value ) );
    }   //  testTimeValues()

    /**
     *  {@inheritDoc}
     */
    @Override
    @Test
    final void testValueTypeConstructor() throws Exception
    {
        skipThreadTest();

        assertThrows( NullArgumentException.class, () -> new TimeValue( null, BigDecimal.ONE ) );
        assertThrows( NullArgumentException.class, () -> new TimeValue( null, 1.0 ) );
        assertThrows( NullArgumentException.class, () -> new TimeValue( null, "1.0" ) );

        assertThrows( NullArgumentException.class, () -> new TimeValue( Time.WEEK, (BigDecimal) null ) );
        assertThrows( NullArgumentException.class, () -> new TimeValue( Time.WEEK, (Number) null ) );
        assertThrows( NullArgumentException.class, () -> new TimeValue( Time.WEEK, (String) null ) );

        assertThrows( EmptyArgumentException.class, () -> new TimeValue( Time.WEEK, EMPTY_STRING ) );

        assertThrows( NumberFormatException.class, () -> new TimeValue( Time.WEEK, " " ) );
    }   //  testValueTypeConstructor()

    /**
     *  Provides test values for
     *  {@link DimensionedValueTestBase#testValueConversion(org.tquadrat.foundation.lang.value.DimensionedValue)}.
     *
     *  @return The test values.
     *  @throws Exception   Something unexpected went wrong.
     */
    @SuppressWarnings( "unused" )
    static final Stream<TimeValue> valueProvider() throws Exception
    {
        final Builder<TimeValue> builder = Stream.builder();
        for( final var u : Time.values() )
        {
            for( final var v : List.of( "0.0", "1", "3.1415", "23456789", "1024" ) )
            {
                builder.add( new TimeValue( u, v ) );
            }
            for( final var v : List.of( BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN ) )
            {
                builder.add( new TimeValue( u, v ) );
            }
            for( final var v : List.of( 0.0, 0.1, 0.2, 0.25, 0.5, 1, 2, 3.1415, 1e10 ) )
            {
                builder.add( new TimeValue( u, v ) );
            }
        }
        builder.add( null );

        final var retValue = builder.build();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueProvider()
}
//  class TestTime

/*
 *  End of File
 */