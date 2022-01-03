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

import static java.util.Arrays.stream;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;
import static org.tquadrat.foundation.util.StringUtils.format;

import java.math.BigDecimal;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.value.api.Dimension;

/**
 *  The various instances of pressure.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Pressure.java 827 2021-01-04 17:01:34Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: Pressure.java 827 2021-01-04 17:01:34Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public enum Pressure implements Dimension
{
        /*------------------*\
    ====** Enum Declaration **=================================================
        \*------------------*/
    /**
     *  A milli Pascal.
     */
    MILLI_PASCAL( new BigDecimal( "0.001" ), "mPa" ),

    /**
     *  One Pascal.
     */
    PASCAL( BigDecimal.ONE, "Pa" ),

    /**
     *  Same as
     *  {@link #PASCAL},
     *  but a different unit.
     */
    NEWTON_PER_SQUAREMETER( PASCAL.factor(), "N/m^2" ),

    /**
     *  A hekto Pascal.
     */
    HEKTO_PASCAL( new BigDecimal( "100" ), "hPa" ),

    /**
     *  A milli bar; same as
     *  {@link #HEKTO_PASCAL}.
     */
    MILLI_BAR( HEKTO_PASCAL.factor(), "mbar" ),

    /**
     *  A Torr.
     */
    TORR( new BigDecimal( "133.322368421" ), "Torr" ),

    /**
     *  A mm of Mercury; same as
     *  {@link #TORR}.
     */
    MILLIMETER_OF_MERCURY( TORR.factor(), "mmHg" ),

    /**
     *  An inch of Mercury.
     */
    INCH_OF_MERCURY( new BigDecimal( "3386.3881578" ), "inHg" ),

    /**
     *  A kilo Pascal.
     */
    KILO_PASCAL( new BigDecimal( "1000" ), "kPa" ),

    /**
     *  A psi.
     */
    PSI( new BigDecimal( "6894.757293178" ), "psi" ),

    /**
     *  A technical <i>atmosphere</i> (at).
     */
    AT( new BigDecimal( "98066.5" ), "at" ),

    /**
     *  A <i>bar</i>.
     */
    BAR( new BigDecimal( "100.000" ), "bar" ),

    /**
     *  A physical <i>atmosphere</i> (atm).
     */
    ATM( new BigDecimal( "101325.2738" ), "atm" ),

    /**
     *  A mega Pascal.
     */
    MEGA_PASCAL( new BigDecimal( "1000000" ), "MPa" ),

    /**
     *  Same as
     *  {@link #MEGA_PASCAL},
     *  but a different unit.
     */
    NEWTON_PER_SQUAREMILLIMETER( MEGA_PASCAL.factor(), "N/mm^2" );

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The factor.
     */
    private final BigDecimal m_Factor;

    /**
     *  The unit string.
     */
    private final String m_UnitSymbol;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code Pressure} instance.
     *
     *  @param  factor  The factor.
     *  @param  unitSymbol    The unit string.
     */
    private Pressure( final BigDecimal factor, final String unitSymbol )
    {
        m_Factor = factor.stripTrailingZeros();
        m_UnitSymbol = unitSymbol;
    }   //  Pressure()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    @SuppressWarnings( "unchecked" )
    public final Pressure baseUnit() { return PASCAL; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final BigDecimal factor() { return m_Factor; }

    /**
     *  Returns the {@code Pressure} instance for the given unit.
     *
     *  @param  unitSymbol    The unit symbol.
     *  @return The respective instance.
     *  @throws IllegalArgumentException    The given unit is unknown.
     */
    public static final Pressure forUnit( final String unitSymbol ) throws IllegalArgumentException
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
    public final String unitSymbol() { return m_UnitSymbol; }
}
//  enum Pressure

/*
 *  End of File
 */