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
 *  The various instances of power &hellip;
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Power.java 1073 2023-10-01 11:08:51Z tquadrat $
 *  @since 0.3.0
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "NewClassNamingConvention" )
@ClassVersion( sourceVersion = "$Id: Power.java 1073 2023-10-01 11:08:51Z tquadrat $" )
@API( status = STABLE, since = "0.3.0" )
public enum Power implements DimensionWithLinearConversion
{
        /*------------------*\
    ====** Enum Declaration **=================================================
        \*------------------*/
    /**
     *  <p>{@summary An erg per second.}</p>
     *  <p>This is the unit of power in the CGS unit system.</p>
     */
    ERG_PER_SECOND( new BigDecimal( "1E-7" ), "erg/s" ),

    /**
     *  A micro Watt.
     */
    MICROWATT( new BigDecimal( "1E-6" ), "µW" ),

    /**
     *  A milli Watt.
     */
    MILLIWATT( new BigDecimal( "1E-3" ), "mW" ),

    /**
     *  A Watt.
     */
    WATT( BigDecimal.ONE, "W", 1 ),

    /**
     *  A 'horsepower'.
     */
    HORSE_POWER( new BigDecimal( "735.49875" ), "HP" ),

    /**
     *  A kilo Watt.
     */
    KILOWATT( new BigDecimal( "1000" ), "kW", 1 ),

    /**
     *  A kilo Watt.
     */
    MEGAWATT( new BigDecimal( "1E+6" ), "MW", 1 ),

    /**
     *  A giga Watt.
     */
    GIGAWATT( new BigDecimal( "1E+9" ), "GW", 1 ),

    /**
     *  A Tera Watt.
     */
    TERAWATT( new BigDecimal( "1E+12" ), "TW", 1 );

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
     *  Creates a new {@code Power} instance, with a default precision of zero
     *  mantissa digits.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol    The unit symbol String.
     */
    private Power( final BigDecimal factor, final String unitSymbol )
    {
        this( factor, unitSymbol, 0 );
    }   //  Power()

    /**
     *  Creates a new {@code Power} instance.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol    The unit symbol String.
     *  @param  precision   The default precision.
     */
    private Power( final BigDecimal factor, final String unitSymbol, final int precision )
    {
        m_Factor = factor.stripTrailingZeros();
        m_UnitSymbol = unitSymbol;
        m_Precision = precision;
    }   //  Power()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    @SuppressWarnings( "unchecked" )
    public final Power baseUnit() { return WATT; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final BigDecimal factor() { return m_Factor; }

    /**
     *  Returns the {@code Power} instance for the given unit symbol.
     *
     *  @param  unitSymbol  The unit symbol.
     *  @return The respective instance.
     *  @throws IllegalArgumentException    The given unit is unknown.
     */
    public static final Power forUnit( final String unitSymbol ) throws IllegalArgumentException
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
//  enum Power

/*
 *  End of File
 */