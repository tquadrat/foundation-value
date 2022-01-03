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

package org.tquadrat.foundation.value;

import static java.util.Arrays.stream;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;
import static org.tquadrat.foundation.util.StringUtils.format;

import java.math.BigDecimal;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.value.api.Dimension;

/**
 *  The various instances of data sizes (for files or free disk space or
 *  capacities of memory sticks &hellip;).
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: DataSize.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: DataSize.java 820 2020-12-29 20:34:22Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public enum DataSize implements Dimension
{
        /*------------------*\
    ====** Enum Declaration **=================================================
        \*------------------*/
    /**
     *  A single byte.
     */
    BYTE( BigDecimal.ONE, "Byte" ),

    /**
     *  A Kilobyte (with 1000 bytes).
     */
    KILOBYTE( new BigDecimal( "1000.0" ), "kB" ),

    /**
     *  A Kilobyte with 1024 bytes.
     */
    KIBIBYTE( new BigDecimal( "1024.0" ), "KiB" ),

    /**
     *  A megabyte (the decimal form).
     */
    MEGABYTE( new BigDecimal( "1000000.0" ), "MB" ),

    /**
     *  A megabyte in the binary form.
     */
    MEBIBYTE( new BigDecimal( "1048576.0" ), "MiB" ),

    /**
     *  A gigabyte (the decimal form).
     */
    GIGABYTE( new BigDecimal( "1000000000.0" ), "GB" ),

    /**
     *  A gigabyte in the binary form.
     */
    GIBIBYTE( new BigDecimal( "1073741824.0" ), "GiB" ),

    /**
     *  A terabyte (the decimal form).
     */
    TERABYTE( new BigDecimal( "1000000000000.0" ), "TB" ),

    /**
     *  A terabyte in the binary form.
     */
    TEBIBYTE( new BigDecimal( "1099511627776.0" ), "TiB" );

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
     *  Creates a new {@code DataSize} instance.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol    The unit string.
     *  @param  precision   The default precision.
     */
    private DataSize( final BigDecimal factor, final String unitSymbol, final int precision )
    {
        m_Factor = factor.stripTrailingZeros();
        m_UnitSymbol = unitSymbol;
        m_Precision = precision;
    }   //  DataSize()

    /**
     *  Creates a new {@code DataSize} instance, with a default precision of
     *  zero mantissa digits.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol    The unit string.
     */
    private DataSize( final BigDecimal factor, final String unitSymbol )
    {
        this( factor, unitSymbol, 0 );
    }   //  DataSize()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    @SuppressWarnings( "unchecked" )
    public final DataSize baseUnit() { return BYTE; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final BigDecimal factor() { return m_Factor; }

    /**
     *  Returns the {@code DataSize} instance for the given unit.
     *
     *  @param  unit    The unit.
     *  @return The respective instance.
     *  @throws IllegalArgumentException    The given unit is unknown.
     */
    public static final DataSize forUnit( final String unit ) throws IllegalArgumentException
    {
        requireNotEmptyArgument( unit, "unit" );

        final var retValue = stream( values() )
            .filter( v -> v.unitSymbol().equals( unit ) )
            .findFirst()
            .orElseThrow( () -> new IllegalArgumentException( format( MSG_UnknownUnit, unit ) ) );

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
//  enum DataSize

/*
 *  End of File
 */