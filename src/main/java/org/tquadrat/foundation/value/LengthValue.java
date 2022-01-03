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
 *  A value class for lengths.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: LengthValue.java 880 2021-02-27 10:47:40Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: LengthValue.java 880 2021-02-27 10:47:40Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public final class LengthValue extends ValueBase<Length,LengthValue>
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
     *  Creates a new {@code LengthValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public LengthValue( final Length dimension, final BigDecimal value )
    {
        //---* Daddy's performing the null check ... *-------------------------
        super( dimension, value );
    }   //  LengthValue()

    /**
     *  Creates a new {@code LengthValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value; it must be possible to parse the given
     *      String into a
     *      {@link BigDecimal}.
     *  @throws NumberFormatException   The provided value cannot be converted
     *      into a {@code BigDecimal}.
     */
    public LengthValue( final Length dimension, final String value ) throws NumberFormatException
    {
        //---* Daddy's performing the null check ... *-------------------------
        super( dimension, value );
    }   //  LengthValue()

    /**
     *  Creates a new {@code LengthValue} instance.
     *
     *  @param  <N> The type of {@code value}.
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public <N extends Number> LengthValue( final Length dimension, final N value )
    {
        //---* Daddy's performing the null check ... *-------------------------
        super( dimension, value );
    }   //  LengthValue()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final LengthValue clone()
    {
        @SuppressWarnings( "cast" )
        final var retValue = super.clone();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  clone()
}
//  class LengthValue

/*
 *  End of File
 */