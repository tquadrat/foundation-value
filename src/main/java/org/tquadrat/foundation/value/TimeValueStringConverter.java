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
 *  {@link TimeValue}
 *  instances.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TimeValueStringConverter.java 827 2021-01-04 17:01:34Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: TimeValueStringConverter.java 827 2021-01-04 17:01:34Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public class TimeValueStringConverter extends DimensionedValueStringConverter<Time,TimeValue>
{
        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  An instance of this class.
     */
    public static final TimeValueStringConverter INSTANCE = new TimeValueStringConverter();

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new instance of {@code TimeValueStringConverter}.
     */
    public TimeValueStringConverter() { super( TimeValue.class ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    protected final TimeValue createValue( final BigDecimal number, final Time dimension )
    {
        return new TimeValue( dimension, number );
    }   //  createValue()

    /**
     *  {@inheritDoc}
     */
    @Override
    protected Time unitFromSymbol( final String unit )
    {
        return Time.forUnit( unit );
    }   //  unitFromSymbol()
}
//  class TimeValueStringConverter

/*
 *  End of File
 */