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

import static org.apiguardian.api.API.Status.STABLE;

import java.io.Serial;
import java.math.BigDecimal;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.value.api.ValueBase;

/**
 *  A value class for pressure values.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: PressureValue.java 989 2022-01-13 19:09:58Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: PressureValue.java 989 2022-01-13 19:09:58Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public final class PressureValue extends ValueBase<Pressure,PressureValue>
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
     *  Creates a new {@code PressureValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public PressureValue( final Pressure dimension, final BigDecimal value )
    {
        super( dimension, value );
    }   //  PressureValue()

    /**
     *  Creates a new {@code PressureValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value; it must be possible to parse the given
     *      String into a
     *      {@link BigDecimal}.
     *  @throws NumberFormatException   The provided value cannot be converted
     *      into a {@code BigDecimal}.
     */
    public PressureValue( final Pressure dimension, final String value ) throws NumberFormatException
    {
        super( dimension, value );
    }   //  PressureValue()

    /**
     *  Creates a new {@code PressureValue} instance.
     *
     *  @param  <N> The type of {@code value}.
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public <N extends Number> PressureValue( final Pressure dimension, final N value )
    {
        super( dimension, value );
    }   //  PressureValue()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "UseOfConcreteClass" )
    @Override
    public final PressureValue clone()
    {
        final var retValue = (PressureValue) super.clone();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  clone()
}
//  class PressureValue

/*
 *  End of File
 */