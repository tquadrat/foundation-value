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
 *  The various instances of area &hellip;
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Area.java 1072 2023-09-30 20:44:38Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "NewClassNamingConvention" )
@ClassVersion( sourceVersion = "$Id: Area.java 1072 2023-09-30 20:44:38Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public enum Area implements DimensionWithLinearConversion
{
        /*------------------*\
    ====** Enum Declaration **=================================================
        \*------------------*/
    /**
     *  A square millimeter.
     */
    SQUARE_MILLIMETER( new BigDecimal( "0.000001" ), "mm^2" )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "mm²"; }
    },

    /**
     *  A square centimeter.
     */
    SQUARE_CENTIMETER( new BigDecimal( "0.0001" ), "cm^2",1 )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "cm²"; }
    },

    /**
     *  A square inch.
     */
    SQUARE_INCH( new BigDecimal( "0.00064516" ), "sqin" ),

    /**
     *  A square foot (144
     *  {@linkplain #SQUARE_INCH square inch}).
     */
    SQUARE_FOOT( new BigDecimal( "0.092990304" ), "sqft" ),

    /**
     *  A square yard (9
     *  {@linkplain #SQUARE_FOOT square feet}).
     */
    SQUARE_YARD( new BigDecimal( "0.83612736" ), "yd^2" )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "yd²"; }
    },

    /**
     *  A square_meter.
     */
    SQUARE_METER( BigDecimal.ONE, "m^2", 1 )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "m²"; }
    },

    /**
     *  An Ar (a unit for areas, used in the EU and Switzerland). It is 100
     *  {@linkplain #SQUARE_METER square meter}.
     */
    AR( new BigDecimal( "100.0" ), "a" ),

    /**
     *  A %quot;Morgen&quot;, an ancient unit for areas that was used in
     *  Germany (and is still used sometimes). It is 25
     *  {@linkplain #AR ar},
     *  2500
     *  {@linkplain #SQUARE_METER square meter}
     *  or &#188;
     *  {@linkplain #HEKTAR ha}.
     */
    MORGEN( new BigDecimal( "2500" ), "Mg" ),

    /**
     *  An acre.
     */
    ACRE( new BigDecimal( "4046.8564224" ), "ac" ),

    /**
     *  A hektar (a unit for areas, used in the EU and Switzerland). It is 100
     *  {@linkplain #AR a}
     *  or 10000
     *  {@linkplain #SQUARE_METER square meter}.
     */
    HEKTAR( new BigDecimal( "10000.0" ), "ha" ),

    /**
     *  A square kilometer.
     */
    SQUARE_KILOMETER( new BigDecimal( "1000000.0" ), "km^2", 3 )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "km²"; }
    },

    /**
     *  A square mile (1760 yard).
     */
    SQUARE_MILE( new BigDecimal( "2589988.110336" ), "sqmi" );

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
     *  Creates a new {@code Area} instance, with a default precision of zero
     *  mantissa digits.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol    The unit symbol String.
     */
    private Area( final BigDecimal factor, final String unitSymbol )
    {
        this( factor, unitSymbol, 0 );
    }   //  Area()

    /**
     *  Creates a new {@code Area} instance.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol    The unit symbol String.
     *  @param  precision   The default precision.
     */
    private Area( final BigDecimal factor, final String unitSymbol, final int precision )
    {
        m_Factor = factor.stripTrailingZeros();
        m_UnitSymbol = unitSymbol;
        m_Precision = precision;
    }   //  Area()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    @SuppressWarnings( "unchecked" )
    public final Area baseUnit() { return SQUARE_METER; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final BigDecimal factor() { return m_Factor; }

    /**
     *  Returns the {@code Area} instance for the given unit symbol.
     *
     *  @param  unitSymbol  The unit symbol.
     *  @return The respective instance.
     *  @throws IllegalArgumentException    The given unit is unknown.
     */
    public static final Area forUnit( final String unitSymbol ) throws IllegalArgumentException
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
//  enum Area

/*
 *  End of File
 */