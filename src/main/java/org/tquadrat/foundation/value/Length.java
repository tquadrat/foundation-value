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
 *  The various instances of length &hellip;
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Length.java 1072 2023-09-30 20:44:38Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: Length.java 1072 2023-09-30 20:44:38Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public enum Length implements DimensionWithLinearConversion
{
        /*------------------*\
    ====** Enum Declaration **=================================================
        \*------------------*/
    /**
     *  An &#x00C5;ngström.
     */
    @SuppressWarnings( {"NonAsciiCharacters", "UnnecessaryUnicodeEscape"} )
    ÅNGSTRÖM( new BigDecimal( "0.0000000001" ), "\u212B" ),

    /**
     *  A nanometer.
     */
    NANOMETER( new BigDecimal( "0.000000001" ), "nm" ),

    /**
     *  A mikrometer.
     */
    MICROMETER( new BigDecimal( "0.000001" ), "µm" ),

    /**
     *  A typographical dot.
     */
    PICA( new BigDecimal( "0.0003527777777778" ), "pica", 1 ),

    /**
     *  A millimeter.
     */
    MILLIMETER( new BigDecimal( "0.001" ), "mm" ),

    /**
     *  A centimeter.
     */
    CENTIMETER( new BigDecimal( "0.01" ), "cm",1 ),

    /**
     *  An inch.
     */
    INCH( new BigDecimal( "0.0254" ), "in." ),

    /**
     *  A decimeter.
     */
    DECIMETER( new BigDecimal( "0.1" ), "dm" ),

    /**
     *  A foot (12
     *  {@linkplain #INCH inch}).
     */
    FOOT( new BigDecimal( "0.3048" ), "ft." ),

    /**
     *  A yard (3
     *  {@linkplain #FOOT feet}).
     */
    YARD( new BigDecimal( "0.9144" ), "yd." ),

    /**
     *  A meter.
     */
    METER( BigDecimal.ONE, "m", 1 ),

    /**
     *  A fathom.
     */
    FATHOM( new BigDecimal( "1.852" ), "fth." ),

    /**
     *  A cable length (1/10 of a
     *  {@linkplain #NAUTICAL_MILE sea mile}
     *  or 100
     *  {@linkplain #FATHOM fathom}).
     */
    CABLE( new BigDecimal( "185.2" ), "cbl." ),

    /**
     *  A kilometer.
     */
    KILOMETER( new BigDecimal( "1000.0" ), "km", 1 ),

    /**
     *  A mile (1760 yard).
     */
    MILE( new BigDecimal( "1609.3440" ), "mi." ),

    /**
     *  A nautical or sea mile.
     */
    NAUTICAL_MILE( new BigDecimal( "1852.0" ), "NM" ),

    /**
     *  An astronomical unit.
     */
    ASTRONOMICAL_UNIT( new BigDecimal( "149597870700.0" ), "AU", 1 ),

    /**
     *  A light year.
     */
    LIGHTYEAR( new BigDecimal( "9460730472580800.0" ), "ly" ),

    /**
     *  A parsec, according to the definition of the IAU.
     */
    PARSEC( new BigDecimal( "3.0857E16" ), "pc", 2 );

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
    private Length( final BigDecimal factor, final String unitSymbol )
    {
        this( factor, unitSymbol, 0 );
    }   //  Length()

    /**
     *  Creates a new {@code Length} instance.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol    The unit symbol String.
     *  @param  precision   The default precision.
     */
    private Length( final BigDecimal factor, final String unitSymbol, final int precision )
    {
        m_Factor = factor.stripTrailingZeros();
        m_UnitSymbol = unitSymbol;
        m_Precision = precision;
    }   //  Length()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    @SuppressWarnings( "unchecked" )
    public final Length baseUnit() { return METER; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final BigDecimal factor() { return m_Factor; }

    /**
     *  Returns the {@code Length} instance for the given unit symbol.
     *
     *  @param  unitSymbol  The unit symbol.
     *  @return The respective instance.
     *  @throws IllegalArgumentException    The given unit is unknown.
     */
    public static final Length forUnit( final String unitSymbol ) throws IllegalArgumentException
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
//  enum Length

/*
 *  End of File
 */