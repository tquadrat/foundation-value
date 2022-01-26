/*
 * ============================================================================
 * Copyright © 2002-2022 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 *
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
import org.tquadrat.foundation.value.api.DimensionedValueStringConverter;

/**
 *  The implementation of
 *  {@link org.tquadrat.foundation.lang.StringConverter}
 *  for
 *  {@link TemperatureValue}
 *  instances.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TemperatureValueStringConverter.java 995 2022-01-23 01:09:35Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: TemperatureValueStringConverter.java 995 2022-01-23 01:09:35Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public final class TemperatureValueStringConverter extends DimensionedValueStringConverter<Temperature,TemperatureValue>
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
    private static final long serialVersionUID = 1L;

    /**
     *  An instance of this class.
     */
    public static final TemperatureValueStringConverter INSTANCE = new TemperatureValueStringConverter();

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new instance of {@code TemperatureValueStringConverter}.
     */
    public TemperatureValueStringConverter() { super( TemperatureValue.class ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "UseOfConcreteClass" )
    @Override
    protected final TemperatureValue createValue( final BigDecimal number, final Temperature dimension )
    {
        return new TemperatureValue( dimension, number );
    }   //  createValue()

    /**
     *  This method is used by the
     *  {@link java.util.ServiceLoader}
     *  to obtain the instance for this
     *  {@link org.tquadrat.foundation.lang.StringConverter}
     *  implementation.
     *
     *  @return The instance for this {@code StringConverter} implementation.
     */
    @SuppressWarnings( "UseOfConcreteClass" )
    public static final TemperatureValueStringConverter provider() { return INSTANCE; }

    /**
     *  {@inheritDoc}
     */
    @Override
    protected Temperature unitFromSymbol( final String unitSymbol )
    {
        return Temperature.forUnit( unitSymbol );
    }   //  dimensionFromUnit()
}
//  class TemperatureValueStringConverter

/*
 *  End of File
 */