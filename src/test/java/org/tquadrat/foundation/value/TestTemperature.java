/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
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

import static java.lang.String.format;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.EmptyArgumentException;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.lang.StringConverter;

/**
 *  Tests for the classes
 *  {@link Temperature},
 *  {@link TemperatureValue},
 *  and
 *  {@link TemperatureValueStringConverter}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestTemperature.java 1076 2023-10-03 18:36:07Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestTemperature.java 1076 2023-10-03 18:36:07Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.value.TestTemperature" )
public class TestTemperature extends DimensionedValueTestBase<Temperature,TemperatureValue>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    protected final Collection<Temperature> getDimensions()
    {
        return asList( Temperature.values() );
    }   //  getDimensions()

    /**
     *  {@inheritDoc}
     */
    @Override
    protected final StringConverter<TemperatureValue> getStringConverter()
    {
        final var converter = StringConverter.forClass( TemperatureValue.class );
        assertTrue( converter.isPresent() );
        final var retValue = converter.get();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getStringConverter()

    /**
     *  {@inheritDoc}
     */
    @Override
    @Test
    final void testValueTypeConstructor() throws Exception
    {
        skipThreadTest();

        {
            final Class<? extends Throwable> expectedException = NullArgumentException.class;

            try
            {
                final var candidate = new TemperatureValue( null, BigDecimal.ONE );
                assertNotNull( candidate );
                fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
            }
            catch( final AssertionError e ) { throw e; }
            catch( final Throwable t )
            {
                final var isExpectedException = expectedException.isInstance( t );
                if( !isExpectedException )
                {
                    t.printStackTrace( out );
                }
                assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            }
            try
            {
                final var candidate = new TemperatureValue( null, 1.0 );
                assertNotNull( candidate );
                fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
            }
            catch( final AssertionError e ) { throw e; }
            catch( final Throwable t )
            {
                final var isExpectedException = expectedException.isInstance( t );
                if( !isExpectedException )
                {
                    t.printStackTrace( out );
                }
                assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            }
            try
            {
                final var candidate = new TemperatureValue( null, "1.0" );
                assertNotNull( candidate );
                fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
            }
            catch( final AssertionError e ) { throw e; }
            catch( final Throwable t )
            {
                final var isExpectedException = expectedException.isInstance( t );
                if( !isExpectedException )
                {
                    t.printStackTrace( out );
                }
                assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            }

            try
            {
                final var candidate = new TemperatureValue( Temperature.RØMER, (BigDecimal) null );
                assertNotNull( candidate );
                fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
            }
            catch( final AssertionError e ) { throw e; }
            catch( final Throwable t )
            {
                final var isExpectedException = expectedException.isInstance( t );
                if( !isExpectedException )
                {
                    t.printStackTrace( out );
                }
                assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            }
            try
            {
                final var candidate = new TemperatureValue( Temperature.RØMER, (String) null );
                assertNotNull( candidate );
                fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
            }
            catch( final AssertionError e ) { throw e; }
            catch( final Throwable t )
            {
                final var isExpectedException = expectedException.isInstance( t );
                if( !isExpectedException )
                {
                    t.printStackTrace( out );
                }
                assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            }
        }

        {
            final Class<? extends Throwable> expectedException = EmptyArgumentException.class;

            try
            {
                final var candidate = new TemperatureValue( Temperature.RØMER, EMPTY_STRING );
                assertNotNull( candidate );
                fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
            }
            catch( final AssertionError e ) { throw e; }
            catch( final Throwable t )
            {
                final var isExpectedException = expectedException.isInstance( t );
                if( !isExpectedException )
                {
                    t.printStackTrace( out );
                }
                assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            }
        }
    }   //  testValueTypeConstructor()

    /**
     *  Tests the validation.
     *
     *  @param  dimension   The units.
     *  @throws Exception   Something unexpected went wrong.
     */
    @ParameterizedTest
    @EnumSource( Temperature.class )
    final void testValidation( final Temperature dimension )
    {
        final var value = new BigDecimal( "-1234.5678" );
        assertThrows( IllegalArgumentException.class, () -> new TemperatureValue( dimension, value ) );
    }   //  testValidation()

    /**
     *  Provides test values for
     *  {@link #testValueConversion(TemperatureValue)}.
     *
     *  @return The test values.
     *  @throws Exception   Something unexpected went wrong.
     */
    @SuppressWarnings( "unused" )
    static final Stream<TemperatureValue> valueProvider() throws Exception
    {
        final Builder<TemperatureValue> builder = Stream.builder();
        for( final var u : Temperature.values() )
        {
            for( final var v : List.of( "0.0", "1", "3.1415", "23456789", "1024" ) )
            {
                builder.add( new TemperatureValue( u, v ) );
            }
            for( final var v : List.of( BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN ) )
            {
                builder.add( new TemperatureValue( u, v ) );
            }
            for( final var v : List.of( 0.0, 0.1, 0.2, 0.25, 0.5, 1, 2, 3.1415, 1e10 ) )
            {
                builder.add( new TemperatureValue( u, v ) );
            }
        }
        builder.add( null );

        final var retValue = builder.build();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueProviderBigDecimal()
}
//  class TestTemperature

/*
 *  End of File
 */