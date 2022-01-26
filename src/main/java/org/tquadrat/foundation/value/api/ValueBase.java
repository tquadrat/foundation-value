/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
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

package org.tquadrat.foundation.value.api;

import static java.util.Locale.ROOT;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.hash;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;
import static org.tquadrat.foundation.util.StringUtils.format;

import java.io.Serial;
import java.math.BigDecimal;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.UnexpectedExceptionError;

/**
 *  An abstract base implementation for the interface
 *  {@link DimensionedValue}
 *  that is intended as the base for concrete implementations of value types.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ValueBase.java 995 2022-01-23 01:09:35Z tquadrat $
 *  @since 0.0.4
 *
 *  @param  <D> The dimension.
 *  @param  <I> The implementing type.
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: ValueBase.java 995 2022-01-23 01:09:35Z tquadrat $" )
@API( status = STABLE, since = "0.0.4" )
public abstract class ValueBase<D extends Dimension, I extends DimensionedValue<D>> implements DimensionedValue<D>
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The unit for the value.
     */
    private D m_Unit;

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
     *  Creates a new {@code ValueBase} instance.
     *
     *  @param  unit   The unit.
     *  @param  value   The value; only absolute (positive) values are allowed,
     *      a sign will be stripped.
     */
    protected ValueBase( final D unit, final BigDecimal value )
    {
        requireNonNullArgument( value, "value" );
        setUnit( unit );
        final var conversion = unit.toBase();
        m_Value = validate( conversion.apply( value ) ).abs().stripTrailingZeros();
    }   //  ValueBase()

    /**
     *  Creates a new {@code ValueBase} instance.
     *
     *  @param  unit   The unit.
     *  @param  value   The value; it must be possible to parse the given
     *      String into a
     *      {@link BigDecimal}.
     *  @throws NumberFormatException   The provided value cannot be converted
     *      into a {@code BigDecimal}.
     */
    protected ValueBase( final D unit, final String value ) throws NumberFormatException
    {
        this( unit, new BigDecimal( requireNotEmptyArgument( value, "value" ) ) );
    }   //  ValueBase()

    /**
     *  Creates a new {@code ValueBase} instance.
     *
     *  @param  <N> The type of {@code value}.
     *  @param  unit   The unit.
     *  @param  value   The value.
     */
    protected <N extends Number> ValueBase( final D unit, final N value )
    {
        this( unit, new BigDecimal( requireNonNullArgument( value, "value" ).toString() ) );
    }   //  ValueBase()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final BigDecimal baseValue() { return m_Value; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public I clone()
    {
        final I retValue;
        try
        {
            @SuppressWarnings( "unchecked" )
            final var clone = (I) super.clone();
            retValue = clone;
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
     */
    @Override
    public final I copy()
    {
        final var retValue = clone();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  copy()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final I copy( final D unit )
    {
        final var retValue = copy();
        retValue.setUnit( unit ); // Does the null check ...

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
        if( !retValue && (obj instanceof ValueBase<?,?> other ) )
        {
            if( baseUnit() == other.baseUnit() )
            {
                retValue = baseValue().equals( other.baseValue() );
            }
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  equals()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final D getUnit() { return m_Unit; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final int hashCode() { return hash( baseValue(), baseUnit() ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void setUnit( final D dimension ) { m_Unit = requireNonNullArgument( dimension, "dimension" ); }

    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "GrazieInspection" )
    @Override
    public final String toString() { return format( ROOT, "%s %s", convert( m_Unit ).toString(), m_Unit.unitSymbol() ); }
}
//  class ValueBase

/*
 *  End of File
 */