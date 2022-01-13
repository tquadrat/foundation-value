/*
 * ============================================================================
 * Copyright © 2002-2022 by Thomas Thrien.
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

import static java.lang.System.out;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.util.StringUtils.format;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.EmptyArgumentException;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.lang.StringConverter;
import com.sun.jdi.Value;

/**
 *  Tests for the classes
 *  {@link Volume},
 *  {@link VolumeValue},
 *  and
 *  {@link VolumeValueStringConverter}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestArea.java 829 2021-01-05 09:08:51Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestArea.java 829 2021-01-05 09:08:51Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.value.TestVolume" )
public class TestVolume extends DimensionedValueTestBase<Volume,VolumeValue>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    protected final Collection<Volume> getDimensions()
    {
        return asList( Volume.values() );
    }   //  getDimensions()

    /**
     *  {@inheritDoc}
     */
    @Override
    protected final StringConverter<VolumeValue> getStringConverter()
    {
        final var converter = StringConverter.forClass( VolumeValue.class );
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

        Volume dimension;
        AreaValue area;
        LengthValue height, length, width;
        BigDecimal value;
        String stringValue;

        {
            final Class<? extends Throwable> expectedException = NullArgumentException.class;

            dimension = null;
            value = BigDecimal.ONE;
            try
            {
                final var candidate = new VolumeValue( dimension, value );
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

            dimension = null;
            try
            {
                final var candidate = new VolumeValue( dimension, 1.0 );
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

            dimension = null;
            stringValue = "1.0";
            try
            {
                final var candidate = new VolumeValue( dimension, stringValue );
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

            dimension = null;
            height = new LengthValue( Length.METER, BigDecimal.ONE );
            area = new AreaValue( Area.SQUARE_METER, BigDecimal.ONE );
            try
            {
                final var candidate = new VolumeValue( dimension, area, height );
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

            dimension = null;
            height = new LengthValue( Length.METER, BigDecimal.ONE );
            length = new LengthValue( Length.METER, BigDecimal.ONE );
            width = new LengthValue( Length.METER, BigDecimal.ONE );
            try
            {
                final var candidate = new VolumeValue( dimension, length, width, height );
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

            dimension = Volume.CUBIC_METER;
            value = null;
            try
            {
                final var candidate = new VolumeValue( dimension, value );
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

            dimension = Volume.CUBIC_METER;
            stringValue = null;
            try
            {
                final var candidate = new VolumeValue( dimension, stringValue );
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

            dimension = Volume.CUBIC_METER;
            height = null;
            area = new AreaValue( Area.SQUARE_METER, BigDecimal.ONE );
            try
            {
                final var candidate = new VolumeValue( dimension, area, height );
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

            dimension = Volume.CUBIC_METER;
            height = new LengthValue( Length.METER, BigDecimal.ONE );
            area = null;
            try
            {
                final var candidate = new VolumeValue( dimension, area, height );
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

            dimension = Volume.CUBIC_METER;
            height = null;
            length = new LengthValue( Length.METER, BigDecimal.ONE );
            width = new LengthValue( Length.METER, BigDecimal.ONE );
            try
            {
                final var candidate = new VolumeValue( dimension, length, width, height );
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

            dimension = Volume.CUBIC_METER;
            height = new LengthValue( Length.METER, BigDecimal.ONE );
            length = null;
            width = new LengthValue( Length.METER, BigDecimal.ONE );
            try
            {
                final var candidate = new VolumeValue( dimension, length, width, height );
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

            dimension = Volume.CUBIC_METER;
            height = new LengthValue( Length.METER, BigDecimal.ONE );
            length = new LengthValue( Length.METER, BigDecimal.ONE );
            width = null;
            try
            {
                final var candidate = new VolumeValue( dimension, length, width, height );
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

            dimension = Volume.CUBIC_METER;
            stringValue = EMPTY_STRING;
            try
            {
                final var candidate = new VolumeValue( dimension, stringValue );
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
     *  Provides test values for
     *  {@link #testValueConversion(AreaValue)}.
     *
     *  @return The test values.
     *  @throws Exception   Something unexpected went wrong.
     */
    @SuppressWarnings( "unused" )
    static final Stream<VolumeValue> valueProvider() throws Exception
    {
        final Builder<VolumeValue> builder = Stream.builder();
        for( final var u : Volume.values() )
        {
            for( final var v : List.of( "0.0", "1", "3.1415", "23456789", "1024" ) )
            {
                builder.add( new VolumeValue( u, v ) );
            }
            for( final var v : List.of( BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN ) )
            {
                builder.add( new VolumeValue( u, v ) );
            }
            for( final var v : List.of( 0.0, 0.1, 0.2, 0.25, 0.5, 1, 2, 3.1415, 1e10 ) )
            {
                builder.add( new VolumeValue( u, v ) );
            }
        }
        builder.add( null );

        final var retValue = builder.build();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueProviderBigDecimal()
}
//  class TestArea

/*
 *  End of File
 */