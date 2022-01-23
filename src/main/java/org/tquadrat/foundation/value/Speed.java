/*
 * ============================================================================
 * Copyright © 2002-2021 by Thomas Thrien.
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

import static java.math.MathContext.DECIMAL128;
import static java.util.Arrays.stream;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;
import static org.tquadrat.foundation.util.StringUtils.format;
import static org.tquadrat.foundation.value.Length.FOOT;
import static org.tquadrat.foundation.value.Length.KILOMETER;
import static org.tquadrat.foundation.value.Length.MILE;
import static org.tquadrat.foundation.value.Length.NAUTICAL_MILE;
import static org.tquadrat.foundation.value.Length.ÅNGSTRÖM;
import static org.tquadrat.foundation.value.Time.HOUR;
import static org.tquadrat.foundation.value.Time.SECOND;
import static org.tquadrat.foundation.value.Time.WEEK;

import java.math.BigDecimal;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.value.api.DimensionWithLinearConversion;

/**
 *  <p>{@summary The various instances of speed &hellip;}</p>
 *  <p>Because speed is distance per time, the values for
 *  {@link #factor()}
 *  are calculated based on the values from
 *  {@link Length#factor()}
 *  and
 *  {@link Time#factor()}.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Speed.java 827 2021-01-04 17:01:34Z tquadrat $
 *  @since 0.0.4
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: Speed.java 827 2021-01-04 17:01:34Z tquadrat $" )
@API( status = STABLE, since = "0.0.4" )
public enum Speed implements DimensionWithLinearConversion
{
        /*------------------*\
    ====** Enum Declaration **=================================================
        \*------------------*/
    /**
     *  &#x00C5;ngström per Week; this is by far not a useful unit for speed,
     *  but it is the implementation of a famous instance of Murphy's Law:
     *  &quot;Units are always provided in the most impractical form, e.g.
     *  speeds as &#x00C5;ngström per Week.&quot;
     */
    @SuppressWarnings( "NonAsciiCharacters" )
    ÅNGSTRÖM_PER_WEEK( ÅNGSTRÖM.factor().divide( WEEK.factor(), DECIMAL128 ), "\u212B/w" ),

    /**
     *  Feet per second.
     */
    FEET_PER_SECOND( FOOT.factor().divide( SECOND.factor(), DECIMAL128 ), "fps" ),

    /**
     *  Knot (nautical mile per hour).
     */
    KNOT( NAUTICAL_MILE.factor().divide( HOUR.factor(), DECIMAL128 ), "kn" ),

    /**
     *  Kilometer per hour.
     */
    KILOMETER_PER_HOUR( KILOMETER.factor().divide( HOUR.factor(), DECIMAL128 ), "km/h" ),

    /**
     *  Meter per second.
     */
    METER_PER_SECOND( BigDecimal.ONE, "m/s" ),

    /**
     *  Mile per hour.
     */
    MILE_PER_HOUR( MILE.factor().divide( HOUR.factor(), DECIMAL128 ), "mph" );

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The factor.
     */
    private final BigDecimal m_Factor;

    /**
     *  The unit symbol.
     */
    private final String m_UnitSymbol;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code Speed} instance.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol  The unit symbol.
     */
    private Speed( final BigDecimal factor, final String unitSymbol )
    {
        m_Factor = factor.stripTrailingZeros();
        m_UnitSymbol = unitSymbol;
    }   //  Speed()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    @SuppressWarnings( "unchecked" )
    public final Speed baseUnit() { return METER_PER_SECOND; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final BigDecimal factor() { return m_Factor; }

    /**
     *  Returns the {@code Speed} instance for the given unit.
     *
     *  @param  unitSymbol  The unit symbol.
     *  @return The respective instance.
     *  @throws IllegalArgumentException    The given unit is unknown.
     */
    public static final Speed forUnit( final String unitSymbol ) throws IllegalArgumentException
    {
        requireNotEmptyArgument( unitSymbol, "unit" );

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
    public final String unitSymbol() { return m_UnitSymbol; }
}
//  enum Speed

/*
 *  End of File
 */