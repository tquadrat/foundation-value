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
 *  The various instances of volume &hellip;
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Volume.java 1073 2023-10-01 11:08:51Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: Volume.java 1073 2023-10-01 11:08:51Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public enum Volume implements DimensionWithLinearConversion
{
        /*------------------*\
    ====** Enum Declaration **=================================================
        \*------------------*/
    /**
     *  A cubic millimeter.
     */
    CUBIC_MILLIMETER( new BigDecimal( "0.000000001" ), "mm^3" )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "mm³"; }
    },

    /**
     *  A micro liter.
     */
    MICRO_LITER( new BigDecimal( "0.000000001" ), "µl" ),

    /**
     *  A cubic centimeter.
     */
    CUBIC_CENTIMETER( new BigDecimal( "0.000001" ), "cm^3" )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "cm³"; }
    },

    /**
     *  A milliliter.
     */
    MILLI_LITER( new BigDecimal( "0.000001" ), "ml" ),

    /**
     *  A centiliter.
     */
    CENTI_LITER( new BigDecimal( "0.00001" ), "cl" ),

    /**
     *  A cubic inch.
     */
    CUBIC_INCH( new BigDecimal( "0.000016387064" ), "in^3",1 )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "in³"; }
    },

    /**
     *  An imperial fluid ounce.
     */
    FLUID_OUNCE_IMPERIAL( new BigDecimal( "0.0000284130625" ), "floz" ),

    /**
     *  A deciliter.
     */
    DECI_LITER( new BigDecimal( "0.0001" ), "dl" ),

    /**
     *  A pint liquid imperial.
     */
    PINT_LIQUID_IMPERIAL( new BigDecimal( "0.00056826128524935" ), "pt",1 ),

    /**
     *  A cubic decimeter.
     */
    CUBIC_DECIMETER( new BigDecimal( "0.001" ), "dm^3",1 )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "dm³"; }
    },

    /**
     *  A liter.
     */
    LITER( new BigDecimal( "0.001" ), "l",1 ),

    /**
     *  A bucket (&quot;Eimer&quot; in German). Obviously, this is not an
     *  official unit for a volume, but it is used quit often on a colloquial
     *  basis in Germany and other German-speaking countries to describe the
     *  amount of 10 liters.
     */
    BUCKET( new BigDecimal( "0.01" ), "Eimer" ),

    /**
     *  A US (liquid) gallon.
     */
    US_GALLON( new BigDecimal( "0.003785411784" ), "USGallon",1 )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "US Gallon"; }
    },

    /**
     *  An imperial gallon.
     */
    GALLON( new BigDecimal( "0.00454609" ), "gal",1 ),

    /**
     *  A cubic foot.
     */
    CUBIC_FOOT( new BigDecimal( "0.028316846592" ), "ft^3" )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "ft³"; }
    },

    /**
     *  A hekto liter.
     */
    HEKTO_LITER( new BigDecimal( "0.1" ), "hl",1 ),

    /**
     *  A barrel as used for mineral oil.
     */
    BARREL_OIL( new BigDecimal( "0.158987294928" ), "barrel(oil)" )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "barrel (oil)"; }
    },

    /**
     *  An imperial barrel.
     */
    IMPERIAL_BARREL( new BigDecimal( "0.16365924" ), "barrel(imperial)",1 )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "barrel (imperial)"; }
    },

    /**
     *  A cubic yard.
     */
    CUBIC_YARD( new BigDecimal( "0.764554857984" ), "yd^3" )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "yd³"; }
    },

    /**
     *  A cubic meter.
     */
    CUBIC_METER( BigDecimal.ONE, "m^3",3 )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "m³"; }
    },

    /**
     *  A &quot;Festmeter&quot;; this is used (in Germany) to specify an amount
     *  of wood.
     */
    FESTMETER( BigDecimal.ONE, "Festmeter",1 ),

    /**
     *  <p>{@summary A ton as used by <i>Traveller</i>® to specify the volume
     *  of a starship or other space going vessels or orbital installations.}
     *  It is defined as the volume of one metric ton or 1000&nbsp;kg of liquid
     *  hydrogen (H<sub>2</sub>) with a specific density of
     *  71&nbsp;kg/m<sup>3</sup>.</p>
     *  <p>The <i>Traveller</i>® literature also uses a value of
     *  13.5&nbsp;m<sup>3</sup>, based on the dimensions used with ship floor
     *  plans: a square on such a plan has a side length of 1.5&nbsp;m and the
     *  room height is taken as 3&nbsp;m, with each square is the equivalent of
     *  half a ton (or 6.75&nbsp;m<sup>3</sup>). For mapping purposes this is a
     *  valid approximation.</p>
     */
    TON( new BigDecimal( "14.084507"), "ton" ),

    /**
     *  A cubic kilometer.
     */
    CUBIC_KILO_METER( new BigDecimal( "1000000000" ), "km^3",3 )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "km³"; }
    },

    /**
     *  A cubic mile.
     */
    CUBIC_MILE( new BigDecimal( "4168181825.4406" ), "mi^3" )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "mi³"; }
    };

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
     *  Creates a new {@code Volume} instance, with a default precision of zero
     *  mantissa digits.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol    The unit symbol String.
     */
    private Volume( final BigDecimal factor, final String unitSymbol )
    {
        this( factor, unitSymbol, 0 );
    }   //  Volume()

    /**
     *  Creates a new {@code Volume} instance.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol    The unit symbol String.
     *  @param  precision   The default precision.
     */
    private Volume( final BigDecimal factor, final String unitSymbol, final int precision )
    {
        m_Factor = factor.stripTrailingZeros();
        m_UnitSymbol = unitSymbol;
        m_Precision = precision;
    }   //  Volume()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    @SuppressWarnings( "unchecked" )
    public final Volume baseUnit() { return CUBIC_METER; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final BigDecimal factor() { return m_Factor; }

    /**
     *  Returns the {@code Volume} instance for the given unit symbol.
     *
     *  @param  unitSymbol  The unit symbol.
     *  @return The respective instance.
     *  @throws IllegalArgumentException    The given unit is unknown.
     */
    public static final Volume forUnit( final String unitSymbol ) throws IllegalArgumentException
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
//  enum Volume

/*
 *  End of File
 */