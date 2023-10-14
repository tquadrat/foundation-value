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
import static java.util.FormattableFlags.LEFT_JUSTIFY;
import static java.util.Locale.ROOT;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.hash;
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;
import static org.tquadrat.foundation.util.StringUtils.padRight;
import static org.tquadrat.foundation.value.api.DimensionedValue.MATH_CONTEXT;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Formattable;
import java.util.Formatter;
import java.util.IllegalFormatException;
import java.util.Locale;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.UnexpectedExceptionError;
import org.tquadrat.foundation.lang.Objects;
import org.tquadrat.foundation.value.api.Dimension;
import org.tquadrat.foundation.value.api.DimensionedValue;

/**
 *  <p>{@summary A value type for currency values.}</p>
 *  <p>As there is no constant conversion between currencies, this value type
 *  not implementing the interface
 *  {@link DimensionedValue}.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: CurrencyValue.java 1072 2023-09-30 20:44:38Z tquadrat $
 *  @since 0.0.4
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: CurrencyValue.java 1072 2023-09-30 20:44:38Z tquadrat $" )
@API( status = STABLE, since = "0.0.4" )
public final class CurrencyValue implements Cloneable, Comparable<CurrencyValue>, Formattable, Serializable
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The unit for the value.
     */
    private final Currency m_Unit;

    /**
     *  The numerical value for this instance.
     */
    private final BigDecimal m_Value;

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The serial version UID for objects of this class: {@value}.
     */
    @Serial
    private static final long serialVersionUID = -2075489505691464486L;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code CurrencyValue} instance.
     *
     *  @param  unit   The unit.
     *  @param  value   The value; only absolute (positive) values are allowed,
     *      a sign will be stripped.
     */
    public CurrencyValue( final Currency unit, final BigDecimal value )
    {
        m_Unit = requireNonNullArgument( unit, "unit" );
        m_Value = requireNonNullArgument( value, "value" ).abs().stripTrailingZeros();
    }   //  CurrencyValue()

    /**
     *  Creates a new {@code CurrencyValue} instance.
     *
     *  @param  unit   The unit.
     *  @param  value   The value; it must be possible to parse the given
     *      String into a
     *      {@link BigDecimal}.
     *  @throws NumberFormatException   The provided value cannot be converted
     *      into a {@code BigDecimal}.
     */
    public CurrencyValue( final Currency unit, final String value ) throws NumberFormatException
    {
        this( unit, new BigDecimal( requireNotEmptyArgument( value, "value" ) ) );
    }   //  CurrencyValue()

    /**
     *  Creates a new {@code CurrencyValue} instance.
     *
     *  @param  <N> The type of {@code value}.
     *  @param  unit   The unit.
     *  @param  value   The value.
     */
    public <N extends Number> CurrencyValue( final Currency unit, final N value )
    {
        this( unit, new BigDecimal( requireNonNullArgument( value, "value" ).toString() ) );
    }   //  CurrencyValue()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  <p>{@summary Returns the amount.}</p>
     *
     *  @return The numerical value for this instance of {@code CurrencyValue}.
     */
    public final BigDecimal baseValue() { return m_Value; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final CurrencyValue clone()
    {
        final CurrencyValue retValue;
        try
        {
            retValue = (CurrencyValue) super.clone();
        }
        catch( final CloneNotSupportedException e )
        {
            throw new UnexpectedExceptionError( e );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  clone()

    /**
     *  {@inheritDoc}
     *
     *  @throws IllegalArgumentException    The currencies for both values are
     *      different.
     */
    @SuppressWarnings( "UseOfConcreteClass" )
    @Override
    public final int compareTo( final CurrencyValue other )
    {
        if( !m_Unit.equals( requireNonNullArgument( other, "other" ).m_Unit ) ) throw new IllegalArgumentException( "Currency differs" );
        final var retValue = Integer.signum( m_Value.compareTo( other.m_Value ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  compareTo()

    /**
     *  Creates a new copy of this value.
     *
     *  @return The copy.
     *
     *  @see Object#clone()
     */
    public final CurrencyValue copy()
    {
        final var retValue = clone();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  copy()

    /**
     *  Creates a new instance of {@code CurrencyValue} for a different
     *  {@link Currency}.
     *
     *  @param  unit    The {@code Currency} for the new value.
     *  @param  conversionFactor    The value for this instance multiplied with
     *      the given factor results in the value for the instance.
     *  @return The new instance.
     */
    public final CurrencyValue copy( final Currency unit, final BigDecimal conversionFactor )
    {
        final var retValue = new CurrencyValue( requireNonNullArgument( unit, "unit" ), m_Value.multiply( requireNonNullArgument( conversionFactor, "conversionFactor" ), MATH_CONTEXT ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  copy()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean equals( final Object obj )
    {
        var retValue = this == obj;
        if( !retValue && (obj instanceof final CurrencyValue other ) )
        {
            retValue = Objects.equals( m_Unit, other.m_Unit ) && Objects.equals( m_Value, other.m_Value );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  equals()

    /**
     *  {@inheritDoc}
     *  <p>The precision is applied to the numerical part only. The width
     *  includes the
     *  {@linkplain Currency#getSymbol(Locale) currency symbol},
     *  too.</p>
     *
     *  @note In case the {@code formatter} argument is {@code null}, this
     *      method throws a {@code NullPointerException} and <i>not</i> the
     *      usual {@code NullArgumentException}, because this method is usually
     *      called by instances of {@code java.util.Formatter}, and those do
     *      not know about our special exceptions.
     *
     *  @throws NullPointerException    The {@code formatter} argument is
     *      {@code null}.
     *
     *  @see java.util.Formatter
     */
    @SuppressWarnings( "ProhibitedExceptionThrown" )
    @Override
    public final void formatTo( final Formatter formatter, final int flags, final int width, final int precision )
    {
        if( isNull( formatter ) ) throw new NullPointerException( "formatter is null" );

        var string = toString( formatter.locale(), width, precision < 0 ? m_Unit.getDefaultFractionDigits() : precision );

        if( ((flags & LEFT_JUSTIFY) == LEFT_JUSTIFY) && (width > string.trim().length()) )
        {
            string = padRight( string.trim(), width );
        }

        /*
         * We do not use Formatter.out().append() because we do not know how to
         * handle the IOException that could be thrown from
         * Appendable.append(). Using Formatter.format() assumes that Formatter
         * knows ...
         */
        formatter.format( "%s", string );
    }   //  formatTo()

    /**
     *  Returns the unit for the value.
     *
     *  @return The unit.
     */
    public final Currency getUnit() { return m_Unit; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final int hashCode() { return hash( m_Value, m_Unit ); }

    /**
     *  <p>{@summary Provides a String representation of this value}, in the
     *  format</p>
     *  <pre><code>&lt;<i>numerical value</i>&gt;&nbsp;&lt;<i>currency symbol</i>&gt;</code></pre>
     *  <p>and for the
     *  {@linkplain Locale#getDefault() default Locale},
     *  like &quot;{@code 4.50 €}&quot;, where the Locale determines the
     *  decimal separator.</p>
     *  <p>The precision is applied to the numerical part only. The width
     *  includes the
     *  {@linkplain Currency#getSymbol(Locale) currency symbol}, too.</p>
     *
     *  @param  width   The minimum number of characters to be written to the
     *      output. If the length of the converted value is less than the width
     *      then the output will be padded by '&nbsp;' until the total number
     *      of characters equals width. The padding is at the beginning, as
     *      numerical values are usually right justified. If {@code width} is
     *      -1 then there is no minimum.
     *  @param  precision – The number of digits for the mantissa of the value.
     *      If {@code precision} is -1 then there is no explicit limit on the
     *      size of the mantissa.
     *  @return The String representation for this value.
     */
    public final String toString( final int width, final int precision )
    {
        final var retValue = toString( Locale.getDefault(), width, precision );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()

    /**
     *  <p>{@summary Provides a String representation of this value}, in the
     *  format</p>
     *  <pre><code>&lt;<i>numerical value</i>&gt;&nbsp;&lt;<i>currency symbol</i>&gt;</code></pre>
     *  <p>and for the
     *  {@linkplain Locale#getDefault() default Locale},
     *  like &quot;{@code 4.50 €}&quot;, where the Locale determines the
     *  decimal separator.</p>
     *  <p>The precision is taken from the
     *  {@link Currency#getDefaultFractionDigits() currency}
     *  and applied to the numerical part only. The width includes the
     *  {@linkplain Currency#getSymbol(Locale) currency symbol}, too.</p>
     *
     *  @param  width   The minimum number of characters to be written to the
     *      output. If the length of the converted value is less than the width
     *      then the output will be padded by '&nbsp;' until the total number
     *      of characters equals width. The padding is at the beginning, as
     *      numerical values are usually right justified. If {@code width} is
     *      -1 then there is no minimum.
     *  @return The String representation for this value.
     */
    public final String toString( final int width )
    {
        final var retValue = toString( width, m_Unit.getDefaultFractionDigits() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()

    /**
     *  <p>{@summary Provides a String representation of this value}, in the
     *  format</p>
     *  <pre><code>&lt;<i>numerical value</i>&gt;&nbsp;&lt;<i>currency symbol</i>&gt;</code></pre>
     *  <p>for the given
     *  {@link Locale}
     *  that determines the decimal separator, like &quot;{@code 4.50 €}&quot;
     *  vs. &quot;{@code 4,50 €}&quot;.</p>
     *  <p>The precision is applied to the numerical part only. The width
     *  includes the
     *  {@linkplain Currency#getSymbol(Locale) currency symbol}, too.</p>
     *
     *  @param  locale  The locale to use.
     *  @param  width   The minimum number of characters to be written to the
     *      output. If the length of the converted value is less than the width
     *      then the output will be padded by '&nbsp;' until the total number
     *      of characters equals width. The padding is at the beginning, as
     *      numerical values are usually right justified. If {@code width} is
     *      -1 then there is no minimum.
     *  @param  precision – The number of digits for the mantissa of the value.
     *      If {@code precision} is -1 then there is no explicit limit on the
     *      size of the mantissa.
     *  @return The String representation for this value.
     */
    public final String toString( final Locale locale, final int width, final int precision )
    {
        final var retValue = toString( locale, width, precision, false );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()

    /**
     *  <p>{@summary Provides a String representation of this value}, in the
     *  format</p>
     *  <pre><code>&lt;<i>numerical value</i>&gt;&nbsp;&lt;<i>currency symbol</i>&gt;</code></pre>
     *  <p>for the given
     *  {@link Locale}
     *  that determines the decimal separator, like &quot;{@code 4.50 €}&quot;
     *  vs. &quot;{@code 4,50 €}&quot;.</p>
     *  <p>The precision is taken from the
     *  {@link Currency#getDefaultFractionDigits() currency}
     *  and applied to the numerical part only. The width includes the
     *  {@linkplain Currency#getSymbol(Locale) currency symbol}, too.</p>
     *
     *  @param  locale  The locale to use.
     *  @param  width   The minimum number of characters to be written to the
     *      output. If the length of the converted value is less than the width
     *      then the output will be padded by '&nbsp;' until the total number
     *      of characters equals width. The padding is at the beginning, as
     *      numerical values are usually right justified. If {@code width} is
     *      -1 then there is no minimum.
     *  @return The String representation for this value.
     */
    public final String toString( final Locale locale, final int width )
    {
        final var retValue = toString( locale, width, m_Unit.getDefaultFractionDigits() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()

    /**
     *  <p>{@summary Provides a String representation of this value}, in the
     *  format</p>
     *  <pre><code>&lt;<i>numerical value</i>&gt;&nbsp;&lt;<i>currency symbol</i>&gt;</code></pre>
     *  <p>for the given
     *  {@link Locale}
     *  that determines the decimal separator, like &quot;{@code 4.50 €}&quot;
     *  vs. &quot;{@code 4,50 €}&quot;.</p>
     *  <p>The precision is applied to the numerical part only. The width
     *  includes the
     *  {@linkplain Dimension#unitSymbol() unit symbol}, too.</p>
     *
     *  @param  locale  The locale to use.
     *  @param  width   The minimum number of characters to be written to the
     *      output. If the length of the converted value is less than the width
     *      then the output will be padded by '&nbsp;' until the total number
     *      of characters equals width. The padding is at the beginning, as
     *      numerical values are usually right justified. If {@code width} is
     *      -1 then there is no minimum.
     *  @param  precision – The number of digits for the mantissa of the value.
     *      If {@code precision} is -1 then there is no explicit limit on the
     *      size of the mantissa.
     *  @param  useNiceUnit {@code true} if the method
     *      {@link Dimension#unitSymbolForPrinting() unitSymbolForPrinting()}
     *      should be used to retrieve the unit symbol, {@code false} if the
     *      usual one is sufficient.
     *  @return The String representation for this value.
     */
    public final String toString( final Locale locale, final int width, final int precision, final boolean useNiceUnit )
    {
        requireNonNullArgument( locale, "locale" );
        final var unitSymbol = useNiceUnit ? m_Unit.getSymbol( locale ) : m_Unit.getCurrencyCode();
        final var effectiveWidth = width - unitSymbol.length() - 1;

        final var format = new StringBuilder( "%" );
        if( effectiveWidth > 0 ) format.append( effectiveWidth );
        if( precision >= 0 ) format.append( "." ).append( precision );
        format.append( "f %s" );

        final var retValue = format( locale, format.toString(), m_Value, unitSymbol );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()

    /**
     *  <p>{@summary Provides a String representation of this value}, in the
     *  format that is defined by the provided format String.</p>
     *  <p>That format String must contain exactly one '%f' tag and one '%s'
     *  tag; the first takes the numerical value, the second the unit.</p>
     *  <p>The provided
     *  {@link Locale}
     *  determines the decimal separator and the optional thousands
     *  separator.</p>
     *
     *  @param  locale  The locale to use.
     *  @param  format  The format String.
     *  @param  useNiceUnit {@code true} if the method
     *      {@link Dimension#unitSymbolForPrinting() unitSymbolForPrinting()}
     *      should be used to retrieve the unit symbol, {@code false} if the
     *      usual one is sufficient.
     *  @return The String representation for this value.
     *  @throws IllegalFormatException  The provided format String is invalid.
     *
     *  @see java.util.Formatter
     */
    public final String toString( final Locale locale, final String format, final boolean useNiceUnit ) throws IllegalFormatException
    {
        requireNonNullArgument( locale, "locale" );
        final var unitSymbol = useNiceUnit ? m_Unit.getSymbol( locale ) : m_Unit.getCurrencyCode();
        final var retValue = format( locale, requireNotEmptyArgument( format, "format" ), m_Value, unitSymbol );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final String toString() { return toString( ROOT, -1, m_Unit.getDefaultFractionDigits(), false ); }
}
//  class ValueBase

/*
 *  End of File
 */