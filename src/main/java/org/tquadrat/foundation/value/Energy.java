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
 *  The various instances of energy &hellip;
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Energy.java 1077 2023-10-14 23:00:23Z tquadrat $
 *  @since 0.3.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: Energy.java 1077 2023-10-14 23:00:23Z tquadrat $" )
@API( status = STABLE, since = "0.3.0" )
public enum Energy implements DimensionWithLinearConversion
{
        /*------------------*\
    ====** Enum Declaration **=================================================
        \*------------------*/
    /**
     *  An electron volt.
     */
    ELECTRONVOLT( new BigDecimal( "1.602176634E-19"), "eV" ),

    /**
     *  An erg.
     */
    ERG( new BigDecimal( "1E-7"), "erg" ),

    /**
     *  A Joule.
     */
    JOULE( BigDecimal.ONE, "J", 1 ),

    /**
     *  A calorie.
     */
    CALORIE( new BigDecimal( "4.1868" ), "cal" ),

    /**
     *  A kilo Joule.
     */
    KILOJOULE( new BigDecimal( "1000" ), "kJ", 1 ),

    /**
     *  A British Thermal Unit (BTU).
     */
    BTU( new BigDecimal( "1055.05585262" ), "BTU" ),

    /**
     *  A kilo calorie.
     */
    KILOCALORIE( new BigDecimal( "4186.8" ), "kcal" ),

    /**
     *  A kilopond meter.
     */
    KILOPONDMETER( new BigDecimal( "9.80665" ), "kpm" ),

    /**
     *  A Mega Joule.
     */
    MEGAJOULE( new BigDecimal( "1E+6" ), "MJ" ),

    /**
     *  A kilo Watt per hour.
     */
    KILOWATT_HOUR( new BigDecimal( "3.6E+6" ), "kWh" ),

    /**
     *  <p>{@summary The energy equivalent of one kg TNT}. This is usually used
     *  to rate the power of (nuclear) bombs or other explosives.</p>
     */
    KILOGRAM_TNT( new BigDecimal( "4.184E+6" ), "kg(TNT)" ),

    /**
     *  A Giga Joule.
     */
    GIGAJOULE( new BigDecimal( "1E+9" ), "GJ" ),

    /**
     *  A Mega Joule.
     */
    TERAJOULE( new BigDecimal( "1E+12" ), "TJ" ),

    /**
     *  <p>{@summary The energy equivalent of one thousand tons of TNT}. This
     *  is usually used to rate the power of (nuclear) bombs or other
     *  explosives.</p>
     */
    KILOTON_TNT( new BigDecimal( "4.184E+12" ), "kT(TNT)" ),

    /**
     *  <p>{@summary The energy equivalent of one million tons of TNT}. This is
     *  usually used to rate the power of (nuclear) bombs or other
     *  explosives.</p>
     */
    MEGATON_TNT( new BigDecimal( "4.184E+15" ), "MT(TNT)" ),

    /**
     *  A quad.
     */
    QUAD( new BigDecimal( "1055.05585262E+15" ), "quad" ),

    /**
     *  <p>{@summary A foe (or a 'bethe').}</p>
     *  <p>A {@code foe} is used to express the large amount of energy released
     *  by a supernova. An acronym for &quot;[ten to the power of] fifty-one
     *  ergs&quot;, the term was introduced by Gerald E. Brown of Stony Brook
     *  University in his work with Hans Bethe, because <cite>&quot;it came up
     *  often enough in our work&quot;</cite>.</p>
     *  <p>Without mentioning the foe, Steven Weinberg proposed in 2006 &quot;a
     *  new unit called the bethe ({@code B})&quot; with the same value, to
     *  &quot;<i>replace</i>&quot; it.</p>
     *  <p>This unit of measure is convenient because a supernova typically
     *  releases about one {@code foe} of observable energy in a very short
     *  period (which can be measured in seconds).</p>
     */
    FOE( new BigDecimal( "1E+44" ), "foe" ),

    /**
     *  <p>{@summary A bethe.}</p>
     *  <p>This is the same as
     *  {@link #FOE foe}.</p>
     */
    BETHE( new BigDecimal( "1E+44" ), "B" );

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
     *  Creates a new {@code Length} instance, with a default precision of zero
     *  mantissa digits.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol    The unit symbol String.
     */
    private Energy( final BigDecimal factor, final String unitSymbol )
    {
        this( factor, unitSymbol, 0 );
    }   //  Energy()

    /**
     *  Creates a new {@code Length} instance.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol    The unit symbol String.
     *  @param  precision   The default precision.
     */
    private Energy( final BigDecimal factor, final String unitSymbol, final int precision )
    {
        m_Factor = factor.stripTrailingZeros();
        m_UnitSymbol = unitSymbol;
        m_Precision = precision;
    }   //  Energy()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    @SuppressWarnings( "unchecked" )
    public final Energy baseUnit() { return JOULE; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final BigDecimal factor() { return m_Factor; }

    /**
     *  Returns the {@code Energy} instance for the given unit symbol.
     *
     *  @param  unitSymbol  The unit symbol.
     *  @return The respective instance.
     *  @throws IllegalArgumentException    The given unit is unknown.
     */
    public static final Energy forUnit( final String unitSymbol ) throws IllegalArgumentException
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
//  enum Energy

/*
 *  End of File
 */