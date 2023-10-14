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
 *  A value class for weights and masses.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: MassValue.java 1072 2023-09-30 20:44:38Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: MassValue.java 1072 2023-09-30 20:44:38Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public final class MassValue extends ValueBase<Mass,MassValue>
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
     *  Creates a new {@code MassValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public MassValue( final Mass dimension, final BigDecimal value )
    {
        //noinspection unchecked
        super( dimension, value, DEFAULT_VALIDATOR );
    }   //  MassValue()

    /**
     *  Creates a new {@code MassValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value; it must be possible to parse the given
     *      String into a
     *      {@link BigDecimal}.
     *  @throws NumberFormatException   The provided value cannot be converted
     *      into a {@code BigDecimal}.
     */
    public MassValue( final Mass dimension, final String value ) throws NumberFormatException
    {
        //noinspection unchecked
        super( dimension, value, DEFAULT_VALIDATOR );
    }   //  MassValue()

    /**
     *  Creates a new {@code MassValue} instance.
     *
     *  @param  <N> The type of {@code value}.
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public <N extends Number> MassValue( final Mass dimension, final N value )
    {
        //noinspection unchecked
        super( dimension, value, DEFAULT_VALIDATOR );
    }   //  MassValue()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final MassValue clone()
    {
        final var retValue = (MassValue) super.clone();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  clone()
}
//  class MassValue

/*
 *  End of File
 */