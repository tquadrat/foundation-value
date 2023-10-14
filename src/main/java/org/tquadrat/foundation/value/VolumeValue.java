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
import static org.tquadrat.foundation.value.Volume.CUBIC_METER;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.function.BiPredicate;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.value.api.ValueBase;

/**
 *  A value class for volumes.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: VolumeValue.java 1073 2023-10-01 11:08:51Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: VolumeValue.java 1073 2023-10-01 11:08:51Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public final class VolumeValue extends ValueBase<Volume, VolumeValue>
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  <p>{@summary The validator for volumes.}</p>
     *  A volume may not be less than 0.
     */
    private static final BiPredicate<Volume, BigDecimal> VOLUME_VALIDATOR = ( unit, value) -> !(value.signum() < 0);

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
     *  Creates a new {@code VolumeValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public VolumeValue( final Volume dimension, final BigDecimal value )
    {
        //---* Daddy's performing the null check ... *-------------------------
        super( dimension, value, VOLUME_VALIDATOR );
    }   //  VolumeValue()

    /**
     *  Creates a new {@code VolumeValue} instance.
     *
     *  @param  dimension   The dimension.
     *  @param  value   The value; it must be possible to parse the given
     *      String into a
     *      {@link BigDecimal}.
     *  @throws NumberFormatException   The provided value cannot be converted
     *      into a {@code BigDecimal}.
     */
    public VolumeValue( final Volume dimension, final String value ) throws NumberFormatException
    {
        //---* Daddy's performing the null check ... *-------------------------
        super( dimension, value, VOLUME_VALIDATOR );
    }   //  VolumeValue()

    /**
     *  Creates a new {@code VolumeValue} instance.
     *
     *  @param  <N> The type of {@code value}.
     *  @param  dimension   The dimension.
     *  @param  value   The value.
     */
    public <N extends Number> VolumeValue( final Volume dimension, final N value )
    {
        //---* Daddy's performing the null check ... *-------------------------
        super( dimension, value, VOLUME_VALIDATOR );
    }   //  VolumeValue()

    /**
     *  <p>{@summary Creates a new {@code VolumeValue} instance.}</p>
     *  <p>A volume can be determined by multiplication of length, width and
     *  height.</p>
     *
     *  @param  dimension   The dimension.
     *  @param  length  The length.
     *  @param  width   The width.
     *  @param  height  The height.
     */
    public VolumeValue( final Volume dimension, @SuppressWarnings( "UseOfConcreteClass" ) final LengthValue length, @SuppressWarnings( "UseOfConcreteClass" ) final LengthValue width, @SuppressWarnings( "UseOfConcreteClass" ) final LengthValue height )
    {
        super( CUBIC_METER, requireNonNullArgument( length, "length" ).baseValue()
            .multiply( requireNonNullArgument( width, "width" ).baseValue() )
            .multiply( requireNonNullArgument( height, "height" ).baseValue() ), VOLUME_VALIDATOR );
        setUnit( requireNonNullArgument( dimension, "dimension" ) );
    }   //  VolumeValue()

    /**
     *  <p>{@summary Creates a new {@code VolumeValue} instance.}</p>
     *  <p>A volume can be determined by multiplication of ground area and
     *  height.</p>
     *
     *  @param  dimension   The dimension.
     *  @param  area    The ground area.
     *  @param  height  The height.
     */
    public VolumeValue( final Volume dimension, @SuppressWarnings( "UseOfConcreteClass" ) final AreaValue area, @SuppressWarnings( "UseOfConcreteClass" ) final LengthValue height )
    {
        super( CUBIC_METER, requireNonNullArgument( area, "area" ).baseValue()
            .multiply( requireNonNullArgument( height, "height" ).baseValue() ), VOLUME_VALIDATOR );
        setUnit( requireNonNullArgument( dimension, "dimension" ) );
    }   //  VolumeValue()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final VolumeValue clone()
    {
        @SuppressWarnings( "cast" )
        final var retValue = super.clone();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  clone()
}
//  class VolumeValue

/*
 *  End of File
 */