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

import static java.util.FormattableFlags.ALTERNATE;
import static java.util.FormattableFlags.LEFT_JUSTIFY;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.util.StringUtils.format;
import static org.tquadrat.foundation.util.StringUtils.padLeft;
import static org.tquadrat.foundation.util.StringUtils.padRight;
import static org.tquadrat.foundation.value.Time.DAY;
import static org.tquadrat.foundation.value.Time.HOUR;
import static org.tquadrat.foundation.value.Time.MINUTE;
import static org.tquadrat.foundation.value.Time.NANOSECOND;
import static org.tquadrat.foundation.value.Time.WEEK;
import static org.tquadrat.foundation.value.Time.YEAR;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Formatter;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.value.api.ValueBase;

/**
 *  A value class for <i>times</i>, what means <i>periods of time</i> in this
 *  case, opposite to the time displayed on the wall-clock.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TimeValue.java 880 2021-02-27 10:47:40Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: TimeValue.java 880 2021-02-27 10:47:40Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public final class TimeValue extends ValueBase<Time,TimeValue>
{
        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The serial version UID for objects of this class: {@value}.
     *
     *  @hidden
     */
    @Serial
    private static final long serialVersionUID = 1729884766468723788L;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code TimeValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public TimeValue( final Time dimension, final BigDecimal value )
    {
        super( dimension, value );
    }   //  TimeValue()

    /**
     *  Creates a new {@code TimeValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value; it must be possible to parse the given
     *      String into a
     *      {@link BigDecimal}.
     *  @throws NumberFormatException   The provided value cannot be converted
     *      into a {@code BigDecimal}.
     */
    public TimeValue( final Time dimension, final String value ) throws NumberFormatException
    {
        super( dimension, value );
    }   //  TimeValue()

    /**
     *  Creates a new {@code TimeValue} instance.
     *
     *  @param  <N> The type of {@code value}.
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public <N extends Number> TimeValue( final Time dimension, final N value )
    {
        super( dimension, value );
    }   //  TimeValue()

    /**
     *  Creates a new {@code TimeValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public TimeValue( final Time dimension, final Duration value )
    {
        super( NANOSECOND, requireNonNullArgument( value, "value" ).get( ChronoUnit.NANOS ) );
        setUnit( dimension );
    }   //  TimeValue()

    /**
     *  Creates a new {@code TimeValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public TimeValue( final Time dimension, final Period value )
    {
        this( dimension, translatePeriodToDuration( value ) );
    }   //  TimeValue()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns this instance of {@code TimeValue} as an instance of
     *  {@link Duration}.
     *
     *  @return The duration that corresponds to this time value.
     */
    public final Duration asDuration()
    {
        final var retValue = Duration.ofNanos( convert( NANOSECOND ).longValue() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  asDuration()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final TimeValue clone()
    {
        final var retValue = (TimeValue) super.clone();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  clone()

    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "ProhibitedExceptionThrown" )
    @Override
    public final void formatTo( final Formatter formatter, final int flags, final int width, final int precision )
    {
        if( isNull( formatter ) ) throw new NullPointerException( "formatter is null" );

        if( (flags & ALTERNATE) == ALTERNATE )
        {
            final var leftJustified = (flags & LEFT_JUSTIFY) == LEFT_JUSTIFY;

            final var buffer = new StringBuilder();

            final Time [] times = { YEAR, WEEK, DAY, HOUR, MINUTE };
            var result = new BigDecimal [2];
            result [1] = baseValue();
            for( final var t : times )
            {
                result = result [1].divideAndRemainder( t.factor(), MATH_CONTEXT );
                if( result [0].longValue() > 0 )
                {
                    buffer.append( result [0].longValue() ).append( t.unitSymbol() ).append( ' ' );
                }
            }
            buffer.append( format( "%1.3fs", result [1] ) );

            final var s = width < buffer.length() ? buffer.toString() : leftJustified ? padRight( buffer, width ) : padLeft( buffer, width );

            /*
             * We do not use Formatter.out().append() because we do not know how to
             * handle the IOException that could be thrown from
             * Appendable.append(). Using Formatter.format() assumes that Formatter
             * knows ...
             */
            formatter.format( "%s", s );
        }
        else
        {
            super.formatTo( formatter, flags, width, precision );
        }
    }   //  formatTo()

    /**
     *  Translates a
     *  {@link Period}
     *  instance to a
     *  {@link Duration}
     *  instance.
     *
     *  @param  period  The period to translate.
     *  @return The resulting duration.
     */
    private static final Duration translatePeriodToDuration( final Period period )
    {
        final var months = requireNonNullArgument( period, "period" ).toTotalMonths();
        final var days = months * ChronoUnit.MONTHS.getDuration().getSeconds() / ChronoUnit.DAYS.getDuration().getSeconds() + period.getDays();
        final var retValue = Duration.ofDays( days );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  translatePeriodToDuration()
}
//  class TimeValue

/*
 *  End of File
 */