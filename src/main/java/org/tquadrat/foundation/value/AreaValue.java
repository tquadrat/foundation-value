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
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.value.Area.SQUARE_METER;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.function.BiPredicate;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.value.api.ValueBase;

/**
 *  A value class for areas.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: AreaValue.java 1073 2023-10-01 11:08:51Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: AreaValue.java 1073 2023-10-01 11:08:51Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public final class AreaValue extends ValueBase<Area, AreaValue>
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  <p>{@summary The validator for areas.}</p>
     *  An area may not be less than 0.
     */
    private static final BiPredicate<Area, BigDecimal> AREA_VALIDATOR = ( unit, value) -> !(value.signum() < 0);

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
     *  Creates a new {@code AreaValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public AreaValue( final Area dimension, final BigDecimal value )
    {
        //---* Daddy's performing the null check ... *-------------------------
        super( dimension, value, AREA_VALIDATOR );
    }   //  AreaValue()

    /**
     *  Creates a new {@code AreaValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value; it must be possible to parse the given
     *      String into a
     *      {@link BigDecimal}.
     *  @throws NumberFormatException   The provided value cannot be converted
     *      into a {@code BigDecimal}.
     */
    public AreaValue( final Area dimension, final String value ) throws NumberFormatException
    {
        //---* Daddy's performing the null check ... *-------------------------
        super( dimension, value, AREA_VALIDATOR );
    }   //  AreaValue()

    /**
     *  Creates a new {@code AreaValue} instance.
     *
     *  @param  <N> The type of {@code value}.
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public <N extends Number> AreaValue( final Area dimension, final N value )
    {
        //---* Daddy's performing the null check ... *-------------------------
        super( dimension, value, AREA_VALIDATOR );
    }   //  AreaValue()

    /**
     *  <p>{@summary Creates a new {@code AreaValue} instance.} An area can be
     *  determined by multiplication of length and width.</p>
     *
     *  @param  dimension   The dimension.
     *  @param  length  The length.
     *  @param  width   The width.
     */
    @SuppressWarnings( "UseOfConcreteClass" )
    public AreaValue( final Area dimension, final LengthValue length, final LengthValue width )
    {
        super( SQUARE_METER, requireNonNullArgument( length, "length" ).baseValue().multiply( requireNonNullArgument( width, "width" ).baseValue() ), AREA_VALIDATOR );
        setUnit( requireNonNullArgument( dimension, "dimension" ) );
    }   //  AreaValue()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final AreaValue clone()
    {
        @SuppressWarnings( "cast" )
        final var retValue = super.clone();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  clone()
}
//  class AreaValue

/*
 *  End of File
 */