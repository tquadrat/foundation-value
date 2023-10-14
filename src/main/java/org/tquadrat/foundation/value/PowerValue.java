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

import static org.apiguardian.api.API.Status.STABLE;

import java.io.Serial;
import java.math.BigDecimal;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.value.api.ValueBase;

/**
 *  A value class for Powers.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: PowerValue.java 1073 2023-10-01 11:08:51Z tquadrat $
 *  @since 0.3.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: PowerValue.java 1073 2023-10-01 11:08:51Z tquadrat $" )
@API( status = STABLE, since = "0.3.0" )
public final class PowerValue extends ValueBase<Power,PowerValue>
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
     *  Creates a new {@code PowerValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public PowerValue( final Power dimension, final BigDecimal value )
    {
        //---* Daddy's performing the null check ... *-------------------------
        //noinspection unchecked
        super( dimension, value, DEFAULT_VALIDATOR );
    }   //  PowerValue()

    /**
     *  Creates a new {@code PowerValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value; it must be possible to parse the given
     *      String into a
     *      {@link BigDecimal}.
     *  @throws NumberFormatException   The provided value cannot be converted
     *      into a {@code BigDecimal}.
     */
    public PowerValue( final Power dimension, final String value ) throws NumberFormatException
    {
        //---* Daddy's performing the null check ... *-------------------------
        //noinspection unchecked
        super( dimension, value, DEFAULT_VALIDATOR );
    }   //  PowerValue()

    /**
     *  Creates a new {@code PowerValue} instance.
     *
     *  @param  <N> The type of {@code value}.
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public <N extends Number> PowerValue( final Power dimension, final N value )
    {
        //---* Daddy's performing the null check ... *-------------------------
        //noinspection unchecked
        super( dimension, value, DEFAULT_VALIDATOR );
    }   //  PowerValue()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final PowerValue clone()
    {
        @SuppressWarnings( "cast" )
        final var retValue = super.clone();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  clone()
}
//  class PowerValue

/*
 *  End of File
 */