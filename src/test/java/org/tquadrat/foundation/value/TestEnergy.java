/*
 * ============================================================================
 * Copyright © 2002-2023 by Thomas Thrien.
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

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.value.Energy.JOULE;

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

/**
 *  Tests for the classes
 *  {@link Energy},
 *  {@link EnergyValue},
 *  and
 *  {@link EnergyValueStringConverter}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestEnergy.java 1076 2023-10-03 18:36:07Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestEnergy.java 1076 2023-10-03 18:36:07Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.value.TestEnergy" )
public class TestEnergy extends DimensionedValueTestBase<Energy,EnergyValue>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    protected final Collection<Energy> getDimensions()
    {
        return asList( Energy.values() );
    }   //  getDimensions()

    /**
     *  {@inheritDoc}
     */
    @Override
    protected final StringConverter<EnergyValue> getStringConverter()
    {
        final var converter = StringConverter.forClass( EnergyValue.class );
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

        assertThrows( NullArgumentException.class, () -> new EnergyValue( null, BigDecimal.ONE ) );
        assertThrows( NullArgumentException.class, () -> new EnergyValue( null, 1.0 ) );
        assertThrows( NullArgumentException.class, () -> new EnergyValue( null, "1.0" ) );
        assertThrows( NullArgumentException.class, () -> new EnergyValue( JOULE, (BigDecimal) null ) );
        assertThrows( NullArgumentException.class, () -> new EnergyValue( JOULE, (String) null ) );

        assertThrows( EmptyArgumentException.class, () -> new EnergyValue( JOULE, EMPTY_STRING ) );
    }   //  testValueTypeConstructor()

    /**
     *  Provides test values for
     *  {@link #testValueConversion(EnergyValue)}.
     *
     *  @return The test values.
     *  @throws Exception   Something unexpected went wrong.
     */
    @SuppressWarnings( "unused" )
    static final Stream<EnergyValue> valueProvider() throws Exception
    {
        final Builder<EnergyValue> builder = Stream.builder();
        for( final var u : Energy.values() )
        {
            for( final var v : List.of( "0.0", "1", "3.1415", "23456789", "1024" ) )
            {
                builder.add( new EnergyValue( u, v ) );
            }
            for( final var v : List.of( BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN ) )
            {
                builder.add( new EnergyValue( u, v ) );
            }
            for( final var v : List.of( 0.0, 0.1, 0.2, 0.25, 0.5, 1, 2, 3.1415, 1.0E+10 ) )
            {
                builder.add( new EnergyValue( u, v ) );
            }
        }
        builder.add( null );

        final var retValue = builder.build();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueProviderBigDecimal()
}
//  class TestEnergy

/*
 *  End of File
 */