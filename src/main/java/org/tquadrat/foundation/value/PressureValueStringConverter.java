/*
 * ============================================================================
 * Copyright © 2002-2021 by Thomas Thrien.
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

import java.math.BigDecimal;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.value.api.DimensionedValueStringConverter;

/**
 *  The implementation of
 *  {@link org.tquadrat.foundation.lang.StringConverter}
 *  for
 *  {@link PressureValue}
 *  instances.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: PressureValueStringConverter.java 827 2021-01-04 17:01:34Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: PressureValueStringConverter.java 827 2021-01-04 17:01:34Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public class PressureValueStringConverter extends DimensionedValueStringConverter<Pressure,PressureValue>
{
        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  An instance of this class.
     */
    public static final PressureValueStringConverter INSTANCE = new PressureValueStringConverter();

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new instance of {@code PressureValueStringConverter}.
     */
    public PressureValueStringConverter() { super( PressureValue.class ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "UseOfConcreteClass" )
    @Override
    protected final PressureValue createValue( final BigDecimal number, final Pressure dimension )
    {
        return new PressureValue( dimension, number );
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
    public static final PressureValueStringConverter provider() { return INSTANCE; }

    /**
     *  {@inheritDoc}
     */
    @Override
    protected Pressure unitFromSymbol( final String unit )
    {
        return Pressure.forUnit( unit );
    }   //  unitFromSymbol()
}
//  class PressureValueStringConverter

/*
 *  End of File
 */