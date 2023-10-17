/*
 * ============================================================================
 * Copyright © 2002-2023 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
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
import static java.util.Arrays.stream;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;

import java.math.BigDecimal;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.value.api.DimensionWithLinearConversion;

/**
 *  The various instances of masses and weights (although this is not really
 *  the same, from a physical or scientific point of view ...).
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Mass.java 1077 2023-10-14 23:00:23Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "NewClassNamingConvention" )
@ClassVersion( sourceVersion = "$Id: Mass.java 1077 2023-10-14 23:00:23Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public enum Mass implements DimensionWithLinearConversion
{
        /*------------------*\
    ====** Enum Declaration **=================================================
        \*------------------*/
    /**
     *  A milligram.
     */
    MILLIGRAM( new BigDecimal( "0.000001" ), "mg" ),

    /**
     *  An English grain. This unit is still in use to define the mass of
     *  firearm bullets, arrow heads, or the explosives load for firearm
     *  cartridges.
     */
    GRAIN( new BigDecimal( "0.00006479891" ), "gr." ),

    /**
     *  A metric carat; this is defined as 0.2&nbsp;g; although no unit sign is
     *  officially defined, the use of &quot;ct&quot; is widely accepted. That
     *  is why we use it here as well.
     */
    CARAT( new BigDecimal( "0.0002" ), "ct" ),

    /**
     *  A gram.
     */
    GRAM( new BigDecimal( "0.001" ), "g" ),

    /**
     *  A dram (Avoirdupois), a 1/16 of an
     *  {@linkplain #OUNCE ounce}.
     */
    DRAM( new BigDecimal( "0.001771845195312500" ), "dr." ),

    /**
     *  An ounce (Avoirdupois), a 1/16 of a
     *  {@linkplain #POUND pound}.
     */
    OUNCE( new BigDecimal( "0.028349523125" ), "oz." ),

    /**
     *  A troy ounce (same as <i>Apothecaries Ounce</i>, as used for precious
     *  metal.
     */
    TROY_OUNCE( new BigDecimal( "0.0311034768" ), "oz.tr." ),

    /**
     *  An imperial pound (Avoirdupois), 7000
     *  {@linkplain #GRAIN grain}.
     */
    POUND( new BigDecimal( "0.45359237" ), "lb." ),

    /**
     *  A kilogram.
     */
    KILOGRAM( BigDecimal.ONE, "kg" ),

    /**
     *  <p>{@summary A stone; although abandoned since 1985, it will still be used in some
     *  Commonwealth countries to determine the body weight.}</p>
     *  <p>1&nbsp;st&nbsp;=&nbsp;14&nbsp;{@linkplain #POUND lb.}&nbsp;=&nbsp;224&nbsp;{@linkplain #OUNCE oz.}&nbsp;=&nbsp;3584&nbsp;{@linkplain #DRAM dr.}</p>
     */
    STONE( new BigDecimal( "6.35029318" ), "st" ),

    /**
     *  A short ton (2000 pound avoirdupois).
     */
    SHORT_TON( new BigDecimal( "907.18474" ), "to." ),

    /**
     *  A metric ton.
     */
    TON( new BigDecimal( "1000.0" ), "t" ),

    /**
     *  <p>{@summary The (estimated) mass of our sun.} This is often used when
     *  comparing stars.</p>
     */
    @API( status = STABLE, since ="0.3.0" )
    SOLAR_MASS( new BigDecimal( "1.989E+30" ), "SolarMass" ),

    /**
     *  <p>{@summary The mass of our planet Earth.} This is often used when
     *  comparing masses of astronomical objects.</p>
     */
    @API( status = STABLE, since ="0.3.0" )
    EARTH_MASS( new BigDecimal( "5.9722E+24" ), "EarthMass" );

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The factor.
     */
    private final BigDecimal m_Factor;

    /**
     *  The default precision.
     */
    private final int m_Precision;

    /**
     *  The unit string.
     */
    private final String m_UnitSymbol;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code Mass} instance with a default precision of zero
     *  mantissa digits.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol    The unit string.
     */
    private Mass( final BigDecimal factor, final String unitSymbol )
    {
        this( factor, unitSymbol, 0 );
    }   //  Mass()

    /**
     *  Creates a new {@code Mass} instance.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol    The unit string.
     *  @param  precision   The default precision.
     */
    private Mass( final BigDecimal factor, final String unitSymbol, final int precision )
    {
        m_Factor = factor.stripTrailingZeros();
        m_UnitSymbol = unitSymbol;
        m_Precision = precision;
    }   //  Mass()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public final Mass baseUnit() { return KILOGRAM; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final BigDecimal factor() { return m_Factor; }

    /**
     *  Returns the {@code Weight} instance for the given unit.
     *
     *  @param  unitSymbol  The unit symbol.
     *  @return The respective instance.
     *  @throws IllegalArgumentException    The given unit is unknown.
     */
    public static final Mass forUnit( final String unitSymbol ) throws IllegalArgumentException
    {
        requireNotEmptyArgument( unitSymbol, "unitSymbol" );

        final var retValue = stream( values() )
            .filter( v -> v.unitSymbol().equals( unitSymbol ) )
            .findFirst()
            .orElseThrow( () -> new IllegalArgumentException( format( MSG_UnknownUnit, unitSymbol ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  forUnit()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final int getPrecision() { return m_Precision; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final String unitSymbol() { return m_UnitSymbol; }
}
//  enum Mass

/*
 *  End of File
 */