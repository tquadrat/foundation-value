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
import static org.tquadrat.foundation.value.api.DimensionedValue.MATH_CONTEXT;
import static org.tquadrat.foundation.value.internal.Tools.V0p8;
import static org.tquadrat.foundation.value.internal.Tools.V0t525;
import static org.tquadrat.foundation.value.internal.Tools.V100;
import static org.tquadrat.foundation.value.internal.Tools.V1p5;
import static org.tquadrat.foundation.value.internal.Tools.V1t3;
import static org.tquadrat.foundation.value.internal.Tools.V273;
import static org.tquadrat.foundation.value.internal.Tools.V32;
import static org.tquadrat.foundation.value.internal.Tools.V5t9;
import static org.tquadrat.foundation.value.internal.Tools.V7p5;
import static org.tquadrat.foundation.value.internal.Tools.retrieveName;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.i18n.Text;
import org.tquadrat.foundation.i18n.Translation;
import org.tquadrat.foundation.value.api.Dimension;

/**
 *  The various instances of temperature &hellip;
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Temperature.java 1072 2023-09-30 20:44:38Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: Temperature.java 1072 2023-09-30 20:44:38Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public enum Temperature implements Dimension
{
        /*------------------*\
    ====** Enum Declaration **=================================================
        \*------------------*/
    /**
     *  Kelvin.
     */
    @Text
    (
        description = "Kelvin",
        translations =
        {
            @Translation( language = "de", text = "Kelvin" ),
            @Translation( language = "en", text = "Kelvin" )
        }
    )
    KELVIN( kelvinValue -> kelvinValue, kelvinValue -> kelvinValue, "K", 2 ),

    /**
     *  Celsius.
     */
    @Text
    (
        description = "Celsius",
        translations =
        {
            @Translation( language = "de", text = "Grad Celsius" ),
            @Translation( language = "en", text = "Celsius" )
        }
    )
    CELSIUS( kelvinValue -> kelvinValue.subtract( V273 ), celsiusValue -> celsiusValue.add( V273 ), "C", 1 )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "°C"; }
    },

    /**
     *  Fahrenheit
     */
    @Text
    (
        description = "Fahrenheit",
        translations =
        {
            @Translation( language = "de", text = "Grad Fahrenheit" ),
            @Translation( language = "en", text = "Fahrenheit" )
        }
    )
    FAHRENHEIT( kelvinValue -> kelvinValue.subtract( V273 ).divide( V5t9, MATH_CONTEXT ).add( V32 ), fahrenheitValue -> fahrenheitValue.subtract( V32 ).multiply( V5t9 ).add( V273 ), "F" )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "°F"; }
    },

    /**
     *  Rankine.
     */
    @Text
    (
        description = "Rankine",
        translations =
        {
            @Translation( language = "de", text = "Grad Rankine" ),
            @Translation( language = "en", text = "Rankine" )
        }
    )
    RANKINE( kelvinValue -> kelvinValue.divide( V5t9, MATH_CONTEXT ), rankineValue -> rankineValue.multiply( V5t9 ), "Ra", 1 )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "°Ra"; }
    },

    /**
     *  Delisle.
     */
    @Text
    (
        description = "Delisle",
        translations =
        {
            @Translation( language = "de", text = "Grad Delisle" ),
            @Translation( language = "en", text = "Delisle" )
        }
    )
    DELISLE( kelvinValue -> kelvinValue.subtract( V273 ).multiply( V1p5 ).subtract( V100 ), delisleValue -> delisleValue.add( V100 ).divide( V1p5, MATH_CONTEXT ).add( V273 ) ,"De", 1 )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "°De"; }
    },

    /**
     *  Réaumur.
     */
    @Text
    (
        description = "Réaumur",
        translations =
        {
            @Translation( language = "de", text = "Grad Réaumur" ),
            @Translation( language = "en", text = "Réaumur" )
        }
    )
    REAUMUR( kelvinValue -> kelvinValue.subtract( V273 ).multiply( V0p8 ), reaumurValue -> reaumurValue.divide( V0p8, MATH_CONTEXT ).add( V273 ),"Re" )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "°Ré"; }
    },

    /**
     *  <p>{@summary Newton.}</p>
     *  <p>Yes, there is really a unit &quot;<i>Newton</i>&quot; for
     *  temperatures! It was introduced by Isaac Newton at the beginning of the
     *  18<sup>th</sup> century, but was not well established.</p>
     */
    @Text
    (
        description = "Newton",
        translations =
        {
            @Translation( language = "de", text = "Grad Newton" ),
            @Translation( language = "en", text = "Newton" )
        }
    )
    NEWTON( kelvinValue -> kelvinValue.subtract( V273 ).multiply( V1t3 ), newtonValue -> newtonValue.divide( V1t3, MATH_CONTEXT ).add( V273 ),"N" )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "°N"; }
    },

    /**
     *  Rømer.
     */
    @SuppressWarnings( "NonAsciiCharacters" ) @Text
    (
        description = "Rømer",
        translations =
        {
            @Translation( language = "de", text = "Grad Rømer" ),
            @Translation( language = "en", text = "Rømer" )
        }
    )
    RØMER( kelvinValue -> kelvinValue.subtract( V273 ).multiply( V0t525 ).add( V7p5 ), romerValue -> romerValue.subtract( V7p5 ).divide( V0t525, MATH_CONTEXT ).add( V273 ), "Ro" )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "°Rø"; }
    };

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The conversion for the given value from Kelvin to this unit.
     */
    private final UnaryOperator<BigDecimal> m_FromKelvin;

    /**
     *  The conversion for the given value from this unit to Kelvin.
     */
    private final UnaryOperator<BigDecimal> m_ToKelvin;

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
     *  Creates a new {@code Temperatur} instance, with a default precision of
     *  zero mantissa digits.
     *
     *  @param  fromKelvin  The conversion from Kelvin.
     *  @param  toKelvin    The conversion to Kelvin.
     *  @param  unitSymbol    The unit symbol String.
     */
    private Temperature( final UnaryOperator<BigDecimal> fromKelvin, final UnaryOperator<BigDecimal> toKelvin, final String unitSymbol )
    {
        this( fromKelvin, toKelvin, unitSymbol, 0 );
    }   //  Temperature()

    /**
     *  Creates a new {@code Temperatur} instance.
     *
     *  @param  fromKelvin  The conversion from Kelvin.
     *  @param  toKelvin    The conversion to Kelvin.
     *  @param  unitSymbol    The unit symbol String.
     *  @param  precision   The default precision.
     */
    private Temperature( final UnaryOperator<BigDecimal> fromKelvin, final UnaryOperator<BigDecimal> toKelvin, final String unitSymbol, final int precision )
    {
        m_FromKelvin = fromKelvin;
        m_ToKelvin = toKelvin;
        m_UnitSymbol = unitSymbol;
        m_Precision = precision;
    }   //  Temperature()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public final Temperature baseUnit() { return KELVIN; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final UnaryOperator<BigDecimal> fromBase() { return m_FromKelvin; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final int getPrecision() { return m_Precision; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final UnaryOperator<BigDecimal> toBase() { return m_ToKelvin; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        final var retValue = retrieveName( this );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final String unitSymbol() { return m_UnitSymbol; }

    /**
     *  Returns the {@code Temperature} instance for the given unit symbol.
     *
     *  @param  unitSymbol  The unit symbol.
     *  @return The respective instance.
     *  @throws IllegalArgumentException    The given unit is unknown.
     */
    public static final Temperature forUnit( final String unitSymbol ) throws IllegalArgumentException
    {
        requireNotEmptyArgument( unitSymbol, "unitSymbol" );

        final var retValue = stream( values() )
            .filter( v -> v.unitSymbol().equals( unitSymbol ) )
            .findFirst()
            .orElseThrow( () -> new IllegalArgumentException( format( MSG_UnknownUnit, unitSymbol ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  forUnit()
}
//  enum Area

/*
 *  End of File
 */