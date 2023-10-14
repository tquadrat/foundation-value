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
import static java.math.BigDecimal.TEN;
import static java.util.Arrays.stream;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.CommonConstants.TROPICAL_YEAR;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.CommonConstants;
import org.tquadrat.foundation.value.api.DimensionWithLinearConversion;

/**
 *  <p>{@summary The various instances of time periods &hellip;}</p>
 *  <p>The instance of {@code Time} refers to the respective instances of
 *  {@link java.util.concurrent.TimeUnit}
 *  and
 *  {@link java.time.temporal.ChronoUnit}
 *  wherever possible.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Time.java 1073 2023-10-01 11:08:51Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "NewClassNamingConvention" )
@ClassVersion( sourceVersion = "$Id: Time.java 1073 2023-10-01 11:08:51Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public enum Time implements DimensionWithLinearConversion
{
        /*------------------*\
    ====** Enum Declaration **=================================================
        \*------------------*/
    /**
     *  A nanosecond.
     */
    NANOSECOND( new BigDecimal( "1E-9" ), "ns", TimeUnit.NANOSECONDS, ChronoUnit.NANOS ),

    /**
     *  A microsecond.
     */
    MICROSECOND( new BigDecimal( "1E-6" ), "µs", TimeUnit.MICROSECONDS, ChronoUnit.MICROS ),

    /**
     *  A millisecond.
     */
    MILLISECOND( new BigDecimal( "0.001" ), "ms", TimeUnit.MILLISECONDS, ChronoUnit.MILLIS ),

    /**
     *  A second.
     */
    SECOND( BigDecimal.ONE, "s", TimeUnit.SECONDS, ChronoUnit.SECONDS ),

    /**
     *  A minute.
     */
    MINUTE( new BigDecimal( "60" ), "min", TimeUnit.MINUTES, ChronoUnit.MINUTES ),

    /**
     *  An hour.
     */
    HOUR( new BigDecimal( "3600.0" ), "h", TimeUnit.HOURS, ChronoUnit.HOURS ),

    /**
     *  Half a day.
     */
    HALF_DAY( new BigDecimal( "43200.0" ), "d/2", null, ChronoUnit.HALF_DAYS )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String unitSymbolForPrinting() { return "½d"; }
    },

    /**
     *  A day.
     */
    DAY( new BigDecimal( "86400.0" ), "d", TimeUnit.DAYS, ChronoUnit.DAYS ),

    /**
     *  A week (7 days).
     */
    WEEK( new BigDecimal( "604800.0" ), "w", null, ChronoUnit.WEEKS ),

    /**
     *  A fortnight (2 weeks or 14 days).
     */
    FORTNIGHT( new BigDecimal( "1209600.0" ), "fortnight", null, null )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        protected final TemporalUnit asTemporalUnit()
        {
            final var retValue = new TemporalUnitImpl( "Fortnights" );

            //---* Done *------------------------------------------------------
            //noinspection ReturnOfInnerClass
            return retValue;
        }   //  asTemporalUnit
    },

    /**
     *  <p>{@summary A bank month.}</p>
     *  <p>This has a fixed length of exact 30 days.</p>
     */
    BANK_MONTH( new BigDecimal( "2592000.0" ), "month30", null, null )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        protected final TemporalUnit asTemporalUnit()
        {
            final var retValue = new TemporalUnitImpl( "BankMonths" );

            //---* Done *------------------------------------------------------
            //noinspection ReturnOfInnerClass
            return retValue;
        }   //  asTemporalUnit
    },

    /**
     *  <p>{@summary A month.}</p>
     *  <p>This is the average month, calculated using the average year with
     *  365.2425 days divided by 12.</p>
     */
    MONTH( new BigDecimal( "2629746.0" ), "month", null, ChronoUnit.MONTHS ),

    /**
     *  <p>{@summary A bank year.}</p>
     *  <p>This has a fixed length of exact 360 days.</p>
     */
    BANK_YEAR( new BigDecimal( "31104000.0" ), "yr360", null, null )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        protected final TemporalUnit asTemporalUnit()
        {
            final var retValue = new TemporalUnitImpl( "BankYears" );

            //---* Done *------------------------------------------------------
            //noinspection ReturnOfInnerClass
            return retValue;
        }   //  asTemporalUnit
    },

    /**
     *  A tropical year, according to SI.
     *
     *  @see CommonConstants#TROPICAL_YEAR
     */
    YEAR( new BigDecimal( "86400.0" ).multiply( TROPICAL_YEAR ), "yr", null, null )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        protected final TemporalUnit asTemporalUnit()
        {
            final var retValue = new TemporalUnitImpl( "TropicalYears" );

            //---* Done *------------------------------------------------------
            //noinspection ReturnOfInnerClass
            return retValue;
        }   //  asTemporalUnit
    },

    /**
     *  <p>{@summary A year.}</p>
     *  <p>This is calculated as the average year with 365.2425 days.</p>
     *
     * @see ChronoUnit#YEARS
     */
    SIMPLE_YEAR( new BigDecimal( ChronoUnit.YEARS.getDuration().get( ChronoUnit.SECONDS ) ), "yrs", null, ChronoUnit.YEARS ),

    /**
     *  <p>{@summary A decade.}</p>
     *  <p>This is calculated as ten average years with 365.2425 days each.</p>
     *
     * @see ChronoUnit#DECADES
     */
    DECADE( new BigDecimal( ChronoUnit.DECADES.getDuration().get( ChronoUnit.SECONDS ) ), "decade", null, ChronoUnit.DECADES ),

    /**
     *  <p>{@summary A century.}</p>
     *  <p>This is calculated as one hundred average years with 365.2425 days
     *  each.</p>
     *
     * @see ChronoUnit#CENTURIES
     */
    CENTURY( new BigDecimal( ChronoUnit.CENTURIES.getDuration().get( ChronoUnit.SECONDS ) ).multiply( TEN ), "century", null, ChronoUnit.CENTURIES );

        /*---------------*\
    ====** Inner Classes **====================================================
        \*---------------*/
    /**
     *  An implementation of
     *  {@link TemporalUnit},
     *  based on the settings for the {@code Time} instance.
     *
     *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
     *  @version $Id: Time.java 1073 2023-10-01 11:08:51Z tquadrat $
     *  @since 0.1.0
     *
     *  @UMLGraph.link
     */
    @ClassVersion( sourceVersion = "$Id: Time.java 1073 2023-10-01 11:08:51Z tquadrat $" )
    @API( status = STABLE, since = "0.1.0" )
    private class TemporalUnitImpl implements TemporalUnit
    {
            /*------------*\
        ====** Attributes **===================================================
            \*------------*/
        /**
         *  The duration for this temporal unit.
         */
        private final Duration m_Duration;

        /**
         *  The name of this temporal unit.
         */
        private final String m_Name;

            /*--------------*\
        ====** Constructors **=================================================
            \*--------------*/
        /**
         *  Creates a new {@code TemporalUnitImpl} instance.
         *
         *  @param  name    The name of this temporal unit.
         */
        public TemporalUnitImpl( final String name )
        {
            m_Duration = Duration.ofSeconds( Time.this.factor().longValue() );
            m_Name = name;
        }   //  TemporalUnitImpl()

            /*---------*\
        ====** Methods **======================================================
            \*---------*/
        /**
         *  {@inheritDoc}
         */
        @SuppressWarnings( "unchecked" )
        @Override
        public <R extends Temporal> R addTo( final R temporal, final long amount )
        {
            return (R) temporal.plus( amount, this );
        }   //  addTo()

        /**
         *  {@inheritDoc}
         */
        @Override
        public long between( final Temporal temporal1Inclusive, final Temporal temporal2Exclusive )
        {
            return temporal1Inclusive.until( temporal2Exclusive, this );
        }   //  between()

        /**
         *  {@inheritDoc}
         */
        @Override
        public final Duration getDuration() { return m_Duration; }

        /**
         *  {@inheritDoc}
         */
        @Override
        public final boolean isDurationEstimated() { return true; }

        /**
         *  {@inheritDoc}
         */
        @Override
        public final boolean isDateBased() { return false; }

        /**
         *  {@inheritDoc}
         */
        @Override
        public final boolean isTimeBased() { return false; }

        /**
         *  {@inheritDoc}
         */
        @Override
        public final String toString() { return m_Name; }
    }
    //  class TemporalUnitImpl()

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

    /**
     *  The time unit as used by the {@code java.time} package.
     */
    @SuppressWarnings( "OptionalUsedAsFieldOrParameterType" )
    private final Optional<TemporalUnit> m_TemporalUnit;

    /**
     *  The time unit as used by the {@code java.util.concurrent} package.
     */
    private final TimeUnit m_TimeUnit;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code Time} instance.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol  The unit symbol.
     *  @param  timeUnit    The time unit as for the
     *      {@code java.util.concurrent} package; may be {@code null}.
     *  @param  temporalUnit    The time unit as for the {@code java.time}
     *      package; may be {@code null}.
     */
    private Time( final BigDecimal factor, final String unitSymbol, final TimeUnit timeUnit, final TemporalUnit temporalUnit )
    {
        m_Factor = factor.stripTrailingZeros();
        m_UnitSymbol = unitSymbol;
        m_TimeUnit = timeUnit;
        m_TemporalUnit = Optional.ofNullable( temporalUnit );
    }   //  Time()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns this instance of {@code Time} as an instance of
     *  {@link TemporalUnit}.
     *
     *  @return {@code this} as {@code TemporalUnit}.
     */
    @SuppressWarnings( "static-method" )
    protected TemporalUnit asTemporalUnit() { return null; }

    /**
     *  {@inheritDoc}
     */
    @Override
    @SuppressWarnings( "unchecked" )
    public final Time baseUnit() { return SECOND; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final BigDecimal factor() { return m_Factor; }

    /**
     *  Returns the {@code Time} instance for the given instance of
     *  {@link ChronoUnit}.
     *
     *  @param  unit    The unit.
     *  @return The respective instance.
     *  @throws IllegalArgumentException    The given unit is unknown.
     */
    public static final Time forUnit( final ChronoUnit unit ) throws IllegalArgumentException
    {
        requireNonNullArgument( unit, "unit" );

        final var retValue = stream( values() )
            .filter( v -> v.getTemporalUnit() == unit )
            .findFirst()
            .orElseThrow( () -> new IllegalArgumentException( format( MSG_UnknownUnit, unit.name() ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  forUnit()

    /**
     *  Returns the {@code Time} instance for the given instance of
     *  {@link TemporalUnit}.
     *
     *  @param  unit    The unit.
     *  @return The respective instance.
     *  @throws IllegalArgumentException    The given unit is unknown.
     */
    public static final Time forUnit( final TemporalUnit unit ) throws IllegalArgumentException
    {
        requireNonNullArgument( unit, "unit" );

        final var retValue = stream( values() )
            .filter( v -> v.getTemporalUnit() == unit )
            .findFirst()
            .orElseThrow( () -> new IllegalArgumentException( format( MSG_UnknownUnit, unit.toString() ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  forUnit()

    /**
     *  Returns the {@code Time} instance for the given instance of
     *  {@link TimeUnit}.
     *
     *  @param  unit    The unit.
     *  @return The respective instance.
     *  @throws IllegalArgumentException    The given unit is unknown.
     */
    public static final Time forUnit( final TimeUnit unit ) throws IllegalArgumentException
    {
        requireNonNullArgument( unit, "unit" );

        final var retValue = stream( values() )
            .filter( v -> v.getTimeUnit() == unit )
            .findFirst()
            .orElseThrow( () -> new IllegalArgumentException( format( MSG_UnknownUnit, unit.name() ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  forUnit()

    /**
     *  Returns the {@code Time} instance for the given unit.
     *
     *  @param  unitSymbol  The unit symbol.
     *  @return The respective instance.
     *  @throws IllegalArgumentException    The given unit is unknown.
     */
    public static final Time forUnit( final String unitSymbol ) throws IllegalArgumentException
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
     *  Returns the correspondent
     *  {@link TemporalUnit}
     *  for this instance, as used by the {@code java.time}
     *  package. Because  we define here more units as in that package, the
     *  return value may be {@code null}.
     *
     *  @return The corresponding temporal unit instance, or {@code null} if
     *      there is no corresponding time unit.
     */
    @SuppressWarnings( "PublicMethodNotExposedInInterface" )
    public final TemporalUnit getTemporalUnit() { return m_TemporalUnit.orElse( asTemporalUnit() ); }

    /**
     *  Returns the correspondent
     *  {@link TimeUnit}
     *  for this instance, as used by the {@code java.util.concurrent}
     *  package. Because  we define here more units as in that package, the
     *  return value may be {@code null}.
     *
     *  @return The corresponding time unit instance, or {@code null} if
     *      there is no corresponding time unit.
     */
    @SuppressWarnings( "PublicMethodNotExposedInInterface" )
    public final TimeUnit getTimeUnit() { return m_TimeUnit; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final String unitSymbol() { return m_UnitSymbol; }
}
//  enum Time

/*
 *  End of File
 */